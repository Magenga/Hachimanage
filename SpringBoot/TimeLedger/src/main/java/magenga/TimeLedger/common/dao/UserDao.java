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

    @Autowired
    private EntityManager entityManager;

    public void save(User theUser) {
        entityManager.persist(theUser);
    }
    public void update(User theUser) {
        entityManager.merge(theUser);
    }
    public void delete(User theUser) {
        User user = entityManager.merge(theUser);
        entityManager.remove(user);
    }

    public User findOne(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> thwQuery = entityManager.createQuery("FROM User ORDER BY id", User.class);
        return thwQuery.getResultList();
    }

    public User findByAccount(String account) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE account = :account", User.class);
        theQuery.setParameter("account",account);
        try {
            return theQuery.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}
