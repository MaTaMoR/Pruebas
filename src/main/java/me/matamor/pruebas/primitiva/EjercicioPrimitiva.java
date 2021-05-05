package me.matamor.pruebas.primitiva;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.Randomizer;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.ArrayList;
import java.util.List;

public class EjercicioPrimitiva extends MenuConsola {

    private final Primitiva primitiva;
    private final JuegoPrimitiva juegoPrimitiva;

    private Combinacion combinacion;

    public EjercicioPrimitiva() {
        super("Primitiva");

        this.primitiva = new Primitiva();
        this.juegoPrimitiva = new JuegoPrimitiva(this);

        registrarOpcion(Constantes.GENERAR_COMBINACION, new OpcionSimple("Generar combinacion", "Crear tu combinacion o genera una aleatoria", this::generarCombinacion));
        registrarOpcion(Constantes.JUGAR, new OpcionSimple("Juego", "Juega diferentes juegos de la primitiva", this::jugar));
    }

    public Primitiva getPrimitiva() {
        return this.primitiva;
    }

    public Combinacion getCombinacion() {
        return this.combinacion;
    }

    private void generarCombinacion() {
        System.out.println("¿ Quieres que el sistema genere una combinación aleatoria ? (si/no)");
        boolean preguntar = Input.leerPregunta();

        if (preguntar) {
            this.combinacion = this.primitiva.generarCombinacion();
        } else {
            List<Integer> numeros = new ArrayList<>();
            boolean repetido;

            for (int i = 0; 6 > i; i++) {
                int numero;

                do {
                    System.out.println("Introduce el numero (" + (i + 1) + "):");
                    numero = Input.leerInt(Constantes.PRIMER_BOMBO_MIN, Constantes.PRIMER_BOMBO_MAX);
                    repetido = numeros.contains(numero);

                    if (repetido) {
                        System.out.println("El número introducido no puede ser repetido!");
                    } else {
                        numeros.add(numero);
                    }
                } while (repetido);
            }

            int complementario;

            do {
                System.out.println("Introduce el numero complementario:");
                complementario = Input.leerInt(Constantes.PRIMER_BOMBO_MIN, Constantes.PRIMER_BOMBO_MAX);
                repetido = numeros.contains(complementario);

                if (repetido) {
                    System.out.println("El número complementario no puede ser repetido!");
                }
            } while (repetido);

            int reintegro = Randomizer.randomInt(Constantes.SEGUNDO_BOMBO_MIN, Constantes.SEGUNDO_BOMBO_MAX);

            this.combinacion = new Combinacion(numeros, complementario, reintegro);
        }

        System.out.println("Tu combinación es la siguiente: " + this.combinacion);
    }

    private void jugar() {
        if (this.combinacion == null) {
            System.out.println("No puedes jugar hasta que hayas generado tu combinación!");
        } else {
            this.juegoPrimitiva.ejecutar();
        }
    }

    public static void main(String[] args) {
        EjercicioPrimitiva ejercicioPrimitiva = new EjercicioPrimitiva();
        ejercicioPrimitiva.ejecutar();
    }
}
