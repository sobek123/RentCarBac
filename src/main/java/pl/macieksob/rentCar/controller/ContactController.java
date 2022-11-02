package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<ContactDTO> messages(){
        return contactService.allMessages();
    }

    @RequestMapping(value = "/addContact",method= RequestMethod.POST)
    public void addContact(@RequestBody @Valid ContactDTO contactDTO){
        contactService.addContact(contactDTO);
    }

    @RequestMapping(value = "/respond", method = RequestMethod.PUT)
    public void respond(@RequestBody @Valid ContactDTO contactDTO){
        contactService.respond(contactDTO);
    }

    @RequestMapping(value = "/setOpened", method = RequestMethod.PUT)
    public void setOpened(@RequestBody @Valid ContactDTO contactDTO){
        contactService.setOpened(contactDTO);
    }
}
