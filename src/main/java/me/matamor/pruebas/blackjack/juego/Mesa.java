package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.cartas.Baraja;
import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorCPU;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketManager;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.*;
import me.matamor.pruebas.lib.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Mesa {

    private final PacketManager packetManager;

    private final Mazo mazo;
    private final JugadorCPU cpu;

    private final List<Jugador> jugadores;

    private EstadoMesa estadoMesa;
    private int manos;

    public Mesa(PacketManager packetManager, List<Jugador> jugadores) {
        this.packetManager = packetManager;

        this.mazo = Baraja.nuevoMazo();
        this.cpu = new JugadorCPU();

        this.jugadores = jugadores;

        this.estadoMesa = EstadoMesa.PARADA;
        this.manos = 0;
    }

    public PacketManager getPacketManager() {
        return this.packetManager;
    }

    public JugadorCPU getCpu() {
        return this.cpu;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public List<Jugador> getJugadores(Jugador.Estado estado) {
        return this.jugadores.stream().filter(e -> e.getEstado() == estado).collect(Collectors.toList());
    }

    public EstadoMesa getEstadoMesa() {
        return this.estadoMesa;
    }

    public int getManos() {
        return this.manos;
    }

    public void iniciar() {
        this.manos = 1;
        this.mazo.mezclar();
        this.estadoMesa = EstadoMesa.ACTIVA;

        this.packetManager.broadcast(new GameStatePacket(this.estadoMesa));
        ThreadUtils.sleep(Constantes.DELAY);

        do {
            // Primera juegan los jugadores
            for (Jugador jugador : getJugadores(Jugador.Estado.ACTIVO)) {
                this.packetManager.broadcast(new PlayerTurnStartPacket(jugador)); //Enviamos el mensaje de que empieza el turno del jugador

                for (int i = 0; Constantes.CARTAS_RONDA > i; i++) {
                    Carta carta = this.mazo.sacarCarta(); // Sacamos una carta del mazo

                    jugador.getMazo().nuevaCarta(carta); //Añadimos la carta a las cartas del jugador

                    this.packetManager.broadcast(new PlayerDrawCardPacket(jugador, carta)); //Mostramos la carta que ha sacado el jugador

                    ThreadUtils.sleep(Constantes.DELAY);
                }


                int puntosJugador = jugador.getMazo().contarPuntosReal();
                this.packetManager.broadcast(new PlayerPointsPacket(jugador, puntosJugador));

                if (puntosJugador < Constantes.PUNTOS_GANAR) { //Si el jugador tiene menos puntos del máximo le preguntamos si quieres seguir jugando
                    Jugador.Respuesta respuesta;

                    do {
                        respuesta = jugador.jugar(this);

                        if (respuesta == Jugador.Respuesta.PEDIR_CARTA) { //El jugador pide carta así que le damos una más
                            this.packetManager.broadcast(new PlayerAskCardPacket(jugador));

                            Carta carta = this.mazo.sacarCarta();

                            jugador.getMazo().nuevaCarta(carta);
                            puntosJugador = jugador.getMazo().contarPuntosReal();

                            this.packetManager.broadcast(new PlayerDrawCardPacket(jugador, carta));
                            this.packetManager.broadcast(new PlayerPointsPacket(jugador, puntosJugador));
                        } else if (respuesta == Jugador.Respuesta.SALTAR) { //Si el jugador decide saltar su turno simplemente seguimops
                            this.packetManager.broadcast(new PlayerStandPacket(jugador, puntosJugador));
                        }

                        ThreadUtils.sleep(Constantes.DELAY);
                    } while (respuesta == Jugador.Respuesta.PEDIR_CARTA && puntosJugador < Constantes.PUNTOS_GANAR);
                }

                if (puntosJugador == Constantes.PUNTOS_GANAR) { //El jugador gana el turno
                    jugador.setEstado(Jugador.Estado.GANA);

                    this.packetManager.broadcast(new PlayerWinTurnPacket(jugador, puntosJugador));

                    ThreadUtils.sleep(Constantes.DELAY);
                } else if (puntosJugador > Constantes.PUNTOS_GANAR) { //El jugador pierde el turno
                    jugador.setEstado(Jugador.Estado.PIERDE);

                    this.packetManager.broadcast(new PlayerLoseTurnPacket(jugador, puntosJugador));

                    ThreadUtils.sleep(Constantes.DELAY);
                }
            }

            List<Jugador> activos = getJugadores(Jugador.Estado.ACTIVO);

            //Comprobamos si queda algún jugador activo, sino ha perdido la CPU
            if (activos.size() > 0) {
                //Ahora juega la máquina

                this.packetManager.broadcast(new PlayerTurnStartPacket(this.cpu)); //Enviamos el mensaje de que empieza el turno de la máquina

                //Sacamos las cartas visibles de la CPU
                for (int i = 0; Constantes.CARTAS_VISIBLES_CPU > i; i++) {
                    Carta carta = this.mazo.sacarCarta();

                    this.cpu.getMazo().nuevaCarta(carta);
                    this.packetManager.broadcast(new PlayerDrawCardPacket(this.cpu, carta)); //Mostramos la carta que ha sacado la CPU

                    ThreadUtils.sleep(Constantes.DELAY);
                }

                //Sacamos las cartas ocultas de la CPU
                for (int i = 0; Constantes.CARTAS_OCULTAS_CPU > i; i++) {
                    Carta carta = this.mazo.sacarCarta();
                    carta.setVisible(false);

                    this.cpu.getMazo().nuevaCarta(carta);
                    this.packetManager.broadcast(new PlayerDrawCardPacket(this.cpu, carta)); //Mostramos la carta que ha sacado la CPU

                    ThreadUtils.sleep(Constantes.DELAY);
                }

                //Comprobamos los puntos de la CPU
                int puntosCPU = this.cpu.getMazo().contarPuntosReal();
                this.packetManager.broadcast(new PlayerPointsPacket(this.cpu, puntosCPU));

                if (puntosCPU < Constantes.PUNTOS_GANAR) {
                    Jugador.Respuesta respuesta;

                    do {
                        respuesta = this.cpu.jugar(this);

                        if (respuesta == Jugador.Respuesta.PEDIR_CARTA) { //El jugador pide carta así que le damos una más
                            this.packetManager.broadcast(new PlayerAskCardPacket(this.cpu));
                            Carta carta = this.mazo.sacarCarta();

                            this.cpu.getMazo().nuevaCarta(carta);
                            puntosCPU = this.cpu.getMazo().contarPuntosReal();

                            this.packetManager.broadcast(new PlayerDrawCardPacket(this.cpu, carta));
                            this.packetManager.broadcast(new PlayerPointsPacket(this.cpu, puntosCPU));
                        } else if (respuesta == Jugador.Respuesta.SALTAR) { //Si el jugador decide saltar su turno simplemente seguimops
                            this.packetManager.broadcast(new PlayerStandPacket(this.cpu, puntosCPU));
                        }
                    } while (respuesta == Jugador.Respuesta.PEDIR_CARTA && puntosCPU < Constantes.PUNTOS_GANAR);
                }

                this.estadoMesa = EstadoMesa.DECIDE;
                this.packetManager.broadcast(new GameStatePacket(this.estadoMesa));
                ThreadUtils.sleep(Constantes.DELAY);

                //Buscamos los jugadores que no han perdido o ganado!
                for (Jugador jugador : activos) {
                    int puntosJugador = jugador.getMazo().contarPuntos();

                    if (puntosJugador > puntosCPU || puntosCPU > Constantes.PUNTOS_GANAR) { //El jugador gana a la CPU
                        jugador.setEstado(Jugador.Estado.GANA);

                        this.packetManager.broadcast(new PlayerWinTurnPacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    } else if (puntosJugador < puntosCPU) { //El jugador pierde contra la CPU
                        jugador.setEstado(Jugador.Estado.PIERDE);

                        this.packetManager.broadcast(new PlayerLoseTurnPacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    } else { //El jugador empata contra la CPU
                        jugador.setEstado(Jugador.Estado.EMPATE);

                        this.packetManager.broadcast(new PlayerTiePacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    }
                }
            }

            this.estadoMesa = EstadoMesa.PAY;
            this.packetManager.broadcast(new GameStatePacket(this.estadoMesa));
            ThreadUtils.sleep(Constantes.DELAY);

            //Damos la apuesta a los ganadores
            for (Jugador ganador : getJugadores(Jugador.Estado.GANA)) {
                double bonificador = (ganador.getMazo().contarPuntosReal() == Constantes.PUNTOS_GANAR ? Constantes.BONIFICADOR_BLACK_JACK : Constantes.BONIFICADOR_NORMAL);
                int apuesta = (int) Math.ceil(ganador.getApuesta() * bonificador);

                ganador.setSaldo(ganador.getSaldo() + apuesta);
                this.cpu.setSaldo(this.cpu.getSaldo() - apuesta);

                ganador.setManosGanadas(ganador.getManosGanadas() + 1);
                ganador.setEstado(Jugador.Estado.ACTIVO);

                this.packetManager.broadcast(new PlayerBetPacket(ganador, apuesta, bonificador, true));
            }

            //Quitamos la apuesta los perdedores
            for (Jugador perdedor : getJugadores(Jugador.Estado.PIERDE)) {
                int apuesta = perdedor.getApuesta();

                perdedor.setSaldo(perdedor.getSaldo() - apuesta);
                this.cpu.setSaldo(this.cpu.getSaldo() + apuesta);

                this.cpu.setManosGanadas(this.cpu.getManosGanadas() + 1);
                perdedor.setEstado(Jugador.Estado.ACTIVO);

                this.packetManager.broadcast(new PlayerBetPacket(perdedor, apuesta, Constantes.BONIFICADOR_NORMAL, false));
            }

            this.estadoMesa = EstadoMesa.FINISHING;
            this.packetManager.broadcast(new GameStatePacket(this.estadoMesa));
            ThreadUtils.sleep(Constantes.DELAY);

            //Devolvemos las cartas al Mazo principal
            for (Jugador jugador : getJugadores()) {
                jugador.getMazo().getCartas().forEach(this.mazo::nuevaCarta);
                jugador.getMazo().limpiarCartas();
            }

            this.cpu.getMazo().getCartas().forEach(this.mazo::nuevaCarta);
            this.cpu.getMazo().limpiarCartas();
            this.mazo.getCartas().forEach(e -> e.setVisible(true));

            //Mezclamos las cartas y aumentamos el contador de manos jugadas
            this.mazo.mezclar();
            this.manos = this.manos + 1;

            //Miramos los jugadores que se han quedado sin suficiente dinero para la puesta
            for (Jugador jugador : getJugadores(Jugador.Estado.ACTIVO)) {
                if (jugador.getSaldo() < jugador.getApuesta()) {
                    jugador.setEstado(Jugador.Estado.FUERA);

                    this.packetManager.broadcast(new PlayerOutPacket(jugador));
                }
            }

            if (getJugadores(Jugador.Estado.ACTIVO).isEmpty()) {
                this.estadoMesa = EstadoMesa.PARADA;
            } else if (this.cpu.getSaldo() < this.cpu.getApuesta()) {
                this.estadoMesa = EstadoMesa.PARADA;

                this.packetManager.broadcast(new PlayerOutPacket(this.cpu));
                this.packetManager.broadcast(new GameStatePacket(this.estadoMesa));
            } else {
                this.estadoMesa = EstadoMesa.ACTIVA;
            }
        } while (this.estadoMesa == EstadoMesa.ACTIVA);
    }
}
