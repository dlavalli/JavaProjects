package com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;

import java.sql.SQLException;

public interface OrderDAO {
    public int create(Order order) throws SQLException;
    Order read(int id) throws SQLException;
    int update(Order order) throws SQLException;
    int delete(int id) throws SQLException;
}
