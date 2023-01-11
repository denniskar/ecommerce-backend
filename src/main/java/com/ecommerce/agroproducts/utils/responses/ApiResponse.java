package com.ecommerce.agroproducts.utils.responses;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {


    public Object response(int Status ,String message){
        Map<String,Object>response=new HashMap<>();
        response.put("status",Status);
        response.put("message",message);
        return  response;
    }
}
