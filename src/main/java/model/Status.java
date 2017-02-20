package model;

/**
 * Created by Student on 2/9/2017.
 */
public enum Status {
    NONE("NONE"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPEND");

    private final String code;

    Status(String code){

        this.code = code;
    }

    public String getCode(){

        return code;
    }
}
