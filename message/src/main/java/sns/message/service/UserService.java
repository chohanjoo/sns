package sns.message.service;

import org.springframework.security.core.GrantedAuthority;
import sns.message.dto.FriendDto;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateFriendRequest;
import sns.message.request.CreateUserRequest;
import sns.message.request.DeleteFriendRequest;
import sns.message.response.ListResult;

import java.util.Collection;
import java.util.List;

public interface UserService {
    public List<UserDto> retrieveAllUser();
    public List<UserDto> retrieveRecommendFriends(String user_id);
    public void createUser(CreateUserRequest request);
    public void createAdmin(CreateUserRequest request);
    public UserDto retrieveUserById(String user_id);

    public Collection<GrantedAuthority> getAuthorities(String user_id);

    public ProfileDto retrieveUserProfile(String user_id);

    public List<FriendDto> retrieveUserFriends(String user_id);
    public void createUserFriend(CreateFriendRequest request);
    public void deleteUserFriend(DeleteFriendRequest request);
}
