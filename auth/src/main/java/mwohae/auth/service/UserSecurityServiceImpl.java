package mwohae.auth.service;

import mwohae.auth.dao.UserDao;
import mwohae.auth.dto.UserDto;
import mwohae.auth.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    UserDao userDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    public UserDto readUser(String user_id) {
        UserDto user = userDao.retrieveUserById(user_id);
        user.setAuthorities(userDao.retrieveAuthority(user_id));

        return user;
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
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
}
