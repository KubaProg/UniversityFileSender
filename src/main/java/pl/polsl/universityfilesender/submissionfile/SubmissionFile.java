package pl.polsl.universityfilesender.submissionfile;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.submission.Submission;

@Entity
@Getter
@Setter
public class SubmissionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", referencedColumnName = "id", nullable = false)
    private Submission submission;

    @Column(name = "file_name", nullable = false)
    @NotBlank
    private String fileName;
}
