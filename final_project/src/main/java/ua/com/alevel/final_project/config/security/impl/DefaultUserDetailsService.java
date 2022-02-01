package ua.com.alevel.final_project.config.security.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.alevel.final_project.persistence.entity.user.User;
import ua.com.alevel.final_project.persistence.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository<User> userRepository;

    public DefaultUserDetailsService(UserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        User user = optionalUser.get();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleType().name()));
        return convertCustomUserToSpringUser(user, authorities);
    }

    private org.springframework.security.core.userdetails.User convertCustomUserToSpringUser(
            User user, Set<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                true,
                authorities
        );
    }
}
