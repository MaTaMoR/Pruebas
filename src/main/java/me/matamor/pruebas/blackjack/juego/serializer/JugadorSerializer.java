package me.matamor.pruebas.blackjack.juego.serializer;

import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.controlador.Controlador;
import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.LinkedHashMap;
import java.util.Map;

public class JugadorSerializer implements Serializer<Jugador> {

    private final ControladorSerializer controladorSerializer = new ControladorSerializer();
    private final MazoSerializer mazoSerializer = new MazoSerializer();

    @Override
    public Object serialize(Jugador value) throws SerializationException {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Nombre", value.getNombre());
        map.put("Controlador", this.controladorSerializer.serialize(value.getControlador()));
        map.put("Mazo", this.mazoSerializer.serialize(value.getMazo()));
        map.put("Saldo", value.getSaldo());
        map.put("Apuesta", value.getApuesta());
        map.put("ManosGanadas", value.getManosGanadas());
        map.put("Estado", value.getEstado().name());
        map.put("DoblarApuesta", value.isDoblarApuesta());

        return map;
    }

    @Override
    public Jugador deserialize(Object serialized) throws SerializationException {
        Map<String, Object> map = asMap(serialized);

        String nombre = get(map, "Name", String.class);
        Controlador controlador = this.controladorSerializer.deserialize(map.get("Controlador"));
        Mazo mazo = this.mazoSerializer.deserialize(map.get("Mazo"));
        int saldo = get(map, "Saldo", int.class);
        int apuesta = get(map, "Apuesta", int.class);
        int manosGanadas = get(map, "ManosGanadas", int.class);

        Jugador.Estado estado;

        try {
            estado = Jugador.Estado.valueOf(get(map, "Estado", String.class));
        } catch (IllegalArgumentException e) {
            throw new SerializationException("No se ha podido cargar el estado!", e);
        }

        boolean doblarApuesta = get(map, "DoblarApuesta", boolean.class);

        Jugador jugador = new Jugador(nombre, controlador, mazo);

        jugador.setSaldo(saldo);
        jugador.setApuesta(apuesta);
        jugador.setManosGanadas(manosGanadas);
        jugador.setEstado(estado);
        jugador.setDoblarApuesta(doblarApuesta);

        return jugador;
    }
}
