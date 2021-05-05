package me.matamor.pruebas.lib;

public class Pila<E> {

    private Object[] elementos;

    private int primero;
    private int ultimo;

    public Pila(int inicio) {
        this.elementos = new Object[inicio];
        this.primero = -1;
        this.ultimo = -1;
    }

    private boolean isFull() {
        return this.size() == this.elementos.length;
    }

    public void push(E elemento) {
        if (isFull()) {
            throw new IllegalStateException("La Pila ha llegado al tamaño maximo!");
        }

        this.primero = (this.primero + 1) % this.elementos.length;
        this.ultimo = this.primero;



        System.out.println(this.ultimo);
    }

    public int size() {
        return (this.elementos.length - primero) - (this.elementos.length - ultimo);
    }

    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {
        Pila<Object> pila = new Pila<>(10);
        pila.push(null);
    }
}
