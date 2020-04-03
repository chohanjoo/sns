package mwohae.generator.service;

import mwohae.generator.dao.PostDao;
import mwohae.generator.dto.PostDto;
import mwohae.generator.dto.UserDto;
import mwohae.generator.request.CreatePostRequest;
import org.markov.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    UserService userService;

    Manager manager = new Manager();

    @Override
    public void createPost(CreatePostRequest request) {
        PostDto postDto = PostDto.create(request);

        postDao.createPost(postDto);
    }

    @Override
    public void createRandomPost() {
        List<UserDto> userList = userService.retrieveAllUser();
        Random random = new Random();

        CreatePostRequest request = new CreatePostRequest();
        int index = random.nextInt(userList.size());

        String user_id = userList.get(index).getId();

        manager.generateSentence();
        String context = manager.getSentence();

        request.setWriter(user_id);
        request.setLove(0);
        request.setContents(context);
        request.setTitle(context.substring(0,10) + "...");

        this.createPost(request);
    }
}
