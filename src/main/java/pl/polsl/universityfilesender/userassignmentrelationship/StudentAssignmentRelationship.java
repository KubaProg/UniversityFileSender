package pl.polsl.universityfilesender.userassignmentrelationship;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.submission.Submission;
import pl.polsl.universityfilesender.submissionfile.SubmissionFile;
import pl.polsl.universityfilesender.user.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_assignment_relationship")
@Getter
@Setter
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

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "submission_id")
//    private Submission submission;

    @OneToMany(mappedBy = "studentAssignmentRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubmissionFile> files = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Status status;



    public enum Status {
        PENDING, ACCEPTED
    }


}
