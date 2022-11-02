package pl.macieksob.rentCar.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    private String name;


    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    private String surname;

    @Email
    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
//    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postCode;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
//    @Pattern(regexp = "[a-z]+([ -][A-Z][a-z]+)?")
    private String city;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false,unique = true)
//    @Pattern(regexp = "\\d{3} \\d{3} \\d{3}")
//    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
//    @Pattern(regexp = "\\\\d+[A-Z]?\\\\\\\\\\\\d+[A-Z]?")
    private String numberOfStreet;

    @Min(1)
    @Column(nullable = false)
//    @NotNull(message = "Pole nie może byc puste")
    private Integer numberOfFlat;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    @Size(min=6,message = "Pole musi mieć co najmniej 6 znaków!")
//    @Pattern(regexp = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\"")
    private String password;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false,unique = true)
    @Size(min=11,max = 11,message = "Pole ma nieprawidłową ilość znaków")
    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    @NotNull(message = "Pole nie może byc puste")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    @NotNull(message = "Pole nie może być puste!")
    private LocalDate dateOfBirth;

//    @NotBlank(message = "Pole nie może byc puste!")
    private String resetPasswordToken;

    @Column(nullable = false,updatable = false)
    @NotBlank(message = "Pole nie może byc puste!")
    private String verificationCode;

//    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    private Boolean enabled;

//    @Column(nullable = false)
//    @NotNull(message = "Pole nie może być puste!")
//    private Integer points;
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @NotEmpty(message = "Pole nie może byc puste")
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(
                    name = "userID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleID", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Contact> contact;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<FullOrder> orders;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
