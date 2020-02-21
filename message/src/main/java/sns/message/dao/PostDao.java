package sns.message.dao;

import sns.message.dto.PostDto;

import java.util.List;

@PostRepository
public interface PostDao {
    public List<PostDto> retrieveAllPost();
    public void createPost(PostDto postDto);
}
