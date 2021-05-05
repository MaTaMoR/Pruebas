package me.matamor.pruebas.tema8.ejercicio6;

import me.matamor.pruebas.lib.Input;

import java.util.Date;

public class Bicicleta {

    private int referencia;
    private String marca;
    private String modelo;
    private double peso;
    private double pulgadasRuedas;
    private boolean motor;
    private Date fechaFabricacion;
    private double precio;
    private int stock;

    public Bicicleta(int referencia, String marca, String modelo, double peso, double pulgadasRuedas, boolean motor,
                     Date fechaFabricacion, double precio, int stock) {

        this.referencia = referencia;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.pulgadasRuedas = pulgadasRuedas;
        this.motor = motor;
        this.fechaFabricacion = fechaFabricacion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPulgadasRuedas() {
        return pulgadasRuedas;
    }

    public void setPulgadasRuedas(double pulgadasRuedas) {
        this.pulgadasRuedas = pulgadasRuedas;
    }

    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void addStock() {
        this.stock++;
    }

    public void removeStock() {
        this.stock--;
    }

    @Override
    public String toString() {
        return  "Marca: " + this.marca + "\n" +
                "Modelo: " + this.modelo + "\n" +
                "Peso: " + this.peso + "\n" +
                "Pulgadas ruedas: " + this.pulgadasRuedas + "\n" +
                "Motor: " + (this.motor ? "sí" : "no") + "\n" +
                "Fecha fabricación: " + Input.dateFormat.format(this.fechaFabricacion) + "\n" +
                "Precio: " + this.precio + "\n" +
                "Stock: " + this.stock + "\n";
    }
}
