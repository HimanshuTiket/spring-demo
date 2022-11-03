package com.himanshu.error;

import java.util.HashMap;
import java.util.Map;

public class InternalServerError extends Exception {

    private Map<String, String> error ;

    public InternalServerError(String message){
        this.error = new HashMap<>();

        this.error.put("error", "Internal Server Error !");
        this.error.put("message", message);
    }

    public InternalServerError(Map<String, String> error){
        this.error = new HashMap<>();

        this.error.put("error", "Internal Server Error !");

        this.error.putAll(error);
    }

    public Map<String, String> getError(){
        return error;
    }
}
