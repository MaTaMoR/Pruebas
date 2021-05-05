package me.matamor.pruebas.test;

import java.util.Random;

public class Prueba {

    public static void main(String[] args) {
        final int ALUMNOS = 20;
        final int MODULOS = 3;

        final double MIN_NOTA = 0;
        final double MAX_NOTA = 10;

        double[][] notasAlumnos = new double[ALUMNOS][MODULOS];
        generarNotas(notasAlumnos, MIN_NOTA, MAX_NOTA);

        double[] notasMaximas = new double[MODULOS]; //Aqui guardaremos las notas maximas
        double[] notasMedias = new double[MODULOS]; //Aqui guardaremos las notas medias

        //Generamos el header
        String[] modulos = new String[MODULOS + 1];
        for (int i = 0; modulos.length > i; i++) {
            modulos[i] = "MOD " + i;
        }

        modulos[modulos.length - 1] = "MEDIA"; //Añadimos el header de la media
        System.out.printf("%-15s %s\n\n", "", String.format(("%-10s").repeat(modulos.length), modulos)); //Mostramos los resultados

        //Calculamos los datos

        for (int alumno = 0; notasAlumnos.length > alumno; alumno++) {
            double[] notasModulos = notasAlumnos[alumno];
            String[] notasFormateadas = new String[notasModulos.length + 1]; //Aqui guardaremos las notas formateadas
            double media = 0;

            //Hacemos la suma de los diferentes modulos para luego calcular la media
            for (int modulo = 0; notasModulos.length > modulo; modulo++) {
                double nota = notasModulos[modulo];

                media += nota; //Sumamos la nota a la media del alumno
                notasMedias[modulo] += nota; //Sumamos la nota a la media global

                //Comprobamos si la nota es la mayor
                if (nota > notasMaximas[modulo]) {
                    notasMaximas[modulo] = nota;
                }

                //Formateamos la nota para luego mostrarla
                notasFormateadas[modulo] = String.format("%.2f", nota);
            }

            //Calculamos la media
            media = media / notasModulos.length;
            //Añadimos la media a las notas formateadas
            notasFormateadas[notasFormateadas.length - 1] = String.format("%.2f", media);

            System.out.printf("%-15s %s\n", "ALUMNO " + (alumno + 1), String.format(("%-10s").repeat(notasFormateadas.length), notasFormateadas));
        }

        //Calculamos las notas medias
        for (int i = 0; notasMedias.length > i; i++) {
            notasMedias[i] = notasMedias[i] / notasAlumnos.length;
        }

        int[] alumnosMayorQueMedia = new int[MODULOS];

        //Contamos la cantidad de alumnos que superan la media
        for (double[] notasModulos : notasAlumnos) {
            for (int modulo = 0; notasModulos.length > modulo; modulo++) {
                double nota = notasModulos[modulo];

                if (nota > notasMedias[modulo]) {
                    alumnosMayorQueMedia[modulo]++;
                }
            }
        }

        String[] notasMaximasFormateadas = new String[MODULOS];
        String[] notasMediasFormateadas = new String[MODULOS];

        for (int i = 0; MODULOS > i; i++) {
            notasMaximasFormateadas[i] = String.format("%.2f", notasMaximas[i]);
            notasMediasFormateadas[i] = String.format("%.2f (%d)", notasMedias[i], alumnosMayorQueMedia[i]);
        }

        System.out.print("\n\n");

        System.out.printf("%-15s %s\n", "NOTA MAXIMA", String.format(("%-10s").repeat(notasMaximasFormateadas.length), notasMaximasFormateadas));
        System.out.printf("%-15s %s\n", "MEDIA (nalum)", String.format(("%-10s").repeat(notasMediasFormateadas.length), notasMediasFormateadas));
    }

    public static double random(double min, double max) {
        Random random = new Random();
        return random.nextDouble() * (max - min) + min;
    }

    public static void generarNotas(double[][] notasAlumnos, double minNota, double maxNota) {
        for (double[] notasModulos : notasAlumnos) {
            for (int modulo = 0; notasModulos.length > modulo; modulo++) {
                notasModulos[modulo] = random(minNota, maxNota);
            }
        }
    }
}




