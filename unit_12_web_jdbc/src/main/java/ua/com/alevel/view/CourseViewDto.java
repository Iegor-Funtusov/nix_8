package ua.com.alevel.view;

import ua.com.alevel.entity.Course;

public class CourseViewDto extends Course {

    private Integer countOfStudents;

    public CourseViewDto() {
        this.countOfStudents = 0;
    }

    public Integer getCountOfStudents() {
        return countOfStudents;
    }

    public void setCountOfStudents(Integer countOfStudents) {
        this.countOfStudents = countOfStudents;
    }
}
