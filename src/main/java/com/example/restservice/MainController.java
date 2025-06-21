package com.example.restservice;

import com.example.dbmodels.User;
import com.example.dtos.CreateUserDTO;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.utils.AppLogger;
import com.example.utils.GlobalExceptionHandler;
import com.example.utils.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

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
            @RequestBody CreateUserDTO createUserDTO) {
        User user = User.fromDTO(createUserDTO);
        try {
            userRepository.save(user);
        }catch(Exception e){
            log.error(MainController.class.getName() ,e.getMessage(), "testID", e);
            System.out.println("\n\n" + request.toString());
            return exceptionHandler.handleAllExceptions(request, e);
        }
        log.info("User Created successfully");
        ResponseWrapper<User> response = ResponseWrapper.<User>builder()
                .data(null)
                .size(0)
                .page(1)
                .responseCode(HttpStatus.CREATED.value())
                .message("Request accepted Successfully")
                .totalElements(0)
                .totalPages(0)
                .timestamp(Instant.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
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
                .timestamp(Instant.now().toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
