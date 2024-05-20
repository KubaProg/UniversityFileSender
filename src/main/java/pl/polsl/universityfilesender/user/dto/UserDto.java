package pl.polsl.universityfilesender.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    public UserDto() {
    }

}
