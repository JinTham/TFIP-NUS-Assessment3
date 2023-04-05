package ibf2022.paf.assessment.server.controllers;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.InsertException;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService todoSvc;

    // @GetMapping(path={"/","/index"})
    // public String getLandingPage() {
    //     return "index.html";
    // }

    @PostMapping(path="/task")
    public ModelAndView postTodoList(@RequestParam MultiValueMap<String,String> form, Model model)  {
        ModelAndView view = new ModelAndView();
        String username = form.getFirst("username");
        List<Task> taskList = new LinkedList<>();
        int taskCount=0;
        
        while (true) {
            if (form.getFirst("description"+"-"+String.valueOf(taskCount)) == null) {
                break;
            }
            Task task = new Task();
            task.setDescription(form.getFirst("description"+"-"+String.valueOf(taskCount)));
            task.setPriority(Integer.parseInt(form.getFirst("priority"+"-"+String.valueOf(taskCount))));
            task.setDueDate(Date.valueOf(form.getFirst("dueDate"+"-"+String.valueOf(taskCount))));
            taskList.add(task);
            taskCount ++ ;
        }

        if (taskList.size() < 1) {
            view.setViewName("error");
            view.setStatus(HttpStatusCode.valueOf(500));
            return view;
        }

        try {
            todoSvc.upsertTask(taskList, username);
            model.addAttribute("username", username);
            model.addAttribute("taskCount", taskCount);
            view.setViewName("result");
            view.setStatus(HttpStatusCode.valueOf(200));
        } catch (InsertException ex) {
            view.setViewName("error");
            view.setStatus(HttpStatusCode.valueOf(500));
        }
        return view;
    }
    
}
