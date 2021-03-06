package com.toyvalley.repositories;

import com.toyvalley.models.data.user.TopTraders;
import com.toyvalley.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
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

  @Modifying
  @Query("select new com.toyvalley.models.data.user.TopTraders(u.id, u.name, u.surname, count(ex.id)) from User u, Toy t, ExchangeRequest ex where t.user = u AND ex.toy_offered = t GROUP BY u.id ORDER BY count(ex.id) DESC")
  List<TopTraders> findTopTraders();

  User findUserById(long userId);

  User findFirstByEmail(String mail);

}
