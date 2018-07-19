package com.codingdojo.BeltExam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.BeltExam.models.Event;
import com.codingdojo.BeltExam.models.User;
import com.codingdojo.BeltExam.services.EventService;
import com.codingdojo.BeltExam.services.MessageService;
import com.codingdojo.BeltExam.services.UserService;

@Controller
public class EventController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private EventService eventService;

  @GetMapping("/dashboard")
  public String dashboard(HttpSession session,Model model){
    System.out.println("Testing");
      if(session.getAttribute("id") != null) {
          User user = userService.findById((Long)session.getAttribute("id"));
      
          if(user != null) {
              model.addAttribute("user", user);
              model.addAttribute("event", new Event());
              model.addAttribute("myStateEvents", eventService.sameState(user.getState()));
              model.addAttribute("otherStateEvents", eventService.diffState(user.getState()));
              return "dashboard";
          }else {
              return "redirect:/";
          }
      }else{
          return "redirect:/";
      }
  }
    @GetMapping("/events/{id}")
    public String viewEvent(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirect) {
        if (session.getAttribute("id") == null) {
            redirect.addFlashAttribute("loginError", "You need to be logged infor editing !");            
            return "redirect:/";
        } else {
            Event event = eventService.findEvent(id);
                if (event == null) {
                return "redirect:/show";} 
                else{
                    User user = userService.findById((Long) session.getAttribute("id"));
                    model.addAttribute("user", user);
                    model.addAttribute("event", event);
                    return "show";
                }
            }
    }

@PostMapping("/events/create")
public String createEvent(@Valid @ModelAttribute("event") Event event,BindingResult result,HttpSession session,Model model){
    if (result.hasErrors()) {
        return "dashboard";
    }
    eventService.createEvent(event,userService.findById((Long) session.getAttribute("id")));
    
    return "redirect:/dashboard";
}

@RequestMapping(value="/edit/{id}")
    public String edit(@ModelAttribute("event") Event event, @PathVariable(value="id") Long id, Model model, HttpSession session, RedirectAttributes redir) {
        Long userId = (Long) session.getAttribute("id");
        if (userId != null) {            
            Event e = eventService.findEvent(id);
            User u = userService.findById(userId);
            if (e.getHoster().getId() == u.getId()) {
                model.addAttribute("event", e);
                return "editevent";
            }
        }
        redir.addFlashAttribute("badlogin", "You must be logged in to enter this webpage");
        return "redirect:/";
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.PUT)
    public String editEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, @PathVariable(value="id") Long id, Model model) {
        if (result.hasErrors()) return "editevent";
        else {
            Event e = eventService.findEvent(id);
            e.setName(event.getName());
            e.setEventDate(event.getEventDate());
            e.setCity(event.getCity());
            e.setState(event.getState());
            eventService.update(e);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value="/{id}/join")
    public String joinEvent(@PathVariable(value="id") Long id, HttpSession session){
        Event e = eventService.findEvent(id);
        User u = userService.findById((Long) session.getAttribute("id"));
        eventService.joinEvent(e, u);
        return "redirect:/dashboard";
    }

    @RequestMapping(value="/{id}/cancel")
    public String leaveEvent(@PathVariable(value="id") Long id, HttpSession session){
        Event e = eventService.findEvent(id);
        User u = userService.findById((Long) session.getAttribute("id"));
        eventService.leaveEvent(e, u);
        return "redirect:/dashboard";
    }
    
    @RequestMapping(value="/delete/{id}")
    public String deleteEvent(@PathVariable(value="id") Long id, HttpSession session, RedirectAttributes redir) {
        Long userId = (Long) session.getAttribute("id");
        if (userId != null) {
            Event e = eventService.findEvent(id);
            User u = userService.findById(userId);
            if (e.getHoster().getId() == u.getId()) {
                eventService.delete(e);
                return "redirect:/dashboard";
            }
        }
        redir.addFlashAttribute("badlogin", "You must be logged in to perform this action");
        return "redirect:/";
    }

}
