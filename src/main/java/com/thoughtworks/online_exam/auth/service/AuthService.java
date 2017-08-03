package com.thoughtworks.online_exam.auth.service;

import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.common.exception.InvalidEmailException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void signup(AuthInfo authInfoWithInvalidEmail) {
        if(!authInfoWithInvalidEmail.getEmail().contains("@")) {
            throw new InvalidEmailException();
        }
    }
}
