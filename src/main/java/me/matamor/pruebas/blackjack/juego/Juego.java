package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.exceptions.BlackJackException;
import me.matamor.pruebas.blackjack.juego.serializer.MesaSerializer;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.EstadoMesa;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketManager;
import me.matamor.pruebas.lib.serializer.Serializer;

import java.util.List;

public class Juego {

    private final PacketManager packetManager;
    private final GameSave gameSave;

    private Mesa mesa;

    public Juego(PacketManager packetManager) {
        this.packetManager = packetManager;
        this.gameSave = new GameSave(this);
    }

    public PacketManager getPacketManager() {
        return this.packetManager;
    }

    public GameSave getGameSave() {
        return this.gameSave;
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public boolean isPartidaActiva() {
        return this.mesa != null && this.mesa.getEstadoMesa() != EstadoMesa.PARADA;
    }

    public boolean cargarPartida() {
        this.mesa = this.gameSave.cargar();
        return this.mesa != null;
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

        this.mesa = new Mesa(this, jugadores);
        return this.mesa;
    }
}
