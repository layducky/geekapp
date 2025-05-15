package com.Geek.service;

import com.Geek.dto.request.UserRequestDto;
import com.Geek.model.User;
import com.Geek.model.Address;
import com.Geek.repository.UserRepository;
import com.Geek.repository.AddressRepository;
//import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public User createUser(UserRequestDto userRequest) {

        // Create User
        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhonenumber(userRequest.getPhonenumber());
        user.setPassword(userRequest.getPassword());
        user = userRepository.save(user);

        Address address = new Address();
        address.setAddress(userRequest.getAddress());
        address.setUser(user);

        addressRepository.save(address);

        return user;
    }
}