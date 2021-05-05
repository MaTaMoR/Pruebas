package me.matamor.pruebas.lib.formato;

public class Formateador {

    public static <T> String[] formatear(T valor, FormatEntry<T>[] entries) {
        String[][] valores = new String[2][entries.length];
        for (int i = 0; valores.length > i; i++) {
            valores[0][i] = entries[i].getNombre();
            valores[1][i] = entries[i].getValor(valor);
        }

        int[] longitudes = new int[entries.length];
        for (String[] grupo : valores) {
            for (int j = 0; grupo.length > j; j++) {
                int longitud = grupo[j].length();

                if (longitud > longitudes[j]) {
                    longitudes[j] = longitud;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int longitud : longitudes) {
            stringBuilder.append("%-").append(longitud).append("s ");
        }

        String formato = stringBuilder.toString();

        String[] resultado = new String[entries.length + 1];
        resultado[0] = String.format(formato, valores[0]);

        for (int i = 1; valores[1].length > i; i++) {
            resultado[i] = String.format(formato, valores[1][i]);
        }

        return resultado;
    }
}
