package magenga.TimeLedger.common;

import jakarta.transaction.Transactional;
import magenga.TimeLedger.common.dao.UserDao;
import magenga.TimeLedger.common.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import static magenga.TimeLedger.common.dao.UserDao.entityManager;

@Repository
public class UserService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public void save(User theUser) {
        entityManager.persist(theUser);
    }



    public boolean signUpCheck (String account) {
        User checkingUser = UserDao.findSeqByAccount(account);
        return checkingUser == null;
    }

    public boolean signInCheck (String account, String password) {
        User checkingUser = UserDao.findSeqByAccount(account);
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
