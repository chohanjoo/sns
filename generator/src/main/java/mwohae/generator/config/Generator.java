package mwohae.generator.config;

import mwohae.generator.dto.UserDto;
import mwohae.generator.request.CreateFriendRequest;
import mwohae.generator.request.CreatePostRequest;
import mwohae.generator.request.CreateUserRequest;
import mwohae.generator.service.PostService;
import mwohae.generator.service.UserService;
import org.kohsuke.randname.RandomNameGenerator;
import org.markov.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Generator {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    RandomNameGenerator generator = new RandomNameGenerator();

//    Manager manager = new Manager("res/text.txt");
    Manager manager = new Manager();

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

    public void createPosts(){
        List<UserDto> userList = userService.retrieveAllUser();
        Random random = new Random();

        CreatePostRequest request = new CreatePostRequest();

        for(int i=0;i<2000;++i){
            int index = random.nextInt(userList.size());

            String user_id = userList.get(index).getId();

            manager.generateSentence();
            String context = manager.getSentence();

            request.setWriter(user_id);
            request.setLove(0);
            request.setContents(context);
            request.setTitle(context.substring(0,10) + "...");

            postService.createPost(request);
        }
    }

}
