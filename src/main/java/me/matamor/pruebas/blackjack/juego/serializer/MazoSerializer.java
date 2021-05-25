package me.matamor.pruebas.blackjack.juego.serializer;

import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.ArrayList;
import java.util.List;

public class MazoSerializer implements Serializer<Mazo> {

    private final CartaSerializer cartaSerializer = new CartaSerializer();

    @Override
    public Object serialize(Mazo value) throws SerializationException {
        List<Object> cartas = new ArrayList<>();
        for (Carta carta : value.getCartas()) {
            cartas.add(this.cartaSerializer.serialize(carta));
        }

        return cartas;
    }

    @Override
    public Mazo deserialize(Object serialized) throws SerializationException {
        List<Object> list = asList(serialized);
        List<Carta> cartas = new ArrayList<>();

        for (Object object : list) {
            cartas.add(this.cartaSerializer.deserialize(object));
        }

        return new Mazo(cartas);
    }
}
