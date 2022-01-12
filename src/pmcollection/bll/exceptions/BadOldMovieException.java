package pmcollection.bll.exceptions;

public class BadOldMovieException extends Exception{
    String message;

    public BadOldMovieException(String message, Exception e)
    {
        this.message = message + " (Check console for details)";
        e.printStackTrace();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
