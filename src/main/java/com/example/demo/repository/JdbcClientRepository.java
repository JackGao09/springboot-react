package com.example.demo.repository;

import com.example.demo.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRepository implements ClientRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from clients", Integer.class);
    }

    @Override
    public Client save(Client client) {
         jdbcTemplate.update("insert into clients (id, name, email) values(?, ?, ?)",
                client.getId(), client.getName(), client.getEmail());
         return client;
    }

    @Override
    public Client update(Client client) {
         jdbcTemplate.update(
                "update clients set name = ?, email = ? where id = ?",
                client.getName(), client.getEmail(), client.getId());
        return client;
    }


    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from clients where id = ?",
                id);
    }

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(
                "select * from clients",
                (rs, rowNum) ->
                        new Client(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email")
                        )
        );
    }

    @Override
    public Optional<Client> findById(int id) {
        return Optional.empty();
    }
}
