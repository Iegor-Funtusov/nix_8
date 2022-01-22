package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto> {

    PageData<StudentResponseDto> findAllByCourseId(Integer courseId, WebRequest webRequest);
}
