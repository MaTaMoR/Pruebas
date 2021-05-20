package me.matamor.pruebas.tema12.ejercicio15;

import java.io.File;
import java.util.*;

public class ComparadorCarpetas {

    private File folder1, folder2;

    public void setFolder(File folder1, File folder2) throws GestionArchivosNotFolderException {
        if (!folder1.isDirectory() || !folder2.isDirectory()) {
            throw new GestionArchivosNotFolderException("One of the files isn't a directory!");
        }

        this.folder1 = folder1;
        this.folder2 = folder2;
    }

    public File getFolder1() {
        return this.folder1;
    }

    public File getFolder2() {
        return this.folder2;
    }

    public Iterator<ResultadoComparacion> compare() {
        List<ResultadoComparacion> comparacion = new ArrayList<>();

        File[] files1 = this.folder1.listFiles();
        File[] files2 = this.folder2.listFiles();

        Set<String> uniqueNames = new HashSet<>();

        if (files1 != null) {
            for (File file : files1) {
                if (file.isFile()) {
                    uniqueNames.add(file.getName());
                }
            }
        }

        if (files2 != null) {
            for (File file : files2) {
                if (file.isFile()) {
                    uniqueNames.add(file.getName());
                }
            }
        }

        for (String uniqueName : uniqueNames) {
            comparacion.add(check(uniqueName, files1, files2));
        }

        return comparacion.iterator();
    }

    private ResultadoComparacion check(String name, File[] files1, File[] files2) {
        File file1 = null;
        File file2 = null;

        if (files1 != null) {
            for (File file : files1) {
                if (file.isFile() && name.equals(file.getName())) {
                    file1 = file;
                    break;
                }
            }
        }

        if (files2 != null) {
            for (File file : files2) {
                if (file.isFile() && name.equals(file.getName())) {
                    file2 = file;
                    break;
                }
            }
        }

        ResultadoComparacion.Resultado resultado;

        if (file1 != null && file2 != null) {
            if (file1.lastModified() == file2.lastModified()) {
                resultado = ResultadoComparacion.Resultado.IGUALES;
            } else if (file1.lastModified() > file2.lastModified()) {
                resultado = ResultadoComparacion.Resultado.MAS_NUEVO_EN_1;
            } else {
                resultado = ResultadoComparacion.Resultado.MAS_NUEVO_EN_2;
            }
        } else if (file1 != null) {
            resultado = ResultadoComparacion.Resultado.FALTA_EN_2;
        } else {
            resultado = ResultadoComparacion.Resultado.FALTA_EN_1;
        }

        return new ResultadoComparacion(name, resultado);
    }
}
