package com.thoughtworks.online_exam.auth.service;

import com.thoughtworks.online_exam.auth.UserMapper;
import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.entity.AuthResult;
import com.thoughtworks.online_exam.auth.model.UserModel;
import com.thoughtworks.online_exam.auth.repository.UserRepository;
import com.thoughtworks.online_exam.common.exception.InvalidEmailException;
import com.thoughtworks.online_exam.common.exception.RegisteredEmailException;
import com.thoughtworks.online_exam.common.exception.UnsupportedEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AuthService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public AuthResult signup(AuthInfo authInfo) {
        String email = authInfo.getEmail();
        if(!email.contains("@")) {
            throw new InvalidEmailException();
        }

        if(!email.contains("@mail.tsinghua.edu.cn")){
            throw new UnsupportedEmailException();
        }

        if(userRepository.findUserByEmail(email) != null) {
            throw new RegisteredEmailException();
        }

        UserModel model = userMapper.map(authInfo, UserModel.class);
        model.setRole("2");
        model.setTimeCreated(new Date());
        userRepository.saveAndFlush(model);

        String token = UUID.randomUUID().toString().replaceAll("-","");
        return new AuthResult(){{
            setRole(model.getRole());
            setToken(token);
        }};
    }
}
