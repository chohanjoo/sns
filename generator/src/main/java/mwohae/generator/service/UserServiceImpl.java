package mwohae.generator.service;

import mwohae.generator.dao.UserDao;
import mwohae.generator.dto.FriendDto;
import mwohae.generator.dto.UserDto;
import mwohae.generator.request.CreateFriendRequest;
import mwohae.generator.request.CreateUserRequest;
import org.kohsuke.randname.RandomNameGenerator;
import org.markov.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    RandomNameGenerator generator = new RandomNameGenerator();

    Manager manager = new Manager();

    @Override
    public List<UserDto> retrieveAllUser() {
        return this.userDao.retrieveAllUser();
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
    public void createUserFriend(CreateFriendRequest request) {
        FriendDto friendDto = userDao.retrieveFriend(request.getUser_id(),request.getFriend_id());

        if(friendDto == null){
            friendDto = FriendDto.create(request);
            userDao.createUserFriend(friendDto);
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String user_id) {
        List<GrantedAuthority> authorities = userDao.retrieveAuthority(user_id);

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserDto user = userDao.retrieveUserById(user_id);
        user.setAuthorities(getAuthorities(user_id));

        return user;
    }

    @Override
    public void createRandomUser() {
        CreateUserRequest request = new CreateUserRequest();
        String word = generator.next();
        request.setName(word);
        request.setPw(word);
        request.setId(word);
        request.setEmail(word + "@naver.com");

        this.createUser(request);
    }

    @Override
    public void createRandomFriend() {
        List<UserDto> userList = this.retrieveAllUser();
        Random random = new Random();

        CreateFriendRequest request = new CreateFriendRequest();
        int user_index = random.nextInt(userList.size());
        int friend_index = random.nextInt(userList.size());

        String user_id = userList.get(user_index).getId();
        String friend_id = userList.get(friend_index).getId();

        request.setUser_id(user_id);
        request.setFriend_id(friend_id);

        if(user_id != friend_id){
            this.createUserFriend(request);
        }
    }
}
