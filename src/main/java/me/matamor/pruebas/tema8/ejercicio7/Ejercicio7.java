package me.matamor.pruebas.tema8.ejercicio7;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.formato.FormatEntry;
import me.matamor.pruebas.lib.formato.SimpleFormatEntry;

import java.text.ParseException;
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

        Paciente paciente1 = new Paciente("12345678", "Santiago", Paciente.Sexo.VARON, 22, "Muy guapo");
        paciente1.registrarConstantes(36, 60, 60, 60);
        paciente1.registrarAlta("Se ha recuperado muy bien!");
        try {
            paciente1.setFechaEntrada(Input.fullDate.parse("16:22:05 21/01/2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente paciente2 = new Paciente("12345678", "Santiago", Paciente.Sexo.VARON, 22, "Muy guapo de nuevo");
        paciente2.registrarConstantes(39, 80, 75, 64);
        try {
            paciente2.setFechaEntrada(Input.fullDate.parse("12:53:23 30/01/2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente paciente3 = new Paciente("87654321", "Cristobal", Paciente.Sexo.VARON, 20, "Esta enfermo");
        Paciente paciente4 = new Paciente("86724524", "Daniel", Paciente.Sexo.VARON, 20, "Se encuentra mal");

        Paciente paciente5 = new Paciente("14216432", "Emilio", Paciente.Sexo.VARON, 20, "Lo ha dejado la novia");
        paciente5.registrarConstantes(36, 60, 60, 60);
        paciente5.registrarAlta("Ya se ha calmado");

        Paciente paciente6 = new Paciente("41411902", "ChicaImaginaria", Paciente.Sexo.MUJER, 19, "For the sake of stats");

        this.centroDeSalud.registrarPacientes(paciente1, paciente2, paciente3, paciente4, paciente5, paciente6);
    }

    private void printNuevo() {
        SimpleFormatEntry<Paciente> test = new SimpleFormatEntry<Paciente>("") {
            @Override
            public String getValor(Paciente objeto) {
                return null;
            }
        };

        FormatEntry<Paciente>[] entries = (FormatEntry<Paciente>[]) new Object[10];

    }

    private void nuevoPaciente() {
        System.out.println("Introduce el SIP del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        //Nos aseguramos que el paciente no este ya registrado y no ha sido dado de alta!
        Paciente[] pacientes = this.centroDeSalud.buscarPaciente(sip, Paciente.Estado.ESPERANDO, Paciente.Estado.ATENDIENDO);
        if (pacientes.length == 0) {
            if (this.centroDeSalud.permiteMasPacientes()) {
                crearPaciente(sip);
            } else {
                System.out.println("El centro de salur no permite m??s paciente, tendras que morir, lo siento!");
            }
        } else {
            System.out.printf("El paciente con el sip '%s' ya esta registrado en el hospital y no ha sido dado de alta!\n", sip);
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

        System.out.println("Introduce la sintomatologia del paciente:");
        String sintomatologia = Input.leerLinea();

        Paciente paciente = new Paciente(sip, nombre, sexo, edad, sintomatologia);
        this.centroDeSalud.registrarPaciente(paciente);

        System.out.printf("Se ha registrado el paciente con el sip '%s'!\n", sip);
    }

    public void atenderPaciente() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente paciente = this.centroDeSalud.buscarPaciente(sip, Paciente.Estado.ESPERANDO);
        if (paciente == null) {
            System.out.printf("No hay ning??n paciente con el sip '%s' que este esperando a ser atendido!\n", sip);
        } else {
            System.out.println("Introduce la temperatura del paciente:");
            double temperatura = Input.leerDouble();

            System.out.println("Introduce las pulsaciones del paciente:");
            double pulsaciones = Input.leerDouble();

            System.out.println("Introduce la tension sistolica del paciente:");
            double tensionSis = Input.leerDouble();

            System.out.println("Introduce la tension diastolica del paciente:");
            double tensonDia = Input.leerDouble();

            paciente.registrarConstantes(temperatura, pulsaciones, tensionSis, tensonDia);
            System.out.printf("Las constantes vitales del paciente '%s' han sido registradas!\n", sip);
        }
    }

    public void altaMedica() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente paciente = this.centroDeSalud.buscarPaciente(sip, Paciente.Estado.ATENDIENDO);
        if (paciente == null) {
            System.out.printf("No hay ning??n paciente con el sip '%s' siendo atendido para darle el alta!\n", sip);
        } else {
            System.out.println("Introduce la raz??n del alta del paciente:");
            String razonAlta = Input.leerLinea();

            paciente.registrarAlta(razonAlta);
            System.out.printf("El paciente con el sip '%s' ha sido dado de alta!\n", sip);
        }
    }

    private void porSip() {
        System.out.println("Introduce el sip del paciente:");
        String sip = Input.leerLinea(LONGITUD_SIP, LONGITUD_SIP);

        Paciente[] pacientes = this.centroDeSalud.buscarPaciente(sip);
        if (pacientes.length == 0) {
            System.out.printf("No hay ning??n registro del paciente con el sip '%s'!\n", sip);
        } else {
            String[] resultado = toStringPacientes(pacientes);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private void porFechas() {
        System.out.println("Introduce la fecha de inicio:");
        long fechaInicio = Input.leerFullDate().getTime();

        System.out.println("Deseas introducir fecha de finalizacion ? (si/no):");
        boolean pregunta = Input.leerPregunta();

        long fechaFinalizacion;
        if (pregunta) {
            System.out.println("Introduce la fecha de finalizacion:");
            fechaFinalizacion = Input.leerFullDate().getTime();
        } else {
            fechaFinalizacion = System.currentTimeMillis();
        }

        CondicionBusqueda<Paciente> condicionBusqueda = new CondicionBusqueda<Paciente>() {
            @Override
            public boolean valido(Paciente value) {
                return value.getFechaEntrada() >= fechaInicio && value.getFechaEntrada() <= fechaFinalizacion;
            }
        };

        Paciente[] pacientes = this.centroDeSalud.buscarPacientes(condicionBusqueda);
        if (pacientes.length == 0) {
            System.out.println("No se ha atendido ning??n paciente entre las fechas introducidas!");
        } else {
            String[] resultado = toStringPacientes(pacientes);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private void historico() {
        System.out.println("Generando historico mensual...");

        Paciente[] pacientes = this.centroDeSalud.pacientes();
        if (pacientes.length == 0) {
            System.out.println("No se ha antendido ning??n paciente el ??ltimo mes!");
        } else {
            String[] resultado = toStringPacientes(pacientes);
            for (String string : resultado) {
                System.out.println(string);
            }
        }
    }

    private void estadisticas() {
        System.out.println("Generando estadisticas mensuales...");

        Paciente[] pacientes = this.centroDeSalud.pacientes();
        if (pacientes.length == 0) {
            System.out.println("No se ha antendido ning??n paciente el ??ltimo mes!");
        } else {
            double[] valores = new double[4];
            int[] cantidades = new int[2];

            for (Paciente paciente : pacientes) {
                if (paciente.tieneConstantes()) {
                    valores[0] += paciente.getConstanteVital(Paciente.ConstanteVital.TEMPERATURA);
                    valores[1] += paciente.getConstanteVital(Paciente.ConstanteVital.PULSACIONES);
                    valores[2] += (paciente.getConstanteVital(Paciente.ConstanteVital.TENSION_SIS) +
                            paciente.getConstanteVital(Paciente.ConstanteVital.TENSION_DIA)) / 2;

                    cantidades[0]++;
                }

                valores[3] += paciente.getEdad();

                if (paciente.getSexo() == Paciente.Sexo.VARON) {
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

    private String[] toStringPacientes(Paciente[] pacientes) {
        String[][] matriz = formatearPacientes(pacientes);
        return formatearMatriz(matriz);
    }

    private String[][] formatearPacientes(Paciente[] pacientes) {
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

        String[][] valoresPacientes = new String[pacientes.length + 1][cabezera.length];
        valoresPacientes[0] = cabezera;

        for (int i = 0; pacientes.length > i; i++) {
            Paciente paciente = pacientes[i];
            valoresPacientes[i + 1] = new String[] {
                    paciente.getSip(), //Sip
                    paciente.getNombre(), //Nombre
                    paciente.getSexo().name().substring(0, 1), //Sexo
                    Input.dateFormat.format(paciente.getFechaEntrada()), //Fecha entrada
                    Input.hourFormat.format(paciente.getFechaEntrada()), //Hora entrada
                    String.valueOf(paciente.getEdad()), //Edad
                    paciente.getSintomatologia(), //Sintomatologia
                    (paciente.tieneConstantes() ? String.valueOf(paciente.getConstanteVital(Paciente.ConstanteVital.TEMPERATURA)) : ""), //Temperatura
                    (paciente.tieneConstantes() ? String.valueOf(paciente.getConstanteVital(Paciente.ConstanteVital.PULSACIONES)) : ""), //Pulsaciones
                    (paciente.tieneConstantes() ? String.valueOf(paciente.getConstanteVital(Paciente.ConstanteVital.TENSION_SIS)) : ""), //TensionSis
                    (paciente.tieneConstantes() ? String.valueOf(paciente.getConstanteVital(Paciente.ConstanteVital.TENSION_DIA)) : ""), //TensionDia
                    (paciente.tieneAlta() ? Input.dateFormat.format(paciente.getFechaSalida()) : ""), //Fecha salida
                    (paciente.tieneAlta() ? Input.hourFormat.format(paciente.getFechaSalida()) : ""), //Hora salida
                    (paciente.tieneAlta() ? paciente.getAlta() : "") //Alta
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
            System.out.println(POR_SIP + ". Por Sip ???");
            System.out.println(POR_FECHAS + ". Por fechas ???");
            System.out.println(ESTADISTICAS + ". Estadisticas");
            System.out.println(HISTORIAL_MENSUAL + ". Mostrar hist??rico mensual");
            System.out.println("--------------------------------");
            System.out.println(VOLVER + ". Volver al men?? principal");

            int opcion = Input.leerInt();

            switch (opcion) {
                case POR_SIP:
                case POR_FECHAS:
                case ESTADISTICAS:
                case HISTORIAL_MENSUAL:
                case VOLVER:
                    return opcion;
                default: {
                    System.out.println("La opci??n introducida no es valida!");
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
                System.out.println("Volviendo al men?? principal...");
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
            System.out.println(NUEVO_PACIENTE + ". Nuevo paciente ???");
            System.out.println(ATENDER_PACIENTE + ". Atender paciente ???");
            System.out.println(CONSULTAS + ". Consultas ???");
            System.out.println(ALTA_METICA + ". Alta m??dica ???");
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
                    System.out.println("La opci??n introducida no es valida!");
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
