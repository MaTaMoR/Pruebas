package me.matamor.pruebas.tema8.ejercicio6;

import me.matamor.pruebas.tema8.ejercicio6.registros.CondicionBusqueda;
import me.matamor.pruebas.tema8.ejercicio6.registros.RegistroBicicletasStackeable;
import me.matamor.pruebas.lib.Input;

import java.util.Date;

public class Ejercicio6 {

    private static final int NUEVA = 1;
    private static final int VENDER = 2;
    private static final int CONSULTAR = 3;
    private static final int MOSTRAR = 4;
    private static final int SALIR = 0;

    private static final int CONSULTAR_REFERENCIA = 1;
    private static final int CONSULTAR_MARCA = 2;
    private static final int CONSULTAR_MODELO = 3;
    private static final int CONSULTAR_VOLVER = 0;

    private static final double MIN_PESO = 1;
    private static final double MIN_PULGADAS_RUEDAS = 1;
    private static final double MIN_PRECIO = 1;
    private static final int MIN_STOCK = 0;

    private RegistroBicicletasStackeable registroBicicleta;

    public Ejercicio6() {
        this.registroBicicleta = new RegistroBicicletasStackeable();
        //this.registroBicicleta = new RegistroBicicletasExpandible();
        //this.registroBicicleta = new RegistroBicicletasMapa();


        //Inicializamos la aplicación con unas bicicletas de forma predeterminada... Para ello creamos primero 3 bicicleta
        Bicicleta suzuki = new Bicicleta(0, "Suzuki", "SuperBicicleta", 10, 20, false, new Date("21/02/1998"), 1000, 10);
        Bicicleta yamaha = new Bicicleta(1, "Yamaha", "Yamcha", 15, 18, false, new Date("30/05/2008"), 750, 5);
        Bicicleta mitsubishi = new Bicicleta(2, "Mitsubishi", "TambienHacenBicis", 50, 15, false, new Date("02/02/2021"), 250, 3);

        //Registramos las bicicletas
        this.registroBicicleta.registrarBicicletas(suzuki, yamaha, mitsubishi);
    }

    private void nuevaBicicleta() {
        System.out.println("Introduce la referencia de la nueva bicicleta:");
        //Preguntamos la referencia
        int referencia = Input.leerInt();

        //Buscamos la bicicleta con la referencia
        Bicicleta bicicleta = this.registroBicicleta.buscarBicicleta(referencia);
        if (bicicleta == null) {
            crearBicicleta(referencia);
        } else {
            bicicleta.addStock();
            System.out.printf("Se ha añadido una nueva bicicleta con la referencia '%d'\n", referencia);
        }
    }

    /**
     * Este metodo solo se deberia llamar despues de comprobar que la referencia no existe
     * si la referencia ya existe dara error antes de terminar la creacion
     * @param referencia la referencia que tendra la nueva bicicleta
     */

    private Bicicleta crearBicicleta(int referencia) {
        //Preguntamos la marca
        System.out.println("Introduce la marca de la nueva bicicleta:");
        String marca = Input.leerLinea();

        //Preguntamos el modelo
        System.out.println("Introduce el modelo de la nueva bicicleta:");
        String modelo = Input.leerLinea();

        //Preguntamos el peso
        System.out.println("Introduce el peso de la nueva bicicleta:");
        double peso = Input.leerDouble(MIN_PESO, -1);

        //Preguntamos las pulgadas
        System.out.println("Introduce las pulgadas de las ruedas de la nueva bicicleta:");
        double pulgadasRuedas = Input.leerDouble(MIN_PULGADAS_RUEDAS, -1);

        //Preguntamos si la bicicleta tiene un motor
        System.out.println("Tiene la nueva bicicleta un motor ? (si/no):");
        boolean motor = Input.leerPregunta();

        //Preguntamos la fecha de creacion de la bicicleta
        System.out.println("Introduce la fecha de creacion de la nueva bicicleta:");
        Date fechaCreacion = Input.leerDate();

        //Preguntamos el precio
        System.out.println("Introduce el precio de la nueva bicicleta:");
        double precio = Input.leerDouble(MIN_PRECIO, -1);

        System.out.println("Introduce el stock de la nueva bicicleta:");
        int stock = Input.leerInt(MIN_STOCK, -1);

        Bicicleta bicicleta = new Bicicleta(referencia, marca, modelo, peso, pulgadasRuedas, motor, fechaCreacion, precio, stock);
        this.registroBicicleta.registrarBicicleta(bicicleta);

        System.out.println("Se ha creado correctamente una nueva bicicleta con la referencia: " + referencia);

        return bicicleta;
    }

    private void venderBicicleta() {
        System.out.println("Introduce la referencia de la bicicleta a vender:");
        //Preguntamos la referencia
        int referencia = Input.leerInt();

        //Buscamos la bicicleta con la referencia
        Bicicleta bicicleta = this.registroBicicleta.buscarBicicleta(referencia);
        if (bicicleta == null) {
            System.out.printf("La bicicleta con la referencia '%d' no existe!\n", referencia);
        } else {
            //Comprobamos si hay suficiente stock para vender una bicicleta
            if (bicicleta.getStock() > 0) {
                bicicleta.removeStock(); //Descontamos la unidad vendidad
                System.out.printf("bicicleta '%d' vendida correctamente\n", referencia);
            } else {
                System.out.printf("no hay suficiente stock de la bicicleta '%d' (stock: %d)\n", referencia, bicicleta.getStock());
            }
        }
    }

