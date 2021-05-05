package me.matamor.pruebas.tema10.ejercicio4;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

public class Cola<E> implements ICola<E> {

    private final List<E> elementos;

    public Cola() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public boolean add(E e) {
        return this.elementos.add(e);
    }

    @Override
    public E remove() throws IllegalStateException {
        if (this.elementos.isEmpty()) {
            throw new IllegalStateException("La cola esta vacia!");
        }

        return this.elementos.remove(0);
    }

    @Override
    public int size() {
        return this.elementos.size();
    }

    @Override
    public E peek() {
        if (this.elementos.isEmpty()) {
            throw new IllegalStateException("La cola esta vacia!");
        }

        return this.elementos.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return this.elementos.isEmpty();
    }

    @Override
    public List<E> elementos() {
        return this.elementos;
    }
}
