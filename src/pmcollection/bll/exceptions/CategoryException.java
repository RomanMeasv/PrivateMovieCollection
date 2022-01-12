package pmcollection.bll.exceptions;

public class CategoryException extends Exception{
    String message;

    public CategoryException(String message, Exception e)
    {
        this.message = message + " (Check console for details)";
        e.printStackTrace();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
