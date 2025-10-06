package com.example.Final.repository;
import com.example.Final.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ RowMapper for Room
    private static class RoomRowMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();
            room.setRoomNumber(rs.getString("room_number"));
            room.setRoomType(rs.getString("room_type"));
            room.setOccupied(rs.getBoolean("is_occupied"));
            return room;
        }
    }

    // ✅ Get all rooms
    public List<Room> getAllRooms() {
        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.query(sql, new RoomRowMapper());
    }

    // ✅ Get available rooms only
    public List<Room> getAvailableRooms() {
        String sql = "SELECT * FROM rooms WHERE is_occupied = FALSE";
        return jdbcTemplate.query(sql, new RoomRowMapper());
    }

    // ✅ Add a new room
    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, room_type, is_occupied) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, room.getRoomNumber(), room.getRoomType(), room.isOccupied());
    }

    // ✅ Delete room
    public void deleteRoom(String roomNumber) {
        String sql = "DELETE FROM rooms WHERE room_number = ?";
        jdbcTemplate.update(sql, roomNumber);
    }
}
