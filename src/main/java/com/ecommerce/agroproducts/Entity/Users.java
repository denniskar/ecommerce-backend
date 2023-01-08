package com.ecommerce.agroproducts.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String username;
    private String phoneNumber;
    private String firstName,LastName;
//    @OneToMany
//    private List <Roles> roles;

    @OneToOne(mappedBy = "users")
    private AssignedProducts loans;

}
