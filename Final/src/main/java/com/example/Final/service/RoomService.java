package com.example.Final.service;

import com.example.Final.model.Room;
import com.example.Final.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.getAvailableRooms();
    }

    public void addRoom(Room room) {
        roomRepository.addRoom(room);
    }

    public void deleteRoom(String roomNumber) {
        roomRepository.deleteRoom(roomNumber);
    }
}
