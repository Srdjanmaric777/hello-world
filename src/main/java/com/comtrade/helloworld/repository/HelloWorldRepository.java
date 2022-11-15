package com.comtrade.helloworld.repository;

import com.comtrade.helloworld.model.HelloWorld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {

}
