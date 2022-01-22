package ua.com.alevel.dto.cource;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.entity.CourseName;

@Getter
@Setter
public class CourseRequestDto extends RequestDto {

    private CourseName name;
}
