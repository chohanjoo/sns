package mwohae.post.dao;

import mwohae.post.dto.PostDto;
import mwohae.post.dto.PostLikeDto;
import mwohae.post.request.CreatePostLikeRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@PostRepository
public interface PostDao {
    public List<PostDto> retrieveAllPost();
    public List<PostDto> retrieveLimitPostById(@Param("id") String id, @Param("date") String date);
    public void createPost(PostDto postDto);
    public void createPostLike(PostLikeDto postLikeDto);
    public List<PostLikeDto> retrievePostLikes(@Param("userId") String userId);
    public List<PostDto> retrieveLikePostList(@Param("userId") String userId);
    public void deletePostLike(CreatePostLikeRequest request);
}
