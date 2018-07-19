package com.codingdojo.BeltExam.controllers;

import org.springframework.stereotype.Controller;
@Controller
public class MessageController {
    // @PostMapping("/messages")
	// public String createMessage(@RequestParam("message")String message, @RequestParam("eventID")Long idEvent, @RequestParam("userID")Long idUser, HttpSession session) {
	// 	if (session.getAttribute("id") == null) {
	// 		return "redirect:/";
	// 	}
	// 	Event event = eventService.findEvent(idEvent);
	// 	User user = userService.findUserById(idUser);
	// 	Message newMessage = new Message();
	// 	newMessage.setMessage(message);
	// 	newMessage.setUser(user);
	// 	newMessage.setEvent(event);
	// 	messageService.createMessage(newMessage);
	// 	return "redirect:/events/"+event.getId();
	
	// }


}
