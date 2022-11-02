package pl.macieksob.rentCar.security;

import org.springframework.format.annotation.DateTimeFormat;
import pl.macieksob.rentCar.model.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class SignupRequest {



    private Set<String> role;


    @NotBlank(message = "Pole nie może byc puste!")
    private String name;


    @NotBlank(message = "Pole nie może byc puste!")
    private String surname;

    @Email
    @NotBlank(message = "Pole nie może byc puste!")
    private String email;

    @NotBlank(message = "Pole nie może byc puste!")
    private String postCode;

    @NotBlank(message = "Pole nie może byc puste!")
//    @Pattern(regexp = "[a-z]+([ -][A-Z][a-z]+)?")
    private String city;

    @NotBlank(message = "Pole nie może byc puste!")

//    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

    @NotBlank(message = "Pole nie może byc puste!")

    private String street;

    @NotBlank(message = "Pole nie może byc puste!")

//    @Pattern(regexp = "\\\\d+[A-Z]?\\\\\\\\\\\\d+[A-Z]?")
    private String numberOfStreet;

    @Min(1)
    @NotNull(message = "Pole nie może byc puste")
    private Integer numberOfFlat;

    @NotBlank(message = "Pole nie może byc puste!")


//    @Pattern(regexp = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\"")
    private String password;

    @NotBlank(message = "Pole nie może byc puste!")

    @Size(min=11,max = 11,message = "Pole ma nieprawidłową ilość znaków")
    private String pesel;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Collection<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}