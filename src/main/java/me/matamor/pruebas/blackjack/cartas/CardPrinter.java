package me.matamor.pruebas.blackjack.cartas;

import me.matamor.pruebas.blackjack.cartas.Baraja;
import me.matamor.pruebas.blackjack.cartas.Carta;

public class CardPrinter {

    private static final char LEFT_TOP_CORNER = '\u250c';
    private static final char RIGHT_TOP_CORNER = '\u2510';
    private static final char LEFT_BOTTOM_CORNER = '\u2514';
    private static final char RIGHT_BOTTOM_CORNER = '\u2518';

    private static final char COLUMN = '\u007C';
    private static final char FLOOR = '\u2500';

    private static final int ROWS = 7;
    private static final int COLUMNS = 7;

    private static String createHeader() {
        return LEFT_TOP_CORNER + String.valueOf(FLOOR).repeat(COLUMNS) + RIGHT_TOP_CORNER;
    }

    private static String createFooter() {
        return LEFT_BOTTOM_CORNER + String.valueOf(FLOOR).repeat(COLUMNS) + RIGHT_BOTTOM_CORNER;
    }

    public static void print2(Carta carta) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(createHeader());

        stringBuilder.append(createFooter());

        System.out.println(stringBuilder.toString());
    }

    private static String juntar(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append("\n");
        }

        return stringBuilder.toString();
    }

    public static void print(Carta carta) {
        String tipo = carta.getTipo().getNombre();

        String resultado = juntar(new String[] {
                "┌───────┐",
                "| c    |".replace("c", (tipo.length() == 2 ? tipo : tipo + " ")),
                "|       |",
                "|   p   |".replace("p", "" + carta.getPalo().getUnicode()),
                "|       |",
                "|    c |".replace("c", (tipo.length() == 2 ? tipo : " " + tipo)),
                "└───────┘"
        });

        System.out.println(resultado);
    }

    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        for (Carta carta : baraja.getCartas()) {
            print(carta);
        }

        System.out.println(LEFT_TOP_CORNER);
        System.out.println(RIGHT_BOTTOM_CORNER);
        System.out.println(RIGHT_TOP_CORNER);
        System.out.println(LEFT_BOTTOM_CORNER);
    }
}
