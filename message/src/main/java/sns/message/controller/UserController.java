package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sns.message.dto.FriendDto;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateFriendRequest;
import sns.message.request.DeleteFriendRequest;
import sns.message.response.ListResult;
import sns.message.service.ResponseService;
import sns.message.service.UserService;

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

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다")
//    @GetMapping(value = "/user")
//    public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
//        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String id = authentication.getName();
//        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
//        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
//    @PutMapping(value = "/user")
//    public SingleResult<User> modify(
//            @ApiParam(value = "회원번호", required = true) @RequestParam int msrl,
//            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
//        User user = User.builder()
//                .msrl(msrl)
//                .name(name)
//                .build();
//        return responseService.getSingleResult(userJpaRepo.save(user));
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
//    @DeleteMapping(value = "/user/{msrl}")
//    public CommonResult delete(
//            @ApiParam(value = "회원번호", required = true) @PathVariable int msrl) {
//        userJpaRepo.deleteById(msrl);
//        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
//        return responseService.getSuccessResult();
//    }
}