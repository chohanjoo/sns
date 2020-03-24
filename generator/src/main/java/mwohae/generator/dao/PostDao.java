package mwohae.generator.dao;

import mwohae.generator.dto.PostDto;

@PostRepository
public interface PostDao {
    public void createPost(PostDto postDto);
}
