package com.example.microservice1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
