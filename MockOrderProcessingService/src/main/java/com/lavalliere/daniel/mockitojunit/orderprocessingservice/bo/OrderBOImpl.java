package com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo.exception.BOException;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao.OrderDAO;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;

import java.sql.SQLException;

public class OrderBOImpl  implements OrderBO {

    private OrderDAO dao;

    @Override
    public boolean placeOder(Order order) throws BOException {
        try {
            int result = dao.create(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = dao.read(id);
            order.setStatus("cancelled");
            int result = dao.update(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            int result = dao.delete(id);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    public OrderDAO getDao() {
        return dao;
    }

    public void setDao(OrderDAO dao) {
        this.dao = dao;
    }
}
