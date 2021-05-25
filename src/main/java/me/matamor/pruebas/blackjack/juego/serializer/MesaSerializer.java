package me.matamor.pruebas.blackjack.juego.serializer;

import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.EstadoMesa;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.Juego;
import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MesaSerializer implements Serializer<Mesa> {

    private final MazoSerializer mazoSerializer = new MazoSerializer();
    private final JugadorSerializer jugadorSerializer = new JugadorSerializer();

    private final Juego juego;

    public MesaSerializer(Juego juego) {
        this.juego = juego;
    }

    @Override
    public Object serialize(Mesa value) throws SerializationException {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Mazo", this.mazoSerializer.serialize(value.getMazo()));
        map.put("CPU", this.jugadorSerializer.serialize(value.getCpu()));

        List<Object> jugadores = new ArrayList<>();
        for (Jugador jugador : value.getJugadores()) {
            jugadores.add(this.jugadorSerializer.serialize(jugador));
        }

        map.put("Jugadores", jugadores);
        map.put("Estado", value.getEstadoMesa().name());
        map.put("Manos", value.getManos());

        return map;
    }

    @Override
    public Mesa deserialize(Object serialized) throws SerializationException {
        Map<String, Object> map = asMap(serialized);

        Mazo mazo = this.mazoSerializer.deserialize(map.get("Mazo"));
        Jugador cpu = this.jugadorSerializer.deserialize(map.get("CPU"));

        List<Jugador> jugadores = new ArrayList<>();
        for (Object object : asList(map.get("Jugadores"))) {
            jugadores.add(this.jugadorSerializer.deserialize(object));
        }

        EstadoMesa estadoMesa;

        try {
            estadoMesa = EstadoMesa.valueOf(get(map, "Estado", String.class));
        } catch (IllegalArgumentException e) {
            throw new SerializationException("No se ha podido cargar el estado!", e);
        }

        int manos = get(map, "Manos", int.class);

        Mesa mesa = new Mesa(this.juego, jugadores, mazo, cpu);
        mesa.setEstadoMesa(estadoMesa);
        mesa.setManos(manos);

        return mesa;
    }
}
