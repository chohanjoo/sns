package sns.message.dto;

import lombok.Getter;
import lombok.Setter;
import sns.message.request.CreateUserRequest;

@Getter
@Setter
public class UserDto {
    private String id;
    private String pw;
    private String email;
    private String name;

    public static UserDto create(CreateUserRequest request){
        UserDto userDto = new UserDto();

        userDto.id = request.getId();
        userDto.pw = request.getPw();
        userDto.email = request.getEmail();
        userDto.name = request.getName();

        return userDto;
    }
}
