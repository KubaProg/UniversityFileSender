package pl.polsl.universityfilesender.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.universityfilesender.user.dto.UserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUser(user));
    }


}
