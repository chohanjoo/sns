package mwohae.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
