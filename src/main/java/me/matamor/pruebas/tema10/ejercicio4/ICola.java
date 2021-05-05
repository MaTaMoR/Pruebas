package me.matamor.pruebas.tema10.ejercicio4;

import java.util.Collection;

public interface ICola<E> {

    boolean add(E e);

    E remove();

    int size();

    E peek();

    boolean isEmpty();

    Collection<E> elementos();

}
