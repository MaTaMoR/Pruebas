package me.matamor.pruebas.tema11.ejercicio7.estadio;

import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.entradas.Entrada;
import me.matamor.pruebas.tema11.ejercicio7.entradas.EntradaNormal;
import me.matamor.pruebas.tema11.ejercicio7.entradas.EntradaVIP;
import me.matamor.pruebas.tema11.ejercicio7.exceptions.ZonaException;

public class Zona {

    private final RegistroEntradas registroEntradas;
    private final int id;

    private final Entrada[][] entradas;
    private final boolean vip;

    private final int filas;
    private final int asientos;

    public Zona(RegistroEntradas registroEntradas, int id, int filas, int asientos) {
        this(registroEntradas, id, filas, asientos, false);
    }

    public Zona(RegistroEntradas registroEntradas, int id, int filas, int asientos, boolean vip) {
        this.registroEntradas = registroEntradas;
        this.id = id;

        this.entradas = new Entrada[filas][asientos];
        this.vip = vip;

        this.filas = filas;
        this.asientos = asientos;
    }

    /**
     * @return la id de la zona
     */

    public int getId() {
        return this.id;
    }

    /**
     * @return la cantidad de filas de la Zona
     */

    public int filas() {
        return this.filas;
    }

    /**
     * @return la cantidad asientos de la Zona
     */

    public int asientos() {
        return this.asientos;
    }

    /**
     * @return true si la Zona es VIP
     */

    public boolean isVip() {
        return this.vip;
    }

    /**
     * @return todas las entradas
     */

    public Entrada[][] getEntradas() {
        return this.entradas;
    }

    /**
     * Comprueba que la fila y el asiento esten dentro del rango valido
     * @param fila la fila a comprobar
     * @param asiento el asiento a comprobar
     * @throws ZonaException si la fila o el asiento estan fueran del rango valido
     */

    private void comprobarRangos(int fila, int asiento) throws ZonaException {
        if (fila < 0 || fila >= this.filas) {
            throw new IllegalArgumentException("La fila está fuera del rango valido!");
        }

        if (asiento < 0 || asiento >= this.asientos) {
            throw new IllegalArgumentException("El asiento está fuera del rango valido!");
        }
    }

    /**
     * Crea una nueva entrada para la fila y asiento definidos
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la entrada creada (EntradaVIP si la zona es vip)
     * @throws ZonaException si la fila/asiento está fuera del rango permitido o si ya hay una entrada registrada en la misma fila y asiento
     */

    public Entrada crearEntrada(int fila, int asiento) throws ZonaException {
        comprobarRangos(fila, asiento);

        Entrada entrada = buscarEntrada(fila, asiento);
        if (entrada == null) {
            double precio = Constantes.PRECIO_BASE * this.registroEntradas.getPartido().getTipoPartido().getMultiplicador();

            if (this.vip) {
                entrada = new EntradaVIP(this.registroEntradas, this.id, fila, asiento, precio);
            } else {
                entrada = new EntradaNormal(this.registroEntradas, this.id, fila, asiento, precio, this.registroEntradas.nuevoNumeroSorteo());
            }

            this.entradas[fila][asiento] = entrada;

            this.registroEntradas.aumentarVentas();
            this.registroEntradas.aumentarRecaudado(precio);
        } else {
            throw new ZonaException("Ya hay una entrada para esa fila y asiento!");
        }

        return entrada;
    }

    /**
     * Comprueba que haya una entrada en la fila y asiento definidos
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return true si hay una entrada en esa fila y asiento
     * @throws ZonaException si la fila/asiento está fuera del rango permitido
     */

    public boolean tieneEntrada(int fila, int asiento) throws ZonaException {
        comprobarRangos(fila, asiento);
        return this.entradas[fila][asiento] != null;
    }

    /**
     * Busca la entrada de la fila y asiento definidos
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la Entrada en la fila y asiento definido
     * @throws IllegalArgumentException si la fila o el asiento está fuera del rango valido
     */

    public Entrada buscarEntrada(int fila, int asiento) throws ZonaException {
        comprobarRangos(fila, asiento);
        return this.entradas[fila][asiento];
    }

    /**
     * Borra la entrada de la fila y asiento definidos
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la Entrada borrada en la fila y asiento definido
     * @throws ZonaException si la fila o el asiento está fuera del rango valido
     */

    public Entrada borrarEntrada(int fila, int asiento) throws ZonaException {
        comprobarRangos(fila, asiento);

        Entrada entrada = this.entradas[fila][asiento];
        this.entradas[fila][asiento] = null;

        if (entrada != null) {
            this.registroEntradas.disminuirVentas();
            this.registroEntradas.disminuirRecudado(entrada.getPrecio());
        }

        return entrada;
    }
}
