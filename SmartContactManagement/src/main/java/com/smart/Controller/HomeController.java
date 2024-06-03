package com.smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.Entities.User;
import com.smart.dao.UserRepository;

@Controller
public class HomeController {
    @Autowired
  private UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test()
    {

        User user = new User();
        user.setName("Amitesh Thakur");
        user.setEmail("athakur9691@gmail.com");
        user.setAbout("I am a student");
       

        userRepository.save(user);

        return "Working ";
    }
}
