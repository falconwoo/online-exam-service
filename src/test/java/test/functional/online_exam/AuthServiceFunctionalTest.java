package test.functional.online_exam;

import com.thoughtworks.online_exam.OnlineExamApplication;
import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.entity.AuthResult;
import com.thoughtworks.online_exam.auth.service.AuthService;
import com.thoughtworks.online_exam.common.constant.EnvProfile;
import com.thoughtworks.online_exam.common.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


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

    @Test(expected = UnsupportedEmailException.class)
    public void should_throw_exception_when_signup_with_unsupported_email() {
        // given
        AuthInfo authInfoWithUnsupportedEmail = new AuthInfo(){{
            setEmail("abc@ecust.com");
            setPassword("passwd");
        }};

        // when
        authService.signup(authInfoWithUnsupportedEmail);

        // then throw exception
    }

    @Test(expected = RegisteredEmailException.class)
    public void should_throw_exception_when_signup_repeatly_with_same_email() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("passwd");
        }};

        // when
        authService.signup(authInfo);
        authService.signup(authInfo);

        // then throw exception
    }

    @Test
    public void should_return_role_2_and_token_when_signup_succes(){
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("passwd");
        }};

        // when
        AuthResult authResult = authService.signup(authInfo);

        // then
        assertThat(authResult.getRole()).isEqualTo("2");
        assertThat(authResult.getToken()).isNotNull().isNotEmpty();
    }

    @Test(expected = MissingEmailException.class)
    public void should_throw_exception_when_signup_missing_email() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setPassword("passwd");
        }};

        // when
        authService.signup(authInfo);

        // then throw exception
    }

    @Test(expected = MissingPasswordException.class)
    public void should_throw_exception_when_signup_missing_password() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
        }};

        // when
        authService.signup(authInfo);

        // then throw exception
    }


}
