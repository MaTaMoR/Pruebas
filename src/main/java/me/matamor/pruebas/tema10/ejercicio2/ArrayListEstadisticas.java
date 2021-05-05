package me.matamor.pruebas.tema10.ejercicio2;

import java.util.*;

public class ArrayListEstadisticas extends ArrayList<Double> implements IEstadisticas {

    private static final int EMPTY_RETURN = -1;

    @Override
    public double minimo() {
        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            double minimo = get(0);

            for (int i = 1; size() > i; i++) {
                double valor = get(i);

                if (valor < minimo) {
                    minimo = valor;
                }
            }

            return minimo;
        }
    }

    @Override
    public double maximo() {
        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            double maximo = get(0);

            for (int i = 1; size() > i; i++) {
                double valor = get(i);

                if (valor > maximo) {
                    maximo = valor;
                }
            }

            return maximo;
        }
    }

    @Override
    public double sumatorio() {
        //No estoy seguro si este el sumatorio que quiere
        //el que vimos antes va de un numero hasta 1 sumando todos los valores
        //pero como exactamente se implementa esto con una lista/array

        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            double sumatorio = 0;

            for (double valor : this) {
                sumatorio += valor;
            }

            return sumatorio;
        }
    }

    @Override
    public double media() {
        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            double total = EMPTY_RETURN;

            for (double valor : this) {
                total += valor;
            }

            return total / size();
        }
    }

    @Override
    public double moda() {
        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            int[] cantidades = new int[size()];

            for (int i = 0; size() > i; i++) {
                double valor = get(i);

                int posicion = i;

                for (int j = 0; size() > i; i++) {
                    double valorPosicion = get(j);

                    if (valorPosicion == valor) {
                        posicion = j;
                        break;
                    }
                }

                cantidades[posicion]++;
            }

            int mayor = 0;
            int mayorPosicion = 0;

            for (int i = 0; cantidades.length > i; i++) {
                int valor = cantidades[i];

                if (valor > mayor) {
                    mayor = valor;
                    mayorPosicion = i;
                }
            }

            return get(mayorPosicion);
        }
    }

    public double moda2() {
        if (isEmpty()) {
            return EMPTY_RETURN;
        } else {
            Map<Double, Integer> cantidades = new HashMap<>();

            for (double valor : this) {
                if (cantidades.containsKey(valor)) {
                    cantidades.put(valor, cantidades.get(valor) + 1);
                } else {
                    cantidades.put(valor, 1);
                }
            }

            double moda = 0;
            double cantidadModa = 0;

            for (Map.Entry<Double, Integer> entry : cantidades.entrySet()) {
                if (entry.getValue() > cantidadModa) {
                    moda = entry.getKey();
                    cantidadModa = entry.getValue();
                }
            }

            return moda;
        }
    }
}
