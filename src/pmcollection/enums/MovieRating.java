package pmcollection.enums;

public enum MovieRating {
    MAX(10), MIN(0);
    //If min was going to set smaller than zero, then MovieLogic.filterMoviesByRating shall be modified

    float value;

    MovieRating(float value)
    {
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }
}
