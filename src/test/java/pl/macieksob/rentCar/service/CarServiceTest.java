package pl.macieksob.rentCar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.repository.CarRepository;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setup(){
        carService.addCar(new CarDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
        carService.addCar(new CarDTO(2L, "LFA", "BI6734T", 660, 700, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                5.5, "hfghfg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2016, 900,4));
        carService.addCar(new CarDTO(3L, "GTR", "BI6734T", 600, 750, 11.0, new Prize( BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                3.6, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2017, 800,2));
        carService.addCar(new CarDTO(4L, "E 200", "BI6734T", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4));
        carService.addCar(new CarDTO(5L, "Sprinter", "BI6734T", 150, 300, 7.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                2.5, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300,3));
    }

    @Test
    public void addCar(){
//        CarDTO carDTO = carService.addCar(new CarDTO(7L, "Ducato", "BI6734T", 160, 300, 6.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250,5));

        assertNotNull(carService.getCarById(7L));
    }

    @Test
    public void editCar(){
        CarDTO car = carService.getCarById(7L);
        car.setKm(1000);
        carService.addCar(car);

        assertThat(carService.getCarById(7L).getKm()).isEqualTo(1000);
    }

    @Test
    public void deleteCar(){
        carService.deleteCar(7L);
        assertThat(carService.getCarById(7L)).isNull();
    }

    @Test
    public void getCars(){
        assertThat(carService.getAllCars().size()).isGreaterThan(0);

    }

    @Test
    public void getCar(){
        when(carService.getCarById(1L)).thenReturn(new CarDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));

        CarDTO carById = carService.getCarById(1L);
        assertEquals("BMW", carById.getBrand());
        assertEquals("M3", carById.getModel());
        assertEquals(2020, carById.getYear());
    }

    @Test
    public void testCar(){
        CarDTO car = carService.getCarById(5L);
        assertThat(car.getBrand()).isEqualTo("Mercedes-Benz");
    }



}