package test.functional.online_exam;

import com.thoughtworks.online_exam.OnlineExamApplication;
import com.thoughtworks.online_exam.auth.UserMapper;
import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.entity.AuthResult;
import com.thoughtworks.online_exam.auth.model.UserModel;
import com.thoughtworks.online_exam.auth.repository.UserRepository;
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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(OnlineExamApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class AuthServiceFunctionalTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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

    @Test(expected = InvalidPasswordException.class)
    public void should_throw_exception_when_signup_invalid_password() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("123");
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

    @Test(expected = WrongAccountException.class)
    public void should_throw_exception_when_signin_with_wrong_account() {
        // given
        AuthInfo correctAccount = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("123456");
        }};
        AuthInfo wrongAccount = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("55555");
        }};

        // when
        UserModel model = userMapper.map(correctAccount, UserModel.class);
        model.setRole("2");
        model.setTimeCreated(new Date());
        userRepository.saveAndFlush(model);
        authService.signin(wrongAccount);

        // then throw exception
    }

    @Test(expected = MissingEmailException.class)
    public void should_throw_exception_when_signin_missing_email() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setPassword("passwd");
        }};

        // when
        authService.signin(authInfo);

        // then throw exception
    }

    @Test(expected = MissingPasswordException.class)
    public void should_throw_exception_when_signin_missing_password() {
        // given
        AuthInfo authInfo = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
        }};

        // when
        authService.signin(authInfo);

        // then throw exception
    }

    @Test
    public void should_return_role_2_and_token_when_student_signin_succes(){
        // given
        AuthInfo studentAccount = new AuthInfo(){{
            setEmail("aloha@mail.tsinghua.edu.cn");
            setPassword("passwd");
        }};

        // when
        UserModel model = userMapper.map(studentAccount, UserModel.class);
        model.setRole("2");
        model.setTimeCreated(new Date());
        userRepository.saveAndFlush(model);
        AuthResult authResult = authService.signin(studentAccount);

        // then
        assertThat(authResult.getRole()).isEqualTo("2");
        assertThat(authResult.getToken()).isNotNull().isNotEmpty();
    }

    @Test
    public void should_return_role_1_and_token_when_teacher_signin_succes(){
        // given
        AuthInfo teacherAccount = new AuthInfo(){{
            setEmail("admin@siwow.edu.cn");
            setPassword("111111");
        }};

        // when
        UserModel model = userMapper.map(teacherAccount, UserModel.class);
        model.setRole("1");
        model.setTimeCreated(new Date());
        userRepository.saveAndFlush(model);
        AuthResult authResult = authService.signin(teacherAccount);

        // then
        assertThat(authResult.getRole()).isEqualTo("1");
        assertThat(authResult.getToken()).isNotNull().isNotEmpty();
    }
}
