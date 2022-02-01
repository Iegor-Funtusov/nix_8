package ua.com.alevel.final_project.persistence.entity.user;

import ua.com.alevel.final_project.persistence.entity.type.RoleType;
import ua.com.alevel.final_project.persistence.listener.FullNameGenerationListener;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

@Entity
@EntityListeners({
        FullNameGenerationListener.class
})
@DiscriminatorValue("PERSONAL")
public class Personal extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
