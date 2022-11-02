package pl.macieksob.rentCar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor

@Getter
@Setter
public class OrderDTO {

    private Long id;

    @JsonProperty("start_date")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDate endDate;

//    @JsonProperty("launch_date")
//    @NotNull(message = "Pole nie może byc puste")
//    private LocalDateTime launchDate;


    @NotNull(message = "Pole nie może byc puste")
    private Integer prize;

//    @NotEmpty(message = "Pole nie może byc puste")
    private CarDTO car;

//    @NotEmpty(message = "Pole nie może byc puste")
//    private UserDTO user;

    @NotNull(message = "Pole nie może byc puste")
    private Place rentPlace;

    @NotNull(message = "Pole nie może byc puste")
    private Place backPlace;



    public OrderDTO(Long id, LocalDate startDate, LocalDate endDate, CarDTO cars, Place rentPlace, Place backPlace, Integer days) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = cars;
        this.rentPlace = rentPlace;
        this.backPlace = backPlace;
//        this.days = days;
    }

    public OrderDTO(Long id, LocalDate startDate, LocalDate endDate, Integer prize, Place rentPlace, Place backPlace,CarDTO cars) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
        this.car = cars;
        this.rentPlace = rentPlace;
        this.backPlace = backPlace;

    }



    public OrderDTO(Long l, LocalDate of, LocalDate of1, Place first, Place second, int i, CarDTO es) {
        this.id = l;
        this.startDate = of;
        this.endDate = of1;
        this.prize = i;
        this.car = es;
        this.rentPlace = first;
        this.backPlace = second;
    }
}
