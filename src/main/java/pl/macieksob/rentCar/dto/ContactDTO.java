package pl.macieksob.rentCar.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDTO {

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private String content;

    private String respond;

    private Boolean opened;

//    @NotNull(message = "Pole nie może być puste")
    private LocalDateTime date;

    public ContactDTO(String mac, String sob, String s, String s1, String gdfgdf) {
        this.name = mac;
        this.surname = sob;
        this.email = s;
        this.phoneNumber = s1;
        this.content = gdfgdf;
    }
}
