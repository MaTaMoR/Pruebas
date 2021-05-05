package me.matamor.pruebas.tema10.ejercicio3;

public interface IPila<E> {

    E push(E e);

    E pop();

    int size();

    E top();

    boolean isEmpty();

}
