package pl.macieksob.rentCar.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.service.CarService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/sport")
    public List<CarDTO> getSportCars(){
        return carService.getSportCar();
    }

    @GetMapping("/economy")
    public List<CarDTO> getEconomyCars(){
        return carService.getEconomyCar();
    }

    @GetMapping("/comfort")
    public List<CarDTO> getComfortCars(){
        return carService.getComfortCar();
    }

    @GetMapping("/SUV")
    public List<CarDTO> getSUVCars(){
        return carService.getSUVCar();
    }

    @GetMapping("/cargo")
    public List<CarDTO> getCargoCars(){
        return carService.getCargoCar();
    }

    @GetMapping("/exclusive")
    public List<CarDTO> getExclusiveCars(){
        return carService.getExclusiveCar();
    }

    @RequestMapping(value = "/newCar",method=RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public CarDTO saveCar(@Valid @RequestBody CarDTO car){
        return carService.addCar(car);
    }


    @GetMapping("/all")
    @CrossOrigin("http://localhost:3000")
    public List<CarDTO> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable Long id){
        return carService.getCarById(id);

    }

    @RequestMapping(value = "/editCar/{id}",method=RequestMethod.PUT)
    public void editCar(@PathVariable Long id, @Valid @RequestBody CarDTO newCar){
        carService.editCar(id,newCar);

    }

    @RequestMapping(value = "/deleteCar/{id}",method=RequestMethod.DELETE)
    @CrossOrigin("http://localhost:3000")
    public void deleteCarById(@PathVariable Long id){
        carService.deleteCarById(id);

    }

    @RequestMapping(value = "/deleteCar",method=RequestMethod.DELETE)
    @CrossOrigin("http://localhost:3000")
    public void deleteCar(CarDTO user){
        carService.deleteCar(user);

    }

    @GetMapping("/brand")
    public List<CarDTO> getCarsByBrand(@RequestParam(value = "brand") String brand){
        return carService.getByBrand(brand);
    }

    @GetMapping("/engine")
    public List<CarDTO> getCarsByEngine(@RequestParam(value = "engine") Double engine){
        return carService.getByEngine(engine);
    }

    @GetMapping("/km")
    public List<CarDTO> getCarsByKm(@RequestParam(value = "km") Integer km){
        return carService.getByKm(km);
    }

    @GetMapping("/model")
    public List<CarDTO> getCarsByModel(@RequestParam(value = "model") String model){
        return carService.getByModel(model);
    }

    @GetMapping("/petrol")
    public List<CarDTO> getCarsByPetrol(@RequestParam(value = "petrol") Petrol petrol){
        return carService.getByPetrol(petrol);
    }

    @GetMapping("/year")
    public List<CarDTO> getCarsByYear(@RequestParam(value = "year") Integer year){
        return carService.getByYear(year);
    }

