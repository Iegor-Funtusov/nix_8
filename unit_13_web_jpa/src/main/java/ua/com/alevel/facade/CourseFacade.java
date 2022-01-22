package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.cource.CourseRequestDto;
import ua.com.alevel.dto.cource.CourseResponseDto;

public interface CourseFacade extends BaseFacade<CourseRequestDto, CourseResponseDto> {

    PageData<CourseResponseDto> findAllByStudentId(Integer studentId, WebRequest webRequest);
}
