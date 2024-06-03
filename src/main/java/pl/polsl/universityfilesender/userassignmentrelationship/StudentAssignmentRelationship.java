package pl.polsl.universityfilesender.userassignmentrelationship;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.file.File;
import pl.polsl.universityfilesender.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_assignment_relationship")
@Getter
@Setter
@Builder
public class StudentAssignmentRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @OneToMany(mappedBy = "studentAssignmentRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<File> files = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentAssignmentRelationship() {

    }

    public StudentAssignmentRelationship(Long id, User student, Assignment assignment, Set<File> files, Status status) {
        this.id = id;
        this.student = student;
        this.assignment = assignment;
        this.files = files;
        this.status = status;
    }


    public enum Status {
        SUBMITTED, NOT_SUBMITTED
    }


}
