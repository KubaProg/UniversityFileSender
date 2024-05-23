package pl.polsl.universityfilesender.submission;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.submissionfile.SubmissionFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;



public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submission_date", nullable = false)
    @NotNull
    private Date submissionDate;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private Set<SubmissionFile> files = new HashSet<>();

}
