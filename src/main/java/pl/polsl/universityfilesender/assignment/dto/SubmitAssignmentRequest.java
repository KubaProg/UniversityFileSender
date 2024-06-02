package pl.polsl.universityfilesender.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class SubmitAssignmentRequest {

    List<MultipartFile> files;
}
