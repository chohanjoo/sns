package mwohae.generator.dto;

import lombok.Getter;
import lombok.Setter;
import mwohae.generator.request.CreatePostRequest;

@Getter
@Setter
public class PostDto {
    private int id;
    private String writer;
    private String title;
    private String contents;
    private int love;
    private String create_date;
    private String update_date;

    public static PostDto create(CreatePostRequest request){
        PostDto postDto = new PostDto();

        postDto.writer = request.getWriter();
        postDto.title = request.getTitle();
        postDto.contents = request.getContents();
        postDto.love = request.getLove();

        return postDto;
    }
}
