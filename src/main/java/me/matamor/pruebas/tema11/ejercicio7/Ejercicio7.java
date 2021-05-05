package me.matamor.pruebas.tema11.ejercicio7;

import me.matamor.pruebas.lib.Fecha;
import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Equipo;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Estadio;
import me.matamor.pruebas.tema11.ejercicio7.estadio.TipoPartido;
import me.matamor.pruebas.tema11.ejercicio7.menus.MenuPrincipal;

public class Ejercicio7 {

    private void ejecutar() {
        Estadio estadio = new Estadio(Constantes.ZONAS, Constantes.FILAS, Constantes.ASIENTOS);
        estadio.registrarPartido(Fecha.parse(23, 4, 2021), TipoPartido.MEDIA_AFLUENCIA, new Equipo("Barcelona"), new Equipo("Real Madrid"));

        MenuPrincipal menuPrincipal = new MenuPrincipal(estadio);
        menuPrincipal.ejecutar();
    }

    public static void main(String[] args) {
        Ejercicio7 ejercicio7 = new Ejercicio7();
        ejercicio7.ejecutar();
    }
}
