package ua.com.alevel.final_project.facade;

import ua.com.alevel.final_project.web.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void registration(AuthDto dto);
}
