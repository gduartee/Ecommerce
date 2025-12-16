package com.ecommerce.joias.service;

import com.ecommerce.joias.dto.CreateUserDto;
import com.ecommerce.joias.entity.User;
import com.ecommerce.joias.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UUID createUser(CreateUserDto createUserDto){

        if(userRepository.existsByEmail(createUserDto.email()))
            throw new RuntimeException("Este e-mail já está cadastrado.");

        if(userRepository.existsByPhoneNumber(createUserDto.phoneNumber()))
            throw new RuntimeException("Este telefone já está cadastrado.");

        if(userRepository.existsByCpf(createUserDto.cpf()))
            throw new RuntimeException("Este CPF já está cadastrado.");

        // DTO -> ENTITY
        User userEntity = new User();
        userEntity.setName(createUserDto.name());
        userEntity.setEmail(createUserDto.email());
        userEntity.setPhoneNumber(createUserDto.phoneNumber());
        userEntity.setCpf(createUserDto.cpf());
        userEntity.setPassword(passwordEncoder.encode(createUserDto.password()));

        var userSaved = userRepository.save(userEntity);

        return userSaved.getUserId();
    }

    public User getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException(("Usuário não encontrado")));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    
}
