//package pl.macieksob.rentCar.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.AdditionalDTO;
//import pl.macieksob.rentCar.dto.CarDTO;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.AdditionalRepository;
//import pl.macieksob.rentCar.repository.RoleRepository;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class AdditionalServiceTest {
//
//    @Mock
//    private AdditionalRepository additionalRepository;
//
//    @InjectMocks
//    private AdditionalService additionalService;
//
//    @BeforeAll
//    public void setup(){
//        additionalService.addAdditional(new AdditionalDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//        additionalService.addAdditional(new AdditionalDTO(2L, "LFA", "BI6734T", 660, 700, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.5, "hfghfg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2016, 900,4));
//        additionalService.addAdditional(new AdditionalDTO(3L, "GTR", "BI6734T", 600, 750, 11.0, new Prize( BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.6, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2017, 800,2));
//        additionalService.addAdditional(new AdditionalDTO(4L, "E 200", "BI6734T", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4));
//        additionalService.addAdditional(new AdditionalDTO(5L, "Sprinter", "BI6734T", 150, 300, 7.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.5, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300,3));
//    }
//
//    @Test
//    public void addAdditional(){
//        AdditionalDTO userDTO = additionalService.addAdditional(new AdditionalDTO(2L, "Maciej", "Sobolewski", 160, 300, 6.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250,5));
//
//        assertNotNull(additionalService.getAdditional(7L));
//    }
//
//    @Test
//    public void editAdditional(){
//        AdditionalDTO additional = additionalService.getAdditional(7L);
//        additional.setPrize(BigDecimal.valueOf(345.55));
//        additionalService.addAdditional(additional);
//
//        assertThat(additionalService.getAdditional(7L).getPrize()).isEqualTo(BigDecimal.valueOf(345.55));
//    }
//
//    @Test
//    public void deleteAdditional(){
//        additionalService.deleteAdditionalById(7L);
//        assertThat(additionalService.getAdditional(7L)).isNull();
//    }
//
//    @Test
//    public void getAdditionals(){
//        assertThat(additionalService.getAll().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void getAdditional(){
//        when(additionalService.getAdditional(1L)).thenReturn(new AdditionalDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//
//        AdditionalDTO additionalDTO = additionalService.getAdditional(1L);
//        assertEquals("Wypożyczenie fotelika dziecięcego",additionalDTO.getName());
//        assertEquals(BigDecimal.valueOf(367.6),additionalDTO.getPrize());
//    }
//}
