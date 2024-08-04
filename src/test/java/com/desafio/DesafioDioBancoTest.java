package com.desafio;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DesafioDioBancoTest {

    @Mock
    ApplicationContext applicationContext;

    @Test
    void setApplicationContext()throws  Exception {
        DesafioDioBanco.main(new String[]{});
    }
}