    private void mostrarConsultas() {
        System.out.println("***********************");
        System.out.println("** CONSULTA BICICLETA **");
        System.out.println("***********************");
        System.out.println(CONSULTAR_REFERENCIA + ".- Consultar por referencia …");
        System.out.println(CONSULTAR_MARCA + ".- Consultar por marca …");
        System.out.println(CONSULTAR_MODELO + ".- Consultar por modelo …");
        System.out.println("-----------------------------");
        System.out.println(CONSULTAR_VOLVER + ".- Volver al menú principal");
    }

    private void consultarReferencia() {
        System.out.println("Introduce la referencia de la bicicleta a buscar:");
        int referencia = Input.leerInt();

        Bicicleta bicicleta = this.registroBicicleta.buscarBicicleta(referencia);
        if (bicicleta == null) {
            System.out.println("No hay ninguna bicicleta con la referencia: " + referencia);
        } else {
            System.out.println(bicicleta);
        }
    }

    private void consultarMarca() {
        System.out.println("Introduce la marca de la bicicleta a buscar:");
        String marca = Input.leerLinea();

        Bicicleta[] bicicletas = this.registroBicicleta.buscarBicicletas(new CondicionBusqueda() {
            @Override
            public boolean valido(Bicicleta bicicleta) {
                return bicicleta.getMarca().equalsIgnoreCase(marca);
            }
        });

        if (bicicletas.length == 0) {
            System.out.println("No hay bicicletas de la marca: " + marca);
        } else {
            for (Bicicleta bicicleta : bicicletas) {
                System.out.println("------------------------");
                System.out.println(bicicleta);
            }
        }
    }

    private void consultarModelo() {
        System.out.println("Introduce el modelo de la bicicleta a buscar:");
        String modelo = Input.leerLinea();

        Bicicleta[] bicicletas = this.registroBicicleta.buscarBicicletas(bicicleta -> bicicleta.getModelo().equalsIgnoreCase(modelo));
        if (bicicletas.length == 0) {
            System.out.println("No hay bicicletas del modelo: " + modelo);
        } else {
            for (Bicicleta bicicleta : bicicletas) {
                System.out.println("------------------------");
                System.out.println(bicicleta);
            }
        }
    }

    private void consultarBicicleta() {
        int opcion;

        do {
            mostrarConsultas();
            opcion = Input.leerInt();

            if (opcion == CONSULTAR_VOLVER) {
                System.out.println("Volviendo al menu principal...");
            } else {
                switch (opcion) {
                    case CONSULTAR_REFERENCIA: {
                        consultarReferencia();
                        break;
                    }
                    case CONSULTAR_MARCA: {
                        consultarMarca();
                        break;
                    }
                    case CONSULTAR_MODELO: {
                        consultarModelo();
                        break;
                    }
                    default: {
                        System.out.println("La opción introducida no es valida!");
                        break;
                    }
                }

                Input.esperarEnter();
            }
        } while (opcion != CONSULTAR_VOLVER);
    }

    private void mostrarStock() {
        Bicicleta[] bicicletas = this.registroBicicleta.bicicletas();

        if (bicicletas.length == 0) {
            System.out.println("No hay bicicletas registradas!");
        } else {
            int marcaLongitud = 15;
            int modeloLongitud = 15;

            for (Bicicleta bicicleta : bicicletas) {
                if (bicicleta.getMarca().length() > marcaLongitud) {
                    marcaLongitud = bicicleta.getMarca().length();
                }

                if (bicicleta.getModelo().length() > modeloLongitud) {
                    modeloLongitud = bicicleta.getModelo().length();
                }
            }

            System.out.printf("%-15s %-" + marcaLongitud + "s %-" + modeloLongitud + "s %-20s\n\n", "Referencia", "Marca", "Modelo", "Stock");
            for (Bicicleta bicicleta : bicicletas) {
                System.out.printf("%-15s %-" + marcaLongitud + "s %-" + modeloLongitud + "s %-20s\n", bicicleta.getReferencia(), bicicleta.getMarca(), bicicleta.getModelo(), bicicleta.getStock());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("*************************");
        System.out.println("** GESTIÓN DE BICICLETAS **");
        System.out.println("*************************");
        System.out.println(NUEVA + ".- Añadir bicicleta ...");
        System.out.println(VENDER + ".- Vender bicicleta ...");
        System.out.println(CONSULTAR + ".- Consultar bicicleta ...");
        System.out.println(MOSTRAR + ".- Mostrar stock ...");
        System.out.println("--------------------------");
        System.out.println(SALIR + ".- Salir ...");
    }

    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = Input.leerInt();

            if (opcion == SALIR) {
                System.out.println("Saliendo...");
            } else {
                switch (opcion) {
                    case NUEVA: {
                        nuevaBicicleta();
                        break;
                    }
                    case VENDER: {
                        venderBicicleta();
                        break;
                    }
                    case CONSULTAR: {
                        consultarBicicleta();
                        break;
                    }
                    case MOSTRAR: {
                        mostrarStock();
                        break;
                    }
                    default: {
                        System.out.println("La opción introducida no es valida!");
                        break;
                    }
                }

                if (opcion != CONSULTAR) {
                    Input.esperarEnter();
                }
            }
        } while (opcion != SALIR);
    }
}
