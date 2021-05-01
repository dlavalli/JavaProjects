package com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo;

import com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo.exception.BOException;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dao.OrderDAO;
import com.lavalliere.daniel.mockitojunit.orderprocessingservice.dto.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SpyListTest {

    @Spy
    List myList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        myList.add("Daniel");
        myList.add("Mario");
        myList.add("Sylvain");

        // By default, Spy will call the real method so cannot use when()... syntax here
        // real will method will be called. To stub, use the doReturn() syntax

        Mockito.doReturn(4 ).when(myList).size();
        assertSame(4, myList.size());
    }
}
