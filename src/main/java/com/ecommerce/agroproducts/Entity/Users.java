package com.ecommerce.agroproducts.Entity;

import com.ecommerce.agroproducts.utils.UserStatus;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 20, nullable = false,unique = true)
    private  String username;
    @Column(name = "phoneNumber", length = 20, nullable = false,unique = true)
    private String phoneNumber;
    @Column(name = "firstName", length = 200, nullable = false)
    private String firstName;
    private String lastName;
    private String otherName;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime dateCreated;
    @Column(name = "password", length = 100, nullable = false)
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "enum('Active','Deactivated','SUSPENDED') DEFAULT 'Active'")
    private UserStatus status;

    //    @OneToMany
//    private List <Roles> roles;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
    private Set<AssignedProducts> loans;

    @JoinColumn(name = "roleId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Roles roles;

    public static Users ofCreate(UsersRequest createUserRequest, Roles roles) {
        Users users =new Users();
        users.setEmail(createUserRequest.getEmail());
        users.setUsername(createUserRequest.getUsername());
        users.setFirstName(createUserRequest.getFirstName());
        users.setRoles(roles);
        users.setLastName(createUserRequest.getLastName());
        users.setPhoneNumber(createUserRequest.getPhoneNumber());
        users.setPassword(createUserRequest.getPassword());
        users.setStatus(UserStatus.Active);
        return users;
    }

}
