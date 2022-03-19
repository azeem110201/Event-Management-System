package com.azeem.demo.controller;

import com.azeem.demo.entity.Events;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.MyUserDetails;
import com.azeem.demo.services.EventsService;
import com.azeem.demo.services.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/events")
public class EventsController {
    private EventsService eventsService;
    private UsersServiceInterface usersService;

    @Autowired
    public EventsController(EventsService eventsService,
                            UsersServiceInterface usersService){

        this.eventsService = eventsService;
        this.usersService = usersService;
    }

    @GetMapping("/list")
    public String listEvents(Model model){
        List<Events> events = eventsService.listEvents();

        model.addAttribute("event", events);

        return "list-events";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Events event = new Events();

        model.addAttribute("event", event);

        return "event-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("event") Events event){
        eventsService.saveEvent(event);

        return "redirect:/api/events/list";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("eventId") int theId,
                                    Model theModel) {

        Events theEvent = eventsService.getEventById(theId);

        theModel.addAttribute("event", theEvent);

        return "event-form";
    }

    @PostMapping("/register")
    public String showFormRegister(@RequestParam("eventId") int theId,
                                    Model theModel) {

        Events theEvent = eventsService.getEventById(theId);
        System.out.println(theEvent.getEventName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof MyUserDetails){
            String username = ((MyUserDetails)principal).getUsername();
            List<Users> usersList = theEvent.getUsersList();

            boolean flag = false;

            for(Users user: usersList){
                if(user.getUsername().equals(username)){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                // add user as it has not enrolled in the course
                Users users = usersService.getUserByUsername(username);
                usersList.add(users);
                theEvent.setUsersList(usersList);
                eventsService.saveEvent(theEvent);
            }
            else{
                // user has already registered for the event
            }
            usersList = theEvent.getUsersList();

            for(Users user: usersList){
                System.out.println(user.getUsername());
            }
        }

        theModel.addAttribute("event", theEvent);

        return "register-form";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("eventId") int theId) {
        eventsService.deleteEvent(theId);

        return "redirect:/api/events/list";

    }
}
