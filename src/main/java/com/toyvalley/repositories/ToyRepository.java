package com.toyvalley.repositories;

import com.toyvalley.models.data.toy.SearchToyResponse;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {
    @Query("SELECT DISTINCT t FROM Toy t, ToyCategory tc WHERE t = tc.toy AND tc.category.id = :categoryId")
    List<Toy> getToysByCategoryId(@Param("categoryId") long categoryId);
    @Query("SELECT new com.toyvalley.models.data.toy.SearchToyResponse(t.id, t.name) FROM Toy t WHERE upper(t.name) LIKE upper(concat('%', :name, '%'))")
    List<SearchToyResponse> getToysByName(@Param("name") String name);
    List<Toy> findToysByUser_CityId(long user_city_id);
    @Query("SELECT new com.toyvalley.models.data.toy.SearchToyResponse(t.id, t.name) FROM Toy t WHERE upper(t.gender) LIKE upper(concat('%', :gender, '%'))")
    List<SearchToyResponse> getToysByGender(@Param("gender") Gender gender);
    List<Toy> findToysByGender(Gender gender);
    @Query("SELECT new com.toyvalley.models.data.toy.SearchToyResponse(t.id, t.name) FROM Toy t WHERE upper(t.condition) LIKE upper(concat('%', :condition, '%'))")
    List<SearchToyResponse> getToysByCondition(@Param("condition") Condition condition);
    List<Toy> findToysByCondition(Condition condition);

}
