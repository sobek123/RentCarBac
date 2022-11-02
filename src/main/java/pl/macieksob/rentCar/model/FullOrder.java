package pl.macieksob.rentCar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Order> orders;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

//    @JsonProperty("launch_date")
//    @NotNull(message = "Pole nie może byc puste")
    private LocalDateTime launchDate;

    @NotNull(message = "Pole nie może byc puste")
    private Integer prize;

    public FullOrder(Set<Order> orders, User user, Integer prize) {
        this.orders = orders;
        this.user = user;
        this.prize = prize;
    }
}
