package org.fintech.paytech.domain.core.user.controller;

import org.fintech.paytech.domain.core.user.dto.common.UserLoginDTO;
import org.fintech.paytech.domain.core.user.dto.common.UserLogoutDTO;
import org.fintech.paytech.domain.core.user.model.User;
import org.fintech.paytech.domain.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*@ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<User> exception(UserNotFoundException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    // Use this keyword to access the current object (current userController class instance)
    @GetMapping("")
    public Iterable<User> findAllUser() {
        return userService.findAllUsers();
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        if (token != null) {
            return new ResponseEntity<String>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody UserLogoutDTO userLogoutDTO) {
        boolean success = userService.logout(userLogoutDTO);
        if (success) {
            return new ResponseEntity<String>("Logout Successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Logout Failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*@RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException();
        }
        users.remove(id);
        user.setId(id);
        users.put(id, user);
        return new ResponseEntity<>("User is updated successfully.", HttpStatus.OK);
    }*/

    @GetMapping("/me")
    public ResponseEntity<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
