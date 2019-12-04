package com.groupname.demo.service;

import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserInformationService {
    @Autowired
    private UserRepository userRepository;


}
