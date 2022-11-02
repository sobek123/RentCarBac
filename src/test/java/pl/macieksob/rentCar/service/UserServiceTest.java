//package pl.macieksob.rentUser.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.model.Category;
//import pl.macieksob.rentCar.model.Petrol;
//import pl.macieksob.rentCar.model.Prize;
//import pl.macieksob.rentCar.model.Transmission;
//import pl.macieksob.rentCar.repository.UserRepository;
//import pl.macieksob.rentCar.service.UserService;
//import pl.macieksob.rentUser.dto.UserDTO;
//import pl.macieksob.rentUser.model.*;
//import pl.macieksob.rentUser.repository.RoleRepository;
//import pl.macieksob.rentUser.repository.UserRepository;
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
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setup(){
//        userService.addUser(new UserDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//        userService.addUser(new UserDTO(2L, "LFA", "BI6734T", 660, 700, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.5, "hfghfg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2016, 900,4));
//        userService.addUser(new UserDTO(3L, "GTR", "BI6734T", 600, 750, 11.0, new Prize( BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.6, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2017, 800,2));
//        userService.addUser(new UserDTO(4L, "E 200", "BI6734T", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4));
//        userService.addUser(new UserDTO(5L, "Sprinter", "BI6734T", 150, 300, 7.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.5, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300,3));
//    }
//    @Test
//    public void addUser(){
//        UserDTO userDTO = userService.addUser(new UserDTO(2L, "Maciej", "Sobolewski", 160, 300, 6.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250,5));
//
//        assertNotNull(userService.getUser(7L));
//    }
//
//    @Test
//    public void getUser(){
//        when(userService.getUser(1L)).thenReturn(new UserDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//
//        UserDTO UserById = userService.getUser(1L);
//        assertEquals("BMW", UserById.getEmail());
//        assertEquals("M3", UserById.getName());
//        assertEquals(2020, UserById.getSurname());
//    }
//
//    @Test
//    public void editUser(){
//        UserDTO User = userService.getUser(7L);
//        User.setEmail("test@gmail.com");
//        userService.addUser(User);
//
//        assertThat(userService.getUser(7L).getEmail()).isEqualTo("test@gmail.com");
//    }
//
//    @Test
//    public void deleteUser(){
//        userService.deleteUserById(7L);
//        assertThat(userService.getUser(7L)).isNull();
//    }
//
//    @Test
//    public void getUsers(){
//        assertThat(userService.getAllUsers().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void testUser(){
//        UserDTO user = userService.getUser(5L);
//        assertThat(user.getEmail()).isEqualTo("macieksob25@gmail.com");
//    }
//
//}