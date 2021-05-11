package me.matamor.pruebas.blackjack.juego.packetmanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PacketHandlerAnnotation {

    Class<? extends PacketHandler> value();

}
