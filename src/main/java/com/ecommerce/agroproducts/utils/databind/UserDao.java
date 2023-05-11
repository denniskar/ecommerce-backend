package com.ecommerce.agroproducts.utils.databind;
import com.ecommerce.agroproducts.utils.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZonedDateTime;

public interface UserDao {
    Long getId();
    String getFirstName();

    String getOtherName();

    String getLastName();

    String getEmail();

    String getPhoneNumber();

    String getUsername();

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    ZonedDateTime getDateCreated();

    RoleDao getRoles();

    UserStatus getStatus();

    @JsonIgnore
    String getPassword();
}


