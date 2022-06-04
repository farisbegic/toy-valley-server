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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = getFullUserByUsername(userName);

        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), Collections.emptyList());
    }

    public SimpleUser getUserByUsername(String userName) {
        getFullUserByUsername(userName);
        return new SimpleUser(userName);
    }

    private User getFullUserByUsername(String userName) {

        return userRepository.findFirstByUsername(userName);
    }
}
