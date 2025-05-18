package com.example.ciandtest.service;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.model.User;
import com.example.ciandtest.model.UserDTO;
import com.example.ciandtest.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(User user) {
        entityManager.clear(); // TODO: testiraj kasnije jel ovo potrebno
        return UserMapper.INSTANCE.userToUserDTO(userRepository.save(user));
    }

    public Optional<UserDTO> findByUsername(String username) {
        Optional<User> user =  userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<UserDTO> result = user.map(UserMapper.INSTANCE::userToUserDTO);
            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            Optional<UserDTO> result = user.map(UserMapper.INSTANCE::userToUserDTO);
            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO deleteUser(UserDTO user) {
        userRepository.deleteById(user.getId());
        return user;
    }

    public UserDTO updateUser(UserDTO user) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.save(new User(user)));
    }
}
