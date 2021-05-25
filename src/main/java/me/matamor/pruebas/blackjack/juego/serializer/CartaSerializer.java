package me.matamor.pruebas.blackjack.juego.serializer;

import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.cartas.Palo;
import me.matamor.pruebas.blackjack.cartas.Tipo;
import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartaSerializer implements Serializer<Carta> {

    @Override
    public Object serialize(Carta value) throws SerializationException {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Palo", value.getPalo().name());
        map.put("Tipo", value.getTipo().name());
        map.put("Visible", value.isVisisble());

        return map;
    }

    @Override
    public Carta deserialize(Object serialized) throws SerializationException {
        Map<String, Object> map = asMap(serialized);

        Palo palo;
        try {
            palo = Palo.valueOf(get(map, "Palo", String.class));
        } catch (IllegalArgumentException e) {
            throw new SerializationException("No se ha podido cargar el palo!", e);
        }

        Tipo tipo;
        try {
            tipo = Tipo.valueOf(get(map, "Tipo", String.class));
        } catch (IllegalArgumentException e) {
            throw new SerializationException("No se ha podido cargar el tipo!", e);
        }

        boolean visible = get(map, "Visible", boolean.class);

        return new Carta(palo, tipo);
    }
}
