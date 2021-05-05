package me.matamor.pruebas.tema11.ejercicio5;

public enum Material {

    PICO(1, 100),
    ESPADA(1, 100),
    PIEDRA,
    MADERA,
    HUEVO(16),
    PERLA_DE_ENDER(16);

    private final int stackSize;
    private final int durabilidadMaxima;
    
    Material() {
        this(Constantes.DEFAULT_STACK_SIZE);
    }

    Material(int stackSize) {
        this(stackSize, Constantes.DEFAULT_MAX_DURABILITY);
    }

    Material(int stackSize, int durabilidadMaxima) {
        this.stackSize = stackSize;
        this.durabilidadMaxima = durabilidadMaxima;
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public int getDurabilidadMaxima() {
        return this.durabilidadMaxima;
    }
}
