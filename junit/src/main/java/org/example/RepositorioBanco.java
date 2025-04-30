package org.example;

/**
 * La interfaz RepositorioBanco define los métodos necesarios para acceder
 * y actualizar el saldo de una cuenta bancaria.
 */
public interface RepositorioBanco {

    /**
     * Obtiene el saldo actual de la cuenta especificada.
     *
     * @param cuenta el identificador único de la cuenta.
     * @return el saldo actual de la cuenta.
     */
    double obtenerSaldo(String cuenta);

    /**
     * Actualiza el saldo de la cuenta especificada con un nuevo valor.
     *
     * @param cuenta    el identificador único de la cuenta.
     * @param nuevoSaldo el nuevo saldo que se asignará a la cuenta.
     */
    void actualizarSaldo(String cuenta, double nuevoSaldo);
}
