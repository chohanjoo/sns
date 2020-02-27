package sns.message.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @ApiParam(value = "아이디", required = true)
    private String username;
    @ApiParam(value = "비밀번호", required = true)
    private String password;
}
