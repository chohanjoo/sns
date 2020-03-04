package sns.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.randname.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sns.message.dto.UserDto;
import sns.message.request.CreateUserRequest;
import sns.message.service.UserService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class GeneratorUserTest {
    RandomNameGenerator generator = new RandomNameGenerator(0);

    @Autowired
    UserService userService;

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
}
