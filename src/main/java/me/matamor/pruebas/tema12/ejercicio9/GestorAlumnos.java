package me.matamor.pruebas.tema12.ejercicio9;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAlumnos {

    private final File archivoAlumnos;
    private final File archivoTempAlumnos;

    public GestorAlumnos(String carpeta, String archivo) {
        this.archivoAlumnos = new File(carpeta, archivo);
        this.archivoTempAlumnos = new File(carpeta, "temp-" + archivo);
    }

    public void guardarAlumnos(String[] alumnos) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.archivoAlumnos))) {
            for (String alumno : alumnos) {
                bufferedWriter.write(alumno + FileUtils.LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarAlumno(String alumno) {
        if (this.archivoAlumnos.isFile()) {
            List<String> alumnos = cargarAlumnos();
            if (alumnos.size() > 0) {
                alumnos.remove(alumno);
                guardarAlumnos(alumnos.toArray(new String[0]));
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void borrarAlumno2(String alumno) {
        if (this.archivoAlumnos.isFile()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.archivoAlumnos))) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.archivoTempAlumnos))) {
                    String linea;

                    while ((linea = bufferedReader.readLine()) != null) {
                        if (linea.equals(alumno)) continue;

                        bufferedWriter.write(linea + FileUtils.LINE_SEPARATOR);
                    }

                    FileUtils.copy(this.archivoTempAlumnos, this.archivoAlumnos);
                    this.archivoTempAlumnos.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void borrarAlumno3(String alumno) {
        if (this.archivoAlumnos.isFile()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.archivoAlumnos))) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.archivoTempAlumnos))) {
                    String linea;

                    while ((linea = bufferedReader.readLine()) != null) {
                        if (linea.equals(alumno)) continue;

                        bufferedWriter.write(linea + FileUtils.LINE_SEPARATOR);
                    }

                    FileUtils.copy(this.archivoTempAlumnos, this.archivoAlumnos);
                    this.archivoTempAlumnos.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> cargarAlumnos() {
        List<String> alumnos = new ArrayList<>();

        if (this.archivoAlumnos.isFile()) {
            try (FileReader fileReader = new FileReader(this.archivoAlumnos)) {
                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String linea;

                    while ((linea = bufferedReader.readLine()) != null) {
                        alumnos.add(linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return alumnos;
    }
}
