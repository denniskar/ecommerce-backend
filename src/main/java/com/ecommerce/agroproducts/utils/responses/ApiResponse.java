package com.ecommerce.agroproducts.utils.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private  int status;
    private  String message;


    public static  ApiResponse response(int status ,String message){
        ApiResponse response =new ApiResponse();
        response.setMessage(message);
        response.setStatus(status);
        return  response;
    }
}
