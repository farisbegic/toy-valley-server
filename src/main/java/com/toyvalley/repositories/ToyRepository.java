package com.toyvalley.repositories;

import com.toyvalley.models.data.toy.SearchToyResponse;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {

    @Modifying
    @Query("SELECT DISTINCT t FROM Toy t, ToyCategory tc WHERE t = tc.toy AND tc.category.id = :categoryId")
    List<Toy> getToysByCategoryId(@Param("categoryId") long categoryId);

    @Modifying
    @Query("SELECT new com.toyvalley.models.data.toy.SearchToyResponse(t.id, t.name) FROM Toy t WHERE upper(t.name) LIKE upper(concat('%', :name, '%'))")
    List<SearchToyResponse> getToysByName(@Param("name") String name);

    List<Toy> findToysByUser_CityId(long user_city_id);

    List<Toy> findToysByUserId(long userId);

    @Modifying
    @Query(value = "SELECT * from Toy WHERE Toy.gender=:gender",nativeQuery = true)
    List<Toy> getToyByGender(@Param("gender") String gender);

    @Modifying
    @Query(value = "SELECT * from Toy WHERE Toy.condition=:condition",nativeQuery = true)
    List<Toy> getToyByCondition(@Param("condition") String condition);

    Toy findAllById(long toyId);

    @Query(value = "SELECT * FROM toy WHERE toy.active = true AND toy.user_id = :userId", nativeQuery = true)
    List<Toy> findActiveToysByUser(@Param("userId") long userId);
}
