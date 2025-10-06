package com.example.Final.controller;

import com.example.Final.model.Room;
import com.example.Final.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    // Get all rooms
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get available rooms only
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Add a new room
    @PostMapping("/add")
    public String addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return "Room added successfully!";
    }

    // Delete room by room number
    @DeleteMapping("/delete/{roomNumber}")
    public String deleteRoom(@PathVariable String roomNumber) {
        roomService.deleteRoom(roomNumber);
        return "Room deleted successfully!";
    }
    
  
}
