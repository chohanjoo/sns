package mwohae.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mwohae.user.dto.FriendDto;
import mwohae.user.dto.ProfileDto;
import mwohae.user.dto.UserDto;
import mwohae.user.request.CreateFriendRequest;
import mwohae.user.request.DeleteFriendRequest;
import mwohae.user.response.ListResult;
import mwohae.user.service.ResponseService;
import mwohae.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/all")
    public ListResult<UserDto> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userService.retrieveAllUser());
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 리스트 조회", notes = "친추 추천 회원을 조회한다")
    @GetMapping(value = "/friend/recommend")
    public ListResult<FriendDto> retrieveRecommendFriends(@RequestParam String user_id) {
        return responseService.getListResult(userService.retrieveRecommendFriends(user_id));
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 프로필 가져오기")
    @GetMapping("/profile")
    @ResponseStatus(value = HttpStatus.OK)
    public ProfileDto retrieveUserProfile(String user_id){
        return this.userService.retrieveUserProfile(user_id);
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 친구 라스트 가져오기")
    @GetMapping("/friend")
    @ResponseStatus(value = HttpStatus.OK)
    public ListResult<FriendDto> retrieveUserFriends(@RequestParam String user_id){
        return responseService.getListResult(userService.retrieveUserFriends(user_id));
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 친구 추가하기")
    @PostMapping("/friend")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUserFriend(@RequestBody CreateFriendRequest request){
        userService.createUserFriend(request);
    }

    @ApiImplicitParams({ @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 친구 삭제하기")
    @DeleteMapping("/friend")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUserFriend(@RequestBody DeleteFriendRequest request){
        userService.deleteUserFriend(request);
    }
}
