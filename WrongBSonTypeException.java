public class WrongBSonTypeException extends RuntimeException{
    public WrongBSonTypeException(String type, String expected){
        super("Wrong BSon Type: " + type + ", expected " + expected);
    }
}
