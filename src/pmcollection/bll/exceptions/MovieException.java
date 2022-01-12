package pmcollection.bll.exceptions;

public class MovieException extends Exception{
    String message;

    public MovieException(String message, Exception e)
    {
        this.message = message + " (Check console for details)";
        e.printStackTrace();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
