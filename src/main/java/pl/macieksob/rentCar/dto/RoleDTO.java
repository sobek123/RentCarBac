package pl.macieksob.rentCar.dto;

import lombok.*;
import pl.macieksob.rentCar.model.User;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    private String name;

    private Collection< User > users;
}
