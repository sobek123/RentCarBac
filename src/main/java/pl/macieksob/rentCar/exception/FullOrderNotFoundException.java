package pl.macieksob.rentCar.exception;

public class FullOrderNotFoundException extends RuntimeException{

    public FullOrderNotFoundException(String message){
        super(message);
    }


}
