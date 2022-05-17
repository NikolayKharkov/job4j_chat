package ru.job4j.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.models.Room;
import ru.job4j.services.RoomService;
import ru.job4j.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;

    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable("id") int id) {
        Optional<Room> result = roomService.findById(id);
        return new ResponseEntity<>(result.orElse(new Room()),
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/newRoom")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        if (userService.findById(room.getUser().getId()).isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.CONFLICT
            );
        }
        room.setCreated(LocalDate.now());
        return new ResponseEntity<>(
                this.roomService.saveOrUpdate(room),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
