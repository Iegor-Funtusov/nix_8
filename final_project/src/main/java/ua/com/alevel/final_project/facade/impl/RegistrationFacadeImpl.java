package ua.com.alevel.final_project.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.final_project.facade.RegistrationFacade;
import ua.com.alevel.final_project.persistence.entity.user.Personal;
import ua.com.alevel.final_project.service.PersonalCrudService;
import ua.com.alevel.final_project.web.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalCrudService personalCrudService;

    public RegistrationFacadeImpl(PersonalCrudService personalCrudService) {
        this.personalCrudService = personalCrudService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
        personal.setUsername(dto.getUsername());
        personal.setPassword(dto.getPassword());
        personalCrudService.create(personal);
    }
}
