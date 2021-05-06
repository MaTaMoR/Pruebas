package me.matamor.pruebas.blackjack.cartas;

import me.matamor.pruebas.blackjack.configuracion.Constantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mazo {

    private final List<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
    }

    public Mazo(List<Carta> cartas) {
        this.cartas = new ArrayList<>(cartas);
    }

    public List<Carta> getCartas() {
        return this.cartas;
    }

    public List<Carta> getCartasVisibles() {
        return this.cartas.stream().filter(Carta::isVisisble).collect(Collectors.toList());
    }

    /**
     * Busca las cartas del mismo palo
     * @param palo el palo de las cartas a buscar
     * @return las cartas del mismo palo
     */

    public List<Carta> buscarCartas(Palo palo) {
        return this.cartas.stream()
                .filter(e -> e.getPalo() == palo)
                .collect(Collectors.toList());
    }

    /**
     * Busca las cartas del mismo tipo
     * @param tipo el tipo de las cartas a buscar
     * @return las cartas del mismo tipo
     */

    public List<Carta> buscarCartas(Tipo tipo) {
        return this.cartas.stream()
                .filter(e -> e.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public Carta sacarCarta() {
        return this.cartas.remove(0);
    }

    public void nuevaCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public void quitarCarta(Carta carta) {
        this.cartas.remove(carta);
    }

    public void limpiarCartas() {
        this.cartas.clear();
    }

    /**
     * Cuenta el total de puntos que valen las cartas
     * @return devuelve el total de puntos
     */

    public int contarPuntos() {
        int puntos = 0;

        for (Carta carta : this.cartas) {
            puntos += carta.getTipo().getValor();
        }

        return puntos;
    }

    public int contarPuntosReal() {
        int puntos = 0;

        List<Carta> cartas = new ArrayList<>(this.cartas);
        List<Carta> cartasAs = cartas.stream().filter(e -> e.getTipo() == Tipo.AS).collect(Collectors.toList());

        cartas.removeAll(cartasAs);

        for (Carta carta : cartas) {
            puntos += carta.getTipo().getValor();
        }

        //Las cartas de tipo AS se calculan de diferente forma
        if (cartasAs.size() > 0) {
            // Si el jugador se pasa ya de puntos no hago ninguna comprobación, ya ha perdido igualmente
            int puntosRestantes = Constantes.PUNTOS_GANAR - puntos;
            if (puntosRestantes > 0) {
                int contador = 1;

                do {
                    if ((puntosRestantes - Constantes.CARTA_AS_MAX) >= ((cartasAs.size() - contador) * Constantes.CARTA_AS_MIN)) {
                        puntos += Constantes.CARTA_AS_MAX;
                    } else {
                        puntos += Constantes.CARTA_AS_MIN;
                    }

                    puntosRestantes = Constantes.PUNTOS_GANAR - puntos;
                    contador++;
                } while (contador <= cartasAs.size());
            } else {
                //Simplemente sumamos el valor máximo ya que el jugador ya ha perdido
                puntos += Constantes.CARTA_AS_MIN * cartas.size();
            }
        }

        return puntos;
    }

    public static void main(String[] args) {
        Mazo mazo = new Mazo();
        mazo.nuevaCarta(new Carta(Palo.CORAZON, Tipo.NUEVE));
        mazo.nuevaCarta(new Carta(Palo.CORAZON, Tipo.AS));
        mazo.nuevaCarta(new Carta(Palo.DIAMANTE, Tipo.AS));

        System.out.println(mazo.contarPuntosReal());
    }
}
