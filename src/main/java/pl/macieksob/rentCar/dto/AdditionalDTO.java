package pl.macieksob.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdditionalDTO {

    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    private String name;

    @NotNull(message = "Pole nie może być puste")
    private BigDecimal prize;
}
