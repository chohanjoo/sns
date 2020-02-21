package sns.message.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDto {
    private String user_id;
    private String school;
    private String company;

    public static ProfileDto create(String user_id){
        ProfileDto profileDto = new ProfileDto();

        profileDto.user_id = user_id;

        return profileDto;
    }
}
