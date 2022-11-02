package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.FullOrder;
import pl.macieksob.rentCar.model.User;

import java.util.List;

@Repository
public interface FullOrderRepository extends JpaRepository<FullOrder, Long> {
    List<FullOrder> findByUser(UserDTO user);

    @Query(nativeQuery = true, value = "select * from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND TRUNC(SYSDATE) EQUALS O.END_DATE")
    List<Long> getTodayOrders(@Param("id") Long id);


    @Query(nativeQuery = true,value = "select FO.FULL_ORDER_ID from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C, ADDITIONAL A, ORDERS_ADDITIONAL OA where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND A.ID = OA.ADDITIONAL_ID AND O.ID = OA.ORDER_ID AND F.PRIZE iLIKE  %:keyword% or O.PRIZE iLIKE  %:keyword% or F.PRIZE iLIKE %:keyword% or F.launch_Date iLIKE  %:keyword% or u.name iLIKE  %:keyword%  or u.surname iLIKE  %:keyword% or u.email iLIKE  %:keyword% or u.date_Of_Birth iLIKE  %:keyword% or u.pesel iLIKE  %:keyword% or u.city iLIKE  %:keyword% or u.street iLIKE  %:keyword% or u.post_Code iLIKE  %:keyword% or u.number_Of_Street iLIKE  %:keyword% or u.number_Of_Flat iLIKE  %:keyword% or c.model iLIKE  %:keyword%  or c.brand iLIKE  %:keyword% or c.km iLIKE  %:keyword% or c.nm iLIKE  %:keyword% or c.license_Plate iLIKE  %:keyword% or c.petrol iLIKE  %:keyword% or c.engine iLIKE  %:keyword% or c.transmission iLIKE  %:keyword% or c.category iLIKE  %:keyword% or a.name iLIKE %:keyword%")
    List<Long> getByKeyword(String keyword);

    @Query(nativeQuery = true, value = "select * from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND TRUNC(SYSDATE) < O.END_DATE;")
    List<Long> getActiveOrders(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND o.start_Date < TRUNC(SYSDATE) AND o.end_Date > TRUNC(SYSDATE) OR\n" +
            "  o.start_Date > TRUNC(SYSDATE) AND o.end_Date > TRUNC(SYSDATE) OR o.end_Date < TRUNC(SYSDATE) AND o.start_Date < TRUNC(SYSDATE)")
    List<Long> getHistoricOrders(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND TRUNC(SYSDATE) EQUALS O.END_DATE;")
    List<Long> getOrdersRentToday(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from FULL_ORDER F, FULL_ORDER_ORDERS FO, ORDERS O, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND TRUNC(SYSDATE) EQUALS O.START_DATE;")
    List<Long> getOrdersBackToday(@Param("id") Long id);
}

