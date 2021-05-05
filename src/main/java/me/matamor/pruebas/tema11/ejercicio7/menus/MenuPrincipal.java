package me.matamor.pruebas.tema11.ejercicio7.menus;

import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Fecha;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;
import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Equipo;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Estadio;
import me.matamor.pruebas.tema11.ejercicio7.estadio.TipoPartido;

import java.util.Arrays;

public class MenuPrincipal extends MenuConsola {

    private final Estadio estadio;
    private final MenuEntradas menuEntradas;

    public MenuPrincipal(Estadio estadio) {
        super("Estadio");

        this.estadio = estadio;
        this.menuEntradas = new MenuEntradas(estadio);

        registrarOpcion(Constantes.NUEVO_PARTIDO, new OpcionSimple("Nuevo partido", "Registra un partido nuevo!", this::nuevoPartido));
        registrarOpcion(Constantes.GESTION_ENTRADAS, new OpcionSimple("Gestión entradas", "Gestiona las entradas de un partido!", this.menuEntradas::ejecutar));
    }

    private void nuevoPartido() {
        try {
            Fecha hoy = Fecha.hoy();
            Fecha fecha = null;

            do {
                System.out.println("Introduce la fecha del partido:");
                Fecha nuevaFecha = Input.leerFecha();

                if (nuevaFecha.esMenor(hoy)) {
                    System.out.println("No se puede registrar un partido en una fecha ya pasada!");
                } else if (this.estadio.hayPartido(fecha)) {
                    System.out.println("Ya hay un partido registrado para esa fecha!");
                } else {
                    fecha = nuevaFecha;
                }
            } while (fecha == null);

            TipoPartido tipoPartido = null;

            do {
                System.out.printf("Introduce el tipo de partido %s:\n", Arrays.toString(TipoPartido.values()));
                try {
                    tipoPartido = TipoPartido.valueOf(Input.leerLinea(Constantes.TIPO_PARTIDO_MIN, Constantes.TIPO_PARTIDO_MAX));
                } catch (IllegalArgumentException e) {
                    System.out.println("El tipo de partido introducido es invalido!");
                }
            } while (tipoPartido == null);

            System.out.println("Introduce el nombre del equipo local:");
            String nombreLocal = Input.leerLinea(Constantes.EQUIPO_MIN, -1);

            System.out.println("Introduce el nombre del equipo visitante:");
            String nombreVisitante = Input.leerLinea(Constantes.EQUIPO_MIN, -1);

            if (nombreLocal.equalsIgnoreCase(nombreVisitante)) {
                System.out.println("El equipo visitante y el equipo local no pueden ser el mismo!");
            } else {
                Equipo equipLocal = new Equipo(nombreLocal);
                Equipo equipoVisitante = new Equipo(nombreVisitante);

                this.estadio.registrarPartido(fecha, tipoPartido, equipLocal, equipoVisitante);

                System.out.println("Partido registrado correctamente!");
            }
        } catch (CancelException e) {
            System.out.println("Creación de partido cancelada!");
        }
    }
}
