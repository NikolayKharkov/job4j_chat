package ru.job4j.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.models.Message;
import ru.job4j.models.Room;
import ru.job4j.models.User;
import ru.job4j.services.MessageService;
import ru.job4j.services.RoomService;
import ru.job4j.services.UserService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RoomService roomService;
    private final UserService userService;
    private final MessageService messageService;

    public MessageController(RoomService roomService, UserService userService, MessageService messageService) {
        this.roomService = roomService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Room> addMessage(@RequestParam("idRoom") int idRoom,
                                           @RequestBody Message message) {
        Optional<Room> room = roomService.findById(idRoom);
        Optional<User> user = userService.findById(message.getUser().getId());
        if (room.isEmpty() || user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        message.setUser(user.get());
        message.setCreated(LocalDate.now());
        Room roomBase = room.get();
        roomBase.addMessage(message);
        return new ResponseEntity<>(
                this.roomService.saveOrUpdate(roomBase),
                HttpStatus.CREATED
        );
     }
}
