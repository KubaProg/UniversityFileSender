package pl.polsl.universityfilesender.file;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.file.dto.FileDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileMapper {


    public FileDto toDto(File file) {
        if (file == null) {
            return null;
        } else {
            FileDto fileDto = new FileDto();
            fileDto.setId(file.getId());
            fileDto.setFileName(file.getFileName());
            return fileDto;
        }
    }

    public List<FileDto> toDto(Set<File> files) {
        if (files == null) {
            return null;
        } else {
            return files.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }
    }


}
