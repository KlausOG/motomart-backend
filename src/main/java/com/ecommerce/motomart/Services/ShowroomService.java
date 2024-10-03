package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.ShowroomDTO;
import com.ecommerce.motomart.Exceptions.ShowroomNotFoundException;
import com.ecommerce.motomart.Models.Showroom;
import com.ecommerce.motomart.Repositories.ShowroomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowroomService {

    @Autowired
    private ShowroomRepository showroomRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ShowroomDTO> getAllShowrooms() {
        return showroomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShowroomDTO getShowroomById(Long id) {
        Showroom showroom = showroomRepository.findById(id)
                .orElseThrow(() -> new ShowroomNotFoundException(id));
        return convertToDTO(showroom);
    }

    public ShowroomDTO createShowroom(ShowroomDTO showroomDTO) {
        Showroom showroom = convertToEntity(showroomDTO);
        Showroom createdShowroom = showroomRepository.save(showroom);
        return convertToDTO(createdShowroom);
    }

    public ShowroomDTO updateShowroom(Long id, ShowroomDTO showroomDTO) {
        Showroom existingShowroom = showroomRepository.findById(id)
                .orElseThrow(() -> new ShowroomNotFoundException(id));

        existingShowroom.setName(showroomDTO.getName());
        existingShowroom.setLocation(showroomDTO.getLocation());

        Showroom updatedShowroom = showroomRepository.save(existingShowroom);
        return convertToDTO(updatedShowroom);
    }

    public void deleteShowroom(Long id) {
        if (!showroomRepository.existsById(id)) {
            throw new ShowroomNotFoundException(id);
        }
        showroomRepository.deleteById(id);
    }

    private ShowroomDTO convertToDTO(Showroom showroom) {
        return modelMapper.map(showroom, ShowroomDTO.class);
    }

    private Showroom convertToEntity(ShowroomDTO showroomDTO) {
        return modelMapper.map(showroomDTO, Showroom.class);
    }
}
