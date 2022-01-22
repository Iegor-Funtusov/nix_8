package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.cource.CourseRequestDto;
import ua.com.alevel.dto.cource.CourseResponseDto;
import ua.com.alevel.entity.Course;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Course> dataTableResponse = courseService.findAll(dataTableRequest);
        List<CourseResponseDto> dtos = dataTableResponse.getEntities().
                stream().
                map(CourseResponseDto::new).toList();
        PageData<CourseResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }

    @Override
    public PageData<CourseResponseDto> findAllByStudentId(Integer studentId, WebRequest webRequest) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(webRequest);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("studentId", studentId);
        dataTableRequest.setQueryMap(queryMap);
        DataTableResponse<Course> dataTableResponse = courseService.findAll(dataTableRequest);
        List<CourseResponseDto> dtos = dataTableResponse.
                getEntities().
                stream().
                map(CourseResponseDto::new).
                toList();
        PageData<CourseResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }
}
