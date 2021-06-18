package com.example.demo.repository;

import com.example.demo.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NamedParamJdbcClientRepository extends JdbcClientRepository{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<Client> findById(int id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from clients where id = :id",
                new MapSqlParameterSource("id", id),  //create KV pair for finding
                (rs, rowNum) ->
                        Optional.of(new Client(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email")
                        ))
        );
    }
}
