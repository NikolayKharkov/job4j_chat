package ru.job4j.services;

import org.springframework.stereotype.Service;
import ru.job4j.models.Room;
import ru.job4j.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    public Room saveOrUpdate(Room room) {
        return roomRepository.save(room);
    }

    public void delete(int id) {
        roomRepository.deleteById(id);
    }
}
