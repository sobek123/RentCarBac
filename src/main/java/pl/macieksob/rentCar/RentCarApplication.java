package pl.macieksob.rentCar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.macieksob.rentCar.dto.*;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(CarService carService, AdditionalService additionalService, UserService userService, OrderService orderService, FullOrderService fullOrderService, ContactService contactService) {

		return (args) -> {
			carService.addCar(new CarDTO(1L, "M3", "BI 1234T", 256, 456, 7.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.5, "./cars/bmw-seria-5-g30-g31.jpg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400, 3));
			carService.addCar(new CarDTO(2L, "LFA", "BI 6734T", 660, 700, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "./cars/Lexus_LFA_Yellow_Las_Vegas.jpg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2016, 900, 4));
			carService.addCar(new CarDTO(3L, "GTR", "WE 546RA", 600, 750, 11.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.6, "./images.jpg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2017, 800, 2));
			carService.addCar(new CarDTO(4L, "E 200", "WY 86977", 300, 400, 8.4, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.6, "./cars/ovZktkpTURBXy9kZmM5NzJjNzY4NjAyZGQxMDg1YmViYTc4NDZmYmRkMC5qcGeRlQPNAQ_NASrNBsHNA84.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300, 4));
			carService.addCar(new CarDTO(5L, "Sprinter", "BI 2231T", 150, 300, 7.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "./cars/5cd94c4c57502a426b2fbc0e.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300, 3));
			carService.addCar(new CarDTO(6L, "A6", "WE 5436R", 189, 320, 8.7, new Prize(BigDecimal.valueOf(109), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "./cars/782226cb52e3b8545a3.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2019, 350, 3));
			carService.addCar(new CarDTO(7L, "Ducato", "BI 7784T", 160, 300, 6.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.4, "./cars/1627985468_4ducato.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250, 5));
			carService.addCar(new CarDTO(8L, "R8", "BI 8905G", 700, 850, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.2, "./cars/895473.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2015, 800, 4));
			carService.addCar(new CarDTO(9L, "RS7", "BI 099KJ", 680, 800, 7.5, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.6, "./cars/audi-a3-cennik-2022-otwarcie.jpg", "Audi", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2017, 750, 6));
			carService.addCar(new CarDTO(10L, "Huracan", "WA 67R2T", 859, 1000, 9.5, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "./cars/1J7A0421-scaled.jpg", "Lamborghini", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2016, 1000, 5));
			carService.addCar(new CarDTO(11L, "Aygo", "BI 8794D", 100, 180, 5.5, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.6, "./cars/3db07d49a3be3a69d148387b70f7c6b0b3096dcc.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200, 4));
			carService.addCar(new CarDTO(12L, "C-HR", "BI 75721", 140, 345, 4.5, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "./cars/5cd4124557502a9a0827a106.jpg", "Toyota", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.ELEKTRYCZNY, 2018, 250, 3));
			carService.addCar(new CarDTO(13L, "Juke", "WE 17141", 160, 245, 6.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.2, "./cars/62e81411999ca85dbd400b1f309f32d1.jpg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2022, 250, 3));
			carService.addCar(new CarDTO(14L, "Q5", "BI 57355", 310, 456, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "./cars/audi-q5-cennik-2022-otwarcie.jpg", "Audi", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2021, 350, 4));
			carService.addCar(new CarDTO(15L, "Q8", "WU 06358", 357, 500, 11.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					4.0, "./cars/audi-q8.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2019, 400, 5));
			carService.addCar(new CarDTO(16L, "Yaris", "BI 23343", 90, 167, 4.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.4, "./cars/1141456.jpg", "Toyota", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 6));
			carService.addCar(new CarDTO(17L, "LC500", "BI 12345", 450, 650, 6.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "./cars/2949273-Ceny-Lexusa-LC-500-Coupe-rozpoczynaja-sie-od-594-tys-zl.jpg", "Lexus", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2016, 500, 7));
			carService.addCar(new CarDTO(18L, "720S", "WT 6YT4T", 720, 900, 15.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "./cars/882511.jpg", "McLaren", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2019, 600, 3));
			carService.addCar(new CarDTO(19L, "Supra", "BI 4534T", 350, 520, 11.2, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.6, "./cars/pol_pl_Splitter-Przedni-V-2-Toyota-Supra-MK5-8876_2.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2018, 350, 4));
			carService.addCar(new CarDTO(20L, "Corsa", "WX 5734A", 125, 235, 4.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "./cars/opel-corsa-e-cennik-2022-otwarcie.jpg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 5));
			carService.addCar(new CarDTO(21L, "A 45 AMG", "WJ 99342", 280, 357, 6.3, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.6, "./cars/651294a44c6de78307fdef7dfc0b506d.jpeg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.DIESEL, 2017, 300, 6));
			carService.addCar(new CarDTO(22L, "Astra", "BI 37331", 112, 199, 4.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "./cars/opel-astra.jpg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.BENZYNA, 2017, 200, 7));
			carService.addCar(new CarDTO(23L, "Megane", "BI 2724F", 101, 190, 5.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "./cars/renault-megane-cennik-2021-otwarcie.jpg", "Renault", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200, 5));
			carService.addCar(new CarDTO(24L, "Qashqai", "BI 1894A", 165, 202, 5.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.4, "./cars/modele-nissana-1-qashqai.jpg", "Nissan", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.DIESEL, 2016, 250, 4));
			carService.addCar(new CarDTO(25L, "LS500h", "BI 7930H", 380, 470, 14.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "./cars/Lexus_LS_500h-35.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2022, 500, 3));
			carService.addCar(new CarDTO(26L, "M8", "WN 4434A", 670, 850, 18.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.0, "./cars/bmw-m8-gc-4-71fcbe29a87b88df7e02.webp", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2018, 500, 4));
			carService.addCar(new CarDTO(27L, "SLS AMG", "BI 60943", 745, 920, 20.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.6, "./cars/940x520_najlepsze-i-niezawodne-tanie-samochody-sportowe-2022-z-cenami-i-zdjeciami-whrm.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2019, 700, 5));
			carService.addCar(new CarDTO(28L, "NSX", "BI 5X34B", 690, 760, 9.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.2, "./cars/f803947a5cce71f6e93e421a29021cb4.jpeg", "Honda", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.ELEKTRYCZNY, 2021, 450, 6));
			carService.addCar(new CarDTO(29L, "7", "BI 50345", 320, 456, 7.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					4.5, "./cars/bmw-7-test-41.jpg", "BMW", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.DIESEL, 2019, 350, 6));
			carService.addCar(new CarDTO(30L, "Arona", "BI 901GF", 190, 230, 6.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "./cars/maxresdefault.jpg", "Seat", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2018, 250, 5));
			carService.addCar(new CarDTO(31L, "Panda", "BI 4YH2K", 80, 156, 4.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.4, "./cars/fiat-panda-2020-5.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2019, 200, 4));
			carService.addCar(new CarDTO(32L, "500", "WE 87891", 80, 145, 4.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.6, "./cars/dcbdf686d2681cd6e3989f9127a994f2.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 3));
			carService.addCar(new CarDTO(33L, "C3", "BI 200A8", 120, 200, 4.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "./cars/citroen-c3-cennik-2022-otwarcie.jpg", "Citroen", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2017, 200, 4));
			carService.addCar(new CarDTO(34L, "3", "BI 67167", 200, 280, 8.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "./cars/2023-bmw-3-series-14-ed5a2154052.webp", "BMW", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2015, 300, 5));
			carService.addCar(new CarDTO(35L, "X5", "BI 2214P", 234, 357, 9.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "./cars/5cdacb7c57502a333d006989.jpg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2016, 350, 6));
//			carService.addCar(new CarDTO(36L, "RX450h", "WA 92341", 200, 305, 6.1, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//					2.0, "./cars/LexusRX450hFSPORTOWE(IV)Frontansicht,14.Februar_2016,Düsseldorf.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300, 4));
			carService.addCar(new CarDTO(37L, "A5", "WT 29801", 256, 489, 8.1, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "./cars/audi_a5_sportback_photo_04.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2019, 300, 5));
			carService.addCar(new CarDTO(38L, "Transit", "WD 24090", 160, 215, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4));
			carService.addCar(new CarDTO(39L, "Boxer", "BI 245TR", 160, 220, 8.9, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.5, "./cars/35-peugeot-boxer-euro6.jpg", "Peugeot", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2021, 200, 3));
			carService.addCar(new CarDTO(40L, "Crafter", "BI 1167Y", 140, 205, 8.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4));
			carService.addCar(new CarDTO(41L, "RAV4", "BI 9080M", 255, 300, 6.6, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.0, "./cars/toyota-rav4-cennik-2022-otwarcie.jpg", "Toyota", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.ELEKTRYCZNY, 2022, 250, 5));
			carService.addCar(new CarDTO(42L, "Corolla", "WE 5421G", 280, 345, 7.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.2, "./cars/toyota-corolla-sedan-exterior-04-full_tcm-1015-1858165.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4));
			additionalService.addAdditional(new AdditionalDTO(1L, "Każdy kolejny dzień spóźnienia oddania samochodu", BigDecimal.valueOf(500)));
			additionalService.addAdditional(new AdditionalDTO(2L, "Wypożyczenie fotelika dziecięcego", BigDecimal.valueOf(100)));
			additionalService.addAdditional(new AdditionalDTO(3L, "Wydanie zgody na wyjazd poza granice Polski - samochody osobowe", BigDecimal.valueOf(400)));
			additionalService.addAdditional(new AdditionalDTO(4L, "Wydanie zgody na wyjazd poza granice Polski - samochody dostawcze", BigDecimal.valueOf(300)));
			additionalService.addAdditional(new AdditionalDTO(5L, "Zniesienie udziału własnego w szkodzie - pakiet podstawowy", BigDecimal.valueOf(500)));
			additionalService.addAdditional(new AdditionalDTO(6L, "Zniesienie udziału własnego w szkodzie - pakiet normalny", BigDecimal.valueOf(1000)));
			additionalService.addAdditional(new AdditionalDTO(7L, "Zniesienie udziału własnego w szkodzie - pakiet rozszerzony", BigDecimal.valueOf(1500)));
			additionalService.addAdditional(new AdditionalDTO(8L, "Odbiór lub zwrot pojazdu z adresu klienta", BigDecimal.valueOf(150)));
			additionalService.addAdditional(new AdditionalDTO(9L, "Mycie samochodu", BigDecimal.valueOf(100)));
			additionalService.addAdditional(new AdditionalDTO(10L, "Sprzątanie samochodu w środku", BigDecimal.valueOf(100)));
			additionalService.addAdditional(new AdditionalDTO(11L, "Ładowanie auta elektrycznego", BigDecimal.valueOf(10)));
			userService.addUser(new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-354", "Bialystok", "514 546 610", "Pogodna", "11", 32, "Sobolek2017?", "67328302379",
					LocalDateTime.now(), LocalDate.of(2000, 12, 12), "fgdgdfg", "dfgdfgdf", true, new Card(),0));
			userService.addUser(new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),0));
//			orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15),LocalDate.of(2022,7,8),32,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250,4)) ,
//					new User(),BigDecimal.valueOf(354.6), Place.FIRST, Place.SECOND,30);
//		};	orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15),LocalDate.of(2022,7,8),32,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//				, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250,4)),new User(),BigDecimal.valueOf(3435.7), Place.FIRST, Place.SECOND, 30));
//			orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15), LocalDate.of(2022,6,18),32,new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-354", "Bialystok", "514 774 710", "Pogodna", "11", 32, "Sobolek2017?", "00321302379",
//					LocalDateTime.now(), LocalDate.of(200, 12, 12), "fgdgdfg", "dfgdfgdf", true, 0),Place.FIRST, Place.SECOND,30, Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4))));
//			System.out.println(userService.findByPassword("Sobolek2017?"));
			fullOrderService.addFullOrder(new FullOrderDTO(1L,Set.of(new OrderDTO(1L, LocalDate.of(2022,6,15), LocalDate.of(2022,6,18),Place.FIRST, Place.SECOND,30,carService.addCar(new CarDTO(40L, "Crafter", "BI6734T", 140, 205, 8.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4))),new OrderDTO(2L, LocalDate.of(2022,6,12), LocalDate.of(2022,5,28),Place.FIRST, Place.SECOND,30,carService.addCar(carService.addCar(new CarDTO(36L, "RX450h", "BI6734T", 200, 305, 6.1, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "./cars/LexusRX450hFSport(IV)Frontansicht,14.Februar_2016,Düsseldorf.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300, 4))))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true,new Card(), 0)));
//			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(new OrderDTO(1L, LocalDate.of(2022,6,15), LocalDate.of(2022,6,18),32,new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-354", "Bialystok", "514 774 710", "Pogodna", "11", 32, "Sobolek2017?", "00321302379",
//					LocalDateTime.now(), LocalDate.of(200, 12, 12), "fgdgdfg", "dfgdfgdf", true, 0),Place.FIRST, Place.SECOND,30,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, 0)));
			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(new OrderDTO(3L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21),Place.FIRST, Place.SECOND,30,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),0)));
			contactService.addContact(new ContactDTO("Mac","Sob","macieksob@gmail.com","654666444","gdfgdf"));
		};
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
