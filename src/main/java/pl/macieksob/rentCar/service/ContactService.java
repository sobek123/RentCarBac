package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.model.Contact;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ContactDTO mapToDTO(Contact contact){
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact mapToEntity(ContactDTO contact){
        return modelMapper.map(contact, Contact.class);
    }

    public List<ContactDTO> allMessages(){
        return contactRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void addContact(ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        contact.setDate(LocalDateTime.now());
        contact.setOpened(false);

        contactRepository.save(contact);
    }

    public void respond(ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        Contact contact1 = contactRepository.findById(contact.getId()).orElseThrow();

        contact1.setRespond(contactDTO.getRespond());

        contactRepository.save(contact1);
    }

    public void setOpened(ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        Contact contact1 = contactRepository.findById(contact.getId()).orElseThrow();

        contact1.setOpened(true);

        contactRepository.save(contact1);
    }
}
