package mwohae.post.service;

import mwohae.post.dto.PostDto;
import mwohae.post.dto.PostLikeDto;
import mwohae.post.request.CreatePostLikeRequest;
import mwohae.post.request.CreatePostRequest;

import java.util.List;

public interface PostService {
    public List<PostDto> retrieveAllPost();
    public List<PostDto> retrieveFollowingPost(String token, String user_id);
    public void createPost(CreatePostRequest request);
    public void createPostLike(CreatePostLikeRequest request);
    public List<PostLikeDto> retrievePostLikes(String userId);
    public List<PostDto> retrieveLikePostList(String userId);
    public void deletePostLike(CreatePostLikeRequest request);
}
