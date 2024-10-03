package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.VariantDTO;
import com.ecommerce.motomart.Exceptions.VariantNotFoundException;
import com.ecommerce.motomart.Models.Variant;
import com.ecommerce.motomart.Repositories.VariantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantService {

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<VariantDTO> getAllVariants() {
        return variantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VariantDTO getVariantById(Long id) {
        Variant variant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException(id));
        return convertToDTO(variant);
    }

    public VariantDTO createVariant(VariantDTO variantDTO) {
        Variant variant = convertToEntity(variantDTO);
        Variant createdVariant = variantRepository.save(variant);
        return convertToDTO(createdVariant);
    }

    public VariantDTO updateVariant(Long id, VariantDTO variantDTO) {
        Variant existingVariant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException( id));

        existingVariant.setVariantName(variantDTO.getName());
        existingVariant.setAdditionalPrice(variantDTO.getAdditionalPrice());

        Variant updatedVariant = variantRepository.save(existingVariant);
        return convertToDTO(updatedVariant);
    }

    public void deleteVariant(Long id) {
        if (!variantRepository.existsById(id)) {
            throw new VariantNotFoundException(id);
        }
        variantRepository.deleteById(id);
    }

    private VariantDTO convertToDTO(Variant variant) {
        return modelMapper.map(variant, VariantDTO.class);
    }

    private Variant convertToEntity(VariantDTO variantDTO) {
        return modelMapper.map(variantDTO, Variant.class);
    }
}
