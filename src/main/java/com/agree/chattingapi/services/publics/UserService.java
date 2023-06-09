package com.agree.chattingapi.services.publics;

import com.agree.chattingapi.conf.JwtUtils;
import com.agree.chattingapi.dtos.user.LoginRequest;
import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserInfo join(UserInfo userInfo){
        userRepository.save(userInfo);
        return userInfo;
    }

    @Transactional
    public String login(LoginRequest request){
        UserInfo findUser = userRepository.findById(request.getId()).get();

        if(request.getPw().equals(findUser.getPw())){
            String token = JwtUtils.generateToken(findUser.getId());
            return token;
        }else {
            return "fail";
        }
    }

    @Transactional
    public String doubleCheck(String id){
        boolean cnt = userRepository.existsById(id);

        if(cnt){
            return id + "는 이미 사용중입니다.";
        }else {
            return "사용 가능한 아이디입니다.";
        }
    }

    @Transactional
    public String modifyUser(ModifyUserRequest request){
        UserInfo findUser = userRepository.findById(request.getId()).orElse(null);

        if (findUser != null) {
            findUser.setName(request.getName());
            findUser.setBirth(request.getBirth());
            return "success";
        } else {
            return "fail";
        }
    }

}
