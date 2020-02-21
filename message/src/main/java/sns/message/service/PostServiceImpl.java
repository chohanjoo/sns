package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sns.message.dao.PostDao;
import sns.message.dao.UserDao;
import sns.message.dto.PostDto;
import sns.message.request.CreatePostRequest;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public List<PostDto> retrieveAllPost() {
        return this.postDao.retrieveAllPost();
    }

    @Override
    public void createPost(CreatePostRequest request) {
        PostDto postDto = PostDto.create(request);

        postDao.createPost(postDto);
    }
}
