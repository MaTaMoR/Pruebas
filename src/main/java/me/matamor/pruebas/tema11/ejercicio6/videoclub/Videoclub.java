package me.matamor.pruebas.tema11.ejercicio6.videoclub;

import me.matamor.pruebas.lib.Condicion;
import me.matamor.pruebas.lib.registro.Registro;
import me.matamor.pruebas.tema11.ejercicio6.exceptions.VideoclubException;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Videoclub  {

    private final Registro<String, Poblacion> registroPoblaciones;
    private final Registro<String, Persona> registroPersonas;
    private final Registro<String, Plataforma> registroPlataforma;
    private final Registro<String, Multimedia> registroMultimedia;
    private final Registro<String, Socio> registroSocios;

    public Videoclub() {
        this.registroPoblaciones = new Registro<>();
        this.registroPersonas = new Registro<>();
        this.registroPlataforma = new Registro<>();
        this.registroMultimedia = new Registro<>();
        this.registroSocios = new Registro<>();
    }

    /**
     * Busca la Población con el nombre definidio
     * @param nombre el nombre de la población a buscar
     * @return la Población con el nombre definidio
     */

    public Poblacion buscarPoblacion(String nombre) {
        return this.registroPoblaciones.buscarRegistro(valor -> valor.getNombre().equalsIgnoreCase(nombre));
    }

    /**
     * Devuelve el registro que cumpla la condición
     * @param condicion la condición para buscar el registro
     * @return el registro que cumple la condición
     */

    public Poblacion buscarPoblacion(Condicion<Poblacion> condicion) {
        return this.registroPoblaciones.buscarRegistro(condicion);
    }

    /**
     * Devuelve los registros que cumplan la condición
     * @param condicion la condición para buscar los registros
     * @return los registros que cumple la condición
     */

    public List<Poblacion> buscarPoblaciones(Condicion<Poblacion> condicion) {
        return this.registroPoblaciones.buscarRegistros(condicion);
    }

    /**
     * Registros socios
     * @return todos los registros de socios
     */

    public Collection<Poblacion> registrosPoblacion() {
        return this.registroPoblaciones.registros();
    }

    /**
     * Busca la persona con el mismo nombre y apellidos
     * @param nombre el nombre de la persona
     * @param apellidos los apellidos de la persona
     * @return la persona con el nombre y apellidos
     */

    public Persona buscarPersona(String nombre, String apellidos) {
        return this.registroPersonas.buscarRegistro(
                valor -> valor.getNombre().equalsIgnoreCase(nombre) && valor.getApellidos().equalsIgnoreCase(apellidos));
    }

    /**
     * Devuelve el registro que cumpla la condición
     * @param condicion la condición para buscar el registro
     * @return el registro que cumple la condición
     */

    public Persona buscarPersona(Condicion<Persona> condicion) {
        return this.registroPersonas.buscarRegistro(condicion);
    }

    /**
     * Devuelve los registros que cumplan la condición
     * @param condicion la condición para buscar los registros
     * @return los registros que cumple la condición
     */

    public List<Persona> buscarPersonas(Condicion<Persona> condicion) {
        return this.registroPersonas.buscarRegistros(condicion);
    }

    /**
     * Registros socios
     * @return todos los registros de socios
     */

    public Collection<Persona> registrosPersona() {
        return this.registroPersonas.registros();
    }

    /**
     * Busca la Plataforma con el nombre definidio
     * @param nombre el nombre de la población a buscar
     * @return la Plataforma con el nombre definidio
     */

    public Plataforma buscarPlataforma(String nombre) {
        return this.registroPlataforma.buscarRegistro(valor -> valor.getNombre().equalsIgnoreCase(nombre));
    }

    /**
     * Devuelve el registro que cumpla la condición
     * @param condicion la condición para buscar el registro
     * @return el registro que cumple la condición
     */

    public Plataforma buscarPlataforma(Condicion<Plataforma> condicion) {
        return this.registroPlataforma.buscarRegistro(condicion);
    }

    /**
     * Devuelve los registros que cumplan la condición
     * @param condicion la condición para buscar los registros
     * @return los registros que cumple la condición
     */

    public List<Plataforma> buscarPlataformas(Condicion<Plataforma> condicion) {
        return this.registroPlataforma.buscarRegistros(condicion);
    }

    /**
     * Registros socios
     * @return todos los registros de socios
     */

    public Collection<Plataforma> registrosPlataforma() {
        return this.registroPlataforma.registros();
    }

    /**
     * Busca el Socio con el nif definido
     * @param nif el nif que se usa para buscar
     * @return el Socio con el nif definido
     */

    public Socio buscarSocio(String nif) {
        return this.registroSocios.buscarRegistro(valor -> valor.getNif().equalsIgnoreCase(nif));
    }

    /**
     * Devuelve el registro que cumpla la condición
     * @param condicion la condición para buscar el registro
     * @return el registro que cumple la condición
     */

    public Socio buscarSocio(Condicion<Socio> condicion) {
        return this.registroSocios.buscarRegistro(condicion);
    }

    /**
     * Devuelve los registros que cumplan la condición
     * @param condicion la condición para buscar los registros
     * @return los registros que cumple la condición
     */

    public List<Socio> buscarSocios(Condicion<Socio> condicion) {
        return this.registroSocios.buscarRegistros(condicion);
    }

    /**
     * Registros socios
     * @return todos los registros de socios
     */

    public Collection<Socio> registrosSocios() {
        return this.registroSocios.registros();
    }

    public Multimedia buscarMultimedia(String titulo, Persona autor) {
        return this.registroMultimedia.buscarRegistro(
                valor -> valor.getTitulo().equalsIgnoreCase(titulo) && valor.getAutor().equals(autor));
    }

    /**
     * Devuelve el registro que cumpla la condición
     * @param condicion la condición para buscar el registro
     * @return el registro que cumple la condición
     */

    public Multimedia buscarMultimedia(Condicion<Multimedia> condicion) {
        return this.registroMultimedia.buscarRegistro(condicion);
    }

    /**
     * Devuelve los registros que cumplan la condición
     * @param condicion la condición para buscar los registros
     * @return los registros que cumple la condición
     */

    public List<Multimedia> buscarMultimedias(Condicion<Multimedia> condicion) {
        return this.registroMultimedia.buscarRegistros(condicion);
    }

    /**
     * Registros multimedia
     * @return todos los registros multimedia
     */

    public Collection<Multimedia> registrosMultimedia() {
        return this.registroMultimedia.registros();
    }

    /**
     * Registra una nueva población
     * @param nombre el nombre de la población
     * @return la población creada
     * @throws VideoclubException si la población ya existe
     */

    public Poblacion registrarPoblacion(String nombre) throws VideoclubException {
        if (buscarPoblacion(nombre) != null) {
            throw new VideoclubException("Ya hay una población registrada con el nombre: " + nombre);
        }

        Poblacion poblacion = new Poblacion(nombre);

        this.registroPoblaciones.registrar(poblacion);

        return poblacion;
    }

    /**
     * Regista una nueva persona
     * @param nombre el nombre de la persona
     * @param apellidos los apellidos de la persona
     * @return la persona creada
     * @throws VideoclubException si la persona ya existe
     */

    public Persona registrarPersona(String nombre, String apellidos) throws VideoclubException {
        if (buscarPersona(nombre, apellidos) != null) {
            throw new VideoclubException("Ya hay una persoan registrada con esos valores!");
        }

        Persona persona = new Persona(nombre, apellidos);

        this.registroPersonas.registrar(persona);

        return persona;
    }

    /**
     * Registra una nueva película
     * @param titulo el título de la película
     * @param autor el autor de la película
     * @param formato el formato de la película
     * @param anyo el año de la película
     * @param duracion la duración de la película
     * @param actorPrincipal el actor principal de la película
     * @param actrizPrincipal la actriz principal de la película
     * @return la película ya creada
     * @throws VideoclubException si la película ya existe
     */

    public Pelicula registrarPelicula(String titulo, Persona autor, Formato formato, int anyo, double duracion, Persona actorPrincipal, Persona actrizPrincipal) throws VideoclubException {
        if (buscarMultimedia(titulo, autor) != null) {
            throw new VideoclubException("Ya hay una pelicula registrada con esos valores!");
        }

        Pelicula pelicula = new Pelicula(titulo, autor, formato, anyo, duracion, actorPrincipal, actrizPrincipal);

        this.registroMultimedia.registrar(pelicula);

        autor.nuevaRelacion(Relacion.AUTOR, pelicula);
        actorPrincipal.nuevaRelacion(Relacion.ACTOR_PRINCIPAL, pelicula);
        actorPrincipal.nuevaRelacion(Relacion.ACTOR_PRINCIPAL, pelicula);

        return pelicula;
    }

    /**
     * Registra una nueva plataforma
     * @param nombre el nombre de la plataforma
     * @return la plataforma ya creada
     * @throws VideoclubException si la plataforma ya existe
     */

    public Plataforma registrarPlataforma(String nombre) throws VideoclubException {
        if (buscarPlataforma(nombre) != null) {
            throw new VideoclubException("Ya está registrada esa plataforma!");
        }

        Plataforma plataforma = new Plataforma(nombre);

        this.registroPlataforma.registrar(plataforma);

        return plataforma;
    }

    /**
     * Registra un nuevo videojuego
     * @param titulo el título del videojuego
     * @param autor el autor del videojuego
     * @param formato el formato del videojuego
     * @param anyio el año del videojuego
     * @param plataforma la plataforma del videojuego
     * @return el videojuego ya creado
     * @throws VideoclubException si el videojuego ya existe
     */

    public Videojuego registrarVideojuego(String titulo, Persona autor, Formato formato, int anyio, Plataforma plataforma) throws VideoclubException {
        if (buscarMultimedia(titulo, autor) != null) {
            throw new VideoclubException("Ya hay un videojuego registrado con esos valores!");
        }

        Videojuego videojuego = new Videojuego(titulo, autor, formato, anyio, plataforma);

        this.registroMultimedia.registrar(videojuego);

        autor.nuevaRelacion(Relacion.AUTOR, videojuego);
        plataforma.nuevoVideojuego(videojuego);

        return videojuego;
    }

    /**
     * Crea un nuevo socio
     * @param nif el nif del socio
     * @param nombre el nombre del socio
     * @param apellido los apellidos del socio
     * @param fechaNacimiento la fecha de nacimiento del socio
     * @param poblacion la población del socio
     * @return el socio ya creado
     * @throws VideoclubException si el socio ya existe
     */

    public Socio registrarSocio(String nif, String nombre, String apellido, Date fechaNacimiento, Poblacion poblacion) throws VideoclubException {
        if (buscarSocio(nif) != null) {
            throw new VideoclubException("Ya hay un socio con el NIF: " + nif);
        }

        Socio socio = new Socio(nombre, apellido, nif, fechaNacimiento, poblacion);

        this.registroSocios.registrar(socio);
        poblacion.registrarSocio(socio);

        return socio;
    }
}
