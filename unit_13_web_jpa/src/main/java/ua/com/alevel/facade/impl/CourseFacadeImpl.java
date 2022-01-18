package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.cource.CourseRequestDto;
import ua.com.alevel.dto.cource.CourseResponseDto;
import ua.com.alevel.entity.Course;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;

    public CourseFacadeImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void create(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setName(courseRequestDto.getName());
        courseService.create(course);
    }

    @Override
    public void update(CourseRequestDto courseRequestDto, Integer id) {
        Course course = courseService.findById(id);
        course.setName(courseRequestDto.getName());
        courseService.update(course);
    }

    @Override
    public void delete(Integer id) {
        courseService.delete(id);
    }

    @Override
    public CourseResponseDto findById(Integer id) {
        return new CourseResponseDto(courseService.findById(id));
    }

    @Override
    public List<CourseResponseDto> findAll() {
        List<Course> courses = courseService.findAll();
        return courses.
                stream().
                map(CourseResponseDto::new).
                collect(Collectors.toList());
    }

    @Override
    public PageData<CourseResponseDto> findAll(WebRequest request) {
        return null;
    }
}
