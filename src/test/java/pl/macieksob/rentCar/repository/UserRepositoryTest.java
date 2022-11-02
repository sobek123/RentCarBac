//package pl.macieksob.rentCar.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.UserRepository;
//
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void saveUserTest(){
//        User user = new User(4L, "E 200", "BI6734T", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4);
//
//        userRepository.save(user);
//
//        assertThat(user.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void deleteUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        userRepository.delete(user);
//
//        User user2 = null;
//
//        Optional<User> opUser =  userRepository.findByEmail("macieksob25@gmail.com");
//
//        if(opUser.isPresent()){
//            user2 = opUser.get();
//        }
//
//        assertThat(user2).isNull();
//    }
//
//    @Test
//    public void getUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        assertThat(user.getId()).isEqualTo(1L);
//    }
//
//    @Test
//    public void getUserListTest(){
//        List<User> all = userRepository.findAll();
//
//        assertThat(all.size()).isGreaterThan(0);
//    }
//
//    @Test
//    public void updateUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        user.setEmail("macieksoe@wp.pl");
//
//        User save = userRepository.save(user);
//
//        assertThat(save.getName()).isEqualTo("macieksoe@wp.pl");
//    }
//}