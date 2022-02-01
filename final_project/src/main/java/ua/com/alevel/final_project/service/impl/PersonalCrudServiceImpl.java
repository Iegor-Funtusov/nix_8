package ua.com.alevel.final_project.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.final_project.persistence.entity.user.Personal;
import ua.com.alevel.final_project.persistence.repository.user.PersonalRepository;
import ua.com.alevel.final_project.service.PersonalCrudService;

@Service
public class PersonalCrudServiceImpl implements PersonalCrudService {

    private final PersonalRepository personalRepository;
    private final BCryptPasswordEncoder encoder;

    public PersonalCrudServiceImpl(PersonalRepository personalRepository, BCryptPasswordEncoder encoder) {
        this.personalRepository = personalRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Personal create(Personal entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return personalRepository.save(entity);
    }

    @Override
    public Personal update(Personal entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
