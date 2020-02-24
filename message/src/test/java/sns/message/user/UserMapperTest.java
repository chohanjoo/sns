package sns.message.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExampleApplication.class)
@WebAppConfiguration
public class UserMapperTest {

    @Autowired
    UserDao userDao;

    @Test
    public void readUserTest() {
        UserDto user = userDao.re("cusonar");
        assertThat("cusonar", is(user.getUsername()));
        assertThat("YCU", is(user.getName()));
        assertThat("1234", is(user.getPassword()));
    }

    @Test
    public void readAuthorityTest() {
        List<String> authorities = userMapper.readAuthority("cusonar");
        assertThat(authorities, hasItems("ADMIN", "USER"));

        authorities= userMapper.readAuthority("abc");
        assertThat(authorities, hasItem("USER"));
    }
}