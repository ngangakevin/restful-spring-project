package com.example.restservice;

import com.example.dbmodels.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.utils.AppLogger;
import com.example.utils.GlobalExceptionHandler;
import com.example.utils.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    private final AppLogger log = AppLogger.getLogger(MainController.class);

    @PostMapping(path="/add")
    public ResponseEntity<?> addNewUser (
            HttpServletRequest request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        try {
            userRepository.save(user);
        }catch(Exception e){
            log.error(MainController.class.getName() ,e.getMessage(), "testID", e);
            System.out.println("\n\n" + request.toString());
            return exceptionHandler.handleAllExceptions(request, e);

        }
        log.info("User Created successfully");
        ResponseWrapper<User> response =  new ResponseWrapper<>(
                HttpStatus.CREATED.value(),
                "Request accepted Successfully",
                null,
                0,
                0,
                1,
                0,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>("response", HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<?> getAllUsers(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<User> users;
        try {
            users = userRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            log.error(MainController.class.getName(), e.getMessage(), "testID", e);
            return exceptionHandler.handleAllExceptions(request, e);
        }

        ResponseWrapper<User> response = ResponseWrapper.<User>builder()
                .responseCode(HttpStatus.OK.value())
                .message("Request accepted Successfully")
                .data(users.getContent()) // Page<MyEntity>
                .page(users.getNumber())
                .size(users.getSize())
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
