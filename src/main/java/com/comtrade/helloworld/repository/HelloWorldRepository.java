package com.comtrade.helloworld.repository;

import com.comtrade.helloworld.model.HelloWorld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {
    @Query("select h from HelloWorld h where upper(h.language) like upper(concat('%', ?1, '%'))")
    Optional<HelloWorld> findByLanguage(String language);
}
