package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.services.publics.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class PrivateUserController {

    @Autowired
    UserService userService;

    @PutMapping("/user")
    public String modifyUser(@RequestBody ModifyUserRequest request){
        return userService.modifyUser(request);
    }

}
