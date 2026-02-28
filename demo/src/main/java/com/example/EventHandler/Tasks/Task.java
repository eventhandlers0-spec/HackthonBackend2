package com.example.EventHandler.Tasks;

import com.example.EventHandler.User.Customer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;
    private String taskName;
    private LocalDateTime dateOfTask;
    private LocalTime gapForNotifications;
    private LocalTime whenToStartNotifications;
    private String location;
    private String eventType;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Customer> customers;

    public Task(){

    }
    public LocalTime getWhenToStartNotifications() {
        return whenToStartNotifications;
    }

    public void setWhenToStartNotifications(LocalTime whenToStartNotifications) {
        this.whenToStartNotifications = whenToStartNotifications;
    }


    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public LocalDateTime getDateOfTask() {
        return dateOfTask;
    }

    public void setDateOfTask(LocalDateTime dateOfTask) {
        this.dateOfTask = dateOfTask;
    }

    public LocalTime getGapForNotifications() {
        return gapForNotifications;
    }

    public void setGapForNotifications(LocalTime gapForNotifications) {
        this.gapForNotifications = gapForNotifications;
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
