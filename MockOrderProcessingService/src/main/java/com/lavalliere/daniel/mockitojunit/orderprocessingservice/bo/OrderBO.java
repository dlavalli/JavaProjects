package com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo.exception.BOException;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;

public interface OrderBO {
    boolean placeOder(Order order) throws BOException;
    boolean cancelOrder(int id) throws BOException;
    boolean deleteOrder(int id) throws BOException;
}
