package pl.polsl.universityfilesender.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.assignment.AssignmentService;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.user.dto.UserDto;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AssignmentService assignmentService;


    public UserService(UserRepository userRepository, UserMapper userMapper, AssignmentService assignmentService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.assignmentService = assignmentService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDto getUser(User user) {

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", "null");
        }

        return userMapper.toUserDto(user);
    }

    public List<UserDto> getStudentsByAssignment(Long assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        return userMapper.toUserDto(userRepository.findAllByAssignments(assignment));
    }

    public User getTeacherByAssignment(Long assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        return userRepository.findTeacherByAssignment(assignment).orElseThrow(() -> new EntityNotFoundException(User.class, "assignmentId", assignmentId.toString()));
    }

    public boolean isAssignmentOwner(Authentication authentication, Long assignmentId) {
        User currentUser = (User) authentication.getPrincipal();
        User teacherOfAssignment = getTeacherByAssignment(assignmentId);
        return teacherOfAssignment.getId().equals(currentUser.getId());
    }
}
