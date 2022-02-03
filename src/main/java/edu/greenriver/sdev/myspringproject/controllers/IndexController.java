package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.Event;
import edu.greenriver.sdev.myspringproject.models.User;
import edu.greenriver.sdev.myspringproject.service.EventService;
import edu.greenriver.sdev.myspringproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for the Overlander page
 * @author Ryan Rivera
 * @version 1.0
 */
@Controller
public class IndexController
{
    private EventService eventService;
    private UserService userService;

    /**
     * Constructor
     * @param eventService Event service layer
     * @param userService Login service layer
     */
    public IndexController(EventService eventService, UserService userService)
    {
        this.eventService = eventService;
        this.userService = userService;
    }

    /**
     * Assigns mapping for and returns the index page
     * @return String name of the template file
     */
    @RequestMapping(value = {"", "/","/index", "/index.html", "/overlander"})
    public String index()
    {
        return "index";
    }

    /**
     * Assigns mapping for and returns the event summary page
     * @param model Model with elements accessible by the html markup
     * @return String name of the template file
     */
    @RequestMapping("overlander/events/all")
    public String eventsAll(Model model)
    {
        model.addAttribute("events", eventService.allEvents());
        return "allEvents";
    }

    /**
     * Assigns mapping for and returns the individual event details page
     * @param model Model with elements accessible by the html markup
     * @param id int Id of event
     * @return String name of the template file
     */
    @RequestMapping("overlander/events/details/{id}")
    public String eventsDetails(Model model, @PathVariable int id)
    {
        model.addAttribute("event", eventService.findEventById(id));
        return "eventDetails";
    }

    /**
     * Assigns GET mapping for and returns the add page
     * @param model Model with elements accessible by the html markup
     * @return String name of the template file
     */
    @GetMapping("overlander/events/add")
    public String loadAddForm(Model model)
    {
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    /**
     * Assigns POST mapping for the add page, saves the event parameter to the database
     * then redirects to the event summary page
     * @param event Event object
     * @return String name of the template file
     */
    @PostMapping("overlander/events/add")
    public String handleAddForm(@ModelAttribute Event event)
    {
        eventService.saveEvent(event);
        return "redirect:/overlander/events/all";
    }

    /**
     * Assigns GET mapping for and returns the edit page, uses the id parameter to return
     * an event from the database
     * @param model Model to add elements accessible to html
     * @param id int Id of event
     * @return String name of the template file
     */
    @GetMapping("overlander/events/edit/{id}")
    public String loadUpdateEventForm(Model model, @PathVariable int id)
    {
        model.addAttribute("event", eventService.findEventById(id));
        return "addEvent";
    }

    /**
     * Assigns mapping for the delete route, uses the id parameter to delete an event from the
     * database
     * @param id int Id of event
     * @return String name of the template file
     */
    @GetMapping("overlander/events/delete/{id}")
    public String deleteEvent(@PathVariable int id)
    {
        eventService.deleteEvent(id);
        return "redirect:/overlander/events/all";
    }

    /**
     * Assigns mapping for and returns the apiDataDisplay page
     * @return String name of the template file
     */
    @GetMapping("overlander/apiDataDisplay")
    public String apiDataDisplay()
    {
        return "apiDataDisplay";
    }

    /**
     * Assigns mapping for and returns the adminDashboard page
     * @return String name of the template file
     */
    @GetMapping("overlander/adminDashboard")
    public String adminDash()
    {
        return "adminDashboard";
    }

    /**
     * Assigns mapping for and returns the register page
     * @param model Model with attributes accessible to the html
     * @return String name of the template path
     */
    @GetMapping("overlander/register")
    public String register(Model model, @RequestParam(required = false) User user)
    {
        model.addAttribute("user", user != null ? user : new User());
        return "register";
    }

    /**
     * Assigns POST mapping for the register page, saves the user parameter to the database
     * then redirects to the login page
     * @param redirectAttributes RedirectAttribute
     * @param user User object
     * @return String name of the template path
     */
    @PostMapping("overlander/register/add")
    public String register(RedirectAttributes redirectAttributes, @ModelAttribute User user)
    {
        if(userService.userExists(user.getUsername())){
            redirectAttributes.addFlashAttribute("error", "That username is not available");
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/overlander/register";
        }
        userService.saveUser(user);

        return "redirect:/overlander/";
    }

    /**
     * Assigns GET mapping for the login page
     * @param model Model with attributes accessible to the html
     * @param error String error message
     * @return String name of the template path
     */
    @GetMapping("overlander/login")
    public String login(Model model, @RequestParam(required = false) String error)
    {
        if(error != null){
            model.addAttribute("error", "Username/password not recognized");
        }
        return "login";
    }
}