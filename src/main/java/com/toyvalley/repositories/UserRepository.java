package com.toyvalley.repositories;

import com.toyvalley.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("update User au set au.isActive = :activity where au.id = :userId")
  boolean setUserActivity(@Param("activity") boolean activity, @Param("userId") long userId);
}
