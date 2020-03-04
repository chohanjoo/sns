package sns.message.config;

import org.kohsuke.randname.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sns.message.dto.UserDto;
import sns.message.request.CreateFriendRequest;
import sns.message.request.CreateUserRequest;
import sns.message.service.UserService;

import java.util.List;
import java.util.Random;

@Component
public class GeneratorUser {

    @Autowired
    UserService userService;

    RandomNameGenerator generator = new RandomNameGenerator(0);

    public void createUser(){
        CreateUserRequest request = new CreateUserRequest();

        for (int i = 0; i < 1000; ++i) {
            String word = generator.next();
            request.setName(word);
            request.setPw(word);
            request.setId(word);
            request.setEmail(word + "@naver.com");

            userService.createUser(request);
        }
    }

    public void createFriend(){
        List<UserDto> userList = userService.retrieveAllUser();
        Random random = new Random();

        CreateFriendRequest request = new CreateFriendRequest();

        for(int i=0;i<2000;++i){
            int user_index = random.nextInt(userList.size());
            int friend_index = random.nextInt(userList.size());

            String user_id = userList.get(user_index).getId();
            String friend_id = userList.get(friend_index).getId();

            request.setUser_id(user_id);
            request.setFriend_id(friend_id);

            if(user_id != friend_id){
                userService.createUserFriend(request);
            }
        }


    }

}
