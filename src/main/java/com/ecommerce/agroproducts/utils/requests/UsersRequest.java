package com.ecommerce.agroproducts.utils.requests;

import lombok.Data;

import java.io.Serializable;
@Data
public class UsersRequest implements  Serializable {

       private  String  firstName,lastname,phoneNumber,username;


}
