package com.ecommerce.agroproducts.utils.databind;

import java.util.Set;

public interface RoleDao {
    String getRole();
    Set<PrivilegesDao> getPrivileges();
}
