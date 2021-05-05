package me.matamor.pruebas.tema11.ejercicio5;

public class Inventario {

    private final Item[] items;

    public Inventario() {
        this(Constantes.DEFAULT_SIZE);
    }

    public Inventario(int size) {
        this.items = new Item[size];
    }

    private void check(int posicion) throws InventoryException {
        if (posicion < 0 || posicion >= this.items.length) {
            throw new InventoryException("Position out of range!");
        }
    }

    public Item getItem(int posicion) throws InventoryException {
        check(posicion);

        return this.items[posicion];
    }

    public Item setItem(int posicion, Item item) throws InventoryException {
        check(posicion);

        Item oldItem = this.items[posicion];
        this.items[posicion] = item;

        return oldItem;
    }

    /**
     * Intenta añadir el item a la posicion definida, si el Item en la posicion definida es similar
     * Se añade la cantidad al item ya existente y se devuelve la cantidad que sobra
     *
     * @param posicion la posicion donde se añade el item
     * @param item el item que se va añadir
     * @return la cantidad de items que no se han añadido
     * @throws InventoryException si la posicion está fuera del rango valido del inventario
     */

    public int addItem(int posicion, Item item) throws InventoryException {
        check(posicion);

        Item oldItem = this.items[posicion];
        if (oldItem == null || !oldItem.isSimilar(item)) {
            this.items[posicion] = item;
            return 0;
        } else {
            int nuevaCantidad = oldItem.getCantidad() + item.getCantidad();
            oldItem.setCantidad(nuevaCantidad);

            return nuevaCantidad - oldItem.getMaterial().getStackSize();
        }
    }

    public Item removeItem(int posicion) throws InventoryException {
        check(posicion);

        Item oldItem = this.items[posicion];
        this.items[posicion] = null;

        return oldItem;
    }

    /**
     * Quita la cantidad definida del Item en la posicion definida, devuelve la cantidad que no se haya podido quitar
     * @param posicion la posicion del item de donde se va a quitar items
     * @param cantidad la cantidad de items a quitar
     * @return devuelve la cantidad que no se ha podido quitar del Item
     * @throws InventoryException si la posicion está fuera del rango valido del inventario
     */

    public int removeItem(int posicion, int cantidad) throws InventoryException {
        check(posicion);

        Item oldItem = this.items[posicion];
        if (oldItem == null) {
            return cantidad;
        }

        int nuevaCantidad = oldItem.getCantidad() - cantidad;
        if (nuevaCantidad > 0) {
            oldItem.setCantidad(0);
            return 0;
        } else {
            this.items[posicion] = null;
            return cantidad - nuevaCantidad;
        }
    }

    public Item[] getItems() {
        return this.items.clone();
    }

    public int size() {
        return this.items.length;
    }
}
