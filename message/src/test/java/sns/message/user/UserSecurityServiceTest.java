package sns.message.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sns.message.dao.UserDao;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.service.UserSecurityService;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class UserSecurityServiceTest {
    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserDao userDao;

    private UserDto user1;

    private ProfileDto profileDto;

    @Before
    public void setup() {
        profileDto = new ProfileDto();
        profileDto.setUser_id("user1");

        user1 = new UserDto();
        user1.setId("user1");
        user1.setPw("pass1");
        user1.setEmail("user1@naver.com");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
    }

    @Test
    public void createUserTest() {
        userDao.createUserProfile("user1");
        userSecurityService.deleteUser(user1.getUsername());
        userSecurityService.createUser(user1);
        UserDto user = userSecurityService.readUser(user1.getUsername());
        assertThat(user.getUsername(), is(user1.getUsername()));

        PasswordEncoder passwordEncoder = userSecurityService.passwordEncoder();
        assertThat(passwordEncoder.matches("pass1", user.getPassword()), is(true));

        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
        while (it.hasNext()) {
            GrantedAuthority authority = it.next();
            assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
        }
    }
}
