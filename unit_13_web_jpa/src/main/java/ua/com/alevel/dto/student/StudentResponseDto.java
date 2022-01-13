package ua.com.alevel.dto.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Student;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto extends ResponseDto {

    private String fullName;
    private String email;
    private int countOfCourses;

    public StudentResponseDto(Student student) {
        super(student.getId());
        this.fullName = student.getFirstName() + " " + student.getLastName();
        this.email = student.getEmail();
        if (CollectionUtils.isNotEmpty(student.getCourses())) {
            this.countOfCourses = student.getCourses().size();
        }
    }
}
