package ru.job4j.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.services.RoleService;
import ru.job4j.models.Role;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") int id) {
        Optional<Role> result = roleService.findById(id);
        return new ResponseEntity<>(result.orElse(new Role()),
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByName/{roleName}")
    public ResponseEntity<Role> findByName(@PathVariable("roleName") String roleName) {
        Optional<Role> result = roleService.findByName(roleName);
        return new ResponseEntity<>(result.orElse(new Role()),
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
