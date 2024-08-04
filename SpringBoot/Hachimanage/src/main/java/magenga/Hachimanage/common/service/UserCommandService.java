package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.dao.UserRepository;
import magenga.Hachimanage.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    @Autowired
    private UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save (User theUser) {
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
    }

    public void updatePassword (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete (User user) {
        userRepository.delete(user);
    }

    public boolean signUpCheck (String account) {
        User checkingUser = userRepository.findByAccount(account);
        return checkingUser == null;
    }

    public boolean signInCheck (String account, String password) {
        User checkingUser = userRepository.findByAccount(account);
        if (checkingUser == null) {
            System.out.println("User not found.");
            return false;
        }
        boolean passwordCheck = passwordEncoder.matches(password,checkingUser.getPassword());
        if (passwordCheck) {
            System.out.println("sign check.");
            return true;
        }else {
            System.out.println("sign failed.");
            return false;
        }
    }
}
