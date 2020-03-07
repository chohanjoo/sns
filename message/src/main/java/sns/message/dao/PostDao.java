package sns.message.dao;

import org.apache.ibatis.annotations.Param;
import sns.message.dto.PostDto;

import java.util.List;

@PostRepository
public interface PostDao {
    public List<PostDto> retrieveAllPost();
    public List<PostDto> retrieveLimitPostById(@Param("id") String id,@Param("date") String date);
    public void createPost(PostDto postDto);
}
