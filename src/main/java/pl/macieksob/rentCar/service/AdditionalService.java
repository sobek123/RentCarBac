package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.macieksob.rentCar.dto.AdditionalDTO;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.exception.CarNotFoundException;
import pl.macieksob.rentCar.exception.RoleNotFoundException;
import pl.macieksob.rentCar.model.Additional;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.repository.AdditionalRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalService {

    @Autowired
    private AdditionalRepository additionalRepository;

    @Autowired
    private ModelMapper modelMapper;

    private AdditionalDTO mapToDTO(Additional additional){
        return modelMapper.map(additional, AdditionalDTO.class);
    }

    private Additional mapToEntity(AdditionalDTO additionalDTO){
        return modelMapper.map(additionalDTO, Additional.class);
    }

    public List<AdditionalDTO> getAll(){
        return additionalRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public AdditionalDTO addAdditional(AdditionalDTO additionalDTO){
        Additional additional = mapToEntity(additionalDTO);
        additionalRepository.save(additional);

        return additionalDTO;
    }

    @Transactional
    public void deleteAdditional(AdditionalDTO additionalDTO){
        Additional additional = mapToEntity(additionalDTO);

        additionalRepository.delete(additional);
    }
    @Transactional
    public void deleteAdditionalById(Long id){
        Additional additional = additionalRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        additionalRepository.deleteById(id);
    }

    public AdditionalDTO getAdditional(Long id){
        Additional additional = additionalRepository.findById(id).orElseThrow(() -> {throw new RoleNotFoundException("Role not exist!");
        });

        return mapToDTO(additional);
    }

    public AdditionalDTO editAdditional(Long id, AdditionalDTO additionalDTO){
        Additional additional = additionalRepository.findById(id).orElseThrow(() -> {throw new RoleNotFoundException("Role not exist!");
        });

        additional.setName(additionalDTO.getName());
        additional.setPrize(additionalDTO.getPrize());

        additionalRepository.save(additional);
        return  mapToDTO(additional);
    }

}