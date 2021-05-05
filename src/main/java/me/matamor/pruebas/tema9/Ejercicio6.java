package me.matamor.pruebas.tema9;

public class Ejercicio6 {

    public void ejecutar() {
        int[] numeros = { -2, -1, 0, 1, 2 };
        int divisor = 2;

        try {
            System.out.println("Metodo divisor 1:");
            dividirArray1(numeros, divisor);
        } catch (ArithmeticException e) {
            System.out.println("Error primer metodo!");
        }

        System.out.println("Metodo divisor 2:");
        dividirArray2(numeros, divisor);

        System.out.println("Metodo divisor 3:");
        dividirArray3(numeros, divisor);
    }

    private void dividirArray1(int[] divisores, int numero) {
        for (int divisor : divisores) {
            System.out.printf("%d / %d = %d\n", numero, divisor, (numero / divisor));
        }
    }

    private void dividirArray2(int[] divisores, int numero) {
        for (int divisor : divisores) {
            try {
                System.out.printf("%d / %d = %d\n", numero, divisor, (numero / divisor));
            } catch (ArithmeticException e) {
                System.out.printf("Error al dividir %d entre %d!\n", numero, divisor);
            }
        }
    }

    private void dividirArray3(int[] divisores, int numero) {
        for (int divisor : divisores) {
            System.out.printf("%d / %d = %d\n", numero, divisor, (divisor == 0 ? 0 : (numero / divisor)));
        }
    }

    public static void main(String[] args) {
        Ejercicio6 ejercicio6 = new Ejercicio6();
        ejercicio6.ejecutar();
    }
}
