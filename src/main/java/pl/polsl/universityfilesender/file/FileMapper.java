package pl.polsl.universityfilesender.file;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.file.dto.FileDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {


    public FileDetailsDto toDto(File file) {
        if (file == null) {
            return null;
        }


        FileDetailsDto fileDetailsDto = new FileDetailsDto();
        fileDetailsDto.setId(file.getId());
        fileDetailsDto.setFileName(file.getFileName());
        fileDetailsDto.setFileType(file.getFileType());
        return fileDetailsDto;
    }


    public List<FileDetailsDto> toDtoList(List<File> files) {
        if (files == null) {
            return null;
        }

        return files.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
