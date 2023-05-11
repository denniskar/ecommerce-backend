package com.ecommerce.agroproducts.utils.requests;

import com.ecommerce.agroproducts.utils.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class UsersRequest {

       private String firstName;

       private String otherName;

       private String lastName;

       private String username;

       private String email;

       private String phoneNumber;

       private String password;

       private UserStatus status;

       private String role;

       public UsersRequest() {
       }

       public UsersRequest(String firstName, String otherName, String lastName, String username, String email, String phoneNumber, String password, UserStatus status, String role) {
              this.firstName = firstName;
              this.otherName = otherName;
              this.lastName = lastName;
              this.username = username;
              this.email = email;
              this.phoneNumber = phoneNumber;
              this.password = password;
              this.status = status;
              this.role = role;
       }

       public String getFirstName() {
              return firstName;
       }

       public void setFirstName(String firstName) {
              this.firstName = firstName;
       }

       public String getOtherName() {
              return otherName;
       }

       public void setOtherName(String otherName) {
              this.otherName = otherName;
       }

       public String getLastName() {
              return lastName;
       }

       public void setLastName(String lastName) {
              this.lastName = lastName;
       }

       public String getUsername() {
              return username;
       }

       public void setUsername(String username) {
              this.username = username;
       }

       public String getEmail() {
              return email;
       }

       public void setEmail(String email) {
              this.email = email;
       }

       public String getPhoneNumber() {
              return phoneNumber;
       }

       public void setPhoneNumber(String phoneNumber) {
              this.phoneNumber = phoneNumber;
       }

       public String getPassword() {
              return password;
       }

       public void setPassword(String password) {
              this.password = password;
       }

       public UserStatus getStatus() {
              return status;
       }

       public void setStatus(UserStatus status) {
              this.status = status;
       }

       public String getRole() {
              return role;
       }

       public void setRole(String role) {
              this.role = role;
       }
}
