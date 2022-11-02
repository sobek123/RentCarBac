//package pl.macieksob.rentCar.controller;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.exception.RoleNotFoundException;
//import pl.macieksob.rentCar.exception.UserDuplicateException;
//import pl.macieksob.rentCar.model.Role;
//import pl.macieksob.rentCar.model.User;
//import pl.macieksob.rentCar.repository.RoleRepository;
//import pl.macieksob.rentCar.repository.UserRepository;
//import pl.macieksob.rentCar.security.*;
//import pl.macieksob.rentCar.service.CustomUserDetails;
//import pl.macieksob.rentCar.service.CustomUserDetailsService;
//import pl.macieksob.rentCar.service.UserService;
////import pl.macieksob.rentCar.security.JWTTokenUtility;
//
//import javax.mail.MessagingException;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.io.UnsupportedEncodingException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin("http://localhost:3000")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    private JWTTokenUtility jwtTokenUtility;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private UserDTO mapToDTO(User user){
//        return modelMapper.map(user, UserDTO.class);
//    }
//
//    private User mapToEntity(UserDTO userDTO){
//        return modelMapper.map(userDTO, User.class);
//    }
//
//
//    @RequestMapping(value = "/signin",
//            method=RequestMethod.POST)
//    public JWTResponse authenticateUser(@RequestBody @Valid LoginRequest request){
//        System.out.println(request.getEmail()+request.getPassword());
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
//            );
//        System.out.println(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = jwtTokenUtility.generateAccessToken(authentication);
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        System.out.println(userDetails.getEmail());
//        System.out.println(userDetails.getId());
//            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//            return new JWTResponse(jwt,
//                    userDetails.getId(),
//                    userDetails.getEmail(),
//                    roles);
//    }
//
//    @RequestMapping(value="/signup", method=RequestMethod.POST)
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
//        if (userService.existsByEmail(signupRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new UserDuplicateException("Email is already taken!"));
//        }
//
//        User user = new User(signupRequest.getEmail(),passwordEncoder.encode(signupRequest.getPassword()));
//
//        Set<String> strRoles = signupRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if(strRoles == null){
//            Role userRole =  roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//            roles.add(userRole);
//        }
//        else{
//            strRoles.forEach(role -> {
//                switch (role){
//                    case "admin":
//                        Role adminRole =  roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(adminRole);
//                        break;
//                    case "logged_user":
//                        Role loggedUserRole = roleRepository.findByName("ROLE_LOGGEDUSER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(loggedUserRole);
//                        break;
//                    default:
//                        Role unloggedUserRole = roleRepository.findByName("ROLE_UNLOGGEDUSER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
//                        roles.add(unloggedUserRole);
//                }
//            });
//        }
//        String url = Utility.getURL(httpServletRequest);
//        userService.sendVerificationEmail(user,url);
//        user.setRoles(roles);
//        userService.addUser(mapToDTO(user));
//
//        return ResponseEntity.ok().build();
//    }
//}
