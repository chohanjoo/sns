package sns.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sns.message.dao.UserDao;
import sns.message.dto.ProfileDto;
import sns.message.dto.UserDto;
import sns.message.request.CreateUserRequest;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

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

        userDao.createUserProfile(request.getId());
        userDao.createUser(userDto);
    }

    @Override
    public ProfileDto retrieveUserProfile(String user_id) {
        return this.userDao.retrieveUserProfile(user_id);
    }
}
