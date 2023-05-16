package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Role;
import com.ghitaprojects.digitalgameshopproject.entity.User;
import com.ghitaprojects.digitalgameshopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User save(User user) {
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public boolean isValidUser(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }



}