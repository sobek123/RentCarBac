package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.AdditionalDTO;
import pl.macieksob.rentCar.model.Additional;
import pl.macieksob.rentCar.service.AdditionalService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/additional")
@CrossOrigin("localhost:3000")
public class AdditionalController {

    @Autowired
    private AdditionalService additionalService;

    @GetMapping("")
    public List<AdditionalDTO> getAll(){
        return additionalService.getAll();
    }

    @PostMapping("/newAdditional")
    public AdditionalDTO addAdditional(@Valid @RequestBody AdditionalDTO additional){
        return additionalService.addAdditional(additional);
    }

    @DeleteMapping("/deleteAdditional/{id}")
    public void deleteAdditionalById(@PathVariable("id") Long id){
        additionalService.deleteAdditionalById(id);
    }

    @DeleteMapping("/deleteAdditional")
    public void deleteAdditional(AdditionalDTO additional){
        additionalService.deleteAdditional(additional);
    }

    @PutMapping("/editAdditional/{id}")
    public AdditionalDTO editAdditional(@PathVariable("id") Long id, @Valid @RequestBody AdditionalDTO additional){
        return additionalService.editAdditional(id, additional);
    }
}
