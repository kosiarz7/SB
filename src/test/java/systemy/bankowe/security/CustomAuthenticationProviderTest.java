package systemy.bankowe.security;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import pl.wkr.fluentrule.api.FluentExpectedException;
import systemy.bankowe.annotations.LoggerFactory;
import systemy.bankowe.annotations.LoggerPostProcessor;
import systemy.bankowe.security.CustomAuthenticationProvider;
import systemy.bankowe.services.encrypt.EncryptException;
import systemy.bankowe.services.encrypt.IEncryptor;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.NoSuchUserException;
import systemy.bankowe.services.user.UserData;

public class CustomAuthenticationProviderTest {
    @Rule
    public FluentExpectedException exception = FluentExpectedException.none();
    
    @InjectMocks
    private CustomAuthenticationProvider sut;
    
    @Mock
    private IEncryptor encrypt;
    @Mock
    private IUserService userService;
    
    @Before
    public void init() {
        sut = new CustomAuthenticationProvider();
        new LoggerPostProcessor(when(mock(LoggerFactory.class).getLogger(any())).thenReturn(mock(Logger.class))
                .getMock()).postProcessBeforeInitialization(sut, null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowAuthenticationExceptionIfUserDoesnotExist() throws Exception {
        // given
        exception.expect(AuthenticationException.class);
        String name = "ThisNameDoesnotExist";
        Authentication mock = when(mock(Authentication.class).getName()).thenReturn(name).getMock();
        when(userService.loadUserByLogin(anyString())).thenThrow(new NoSuchUserException(name));
        
        // when 
        sut.authenticate(mock);
        
        // then
    }
    
    @Test
    public void shouldThrowBadCredentialsExceptionIfPasswordIsWrong() throws Exception {
        // given
        exception.expect(BadCredentialsException.class);
        when(encrypt.encrypt(anyString())).thenReturn("PASS3");
        UserData userData = when(mock(UserData.class).getPassword()).thenReturn("PASS").getMock();
        
        // when
        sut.checkPassword("PASS2", userData);
        
        // then
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldThrowBadCredentialsExceptionIfEncryptorThrowException() throws Exception {
        // given
        exception.expect(BadCredentialsException.class);
        when(encrypt.encrypt(anyString())).thenThrow(EncryptException.class);
        UserData userData = when(mock(UserData.class).getPassword()).thenReturn("PASS2").getMock();
        
        // when
        sut.checkPassword("PASS2", userData);
        
        // then
    }

    @Test
    public void shouldThrowBadCredentialsExceptionIfUserIsBlocked() throws Exception {
        // given
        exception.expect(BadCredentialsException.class);
        UserData userData = when(mock(UserData.class).isEnabled()).thenReturn(false).getMock();
        
        // when
        sut.throwExceptionIfUserIsDisabled(userData);
        
        // then
    }
}
