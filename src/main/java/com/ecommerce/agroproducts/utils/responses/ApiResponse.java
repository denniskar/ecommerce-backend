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
    private  Object data;


    public static  ApiResponse response(int status ,String message){
        ApiResponse response =new ApiResponse();
        response.setMessage(message);
        response.setStatus(status);
        return  response;
    }

    public static ApiResponse ofSuccess(int status,String message,Object data){
        ApiResponse response=new ApiResponse();
        response.setMessage(message);
        response.setStatus(status);
        response.setData(data);
        return  response;
    }
}
