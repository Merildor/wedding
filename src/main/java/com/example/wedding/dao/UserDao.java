package com.example.wedding.dao;

import com.example.wedding.user.User;
import com.example.wedding.user.UserWithLesserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Query(value = "SELECT u.id, u.email, u.first_name, u.last_name FROM users u", nativeQuery = true)
    List<UserWithLesserDetails> findAllUsersWithLessDetails();

    @Transactional
    @Modifying
    @Query("UPDATE User a SET a.isEnabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
