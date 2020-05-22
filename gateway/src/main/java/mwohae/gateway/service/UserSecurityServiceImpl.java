package mwohae.gateway.service;

import mwohae.gateway.dao.UserDao;
import mwohae.gateway.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserDto user = userDao.retrieveUserById(user_id);
        user.setAuthorities(getAuthorities(user_id));

        return user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String user_id) {
        List<GrantedAuthority> authorities = userDao.retrieveAuthority(user_id);

        return authorities;
    }
}
