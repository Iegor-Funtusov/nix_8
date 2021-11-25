package ua.com.alevel.crud;

public class Department extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return "Department{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
