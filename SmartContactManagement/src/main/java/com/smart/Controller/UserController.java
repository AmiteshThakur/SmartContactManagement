package com.smart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
// ab jitne bi url hai jo user se start honge isme check karenge.....
public class UserController {

    @RequestMapping("/index")
    // this url becomes like : http://localhost:3306/user/index
    public String dashboard()
    {
        return "normal/user_dashboard";
    }
}
