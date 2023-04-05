package ibf2022.paf.assessment.server.controllers;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService todoSvc;

    @PostMapping
    public String postTodoList(@RequestBody MultiValueMap<String,String> form, Model model) {
        String username = form.getFirst("username");
        List<Task> taskList = new LinkedList<>();
        int taskCount;
        for (int i=0;i<taskCount;i++){
            Task task = new Task();
            task.setDescription(form.getFirst("description"+"-"+String.valueOf(i)));
            task.setPriority(Integer.parseInt(form.getFirst("priority"+"-"+String.valueOf(i))));
            task.setDueDate(Date.valueOf(form.getFirst("dueDate"+"-"+String.valueOf(i))));
            taskList.add(task);
        }
        todoSvc.upsertTask(taskList, username);
        model.addAttribute("username", username);
        model.addAttribute("taskCount", taskCount);
        return "result";
    }
    
}
