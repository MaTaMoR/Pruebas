package me.matamor.pruebas.test.map;

import java.lang.reflect.Field;
import java.util.Objects;

public class MapCustom<K,V> {

    private static final int MAX_LENGTH = 99;
    private static final int CONST_BASE = 31;
    private static final float MAX_LOAD_FACTOR = 0.7f;
    private static final float GROW_FACTOR = 1.5f;

    private int currentSize;

    private Node<K,V>[] tabla;

    public MapCustom(int capacidadInicial) {
        this.tabla = new Node[nextPrime(capacidadInicial)];
    }

    private static String cut(String key, int length) {
        if (key.length() > length) {
            key = key.substring(length);
        }

        return key;
    }

    public V put(K key, V value) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            resize();
        }

        String keyStr = extractString(key);
        int posicion = hash(keyStr);

        Node<K,V> oldValue = this.tabla[posicion];
        Node<K,V> newValue = new Node<>(posicion, key, value, keyStr);
        if (oldValue == null || oldValue.equals(newValue)) {
            this.tabla[posicion] = newValue;

            if (oldValue != null) {
                newValue.setNext(oldValue.getNext());
                return oldValue.getValue();
            } else {
                this.currentSize++;
                return null;
            }
        }

        Node<K,V> nextNode = oldValue;
        while (nextNode.getNext() != null) {
            nextNode = oldValue.getNext();

            if (nextNode.equals(newValue)) {
                oldValue.setNext(newValue);
                newValue.setNext(nextNode.getNext());
                return oldValue.getValue();
            }

            oldValue = nextNode;
        }

        this.currentSize++;
        nextNode.setNext(newValue);
        return null;
    }

    public V get(K key) {
        String keyStr = extractString(key);
        int posicion = hash(keyStr);

        Node<K,V> node = this.tabla[posicion];
        while (node != null && !node.getKeyStr().equals(keyStr)) {
            node = node.getNext();
        }

        return (node == null ? null : node.getValue());
    }

    public V remove(K key) {
        String keyStr = extractString(key);
        int posicion = hash(keyStr);

        Node<K,V> node = this.tabla[posicion];
        Node<K,V> previousNode = null;

        while (node != null && !node.getKeyStr().equals(keyStr)) {
            previousNode = node;
            node = node.getNext();
        }

        if (node == null) {
            return null;
        } else {
            if (previousNode == null) {
                this.tabla[posicion] = null;
                return node.getValue();
            }

            previousNode.setNext(node.getNext());
            return node.getValue();
        }
    }

    private float loadFactor() {
        return (float) this.currentSize / tabla.length;
    }

    private void resize() {
        this.currentSize = nextPrime((int) (this.tabla.length * GROW_FACTOR));

        Node<K,V>[] oldArray = this.tabla;
        this.tabla = new Node[this.currentSize];

        this.currentSize = 0;

        for (Node<K,V> node : oldArray) {
            if (node == null) continue;

            do {
                put(node.getKey(), node.getValue());
                node = node.getNext();
            } while (node != null);
        }
    }

    private int nextPrime(int number) {
        if (isPrime(number)) {
            return number;
        }

        if (number % 2 == 0) {
            number++;
        }

        while (!(isPrime(number))) {
            number += 2;
        }

        return number;
    }

    private boolean isPrime(int number) {
        if (number > 2 && number % 2 == 0) {
            return false;
        }

        int root = (int) Math.sqrt(number) + 1;
        for (int i = 3; root > i; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static String extractString(Object key) {
        StringBuilder builder = new StringBuilder();
        Field[] fields = key.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                builder.append(field.getName()).append(field.get(key));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return cut(builder.toString(), MAX_LENGTH);
    }

    private int hash(String keyStr) {
        int result = 0;

        for (int i = 0; keyStr.length() > i; i++) {
            result = result * CONST_BASE + keyStr.charAt(i);
        }

        /*
            Con esta operación nos asguramos que el número sea positivo
            realiza una operación AND en binario de los dos números
            el número 0x7FFFFFFF representa un número en hexadecimal
            el número en binario es: 01111111.11111111.11111111.11111111
            de tal forma que el primer bit sea 0, el primero bit se utiliza
            para representar si un número es positivo o negaitov
            0 = positivo y 1 = negativo
         */

        result = result & 0x7FFFFFFF;
        result %= this.tabla.length;

        return result;
    }

    static class Node<K,V> {

        private final int hash;
        private final K key;
        private final V value;
        private final String keyStr;

        private Node<K,V> next;

        public Node(int hash, K key, V value, String keyStr) {
            this(hash, key, value, keyStr, null);
        }

        public Node(int hash, K key, V value, String keyStr, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.keyStr = keyStr;

            this.next = next;
        }

        public int getHash() {
            return this.hash;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public String getKeyStr() {
            return this.keyStr;
        }

        public void setNext(Node<K,V> next) {
            this.next = next;
        }

        public Node<K,V> getNext() {
            return this.next;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj.getClass() != getClass()) {
                return false;
            } else {
                Node node = (Node) obj;
                return Objects.equals(this.keyStr, node.keyStr);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", keyStr='" + keyStr + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        String key = "hola";
        String value = "adios";

        MapCustom<String, String> mapCustom = new MapCustom<>(10);
        mapCustom.put(key, value);

        System.out.println(mapCustom.get(key));
        mapCustom.resize();
        System.out.println(mapCustom.get(key));
    }
}
