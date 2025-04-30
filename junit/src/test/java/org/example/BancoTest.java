package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    public void testDepositar() {
        Banco banco = new Banco();
        banco.depositar(100);
        assertEquals(100, banco.consultarSaldo(), "El saldo debe ser 100 tras el depósito");
    }

    @Test
    public void testRetirarFondosSuficientes() {
        Banco banco = new Banco();
        banco.depositar(200);
        banco.retirar(100);
        assertEquals(100, banco.consultarSaldo(), "El saldo debe ser 100 después del retiro");
    }

    @Test
    public void testRetirarFondosInsuficientes() {
        Banco banco = new Banco();
        banco.depositar(50);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            banco.retirar(100);
        });
        assertEquals("Fondos insuficientes", exception.getMessage());
    }
}
