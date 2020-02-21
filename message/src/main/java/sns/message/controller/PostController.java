package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sns.message.dto.PostDto;
import sns.message.request.CreatePostRequest;
import sns.message.service.PostService;

import java.util.List;

@CrossOrigin
@RestController
@Api(value = "post", description = "post")
@RequestMapping("post")
public class PostController {

    @Autowired
    PostService postService;

    @ApiOperation(value = "전체 post 가져오기")
    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostDto> retrieveAllPost(){
        return this.postService.retrieveAllPost();
    }

    @ApiOperation(value = "post 생성")
    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public void createPost(CreatePostRequest request){
        postService.createPost(request);
    }
}
