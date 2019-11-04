package com.marcin.Facebook.message.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository <Message, Integer> {

    @Override
    Optional<Message> findById(Integer integer);
}
