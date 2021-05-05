package me.matamor.pruebas.tema11.ejercicio6.menu;

import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Persona;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Poblacion;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Videoclub;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;
import me.matamor.pruebas.tema11.ejercicio6.exceptions.VideoclubException;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Formato;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Plataforma;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MenuAltas extends MenuConsola {

    private final Videoclub videoclub;

    public MenuAltas(Videoclub videoclub) {
        super("Altas");

        this.videoclub = videoclub;

        registrarOpcion(Constantes.NUEVA_PELICULA, new OpcionSimple("Nueva película", "Registra una nueva película en el sistemas", this::nuevaPelicula));
        registrarOpcion(Constantes.NUEVO_VIDEOJUEGO, new OpcionSimple("Nuevo videojuego", "Registra un nuevo videojuego en el sistemas", this::nuevoVideojuego));
        registrarOpcion(Constantes.NUEVO_SOCIO, new OpcionSimple("Nuevo socio", "Registra un nuevo socio en el sistemas", this::nuevoSocio));
    }

    private void nuevaPelicula() {
        try {
            System.out.println("Escribe el título de la película: ");
            String titulo = Input.leerLinea(Constantes.MULTIMEDIA_TITULO_MIN, -1);

            Persona autor = pedirPersona("Escribe el nombre del autor de la película: ", "Escribe los apellidos del autor de la película: ");

            Formato formato = null;

            do {
                System.out.println("Escrbe el formato de la película: ");
                String nombreFormat = Input.leerLinea(Constantes.FORMATO_MIN, Constantes.FORMATO_MAX);
                try {
                    formato = Formato.valueOf(nombreFormat);
                } catch (IllegalArgumentException e) {
                    System.out.println("El nombre introducido no pertenece a ningún formato: " + nombreFormat);
                }
            } while (formato == null);

            System.out.println("Escribe el año de la película: ");
            int anyo = Input.leerInt(Constantes.ANYO_MIN, Constantes.ANYO_MAX);

            System.out.println("Escribe la duración de la película: ");
            double duracion = Input.leerDouble(Constantes.DURACION_MIN, -1);

            Persona actorPrincipal = pedirPersona("Escribe el nombre del actor principal: ", "Escribe el nombre del actor principal: ");
            Persona actrizPrincipal = pedirPersona("Escribe el nombre de la actriz principal: ", "Escribe los apellidos de la actriz principal: ");

            try {
                this.videoclub.registrarPelicula(titulo, autor, formato, anyo, duracion, actorPrincipal, actrizPrincipal);
                System.out.println("Película registrada correctamente!");
            } catch (VideoclubException e) {
                System.out.println("No se ha podido registrar la película: " + e.getMessage());
            }
        } catch (CancelException e) {
            System.out.println("Registro de pelicula nueva cancelado!");
        }
    }

    private void nuevoVideojuego() {
        try {
            System.out.println("Escribe el título del videojuego: ");
            String titulo = Input.leerLinea(Constantes.MULTIMEDIA_TITULO_MIN, -1);

            Persona autor = pedirPersona("Escribe el nombre del autor del videojuego: ", "Escribe los apellidos del autor del videojuego: ");

            Formato formato = null;

            do {
                System.out.println("Escrbe el formato del videojuego: ");
                String nombreFormat = Input.leerLinea(Constantes.FORMATO_MIN, Constantes.FORMATO_MAX);
                try {
                    formato = Formato.valueOf(nombreFormat);
                } catch (IllegalArgumentException e) {
                    System.out.println("El nombre introducido no pertenece a ningún formato: " + nombreFormat);
                }
            } while (formato == null);

            System.out.println("Escribe el año  del videojuego: ");
            int anyo = Input.leerInt(Constantes.ANYO_MIN, Constantes.ANYO_MAX);

            Plataforma plataforma = pedirPlataforma();

            try {
                this.videoclub.registrarVideojuego(titulo, autor, formato, anyo, plataforma);
                System.out.println("Videojuego registrado correctamente!");
            } catch (VideoclubException e) {
                System.out.println("No se ha podido registrar el videojuego: " + e.getMessage());
            }
        } catch (CancelException e) {
            System.out.println("Registro de videojuego nuevo cancelado!");
        }
    }

    private void nuevoSocio() {
        try {
            System.out.println("Escribe el nif del socio: ");
            String nif = Input.leerLinea(Constantes.NIF_MIN, Constantes.NIF_MAX);

            System.out.println("Escribe el nombre del socio: ");
            String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

            System.out.println("Escribe el apellido del socio: ");
            String apellidos = Input.leerLinea(Constantes.APELLIDO_MIN, -1);

            System.out.println("Escribe la fecha de nacimiento del socio: ");
            Date fechaNacimiento = Input.leerDate(Input.dateFormat);

            if (esMayor(fechaNacimiento)) {
                Poblacion poblacion = pedirPoblacion();

                try {
                    this.videoclub.registrarSocio(nif, nombre, apellidos, fechaNacimiento, poblacion);
                } catch (VideoclubException e) {
                    System.out.println("No se ha podido registrar al socio: " + e.getMessage());
                }
            } else {
                System.out.println("No se puede registrar a un menor de edad!");
            }
        } catch (CancelException e) {
            System.out.println("Registro de socio nuevo cancelado!");
        }
    }

    private boolean esMayor(Date fechaNacimiento) {
        long ahora = System.currentTimeMillis();
        long diferencia = ahora - fechaNacimiento.getTime();

        int dias = (int) TimeUnit.MILLISECONDS.toDays(diferencia);
        int anyos = dias / Constantes.DIAS_ANYO;

        return anyos >= Constantes.EDAD_MIN;
    }

    private Poblacion pedirPoblacion() {
        System.out.println("Escribe el nombre de la población: ");
        String nombrePoblacion = Input.leerLinea(Constantes.POBLACION_MIN, -1);

        Poblacion poblacion = this.videoclub.buscarPoblacion(nombrePoblacion);
        if (poblacion == null) {
            poblacion = this.videoclub.registrarPoblacion(nombrePoblacion);
        }

        return poblacion;
    }

    private Plataforma pedirPlataforma() {
        System.out.println("Escribe la plataforma del videojuego: ");
        String nombrePlataforma = Input.leerLinea(Constantes.PLATAFORMA_MIN, -1);

        Plataforma plataforma = this.videoclub.buscarPlataforma(nombrePlataforma);
        if (plataforma == null) {
            plataforma = this.videoclub.registrarPlataforma(nombrePlataforma);
        }

        return plataforma;
    }

    private Persona pedirPersona(String nombreMensaje, String apellidosMensaje) {
        System.out.println(nombreMensaje);
        String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

        System.out.println(apellidosMensaje);
        String apellidos = Input.leerLinea(Constantes.APELLIDO_MIN, -1);

        Persona persona = this.videoclub.buscarPersona(nombre, apellidos);
        if (persona == null) {
            persona = this.videoclub.registrarPersona(nombre, apellidos);
        }

        return persona;
    }
}
