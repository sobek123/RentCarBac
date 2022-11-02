package pl.macieksob.rentCar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FullOrderDTO {

    private Long id;
    @OneToMany
    private Set<OrderDTO> orders;

    @ManyToOne
    private UserDTO user;


    private LocalDateTime launchDate;

    @NotNull(message = "Pole nie może byc puste")
    private Integer prize;

    public FullOrderDTO(Long id, Set<OrderDTO> orders, UserDTO user) {
        this.id = id;
        this.orders = orders;
        this.user = user;
    }
}