//    @GetMapping("/prize")
//    public List<CarDTO> getCarsByPrize(@RequestParam(value = "prize") BigDecimal prize){
//        return carService.getByPrize(prize);
//    }

    @GetMapping("/transmission")
    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "transmission") Transmission transmission){
        return carService.getByTransmission(transmission);
    }

    @GetMapping("/keyword")
    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
        return carService.getByKeyword(keyword);
    }

    @GetMapping("/keywordActive")
    public List<CarDTO> getCarsByKeywordActive(@RequestParam(value = "keyword") String keyword){
        return carService.getByKeywordActive(keyword);
    }

    @GetMapping("/")
    public List<CarDTO> getCarsByTransmissionAndModelAndBrandAndYearAndEngineAndKmAndPetrol(@RequestParam(value = "transmission") Transmission transmission,@RequestParam(value = "model") String model,
    @RequestParam(value = "brand") String brand,@RequestParam(value = "year") Integer year,@RequestParam(value = "engine") Double engine,@RequestParam(value = "petrol") Petrol petrol,
    @RequestParam(value = "km") Integer km,@RequestParam(value = "prize") BigDecimal prize){
        return carService.getAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(brand,model,prize,km,petrol,year,engine,transmission);
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        Category[] values = Category.values();

        return Arrays.asList(values);
    }

    @GetMapping("/transmissions")
    public List<Transmission> getTransmissions(){
        Transmission[] values = Transmission.values();
        return Arrays.asList(values);
    }


    @GetMapping("/petrols")
    public List<Petrol> getPetrols(){
        Petrol[] values = Petrol.values();
        return Arrays.asList(values);
    }


    @CrossOrigin("http://localhost:3000")
    @GetMapping("/models")
    public List<String> getModels(){
        return carService.getAllCars().stream().map(CarDTO::getModel).collect(Collectors.toList());

    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/brands")
    public List<String> getBrands(){
        return carService.getAllCars().stream().distinct().map(CarDTO::getBrand).collect(Collectors.toList());
    }

    @GetMapping("/years")
    public List<Integer> getYears(){
        return carService.getAllCars().stream().map(CarDTO::getYear).collect(Collectors.toList());
    }

    @GetMapping("/modelByBrand")
    public List<String> getModelByBrand(@RequestParam(value = "brand") String brand){
        return carService.getModelsByBrands(brand);
    }

    @GetMapping("/yearByBrand")
    public List<Integer> getYearByBrand(@RequestParam(value = "brand") String brand){
        return carService.getYearByBrands(brand);
    }

    @GetMapping("/numberOfSeatsByBrand")
    public List<Integer> getNumberOfSeatsByBrand(@RequestParam(value = "brand") String brand){
        return carService.getNumberOfSeatsByBrands(brand);
    }
    @GetMapping("/sortByKmAsc")
    public List<CarDTO> sortCarsByKmAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByKmAscending(category);
    }
    @GetMapping("/sortByKmDesc")
    public List<CarDTO> sortCarsByKmDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByKmDescending(category);
    }

    @GetMapping("/sortByNmAsc")
    public List<CarDTO> sortCarsByNmAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByNmAscending(category);
    }

    @GetMapping("/sortByNmDesc")
    public List<CarDTO> sortCarsByNmDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByNmDescending(category);
    }

    @GetMapping("/sortByNumberOfSeatsAsc")
    public List<CarDTO> sortCarsByNumberOfSeatsAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByNumberOfSeatsAscending(category);
    }

    @GetMapping("/sortByNumberOfSeatsDesc")
    public List<CarDTO> sortCarsByNumberOfSeatsDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByNumberOfSeatsDescending(category);
    }

    @GetMapping("/sortByEngineAsc")
    public List<CarDTO> sortCarsByEngineAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByEngineAscending(category);
    }

    @GetMapping("/sortByEngineDesc")
    public List<CarDTO> sortCarsByEngineDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByEngineDescending(category);
    }

    @GetMapping("/sortByCombustionAsc")
    public List<CarDTO> sortCarsByCombustionAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByCombustionAscending(category);
    }

    @GetMapping("/sortByCombustionDesc")
    public List<CarDTO> sortCarsByCombustionDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByCombustionDescending(category);
    }

    @GetMapping("/sortByYearAsc")
    public List<CarDTO> sortCarsByYearAsc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByYearAscending(category);
    }

    @GetMapping("/sortByYearDesc")
    public List<CarDTO> sortCarsByYearDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByYearDescending(category);
    }

