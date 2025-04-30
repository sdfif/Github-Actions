package org.example;

/**
 * La clase BancoService proporciona servicios para gestionar operaciones
 * bancarias básicas como deposito, retiro y consulta de saldo, usando un repositorio
 */
public class BancoService {
    private final RepositorioBanco repositorio;

    /**
     * Crea una nueva instancia de BancoService con el repositorio especificado.
     *
     * @param repositorio la implementación de RepositorioBanco que se utilizará para interactuar
     *  con los datos de las cuentas.
     */
    public BancoService(RepositorioBanco repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Deposita un monto en la cuenta bancaria indicada.
     *
     * El método obtiene el saldo actual de la cuenta, le suma el monto a depositar y actualiza el saldo
     * a través del repositorio.
     *
     * @param cuenta el identificador único de la cuenta.
     * @param monto  el monto a depositar.
     */
    public void depositar(String cuenta, double monto) {
        double saldoActual = repositorio.obtenerSaldo(cuenta);
        repositorio.actualizarSaldo(cuenta, saldoActual + monto);
    }

    /**
     * Retira un monto de la cuenta bancaria indicada.
     *
     * @param cuenta el identificador único de la cuenta.
     * @param monto  el monto a retirar.
     * @throws IllegalArgumentException si el monto a retirar es superior al saldo disponible.
     */
    public void retirar(String cuenta, double monto) {
        double saldoActual = repositorio.obtenerSaldo(cuenta);
        if (monto > saldoActual) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        repositorio.actualizarSaldo(cuenta, saldoActual - monto);
    }

    /**
     * Consulta y devuelve el saldo actual de la cuenta bancaria.
     *
     * @param cuenta el identificador único de la cuenta.
     * @return el saldo actual obtenido a través del repositorio.
     */
    public double consultarSaldo(String cuenta) {
        return repositorio.obtenerSaldo(cuenta);
    }
}
