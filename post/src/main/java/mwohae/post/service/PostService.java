package mwohae.post.service;

import mwohae.post.dto.PostDto;
import mwohae.post.request.CreatePostRequest;

import java.util.List;

public interface PostService {
    public List<PostDto> retrieveAllPost();
    public List<PostDto> retrieveFollowingPost(String token, String user_id);
    public void createPost(CreatePostRequest request);
}
