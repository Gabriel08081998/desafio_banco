package com.desafio.util;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class UtilCriptografiaTest {
    @InjectMocks
    private UtilCriptografia utilCriptografia;

    @Test
    void hashPassword() {
        String password = "123456";
        String hashedPassword = UtilCriptografia.hashPassword(password);
        assertEquals("e10adc3949ba59abbe56e057f20f883e", hashedPassword);
    }

    @Test
    void hashPasswordWithDifferentInput() {
        String password = "abcdef";
        String hashedPassword = UtilCriptografia.hashPassword(password);
        assertEquals("e80b5017098950fc58aad83c8c14978e", hashedPassword);
    }

    @Test
    void hashPasswordEmptyString() {
        String password = "";
        String hashedPassword = UtilCriptografia.hashPassword(password);
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hashedPassword);
    }

    @Test
    void hashPasswordNull() {
        String hashedPassword = UtilCriptografia.hashPassword(null);
        assertNull(hashedPassword);
    }

    @Test
    void hashPasswordSpecialCharacters() {
        String password = "!@#$%^&*()";
        String hashedPassword = UtilCriptografia.hashPassword(password);
        assertEquals("05b28d17a7b6e7024b6e5d8cc43a8bf7", hashedPassword);
    }
}
