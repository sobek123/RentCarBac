package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.FullOrderDuplicateException;
import pl.macieksob.rentCar.exception.FullOrderNotFoundException;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    private OrderDTO mapToDTO(Order order){
        OrderDTO map = modelMapper.map(order, OrderDTO.class);
        return map;
    }

    private Order mapToEntity(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO, Order.class);
        return order;
    }
    @Transactional
    public OrderDTO addOrder(OrderDTO order){
        if(orderRepository.existsById(order.getId())){
            throw new FullOrderDuplicateException("Order already exists!");
        }

        Order order1 = mapToEntity(order);
//        order1.getCar().setTaken(true);
//        order1.getCar().setRentings(order1.getCar().getRentings());
//        order1.setLaunchDate(LocalDateTime.now());
        orderRepository.save(order1);

        return order;
    }
    @Transactional
    public OrderDTO editOrder(Long id, OrderDTO editOrder){
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
        });


        order.setStartDate(editOrder.getStartDate());
        order.setEndDate(editOrder.getEndDate());
//        order.setCars(editOrder.getCars());

        orderRepository.save(order);

        return mapToDTO(order);
    }

    public void deleteOrderById(Long id) throws FullOrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
        });

        orderRepository.delete(order);
    }
    @Transactional
    public void deleteOrder(OrderDTO order){
        Order order1 = mapToEntity(order);

        orderRepository.delete(order1);
    }

    public List<OrderDTO> getAllByKeyword(String keyword){
        return orderRepository.findAllByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new FullOrderNotFoundException("Order not exist!");
        });
        return mapToDTO(order);
    }
//    public List<OrderDTO> getAllOrdersByEndDate(LocalDate endDate){
//        return orderRepository.findAllByEndDate(endDate Sort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

//    public List<OrderDTO> getAllOrdersByEndDateDesc(LocalDate endDate){
//        return orderRepository.findAllByEndDate(endDateSort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    public List<OrderDTO> getAllOrdersByEndDateAsc(LocalDate endDate){
//        return orderRepository.findAllByEndDate(endDateSort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//
//    public List<OrderDTO> getAllOrdersByStartDate(LocalDate startDate){
//        return orderRepository.findAllByStartDate(startDate Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    public List<OrderDTO> getAllOrdersByStartDateDesc(LocalDate startDate){
//        return orderRepository.findAllByStartDate(startDate,Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    public List<OrderDTO> getAllOrdersByStartDateAsc(LocalDate startDate){
//        return orderRepository.findAllByEndDate(startDate,Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    public List<OrderDTO> getAllOrdersByPlace(Place place){
//        return orderRepository.findAllByRentPlace(place, Sort.by(Sort.Order.asc("endDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

//    @GetMapping("/")
//    public List<OrderDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
//        return carService.getByKeyword();
//    }
public List<OrderDTO> getAllOrdersByStartDateAndEndDate(LocalDate startDate, LocalDate endDate){
    return orderRepository.findAllByStartDate(startDate,endDate,Sort.by(Sort.Order.asc("startDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
}

    public java.util.List<OrderDTO> sortByPrizeAscending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByPrizeDescending(){
        return orderRepository.findAll(Sort.by(Sort.Order.desc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByStartDateAscending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("startDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByStartDateDescending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("startDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByEndDateAscending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("endDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByEndDateDescending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("endDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<OrderDTO> sortByKmAscending(){
        return orderRepository.findAll(Sort.by(Sort.Order.asc("km"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
