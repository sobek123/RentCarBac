package pl.macieksob.rentCar.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@Table(name="ROLES")
@Setter
@Getter
@NoArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie może byc puste")
    private String name;

    @ManyToMany(mappedBy = "roles",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @NotEmpty(message = "Pole nie może byc puste")
    private Collection<User> users;

    public Role(long l, String name) {
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}
