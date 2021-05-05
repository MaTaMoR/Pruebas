package me.matamor.pruebas.tema11.ejercicio3;

public class CocheCambioAutomatico extends Coche {

    public CocheCambioAutomatico(String matricula) {
        super(matricula, new int[] { 20, 50, 80, 120, 180 });
    }

    @Override
    public void acelerar(int cantidad) throws CocheException {
        if (cantidad <= 0) {
            throw new CocheException("El coche solo puede acelerar una cantidad igual o superior a 1!");
        }

        int nuevaVelocidad = getVelocidad() + cantidad;
        int velocidadMaxima = velocidadMaxima();

        if (nuevaVelocidad > velocidadMaxima) {
            nuevaVelocidad = velocidadMaxima;
        }

        int marchaMaxima = getMarchas().length - 1;

        System.out.printf("Acelerar de %d hasta %d!\n", getVelocidad(), nuevaVelocidad);

        while (getVelocidad() < nuevaVelocidad) {
            int velocidad = getVelocidad();
            int marcha = getMarcha();

            int velocidadMaximaActual = velocidadMaximaMarchaActual();

            System.out.printf("Velocidad: %d, Marcha: %d, Velocidad maxima: %d\n", velocidad, marcha, velocidadMaximaActual);

            setVelocidad(velocidad + 1);

            if (getVelocidad() > velocidadMaximaActual && marcha < marchaMaxima) {
                cambiarMarcha(marcha + 1);
            }
        }
    }

    @Override
    public void frenar(int cantidad) throws CocheException {
        if (cantidad <= 0) {
            throw new CocheException("El coche solo puede frenar una cantidad igual o superior a 1!");
        }

        int nuevaVelocidad = getVelocidad() - cantidad;
        if (nuevaVelocidad < 0) {
            nuevaVelocidad = 0;
        }

        /*
            Está hecho para frenar de manera gradual y no un salto directamente
         */

        System.out.printf("Frenar de %d hasta %d!\n", getVelocidad(), nuevaVelocidad);

        while (getVelocidad() > nuevaVelocidad) {
            int velocidad = getVelocidad();
            int marcha = getMarcha();

            int velocidadMinimaActual = velocidadMinimaMarchaActual();

            System.out.printf("Velocidad: %d, Marcha: %d, Velocidad minima: %d\n", velocidad, marcha, velocidadMinimaActual);

            setVelocidad(velocidad - 1);

            if (getVelocidad() < velocidadMinimaActual && marcha > 0) {
                cambiarMarcha(marcha - 1);
            }
        }
    }
}
