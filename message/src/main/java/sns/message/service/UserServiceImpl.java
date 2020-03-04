package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sns.message.dao.UserDao;
import sns.message.dto.FriendDto;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateFriendRequest;
import sns.message.request.CreateUserRequest;
import sns.message.request.DeleteFriendRequest;
import sns.message.response.ListResult;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> retrieveAllUser() {
        return this.userDao.retrieveAllUser();
    }

    @Override
    public UserDto retrieveUserById(String user_id) {
        return this.userDao.retrieveUserById(user_id);
    }

    @Override
    public void createUser(CreateUserRequest request) {
        UserDto userDto = UserDto.create(request);

        userDto.setPw(passwordEncoder.encode(userDto.getPassword()));
        userDto.setAuthorities(AuthorityUtils.createAuthorityList("USER"));

        userDao.createUserProfile(request.getId());
        userDao.createUser(userDto);
        userDao.createAuthority(userDto);
    }

    @Override
    public void createAdmin(CreateUserRequest request) {
        UserDto userDto = UserDto.create(request);

        userDto.setPw(passwordEncoder.encode(userDto.getPassword()));
        userDto.setAuthorities(AuthorityUtils.createAuthorityList("ADMIN"));

        userDao.createUserProfile(request.getId());
        userDao.createUser(userDto);
        userDao.createAuthority(userDto);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String user_id) {
        List<GrantedAuthority> authorities = userDao.retrieveAuthority(user_id);

        return authorities;
    }

    @Override
    public ProfileDto retrieveUserProfile(String user_id) {
        return this.userDao.retrieveUserProfile(user_id);
    }


    @Override
    public List<FriendDto> retrieveUserFriends(String user_id) {
        return this.userDao.retrieveUserFriends(user_id);
    }

    @Override
    public List<UserDto> retrieveRecommendFriends(String user_id) {
        return this.userDao.retrieveRecommendFriends(user_id);
    }

    @Override
    public void createUserFriend(CreateFriendRequest request) {
        FriendDto friendDto = FriendDto.create(request);

        userDao.createUserFriend(friendDto);
    }

    @Override
    public void deleteUserFriend(DeleteFriendRequest request) {
        userDao.deleteUserFriend(request);
    }
}
