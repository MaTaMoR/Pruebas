package me.matamor.pruebas.tema11.ejercicio6.menu;

import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Plataforma;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Videojuego;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.*;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Multimedia;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Pelicula;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuListados extends MenuConsola {

    private final Videoclub videoclub;

    public MenuListados(Videoclub videoclub) {
        super("Listados");

        this.videoclub = videoclub;

        registrarOpcion(Constantes.LISTADO_MULTIMEDIA, new OpcionSimple("Listado multimedia", "Listado de todo el registro multimedia", this::listadoMultimedia));
        registrarOpcion(Constantes.LISTADO_PELICULAS, new OpcionSimple("Listado peliculas", "Listado de todas las películas", this::listadoPeliculas));
        registrarOpcion(Constantes.LISTADO_VIDEOJUEGOS, new OpcionSimple("Listado videojuegos", "Listado de todos los videojuegos", this::listadoVideojuegos));
        registrarOpcion(Constantes.LISTADO_HISTORICO_SOCIO, new OpcionSimple("Historico de alquileres", "Historico de alquiler de un socio", this::listadoRegistrosSocio));
        registrarOpcion(Constantes.LISTADO_SOCIO_ACTIVO, new OpcionSimple("Listado de alquileres activos", "Listado de alquileres activos", this::listadoRegistrosActivoSocio));
        registrarOpcion(Constantes.LISTADO_ALQUILERES_CADUCADOS, new OpcionSimple("Listado de alquileres caducados", "Listado de alquileres caducados", this::listadoSociosRecargosPendientes));
        registrarOpcion(Constantes.LISTADO_MULTIMEDIA_PERSONA, new OpcionSimple("Listado multimedia de persona", "Listado de las relaciones multimedia de persona", this::buscarMultimediaPorPersona));
        registrarOpcion(Constantes.LISTADO_VIDEOJUEGOS_PLATAFORMA, new OpcionSimple("Listado videojuegos de plataforma", "Listado de videojuegos de una plataforma", this::listadoVideojuegosDePlataforma));
        registrarOpcion(Constantes.LISTADO_SOCIOS_POBLACION, new OpcionSimple("Listado socios de poblacion", "Listado de socios de una poblacion", this::listadoSociosDePoblacion));
    }

    private void listadoMultimedia() {
        Collection<Multimedia> listado = this.videoclub.registrosMultimedia();
        if (listado.isEmpty()) {
            System.out.println("No hay registros multimedia!");
        } else {
            System.out.println("Registros: ");

            for (Multimedia multimedia : listado) {
                System.out.println("- " + multimedia);
            }
        }
    }

    private void listadoPeliculas() {
        List<Multimedia> listado = this.videoclub.buscarMultimedias(e -> e instanceof Pelicula);
        listado.sort(Comparator.comparing(Multimedia::getTitulo));

        if (listado.isEmpty()) {
            System.out.println("No hay registros de peliculas!");
        } else {
            System.out.println("Registros: ");

            for (Multimedia multimedia : listado) {
                System.out.println("- " + multimedia);
            }
        }
    }

    private void listadoVideojuegos() {
        List<Multimedia> listado = this.videoclub.buscarMultimedias(e -> e instanceof Pelicula);
        listado.sort(Comparator.comparing(Multimedia::getAnyo));

        if (listado.isEmpty()) {
            System.out.println("No hay registros de videojuegos!");
        } else {
            System.out.println("Registros: ");

            for (Multimedia multimedia : listado) {
                System.out.println("- " + multimedia);
            }
        }
    }

    private void listadoRegistrosSocio() {
        try {
            System.out.println("Introduce el nif del socio: ");
            String nif = Input.leerLinea(Constantes.NIF_MIN, Constantes.NIF_MAX);

            Socio socio = this.videoclub.buscarSocio(nif);
            if (socio == null) {
                System.out.println("No hay ningún socio registrado con el nif: " + nif);
            } else {
                List<RegistroAlquiler> registroAlquileres = socio.registroAlquileres();
                if (registroAlquileres.isEmpty()) {
                    System.out.println("Este socio no ha alquilado nada todavía!");
                } else {
                    registroAlquileres.sort(Comparator.comparing(RegistroAlquiler::getFechaAlquiler));
                    System.out.println("Registros:");

                    for (RegistroAlquiler registroAlquiler : registroAlquileres) {
                        System.out.println("- " + registroAlquiler);
                    }
                }
            }
        } catch (CancelException e) {
            System.out.println("Búsqueda de registros de socio cancelada!");
        }
    }

    private void listadoRegistrosActivoSocio() {
        try {
            System.out.println("Introduce el nif del socio: ");
            String nif = Input.leerLinea(Constantes.NIF_MIN, Constantes.NIF_MAX);

            Socio socio = this.videoclub.buscarSocio(nif);
            if (socio == null) {
                System.out.println("No hay ningún socio registrado con el nif: " + nif);
            } else {
                List<RegistroAlquiler> registroAlquileres = socio.registroAlquileres().stream().filter(e -> !e.pagado()).collect(Collectors.toList());
                if (registroAlquileres.isEmpty()) {
                    System.out.println("Este socio no tiene ningún alquiler activo!");
                } else {
                    System.out.println("Registros:");

                    for (RegistroAlquiler registroAlquiler : registroAlquileres) {
                        System.out.println("- " + registroAlquiler);
                    }
                }
            }
        } catch (CancelException e) {
            System.out.println("Búsqueda de registros de socio cancelada!");
        }
    }

    private void listadoSociosRecargosPendientes() {
        List<Socio> socios = this.videoclub.buscarSocios(
                socio -> socio.registroAlquileres().stream().anyMatch(RegistroAlquiler::caducado));

        if (socios.isEmpty()) {
            System.out.println("No hay socios con alquileres caducados!");
        } else {
            System.out.println("Registros:");

            for (Socio socio : socios) {
                System.out.println("- " + socio);
                System.out.println(" - Alquileres pendientes: ");

                socio.registroAlquileres().stream().filter(RegistroAlquiler::caducado).forEach(e -> System.out.println("  - " + e));
            }
        }
    }

    private void buscarMultimediaPorPersona() {
        System.out.println("Escribe el nombre de la persona: ");
        String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

        System.out.println("Escribe los apellidos de la persona: ");
        String apellidos = Input.leerLinea(Constantes.APELLIDO_MIN, -1);

        Persona persona = this.videoclub.buscarPersona(nombre, apellidos);
        if (persona == null) {
            System.out.printf("No hay ninguna persona con el nombre %s %s!\n", nombre, apellidos);
        } else {
            Map<Relacion, List<Multimedia>> relaciones = persona.relaciones();
            if (relaciones.isEmpty()) {
                System.out.println("Esta persona no tiene ninguna relación!");
            } else {
                for (Map.Entry<Relacion, List<Multimedia>> entry : relaciones.entrySet()) {
                    System.out.println(entry.getKey() + ":");

                    for (Multimedia multimedia : entry.getValue()) {
                        System.out.println("- " + multimedia);
                    }
                }
            }
        }
    }

    private void listadoVideojuegosDePlataforma() {
        System.out.println("Escribe el nombre de la plataforma: ");
        String nombrePlataforma = Input.leerLinea(Constantes.PLATAFORMA_MIN, -1);

        Plataforma plataforma = this.videoclub.buscarPlataforma(nombrePlataforma);
        if (plataforma == null) {
            System.out.println("No hay ninguna plataforma registrada con el nombre: " + nombrePlataforma);
        } else {
            if (plataforma.videojuegos().isEmpty()) {
                System.out.println("Esta plataforma no tiene ningún videojuego registrado!");
            } else {
                System.out.println(plataforma.getNombre() + ":");

                for (Videojuego videojuego : plataforma.videojuegos()) {
                    System.out.println("- " + videojuego);
                }
            }
        }
    }

    private void listadoSociosDePoblacion() {
        System.out.println("Escribe el nombre de la población: ");
        String nombrePoblacion = Input.leerLinea(Constantes.POBLACION_MIN, -1);

        Poblacion poblacion = this.videoclub.buscarPoblacion(nombrePoblacion);
        if (poblacion == null) {
            System.out.println("No hay ninguna población registrada con el nombre: " + nombrePoblacion);
        } else {
            if (poblacion.socios().isEmpty()) {
                System.out.println("Esta población no tiene ningún socio registrado!");
            } else {
                System.out.println(poblacion.getNombre() + ":");

                for (Socio socio : poblacion.socios()) {
                    System.out.println("- " + socio);
                }
            }
        }
    }
}
