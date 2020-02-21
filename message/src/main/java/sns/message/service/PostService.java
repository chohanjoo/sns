package sns.message.service;

import sns.message.dto.PostDto;
import sns.message.request.CreatePostRequest;

import java.util.List;

public interface PostService {
    public List<PostDto> retrieveAllPost();
    public void createPost(CreatePostRequest request);
}
