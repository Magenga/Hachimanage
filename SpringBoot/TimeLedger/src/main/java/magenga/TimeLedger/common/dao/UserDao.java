package magenga.TimeLedger.common.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    public static EntityManager entityManager;

    @Autowired
    public UserDao(EntityManager entityManager) {
        UserDao.entityManager = entityManager;;
    }

    public User findOne (int id) {
        return entityManager.find(User.class, id);
    }

    public static List<User> findAll() {
        TypedQuery<User> thwQuery = entityManager.createQuery("FROM User", User.class);
        return thwQuery.getResultList();
    }

    public static User findSeqByAccount(String account) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE account = :account", User.class);
        theQuery.setParameter("account",account);
        try {
            return theQuery.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}
