package me.matamor.pruebas.tema10.ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class ArrayListPila<E> implements IPila<E> {

    private final List<E> valores;

    public ArrayListPila() {
        this.valores = new ArrayList<>();
    }

    public ArrayListPila(int capacidadInicial) {
        this.valores = new ArrayList<>(capacidadInicial);
    }

    @Override
    public E push(E e) {
        this.valores.add(e);

        return e;
    }

    @Override
    public E pop() {
        if (this.valores.isEmpty()) {
            throw new IllegalStateException("No hay ningun elemento!");
        }

        return this.valores.remove(this.valores.size() - 1);
    }

    @Override
    public int size() {
        return this.valores.size();
    }

    @Override
    public E top() {
        //Creo que con este metodo quiere la funciondalidad de Stack#peek()
        if (this.valores.isEmpty()) {
            throw new IllegalStateException("No hay ningun elemento!");
        }

        return this.valores.get(this.valores.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.valores.isEmpty();
    }
}
