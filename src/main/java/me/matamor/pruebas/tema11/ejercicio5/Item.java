package me.matamor.pruebas.tema11.ejercicio5;

public class Item {

    private Material material;
    private int cantidad;
    private int durabilidad;

    public Item(Material material, int cantidad) {
        this(material, cantidad, material.getDurabilidadMaxima());
    }

    public Item(Material material, int cantidad, int durabilidad) {
        this.material = material;

        setCantidad(cantidad);
        setDurabilidad(durabilidad);
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        if (this.material != material) {
            this.material = material;
            setCantidad(this.cantidad);
        }
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        if (this.cantidad != cantidad) {
            if (cantidad > this.material.getStackSize()) {
                cantidad = this.material.getStackSize();
            } else if (cantidad < 1) {
                cantidad = 1;
            }

            this.cantidad = cantidad;
        }
    }

    public int getDurabilidad() {
        return this.durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        if (this.durabilidad != durabilidad) {
            if (durabilidad > this.material.getDurabilidadMaxima()) {
                durabilidad = this.material.getDurabilidadMaxima();
            } else if (durabilidad < 1) {
                durabilidad = 1;
            }

            this.durabilidad = durabilidad;
        }
    }

    public boolean isSimilar(Item item) {
        return this.material == item.getMaterial() && this.durabilidad == item.getDurabilidad();
    }

    public Item copiar() {
        return new Item(this.material, this.cantidad, this.durabilidad);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (object == this) {
            return true;
        } else if (object.getClass() == getClass()) {
            Item item = (Item) object;
            return item.getMaterial() == this.material && item.getCantidad() == this.cantidad;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.material + "x" + this.cantidad;
    }
}
