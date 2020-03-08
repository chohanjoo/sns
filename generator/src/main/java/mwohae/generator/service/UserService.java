package mwohae.generator.service;

import mwohae.generator.dto.UserDto;
import mwohae.generator.request.CreateFriendRequest;
import mwohae.generator.request.CreateUserRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserDto> retrieveAllUser();
    public void createUser(CreateUserRequest request);
    public void createUserFriend(CreateFriendRequest request);
    public Collection<GrantedAuthority> getAuthorities(String user_id);
}
