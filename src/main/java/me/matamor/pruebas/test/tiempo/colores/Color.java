package me.matamor.pruebas.test.tiempo.colores;

public enum Color {

    NEGRO(0),
    ROJO(1),
    VERDE(2),
    AMARILLO(3),
    AZUL(4),
    MAGENTA(5),
    CYAN(6),
    GRIS(7);

    private final int codigo;

    Color(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }
}
