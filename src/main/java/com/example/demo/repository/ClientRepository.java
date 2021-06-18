package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    int count();

    Client save(Client book);

    Client update(Client book);

    int deleteById(int id);

    List<Client> findAll();

    Optional<Client> findById(int id);
}
