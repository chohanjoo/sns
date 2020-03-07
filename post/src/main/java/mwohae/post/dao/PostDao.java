package mwohae.post.dao;

import mwohae.post.dto.PostDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@PostRepository
public interface PostDao {
    public List<PostDto> retrieveAllPost();
    public List<PostDto> retrieveLimitPostById(@Param("id") String id, @Param("date") String date);
    public void createPost(PostDto postDto);
}
