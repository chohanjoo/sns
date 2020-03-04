package sns.message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.randname.RandomNameGenerator;
import org.markov.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sns.message.dao.UserDao;
import sns.message.dto.PostDto;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreatePostRequest;
import sns.message.request.CreateUserRequest;
import sns.message.service.PostService;
import sns.message.service.UserService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class GeneratorTest {
    RandomNameGenerator generator = new RandomNameGenerator(0);

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    private UserDao userDao;

    private UserDto user1;

    private ProfileDto profileDto;

    private Manager manager;

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
    public void createTenUser(){
        for(int i=0;i<10;++i){
            System.out.println(generator.next());
        }
    }

    @Test
    @Transactional
    public void createUser() {
        CreateUserRequest request = new CreateUserRequest();

        for (int i = 0; i < 10; ++i) {
            String word = generator.next();
            request.setName(word);
            request.setPw(word);
            request.setId(word);
            request.setEmail(word + "@naver.com");

            userService.createUser(request);
        }

        List<UserDto> list = userService.retrieveAllUser();

        for(UserDto user : list){
            System.out.println(user.getUsername());
        }
    }

    @Test
    @Transactional
    public void createPost(){
        userDao.createUserProfile("user1");
        userDao.createUser(user1);

        manager = new Manager("res/text.txt");
        manager.generateSentence();
        CreatePostRequest request = new CreatePostRequest();
        request.setWriter(user1.getId());
        request.setLove(0);
        request.setTitle("");
        request.setContents(manager.getSentence());

        postService.createPost(request);
    }
}
