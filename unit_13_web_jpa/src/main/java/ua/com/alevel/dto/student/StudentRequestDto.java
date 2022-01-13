package ua.com.alevel.dto.student;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.dto.RequestDto;

@Getter
@Setter
public class StudentRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private String email;
}
