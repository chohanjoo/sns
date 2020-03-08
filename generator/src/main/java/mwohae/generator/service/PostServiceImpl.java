package mwohae.generator.service;

import mwohae.generator.dao.PostDao;
import mwohae.generator.dto.PostDto;
import mwohae.generator.request.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public void createPost(CreatePostRequest request) {
        PostDto postDto = PostDto.create(request);

        postDao.createPost(postDto);
    }
}
