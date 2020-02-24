package sns.message.dao;

import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;

import java.util.List;

@UserRepository
public interface UserDao {
    public List<UserDto> retrieveAllUser();
    public UserDto retrieveUserByUserId(String user_id);
    public void createUser(UserDto userDto);

    public ProfileDto retrieveUserProfile(String user_id);
    public void createUserProfile(String user_id);
}
