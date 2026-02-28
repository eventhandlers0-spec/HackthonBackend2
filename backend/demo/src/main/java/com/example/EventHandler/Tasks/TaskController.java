package com.example.EventHandler.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Event")
public class TaskController {
  @Autowired
    private TaskServices taskServices;
  @PostMapping("/addEvent")
    public void addTask(@RequestBody Task task){
      taskServices.addTask(task);
  }
  @DeleteMapping("/removeEvent")
    public void removeTask(@RequestParam int task_id){
       taskServices.removeTask(task_id);
  }
  @GetMapping("/allEvents")
    public List<Task> allTasks(){
     return taskServices.allTasks();

  }
}
