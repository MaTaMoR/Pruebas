package me.matamor.pruebas.tema8.ejercicio72;

import me.matamor.pruebas.lib.Input;

import java.util.Arrays;

public class Ejercicio7 {

    private static final int NUEVO_PACIENTE = 1;
    private static final int ATENDER_PACIENTE = 2;
    private static final int CONSULTAS = 3;
    private static final int ALTA_METICA = 4;
    private static final int SALIR = 0;

    private static final int POR_SIP = 1;
    private static final int POR_FECHAS = 2;
    private static final int ESTADISTICAS = 3;
    private static final int HISTORIAL_MENSUAL = 4;
    private static final int VOLVER = 5;

    private static final int LONGITUD_SIP = 8;
    private static final int EDAD_MINIMA = 1;

    private final CentroDeSalud centroDeSalud;

    public Ejercicio7() {
        this.centroDeSalud = new CentroDeSalud();

        Paciente paciente1 = new Paciente("12345678", "Santiago", Paciente.Sexo.VARON, 22);
        paciente1.getRegistroAtencion().registrarAtenciones(
                new Atencion(paciente1, "Esta muy guapo")
                    .registrarConstantes(36, 60, 60, 60)
                    .registrarAlta("Se ha recuperado muy bien!"),
                new Atencion(paciente1, "Esta muy guapo de nuevo")
                        .registrarConstantes(36, 60, 60, 60));

        Paciente paciente2 = new Paciente("87654321", "Bebecita", Paciente.Sexo.MUJER, 19);
        paciente2.getRegistroAtencion().registrarAtenciones(
                new Atencion(paciente2, "Esta enferma")
                    .registrarConstantes(36, 60, 60, 60)
                    .registrarAlta("Se va pa casa"),
                new Atencion(paciente2, "Se vuelve a encontrar mal"));

        this.centroDeSalud.registrarPacientes(paciente1, paciente2);
    }

    private void nuevoPaciente() {
        System.out.println("Introduce el SIP del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        //Nos aseguramos que el paciente no este ya registrado y no ha sido dado de alta!
        Paciente paciente = this.centroDeSalud.buscarPaciente(sip);
        if (paciente == null) {
            if (this.centroDeSalud.permiteMasPacientes()) {
                crearPaciente(sip);
            } else {
                System.out.println("El centro de salur no permite más paciente, tendras que morir, lo siento!");
            }
        } else {
            Atencion atencion = paciente.getRegistroAtencion().ultimaAtencion();
            if (atencion == null || atencion.getEstado() == Atencion.Estado.ALTA) {
                crearAtencion(paciente);
            } else {
                System.out.printf("El paciente con el sip '%s' ya esta registrado en el hospital y no ha sido dado de alta!\n", sip);
            }
        }
    }

    private void crearPaciente(String sip) {
        System.out.println("Introduce el nombre del paciente:");
        String nombre = Input.leerLinea();

        Paciente.Sexo sexo = null;

        do {
            System.out.printf("Introduce el sexo del paciente %s:\n", Arrays.toString(Paciente.Sexo.values()));
            String sexoRaw = Input.leerLinea();

            try {
                sexo = Paciente.Sexo.valueOf(sexoRaw.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("El valor introducido no es un sexo valido!");
            }
        } while (sexo == null);

        System.out.println("Introduce la edad del paciente:");
        int edad = Input.leerInt(EDAD_MINIMA, -1);

        Paciente paciente = new Paciente(sip, nombre, sexo, edad);
        this.centroDeSalud.registrarPaciente(paciente);

        System.out.printf("Se ha registrado el paciente con el sip '%s'!\n", paciente.getSip());

        crearAtencion(paciente);
    }

    private void crearAtencion(Paciente paciente) {
        System.out.println("Creando nueva atención para el paciente...");

        System.out.println("Introduce la sintomatologia del paciente:");
        String sintomatologia = Input.leerLinea();

        Atencion atencion = new Atencion(paciente, sintomatologia);
        paciente.getRegistroAtencion().registrarAtencion(atencion);

        System.out.printf("Se ha registrado una nueva atencion para el paciente '%s' con la sintomatologia '%s'!\n", paciente.getSexo(), sintomatologia);
    }

    public void atenderPaciente() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente paciente = this.centroDeSalud.buscarPaciente(sip);
        if (paciente == null) {
            System.out.printf("No hay ningún paciente registrado con el sip '%s'!\n", sip);
        } else {
            Atencion atencion = paciente.getRegistroAtencion().ultimaAtencion();
            if (atencion == null || atencion.getEstado() != Atencion.Estado.ESPERANDO) {
                System.out.printf("El paciente con el sip '%s' no este esperando a ser atendido!\n", sip);
            } else {
                System.out.println("Introduce la temperatura del paciente:");
                double temperatura = Input.leerDouble();

                System.out.println("Introduce las pulsaciones del paciente:");
                double pulsaciones = Input.leerDouble();

                System.out.println("Introduce la tension sistolica del paciente:");
                double tensionSis = Input.leerDouble();

                System.out.println("Introduce la tension diastolica del paciente:");
                double tensionDia = Input.leerDouble();

                atencion.registrarConstantes(temperatura, pulsaciones, tensionSis, tensionDia);
                System.out.printf("Las constantes vitales del paciente '%s' han sido registradas!\n", sip);
            }
        }
    }

    public void altaMedica() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente paciente = this.centroDeSalud.buscarPaciente(sip);
        if (paciente == null) {
            System.out.printf("No hay ningún paciente registrado con el sip '%s'!\n", sip);
        } else {
            Atencion atencion = paciente.getRegistroAtencion().ultimaAtencion();
            if (atencion == null || atencion.getEstado() != Atencion.Estado.ESPERANDO) {
                System.out.printf("El paciente con el sip '%s' no esta siendo atendido para darle el alta!\n", sip);
            } else {
                System.out.println("Introduce la razón del alta del paciente:");
                String razonAlta = Input.leerLinea();

                atencion.registrarAlta(razonAlta);
                System.out.printf("El paciente con el sip '%s' ha sido dado de alta!\n", sip);
            }
        }
    }

