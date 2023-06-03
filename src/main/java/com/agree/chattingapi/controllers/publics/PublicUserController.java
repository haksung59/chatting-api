package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.user.LoginRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.services.publics.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    @Autowired
    UserService userService;

    @PostMapping("/join")
    public UserInfo join(@RequestBody UserInfo user){
        return userService.join(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/double-check/{id}")
    public String doubleCheck(@PathVariable String id){
        return userService.doubleCheck(id);
    }

}
