package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.ChangeLog;
import com.example.eksamensprojektbilabonnement.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<ChangeLog> getChangeLogs() {
        String query = "SELECT * FROM change_logs";
        RowMapper<ChangeLog> rowMapper = new BeanPropertyRowMapper<>(ChangeLog.class);
        return jdbcTemplate.query(query, rowMapper);
    }
}
