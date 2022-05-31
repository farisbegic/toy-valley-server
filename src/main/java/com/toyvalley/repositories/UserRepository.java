package com.toyvalley.repositories;

import com.toyvalley.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Modifying
  @Query("update User au set au.active = :activity where au.id = :userId")
  void setUserActivity(@Param("activity") boolean activity, @Param("userId") long userId);

  @Query("select u.id, u.name, u.surname, u.phone, u.address, u.city, u.email, u.password from User u where u.active = true")
  List<User> findActiveUsers();

  Optional<User> findByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE User a " +
    "SET a.active = TRUE WHERE a.email = ?1")
  int enableUser(String email);

}
