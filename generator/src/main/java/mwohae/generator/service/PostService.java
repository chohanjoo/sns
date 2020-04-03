package mwohae.generator.service;

import mwohae.generator.request.CreatePostRequest;


public interface PostService {
    public void createPost(CreatePostRequest request);
    public void createRandomPost();
}
