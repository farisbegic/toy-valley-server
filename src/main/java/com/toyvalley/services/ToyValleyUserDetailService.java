package com.toyvalley.services;

import com.toyvalley.models.data.user.SimpleUser;
import com.toyvalley.models.entities.User;
import com.toyvalley.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ToyValleyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public ToyValleyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = getFullUserByMail(mail);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), Collections.emptyList());
    }

    public SimpleUser getUserByUsername(String mail) {
        getFullUserByMail(mail);
        return new SimpleUser(mail);
    }

    private User getFullUserByMail(String mail) {
        return userRepository.findFirstByEmail(mail);
    }
}
