package me.matamor.pruebas.lib.registro;

import me.matamor.pruebas.lib.Condicion;
import me.matamor.pruebas.lib.Keyable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Registro<K, V extends Keyable<K>> {

    private final Map<K, V> registros;

    public Registro() {
        this.registros = new HashMap<>();
    }

    /**
     * Registra el valor con su key asociada
     * @param value el valor a registrar
     * @throws RegistroException si el valor es null o la key ya ha sido utilizada
     */

    public void registrar(V value) throws RegistroException {
        if (value == null) {
            throw new RegistroException("El valor no puede ser null!");
        }

        K key = value.getKey();
        if (this.registros.containsKey(key)) {
            throw new RegistroException("Ya hay un registro con la key: " + key);
        }

        this.registros.put(key, value);
    }

    /**
     * Busca un registro con la key asociada
     * @param key la key del registro a buscar
     * @return el registro asociado a la key o null si no existe
     */

    public V buscarRegistro(K key) {
        return this.registros.get(key);
    }

    /**
     * Devuelve el primer registro que cumpla con la condicion
     * @param condicion la condicion para buscar el registro
     * @return el registro encontrado o null si ningun registro cumple la condicion
     */

    public V buscarRegistro(Condicion<V> condicion) {
        return this.registros.values().stream().filter(condicion::comprobar).findFirst().orElse(null);
    }

    /**
     * Devuelve un listado con los registros que cumpla la condicion
     * @param condicion la condicion para buscar los registros
     * @return el listado de registros que cumplen la condicion
     */

    public List<V> buscarRegistros(Condicion<V> condicion) {
        return this.registros.values().stream().filter(condicion::comprobar).collect(Collectors.toList());
    }

    /**
     * Comprueba si existe un registro asociado a la key
     * @param key la key del registro
     * @return true si existe un registro asociado a la key
     */

    public boolean tieneRegistro(K key) {
        return this.registros.containsKey(key);
    }

    /**
     * Borra el registro asociado a la key
     * @param key la key del registro
     * @return devuelve el registro asociado a la key que ha sido borrado
     */

    public V borrarRegistro(K key) {
        return this.registros.remove(key);
    }

    /**
     * Devuelve los registros
     * @return collection de todos los registros
     */

    public Collection<V> registros() {
        return this.registros.values();
    }
}