//    @GetMapping("/getByStartDateAndEndDate")
//    public List<CarDTO> getByStartDateAndEndDate(@RequestParam("startDate")LocalDate startDate, @RequestParam("endDate")LocalDate endDate){
//        return carService.getCarsByStartDateAndEndDate(startDate,endDate);
//    }

    @RequestMapping(value = "/fault",method=RequestMethod.PUT)
    public void addFault(@RequestParam("fault") String fault,@RequestParam("id") Long id){
        carService.addFault(fault, id);
    }

    @GetMapping("/getByBrandAndPetrol")
    public List<CarDTO> getByBrandAndPetrol(@RequestParam String brand,@RequestParam Petrol petrol){
        return carService.getByBrandAndPetrol(brand, petrol);
    }

    @GetMapping("/getByBrandAndNumberOfSeats")
    public List<CarDTO> getByBrandAndNumberOfSeats(@RequestParam String brand,@RequestParam Integer numberOfSeats){
        return carService.getByBrandAndNumberOfSeats(brand, numberOfSeats);
    }

    @GetMapping("/getByBrandAndTransmission")
    public List<CarDTO> getByBrandAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission){
        return carService.getByBrandAndTransmission(brand, transmission);
    }

    @GetMapping("/getByBrandAndCategory")
    public List<CarDTO> getByBrandAndCategory(@RequestParam String brand,@RequestParam Category category){
        return carService.getByBrandAndCategory(brand, category);
    }

    @GetMapping("/getByBrandAndYear")
    public List<CarDTO> getByBrandAndYear(@RequestParam String brand,@RequestParam Integer year){
        return carService.getByBrandAndYear(brand, year);
    }

    @GetMapping("/getByPetrolAndNumberOfSeats")
    public List<CarDTO> getByPetrolAndNumberOfSeats(@RequestParam Petrol petrol,@RequestParam Integer numberOfSeats){
        return carService.getByPetrolAndNumberOfSeats(petrol, numberOfSeats);
    }

    @GetMapping("/getByPetrolAndTransmission")
    public List<CarDTO> getByPetrolAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carService.getByPetrolAndTransmission(petrol, transmission);
    }

    @GetMapping("/getByPetrolAndCategory")
    public List<CarDTO> getByPetrolAndCategory(@RequestParam Petrol petrol,@RequestParam Category category){
        return carService.getByPetrolAndCategory(petrol, category);
    }

    @GetMapping("/getByPetrolAndYear")
    public List<CarDTO> getByPetrolAndYear(@RequestParam Petrol petrol,@RequestParam Integer year){
        return carService.getByPetrolAndYear(petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission){
        return carService.getByNumberOfSeatsAndTransmission(numberOfSeats, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndCategory(numberOfSeats, category);
    }

    @GetMapping("/getByNumberOfSeatsAndYear")
    public List<CarDTO> getByNumberOfSeatsAndYear(@RequestParam Integer numberOfSeats,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndYear(numberOfSeats, year);
    }

    @GetMapping("/getByTransmissionAndCategory")
    public List<CarDTO> getByTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByTransmissionAndCategory(transmission, category);
    }

    @GetMapping("/getByTransmissionAndYear")
    public List<CarDTO> getByTransmissionAndYear(@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByTransmissionAndYear(transmission, year);
    }

    @GetMapping("/getByCategoryAndYear")
    public List<CarDTO> getByCategoryAndYear(@RequestParam Category category,@RequestParam Integer year){
        return carService.getByCategoryAndYear(category, year);
    }
    //6;

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(numberOfSeats, brand, petrol, transmission, category, year);
    }

    //5
    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(numberOfSeats, brand, petrol, transmission, category);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(numberOfSeats, brand, petrol, transmission, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(numberOfSeats, brand, petrol, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(numberOfSeats, brand, transmission, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear")
    public List<CarDTO> getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(numberOfSeats, petrol,transmission,  category, year);
    }

    @GetMapping("/getByTransmissionAndBrandAndPetrolAndCategoryAndYear")
    public List<CarDTO> getByTransmissionAndBrandAndPetrolAndCategoryAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByTransmissionAndBrandAndPetrolAndCategoryAndYear( brand, petrol, transmission, category, year);
    }

    //3
    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrol")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrol(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol){
        return carService.getByNumberOfSeatsAndBrandAndPetrol(numberOfSeats, brand, petrol);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYear")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndYear(numberOfSeats, brand, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission){
        return carService.getByNumberOfSeatsAndBrandAndTransmission(numberOfSeats, brand, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndBrandAndCategory(numberOfSeats, brand, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYear")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Integer year){
        return carService.getByNumberOfSeatsAndPetrolAndYear(numberOfSeats, petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Category category){
        return carService.getByNumberOfSeatsAndPetrolAndCategory(numberOfSeats, petrol, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam  Petrol petrol,@RequestParam Transmission transmission){
        return carService.getByNumberOfSeatsAndPetrolAndTransmission(numberOfSeats, petrol, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Integer year,@RequestParam Transmission transmission){
        return carService.getByNumberOfSeatsAndYearAndTransmission(numberOfSeats, transmission, year);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndYearAndCategory(numberOfSeats,category,year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmissionAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndTransmissionAndCategory(numberOfSeats, transmission, category);
    }

    @GetMapping("/getByBrandAndPetrolAndYear")
    public List<CarDTO> getByBrandAndPetrolAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carService.getByBrandAndPetrolAndYear(brand, petrol, year);
    }

    @GetMapping("/getByBrandAndPetrolAndTransmission")
    public List<CarDTO> getByBrandAndPetrolAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carService.getByBrandAndPetrolAndTransmission(brand, petrol, transmission);
    }

    @GetMapping("/getByBrandAndPetrolAndCategory")
    public List<CarDTO> getByBrandAndPetrolAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carService.getByBrandAndPetrolAndCategory(brand, petrol, category);
    }

    @GetMapping("/getByBrandAndYearAndTransmission")
    public List<CarDTO> getByBrandAndYearAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByBrandAndYearAndTransmission(brand,transmission,year);
    }

    @GetMapping("/getByBrandAndYearAndCategory")
    public List<CarDTO> getByBrandAndYearAndCategory(@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByBrandAndYearAndCategory(brand, category, year);
    }

    @GetMapping("/getByBrandAndTransmissionAndCategory")
    public List<CarDTO> getByBrandAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByBrandAndTransmissionAndCategory(brand, transmission, category);
    }

    @GetMapping("/getByPetrolAndYearAndTransmission")
    public List<CarDTO> getByPetrolAndYearAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByPetrolAndYearAndTransmission(petrol, transmission, year );
    }

    @GetMapping("/getByPetrolAndYearAndCategory")
    public List<CarDTO> getByPetrolAndYearAndCategory(@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByPetrolAndYearAndCategory(petrol,category, year);
    }

    @GetMapping("/getByPetrolAndTransmissionAndCategory")
    public List<CarDTO> getByPetrolAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByPetrolAndTransmissionAndCategory(petrol, transmission, category);
    }

    @GetMapping("/getByYearAndTransmissionAndCategory")
    public List<CarDTO> getByYearAndTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByYearAndTransmissionAndCategory( transmission, category,year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndYear")
    //4
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndYear(numberOfSeats, brand, petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndTransmission(numberOfSeats, brand, petrol, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndBrandAndPetrolAndCategory(numberOfSeats, brand, petrol, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYearAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndPetrolAndYearAndTransmission(numberOfSeats, petrol,transmission,year );
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYearAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndPetrolAndYearAndCategory(numberOfSeats, petrol,category, year );
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndPetrolAndTransmissionAndCategory(numberOfSeats, petrol, transmission, category);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndTransmissionAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndYearAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndYearAndTransmissionAndCategory(numberOfSeats, transmission, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmissionAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByNumberOfSeatsAndBrandAndTransmissionAndCategory(numberOfSeats, brand,transmission,category );
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYearAndCategory")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndYearAndCategory(numberOfSeats, brand,category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYearAndTransmission")
    public List<CarDTO> getByNumberOfSeatsAndBrandAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByNumberOfSeatsAndBrandAndYearAndTransmission(numberOfSeats, brand, transmission, year);
    }

    @GetMapping("/getByBrandAndPetrolAndYearAndTransmission")
    public List<CarDTO> getByBrandAndPetrolAndYearAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carService.getByBrandAndPetrolAndYearAndTransmission(brand, petrol, transmission, year);
    }

    @GetMapping("/getByBrandAndPetrolAndYearAndCategory")
    public List<CarDTO> getByBrandAndPetrolAndYearAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByBrandAndPetrolAndYearAndCategory(brand, petrol, category, year);
    }

    @GetMapping("/getByBrandAndYearAndTransmissionAndCategory")
    public List<CarDTO> getByBrandAndYearAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByBrandAndYearAndTransmissionAndCategory(brand,  transmission, category, year);
    }

    @GetMapping("/getByBrandAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> getByBrandAndPetrolAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carService.getByBrandAndPetrolAndTransmissionAndCategory(brand, petrol, transmission, category);
    }

    @GetMapping("/getByPetrolAndYearAndTransmissionAndCategory")
    public List<CarDTO> getByPetrolAndYearAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carService.getByPetrolAndYearAndTransmissionAndCategory(petrol, transmission, category,  year);
    }


    @GetMapping("/mostRentedCar")
    public List<CarDTO> mostRentedCar(@RequestParam("days") Integer days){
        return carService.mostRentedCarInDays(days);
    }

    @GetMapping("/getByLastDays")
    public List<CarDTO> getByLastDays(@RequestParam("days") Integer days){
        return carService.getByLastDays(days);
    }

    @RequestMapping(value = "/setOpened", method = RequestMethod.PUT)
    public void setTaken(@RequestBody @Valid CarDTO contactDTO){
        carService.setTaken(contactDTO);
    }
}
