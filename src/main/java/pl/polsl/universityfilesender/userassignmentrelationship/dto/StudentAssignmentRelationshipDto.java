package pl.polsl.universityfilesender.userassignmentrelationship.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.file.dto.FileDetailsDto;

import java.util.List;

@Getter
@Setter
public class StudentAssignmentRelationshipDto {

    private Long id;

    private Long studentId;

    private String studentFirstName;

    private String studentLastName;

    private List<FileDetailsDto> files;

    private String status;


}
