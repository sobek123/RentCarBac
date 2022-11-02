package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.exception.FullOrderDuplicateException;
import pl.macieksob.rentCar.exception.FullOrderNotFoundException;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.FullOrder;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.FullOrderRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FullOrderService {

    @Autowired
    private FullOrderRepository fullOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    private FullOrderDTO mapToDTO(FullOrder order){
        FullOrderDTO map = modelMapper.map(order, FullOrderDTO.class);
        return map;
    }

    private FullOrder mapToEntity(FullOrderDTO orderDTO){
        FullOrder order = modelMapper.map(orderDTO, FullOrder.class);
        return order;
    }

    public String manOrWoman(UserDTO user){
        if(user.getName().endsWith("a")){
            return "Szanowna Pani ";
        }
        else {
            return "Szanowny Panie ";
        }
    }

    @Transactional
    public void addFullOrder(FullOrderDTO order) throws MessagingException, UnsupportedEncodingException {
        FullOrder order1 = mapToEntity(order);
//        if(fullOrderRepository.existsById(order1.getId())){
//            throw new FullOrderDuplicateException("Order already exists!");
//        }


//        order1.getCar().setTaken(true);
//        order1.getCar().setRentings(order1.getCar().getRentings());
        System.out.println(order1.getOrders());
        order1.setLaunchDate(LocalDateTime.now());
        order1.setPrize(1);
        Card points = order.getUser().getCard();
        if( points != null){
            int fullPoints = 0;
            Set<OrderDTO> orders = order.getOrders();
            for(OrderDTO orderd : orders){
                fullPoints += orderd.getCar().getPoints();
            }
            points.setPoints(points.getPoints() + fullPoints);
        }

//        String subject  = "Potwierdzenie rezerwacji";
//        String from = "RentCar";
//        String message = "<p>" + manOrWoman(order.getUser()) + order.getUser().getName() + " " + order.getUser().getSurname() + ",</p>";
//        message += "<p>Potwierdzamy rezerwację samochodu </p>";
//        message += "<p>Numer rezerwacji:  </p>" + order.getId();
//
//
//
//        message += "<p>Pozdrawiamy, <br> zespół RentCar!</p>";
//
//
//        mailService.sendMail(from,"macieksob25@gmail.com",order.getUser().getEmail(), message,subject,true);
        fullOrderRepository.save(order1);

//        return order;
    }
//    @Transactional
//    public FullOrderDTO editFullOrder(Long id, FullOrderDTO editFullOrder){
//        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
//        });
//
//
//        order.setStartDate(editFullOrder.getStartDate());
//        order.setEndDate(editFullOrder.getEndDate());
////        order.setCars(editOrder.getCars());
//
//        fullOrderRepository.save(order);
//
//        return mapToDTO(order);
//    }

    public void deleteFullOrderById(Long id) throws FullOrderNotFoundException{
        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
        });

        fullOrderRepository.delete(order);
    }
    @Transactional
    public void deleteFullOrder(FullOrderDTO order){
        FullOrder order1 = mapToEntity(order);

        fullOrderRepository.delete(order1);
    }

    public List<FullOrderDTO> getAllFullOrders(){
        return fullOrderRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public FullOrderDTO getFullOrderById(Long id){
        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {
            throw new FullOrderNotFoundException("Order not exist!");
        });
        return mapToDTO(order);
    }

    public List<FullOrderDTO> findFullOrderByUser(UserDTO user){

        return fullOrderRepository.findByUser(user).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> getByKeyword(String keyword) {
        List<Long> activeOrders = fullOrderRepository.getByKeyword(keyword);

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: activeOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> sortByPrizeDesc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.desc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByPrizeAsc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.asc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByLaunchDateAsc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.asc("launchDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByLaunchDateDesc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.desc("launchDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> getActiveOrders(Long days){
        List<Long> activeOrders = fullOrderRepository.getActiveOrders(days);

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: activeOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getHistoricOrders(Long days){
        List<Long> historicOrders = fullOrderRepository.getHistoricOrders(days);

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getOrdersBackToday(Long days){
        List<Long> historicOrders = fullOrderRepository.getOrdersBackToday(days);

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getOrdersRentToday(Long days){
        List<Long> historicOrders = fullOrderRepository.getOrdersRentToday(days);

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

}
