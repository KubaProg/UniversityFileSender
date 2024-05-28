package pl.polsl.universityfilesender.file;


import lombok.Getter;
import lombok.Setter;
import org.springframework.expression.spel.ast.Assign;
import pl.polsl.universityfilesender.assignment.Assignment;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_assignment_relationship_id")
    private StudentAssignmentRelationship studentAssignmentRelationship;

    @ManyToOne(fetch = FetchType.LAZY)
    private Assignment assignment;

    @Column(name = "file_name", nullable = false)
    @NotBlank
    private String fileName;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;
}
