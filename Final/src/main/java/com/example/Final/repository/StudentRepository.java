package com.example.Final.repository;
import com.example.Final.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper for Student
    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setStartDate(rs.getDate("start_date"));
            s.setDueDate(rs.getDate("due_date"));
            s.setPrice(rs.getDouble("price"));
            s.setPaidAmt(rs.getDouble("paid_amt"));   // ✅ corrected
            s.setDueAmt(rs.getDouble("due_amt"));     // ✅ corrected
            s.setRoomId(rs.getInt("room_id"));        // ✅ corrected
            s.setClassType(rs.getString("class_type"));
            return s;
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    // Add a new student
    public void addStudent(Student s) {
        String sql = "INSERT INTO students(name, start_date, due_date, price, paid_amt, due_amt, room_id, class_type) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                s.getName(),
                s.getStartDate(),
                s.getDueDate(),
                s.getPrice(),
                s.getPaidAmt(),   // ✅ corrected
                s.getDueAmt(),    // ✅ corrected
                s.getRoomId(),    // ✅ corrected
                s.getClassType()
        );
    }

    // Update student details
    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, start_date=?, due_date=?, price=?, paid_amt=?, due_amt=?, room_id=?, class_type=? WHERE id=?";
        jdbcTemplate.update(sql,
                s.getName(),
                s.getStartDate(),
                s.getDueDate(),
                s.getPrice(),
                s.getPaidAmt(),   // ✅ corrected
                s.getDueAmt(),    // ✅ corrected
                s.getRoomId(),    // ✅ corrected
                s.getClassType(),
                s.getId()
        );
    }

    // Delete a student by ID
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
