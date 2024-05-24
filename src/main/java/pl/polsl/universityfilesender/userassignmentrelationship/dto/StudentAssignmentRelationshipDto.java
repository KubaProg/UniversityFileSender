package pl.polsl.universityfilesender.userassignmentrelationship.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.file.dto.FileDto;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StudentAssignmentRelationshipDto {

    private Long id;

    private Long studentId;

    private String studentFirstName;

    private String studentLastName;

    private List<FileDto> files;

    private String status;


}
