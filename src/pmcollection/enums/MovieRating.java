package pmcollection.enums;

public enum MovieRating {
    MAX(10), MIN(0);

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
