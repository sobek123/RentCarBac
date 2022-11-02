package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {

    @Query(nativeQuery = true,value = "select * from ORDERS O inner join CATEGORY C on C.ID = i.CATEGORY_ID where i.TITLE iLIKE  %:keyword%  or i.CONTENT iLIKE  %:keyword% or c.name iLIKE  %:keyword% or i.date iLIKE  %:keyword%")
    List<Order> findAllByKeyword(String keyword);

    List<Order> findAllByEndDate(LocalDate endDate);

    List<Order> findAllByStartDate(LocalDate startDate);

    List<Order> findAllByRentPlace(Place place);


    List<Order> findAllByStartDate(LocalDate startDate, LocalDate endDate, Sort startDate1);
}
