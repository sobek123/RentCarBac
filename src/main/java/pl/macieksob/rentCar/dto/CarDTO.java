package pl.macieksob.rentCar.dto;

import lombok.*;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarDTO {


    private Long id;

    @NotBlank(message = "Pole nie może byc puste")
    private String model;

    @NotBlank(message = "Pole nie może byc puste")
    private String licensePlate;
    @Min(2)
    @NotNull(message = "Pole nie może byc puste")
    private Integer numberOfSeats;

    @NotNull(message = "Pole nie może byc puste")
    @Min(30)
    private Integer km;

    @NotNull(message = "Pole nie może byc puste")
    @Min(50)
    private Integer nm;

    @NotNull(message = "Pole nie może byc puste")
    @Min(5)
    @Max(50)
    private Double combustion;

//    @NotEmpty(message = "Pole nie może byc puste")
//    @OneToOne
    @Embedded
    private Prize prize;


    @NotNull(message = "Pole nie może byc puste")
    @Min(1)
    @Max(10)
    private Double engine;

    @NotBlank(message = "Pole nie może byc puste")
    private String image;

    @NotBlank(message = "Pole nie może byc puste")
    private String brand;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Pole nie może byc puste")
    private Transmission transmission;

    @NotBlank(message = "Pole nie może byc puste")
    private String details;

    @NotNull(message = "Pole nie może byc puste")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @NotNull(message = "Pole nie może byc puste")
    @Enumerated(value = EnumType.STRING)
    private Petrol petrol;

    @NotNull(message = "Pole nie może byc puste")
    @Min(1960)
    @Max(2023)
    private Integer year;

    @NotNull(message = "Pole nie może byc puste")
    private Boolean taken;

    private String fault;
//    @ManyToMany(mappedBy = "cars")
//    @NotEmpty(message = "Pole nie może byc puste")
//    private Set<Order> orders;

    @NotNull(message = "Pole nie może być puste!")
    private Integer points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return brand.equals(carDTO.brand);
    }
    @Min(0)
    private Integer rentings;
    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }

    public CarDTO(Long id, String model, String licensePlate, Integer km, Integer nm, Double combustion, Prize prize, Double engine, String image, String brand, Transmission transmission, String details, Category category, Petrol petrol, Integer year, Integer points, Integer numberOfSeats) {
        this.id = id;
        this.model = model;
        this.licensePlate = licensePlate;
        this.km = km;
        this.nm = nm;
        this.combustion = combustion;
        this.prize = prize;
        this.engine = engine;
        this.image = image;
        this.brand = brand;
        this.transmission = transmission;
        this.details = details;
        this.category = category;
        this.petrol = petrol;
        this.year = year;
        this.points = points;
        this.numberOfSeats = numberOfSeats;
    }
}
