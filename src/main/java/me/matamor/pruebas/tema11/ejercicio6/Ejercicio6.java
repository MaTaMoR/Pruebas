package me.matamor.pruebas.tema11.ejercicio6;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.tema11.ejercicio6.menu.MenuPrincipal;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Formato;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Videoclub;

import java.text.ParseException;

public class Ejercicio6 {

    private final Videoclub videoclub;
    private final MenuPrincipal menuPrincipal;

    public Ejercicio6() {
        this.videoclub = new Videoclub();
        this.menuPrincipal = new MenuPrincipal(this.videoclub);

        this.videoclub.registrarPelicula(
                "Avatar",
                this.videoclub.registrarPersona("James", "Cameron"),
                Formato.BLU_RAY,
                2010,
                162,
                this.videoclub.registrarPersona("Sam", "Worthington"),
                this.videoclub.registrarPersona("Zoe", "Saldaña"));

        this.videoclub.registrarVideojuego(
                "Black Desert",
                this.videoclub.registrarPersona("Santiago", "Gonzalez"),
                Formato.ARCHIVO,
                2015,
                this.videoclub.registrarPlataforma("PC"));

        try {
            this.videoclub.registrarSocio("X8136494Z", "Santiago", "Gonzalez Londoño", Input.dateFormat.parse("21/02/1998"), this.videoclub.registrarPoblacion("Benissa"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void ejecutar() {
        this.menuPrincipal.ejecutar();
    }

    public static void main(String[] args) {
        Ejercicio6 ejercicio6 = new Ejercicio6();
        ejercicio6.ejecutar();
    }
}
