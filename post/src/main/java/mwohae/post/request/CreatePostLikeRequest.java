package mwohae.post.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostLikeRequest {
    private String user_id;
    private int post_id;
    private Boolean is_love;
}
