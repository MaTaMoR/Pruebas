package me.matamor.pruebas.tema10.ejercicio11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Escuela {

    private final Identificadores identificadores;

    private final Map<Integer, Aula> aulas = new HashMap<>();
    private final Map<String, Profesor> profesores = new HashMap<>();
    private final Map<Integer, Grupo> grupos = new HashMap<>();
    private final Map<Integer, Asignatura> asignaturas = new HashMap<>();
    private final Map<Integer, Alumno> alumnos = new HashMap<>();

    public Escuela() {
        this.identificadores = new Identificadores();
    }

    public Aula registrarAula(double metrosCuadrados) {
        int identificador = this.identificadores.nuevoIdentificadorAulas();
        Aula aula = new Aula(identificador, metrosCuadrados);

        this.aulas.put(identificador, aula);

        return aula;
    }

    public Profesor registrarProfesor(String dni, String nombre, double sueldo) throws EscuelaException {
        if (this.profesores.containsKey(dni)) {
            throw new EscuelaException("Ya hay un profesor registrado con el DNI '" + dni + "'!");
        }

        Profesor profesor = new Profesor(dni, nombre, sueldo);
        this.profesores.put(dni, profesor);

        return profesor;
    }

    public Grupo registrarGrupo(String nombre, int identificadorAula) throws EscuelaException {
        Aula aula = buscarAula(identificadorAula);
        if (aula == null) {
            throw new EscuelaException("No existe ninguna aula con el identificador '" + identificadorAula + "'!");
        }

        //Es posible que no se quiera esta condición
        if (buscarGrupos(grupo -> grupo.getNombre().equalsIgnoreCase(nombre)).size() > 0) {
            throw new EscuelaException("Ya existe un grupo con el nombre '" + nombre + "'!");
        }

        int identificador = this.identificadores.nuevoIdentificadorGrupos();
        Grupo grupo = new Grupo(identificador, nombre, aula);

        this.grupos.put(identificador, grupo);

        return grupo;
    }

    public Grupo registrarGrupo(String nombre, Aula aula) throws EscuelaException {
        if (aula == null) {
            throw new EscuelaException("El aula no puede ser null!");
        }

        if (!this.aulas.containsKey(aula.getIdentificador())) {
            throw new EscuelaException("El aula con el identificador '" + aula.getIdentificador() + "' no esta registrada!");
        }

        //Es posible que no se quiera esta condición
        if (buscarGrupos(grupo -> grupo.getNombre().equalsIgnoreCase(nombre)).size() > 0) {
            throw new EscuelaException("Ya existe un grupo con el nombre '" + nombre + "'!");
        }

        int identificador = this.identificadores.nuevoIdentificadorGrupos();
        Grupo grupo = new Grupo(identificador, nombre, aula);

        this.grupos.put(identificador, grupo);

        return grupo;
    }

    public Asignatura registrarAsignatura(String nombre, String dniProfesor) throws EscuelaException {
        Profesor profesor = buscarProfesor(dniProfesor);
        if (profesor == null) {
            throw new EscuelaException("No existe ningún profesor con el DNI '" + dniProfesor + "'!");
        }

        //Es posible que no se quiera esta condición
        if (buscarAsignaturas(asignatura -> asignatura.getNombre().equalsIgnoreCase(nombre)).size() > 0) {
            throw new EscuelaException("Ya existe una asignatura con el nombre '" + nombre + "'!");
        }

        int identificador = this.identificadores.nuevoIdentificadorAsignaturas();
        Asignatura asignatura = new Asignatura(identificador, nombre, profesor);

        this.asignaturas.put(identificador, asignatura);

        return asignatura;
    }

    public Asignatura registrarAsignatura(String nombre, Profesor profesor) throws EscuelaException  {
        if (profesor == null) {
            throw new EscuelaException("Profesor no puede ser null!");
        }

        if (!this.profesores.containsKey(profesor.getDni())) {
            throw new EscuelaException("El profesor con el dni '" + profesor.getDni() + "' no esta registrado!");
        }

        //Es posible que no se quiera esta condición
        if (buscarAsignaturas(asignatura -> asignatura.getNombre().equalsIgnoreCase(nombre)).size() > 0) {
            throw new EscuelaException("Ya existe una asignatura con el nombre '" + nombre + "'!");
        }

        int identificador = this.identificadores.nuevoIdentificadorAsignaturas();
        Asignatura asignatura = new Asignatura(identificador, nombre, profesor);

        this.asignaturas.put(identificador, asignatura);

        return asignatura;
    }

    public Alumno registrarAlumno(String nombre, int identificadorGrupo, List<Integer> identificadoresAsignaturas) throws EscuelaException {
        Grupo grupo = buscarGrupo(identificadorGrupo);
        if (grupo == null) {
            throw new EscuelaException("No existe ningún grupo con el identificador '" + identificadorGrupo + "'!");
        }

        //Es posible que no se quiera esta condición
        List<Asignatura> asignaturas = buscarAsignaturas(asignatura -> identificadoresAsignaturas.contains(asignatura.getIdentificador()));
        if (asignaturas.isEmpty()) {
            throw new EscuelaException("No se puede registrar un alumno sin asignaturas!");
        }

        int identificador = this.identificadores.nuevoIdentificadorAlumno();
        Alumno alumno = new Alumno(identificador, nombre, grupo, asignaturas);

        this.alumnos.put(identificador, alumno);

        return alumno;
    }

    public Alumno registrarAlumno(String nombre, Grupo grupo, List<Asignatura> asignaturas) throws EscuelaException {
        if (grupo == null) {
            throw new EscuelaException("El grupo no puede ser null!");
        }

        //Es posible que no se quiera esta condición
        if (asignaturas == null || asignaturas.isEmpty()) {
            throw new EscuelaException("No se puede registrar un alumno sin asignaturas!");
        }

        for (Asignatura asignatura : asignaturas) {
            if (!this.asignaturas.containsKey(asignatura.getIdentificador())) {
                throw new EscuelaException("La asignatura con el identificador '" + asignatura.getIdentificador() + "' no esta registrada!");
            }
        }

        int identificador = this.identificadores.nuevoIdentificadorAlumno();
        Alumno alumno = new Alumno(identificador, nombre, grupo, asignaturas);

        this.alumnos.put(identificador, alumno);

        return alumno;
    }

    public Aula buscarAula(int identificador) {
        return this.aulas.get(identificador);
    }

    public List<Aula> buscarAulas(Condicion<Aula> condicion) {
        return buscar(this.aulas, condicion);
    }

    public List<Aula> aulas() {
        return new ArrayList<>(this.aulas.values());
    }

    public Profesor buscarProfesor(String dni) {
        return this.profesores.get(dni);
    }

    public List<Profesor> buscarProfesores(Condicion<Profesor> condicion) {
        return buscar(this.profesores, condicion);
    }

    public List<Profesor> profesores() {
        return new ArrayList<>(this.profesores.values());
    }

    public Grupo buscarGrupo(int identificador) {
        return this.grupos.get(identificador);
    }

    public List<Grupo> buscarGrupos(Condicion<Grupo> condicion) {
        return buscar(this.grupos, condicion);
    }

    public List<Grupo> grupos() {
        return new ArrayList<>(this.grupos.values());
    }

    public Asignatura buscarAsignatura(int identificador) {
        return this.asignaturas.get(identificador);
    }

    public List<Asignatura> buscarAsignaturas(Condicion<Asignatura> condicion) {
        return buscar(this.asignaturas, condicion);
    }

    public List<Asignatura> asignaturas() {
        return new ArrayList<>(this.asignaturas.values());
    }

    public Alumno buscarAlumno(int identificador) {
        return this.alumnos.get(identificador);
    }

    public List<Alumno> buscarAlumnos(Condicion<Alumno> condicion) {
        return buscar(this.alumnos, condicion);
    }

    public List<Alumno> alumnos() {
        return new ArrayList<>(this.alumnos.values());
    }

    private <T> List<T> buscar(Map<?, T> valores, Condicion<T> condicion) {
        List<T> resultado = new ArrayList<>();

        for (T valor : valores.values()) {
            if (condicion.validar(valor)) {
                resultado.add(valor);
            }
        }

        return resultado;
    }
}
