package com.thoughtworks.online_exam.auth.service;

import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.common.exception.InvalidEmailException;
import com.thoughtworks.online_exam.common.exception.UnsupportedEmailException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void signup(AuthInfo authInfoWithInvalidEmail) {
        String email = authInfoWithInvalidEmail.getEmail();
        if(!email.contains("@")) {
            throw new InvalidEmailException();
        }

        if(!email.contains("@mail.tsinghua.edu.cn")){
            throw new UnsupportedEmailException();
        }
    }
}
