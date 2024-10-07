package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.CartDTO;
import com.ecommerce.motomart.DTO.CartItemDTO;
import com.ecommerce.motomart.DTO.OrderDTO;
import com.ecommerce.motomart.Exceptions.OrderNotFoundException;
import com.ecommerce.motomart.Models.Order;
import com.ecommerce.motomart.Models.Bike;
import com.ecommerce.motomart.Models.OrderItem;
import com.ecommerce.motomart.Models.User;
import com.ecommerce.motomart.Models.Payment;
import com.ecommerce.motomart.Repositories.OrderRepository;
import com.ecommerce.motomart.Repositories.BikeRepository;
import com.ecommerce.motomart.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;

    // Fetch all orders and map them to OrderDTO
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Fetch a single order by its ID
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        OrderDTO orderDTO = convertToDTO(order);
        setPaymentMethod(order, orderDTO); // Set payment method if exists
        return orderDTO;
    }

    // Create a new order from an OrderDTO
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order createdOrder = orderRepository.save(order);
        return convertToDTO(createdOrder);
    }

    // Create a new order from CartDTO
    @Transactional
    public OrderDTO createOrderFromCart(CartDTO cartDTO) {
        double totalAmount = calculateTotalAmount(cartDTO.getCartItems());

        // Fetch the user by ID
        User user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create and populate the order entity
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(cartDTO.getShippingAddress());
        order.setOrderDate(new Date().toString());
        order.setStatus("PENDING");

        // Set order items
        setOrderItems(cartDTO, order);

        // Set the total amount
        order.setTotalAmount(totalAmount);

        // Process payment before saving the order
        Payment payment = paymentService.processPayment(order.getTotalAmount(), user);
        if (payment != null && "SUCCESS".equals(payment.getPaymentStatus())) {
            order.setPayment(payment);
            order.setStatus("CONFIRMED");
        } else {
            throw new RuntimeException("Payment processing failed");
        }

        // Save the order
        Order createdOrder = orderRepository.save(order);
        return convertToDTO(createdOrder);
    }

    // Calculate the total amount from CartItemDTO
    private double calculateTotalAmount(List<CartItemDTO> cartItems) {
        double total = 0.0;
        for (CartItemDTO item : cartItems) {
            Bike bike = bikeRepository.findById(item.getBikeId())
                    .orElseThrow(() -> new RuntimeException("Bike not found"));
            total += bike.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Set order items based on CartDTO
    private void setOrderItems(CartDTO cartDTO, Order order) {
        order.setOrderItems(cartDTO.getCartItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBike(bikeRepository.findById(item.getBikeId())
                            .orElseThrow(() -> new RuntimeException("Bike not found")));
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setOrder(order);
                    return orderItem;
                }).collect(Collectors.toList()));
    }

    // Update an existing order by its ID
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setShippingAddress(orderDTO.getShippingAddress());

        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    // Delete an order by its ID
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.deleteById(id);
    }

    // Convert Order entity to OrderDTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        setPaymentMethod(order, orderDTO);
        return orderDTO;
    }

    // Convert OrderDTO to Order entity
    private Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    // Set the payment method if the payment entity exists
    private void setPaymentMethod(Order order, OrderDTO orderDTO) {
        if (order.getPayment() != null) {
            orderDTO.setPaymentMethod(order.getPayment().getPaymentMethod());
        }
    }
}
