package magenga.TimeLedger.common;

import magenga.TimeLedger.common.dao.UserDao;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserQueryService {

    @Autowired
    private UserDao userDao;

    public User findOne(int id) {
        return userDao.findOne(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findSeqByAccount(String account) {
        return userDao.findSeqByAccount(account);
    }
}