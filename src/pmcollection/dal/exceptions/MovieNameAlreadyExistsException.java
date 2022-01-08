package pmcollection.dal.exceptions;

public class MovieNameAlreadyExistsException extends Throwable{
    String msg;

    public MovieNameAlreadyExistsException()
    {
        this.msg = "Cannot create movie, since a movie with the same name already exists in the database!";
    }

    @Override
    public String getMessage()
    {
        return msg;
    }
}
