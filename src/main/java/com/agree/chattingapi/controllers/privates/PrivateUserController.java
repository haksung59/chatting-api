package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.publics.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/user")
public class PrivateUserController {

    private UserService userService;

    @Autowired
    public PrivateUserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    public CommonResponse<String> modifyUser(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyUser(request));
    }

}
