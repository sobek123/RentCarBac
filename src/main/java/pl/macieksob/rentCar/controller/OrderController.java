package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.service.OrderService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/newOrder",method=RequestMethod.POST)
    public OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO);
        return orderService.addOrder(orderDTO);

    }
    @RequestMapping(value = "/deleteOrder/{id}",method=RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);

    }

    @RequestMapping(value = "/deleteOrder",method=RequestMethod.DELETE)
    public void deleteOrder(OrderDTO order){
        orderService.deleteOrder(order);
    }
    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);

    }

    @RequestMapping(value = "/editOrder/{id}",method=RequestMethod.PUT)
    public OrderDTO editOrder(@PathVariable Long id,@Valid @RequestBody OrderDTO newOrder){
        return orderService.editOrder(id,newOrder);

    }

//    @GetMapping("/place")
//    public List<OrderDTO> getAllOrdersByPlace(@RequestParam(value = "place")Place place){
//        return orderService.getAllOrdersByPlace(place);
//    }
//
//    @GetMapping("/startDate")
//    public List<OrderDTO> getAllOrdersByStartDate(@RequestParam(value = "startDate")LocalDate startDate){
//        return orderService.getAllOrdersByStartDate(startDate);
//    }
//
//    @GetMapping("/endDate")
//    public List<OrderDTO> getAllOrdersByEndDate(@RequestParam(value = "endDate")LocalDate endDate){
//        return orderService.getAllOrdersByEndDate(endDate);
//    }

    @GetMapping("/places")
    public List<String> getAllPlaces(){
        List<String> places = new ArrayList<>();
        for(Place m : Place.values()) {
            places.add(m.showPlace());
        }
        return places;
    }
    @GetMapping("/sortByPrizeAscending")
    public java.util.List<OrderDTO> sortByPrizeAscending(){
        return orderService.sortByPrizeAscending();
    }
    @GetMapping("/sortByPrizeDescending")
    public java.util.List<OrderDTO> sortByPrizeDescending(){
        return orderService.sortByPrizeDescending();
    }
    @GetMapping("/sortByStartDateAscending")
    public java.util.List<OrderDTO> sortByStartDateAscending(){
        return orderService.sortByStartDateAscending();
    }
    @GetMapping("/sortByStartDateDescending")
    public java.util.List<OrderDTO> sortByStartDateDescending(){
        return orderService.sortByStartDateDescending();
    }
    @GetMapping("/sortByEndDateAscending")
    public java.util.List<OrderDTO> sortByEndDateAscending(){
        return orderService.sortByEndDateAscending();
    }
    @GetMapping("/sortByEndDateDescending")
    public java.util.List<OrderDTO> sortByEndDateDescending(){
        return orderService.sortByEndDateDescending();
    }



}
