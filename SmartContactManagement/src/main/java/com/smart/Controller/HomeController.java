package com.smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.dao.UserRepository;

@Controller
public class HomeController {
    @Autowired
  private UserRepository userRepository;

  @GetMapping({"home", "/"})

  public String home(Model model)
  {
    model.addAttribute("title", "Home - Smart contact Management");
    return "index";
  }  

  @RequestMapping("/about")
  public String About(Model model)
  {
    model.addAttribute("title", "About - Smart Contact Management");
    return "About";
  }
  

  @RequestMapping("/signup")
  public String Signup(Model model)
  {
    model.addAttribute("title", "Sign Up - Smart Contact Management");
    model.addAttribute("user", new User());
    return "Signup";
  }
  
  //this handler for registering user
  @PostMapping("/do_register")
  public String registerUser(@ModelAttribute("user") User user, @RequestParam(value="aggrement", defaultValue = "false") boolean agreement, Model model)
  {
    System.out.println("Agreeement"+agreement);
    System.out.println(user);

    return "Signup";
  }
  
  
  @GetMapping("/test")
    @ResponseBody
    // if we do not write @Response body then it will serach for html page of that name
    public String test()
    {

        User user = new User();
        user.setName("Amitesh Thakur");
        user.setEmail("athakur9691@gmail.com");
        user.setAbout("I am a student");
      
        Contact contact= new Contact();
        user.getContacts().add(contact);

        userRepository.save(user);

        return "Working ";
    }
}
