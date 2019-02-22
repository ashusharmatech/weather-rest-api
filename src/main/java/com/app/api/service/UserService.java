package com.app.api.service;

import com.app.api.dto.UserDTO;
import com.app.api.model.RoleName;
import com.app.api.model.User;
import com.app.api.repository.RoleRepository;
import com.app.api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User save(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setRoles(roleRepository.findAllByName(RoleName.ROLE_USER));
        user.setLastPasswordResetDate(new Date());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user;
    }

    public boolean existUserByEmail(String email) {
        return userRepository.existsUsersByEmail(email);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> convertToDto(user)).collect(Collectors.toList());
    }

    public User find(Long id) {
        return userRepository.findOne(id);
    }

    public UserDTO findById(Long id) {
        return convertToDto(userRepository.findOne(id));
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

}
