package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sns.message.dao.UserDao;
import sns.message.dto.UserDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDto> retrieveAllUser() {
        return this.userDao.retrieveAllUser();
    }
}
