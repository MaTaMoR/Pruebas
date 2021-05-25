package me.matamor.pruebas.blackjack.juego.serializer;

import me.matamor.pruebas.blackjack.jugadores.PersonalidadBot;
import me.matamor.pruebas.blackjack.jugadores.controlador.Controlador;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorBot;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorCPU;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorConsola;
import me.matamor.pruebas.lib.serializer.SerializationException;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControladorSerializer implements Serializer<Controlador> {

    @Override
    public Object serialize(Controlador value) throws SerializationException {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Class", value.getClass().getName());

        if (value instanceof ControladorBot) {
            map.put("Personalidad", ((ControladorBot) value).getPersonalidadBot().name());
        }

        return map;
    }

    @Override
    public Controlador deserialize(Object serialized) throws SerializationException {
        Map<String, Object> map = asMap(serialized);

        Class<?> clazz;
        try {
             clazz = Class.forName(get(map, "Class", String.class));
        } catch (ClassNotFoundException e) {
            throw new SerializationException("Couldn't load class!", e);
        }

        if (clazz == ControladorBot.class) {
            PersonalidadBot personalidadBot;

            try {
                personalidadBot = PersonalidadBot.valueOf(get(map, "Personalidad", String.class));
            } catch (IllegalArgumentException e) {
                throw new SerializationException("No se ha podido cargar la personalidad del bot!");
            }

            return new ControladorBot(personalidadBot);
        } else if (clazz == ControladorCPU.class) {
            return new ControladorCPU();
        } else if (clazz == ControladorConsola.class) {
            return new ControladorConsola();
        } else {
            throw new SerializationException("Clase del controlador desconocida!");
        }
    }
}
