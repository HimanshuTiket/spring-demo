package com.himanshu.error;

import java.util.HashMap;
import java.util.Map;

public class BadRequestError extends Exception {

    private Map<String, String> error ;

    public BadRequestError(String message){
        this.error = new HashMap<>();

        this.error.put("error", "Bad Request !");
        this.error.put("message", message);
    }

    public BadRequestError(Map<String, String> error){
        this.error = new HashMap<>();

        this.error.put("error", "Bad Request !");

        this.error.putAll(error);
    }


    public Map<String, String> getError(){
        return error;
    }
}
