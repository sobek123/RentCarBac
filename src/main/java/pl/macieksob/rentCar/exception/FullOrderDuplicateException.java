package pl.macieksob.rentCar.exception;

public class FullOrderDuplicateException extends RuntimeException{

    public FullOrderDuplicateException(String message){
        super(message);
    }
}
