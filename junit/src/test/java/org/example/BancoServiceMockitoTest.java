package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BancoServiceMockitoTest {
    @Test
    public void testDepositar() {
        RepositorioBanco repositorio = mock(RepositorioBanco.class);
        BancoService service = new BancoService(repositorio);

        when(repositorio.obtenerSaldo("123")).thenReturn(300.0);

        service.depositar("123", 150.0);

        verify(repositorio).actualizarSaldo("123", 450.0);
    }

    @Test
    public void testRetirarFondosInsuficientes() {
        RepositorioBanco repositorio = mock(RepositorioBanco.class);
        BancoService service = new BancoService(repositorio);

        when(repositorio.obtenerSaldo("123")).thenReturn(100.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.retirar("123", 150.0);
        });
        assertEquals("Fondos insuficientes", exception.getMessage());
    }

    @Test
    public void testOrdenDeEjecucion() {
        RepositorioBanco repositorio = mock(RepositorioBanco.class);
        BancoService service = new BancoService(repositorio);

        when(repositorio.obtenerSaldo("123")).thenReturn(500.0);

        service.depositar("123", 100.0);
        service.retirar("123", 50.0);

        InOrder inOrder = inOrder(repositorio);
        inOrder.verify(repositorio).obtenerSaldo("123");
        inOrder.verify(repositorio).actualizarSaldo("123", 600.0);
        inOrder.verify(repositorio).obtenerSaldo("123");
        inOrder.verify(repositorio).actualizarSaldo("123", 550.0);
    }

    @Test
    public void testDoThrow() {
        RepositorioBanco repositorio = mock(RepositorioBanco.class);
        BancoService service = new BancoService(repositorio);

        when(repositorio.obtenerSaldo("123")).thenReturn(400.0);
        doThrow(new RuntimeException("Error actualizando saldo"))
                .when(repositorio).actualizarSaldo("123", 500.0);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.depositar("123", 100.0);
        });
        assertEquals("Error actualizando saldo", thrown.getMessage());
    }

    @Test
    public void testSpy() {
        RepositorioBanco repositorioReal = new RepositorioBanco() {
            private double saldo = 300.0;
            @Override
            public double obtenerSaldo(String cuenta) {
                return saldo;
            }
            @Override
            public void actualizarSaldo(String cuenta, double nuevoSaldo) {
                this.saldo = nuevoSaldo;
            }
        };

        RepositorioBanco repositorioSpy = spy(repositorioReal);
        BancoService service = new BancoService(repositorioSpy);

        service.depositar("123", 200.0);

        assertEquals(500.0, repositorioSpy.obtenerSaldo("123"));
        verify(repositorioSpy).obtenerSaldo("123");
        verify(repositorioSpy).actualizarSaldo("123", 500.0);
    }
}
