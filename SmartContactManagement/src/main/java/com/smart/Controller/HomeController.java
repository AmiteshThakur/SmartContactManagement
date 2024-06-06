package com.smart.Controller;

import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.dao.UserRepository;
import com.smart.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping({ "home", "/" })
  public String home(Model model) {
    model.addAttribute("title", "Home - Smart contact Management");
    return "index";
  }

  @RequestMapping("/about")
  public String About(Model model) {
    model.addAttribute("title", "About - Smart Contact Management");
    return "About";
  }

  @RequestMapping("/signup")
  public String Signup(Model model) {
    model.addAttribute("title", "Sign Up - Smart Contact Management");
    model.addAttribute("user", new User());
    return "Signup";
  }

  //this handler for registering user
  //@Valid triggers the validation we have applied on the Entitis.
  // BindingResult gives the result of the vlidation we have applied.......
  @PostMapping("/do_register")
  public String registerUser(
    @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
    @RequestParam(
      value = "agreement",
      defaultValue = "false"
    ) boolean agreement,
    Model model,
    
    HttpSession session
  ) {
    try {
      if (!agreement) {
        System.out.println("You have not agreed to terms and condition.");
        throw new Exception("You have not agreed to terms and condition.");
      }

      //checking server side validation ......
      if (bindingResult.hasErrors()) {
        System.out.println("Error: "+bindingResult.toString());
        model.addAttribute("user", user);
        return "Signup";
      }

      user.setRole("ROLE_USER");
      user.setEnabled(true);
      user.setImageUrl("userImg.jpg");
      // System.out.println("Agreeement"+agreement);
      // System.out.println(user);
      User result = this.userRepository.save(user);

      model.addAttribute("user", new User());

      session.setAttribute(
        "message",
        new Message("Success !!", "alert-success")
      );
      return "Signup";
    } catch (Exception exception) {
      exception.printStackTrace();
      model.addAttribute("user", user);
      session.setAttribute(
        "message",
        new Message(
          "Something went wrong !!" + exception.getMessage(),
          "alert-danger"
        )
      );
      return "Signup";
    }
  }







  

  @GetMapping("/test")
  @ResponseBody
  // if we do not write @Response body then it will serach for html page of that name
  public String test() {
    User user = new User();
    user.setName("Amitesh Thakur");
    user.setEmail("athakur9691@gmail.com");
    user.setAbout("I am a student");

    Contact contact = new Contact();
    user.getContacts().add(contact);

    userRepository.save(user);

    return "Working ";
  }
}
