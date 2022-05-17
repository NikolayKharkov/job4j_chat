package ru.job4j.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
