package me.matamor.pruebas.tema10.ejercicio10;

import me.matamor.pruebas.lib.Condicion;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.List;
import java.util.Map;

public class ConsultasEmpresa extends MenuConsola {

    private final Empresa empresa;

    public ConsultasEmpresa(Empresa empresa) {
        super("Consultas");

        this.empresa = empresa;

        registrarOpcion(Constantes.BUSCAR_NIF, new OpcionSimple("Buscar por NIF", "Busca un empleado por su NIF", this::buscarNif));
        registrarOpcion(Constantes.BUSCAR_NOMBRE, new OpcionSimple("Buscar por nombre", "Busca un empleado por su nombre", this::buscarNombre));
        registrarOpcion(Constantes.BUSCAR_RANGO_EDAD, new OpcionSimple("Buscar por edad", "Busca empleados entre un rango de edad", this::buscarRangoEdad));
        registrarOpcion(Constantes.BUSCAR_RANGO_SALARIO, new OpcionSimple("Buscar por sueldo", "Busca empleados entre un rango de salario", this::buscarRangoSalario));
        registrarOpcion(Constantes.BUSCAR_HIJOS_MAYORES, new OpcionSimple("Buscar hijos", "Busca los hijos de empleados mayores de edad", this::buscarHijosMayores));
    }

    private void buscarNif() {
        System.out.println("Introduce el documento del empleado:");
        String documento = Input.leerLinea(Constantes.DOCUMENTO_SIZE, Constantes.DOCUMENTO_SIZE);

        Empleado empleado = this.empresa.buscarEmpleado(documento);
        if (empleado == null) {
            System.out.printf("No existe ningún empleado con el documento '%s'!\n", documento);
        } else {
            System.out.println(empleado);
        }
    }

    private void buscarNombre() {
        System.out.println("Introduce el nombre del empleado:");
        String nombre = Input.leerLinea(Constantes.NOMBRE_MIN, -1);

        List<Empleado> empleados = this.empresa.buscarEmpleados(empleado -> empleado.getNombre().equalsIgnoreCase(nombre));
        if (empleados.isEmpty()) {
            System.out.printf("No existe ningún empleado con el nombre '%s'!\n", nombre);
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("---------------");
                System.out.println(empleado);
            }
        }
    }

    private void buscarRangoEdad() {
        System.out.println("Introduce le edad minima del rango:");
        int edadMin = Input.leerInt(Constantes.EDAD_MIN, -1);

        System.out.println("Introduce la edad máxima del rango:");
        int edadMax = Input.leerInt(Constantes.EDAD_MIN, -1);

        List<Empleado> empleados = this.empresa.buscarEmpleados(empleado -> {
            int edad = empleado.edad();
            return edad >= edadMin && edad <= edadMax;
        });

        if (edadMin > edadMax) {
            System.out.println("La edad minima no puede ser mayor a la edad máxima!");
        } else {
            if (empleados.isEmpty()) {
                System.out.printf("No existe ningún empleado dentro del rango de edad %d-%d!\n", edadMin, edadMax);
            } else {
                for (Empleado empleado : empleados) {
                    System.out.println("---------------");
                    System.out.println(empleado);
                }
            }
        }
    }

    private void buscarRangoSalario() {
        System.out.println("Introduce el salario minimo del rango:");
        double salarioMin = Input.leerDouble(Constantes.SALARIO_MIN, -1);

        System.out.println("Introduce el salario minimo del rango:");
        double salarioMax = Input.leerDouble(Constantes.SALARIO_MIN, -1);

        List<Empleado> empleados = this.empresa.buscarEmpleados(empleado -> {
            double salario = empleado.getSueldo();
            return salario >= salarioMin && salario <= salarioMax;
        });

        if (salarioMin > salarioMax) {
            System.out.println("El salario minimo no puede ser mayor al salario máximo!");
        } else {
            if (empleados.isEmpty()) {
                System.out.printf("No existe ningún empleado dentro del rango de salario %.2f-%.2f!\n", salarioMin, salarioMax);
            } else {
                for (Empleado empleado : empleados) {
                    System.out.println("---------------");
                    System.out.println(empleado);
                }
            }
        }
    }

    private void buscarHijosMayores() {
        Map<Empleado, List<Hijo>> resultado = this.empresa.buscarHijos(hijo -> hijo.getEdad() >= Constantes.MAYOR_EDAD);
        if (resultado.isEmpty()) {
            System.out.println("No hay ningún hijo registrado mayor de edad!");
        } else {
            for (Map.Entry<Empleado, List<Hijo>> entry : resultado.entrySet()) {
                System.out.println("Empleado: " + entry.getKey().getDocumento());

                for (Hijo hijo : entry.getValue()) {
                    System.out.println("---------------");
                    System.out.println(hijo);
                }
            }
        }
    }
}
