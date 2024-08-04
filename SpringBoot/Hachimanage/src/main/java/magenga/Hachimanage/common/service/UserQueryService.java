package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.dao.UserRepository;
import magenga.Hachimanage.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);

        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
        }
        return theUser;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findSeqByAccount(String account) {
        return userRepository.findByAccount(account);
    }
}