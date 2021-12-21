package ua.com.alevel.entity;

public class Course extends BaseEntity {

    private CourseName name;

    public CourseName getName() {
        return name;
    }

    public void setName(CourseName name) {
        this.name = name;
    }
}
