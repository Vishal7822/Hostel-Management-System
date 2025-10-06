package com.example.Final.model;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Student {

    private int id;
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    private double price;
    private double paidAmt;   // matches DB column paid_amt
    private double dueAmt;    // matches DB column due_amt
    private int roomId;       // matches DB column room_id
    private String classType;

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getPaidAmt() { return paidAmt; }
    public void setPaidAmt(double paidAmt) { this.paidAmt = paidAmt; }

    public double getDueAmt() { return dueAmt; }
    public void setDueAmt(double dueAmt) { this.dueAmt = dueAmt; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }
}
