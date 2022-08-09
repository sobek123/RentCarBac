package pl.macieksob.rentCar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal first;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal second;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal third;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal forth;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal fifth;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal six;

    @NotNull(message = "Pole nie może byc puste")
    @Min(200)
    private BigDecimal deposit;

}
