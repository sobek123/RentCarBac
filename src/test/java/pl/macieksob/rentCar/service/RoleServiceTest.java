//package pl.macieksob.rentCar.service;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.CarDTO;
//import pl.macieksob.rentCar.dto.RoleDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.RoleRepository;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class RoleServiceTest {
//    @Mock
//    private RoleRepository roleRepository;
//
//    @InjectMocks
//    private RoleService roleService;
//    @BeforeEach
//    public void setup(){
//        roleService.addRole(new RoleDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//        roleService.addRole(new RoleDTO(2L, "LFA", "BI6734T", 660, 700, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.5, "hfghfg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2016, 900,4));
//        roleService.addRole(new RoleDTO(3L, "GTR", "BI6734T", 600, 750, 11.0, new Prize( BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.6, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2017, 800,2));
//        roleService.addRole(new RoleDTO(4L, "E 200", "BI6734T", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4));
//        roleService.addRole(new RoleDTO(5L, "Sprinter", "BI6734T", 150, 300, 7.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.5, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300,3));
//    }
//    @Test
//    public void addRole(){
//        RoleDTO RoleDTO = roleService.addRole(new RoleDTO(2L, "Maciej", "Sobolewski", 160, 300, 6.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250,5));
//
//        assertNotNull(roleService.getRole(7L));
//    }
//
//    @Test
//    public void editRole(){
//        RoleDTO role = roleService.getRole(7L);
//        role.setName("ROLE_ADMIN");
//        roleService.addRole(role);
//
//        assertThat(roleService.getRole(7L).getName()).isEqualTo("ROLE_ADMIN");
//    }
//
//    @Test
//    public void deleteRole(){
//        roleService.deleteRoleById(7L);
//        assertThat(roleService.getRole(7L)).isNull();
//    }
//
//    @Test
//    public void getRole(){
//        when(roleService.getRole(1L)).thenReturn(new RoleDTO(1L, "M3", "BI1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
//
//        RoleDTO roleById = roleService.getRole(1L);
//        assertThat(roleById.getName()).isEqualTo("ROLE_ADMIN");
//
//    }
//
//
//
//    @Test
//    public void getRoles(){
//        assertThat(roleService.getRoles().size()).isGreaterThan(0);
//
//    }
//}