package me.matamor.pruebas.tema11.ejercicio5;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.Arrays;

public class Ejercicio5 extends MenuConsola {

    private final Inventario inventario;
    private final GeneradorItem generadorItem;

    public Ejercicio5() {
        super("Inventario");

        this.inventario = new Inventario();

        this.generadorItem = new GeneradorItem();
        randomInventory();

        registrarOpcion(Constantes.LISTADO_DE_MATERIALES, new OpcionSimple("Listado de materiales", "Lista de todos los materiales disponibles", this::listadoMateriales));
        registrarOpcion(Constantes.INFORMACION_MATERIAL, new OpcionSimple("Información de material", "Mira la información sobre un material", this::informacionMaterial));
        registrarOpcion(Constantes.NUEVO_ITEM, new OpcionSimple("Añadir item", "Añade un item al inventario", this::nuevoItem));
        registrarOpcion(Constantes.BORRAR_ITEM, new OpcionSimple("Borrar item", "Borra un item del inventario", this::borrarItem));
        registrarOpcion(Constantes.VISUALIZAR_INVENTARIO, new OpcionSimple("Visualizar inventario", "Mira el contenido del inventario", this::visualizarInventario));
        registrarOpcion(Constantes.GENERAR_INVENTARIO, new OpcionSimple("Generar inventario", "Genera un inventario con items nuevos", this::generarInventario));
    }

    private void randomInventory() {
        for (int i = 0; this.inventario.size() > i; i++) {
            this.inventario.setItem(i, this.generadorItem.generar());
        }
    }

    private void listadoMateriales() {
        System.out.println("Materiales:");
        System.out.println(Arrays.toString(Material.values()));
    }

    private void informacionMaterial() {
        Material material = null;

        do {
            System.out.println("Introduce el material del nuevo item: ");
            String materialName = Input.leerLinea(Constantes.MATERIAL_NAME_MIN, -1);

            try {
                material = Material.valueOf(materialName);
            } catch (IllegalArgumentException e) {
                System.out.printf("El material '%s' no existe!\n", materialName);
            }
        } while (material == null);

        System.out.println("Información del material: " + material);
        System.out.println("    Stack size: " + material.getStackSize());
        System.out.println("    Durabilidad máxima: " + (material.getDurabilidadMaxima() > 1 ? material.getDurabilidadMaxima() : "No tiene"));
    }

    private void generarInventario() {
        randomInventory();
        System.out.println("Inventario actualizado!");
    }

    private void nuevoItem() {
        Material material = null;

        do {
            System.out.println("Introduce el material del nuevo item: ");
            String materialName = Input.leerLinea(Constantes.MATERIAL_NAME_MIN, -1);

            try {
                material = Material.valueOf(materialName);
            } catch (IllegalArgumentException e) {
                System.out.printf("El material '%s' no existe!\n", materialName);
            }
        } while (material == null);

        int cantidad = 1;
        if (material.getStackSize() > 1) {
            System.out.println("Introduce la cantidad del nuevo item: ");
            cantidad = Input.leerInt(1, material.getStackSize());
        }

        System.out.printf("Introduce la posición del nuevo item (0-%d): \n", this.inventario.size() - 1);
        int posicion = Input.leerInt(0, this.inventario.size() - 1);

        int durabilidad = material.getDurabilidadMaxima();
        if (durabilidad > 1) {
            System.out.printf("Introduce la durabilidad del nuevo item (1-%s): \n", durabilidad);
            durabilidad = Input.leerInt(1, durabilidad);
        }

        Item item = new Item(material, cantidad, durabilidad);
        int restante = this.inventario.addItem(posicion, item);

        System.out.println(restante);

        if (restante > 0) {
            System.out.printf("La cantidad excede el limite de stack del item! No se han añadido %d item(s)!\n", restante);
        }

        if (restante != cantidad) {
            System.out.println("Inventario actualizado!");
        }
    }

    private void borrarItem() {
        System.out.printf("Introduce la posición del item a borrar (0,%d): \n", this.inventario.size() - 1);
        int posicion = Input.leerInt(0, this.inventario.size() - 1);

        Item item = this.inventario.getItem(posicion);
        if (item == null) {
            System.out.println("No hay ningún item en la posición: " + posicion);
        } else {
            int cantidad = 1;

            if (item.getCantidad() > 1) {
                System.out.println("Introduce la cantidad de items a borrar!");
                cantidad = Input.leerInt(1, item.getCantidad());
            }

            this.inventario.removeItem(posicion, cantidad);

            System.out.println("Inventario actualizado!");
        }
    }

    private void visualizarInventario() {
        System.out.println("Inventario:");
        System.out.println(Arrays.toString(this.inventario.getItems()));
    }

    public static void main(String[] args) {
        Ejercicio5 ejercicio5 = new Ejercicio5();
        ejercicio5.ejecutar();
    }
}
