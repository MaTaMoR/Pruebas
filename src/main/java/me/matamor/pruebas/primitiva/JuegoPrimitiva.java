package me.matamor.pruebas.primitiva;

import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.LinkedHashMap;
import java.util.Map;

public class JuegoPrimitiva extends MenuConsola {

    private final EjercicioPrimitiva main;

    public JuegoPrimitiva(EjercicioPrimitiva main) {
        super("Juego");

        this.main = main;

        registrarOpcion(Constantes.JUEGO_UNICO, new OpcionSimple("Juego unico", "Juego a la primitiva una sola vez", this::juegoUnico));
        registrarOpcion(Constantes.JUGAR_HASTA_GANAR, new OpcionSimple("Juego hasta ganar", "Juego hasta ganar un premio", this::juegoHastaGanar));
        registrarOpcion(Constantes.JUEGO_HASTA_GANAR_SIN, new OpcionSimple("Juego hasta ganar sin reintegro", "Juego hasta ganar un premio sin reintegro", this::juegoHastaGanarSin));
        registrarOpcion(Constantes.JUGAR_CICLO, new OpcionSimple("Juego ciclo", "Juego un ciclo de veces y luego mira los premios", this::juegoCiclo));
        registrarOpcion(Constantes.JUGAR_HASTA_GANAR_ESPECIAL, new OpcionSimple("Juego hasta ganar especial", "Juego hasta ganar el premio especial", this::juegoHastaGanarEspecial));
    }

    private void juegoUnico() {
        System.out.println("Juego unico:");

        this.main.getPrimitiva().generarCombinacionGanadora();

        System.out.println("Combinación ganadora: " + this.main.getPrimitiva().getCombinacionGanadora());
        System.out.println("Tu combinación: " + this.main.getCombinacion());

        Premio premio = this.main.getPrimitiva().comparar(this.main.getCombinacion());
        System.out.println("Premio: " + (premio == null ? "No has ganado un premio" : premio));
    }

    private void juegoHastaGanar() {
        System.out.println("Juego hasta ganar premio:");

        Premio premio;
        int intentos = 0;

        do {
            this.main.getPrimitiva().generarCombinacionGanadora();

            premio = this.main.getPrimitiva().comparar(this.main.getCombinacion());
            intentos++;
        } while (premio == null);

        System.out.println("Intentos: " + intentos);
        System.out.println("Premio: " + premio);
    }

    private void juegoHastaGanarSin() {
        System.out.println("Juego hasta ganar premio sin reintegro:");

        Premio premio;
        int intentos = 0;

        do {
            this.main.getPrimitiva().generarCombinacionGanadora();

            premio = this.main.getPrimitiva().comparar(this.main.getCombinacion());
            intentos++;
        } while (premio == null || premio == Premio.REINTEGRO);

        System.out.println("Intentos: " + intentos);
        System.out.println("Premio: " + premio);
    }

    private void juegoCiclo() {
        System.out.println("Juego ciclo:");
        Map<Premio, Integer> premios = new LinkedHashMap<>();
        for (Premio premio : Premio.values()) {
            premios.put(premio, 0);
        }

        for (int i = 0; Constantes.CANTIDAD_CICLOS > i; i++) {
            this.main.getPrimitiva().generarCombinacionGanadora();

            Premio premio = this.main.getPrimitiva().comparar(this.main.getCombinacion());
            if (premio != null) {
                premios.put(premio, premios.getOrDefault(premio, 0) + 1);
            }
        }

        System.out.println("Intentos: " + Constantes.CANTIDAD_CICLOS);
        System.out.println("Premios:");
        for (Map.Entry<Premio, Integer> entry : premios.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void juegoHastaGanarEspecial() {
        System.out.println("Juego hasta ganar premio especial:");

        Premio premio;
        int intentos = 0;

        do {
            this.main.getPrimitiva().generarCombinacionGanadora();

            premio = this.main.getPrimitiva().comparar(this.main.getCombinacion());
            intentos++;
        } while (premio != Premio.ESPECIAL);

        System.out.println("Intentos: " + intentos);
        System.out.println("Premio: " + premio);
    }
}
