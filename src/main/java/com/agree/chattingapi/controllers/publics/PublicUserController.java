package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.user.LoginRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.publics.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    @Autowired
    UserService userService;

    @PostMapping("/join")
    public CommonResponse<UserInfo> join(@RequestBody UserInfo user){
        return new CommonResponse<>(userService.join(user));
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody LoginRequest request){
        return new CommonResponse<>(userService.login(request));
    }

    @GetMapping("/double-check/{id}")
    public CommonResponse<String> doubleCheck(@PathVariable String id){
        return new CommonResponse<>(userService.doubleCheck(id));
    }

}
