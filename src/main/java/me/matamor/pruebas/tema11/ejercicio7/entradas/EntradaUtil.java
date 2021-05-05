package me.matamor.pruebas.tema11.ejercicio7.entradas;

public final class EntradaUtil {

    private EntradaUtil() {

    }

    private static final int NUMERO_MAX = 9;

    private static final int CONVERTIDO_MAX = NUMERO_MAX + 1;
    private static final int CONVERTIDO_MIN = 2;

    public static final int ID_LONGITUD_MAX = CONVERTIDO_MAX * 3;
    public static final int ID_LONGITUD_MIN = CONVERTIDO_MIN * 3;

    public static final int ZONA = 0;
    public static final int FILA = 1;
    public static final int ASIENTO = 2;

    /**
     * Le añade adelante al número la longitud del número, el número no puede ser mayor que la LONGITUD_MAXIMA
     * @param numero el número a convertir
     * @return devuelve el número convertido
     * @throws ConversionException si el número es más grande que la LONGITUD_MAXIMA
     */

    public static int convertirNumero(int numero) throws ConversionException {
        String stringNumero = String.valueOf(numero);

        int longitud = stringNumero.length();
        if (longitud > NUMERO_MAX) {
            throw new EntradaUtil.ConversionException("El número es más grande que el tamaño máximo permitido!");
        }

        return Integer.parseInt(longitud + "" + numero);
    }

    /**
     * Desconvierte el número que se convirtio con EntradaUtil#convertirNumero(int.class)
     * @param numero el número que fue convertido
     * @return el número desconvertido
     * @throws ConversionException si el número es más largo que LONGITUD_MAXIMA + 1
     */

    public static int desconvertirNumero(int numero) throws ConversionException {
        String stringNumero = String.valueOf(numero);

        int longitud = stringNumero.length();
        if (longitud > CONVERTIDO_MAX) { //Le sumo 1 porque el primer número debería ser la cantidad
            throw new ConversionException("El número es más grande que el tamaño máximo permitido!");
        } else if (longitud < CONVERTIDO_MIN) {
            throw new ConversionException("El número es menor que el tamaño minimo permitido!");
        }

        return Integer.parseInt(stringNumero.substring(1, longitud));
    }

    /**
     * Crea una id a base de los 3 números
     * @param zona la zona de la entrada
     * @param fila la fila de la entrada
     * @param asiento el asiento de la entrada
     * @return la id creada a partir de la zona, fila y asiento
     * @throws ConversionException si los valores son invalidos para crear un ID
     */

    public static int crearId(int zona, int fila, int asiento) throws ConversionException {
        return Integer.parseInt(convertirNumero(zona) + "" + convertirNumero(fila) + "" + convertirNumero(asiento));
    }

    /**
     * Leer una ID y saca los datos que se usaron para crearla: zona, fila y asiento
     * @param id la id creada con EntradaUtil#crearId(int.class, int.class, int.class)
     * @return los 3 valores que se usaron para crear la id
     * @throws ConversionException si la id es invalida
     */

    public static int[] leerId(int id) throws ConversionException {
        String stringNumero = String.valueOf(id);

        int longitud = stringNumero.length();
        if (longitud > ID_LONGITUD_MAX) {
            throw new ConversionException("El número es más grande que el tamaño máximo permitido!");
        } else if (longitud < ID_LONGITUD_MIN) {
            throw new ConversionException("El número es más pequeño que el tamaño minimo permitido!");
        }

        int[] numeros = new int[3];

        int partes = 0;
        int inicio = 0;
        int fin;

        do {
            int longitudNumero = Integer.parseInt(String.valueOf(stringNumero.charAt(inicio)));
            fin = inicio + longitudNumero + 1;

            if (fin > longitud) {
                throw new ConversionException("El tamaño del número a leer es mayor que todo el número entero!");
            }

            String rawNumero = stringNumero.substring(inicio, fin);
            numeros[partes++] = desconvertirNumero(Integer.parseInt(rawNumero));

            inicio = fin;
        } while (partes < 3);

        return numeros;
    }

    public static class ConversionException extends RuntimeException {

        public ConversionException(String message) {
            super(message);
        }
    }
}
