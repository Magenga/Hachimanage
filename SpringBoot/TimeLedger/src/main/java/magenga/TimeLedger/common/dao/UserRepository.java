package magenga.TimeLedger.common.dao;

import magenga.TimeLedger.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);

    @Override
    @Query("SELECT u FROM User u ORDER BY u.id")
    List<User> findAll();
}
