package me.matamor.pruebas.tema11.ejercicio6.menu;

import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.RegistroAlquiler;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Socio;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Videoclub;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Multimedia;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MenuPrincipal extends MenuConsola {

    private final Videoclub videoclub;

    private final MenuAltas menuAltas;
    private final MenuListados menuListados;

    public MenuPrincipal(Videoclub videoclub) {
        super("Videoclub");

        this.videoclub = videoclub;

        this.menuAltas = new MenuAltas(this.videoclub);
        this.menuListados = new MenuListados(this.videoclub);

        registrarOpcion(Constantes.ALTAS, new OpcionSimple("Altas", "Da de altas socios o multimedia", this.menuAltas::ejecutar));
        registrarOpcion(Constantes.ALQUILAR_MULTIMEDIA, new OpcionSimple("Alquilar multimedia", "Alquila multimedia a socio", this::alquilarMultimedia));
        registrarOpcion(Constantes.DEVOLVER_MULTIMEDIA, new OpcionSimple("Devolver multimedia", "Devolver multimedia de socio", this::devolverMultimedia));
        registrarOpcion(Constantes.LISTADOS, new OpcionSimple("Listados", "Alquila multimedia a socio", this.menuListados::ejecutar));
    }

    private void alquilarMultimedia() {
        try {
            System.out.println("Escribe el nif del socio: ");
            String nif = Input.leerLinea(Constantes.NIF_MIN, Constantes.NIF_MAX);

            Socio socio = this.videoclub.buscarSocio(nif);
            if (socio == null) {
                System.out.println("No hay ningún socio registrado con el nif: " + nif);
            } else {
                boolean alquileresCaducados = socio.registroAlquileres().stream().anyMatch(RegistroAlquiler::caducado);
                if (alquileresCaducados) {
                    System.out.println("Este socio ya tiene alquileres caducados que debe pagar primero antes de alquiler otro elemento multimedia!");
                } else {
                    System.out.println("Escribe el título del multimedia: ");
                    String titulo = Input.leerLinea(Constantes.MULTIMEDIA_TITULO_MIN, -1);

                    List<Multimedia> listado = this.videoclub.buscarMultimedias(e -> e.getTitulo().equalsIgnoreCase(titulo));
                    if (listado.isEmpty()) {
                        System.out.println("No hay ningún elemento multimedia con el título: " + titulo);
                    } else {
                        Multimedia multimedia;

                        if (listado.size() == 1) {
                            multimedia = listado.get(0);
                        } else {
                            System.out.println("Elige el elemento multimedia (usando el número identificatorio): ");
                            int contador = 0;

                            for (Multimedia elemento : listado) {
                                System.out.println(contador + " - " + elemento);
                            }

                            multimedia = listado.get(Input.leerInt(0, listado.size() - 1));
                        }

                        if (multimedia.getRegistroAlquiler() == null) {
                            RegistroAlquiler registroAlquiler = new RegistroAlquiler(socio, multimedia, new Date(System.currentTimeMillis()));

                            socio.nuevoAlquiler(registroAlquiler);
                            multimedia.setRegistroAlquiler(registroAlquiler);

                            System.out.println("Elemento alquilado correctamente!");
                        } else {
                            System.out.println("Este elemento multimedia ya está siendo arquilado!");
                        }
                    }
                }
            }
        } catch (CancelException e) {
            System.out.println("Alquiler multimedia cancelado!");
        }
    }

    private void devolverMultimedia() {
        try {
            System.out.println("Escribe el nif del socio: ");
            String nif = Input.leerLinea(Constantes.NIF_MIN, Constantes.NIF_MAX);

            Socio socio = this.videoclub.buscarSocio(nif);
            if (socio == null) {
                System.out.println("No hay ningún socio registrado con el nif: " + nif);
            } else {
                List<RegistroAlquiler> listado = socio.registroAlquileres().stream().filter(e -> !e.pagado()).collect(Collectors.toList());

                if (listado.isEmpty()) {
                    System.out.println("Este socio no tiene ningún elemento multimedia alquilado sin pagar!");
                } else {
                    RegistroAlquiler registroAlquiler;

                    if (listado.size() == 1) {
                        registroAlquiler = listado.get(0);
                    } else {
                        System.out.println("Elige el alquiler a pagar (usando el número identificatorio): ");
                        int contador = 0;

                        for (RegistroAlquiler elemento : listado) {
                            System.out.println(contador + " - " + elemento);
                        }

                        registroAlquiler = listado.get(Input.leerInt(0, listado.size() - 1));
                    }

                    double precio = registroAlquiler.getMultimedia().precio();
                    int diasTarde = registroAlquiler.diasTarde();

                    if (diasTarde > 0) {
                        System.out.println("Alquiler tarde por los siguientes días: " + diasTarde);
                        precio += diasTarde * Constantes.PRECIO_DIAS_TARDE;
                    }

                    System.out.println("Total a pagar: " + precio);

                    registroAlquiler.pagar();
                    registroAlquiler.getMultimedia().setRegistroAlquiler(null);
                }
            }
        } catch (CancelException e) {
            System.out.println("Devolver alquiler multimedia cancelado!");
        }
    }
}
