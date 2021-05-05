package me.matamor.pruebas.test;

public class ReplaceDelChino {

    public static String replace(String texto, String target, String reemplazo) {
        if (texto == null || texto.isEmpty()) { //Verificamos que Texto no sea null o este vacio
            throw new IllegalArgumentException("Text can't be null or empty!");
        }

        if (target == null || target.isEmpty()) { //Verificamos que Target no sea null o este vacio
            throw new IllegalArgumentException("Target can't be null or empty!");
        }

        if (reemplazo == null) { //Verificamos que Reemplazo no sea null o este vacio
            throw new IllegalArgumentException("Reemplazo can't be null!");
        }

        if (target.length() > texto.length()) {  //Verificamos que el texto pueda contener el target
            return texto;
        }

        char[] caracteresTexto = texto.toCharArray();
        char[] caracteresTarget = target.toCharArray();

        int longitudTexto = texto.length();
        int longitudTarget = target.length();

        StringBuilder caracteresEncontrados = new StringBuilder();
        StringBuilder textoReemplazado = new StringBuilder();

        int contador = 0;

        //Se deberia parar si la cantidad de caracteres que faltan para poder reemplazar es mayor a los caracteres que faltan
        // && ((longitudTexto - contador) >= (longitudReemplazo - caracteresEncontrados.length()))
        boolean funcionar;

        do {
            char caracter = caracteresTexto[contador];

            //Comprobamos si el caracter coincide con el caracter de target en la posicion actual
            if (caracter == caracteresTarget[caracteresEncontrados.length()]) {
                caracteresEncontrados.append(caracter); //Añadimos el caracter a los caracteres encontrados
            } else {
                //Si el caracter no coincide es posible que hayamos detectado parte del target
                //Hay que tener en cuenta este texto y añadirlo
                if (caracteresEncontrados.length() > 0) {
                    textoReemplazado.append(caracteresEncontrados.toString()); //Añadimos el texto sobrante
                    caracteresEncontrados.setLength(0); //Reiniciamos el texto encontrado
                }

                //Puede ser que al reiniciar los caracteres encontrados, el caracter actual pueda iniciar de nuevo la cadena
                if (caracter == caracteresTarget[caracteresEncontrados.length()]) {
                    caracteresEncontrados.append(caracter);  //Añadimos el caracter a los caracteres encontrados
                } else {
                    //Añadimos el caracter que no coincide
                    textoReemplazado.append(caracter);
                }
            }

            //Comprobamos si ya hemos encontrado todos los caracteres de target
            if (caracteresEncontrados.length() == longitudTarget) {
                caracteresEncontrados.setLength(0); //Reiniciamos el texto encontrado

                textoReemplazado.append(reemplazo); //Añadimos el reemplazo
            }

            //Aumetamos el contador
            contador++;

            int caracteresQueQuedan = longitudTexto - contador; //Calculamos cuantos caracteres quedan
            int caracteresQueFaltan = longitudTarget - caracteresEncontrados.length(); //Calculamos cuantos caracteres faltan

            //Si faltan mas caracteres que los que quedan no va a ser posible reemplazar la palabra
            //Por lo cual es inutil seguir buscando, asi que paramos de buscar mas
            if (caracteresQueFaltan > caracteresQueQuedan) {
                //Debemos añadir el texto que falta
                for (int i = contador; longitudTexto > i; i++) {
                    textoReemplazado.append(caracteresTexto[i]);
                }

                //Paramos de interactuar
                funcionar = false;
            } else { //En teoria el codigo no deberia depender de esto nunca, pero esta puesto just in case baby
                funcionar = contador < longitudTexto;
            }
        } while (funcionar);

        return textoReemplazado.toString();
    }

    public static void main(String[] args) {
        String texto = "holamaninquemanintal";
        String target = "manin";
        String reemplazo = " ";

        System.out.println(replace(texto, target, reemplazo));
    }
}
