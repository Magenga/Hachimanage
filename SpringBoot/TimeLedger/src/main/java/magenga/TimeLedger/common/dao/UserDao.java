package magenga.TimeLedger.common.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    public void save(User theUser) {
        entityManager.persist(theUser);
    }

    public User findOne(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> thwQuery = entityManager.createQuery("FROM User", User.class);
        return thwQuery.getResultList();
    }

    public User findSeqByAccount(String account) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE account = :account", User.class);
        theQuery.setParameter("account",account);
        try {
            return theQuery.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}
