package mwohae.generator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mwohae.generator.config.Generator;
import mwohae.generator.service.PostService;
import mwohae.generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4-1. Generator API"})
@CrossOrigin
@RestController
@RequestMapping("generator/api")
public class APIController {

    @Autowired
    private UserService userService;

    @Autowired
    private Generator generator;

    @Autowired
    private PostService postService;

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 User 생성")
    @GetMapping(value = "/user")
    public void createUsers() {
        userService.createRandomUser();
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 User Friend 생성")
    @GetMapping(value = "/friend")
    public void createFriends() {
        userService.createRandomFriend();
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 Post 생성")
    @GetMapping(value = "/post")
    public void createPosts() {
        postService.createRandomPost();
    }
}
