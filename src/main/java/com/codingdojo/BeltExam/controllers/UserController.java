package com.codingdojo.BeltExam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.codingdojo.BeltExam.models.User;
import com.codingdojo.BeltExam.services.UserService;
import com.codingdojo.BeltExam.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
   private UserService userService;
   @Autowired
   private UserValidator userVal;

   @RequestMapping("/")
	public String index(@ModelAttribute("user")User user) {
		return "register";
	}

    @GetMapping("/logout") // logout 
    public String register(@ModelAttribute("user") User user, HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("user") User user, BindingResult res, RedirectAttributes flash, HttpSession session){
        userVal.validate(user, res);
        if(res.hasErrors()) {
            flash.addFlashAttribute("errors",res.getAllErrors());
            return "redirect:/";
        }

        User registerer = userService.register(user);

        if(registerer != null){
            session.setAttribute("id", registerer.getId());
            return "redirect:/dashboard";
        }else {
            flash.addFlashAttribute("registerError","A user with this email already exists.");
            return "redirect:/";
        }
    }
    @PostMapping("login")
    public String login( @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes flash){
        User user = userService.login(email, password);

        if(user == null){
            flash.addFlashAttribute("registerError", "Invalid Credentials");
            return "redirect:/";
        }else {
            session.setAttribute("id", user.getId());
            return "redirect:/dashboard";
        }
    }
}
