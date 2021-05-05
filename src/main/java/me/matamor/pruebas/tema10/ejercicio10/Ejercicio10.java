package me.matamor.pruebas.tema10.ejercicio10;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.Date;

public class Ejercicio10 extends MenuConsola {

    private final Empresa empresa;
    private final ConsultasEmpresa consultasEmpresa;

    public Ejercicio10() {
        super("GESTIÓN EMPLEADOS");

        this.empresa = new Empresa();

        registrarOpcion(Constantes.NUEVO_EMPLEADO, new OpcionSimple("Nuevo Empleado", "Registra un nuevo empleado", this::nuevoEmpleado));
        registrarOpcion(Constantes.NUEVO_HIJO, new OpcionSimple("Nuevo Hijo", "Registra un hijo a un empleado", this::nuevoHijo));
        registrarOpcion(Constantes.MODIFICAR_SUELDO, new OpcionSimple("Modificar Sueldo", "Modifica el sueldo de un empleado", this::modificarSueldo));
        registrarOpcion(Constantes.BORRAR_EMPLEADO, new OpcionSimple("Borrar Empleado", "Borra un empleado de la empresa", this::borrarEmpleado));
        registrarOpcion(Constantes.BORRAR_HIJO, new OpcionSimple("Borrar Hijo", "Borra un hijo de un empleado", this::borrarHijo));
        registrarOpcion(Constantes.CONSULTAS, new OpcionSimple("Consultas", "Consulta información de la empresa", this::consultas));

        this.consultasEmpresa = new ConsultasEmpresa(this.empresa);
    }

    private void nuevoEmpleado() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.buscarEmpleado(documento);
        if (empleado == null) {
            crearEmpleado(documento);
        } else {
            System.out.printf("El empleado '%s' ya está registrado en la empresa!\n", documento);
        }
    }

    private void crearEmpleado(String documento) {
        System.out.println("Introduce el nombre del nuevo empleado:");
        String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

        System.out.println("Introduce los apellidos del nuevo empleado:");
        String apellidos = Input.leerLinea(Constantes.APELLIDOS_MIN, -1);

        System.out.println("Introduce la fecha de nacimiento del nuevo empleado:");
        Date fechaNacimiento = Input.leerDate();

        System.out.println("Introduce el salario del nuevo empleado:");
        double salario = Input.leerDouble(Constantes.SALARIO_MIN, -1);

        Empleado empleado = new Empleado(documento, nombre, apellidos, fechaNacimiento, salario);
        this.empresa.registrarEmpleado(empleado);

        System.out.println("Se ha registrado correctamente el nuevo empleado!");
    }

    private void nuevoHijo() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.buscarEmpleado(documento);
        if (empleado == null) {
            System.out.printf("No existe ningún empleado con el documento '%s'!\n", documento);
        } else {
            crearHijo(empleado);
        }
    }

    private void crearHijo(Empleado empleado) {
        System.out.println("Introduce el nombre del nuevo hijo:");
        String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

        Hijo hijo = empleado.buscarHijo(nombre);
        if (hijo == null) {
            System.out.println("Introduce la edad del nuevo hijo:");
            int edad = Input.leerInt(Constantes.EDAD_MIN, -1);

            hijo = new Hijo(nombre, edad);
            empleado.registrarHijo(hijo);

            System.out.println("Se ha registrado el nuevo hijo correctamente!");
        } else {
            System.out.printf("El empleado '%s' ya tiene un hijo con el nombre '%s'!", empleado.getDocumento(), nombre);
        }
    }

    private void modificarSueldo() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.buscarEmpleado(documento);
        if (empleado == null) {
            System.out.printf("No existe ningún empleado con el documento '%s'!\n", documento);
        } else {
            System.out.println("Introduce el nuevo sueldo del empleado:");
            double sueldo = Input.leerDouble(Constantes.SALARIO_MIN, -1);

            empleado.setSueldo(sueldo);
            System.out.println("Se ha actualizado el sueldo del empleado correctamente!");
        }
    }

    private void borrarEmpleado() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.eliminarEmpleado(documento);
        if (empleado == null) {
            System.out.printf("No existe ningún empleado con el documento '%s'!\n", documento);
        } else {
            System.out.println("Se ha eliminado el empleado correctamente!");
        }
    }

    private void borrarHijo() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.buscarEmpleado(documento);
        if (empleado == null) {
            System.out.printf("No existe ningún empleado con el documento '%s'!\n", documento);
        } else {
            System.out.println("Introduce el nombre del hijo a eliminar:");
            String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

            if (empleado.eliminarHijo(nombre)) {
                System.out.println("Se ha eliminado el hijo del empleado correctamente!");
            } else {
                System.out.printf("El empleado '%s' no tiene ningún hijo con el nombre '%s'!\n", documento, nombre);
            }
        }
    }

    private void consultas() {
        this.consultasEmpresa.ejecutar();
    }

    public static void main(String[] args) {
        Ejercicio10 ejercicio10 = new Ejercicio10();
        ejercicio10.ejecutar();
    }
}
