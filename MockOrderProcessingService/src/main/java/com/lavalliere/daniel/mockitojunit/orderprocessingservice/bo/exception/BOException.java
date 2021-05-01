package com.lavalliere.daniel.mockitojunit.orderprocessingservice.bo.exception;

import java.sql.SQLException;

public class BOException extends Exception {
    private static final long servialVersionUID = 1L;
    public BOException(SQLException e) {
        super(e);
    }
}
