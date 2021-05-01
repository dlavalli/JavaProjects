package com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo.exception.BOException;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao.OrderDAO;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderBOImplTest {

    private OrderBOImpl bo;

    // Just an example (annotation comes with MockitoExtension)
    // @InjectMocks
    // private GreetingService service;

    @Mock
    private OrderDAO dao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);   // deprecated way
        bo = new OrderBOImpl();
        bo.setDao(dao);
    }

    @AfterEach
    public void cleanup() throws Exception {
        bo = null;
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
        // Mocking starts here
        Order order = new Order();
       // when(dao.create(order)).thenReturn(1);  // Set the expectations
        when(dao.create(any(Order.class))).thenReturn(1);  // Set the expectations

        boolean result = bo.placeOder(order);
        assertTrue(result);

        // Verify that the methpd to verify was called once
        // verify(dao, times(1)).create(order);   // Verify phase
        verify(dao, atLeast(1)).create(order);   // Verify phase
    }

    @Test
    public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {
        // Mocking starts here
        Order order = new Order();
        when(dao.create(order)).thenReturn(0);  // Set the expectations

        boolean result = bo.placeOder(order);
        assertFalse(result);

        // Verify that the methpd to verify was calle donce
        verify(dao).create(order);   // Verify phase
    }

    @Test
    public void placeOrder_Should_Throw_BOException() throws SQLException, BOException {

        Assertions.assertThrows(BOException.class, () -> {
            // Mocking starts here
            Order order = new Order();
            when(dao.create(order)).thenThrow(SQLException.class);  // Set the expectations
            boolean result = bo.placeOder(order);
        });
    }

    @Test
    public void cancel_Order_should_cancel_the_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order) ;  // Set the expectations
        when(dao.update(order)).thenReturn(1);
        boolean result = bo.cancelOrder(123);
        assertTrue(result);

        // Verify that the methpd to verify was calle donce
        verify(dao).read(123);   // Verify phase
        verify(dao).update(order);
    }

    @Test
    public void cancel_Order_should_not_cancel_the_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order) ;  // Set the expectations
        when(dao.update(order)).thenReturn(0);
        boolean result = bo.cancelOrder(123);
        assertFalse(result);

        // Verify that the methpd to verify was calle donce
        verify(dao).read(123);   // Verify phase
        verify(dao).update(order);
    }

    @Test
    public void cancel_Order_should_throw_BOException_On_Read() throws SQLException, BOException {
        Assertions.assertThrows(BOException.class, () -> {
            // when(dao.read(123)).thenThrow(SQLException.class) ;  // Set the expectations
            when(dao.read(anyInt())).thenThrow(SQLException.class) ;  // Set the expectations
            boolean result = bo.cancelOrder(123);
        });
    }

    @Test
    public void cancel_Order_should_throw_BOException_On_Update() throws SQLException, BOException {
        Assertions.assertThrows(BOException.class, () -> {
            Order order = new Order();
            when(dao.read(123)).thenReturn(order);  // Set the expectations
            when(dao.update(order)).thenThrow(SQLException.class);
            boolean result = bo.cancelOrder(123);
        });
    }

    @Test
    public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
        when(dao.delete(123)).thenReturn(1);
        boolean result = bo.deleteOrder(123);
        assertTrue(result);
        verify(dao).delete(123);
    }
}
