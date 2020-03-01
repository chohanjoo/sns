package sns.message.dao;

import org.springframework.security.core.GrantedAuthority;
import sns.message.dto.FriendDto;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.DeleteFriendRequest;
import sns.message.response.ListResult;

import java.util.List;
import java.util.Optional;

@UserRepository
public interface UserDao {
    public List<UserDto> retrieveAllUser();
    public UserDto retrieveUserById(String user_id);
    public void createUser(UserDto userDto);
    public void createAuthority(UserDto user);
    public void deleteUser(String user_id);
    public void deleteAuthority(String user_id);

    public ProfileDto retrieveUserProfile(String user_id);
    public void createUserProfile(String user_id);

    public List<GrantedAuthority> retrieveAuthority(String user_id);

    public List<FriendDto> retrieveUserFriends(String user_id);
    public FriendDto retrieveFriend(String user_id, String friend_id);
    public void createUserFriend(FriendDto friendDto);
    public void deleteUserFriend(DeleteFriendRequest request);
}
