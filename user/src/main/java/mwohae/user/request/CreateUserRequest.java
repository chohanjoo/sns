package mwohae.user.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @ApiParam(value = "아이디", required = true)
    private String id;
    @ApiParam(value = "비밀번호", required = true)
    private String pw;
    @ApiParam(value = "이메일")
    private String email;
    @ApiParam(value = "이름", required = true)
    private String name;
}
