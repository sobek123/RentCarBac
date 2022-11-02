package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository< Car, Long> {

    java.util.List<Car> findAllByModel(String model);

    java.util.List<Car> findAllByBrandAndTaken(String brand,boolean taken);
    java.util.List<Car> findAllByKmAndTaken(Integer km,boolean taken);

    List< Car> findAllByTransmissionAndTaken(Transmission transmission,boolean taken);

    List< Car> findAllByNmAndTaken(Integer nm,boolean taken);
    List< Car> findAllByCategoryAndTaken(Category category, Boolean taken);

    @Query(nativeQuery = true, value = "gdgf")
    List< Car> findAllByStartDateAndEndDateAndPLace(String category);

    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND TRUNC(SYSDATE) - ?1 < O.START_DATE")
    List<Long> findMostRentedCar(Integer days);
//    SELECT C.ID FROM FULL_ORDER F,FULL_ORDER_ORDERS FO, ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND TRUNC(SYSDATE) - :days < O.START_DATE
    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  TRUNC(SYSDATE) - ?1 < O.START_DATE")
    List<Long> findCarsByLastDays( Integer days);

    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND TRUNC(SYSDATE) - ?1 < O.START_DATE")
    List<Long> findMostRentedCarBrand(Integer days);
    //    SELECT C.ID FROM FULL_ORDER F,FULL_ORDER_ORDERS FO, ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND TRUNC(SYSDATE) - :days < O.START_DATE
    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  TRUNC(SYSDATE) - ?1 < O.START_DATE")
    List<Long> findCarsByLastDaysBrand( Integer days);

//    @Query(nativeQuery = true, value = "")
//    List<Long> findCarsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

    List< Car> findAllByPrize(BigDecimal prize);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Double engine);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndEngineAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByEngineAndModelAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndEngineAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndEngineAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYear(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km, Transmission transmission);
    List< Car> findAllByPrizeAndModelAndBrandAndKm(BigDecimal prize, String model, String brand, Integer km);
    List< Car> findAllByPrizeAndModelAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModel(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndModel(BigDecimal prize, String model);
//    List< Car> findAllBrandAndModel(BigDecimal prize, String model);
//    List< Car> findAllByKmAndModel(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndModel(BigDecimal prize, String model);
//    List< Car> findAllByYearAndModel(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndKm(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByYearAndAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByPetrolAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndKM(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndYear(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByKmAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByKmAndYear(BigDecimal prize, String model);
//    List< Car> findAllByKmAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndYear(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByYearAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndModelAndEngine(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndKm(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndKm(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndYearAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndPetrolAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndYearAndPetrol(BigDecimal prize, String model, String brand);
    List< Car> findAllByYearAndTaken(Integer year, boolean taken);
    List< Car> findAllByPetrolAndTaken(Petrol petrol, boolean taken);
    List< Car> findAllByEngineAndTaken(Double engine, boolean taken);
//    List< Car> findAllByPrizeAndModelAndEngineAndKm(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByYearAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPetrolAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndPetrolAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndYearAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndYearAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndYearAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndPetrolAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndYearAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndPetrolAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//
//
//    List< Car> findAllByPrizeAndYear(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndKm(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndBrand(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndModelAndBrandAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndEngine(BigDecimal prize, String model, String brand, Integer km);



    @Query(nativeQuery = true,value = "select * from CARS c where c.model iLIKE  %:keyword%  or c.brand iLIKE  %:keyword% or c.km iLIKE  %:keyword% or c.nm iLIKE  %:keyword% " +
            "or c.petrol iLIKE  %:keyword% or c.engine iLIKE  %:keyword% or c.transmission iLIKE  %:keyword% or c.category iLIKE  %:keyword% or c.license_Plate iLIKE  %:keyword% ")
    List<Car> findAllByKeyword(String keyword);

    @Query(nativeQuery = true,value = "select * from CARS c where c.model iLIKE  %:keyword%  or c.brand iLIKE  %:keyword% or c.km iLIKE  %:keyword% or c.nm iLIKE  %:keyword% " +
            "or c.petrol iLIKE  %:keyword% or c.engine iLIKE  %:keyword% or c.transmission iLIKE  %:keyword% or c.category iLIKE  %:keyword% or c.license_Plate iLIKE  %:keyword% and c.taken EQUALS FALSE ")
    List<Car> findAllByKeywordActive(String keyword);


    Optional<Car> findByKm(int km);

    //2
    List<Car> getByBrandAndPetrol(String brand, Petrol petrol);
    List<Car> getByBrandAndNumberOfSeats(String brand, Integer numberOfSeats);
    List<Car> getByBrandAndTransmission(String brand, Transmission transmission);
    List<Car> getByBrandAndCategory(String brand, Category category);
    List<Car> getByBrandAndYear(String brand, Integer year);

    List<Car> getByPetrolAndNumberOfSeats(Petrol petrol, Integer numberOfSeats);
    List<Car> getByPetrolAndTransmission(Petrol petrol, Transmission transmission);
    List<Car> getByPetrolAndCategory(Petrol petrol, Category category);
    List<Car> getByPetrolAndYear(Petrol petrol, Integer year);

    List<Car> getByNumberOfSeatsAndTransmission(Integer numberOfSeats, Transmission transmission);
    List<Car> getByNumberOfSeatsAndCategory(Integer numberOfSeats, Category category);
    List<Car> getByNumberOfSeatsAndYear(Integer numberOfSeats,Integer year);

    List<Car> getByTransmissionAndCategory(Transmission transmission, Category category);
    List<Car> getByTransmissionAndYear(Transmission transmission, Integer year);

    List<Car> getByCategoryAndYear(Category category, Integer year);
    //6;
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category, Integer year);

    //5
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category);
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Integer year);
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(Integer numberOfSeats, String brand, Petrol petrol, Category category, Integer year);
    List<Car> getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(Integer numberOfSeats, String brand, Transmission transmission, Category category, Integer year);
    List<Car> getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(Integer numberOfSeats,  Transmission transmission,Petrol petrol, Category category, Integer year);
    List<Car> getByTransmissionAndBrandAndPetrolAndCategoryAndYear(Transmission transmission,String brand, Petrol petrol,  Category category, Integer year);

    //3
    List<Car> getByNumberOfSeatsAndBrandAndPetrol(Integer numberOfSeats, String brand, Petrol petrol);
    List<Car> getByNumberOfSeatsAndBrandAndYear(Integer numberOfSeats, String brand, Integer year);
    List<Car> getByNumberOfSeatsAndBrandAndTransmission(Integer numberOfSeats, String brand, Transmission transmission);
    List<Car> getByNumberOfSeatsAndBrandAndCategory(Integer numberOfSeats, String brand, Category category);

    List<Car> getByNumberOfSeatsAndPetrolAndYear(Integer numberOfSeats, Petrol petrol,  Integer year);
    List<Car> getByNumberOfSeatsAndPetrolAndCategory(Integer numberOfSeats, Petrol petrol,  Category category);
    List<Car> getByNumberOfSeatsAndPetrolAndTransmission(Integer numberOfSeats,  Petrol petrol, Transmission transmission);

    List<Car> getByNumberOfSeatsAndYearAndTransmission(Integer numberOfSeats, Integer year,Transmission transmission);
    List<Car> getByNumberOfSeatsAndYearAndCategory(Integer numberOfSeats,Integer year, Category category );

    List<Car> getByNumberOfSeatsAndTransmissionAndCategory(Integer numberOfSeats, Transmission transmission, Category category);


    List<Car> getByBrandAndPetrolAndYear(String brand, Petrol petrol, Integer year);
    List<Car> getByBrandAndPetrolAndTransmission(String brand, Petrol petrol, Transmission transmission);
    List<Car> getByBrandAndPetrolAndCategory(String brand, Petrol petrol, Category category);

    List<Car> getByBrandAndYearAndTransmission(String brand, Integer year,Transmission transmission);
    List<Car> getByBrandAndYearAndCategory(String brand,Integer year,Category category);

    List<Car> getByBrandAndTransmissionAndCategory(String brand, Transmission transmission, Category category);

    List<Car> getByPetrolAndYearAndTransmission( Petrol petrol, Integer year, Transmission transmission);
    List<Car> getByPetrolAndYearAndCategory(Petrol petrol, Integer year,Category category);

    List<Car> getByPetrolAndTransmissionAndCategory(Petrol petrol, Transmission transmission, Category category);

    List<Car> getByYearAndTransmissionAndCategory(Integer year,Transmission transmission, Category category);

    //4
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndYear(Integer numberOfSeats, String brand, Petrol petrol, Integer year);
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndTransmission(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission);
    List<Car> getByNumberOfSeatsAndBrandAndPetrolAndCategory(Integer numberOfSeats, String brand, Petrol petrol, Category category);
    List<Car> getByNumberOfSeatsAndPetrolAndYearAndTransmission(Integer numberOfSeats, Petrol petrol,Integer year,Transmission transmission);
    List<Car> getByNumberOfSeatsAndPetrolAndYearAndCategory(Integer numberOfSeats, Petrol petrol, Integer year,Category category);
    List<Car> getByNumberOfSeatsAndPetrolAndTransmissionAndCategory(Integer numberOfSeats, Petrol petrol, Transmission transmission, Category category);
    List<Car> getByNumberOfSeatsAndYearAndTransmissionAndCategory(Integer numberOfSeats,Integer year, Transmission transmission, Category category);
    List<Car> getByNumberOfSeatsAndBrandAndTransmissionAndCategory(Integer numberOfSeats, String brand, Transmission transmission, Category category);
    List<Car> getByNumberOfSeatsAndBrandAndYearAndCategory(Integer numberOfSeats, String brand, Integer year,Category category);
    List<Car> getByNumberOfSeatsAndBrandAndYearAndTransmission(Integer numberOfSeats, String brand,Integer year, Transmission transmission);

    List<Car> getByBrandAndPetrolAndYearAndTransmission(String brand, Petrol petrol, Integer year, Transmission transmission);
    List<Car> getByBrandAndPetrolAndYearAndCategory(String brand, Petrol petrol, Integer year, Category category);
    List<Car> getByBrandAndYearAndTransmissionAndCategory(String brand, Integer year,Transmission transmission, Category category);
    List<Car> getByBrandAndPetrolAndTransmissionAndCategory(String brand, Petrol petrol, Transmission transmission, Category category);


    List<Car> getByPetrolAndYearAndTransmissionAndCategory(Petrol petrol,Integer year, Transmission transmission, Category category );
}
