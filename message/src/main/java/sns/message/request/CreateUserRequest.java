package sns.message.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String id;
    private String pw;
    private String email;
    private String name;
}
