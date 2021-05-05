package me.matamor.pruebas.tema8.ejercicio3;

import com.github.javafaker.Faker;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.Randomizer;
import me.matamor.pruebas.lib.ThreadUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Ejercicio03 {
    /** Objeto de la clase Scanner que nos permite leer desde la entrada estándar */

    /** Número mínimo para considerar válido un NIA */
    private static final int MIN_NIA = 1000;
    /** Número máximo para considerar válido un NIA */
    private static final int MAX_NIA = 199999999;

    private static final int MIN_EDAD = 1;
    private static final int MAX_EDAD = 99;

    /** Longitud mínima del nombre */
    private static final int MIN_NOMBRE = 2;
    /** Longitud mínima de los apellidos */
    private static final int MIN_APELLIDOS = 3;
    /** Longitud mínima de un grupo */
    private static final int MIN_GRUPO = 3;
    /** Longitud mínima de un número de teléfono */
    private static final int MIN_TELEFONO = 8;
    /** Tamaño inicial del array de alumnos */
    private static final int INITIAL_ALUMNO_SIZE = 1000;
    /** Número de alumnos aleatorios a generar para tener casos de prueba */
    private static final int ALUMNOS_ALEATORIOS = 10;
    /** Indica como crecerá el array cada vez que llegue a su tamaño máximo */
    private static final int GROW_FACTOR = 2;
    /** Array auxiliar útil para generar grupos pseudo-aleatorios para casos de prueba */
    private static final String[] GRUPOS = {"1DAM", "2DAM", "1SMX", "2SMX", "1DAW", "2DAW"};
    /** El array donde se guardarán todos los alumnos */
    private Alumno[] alumnos;
    /** Contador para llevar el control de la cantidad de alumnos que han sido añadidos al array */
    private int nAlumnos;

    /**
     * Constructor de la clase Ejercicio03
     * Recuerda que la función de los constructores es inicializar los atributos a los valores deseados.
     */
    public Ejercicio03() {
        alumnos = new Alumno[INITIAL_ALUMNO_SIZE];
        nAlumnos = 0;
        generarAlumnosAleatorios(ALUMNOS_ALEATORIOS);
    }

    /**
     * Genera alumnos con datos aleatorios utilizando la librería
     * Java Faker.
     * Recuerda que para utilizar dicha librería debes importarla primero.
     * La forma de realizar dicha importación depende del gestor de proyectos
     * (Gradle, Maven, etc.) utilizado.
     * En este caso se ha utilizado Gradle, por lo que hay que añadir en el apartado
     * dependencies del archivo build.gradle:
     * dependencies {
     *     ...
     *     implementation 'com.github.javafaker:javafaker:1.0.2'
     * }
     * @param alumnosAleatorios número de alumnos a generar
     */
    private void generarAlumnosAleatorios(int alumnosAleatorios) {
        Faker faker = new Faker(new Locale("es"));
        int nia;
        String nombre;
        String apellidos;
        GregorianCalendar fecha = null;
        String grupo;
        String telefono;
        boolean valido;
        for(int i = 0; i < alumnosAleatorios; i++) {
            nia = faker.number().numberBetween(MIN_NIA, MAX_NIA);
            nombre = faker.name().firstName();
            apellidos = faker.name().lastName();
            fecha = new GregorianCalendar();
            fecha.setTime(faker.date().birthday(18, 80));
            grupo = GRUPOS[Randomizer.randomInt(0, GRUPOS.length -1)];
            telefono = faker.phoneNumber().phoneNumber();
            alumnos[nAlumnos] = new Alumno(nia, nombre, apellidos, fecha, grupo, telefono);
            nAlumnos++;
        }
    }

    /**
     * Método que invoremos para ejecutar este ejercicio desde la clase Main
     * Puede tener cualquier nombre. Lo importante es que le demos el mismo
     * nombre en todos los ejercicios.
     */
    public void execute() {
        int opcion;
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:
                    nuevoAlumno();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    consultas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
            }

        } while (opcion != 0);
    }

    /**
     * Muestra el menú de consultas y ejecuta la opción indicada por el usuario
     */
    private void consultas() {
        int opcion;
        do {
            opcion = menuConsultas();
            switch (opcion) {
                case 1:
                    consultaPorGrupo();
                    break;
                case 2:
                    consultaPorEdad();
                    break;
                case 3:
                    consultaPorNia();
                    break;
                case 4:
                    consultaPorApellidos();
                    break;
                case 5:
                    mostrarTodosLosAlumnos();
                    break;
            }
            ThreadUtils.sleep();
        } while(opcion != 0);
    }

    /**
     * Solicita los apellidos de un alumno y realiza una búsqueda
     * de todos los alumnos que cumplen dicho criterio
     */
    private void consultaPorApellidos() {
        String apellidos;
        System.out.println("*** CONSULTA POR APELLIDOS ***");
        System.out.println("Apellidos: ");
        apellidos = Input.leerLinea();
        Alumno[] alum = buscarPorApellidos(apellidos);
        if(alum != null) {
            for(int i = 0; i < alum.length; i++) {
                System.out.println(alum[i]);
            }
        }
    }

    /**
     * Busca los alumnos con los apellidos indicados
     * @param apellidos apellidos de los alumnos que queremos buscar
     * @return Array con los alumnos que cumplen los criterios de la búsqueda
     */
    private Alumno[] buscarPorApellidos(String apellidos) {
        Alumno[] resultado = null;
        int encontrados = 0;
        int j = 0;
        for(int i = 0; i < nAlumnos; i++) {
            if(alumnos[i].getApellidos().equalsIgnoreCase(apellidos)) {
                encontrados++;
            }
        }
        if(encontrados > 0) {
            resultado = new Alumno[encontrados];
            for(int i = 0; i < nAlumnos; i++) {
                if(alumnos[i].getApellidos().equalsIgnoreCase(apellidos)) {
                    resultado[j] = alumnos[i];
                    j++;
                }
            }
        }
        return resultado;
    }

    /**
     * Busca el alumno con el nia indicado
     * @param nia del alumno a buscar
     * @return un número mayor o igual que 0 indicando la posición
     * del array donde se encuentra dicho alumno.
     * Devuelve -1 si no se ha encontrado.
     */
    public int buscarPorNia(int nia) {
        for(int i = 0; i < nAlumnos; i++) {
            if(alumnos[i].getNia() == nia) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Solicita el nia a buscar, realiza la búsqueda y muestra
     * los datos del alumno que cumple dicho criterio
     */
    private void consultaPorNia() {
        int nia;
        System.out.println("*** CONSULTA POR NIA ***");
        System.out.println("Nia: ");
        nia = Input.leerInt(MIN_NIA, MAX_NIA);
        int posicion = buscarPorNia(nia);
        if(posicion >= 0) {
            System.out.println(alumnos[posicion]);
        }
    }

    /**
     * Busca los alumnos con la edad indicada
     * @param edad Edad de los alumnos que queremos buscar
     * @return Array con los alumnos que cumplen los criterios de la búsqueda
     */
    public Alumno[] buscarPorEdad(int edad) {
        Alumno[] resultado = null;
        int encontrados = 0;
        int j = 0;
        for(int i = 0; i < nAlumnos; i++) {
            if(alumnos[i].getEdad() == edad) {
                encontrados++;
            }
        }
        if(encontrados > 0) {
            resultado = new Alumno[encontrados];
            for (int i = 0; i < nAlumnos; i++) {
                if (alumnos[i].getEdad() == edad) {
                    resultado[j] = alumnos[i];
                    j++;
                }
            }
        }
        return resultado;
    }

    /**
     * Solicita la edad de un alumno y realiza una búsqueda
     * de todos los alumnos que cumplen dicho criterio
     */
    private void consultaPorEdad() {
        int edad;
        System.out.println("*** CONSULTA POR EDAD ***");
        System.out.println("Edad: ");
        edad = Input.leerInt(MIN_EDAD, MAX_EDAD);
        Alumno[] alum = buscarPorEdad(edad);
        if(alum != null) {
            for (int i = 0; i < alum.length; i++) {
                System.out.println(alum[i]);
            }
        }
    }

    /**
     * Busca los alumnos que pertenecen al grupo indicado
     * @param grupo Grupo de los alumnos que queremos buscar
     * @return Array con los alumnos que cumplen los criterios de la búsqueda
     */
    public Alumno[] buscarPorGrupo(String grupo) {
        Alumno[] resultado = null;
        int encontrados = 0;
        int j = 0;
        for(int i = 0; i < nAlumnos; i++) {
            if(alumnos[i].getGrupo().equalsIgnoreCase(grupo)) {
                encontrados++;
            }
        }
        if(encontrados > 0) {
            resultado = new Alumno[encontrados];
            for(int i = 0; i < nAlumnos; i++) {
                if(alumnos[i].getGrupo().equalsIgnoreCase(grupo)) {
                    resultado[j] = alumnos[i];
                    j++;
                }
            }
        }
        return resultado;
    }

    /**
     * Solicita un grupo y realiza una búsqueda
     * de todos los alumnos que cumplen dicho criterio
     */
    private void    consultaPorGrupo() {
        String grupo;
        System.out.println("*** CONSULTA POR GRUPO ***");
        System.out.println("Grupo: ");
        grupo = Input.leerLinea();
        for(int i = 0; i < nAlumnos; i++) {
            if(alumnos[i].getGrupo().equalsIgnoreCase(grupo)) {
                System.out.println(alumnos[i]);
            }
        }
        Alumno[] alum = buscarPorGrupo(grupo);
        if(alum != null) {
            for(int i = 0; i < alum.length; i++) {
                System.out.println(alum[i]);
            }
        }
    }

    /**
     * Muestra el menú de consultas y solicita una opción al usuario
     * @return La opción elegida por el usuario validada
     */
    private int menuConsultas() {
        int opcion;
        do {
            System.out.println("***************");
            System.out.println("** CONSULTAS **");
            System.out.println("***************");
            System.out.println("1. Por grupo...");
            System.out.println("2. Por edad...");
            System.out.println("3. Por nia...");
            System.out.println("4. Por apellidos...");
            System.out.println("5. Mostrar todos...");
            System.out.println("----------------");
            System.out.println("0. Volver al menú principal");
            System.out.println("Elija una opción:");
            opcion = Input.leerInt();
            if(opcion < 0 || opcion > 5) {
                System.out.println("Elija una opción del menú [0-5]");
            }
        } while(opcion < 0 || opcion > 5);
        return opcion;
    }

    /**
     * Muestra los datos de todos los alumnos
     */
    public void mostrarTodosLosAlumnos() {
        for(int i = 0; i < nAlumnos; i++) {
            System.out.println(alumnos[i].toString());
        }
    }

    /**
     * Solicita el nia del alumno a dar de baja, lo busca y en caso de
     * encontrarlo, lo elimina del array.
     * Para aplicar el borrado en un array (estructura estática, es decir
     * su tamaño es fijo y no puede variar a lo largo de la ejución de un programa)
     * podemos adoptar dos estrategias:
     * - Opción A) Al borrar un elemento, simplemente marcarlo como null y tener en cuenta que
     *   pueden existir elementos del array a null.
     *   * Ventajas: borrado rápido.
     *   * Desventajas: búsquedas lentas. Una posible mejora es llevar la cuenta de los elementos
     *     efectivos que hay en el array.
     * - Opción B) Al borrar un elemento desplazamos todos los elementos que queden a la derecha
     *   del elemento borrado una posición hacia la izquierda.
     *   * Ventajas: búsquedas rápidas
     *   *  Desventajas: borrado más lento.
     *
     *  En este caso hemos implementado la opción B ya que las búsquedas serán operaciones más comunes
     *  que los borrados.
     */
    private void bajaAlumno() {
        int nia;
        System.out.println("*** BAJA ALUMNO ***");
        System.out.println("Nia: ");
        nia = Input.leerInt(MIN_NIA, MAX_NIA);
        int posicion = buscarPorNia(nia);
        if(posicion >= 0) {
            for(int i = posicion; i < alumnos.length -1; i++) {
                alumnos[i] = alumnos[i+1];
            }
            nAlumnos--;
            System.out.println("El alumno con nia "+ nia + " ha sido dado de baja correctamente");
        } else {
            System.out.println("No existe ningún alumno con el nia " + nia);
        }
    }

    /**
     * Solicita los datos de un alumno, los valida y crea un nuevo alumno con los datos indicados.
     * En caso de que el array esté lleno, crea un nuevo array ampliando el tamaño del anterior
     * en un factor de GROW_FACTOR, copia todos los alumnos (punteros a alumnos en realidad) al
     * nuevo array, y luego establece el array alumnos como el nuevo array creado.
     */
    private void nuevoAlumno() {
        int nia;
        String nombre;
        String apellidos;
        String fecha;
        GregorianCalendar fechaGregorian = null;
        String grupo;
        String telefono;
        boolean valido;
        System.out.println("*** NUEVO ALUMNO ***");

        do {
            System.out.println("Nia: ");
            nia = Input.leerInt(MIN_NIA, MAX_NIA);
            valido = buscarPorNia(nia) == -1;
            if(!valido) {
                System.out.println("El nia introducido ya existe");
            } else {
                valido = (nia > MIN_NIA && nia < MAX_NIA);
                if (!valido) {
                    System.out.println("El nia debe de estar comprendido entre " + MIN_NIA + " y " + MAX_NIA);
                }
            }
        } while(!valido);

        do {
            System.out.println("Nombre: ");
            nombre = Input.leerLinea(MIN_NOMBRE, -1);
            valido = (nombre.length() >= MIN_NOMBRE);
            if(!valido) {
                System.out.println("El nombre debe tener al menos " + MIN_NOMBRE + " caracteres");
            }
        } while(!valido);

        do {
            System.out.println("Apellidos: ");
            apellidos = Input.leerLinea(MIN_APELLIDOS, -1);
            valido = (apellidos.length() >= MIN_APELLIDOS);
            if(!valido) {
                System.out.println("Los apellidos debe tener al menos " + MIN_APELLIDOS + " caracteres");
            }
        } while(!valido);

        System.out.println("Fecha de nacimiento: ");
        fechaGregorian = new GregorianCalendar();
        fechaGregorian.setTime(Input.leerDate());

        do {
            System.out.println("Grupo: ");
            grupo = Input.leerLinea(MIN_GRUPO, -1);
            valido = (grupo.length() >= MIN_GRUPO);
            if(!valido) {
                System.out.println("El grupo debe tener al menos " + MIN_GRUPO + " caracteres");
            }
        } while(!valido);

        do {
            System.out.println("Teléfono: ");
            telefono = Input.leerLinea(MIN_TELEFONO, -1);
            valido = (telefono.length() >= MIN_TELEFONO);
            if(!valido) {
                System.out.println("El telefono debe tener al menos " + MIN_TELEFONO + " caracteres");
            }
        } while(!valido);

        Alumno a = new Alumno(nia, nombre, apellidos, fechaGregorian, grupo, telefono);
        if(nAlumnos == alumnos.length) {
            Alumno[] alumnosAux = new Alumno[alumnos.length * GROW_FACTOR];
            for(int i = 0; i < alumnos.length; i++) {
                alumnosAux[i] = alumnos[i];
            }
            alumnos = alumnosAux;
        }
        alumnos[nAlumnos] = a;
        nAlumnos++;
    }

    public Alumno[] alumnos() {
        Alumno[] alumnos = new Alumno[this.nAlumnos];
        for (int i = 0; alumnos.length > i; i++) {
            alumnos[i] = this.alumnos[i];
        }

        return alumnos;
    }

    /**
     * Muestra el menú principal de la aplicación, solicita una opción al usuario
     * @return La opción (validada) elegida por el usuario
     */
    public int menuPrincipal() {
        int opcion;
        do {
            System.out.println("************************");
            System.out.println("** GESTIÓN DE ALUMNOS **");
            System.out.println("************************");
            System.out.println("1. Nuevo alumno...");
            System.out.println("2. Baja de alumno...");
            System.out.println("3. Consultas...");
            System.out.println("------------------------");
            System.out.println("0. Salir\n");
            System.out.println("Elija una opción: ");
            opcion = Input.leerInt();
            if (opcion < 0 || opcion > 3) {
                System.out.println("Opción no válida. Elija una opción del menú");
                Input.esperarEnter();
            }
        } while (opcion < 0 || opcion > 3);

        return opcion;
    }

    public static void generarGuardar() {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("text.txt"), StandardCharsets.UTF_8)) {
            Ejercicio03 ejercicio03 = new Ejercicio03();
            ejercicio03.generarAlumnosAleatorios(10);

            Alumno[] alumnos = ejercicio03.alumnos();
            for (Alumno alumno : alumnos) {
                writer.write("1\n");
                writer.write(String.valueOf(alumno.getNia()));
                writer.append("\n");
                writer.write(alumno.getNombre());
                writer.append("\n");
                writer.write(alumno.getApellidos());
                writer.append("\n");
                writer.write(Input.dateFormat.format(alumno.getFechaNacimiento().getTime()));
                writer.append("\n");
                writer.write(alumno.getGrupo());
                writer.append("\n");
                writer.write(alumno.getTelefono());
                writer.append("\n");
            }

            writer.close();
            System.out.println("done bebecito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Ejercicio03 ejercicio03 = new Ejercicio03();
        ejercicio03.execute();
    }
}
