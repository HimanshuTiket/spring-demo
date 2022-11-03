package com.himanshu.helpers;

import org.bson.types.ObjectId;
import org.springframework.http.HttpMethod;

public class Helpers {

    private Helpers() {}

    public static boolean validateId(String id){

        System.out.println(id);
        System.out.println(ObjectId.isValid(id));

        return ObjectId.isValid(id);
    }

    public static HttpMethod getHttpMethod(String method){
        HttpMethod status = HttpMethod.GET;

        if(method == "PUT") status = HttpMethod.PUT;
        if(method == "POST") status = HttpMethod.POST;
        if(method == "PATCH") status = HttpMethod.PATCH;
        if(method == "DELETE") status = HttpMethod.DELETE;

        return status;
    }


}
