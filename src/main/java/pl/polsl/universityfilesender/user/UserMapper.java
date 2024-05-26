package pl.polsl.universityfilesender.user;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().toString());
        return userDto;
    }

    public List<UserDto> toUserDto(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }



}
