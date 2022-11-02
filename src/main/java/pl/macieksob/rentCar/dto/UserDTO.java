package pl.macieksob.rentCar.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;

    private String name;


    private String surname;

    private String email;

    private String postCode;

//    @Pattern(regexp = "[a-z]+([ -][A-Z][a-z]+)?")
    private String city;

//    @Pattern(regexp = "\\d{3} \\d{3} \\d{3}")
//    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

    private String street;

//    @Pattern(regexp = "\\\\d+[A-Z]?\\\\\\\\\\\\d+[A-Z]?")
    private String numberOfStreet;

    private Integer numberOfFlat;

    private String password;

    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String resetPasswordToken;

    private String verificationCode;

    //    @NotBlank(message = "Pole nie może byc puste!")
    private Boolean enabled;

    private Card card;

//    @NotEmpty(message = "Pole nie może byc puste")
//    private Collection<Role> roles;

    private Integer points;
}
