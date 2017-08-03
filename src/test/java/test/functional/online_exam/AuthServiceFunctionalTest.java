package test.functional.online_exam;

import com.thoughtworks.online_exam.OnlineExamApplication;
import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.service.AuthService;
import com.thoughtworks.online_exam.common.constant.EnvProfile;
import com.thoughtworks.online_exam.common.exception.InvalidEmailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(OnlineExamApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class AuthServiceFunctionalTest {
    @Autowired
    private AuthService authService;

    @Test(expected = InvalidEmailException.class)
    public void should_throw_exception_when_signup_with_invalid_email() {
        // given
        AuthInfo authInfoWithInvalidEmail = new AuthInfo(){{
            setEmail("XXX#abc.com");
            setPassword("passwd");
        }};

        // when
        authService.signup(authInfoWithInvalidEmail);

        // then throw exception
    }
}
