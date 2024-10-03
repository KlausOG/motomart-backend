package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.SpecificationDTO;
import com.ecommerce.motomart.Exceptions.SpecificationNotFoundException;
import com.ecommerce.motomart.Models.Specification;
import com.ecommerce.motomart.Repositories.SpecificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecificationService {

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SpecificationDTO> getAllSpecifications() {
        return specificationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SpecificationDTO getSpecificationById(Long id) {
        Specification specification = specificationRepository.findById(id)
                .orElseThrow(() -> new SpecificationNotFoundException(id));
        return convertToDTO(specification);
    }

    public SpecificationDTO createSpecification(SpecificationDTO specificationDTO) {
        Specification specification = convertToEntity(specificationDTO);
        Specification createdSpecification = specificationRepository.save(specification);
        return convertToDTO(createdSpecification);
    }

    public SpecificationDTO updateSpecification(Long id, SpecificationDTO specificationDTO) {
        Specification existingSpecification = specificationRepository.findById(id)
                .orElseThrow(() -> new SpecificationNotFoundException(id));

        existingSpecification.setSpecs(specificationDTO.getSpecs());
        existingSpecification.setValue(specificationDTO.getValue());

        Specification updatedSpecification = specificationRepository.save(existingSpecification);
        return convertToDTO(updatedSpecification);
    }

    public void deleteSpecification(Long id) {
        if (!specificationRepository.existsById(id)) {
            throw new SpecificationNotFoundException(id);
        }
        specificationRepository.deleteById(id);
    }

    private SpecificationDTO convertToDTO(Specification specification) {
        return modelMapper.map(specification, SpecificationDTO.class);
    }

    private Specification convertToEntity(SpecificationDTO specificationDTO) {
        return modelMapper.map(specificationDTO, Specification.class);
    }
}
