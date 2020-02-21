package sns.message.dao;

import sns.message.dto.UserDto;

import java.util.List;

@UserRepository
public interface UserDao {
    public List<UserDto> retrieveAllUser();
}
