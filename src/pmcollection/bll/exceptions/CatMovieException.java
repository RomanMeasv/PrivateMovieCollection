package pmcollection.bll.exceptions;

public class CatMovieException extends Exception{
    String message;

    public CatMovieException(String message, Exception e)
    {
        this.message = message + " (Check console for details)";
        e.printStackTrace();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
