package ua.com.alevel.final_project.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.final_project.config.security.SecurityService;
import ua.com.alevel.final_project.facade.AuthValidatorFacade;
import ua.com.alevel.final_project.web.dto.request.register.AuthDto;

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final SecurityService securityService;

    public AuthValidatorFacadeImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthDto dto = (AuthDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (dto.getUsername().length() < 6 || dto.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.authForm.username");
        }

        if (securityService.existsByUsername(dto.getUsername())) {
            errors.rejectValue("username", "Duplicate.authForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (dto.getPassword().length() < 8 || dto.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }
        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
