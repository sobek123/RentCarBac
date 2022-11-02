package pl.macieksob.rentCar.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CardDTO {
    private Long id;

    @NotBlank(message = "Pole nie może byc puste!")
    private String code;

    @NotNull(message="Pole nie może byc psute")
    private Integer points;
}
