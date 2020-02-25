package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import sns.message.domain.AuthenticationToken;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.AuthenticationRequest;
import sns.message.request.CreateUserRequest;
import sns.message.service.UserSecurityService;
import sns.message.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@Api(value = "user", description = "user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserSecurityService userSecurityService;

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

    @RequestMapping(value="/user/login", method=RequestMethod.POST)
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session
    ) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        UserDto user = userSecurityService.readUser(username);
        return new AuthenticationToken(user.getId(), user.getAuthorities(), session.getId());
    }
}
