package com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao.com.lavalliere.daniel.mockitojunit.orderprocessingservice;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao.OrderDAO;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;

import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public int create(Order order) throws SQLException {
        return 0;
    }

    @Override
    public Order read(int id) throws SQLException {
        return null;
    }

    @Override
    public int update(Order order) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
