package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.publics.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/user/pw")
    public CommonResponse<String> modifyPw(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPw(request));
    }

    @GetMapping("/logout")
    public CommonResponse<String> logout(HttpServletResponse response){
        return new CommonResponse<>(userService.logout(response));
    }

}
