package pl.polsl.universityfilesender.userassignmentrelationship;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.user.User;

@Entity
@Table(name = "user_assignment_relationship")
@Getter
@Setter
public class UserAssignmentRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING, ACCEPTED
    }
}
