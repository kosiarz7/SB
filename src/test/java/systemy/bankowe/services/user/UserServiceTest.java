package systemy.bankowe.services.user;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.wkr.fluentrule.api.FluentExpectedException;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.services.user.NoSuchUserException;
import systemy.bankowe.services.user.UserService;

public class UserServiceTest {
    @Rule
    public FluentExpectedException exception = FluentExpectedException.none();

    @InjectMocks
    private UserService sut;
    @Mock
    private IUserDao userDao;

    @Before
    public void init() {
        sut = new UserService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowNoSuchUserExceptionIfUserDaoReturnUnloadedUserDto() throws Exception {
        // given
        exception.expect(NoSuchUserException.class);
        UserDto mock = mock(UserDto.class);
        when(mock.isLoaded()).thenReturn(false);
        when(userDao.loadUserByUserName(anyString())).thenReturn(mock);

        // when
        sut.loadUserByLogin("login");
        
        // then
    }
}