package com.example.microservice1.repository;

import com.example.microservice1.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
//    https://javarush.com/groups/posts/3099-spring-ehto-ne-strashno--ili-kak-zadatjh-vopros-bd
    Message findMessageById(int id);
}
