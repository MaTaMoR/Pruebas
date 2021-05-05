package me.matamor.pruebas.tema11.ejercicio6.videoclub;

import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Multimedia;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegistroAlquiler {

    private final Socio socio;
    private final Multimedia multimedia;
    private final Date fechaAlquiler;

    private boolean pagado;

    public RegistroAlquiler(Socio socio, Multimedia multimedia, Date fechaAlquiler) {
        this.socio = socio;
        this.multimedia = multimedia;
        this.fechaAlquiler = fechaAlquiler;
    }

    public Socio getSocio() {
        return this.socio;
    }

    public Multimedia getMultimedia() {
        return this.multimedia;
    }

    public Date getFechaAlquiler() {
        return this.fechaAlquiler;
    }

    public boolean pagado() {
        return this.pagado;
    }

    public void pagar() {
        this.pagado = true;
    }

    public boolean caducado() {
        return diasArquilado() > Constantes.DURACION_DIAS_ALQUILER;
    }

    public int diasTarde() {
        int diasArquilado = diasArquilado();
        if (diasArquilado > Constantes.DURACION_DIAS_ALQUILER) {
            return diasArquilado - Constantes.DURACION_DIAS_ALQUILER;
        } else {
            return 0;
        }
    }

    public int diasArquilado() {
        long ahora = System.currentTimeMillis();
        long diferencia = ahora - this.fechaAlquiler.getTime();

        return (int) TimeUnit.MILLISECONDS.toDays(diferencia);
    }

    @Override
    public String toString() {
        return "RegistroAlquiler{" +
                "socio=" + socio.getKey() +
                ", multimedia=" + multimedia.getKey() +
                ", fechaAlquiler=" + fechaAlquiler +
                ", pagado=" + pagado +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof RegistroAlquiler) {
            RegistroAlquiler registroAlquiler = (RegistroAlquiler) object;
            return Objects.equals(this.socio, registroAlquiler.socio)
                    && Objects.equals(this.multimedia, registroAlquiler.multimedia)
                    && Objects.equals(this.fechaAlquiler, registroAlquiler.fechaAlquiler);
        }

        return false;
    }
}
