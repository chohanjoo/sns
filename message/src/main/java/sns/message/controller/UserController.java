package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sns.message.dto.UserDto;
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
}
