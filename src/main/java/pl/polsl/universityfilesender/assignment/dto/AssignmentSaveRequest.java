package pl.polsl.universityfilesender.assignment.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AssignmentSaveRequest {

    @NotBlank(message = "Assignment name is required")
    @Size(max = 255, message = "Assignment name is too long")
    private String assignmentName;

    @NotBlank(message = "Description is required")
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Deadline date is required")
    private LocalDateTime deadlineDate;


    private List<MultipartFile> files;
}
