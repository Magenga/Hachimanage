package magenga.TimeLedger.common;

import jakarta.transaction.Transactional;
import magenga.TimeLedger.common.dao.UserDao;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserCommandService {
    @Autowired
    private UserDao userDao;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void save(User theUser) {
        theUser.setPassword(encoder.encode(theUser.getPassword()));
        userDao.save(theUser);
    }

    public void updatePassword(User theUser, String changedPassword) {
        theUser = userDao.findSeqByAccount(theUser.getAccount());
        User user = userDao.findOne(theUser.getUserSeq());
        theUser.setPassword(encoder.encode(changedPassword));
    }

    public boolean signUpCheck (String account) {
        User checkingUser = userDao.findSeqByAccount(account);
        return checkingUser == null;
    }

    public boolean signInCheck (String account, String password) {
        User checkingUser = userDao.findSeqByAccount(account);
        if (checkingUser == null) {
            System.out.println("User not found.");
            return false;
        }
        boolean passwordCheck = encoder.matches(password,checkingUser.getPassword());
        if (passwordCheck) {
            System.out.println("sign check.");
            return true;
        }else {
            System.out.println("sign failed.");
            return false;
        }
    }
}
