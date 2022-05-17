package ru.job4j.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.services.RoleService;
import ru.job4j.services.UserService;
import ru.job4j.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        Optional<User> result = userService.findById(id);
        return new ResponseEntity<>(result.orElse(new User()),
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.addRole(roleService.findByName("ROLE_USER").get());
        return new ResponseEntity<>(
                this.userService.saveOrUpdate(user),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/editUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        this.userService.saveOrUpdate(user);
        return ResponseEntity.ok().build();
    }
}
