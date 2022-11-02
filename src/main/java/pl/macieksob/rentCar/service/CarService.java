package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.CarDuplicateException;
import pl.macieksob.rentCar.exception.CarNotFoundException;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.repository.CarRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ModelMapper modelMapper;

    private CarDTO mapToDTO(Car car){
        return modelMapper.map(car, CarDTO.class);
    }

    private Car mapToEntity(CarDTO carDTO){
        return modelMapper.map(carDTO, Car.class);
    }

    @Transactional
    public CarDTO addCar(CarDTO car){
//        if(carRepository.existsById(car.getId())){
//            throw new CarDuplicateException("Car already exists!");
//        }

        Car car1 = mapToEntity(car);
        car1.setTaken(false);
        car1.setRentings(0);
        carRepository.save(car1);

        return car;
    }
    @Transactional
    public void deleteCar(Long id) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        carRepository.delete(car);
    }

    public CarDTO getCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not exist!");
        });
        return mapToDTO(car);
    }
    @Transactional
    public CarDTO editCar(Long id, CarDTO editCar) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        car.setPoints(editCar.getPoints());
        car.setPrize(editCar.getPrize());
        car.setFault(car.getFault() + ", "+ editCar.getFault());
        carRepository.save(car);

        return mapToDTO(car);
    }
    @Transactional
    public void deleteCar(CarDTO car){
        Car car1 = mapToEntity(car);
        carRepository.delete(car1);
    }
    @Transactional
    public void deleteCarById(Long id){
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });
        carRepository.deleteById(id);
    }

    public java.util.List<CarDTO> getByModel(String model){
        return carRepository.findAllByModel(model).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public java.util.List<CarDTO> getAllCars(){
        return carRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public java.util.List<CarDTO> getByBrand(String brand){
        return carRepository.findAllByBrandAndTaken(brand,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByKm(Integer km){
        return carRepository.findAllByKmAndTaken(km,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByTransmission(Transmission transmission){
        return carRepository.findAllByTransmissionAndTaken(transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<String> getModelsByBrands(String brand){
        List<CarDTO> collect = carRepository.findAllByBrandAndTaken(brand,false).stream().map(this::mapToDTO).collect(Collectors.toList());

        return collect.stream().map(CarDTO::getModel).distinct().collect(Collectors.toList());
    }

    public List<Integer> getYearByBrands(String brand){
        List<CarDTO> collect = carRepository.findAllByBrandAndTaken(brand,false).stream().map(this::mapToDTO).collect(Collectors.toList());

        return collect.stream().map(CarDTO::getYear).distinct().collect(Collectors.toList());
    }

    public List<Integer> getNumberOfSeatsByBrands(String brand){
        List<CarDTO> collect = carRepository.findAllByBrandAndTaken(brand,false).stream().map(this::mapToDTO).collect(Collectors.toList());
        return collect.stream().map(CarDTO::getNumberOfSeats).distinct().collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByPetrol(Petrol petrol){
        return carRepository.findAllByPetrolAndTaken(petrol,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByEngine(Double engine){
        return carRepository.findAllByEngineAndTaken(engine,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByYear(Integer year){
        return carRepository.findAllByYearAndTaken(year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    public java.util.List<CarDTO> getByPrize(BigDecimal prize){
//        return carRepository.findAllByPrizeAndTaken(prize).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    //SORTING
    public java.util.List<CarDTO> sortByPrizeAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("prize"))).stream().filter(elem -> elem.getCategory().equals(category)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByPrizeDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("prize"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNumberOfSeatsAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("numberOfSeats"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNumberOfSeatsDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("numberOfSeats"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("year"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("year"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("km"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("km"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("nm"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("nm"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("combustion"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("combustion"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("engine"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("engine"))).stream().filter(elem -> elem.getCategory().equals(category)).filter(elem -> elem.getTaken().equals(false)).map(this::mapToDTO).collect(Collectors.toList());
    }




    public java.util.List<CarDTO> getByKeyword(String keyword){
        return carRepository.findAllByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //FILTERING
    public List<CarDTO> getSportCar(){
        return carRepository.findAllByCategoryAndTaken(Category.SPORTOWE,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getExclusiveCar(){
        return carRepository.findAllByCategoryAndTaken(Category.EKSKLUZYWNE,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getComfortCar(){
        return carRepository.findAllByCategoryAndTaken(Category.KOMFORTOWE,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getCargoCar(){
        return carRepository.findAllByCategoryAndTaken(Category.DOSTAWCZE,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getSUVCar(){
        return carRepository.findAllByCategoryAndTaken(Category.SUV,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getEconomyCar(){
        return carRepository.findAllByCategoryAndTaken(Category.EKONOMICZNE,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(String brand, String model, BigDecimal prize, Integer km, Petrol petrol, Integer year, Double engine, Transmission tr){

        return carRepository.findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(prize,model,brand,km,tr,year,petrol,engine).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    public void rentCar(Long id, OrderDTO order){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String credentials = (String) auth.getCredentials();
//        UserDTO byPassword = userService.findByPassword(credentials);
//        order.setUser(byPassword);
//        order.setLaunchDate(LocalDateTime.now());
//        orderService.addOrder(order);
//        CarDTO carById = getCarById(id);
//        carById.setTaken(true);
//        carById.setRentings(carById.getRentings() + 1);
//        Car car = mapToEntity(carById);
//        byPassword.setPoints(byPassword.getPoints() + car.getPoints());
//
//        String subject  = "Potwierdzenie rezerwacji";
//        String from = "RentCar";
//        String message = "<p>" + manOrWoman(user) + user.getName() + " " + user.getSurname() + ",</p>";
//        message += "<p>Potwierdzamy rezerwację samochodu </p>" + car.getBrand() + car.getModel();
//        message += "<p>Numer rezerwacji:  </p>" + order.getId();
//
//
//
//        message += "<p>Pozdrawiamy, <br> zespół RentCar!</p>";
//
//
//        mailService.sendMail(from,"macieksob25@gmail.com",user.getEmail(), message,subject,true);
//        carRepository.save(car);
//    }

//    public List<CarDTO> getCarsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate){
//        List<Long> mostRentedCar = carRepository.findCarsByStartDateAndEndDate(startDate, endDate);
//
//        List<CarDTO> cars = new ArrayList<>();
//
//        for(Long l: mostRentedCar){
//            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
//        }
//
//        return cars;
//    }


    public void addFault(String fault, Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> {throw new CarNotFoundException("Car not found");});
        car.setFault(fault);
        carRepository.save(car);
    }

    public List<String> mostRentedCarInDaysBrand(Integer days){
        List<Long> mostRentedCar = carRepository.findMostRentedCar(days);

        List<String> cars = new ArrayList<>();

        for(Long l: mostRentedCar){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()).getBrand());
        }

        return cars;
    }

    public List<String> getByLastDaysBrand(int days){
        List<Long> carsByLastDays = carRepository.findCarsByLastDays(days);
        List<String> cars = new ArrayList<>();

        for(Long l: carsByLastDays){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()).getBrand());
        }

        return cars;
    }

    public List<CarDTO> mostRentedCarInDays(Integer days){
        List<Long> mostRentedCar = carRepository.findMostRentedCar(days);

        List<CarDTO> cars = new ArrayList<>();

        for(Long l: mostRentedCar){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }

    public List<CarDTO> getByLastDays(int days){
        List<Long> carsByLastDays = carRepository.findCarsByLastDays(days);
        List<CarDTO> cars = new ArrayList<>();

        for(Long l: carsByLastDays){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }


    public List<CarDTO> getByBrandAndPetrol(@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.getByBrandAndPetrol(brand, petrol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndNumberOfSeats(@RequestParam String brand,@RequestParam Integer numberOfSeats){
        return carRepository.getByBrandAndNumberOfSeats(brand, numberOfSeats).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.getByBrandAndTransmission(brand, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndCategory(@RequestParam String brand,@RequestParam Category category){
        return carRepository.getByBrandAndCategory(brand, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndYear(@RequestParam String brand,@RequestParam Integer year){
        return carRepository.getByBrandAndYear(brand, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndNumberOfSeats(@RequestParam Petrol petrol,@RequestParam Integer numberOfSeats){
        return carRepository.getByPetrolAndNumberOfSeats(petrol, numberOfSeats).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.getByPetrolAndTransmission(petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndCategory(@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.getByPetrolAndCategory(petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndYear(@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.getByPetrolAndYear(petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission){
        return carRepository.getByNumberOfSeatsAndTransmission(numberOfSeats, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndCategory(numberOfSeats, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndYear(@RequestParam Integer numberOfSeats,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndYear(numberOfSeats, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByTransmissionAndCategory(transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> getByTransmissionAndYear(@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByTransmissionAndYear(transmission, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByCategoryAndYear(@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByCategoryAndYear(category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //6;
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(numberOfSeats, brand, petrol, transmission, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //5
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(numberOfSeats, brand, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(numberOfSeats, brand, petrol, transmission, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(numberOfSeats, brand, petrol, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(numberOfSeats, brand, transmission, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(numberOfSeats, transmission, petrol, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByTransmissionAndBrandAndPetrolAndCategoryAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByTransmissionAndBrandAndPetrolAndCategoryAndYear(transmission, brand, petrol,  category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //3
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrol(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrol(numberOfSeats, brand, petrol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndYear(numberOfSeats, brand, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.getByNumberOfSeatsAndBrandAndTransmission(numberOfSeats, brand, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndBrandAndCategory(numberOfSeats, brand, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Integer year){
        return carRepository.getByNumberOfSeatsAndPetrolAndYear(numberOfSeats, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Category category){
        return carRepository.getByNumberOfSeatsAndPetrolAndCategory(numberOfSeats, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam  Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.getByNumberOfSeatsAndPetrolAndTransmission(numberOfSeats, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndYearAndTransmission(numberOfSeats, year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndYearAndCategory(numberOfSeats,year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndTransmissionAndCategory(numberOfSeats, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public List<CarDTO> getByBrandAndPetrolAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.getByBrandAndPetrolAndYear(brand, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndPetrolAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.getByBrandAndPetrolAndTransmission(brand, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndPetrolAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.getByBrandAndPetrolAndCategory(brand, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndYearAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByBrandAndYearAndTransmission(brand,year,transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndYearAndCategory(@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByBrandAndYearAndCategory(brand, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByBrandAndTransmissionAndCategory(brand, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndYearAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByPetrolAndYearAndTransmission(petrol,  year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndYearAndCategory(@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByPetrolAndYearAndCategory(petrol, year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByPetrolAndTransmissionAndCategory(petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByYearAndTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByYearAndTransmissionAndCategory( year,transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //4
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndYear(numberOfSeats, brand, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndTransmission(numberOfSeats, brand, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndBrandAndPetrolAndCategory(numberOfSeats, brand, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndPetrolAndYearAndTransmission(numberOfSeats, petrol,year, transmission ).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndPetrolAndYearAndCategory(numberOfSeats, petrol, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndPetrolAndTransmissionAndCategory(numberOfSeats, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndYearAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndYearAndTransmissionAndCategory(numberOfSeats, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByNumberOfSeatsAndBrandAndTransmissionAndCategory(numberOfSeats, brand, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndYearAndCategory(numberOfSeats, brand,  year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByNumberOfSeatsAndBrandAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByNumberOfSeatsAndBrandAndYearAndTransmission(numberOfSeats, brand,  year,transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndPetrolAndYearAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.getByBrandAndPetrolAndYearAndTransmission(brand, petrol, year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndPetrolAndYearAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByBrandAndPetrolAndYearAndCategory(brand, petrol, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndYearAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByBrandAndYearAndTransmissionAndCategory(brand, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByBrandAndPetrolAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.getByBrandAndPetrolAndTransmissionAndCategory(brand, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByPetrolAndYearAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.getByPetrolAndYearAndTransmissionAndCategory(petrol, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getByKeywordActive(String keyword) {
        return carRepository.findAllByKeywordActive(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void setTaken(CarDTO carDTO){
        Car contact = mapToEntity(carDTO);
        Car contact1 = carRepository.findById(contact.getId()).orElseThrow();

        contact1.setTaken(true);

        carRepository.save(contact1);
    }
}
