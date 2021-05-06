package me.matamor.pruebas.blackjack.menu;

import me.matamor.pruebas.blackjack.exceptions.BlackJackException;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.EstadoMesa;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketManager;

import java.util.List;

public class Juego {

    private final PacketManager packetManager;
    private Mesa mesa;

    public Juego(PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public boolean isPartidaActiva() {
        return this.mesa != null && this.mesa.getEstadoMesa() != EstadoMesa.PARADA;
    }

    /**
     * Crea una partida nueva con los jugadores
     * @param jugadores los jugadores para la partida
     * @return la Mesa con los jugadores
     * @throws BlackJackException si ya hay una mesa creada, actualmente no soporte multiples mesas
     */

    public Mesa crearPartida(List<Jugador> jugadores) throws BlackJackException {
        if (isPartidaActiva()) {
            throw new BlackJackException("Ya hay una partida activa");
        }

        this.mesa = new Mesa(this.packetManager, jugadores);
        return this.mesa;
    }
}