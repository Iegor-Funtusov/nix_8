package ua.com.alevel.final_project.persistence.listener;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.final_project.persistence.entity.user.Personal;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class FullNameGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateFullName(Personal personal) {
        if (StringUtils.isBlank(personal.getFirstName()) || StringUtils.isBlank(personal.getLastName())) {
            personal.setFullName("");
            return;
        }
        personal.setFullName(personal.getFirstName() + " " + personal.getLastName());
    }
}
