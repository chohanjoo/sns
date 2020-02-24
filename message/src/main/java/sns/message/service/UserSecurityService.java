package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;

@Service
public class UserSecurityService  implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userDao.retrieveUserByUserId(username);

        if(null == user) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return user;
    }

}
