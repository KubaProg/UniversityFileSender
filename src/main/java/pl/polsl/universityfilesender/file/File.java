package pl.polsl.universityfilesender.file;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_assignment_relationship_id", nullable = false)
    private StudentAssignmentRelationship studentAssignmentRelationship;

    @Column(name = "file_name", nullable = false)
    @NotBlank
    private String fileName;
}
