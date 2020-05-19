package mwohae.post.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mwohae.post.dto.PostDto;
import mwohae.post.dto.PostLikeDto;
import mwohae.post.request.CreatePostLikeRequest;
import mwohae.post.request.CreatePostRequest;
import mwohae.post.request.UserIdRequest;
import mwohae.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"3. Post"})
@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService postService;

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "전체 post 가져오기")
    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostDto> retrieveAllPost(){
        return this.postService.retrieveAllPost();
    }

//    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "post 가져오기")
    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostDto> retrieveFollowingPost(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody UserIdRequest request){
        return this.postService.retrieveFollowingPost(token, request.getUser_id());
    }


    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "post 생성")
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.OK)
    public void createPost(CreatePostRequest request){
        postService.createPost(request);
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "post like 생성")
    @PostMapping("/like")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createPostLike(@RequestBody CreatePostLikeRequest request){
        postService.createPostLike(request);
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "post like 조회")
    @GetMapping("/like")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostLikeDto> retrievePostLike(@RequestParam String userId){
        return this.postService.retrievePostLikes(userId);
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "post like 삭제")
    @DeleteMapping("/like")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePostLike(@RequestBody CreatePostLikeRequest request){
        postService.deletePostLike(request);
    }
}
