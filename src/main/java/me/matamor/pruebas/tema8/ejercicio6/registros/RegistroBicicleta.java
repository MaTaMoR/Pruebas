package me.matamor.pruebas.tema8.ejercicio6.registros;

import me.matamor.pruebas.tema8.ejercicio6.Bicicleta;

public interface RegistroBicicleta {

    void registrarBicicleta(Bicicleta bicicleta);

    void registrarBicicletas(Bicicleta... bicicletas);

    Bicicleta buscarBicicleta(int referencia);

    Bicicleta[] buscarBicicletas(CondicionBusqueda condicionBusqueda);

    Bicicleta[] bicicletas();
}
