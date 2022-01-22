package ua.com.alevel.dto.cource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.collections4.CollectionUtils;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Course;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponseDto extends ResponseDto {

    private String name;
    private int countOfStudents;

    public CourseResponseDto(Course course) {
        super(course.getId());
        if (course.getName() != null) {
            this.name = course.getName().name();
        }
        if (CollectionUtils.isNotEmpty(course.getStudents())) {
            this.countOfStudents = course.getStudents().size();
        }
    }
}
