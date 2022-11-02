package pl.macieksob.rentCar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false,unique = true,updatable = false)
    private String code;

    @NotNull(message="Pole nie może byc psute")
    @Column(nullable = false)
    private Integer points;

    public Card() {
        points = 0;

        int i = (int) (Math.random() * ((10000) + 1));
        int i2 = (int) (Math.random() * ((10000) + 1));
        int i3 = (int) (Math.random() * ((10000) + 1));
        int i4 = (int) (Math.random() * ((10000) + 1));

        String first = String.valueOf(i);
        String second = String.valueOf(i2);
        String third = String.valueOf(i3);
        String forth = String.valueOf(i4);

        if(i < 9000){
            int len = first.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            first = format + first;
        }else if(i2 < 9000){
            int len = second.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            second = format + second;
        }else if(i3 < 9000){
            int len = third.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            third = format + third;
        }
        else if(i4 < 9000){
            int len = third.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            third = format + third;
        }
        code = first + second + third + forth;
    }
}
