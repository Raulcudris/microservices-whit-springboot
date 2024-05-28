package com.makiia.users_services.services;
import com.makiia.users_services.entity.User;
import com.makiia.users_services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public User getUserById( Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User userNew = userRepository.save(user);
        return  userNew;
    }

}
