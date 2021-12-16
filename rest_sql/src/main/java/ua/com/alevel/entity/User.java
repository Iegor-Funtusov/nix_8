package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
