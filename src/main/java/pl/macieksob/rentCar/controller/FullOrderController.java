package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.service.FullOrderService;
import pl.macieksob.rentCar.service.OrderService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fullOrder")
public class FullOrderController {

    @Autowired
    private FullOrderService fullOrderService;

    @GetMapping("/sortByPrizeDesc")
    public List<FullOrderDTO> sortByPrizeDesc(){
        return fullOrderService.sortByPrizeDesc();
    }

    @GetMapping("/sortByPrizeAsc")
    public List<FullOrderDTO> sortByPrizeAsc(){
        return fullOrderService.sortByPrizeAsc();
    }

    @GetMapping("/sortByLaunchDateAsc")
    public List<FullOrderDTO> sortByLaunchDateAsc(){
        return fullOrderService.sortByLaunchDateAsc();
    }

    @GetMapping("/sortByLaunchDateDesc")
    public List<FullOrderDTO> sortByLaunchDateDesc(){
        return fullOrderService.sortByLaunchDateDesc();
    }

    @RequestMapping(value = "/newFullOrder",method= RequestMethod.POST)
    public void addFullOrder(@Valid @RequestBody FullOrderDTO fullOrderDTO) throws MessagingException, UnsupportedEncodingException {


        Set<OrderDTO> orders = fullOrderDTO.getOrders();
        for(OrderDTO ord: orders){
//            if(ord.getRentPlace() == Place.FIRST || ord.getBackPlace() == Place.FIRST){
//                ord.setRentPlace(Place.FIRST);
//                ord.setBackPlace(Place.FIRST);
//            }else if(ord.getRentPlace() == Place.SECOND || ord.getBackPlace() == Place.SECOND){
//                ord.setRentPlace(Place.SECOND);
//                ord.setRentPlace(Place.SECOND);
//            }else if(ord.getRentPlace() == Place.THIRD || ord.getBackPlace() == Place.THIRD){
//                ord.setRentPlace(Place.THIRD);
//                ord.setRentPlace(Place.THIRD);
//            }
            ord.setStartDate(LocalDate.of(2022,10,10));
            ord.setEndDate(LocalDate.of(2022,10,13));
        }
        System.out.println(fullOrderDTO);
        fullOrderService.addFullOrder(fullOrderDTO);

    }
    @RequestMapping(value = "/deleteFullOrder/{id}",method=RequestMethod.DELETE)
    public void deleteFullOrderById(@PathVariable Long id){
        fullOrderService.deleteFullOrderById(id);

    }

    @RequestMapping(value = "/deleteFullOrder",method=RequestMethod.DELETE)
    public void deleteFullOrder(FullOrderDTO fullOrder){
        fullOrderService.deleteFullOrder(fullOrder);
    }
    @GetMapping
    public List<FullOrderDTO> getAllFullOrders(){
        return fullOrderService.getAllFullOrders();
    }

    @GetMapping("/{id}")
    public FullOrderDTO getFullOrder(@PathVariable Long id){
        return fullOrderService.getFullOrderById(id);

    }

//    @RequestMapping(value = "/editFullOrder/{id}",method=RequestMethod.PUT)
//    public FullOrderDTO editFullOrder(@PathVariable Long id,@Valid @RequestBody FullOrderDTO newFullOrder){
//        return fullOrderService.editFullOrder(id,newFullOrder);
//
//    }

    @GetMapping("/keyword")
    public List<FullOrderDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
        return fullOrderService.getByKeyword(keyword);
    }
    @GetMapping("/getActiveOrders")
    public List<FullOrderDTO> activeOrders(@PathVariable Long id){
        return fullOrderService.getActiveOrders(id);

    }

    @GetMapping("/getHistoricOrders")
    public List<FullOrderDTO> historicOrders(@PathVariable Long id){
        return fullOrderService.getHistoricOrders(id);

    }

    @GetMapping("/rentToday")
    public List<FullOrderDTO> rentToday(@PathVariable Long id){
        return fullOrderService.getOrdersRentToday(id);

    }

    @GetMapping("/backToday")
    public List<FullOrderDTO> backToday(@PathVariable Long id){
        return fullOrderService.getOrdersBackToday(id);

    }

}
