package pl.macieksob.rentCar.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    private String name;

    @NotBlank(message = "Pole nie może być puste")
    private String phoneNumber;

    @NotBlank(message = "Pole nie może być puste")
    private String surname;

    @NotBlank(message = "Pole nie może być puste")
    private String email;

    @NotBlank(message = "Pole nie może być puste")
    private String content;

    @NotNull(message = "Pole nie może być puste")
    private LocalDateTime date;

    private String respond;

    @NotNull(message = "Pole nie może być puste")
    private Boolean opened;
}
