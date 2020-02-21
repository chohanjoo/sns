package sns.message.service;

import sns.message.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> retrieveAllUser();
}
