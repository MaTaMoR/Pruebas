package me.matamor.pruebas.tema10.ejercicio10;

import me.matamor.pruebas.lib.Condicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empresa {

    private final Map<String, Empleado> empleados;

    public Empresa() {
        this.empleados = new HashMap<>();
    }

    public void registrarEmpleado(Empleado empleado) throws EmpresaException {
        if (this.empleados.containsKey(empleado.getDocumento())) {
            throw new EmpresaException("Ya hay un empleado registrado con ese documento!");
        }

        this.empleados.put(empleado.getDocumento(), empleado);
    }

    public Empleado buscarEmpleado(String documento) {
        return this.empleados.get(documento);
    }

    public Empleado eliminarEmpleado(String documento) {
        return this.empleados.remove(documento);
    }

    public List<Empleado> buscarEmpleados(Condicion<Empleado> condicion) {
        List<Empleado> empleados = new ArrayList<>();

        for (Empleado empleado : this.empleados.values()) {
            if (condicion.comprobar(empleado)) {
                empleados.add(empleado);
            }
        }

        return empleados;
    }

    public Map<Empleado, List<Hijo>> buscarHijos(Condicion<Hijo> condicion) {
        Map<Empleado, List<Hijo>> resultado = new HashMap<>();

        for (Empleado empleado : this.empleados.values()) {
            for (Hijo hijo : empleado.hijos()) {
                if (condicion.comprobar(hijo)) {
                    resultado.computeIfAbsent(empleado, k -> new ArrayList<>()).add(hijo);
                }
            }
        }

        return resultado;
    }
}
