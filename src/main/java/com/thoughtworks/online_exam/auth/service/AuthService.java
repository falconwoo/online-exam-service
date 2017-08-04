package com.thoughtworks.online_exam.auth.service;

import com.thoughtworks.online_exam.auth.UserMapper;
import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.entity.AuthResult;
import com.thoughtworks.online_exam.auth.model.UserModel;
import com.thoughtworks.online_exam.auth.repository.UserRepository;
import com.thoughtworks.online_exam.common.exception.*;
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
        checkInfoIntegrality(authInfo);
        checkEmailFormat(authInfo.getEmail());
        checkEmailSupportList(authInfo.getEmail());
        UserModel model = createUser(authInfo);
        return createResult(model.getRole());
    }

    private AuthResult createResult(String role) {
        return new AuthResult(){{
            setStatusCode("SUCCESS");
            setRole(role);
            setToken(UUID.randomUUID().toString().replaceAll("-",""));
        }};
    }

    private UserModel createUser(AuthInfo authInfo) {
        checkDuplicateEmail(authInfo.getEmail());
        return saveAuthInfo(authInfo);
    }

    private UserModel saveAuthInfo(AuthInfo authInfo) {
        UserModel model = userMapper.map(authInfo, UserModel.class);
        model.setRole("2");
        model.setTimeCreated(new Date());
        userRepository.saveAndFlush(model);
        return model;
    }

    private void checkDuplicateEmail(String email) {
        if(userRepository.findUserByEmail(email) != null) {
            throw new RegisteredEmailException();
        }
    }

    private void checkEmailSupportList(String email) {
        if(!email.contains("@mail.tsinghua.edu.cn")){
            throw new UnsupportedEmailException();
        }
    }

    private void checkEmailFormat(String email) {
        if(!email.contains("@")) {
            throw new InvalidEmailException();
        }
    }

    private void checkInfoIntegrality(AuthInfo authInfo) {
        if(authInfo.getEmail() == null){
            throw new MissingEmailException();
        }

        if(authInfo.getPassword() == null){
            throw new MissingPasswordException();
        }
    }

    public AuthResult signin(AuthInfo account) {
        checkInfoIntegrality(account);

        UserModel userModel = checkAccount(account);
        return createResult(userModel.getRole());
    }

    private UserModel checkAccount(AuthInfo account) {
        UserModel userModel = userRepository.
                findUserByEmailAndPassword(account.getEmail(), account.getPassword());
        if(userModel == null){
            throw new WrongAccountException();
        }
        return userModel;
    }
}
