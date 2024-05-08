package pl.polsl.universityfilesender.submission;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.submissionfile.SubmissionFile;
import pl.polsl.universityfilesender.userassignmentrelationship.UserAssignmentRelationship;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submission_date", nullable = false)
    @NotNull
    private Date submissionDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_assignment_relationship_id", nullable = false)
    private UserAssignmentRelationship userAssignmentRelationship;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private Set<SubmissionFile> files = new HashSet<>();

}
