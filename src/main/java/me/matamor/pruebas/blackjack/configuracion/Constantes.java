package me.matamor.pruebas.blackjack.configuracion;

public final class Constantes {

    private Constantes() {

    }

    public static final int NAME_MIN = 3;
    public static final int SALDO_DEFAULT = 2500;
    public static final int APUESTA_DEFAULT = 1000;
    public static final int SALDO_CPU_DEFAULT = SALDO_DEFAULT * 2;
    public static final int JUGADORES_MAXIMOS = 7;

    public static final double CPU_MIN_SIN = 15;
    public static final double CPU_MIN_MEJOR = 85;

    public static final long DELAY = 2000L;

    public static final int PUNTOS_GANAR = 21;
    public static final int CARTAS_RONDA = 2;

    public static final int CARTAS_VISIBLES_CPU = 1;
    public static final int CARTAS_OCULTAS_CPU = 1;
    public static final int PEDIR_CARTA_CPU = 16;

    public static final int CARTA_AS_MIN = 1;
    public static final int CARTA_AS_MAX = 11;

    public static final double BONIFICADOR_BLACK_JACK = 1.5;
    public static final double BONIFICADOR_NORMAL = 1;

    public static final int NUEVA_PARTIDA = 1;
    public static final int CARGAR_PARTIDA = 2;
    public static final int ESTADISTICAS = 3;

}
