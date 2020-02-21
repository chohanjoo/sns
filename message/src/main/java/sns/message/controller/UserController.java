package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateUserRequest;
import sns.message.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@Api(value = "user", description = "user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "전체 User 가져오기")
    @GetMapping("/user")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> retrieveAllUser(){
        return this.userService.retrieveAllUser();
    }

    @ApiOperation(value = "User 생")
    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.OK)
    public void createUser(CreateUserRequest request){
        userService.createUser(request);
    }

    @ApiOperation(value = "UserProfile 가져오기")
    @GetMapping("/user/profile")
    @ResponseStatus(value = HttpStatus.OK)
    public ProfileDto retrieveUserProfile(String user_id){
        return this.userService.retrieveUserProfile(user_id);
    }
}