    private void porSip() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente paciente = this.centroDeSalud.buscarPaciente(sip);
        if (paciente == null) {
            System.out.printf("No hay ningún paciente registrado con el sip '%s'!\n", sip);
        } else {
            Atencion[] atenciones = paciente.getRegistroAtencion().atenciones();

            if (atenciones.length == 0) {
                System.out.printf("No hay ningún registro del paciente con el sip '%s'!\n", sip);
            } else {
                String[] resultado = toStringPacientes(atenciones);
                for (String string : resultado) {
                    System.out.println(string);
                }
            }
        }
    }

    private void porFechas() {
        System.out.println("Introduce la fecha de inicio:");
        long fechaInicio = Input.leerDate().getTime();

        System.out.println("Deseas introducir fecha de finalizacion ? (si/no):");
        boolean pregunta = Input.leerPregunta();

        long fechaFinalizacion;
        if (pregunta) {
            System.out.println("Introduce la fecha de finalizacion:");
            fechaFinalizacion = Input.leerDate().getTime();
        } else {
            fechaFinalizacion = System.currentTimeMillis();
        }

        CondicionBusqueda<Atencion> condicionBusqueda =
                atencion -> atencion.getFechaEntrada() >= fechaInicio && atencion.getFechaEntrada() <= fechaFinalizacion;

        Atencion[] atenciones = this.centroDeSalud.buscarAtenciones(condicionBusqueda);

        if (atenciones.length == 0) {
            System.out.println("No se ha atendido ningún paciente entre las fechas introducidas!");
        } else {
            String[] resultado = toStringPacientes(atenciones);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private void historico() {
        System.out.println("Generando historico mensual...");

        Atencion[] atenciones = this.centroDeSalud.atenciones();
        if (atenciones.length == 0) {
            System.out.println("No se ha antendido ningún paciente el último mes!");
        } else {
            String[] resultado = toStringPacientes(atenciones);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private void estadisticas() {
        System.out.println("Generando estadisticas mensuales...");

        Paciente[] pacientes = this.centroDeSalud.pacientes();
        if (pacientes.length == 0) {
            System.out.println("No se ha antendido ningún paciente el último mes!");
        } else {
            double[] valores = new double[4];
            int[] cantidades = new int[2];

            for (Atencion atencion : this.centroDeSalud.atenciones()) {
                if (atencion.tieneConstantes()) {
                    valores[0] += atencion.getConstanteVital(Atencion.ConstanteVital.TEMPERATURA);
                    valores[1] += atencion.getConstanteVital(Atencion.ConstanteVital.PULSACIONES);
                    valores[2] += (atencion.getConstanteVital(Atencion.ConstanteVital.TENSION_SIS) +
                            atencion.getConstanteVital(Atencion.ConstanteVital.TENSION_DIA)) / 2;

                    cantidades[0]++;
                }

                //Dependiendo de como lo quieras hacer puedes calcular esto solo por paciente y no por cada vez que se atiende a un paciente
                valores[3] += atencion.getPaciente().getEdad();

                if (atencion.getPaciente().getSexo() == Paciente.Sexo.VARON) {
                    cantidades[1]++;
                }
            }

            double[] medias = new double[6];
            medias[0] = valores[0] / cantidades[0];
            medias[1] = valores[1] / cantidades[0];
            medias[2] = valores[2] / cantidades[0];
            medias[3] = valores[3] / pacientes.length;
            medias[4] = (double) cantidades[1] * 100 / pacientes.length;
            medias[5] = 100 - medias[4];

            String[][] matriz = new String[2][6];
            matriz[0] = new String[] {
                    "Media temperatura",
                    "Media pulsaciones",
                    "Media tension",
                    "Media edad",
                    "Porcentaje varones",
                    "Porcentaje mujeres"
            };

            for (int i = 0; medias.length > i; i++) {
                matriz[1][i] = String.format("%.1f", medias[i]);
            }

            String[] resultado = formatearMatriz(matriz);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private String[] toStringPacientes(Atencion[] atenciones) {
        String[][] matriz = formatearPacientes(atenciones);
        return formatearMatriz(matriz);
    }

    private String[][] formatearPacientes(Atencion[] atenciones) {
        String[] cabezera = new String[] {
                "SIP",
                "Nombre",
                "Sexo",
                "Fecha de entrada",
                "Hora de salida",
                "Edad",
                "Sintomatologia",
                "Temp",
                "ppm",
                "TenSis",
                "TenDias",
                "Fecha de alta",
                "Hora de alta",
                "Motivo de alta"
        };

        String[][] valoresPacientes = new String[atenciones.length + 1][cabezera.length];
        valoresPacientes[0] = cabezera;

        for (int i = 0; atenciones.length > i; i++) {
            Atencion atencion = atenciones[i];
            valoresPacientes[i + 1] = new String[] {
                    atencion.getPaciente().getSip(), //Sip
                    atencion.getPaciente().getNombre(), //Nombre
                    atencion.getPaciente().getSexo().name().substring(0, 1), //Sexo
                    Input.dateFormat.format(atencion.getFechaEntrada()), //Fecha entrada
                    Input.hourFormat.format(atencion.getFechaEntrada()), //Hora entrada
                    String.valueOf(atencion.getPaciente().getEdad()), //Edad
                    atencion.getSintomatologia(), //Sintomatologia
                    (atencion.tieneConstantes() ? String.valueOf(atencion.getConstanteVital(Atencion.ConstanteVital.TEMPERATURA)) : ""), //Temperatura
                    (atencion.tieneConstantes() ? String.valueOf(atencion.getConstanteVital(Atencion.ConstanteVital.PULSACIONES)) : ""), //Pulsaciones
                    (atencion.tieneConstantes() ? String.valueOf(atencion.getConstanteVital(Atencion.ConstanteVital.TENSION_SIS)) : ""), //TensionSis
                    (atencion.tieneConstantes() ? String.valueOf(atencion.getConstanteVital(Atencion.ConstanteVital.TENSION_DIA)) : ""), //TensionDia
                    (atencion.tieneAlta() ? Input.dateFormat.format(atencion.getFechaSalida()) : ""), //Fecha salida
                    (atencion.tieneAlta() ? Input.hourFormat.format(atencion.getFechaSalida()) : ""), //Hora salida
                    (atencion.tieneAlta() ? atencion.getAlta() : "") //Alta
            };
        }

        return valoresPacientes;
    }

    private String[] formatearMatriz(String[][] matriz) {
        String[] resultado = new String[matriz.length];

        if (matriz.length > 1) {
            int[] longitudes = new int[matriz[0].length];

            for (String[] grupo : matriz) {
                for (int i = 0; grupo.length > i; i++) {
                    if (grupo[i].length() > longitudes[i]) {
                        longitudes[i] = grupo[i].length();
                    }
                }
            }

            for (int i = 0; longitudes.length > i; i++) {
                longitudes[i] = longitudes[i] + 3;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int longitud : longitudes) {
                if (longitud == 0) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append("%-").append(longitud).append("s ");
                }
            }

            String formato = stringBuilder.toString();
            stringBuilder.setLength(0);

            for (int i = 0; matriz.length > i; i++) {
                resultado[i] = String.format(formato, matriz[i]);
            }
        }

        return resultado;
    }

    private int mostrarConsultas() {
        do {
            System.out.println("***************");
            System.out.println("** CONSULTAS **");
            System.out.println("***************");
            System.out.println(POR_SIP + ". Por Sip …");
            System.out.println(POR_FECHAS + ". Por fechas …");
            System.out.println(ESTADISTICAS + ". Estadisticas");
            System.out.println(HISTORIAL_MENSUAL + ". Mostrar histórico mensual");
            System.out.println("--------------------------------");
            System.out.println(VOLVER + ". Volver al menú principal");

            int opcion = Input.leerInt();

            switch (opcion) {
                case POR_SIP:
                case POR_FECHAS:
                case ESTADISTICAS:
                case HISTORIAL_MENSUAL:
                case VOLVER:
                    return opcion;
                default: {
                    System.out.println("La opción introducida no es valida!");
                    break;
                }
            }
        } while (true);
    }

    private void consultas() {
        int opcion;

        do {
            opcion = mostrarConsultas();

            if (opcion == VOLVER) {
                System.out.println("Volviendo al menú principal...");
            } else {
                switch (opcion) {
                    case POR_SIP: {
                        porSip();
                        break;
                    }
                    case POR_FECHAS: {
                        porFechas();
                        break;
                    }
                    case ESTADISTICAS: {
                        estadisticas();
                        break;
                    }
                    case HISTORIAL_MENSUAL: {
                        historico();
                        break;
                    }
                }

                Input.esperarEnter();
            }
        } while (opcion != VOLVER);
    }
    
    private int mostrarMenu() {
        do {
            System.out.println("********************");
            System.out.println("** URGENCIAS **");
            System.out.println("********************");
            System.out.println(NUEVO_PACIENTE + ". Nuevo paciente …");
            System.out.println(ATENDER_PACIENTE + ". Atender paciente …");
            System.out.println(CONSULTAS + ". Consultas …");
            System.out.println(ALTA_METICA + ". Alta médica …");
            System.out.println("-----------------------------");
            System.out.println(SALIR + ". Salir");

            int opcion = Input.leerInt();

            switch (opcion) {
                case NUEVO_PACIENTE:
                case ATENDER_PACIENTE:
                case CONSULTAS:
                case ALTA_METICA:
                case SALIR:
                    return opcion;
                default: {
                    System.out.println("La opción introducida no es valida!");
                    break;
                }
            }
        } while (true);
    }

    public void ejecutar() {
        int opcion;

        do {
            opcion = mostrarMenu();

            if (opcion == SALIR) {
                System.out.println("Saliendo...");
            } else {
                switch (opcion) {
                    case NUEVO_PACIENTE: {
                        nuevoPaciente();
                        break;
                    }
                    case ATENDER_PACIENTE: {
                        atenderPaciente();
                        break;
                    }
                    case CONSULTAS: {
                        consultas();
                        break;
                    }
                    case ALTA_METICA: {
                        altaMedica();
                        break;
                    }
                }

                Input.esperarEnter();
            }
        } while (opcion != SALIR);
    }
}
