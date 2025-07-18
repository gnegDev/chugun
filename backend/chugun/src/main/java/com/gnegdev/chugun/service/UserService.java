package com.gnegdev.chugun.service;

import com.gnegdev.chugun.entity.User;
import com.gnegdev.chugun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Optional<User> findByPartyRk(Integer partyRk) {
        System.out.println();
        return userRepository.findByPartyRk(partyRk);
//        Optional<User> user = userRepository.findByPartyRk(partyRk);
//        System.out.println(user.get().getTransactions().get(0).toString());
//        return user;
    }

    public User createUser(User user) {
        user = userRepository.save(user);
        return user;
    }

}
