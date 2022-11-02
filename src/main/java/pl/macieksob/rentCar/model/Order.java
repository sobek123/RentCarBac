package pl.macieksob.rentCar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("start_date")
    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private LocalDate endDate;

//    @JsonProperty("start_date")
//    @NotNull(message = "Pole nie może byc puste")
//    @Column(nullable = false)
//    private LocalDateTime launchDate;

    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private Place rentPlace;

    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private Place backPlace;


    @OneToOne(cascade = CascadeType.MERGE)
//    @NotEmpty(message = "Pole nie może byc puste")
    private Car car;

//    @ManyToOne
////    @NotEmpty(message = "Pole nie może byc puste")
//    private User user;

    @OneToMany
   private List<Additional> additional;


    @NotNull(message = "Pole nie może byc puste")
    private Integer prize;
}
