package sns.message.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import sns.message.MessageApplication;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void readUserTest() {
        UserDto user = userDao.retrieveUserById("cusonar");
        assertThat("cusonar", is(user.getUsername()));
        assertThat("YCU", is(user.getName()));
        assertThat("1234", is(user.getPassword()));
    }

    @Test
    public void readAuthorityTest() {
        List<GrantedAuthority> authorities = userDao.retrieveAuthority("cusonar");
        Iterator<GrantedAuthority> it = authorities.iterator();

        while(it.hasNext()){
            GrantedAuthority authority = it.next();
            assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
        }

    }
}