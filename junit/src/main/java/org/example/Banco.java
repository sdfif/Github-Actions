package org.example;

/**
 * La clase Banco representa una cuenta bancaria simple que permite
 * realizar operaciones básicas como depositar, retirar y consultar el saldo.
 *
 * @author Samuel Fiffe
 */
public class Banco {
    private double saldo;

    /**
     * Crea un nuevo objeto con saldo inicial en cero.
     */
    public Banco() {
        this.saldo = 0.0;
    }

    /**
     * Incrementa el saldo de la cuenta bancaria.
     *
     * @param monto el monto a depositar.
     */
    public void depositar(double monto) {
        saldo += monto;
    }

    /**
     * Disminuye el saldo de la cuenta bancaria si hay fondos suficientes.
     *
     * @param monto el monto a retirar.
     * @throws IllegalArgumentException si el monto a retirar excede el saldo disponible.
     */
    public void retirar(double monto) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        saldo -= monto;
    }
    /**
     * Consulta el saldo actual de la cuenta bancaria.
     *
     * @return el saldo actual.
     */
    public double consultarSaldo() {
        return saldo;
    }
}
