package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.CardDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    private CardDTO mapToDTO(Card card){
        return modelMapper.map(card, CardDTO.class);
    }

    private Card mapToEntity(CardDTO cardDTO){
        return modelMapper.map(cardDTO, Card.class);
    }

    @Transactional
    public CardDTO addCard(CardDTO cardDTO){
        return mapToDTO(cardRepository.save(mapToEntity(cardDTO)));
    }
}
