package com.ecommerce.agroproducts.service;
import com.ecommerce.agroproducts.utils.UserStatus;
import com.ecommerce.agroproducts.utils.databind.RoleDao;
import com.ecommerce.agroproducts.utils.databind.UserDao;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersService userService;


    public CustomUserDetailsService(UsersService userService) {
        this.userService = userService;

    }

    public static Set<String> privileges(RoleDao role) {

        Set<String> privilege = new HashSet<>();
        if (role.getPrivileges() != null && !role.getPrivileges().isEmpty()) {
            privilege.add(String.format("ROLE_%s", role.getRole().toUpperCase()));
            role.getPrivileges().forEach(p -> {

                privilege.add(p.getPrivilege());

            });
        }
        return privilege;

    }

    @Override

    public UserDetails loadUserByUsername(String username) {

        Optional<UserDao> users = userService.getUserByUsername(username);
        if (users.isPresent()) {

            UserDao user = users.get();


            if (user == null || !user.getStatus().equals(UserStatus.Active)) {
                throw new BadCredentialsException(String.format("User is %s", user.getStatus()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(),
                    getAuthorities(user.getRoles()));
        } else {
            throw new BadCredentialsException(String.format("user with provided username %s not found.", username));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(RoleDao roleDao) {
        return getGrantedPrivileges(roleDao);
    }

    private Collection<? extends GrantedAuthority> getGrantedPrivileges(RoleDao roleDao) {

        return privileges(roleDao).stream().map(SimpleGrantedAuthority::new).collect(toList());

    }


}
