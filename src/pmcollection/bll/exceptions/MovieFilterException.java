package pmcollection.bll.exceptions;

public class MovieFilterException extends Exception{
    String message;

    public MovieFilterException(String message, Exception e)
    {
        this.message = message + " (Check console for details)";
        e.printStackTrace();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
