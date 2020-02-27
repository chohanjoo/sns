package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;

import java.util.ArrayList;
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
    public void createUser(UserDto user) {
        String rawPassword = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);

        user.setPw(encodedPassword);
        userDao.createUser(user);
        userDao.createAuthority(user);
    }

    @Override
    public void deleteUser(String user_id) {
        userDao.deleteUser(user_id);
        userDao.deleteAuthority(user_id);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
