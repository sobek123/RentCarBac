package pl.macieksob.rentCar.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/workers")
    public List<UserDTO> getAllWorkers(){
        return userService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Long id){
        System.out.println(userService.getUser(id));
        return userService.getUser(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDTO registerUser(@RequestBody @Valid UserDTO user, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        System.out.println(user);
        String url = Utility.getURL(httpServletRequest);
//        userService.sendVerificationEmail(user,url);
//        user.setCreatedTime(LocalDateTime.now());
//        user.setRoles(Set.of(new Role("LOGGED_USER")));
        return userService.addUser(user);

    }

    @RequestMapping(value="/changePassword", method = RequestMethod.PUT)
    public void changePassword(@RequestBody UserDTO userDTO){
        userService.changePassword(userDTO);
    }

    @RequestMapping(value="/makeCard", method=RequestMethod.PUT)
    public void makeCard(@RequestBody UserDTO userDTO){userService.makeCard(userDTO);}
    @RequestMapping(value = "/editUser/{id}",method=RequestMethod.PUT)
    public UserDTO editUser(@PathVariable Long id, @Valid @RequestBody UserDTO newUser){
        return userService.editUser(id,newUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(UserDTO user){
        userService.deleteUser(user);

    }
    @GetMapping("/verify")
    public String verifyAccount(String code){
        boolean verify = userService.verify(code);

        String pageTitle = verify ? "Weryfikacja przebiegła pomyślnie!" : "Weryfikacja nieudana.";
        return pageTitle;
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "";
    }

    @RequestMapping(value = "/",method=RequestMethod.POST)
    public String processForgotPasswordForm(HttpServletRequest request){
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token,email);
        String resetPasswordLink = Utility.getURL(request)+"/reset_password?token=" + token;
        try {
            mailService.sendMailResetPassword(email,resetPasswordLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(String token){
        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }
        return "";
    }

    @RequestMapping(value = "/deleteCar/{id}",method=RequestMethod.POST)
    public String processResetPasswordForm(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");

        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }else {
            userService.updatePassword(user,password );
        }

        return "";
    }

    @GetMapping("/city")
    public List<UserDTO> getUsersByCity(@RequestParam(value = "city") String city){
        return userService.getAllUsersByCity(city);


    }

    @GetMapping("/name")
    public List<UserDTO> getUsersByName(@RequestParam(value = "name") String name){
        return userService.getAllUsersByName(name);


    }

    @GetMapping("/surname")
    public List<UserDTO> getUsersBySurname(@RequestParam(value = "surname") String surname){
        return userService.getAllUsersBySurname(surname);


    }


//    @GetMapping("/keyword")
//    public List<UserDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
//        return userService.getByKeyword(keyword);
//    }

    @GetMapping(value="/checkPassword")
    public boolean checkPassword(@RequestParam("password") String password,@RequestBody UserDTO userDTO){
        return userService.findByPassword(password,userDTO);
    }

    @GetMapping("keyword")
    public List<UserDTO> getByKeyword(@RequestParam String keyword){
        return userService.getByKeyword(keyword);
    }

    @GetMapping("/sortUp")
    public List<UserDTO> sortByNameAndSurnameAsc(){
        return userService.sortByNameAndSurnameAsc();
    }

    @GetMapping("/sortDown")
    public List<UserDTO> sortByNameAndSurnameDesc(){
        return userService.sortByNameAndSurnameDesc();
    }

}
