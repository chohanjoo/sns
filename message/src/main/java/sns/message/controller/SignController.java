package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sns.message.config.JwtTokenProvider;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;
import sns.message.model.CommonResult;
import sns.message.model.SingleResult;
import sns.message.service.ResponseService;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private UserDao userDao;
    private JwtTokenProvider jwtTokenProvider;
    private ResponseService responseService;
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) throws Exception {

        UserDto userDto = userDao.findById(id);

        if(!passwordEncoder.matches(password, userDto.getPassword()))
            throw new Exception();

        return responseService.getSingleResult(jwtTokenProvider.createToken(userDto.getUsername(),userDto.getRoles()));
    }

    @ApiOperation(value="가입", notes = "회원가입을 한다.")
    @GetMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "비밀번", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name) {

        return responseService.getSuccessResult();
    }

}
