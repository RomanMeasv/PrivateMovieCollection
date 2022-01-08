package pmcollection.dal.exceptions;

public class MovieNameAlreadyExistsException extends Throwable{
    String msg;

    MovieNameAlreadyExistsException(String msg, Exception e)
    {
        this.msg = "Cannot create movie, since a movie with the same name already exists in the database!";
        e.printStackTrace();
    }

    @Override
    public String getMessage()
    {
        return msg;
    }
}
