package sns.message.service;

import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateUserRequest;

import java.util.List;

public interface UserService {
    public List<UserDto> retrieveAllUser();
    public void createUser(CreateUserRequest request);
    public UserDto retrieveUserById(String user_id);

    public ProfileDto retrieveUserProfile(String user_id);
}
