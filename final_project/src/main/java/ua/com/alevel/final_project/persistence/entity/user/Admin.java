package ua.com.alevel.final_project.persistence.entity.user;

import ua.com.alevel.final_project.persistence.entity.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {
        super();
        setRoleType(RoleType.ROLE_ADMIN);
    }
}
