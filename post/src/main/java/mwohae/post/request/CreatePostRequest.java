package mwohae.post.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {
    private String writer;
    private String title;
    private String contents;
    private int love;
}
