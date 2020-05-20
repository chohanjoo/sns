package mwohae.post.dto;

import lombok.Getter;
import lombok.Setter;
import mwohae.post.request.CreatePostLikeRequest;

@Getter
@Setter
public class PostLikeDto {
    private String user_id;
    private int post_id;
    private Boolean is_love;

    public static PostLikeDto create(CreatePostLikeRequest request){
        PostLikeDto postLikeDto = new PostLikeDto();

        postLikeDto.user_id = request.getUser_id();
        postLikeDto.post_id = request.getPost_id();
        postLikeDto.is_love = request.getIs_love();

        return postLikeDto;
    }
}
