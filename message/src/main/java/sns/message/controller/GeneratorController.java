package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sns.message.config.Generator;
import sns.message.service.UserService;

@Api(tags = {"4. Generator"})
@CrossOrigin
@RestController
@RequestMapping("generator")
public class GeneratorController {
    @Autowired
    private UserService userService;

    @Autowired
    private Generator generator;

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 User 생성")
    @GetMapping(value = "/users")
    public void createUsers() {
        generator.createUser();
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 User Friend 생성")
    @GetMapping(value = "/friends")
    public void createFriends() {
        generator.createFriend();
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "랜덤 Post 생성")
    @GetMapping(value = "/posts")
    public void createPosts() {
        generator.createPosts();
    }
}
