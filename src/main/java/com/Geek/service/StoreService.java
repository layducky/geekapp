package com.Geek.service;

import com.Geek.dto.request.StoreRequestDto;
import com.Geek.model.Address;
import com.Geek.model.Store;
import com.Geek.model.User;
import com.Geek.repository.AddressRepository;
import com.Geek.repository.StoreRepository;
import com.Geek.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public StoreService(StoreRepository storeRepository,
                        UserRepository userRepository,
                        AddressRepository addressRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Store createStore(StoreRequestDto storeRequest) {

        UUID userId = UUID.fromString(storeRequest.getUserId());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Address address = null;
        if (storeRequest.getAddressId() != null) {
            UUID addressId = UUID.fromString(storeRequest.getAddressId());
            address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + addressId));
        }

        // Create Store
        Store store = new Store();
        store.setStatus("Active");
        store.setUser(user);
        store.setAddress(address);

        return storeRepository.save(store);
    }
}