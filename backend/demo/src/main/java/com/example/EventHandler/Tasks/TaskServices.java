package com.example.EventHandler.Tasks;

import com.example.EventHandler.User.Customer;
import com.example.EventHandler.mailService.emailService;
import com.example.EventHandler.User.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private emailService emailService;
    public void addTask(Task task){
        List<Customer> list=customerRepository.findByLocation(task.getLocation());
        for(int i=0;i<list.size();i++){
            Customer c=list.get(i);
            emailService.sendMail(c.getEmail(), "New Event Has been Anounced in your local area",
                    "EventName  "+task.getTaskName()+
                            "EventTimings  "+task.getDateOfTask()
                    );
        }
        taskRepository.save(task);
    }
    public void removeTask(int task_id){
        Task t= taskRepository.findById(task_id);
        if(t==null)
            throw new RuntimeException("Task Not Found");
        taskRepository.delete(t);
    }
    public List<Task> allTasks(){
          return taskRepository.findAll();
    }
}
