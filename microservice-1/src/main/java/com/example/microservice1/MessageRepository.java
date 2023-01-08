package com.example.microservice1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
//    https://javarush.com/groups/posts/3099-spring-ehto-ne-strashno--ili-kak-zadatjh-vopros-bd
    Message findMessageById(int id);
}
