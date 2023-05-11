package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.Entity.Roles;
import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.repository.Repo;
import com.ecommerce.agroproducts.repository.RolesRepository;
import com.ecommerce.agroproducts.repository.UsersRepository;
import com.ecommerce.agroproducts.service.UsersService;
import com.ecommerce.agroproducts.utils.databind.RoleDao;
import com.ecommerce.agroproducts.utils.databind.UserDao;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    private final Repo repo;
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, Repo repo) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    @Override
    public ResponseEntity<ApiResponse> getUsers() {
        List<UserDao> users;
        try {

            users = usersRepository.findBy();
            return ResponseEntity.ok(ApiResponse.ofSuccess(0, "success", users));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.response(1, "An Error Occurred"));
        }


    }

    @Override
    public ResponseEntity<ApiResponse> createUsers(UsersRequest createUserRequest) {
        Optional<Users> validateUser = usersRepository.findByUsername(createUserRequest.getUsername());
        //   Request request =new Request();
        if (validateUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "User already Exists"));
        }
        Optional<Roles> roles = rolesRepository.findByRole(createUserRequest.getRole());
        if (!roles.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "Invalid Role"));
        }

        Optional<Users> phone = usersRepository.findByPhoneNumber(createUserRequest.getPhoneNumber());
        if (phone.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "phone number already exists"));
        }
//        Optional<Users>email=usersRepository.findByEmail(createUserRequest.getEmail());
//        if(email.isPresent()){
//            request.setMessage("Email not available");
//            request.setStatus(1);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
//        }


        try {
            createUserRequest.setPassword(createUserRequest.getPassword() != null ? passwordEncoder.encode(createUserRequest.getPassword()) : passwordEncoder.encode(createUserRequest.getPhoneNumber()));
            usersRepository.save(Users.ofCreate(createUserRequest, roles.get()));
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.response(0, "Success"));
        } catch (Exception d) {
            LOGGER.error(d.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "An Error Occurred"));
        }
    }

    @Override
    public Optional<UserDao> getUserByUsername(String username) {
        try {
            return repo.findByUsername(username);
        } catch (Exception e) {
            LOGGER.error("Fetching User By Email Error", e);
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ApiResponse> getRoles() {
        try {
            List<RoleDao> roles;
            //  List<Roles>roles=rolesRepository.findAll();

            roles = rolesRepository.findBy();
            return ResponseEntity.ok(ApiResponse.response(0, "success"));


        } catch (Exception e) {
            LOGGER.error("error =" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "An Error occurred"));
        }


    }
}
