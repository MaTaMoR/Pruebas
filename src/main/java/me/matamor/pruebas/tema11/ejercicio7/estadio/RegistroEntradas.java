package me.matamor.pruebas.tema11.ejercicio7.estadio;

import me.matamor.pruebas.lib.Randomizer;
import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.entradas.Entrada;
import me.matamor.pruebas.tema11.ejercicio7.exceptions.RegistroException;

public class RegistroEntradas {

    private final Partido partido;

    private final Zona[] zonas;

    private int contadorLoteria;
    private int contadorVentas;
    private double totalRecaudado;

    public RegistroEntradas(Partido partido) {
        this.partido = partido;

        int zonas = partido.getEstadio().getZonas();
        int filas = partido.getEstadio().getFilas();
        int asientos = partido.getEstadio().getAsientos();

        this.zonas = new Zona[zonas];

        for (int zona = 0; zonas > zona; zona++) {
            //No se explica bien como
            int random = Randomizer.randomInt(0, 100);
            boolean vip = random < Constantes.PROBABILIDAD_VIP;

            this.zonas[zona] = new Zona(this, zona, filas, asientos, vip);
        }

        this.contadorLoteria = 0;
        this.contadorVentas = 0;
        this.totalRecaudado = 0;
    }

    /**
     * @return el partido de el registro de entradas
     */

    public Partido getPartido() {
        return this.partido;
    }

    /**
     * @return las zonas del estadio
     */

    public Zona[] zonas() {
        return this.zonas.clone();
    }

    /**
     * @return el contador de sorteos
     */

    public int getContadorSorteo() {
        return this.contadorLoteria;
    }

    /**
     * @return genera un nuvo número de sorteo
     */

    protected int nuevoNumeroSorteo() {
        return this.contadorLoteria++;
    }

    /**
     * @return devuelve el contador de ventas
     */

    public int getContadorVentas() {
        return this.contadorVentas;
    }

    /**
     * aumenta la cantidad de ventas
     */

    protected void aumentarVentas() {
        this.contadorVentas++;
    }

    /**
     * disminuye la cantidad de ventas
     */

    protected void disminuirVentas() {
        this.contadorVentas--;
    }

    /**
     * @return el total recaudado con el partido
     */

    public double getTotalRecaudado() {
        return this.totalRecaudado;
    }

    /**
     * Aumenta el total recaudado
     * @param cantidad la cantidad a aumentar
     */

    protected void aumentarRecaudado(double cantidad) {
        this.totalRecaudado += cantidad;
    }

    /**
     * Disminuye el total recaudado
     * @param cantidad la cantidad a disminuir
     */

    protected void disminuirRecudado(double cantidad) {
        this.totalRecaudado -= cantidad;
    }

    /**
     * Comprueba que la id de la zona este dentro del rango valido
     * @param zona la id de la zona
     * @throws RegistroException si la id de la zona está fuera del rango
     */

    private void comprobarRango(int zona) throws RegistroException {
        if (zona < 0 || zona > this.zonas.length) {
            throw new RegistroException("La zona está fuera del rango valido!");
        }
    }

    /**
     * Crea una entrada para la zona, fila y asiento definidos
     * @param zona la zona de la entrada
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la Entrada ya creada
     * @throws RegistroException si la zona, fila o asiento estan fuera de rango
     */

    public Entrada crearEntrada(int zona, int fila, int asiento) throws RegistroException {
        comprobarRango(zona);
        return this.zonas[zona].crearEntrada(fila, asiento);
    }

    /**
     * Comprueba si la entrada existe en la zona, fila y asiento definidos
     * @param zona la zona de la entrada
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return true si la entrada existe
     * @throws RegistroException si la zona, fila o asiento estan fuera de rango
     */

    public boolean tieneEntrada(int zona, int fila, int asiento) throws RegistroException {
        comprobarRango(zona);
        return this.zonas[zona].tieneEntrada(fila, asiento);
    }

    /**
     * Busca una entrada en la zona, fila y asiento definidos
     * @param zona la zona de la entrada
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la Entrada encontrada
     * @throws RegistroException si la zona, fila o asiento estan fuera de rango
     */


    public Entrada buscarEntrada(int zona, int fila, int asiento) throws RegistroException {
        comprobarRango(zona);
        return this.zonas[zona].buscarEntrada(fila, asiento);
    }

    /**
     * Borra la entrada de la zona, fila y asiento definidos
     * @param zona la zona de la entrada
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la Entrada borrada
     * @throws RegistroException si la zona, fila o asiento estan fuera de rango
     */

    public Entrada borrarEntrada(int zona, int fila, int asiento) throws RegistroException {
        comprobarRango(zona);
        return this.zonas[zona].borrarEntrada(fila, asiento);
    }
}
