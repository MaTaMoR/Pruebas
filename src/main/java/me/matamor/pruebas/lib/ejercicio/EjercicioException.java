package me.matamor.pruebas.lib.ejercicio;

public class EjercicioException extends RuntimeException {

    public EjercicioException(String message) {
        super(message);
    }

    public EjercicioException(String message, Exception exception) {
        super(message, exception);
    }
}
