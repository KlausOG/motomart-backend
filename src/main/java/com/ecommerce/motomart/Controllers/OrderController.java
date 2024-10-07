package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.CartDTO; // Import CartDTO
import com.ecommerce.motomart.DTO.OrderDTO;
import com.ecommerce.motomart.Exceptions.OrderNotFoundException;
import com.ecommerce.motomart.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(201).body(createdOrder); // Return 201 Created
    }

    // New endpoint to create an order from a cart
    @PostMapping("/from-cart")
    public ResponseEntity<OrderDTO> createOrderFromCart(@RequestBody CartDTO cartDTO) {
        OrderDTO createdOrder = orderService.createOrderFromCart(cartDTO);
        return ResponseEntity.status(201).body(createdOrder); // Return 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDetails) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
