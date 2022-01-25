package ua.com.alevel.dto.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.dto.RequestDto;

@Getter
@Setter
@ToString
public class StudentRequestDto extends RequestDto {

    private Integer courseId;
    private String firstName;
    private String lastName;
    private String email;
}
