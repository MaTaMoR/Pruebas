package me.matamor.pruebas.tema11.ejercicio7.menus;

import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Fecha;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;
import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.entradas.Entrada;
import me.matamor.pruebas.tema11.ejercicio7.entradas.EntradaNormal;
import me.matamor.pruebas.tema11.ejercicio7.entradas.EntradaUtil;
import me.matamor.pruebas.tema11.ejercicio7.entradas.EntradaVIP;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Estadio;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Partido;
import me.matamor.pruebas.tema11.ejercicio7.estadio.Zona;

import java.util.List;

public class MenuEntradas extends MenuConsola {

    private final Estadio estadio;
    private Partido seleccionado;

    public MenuEntradas(Estadio estadio) {
        super("Gestión Entradas");

        this.estadio = estadio;

        registrarOpcion(Constantes.VENTA_ENTRADA, new OpcionSimple("Venta entrada", "Compra tu entrada para el partido!", this::ventaEntrada));
        registrarOpcion(Constantes.DEVOLVER_ENTRADA, new OpcionSimple("Devolver entrada", "Devuelve tu entrada!", this::devolverEntrada));
        registrarOpcion(Constantes.LOCALIDADES_OCUPADAS, new OpcionSimple("Localidades ocupadas", "Listado de todas las localidades ocupadas!", this::listadoLocalidadesOcupadas));
        registrarOpcion(Constantes.TOTAL_RECAUDADO, new OpcionSimple("Total recaudado", "Total recaudado este partido!", this::totalRecaudado));
    }

    private void ventaEntrada() {
        try {
            System.out.printf("Introduce la zona que quieres (0-%d):\n", this.estadio.getZonas() - 1);
            int zona = Input.leerInt(0, this.estadio.getZonas() - 1);

            System.out.printf("Introduce la fila que quieres (0-%d):\n", this.estadio.getFilas() - 1);
            int fila = Input.leerInt(0, this.estadio.getFilas() - 1);

            System.out.printf("Introduce el asiento que quieres (0-%d):\n", this.estadio.getAsientos() - 1);
            int asiento = Input.leerInt(0, this.estadio.getAsientos() - 1);

            if (this.seleccionado.getRegistroEntradas().tieneEntrada(zona, fila, asiento)) {
                System.out.printf("Ya se ha vendido la entrada para la zona: %d / fila: %d / asiento: %d\n", zona, fila, asiento);
            } else {
                Entrada entrada = this.seleccionado.getRegistroEntradas().crearEntrada(zona, fila, asiento);
                System.out.printf("Precio entrada: %.2f\n", entrada.getPrecio());

                System.out.println("Entrada comprada correctamente!");
                System.out.println("La ID de tu entrada es: " + entrada.getIdentificador());

                if (entrada instanceof EntradaNormal) {
                    System.out.println("Tu número de sorteo es: " + ((EntradaNormal) entrada).getNumeroSorteo());
                } else if (entrada instanceof EntradaVIP) {
                    System.out.println("Tu número de contraseña VIP es: " + ((EntradaVIP) entrada).getPassword());
                }
            }
        } catch (CancelException e) {
            System.out.println("Venta de entrada cancelada!");
        }
    }

    private void devolverEntrada() {
        try {
            int identificador = -1;

            do {
                System.out.println("Introduce el identificador de tu entrada:");
                String rawIdentificador = Input.leerLinea(EntradaUtil.ID_LONGITUD_MIN, EntradaUtil.ID_LONGITUD_MAX);

                try {
                    identificador = Integer.parseInt(rawIdentificador);
                } catch (NumberFormatException e) {
                    System.out.println("El identificador introducida no es un número valido!");
                }
            } while (identificador == -1);

            try {
                int[] datos = EntradaUtil.leerId(identificador);

                int zona = datos[EntradaUtil.ZONA];
                int fila = datos[EntradaUtil.FILA];
                int asiento = datos[EntradaUtil.ASIENTO];

                if (zona < 0 || zona > (this.estadio.getZonas() - 1)) {
                    System.out.println("La zona está fuera del rango valido!");
                } else if (fila < 0 || (fila > this.estadio.getFilas())) {
                    System.out.println("La fila está fuera del rango valido!");
                } else if (asiento < 0 || (asiento > this.estadio.getAsientos())) {
                    System.out.println("El asiento está fuera del rango valido!");
                } else {
                    Entrada entrada = this.seleccionado.getRegistroEntradas().borrarEntrada(zona, fila, asiento);
                    if (entrada == null) {
                        System.out.println("No hay ninguna entrada registrada para ese identificador!");
                    } else {
                        System.out.println("Entrada borrada correctamente!");
                    }
                }
            } catch (EntradaUtil.ConversionException e) {
                System.out.println("El identificador introducido no es valido!");
            }
        } catch (CancelException e) {
            System.out.println("Devolución de entrada cancelada!");
        }
    }

    private void listadoLocalidadesOcupadas() {
        System.out.println("Listado de localidades ocupadas:");
        if (this.seleccionado.getRegistroEntradas().getContadorVentas() == 0) {
            System.out.println("No se ha vendido todavía ninguna entrada!");
        } else {
            Zona[] zonas = this.seleccionado.getRegistroEntradas().zonas();

            for (int idZona = 0; zonas.length > idZona; idZona++) {
                Zona zona = zonas[idZona];
                Entrada[][] entradas = zona.getEntradas();

                for (int fila = 0; entradas.length > fila; fila++) {
                    Entrada[] asientos = entradas[fila];

                    for (int asiento = 0; asientos.length > asiento; asiento++) {
                        Entrada entrada = asientos[asiento];

                        if (entrada != null) {
                            System.out.printf("Zona: %d - Fila: %d - Asiento: %d\n", idZona, fila, asiento);
                        }
                    }
                }
            }
        }
    }

    private void totalRecaudado() {
        System.out.printf("Total recaudado este partido: %.2f\n", this.seleccionado.getRegistroEntradas().getTotalRecaudado());
    }

    @Override
    public void ejecutar() {
        this.seleccionado = null;

        Fecha hoy = Fecha.hoy();
        List<Partido> partidos = this.estadio.buscarPartidos(e -> e.getFechaPartido().esMayorIgual(hoy));

        if (partidos.isEmpty()) {
            System.out.println("No hay ningún partido registrado para hoy o fechas futuras!");
        } else {
            if (partidos.size() == 1) {
                this.seleccionado = partidos.get(0);
            } else {
                for (int i = 0; partidos.size() > i; i++) {
                    Partido partido = partidos.get(i);

                    System.out.println(i + " - " + partido);
                }

                System.out.printf("Introduce el número del partido que quieres (0-%d):\n", partidos.size());
                int posicion = Input.leerInt(0, partidos.size() - 1);

                this.seleccionado = partidos.get(posicion);

                System.out.println("Partido seleccionado correctamente!");
            }

            super.ejecutar();
        }
    }
}
