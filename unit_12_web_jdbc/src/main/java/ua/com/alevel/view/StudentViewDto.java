package ua.com.alevel.view;

import ua.com.alevel.entity.Student;

public class StudentViewDto extends Student {

    private int countOfCourses;

    public int getCountOfCourses() {
        return countOfCourses;
    }

    public void setCountOfCourses(int countOfCourses) {
        this.countOfCourses = countOfCourses;
    }
}
