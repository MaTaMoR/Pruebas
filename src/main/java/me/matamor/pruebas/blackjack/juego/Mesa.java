package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.cartas.Baraja;
import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.*;
import me.matamor.pruebas.blackjack.jugadores.controlador.Controlador;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorCPU;
import me.matamor.pruebas.lib.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Mesa {

    private final Juego juego;

    private final Mazo mazo;
    private final Jugador cpu;

    private final List<Jugador> jugadores;

    private EstadoMesa estadoMesa;
    private int manos;

    private boolean cargada;

    public Mesa(Juego juego, List<Jugador> jugadores) {
        this.juego = juego;

        this.mazo = Baraja.nuevoMazo();

        this.cpu = new Jugador("CPU", new ControladorCPU());
        this.cpu.setSaldo(Constantes.SALDO_CPU_DEFAULT);

        this.jugadores = jugadores;

        this.estadoMesa = EstadoMesa.PARADA;
        this.manos = 0;
        this.cargada = false;
    }

    public Mesa(Juego juego, List<Jugador> jugadores, Mazo mazo, Jugador cpu) {
        this.juego = juego;

        this.mazo = mazo;
        this.cpu = cpu;

        this.jugadores = jugadores;

        this.estadoMesa = EstadoMesa.PARADA;
        this.manos = 0;
        this.cargada = true;
    }

    public Juego getJuego() {
        return this.juego;
    }

    public Mazo getMazo() {
        return this.mazo;
    }

    public Jugador getCpu() {
        return this.cpu;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public List<Jugador> getJugadoresExcepto(Jugador.Estado estado) {
        return this.jugadores.stream().filter(e -> e.getEstado() != estado).collect(Collectors.toList());
    }

    public List<Jugador> getJugadores(Jugador.Estado estado) {
        return this.jugadores.stream().filter(e -> e.getEstado() == estado).collect(Collectors.toList());
    }

    public EstadoMesa getEstadoMesa() {
        return this.estadoMesa;
    }

    public void setEstadoMesa(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public int getManos() {
        return this.manos;
    }

    public void setManos(int manos) {
        this.manos = manos;
    }

    private void broadcast(Packet packet) {
        this.juego.getPacketManager().broadcast(this.jugadores, packet);
    }

    private Controlador.Respuesta jugar(Jugador jugador) {
        return jugador.getControlador().jugar(jugador.getMazo(), this);
    }

    private boolean doblarApuesta(Jugador jugador) {
        return jugador.getControlador().doblarApuesta(jugador.getMazo(), this);
    }
    
    public void iniciar() {
        if (this.cargada) {
            this.cargada = false;
        } else {
            this.manos = 1;
            this.mazo.mezclar();
            this.estadoMesa = EstadoMesa.ACTIVA;

            broadcast(new GameStatePacket(this.estadoMesa));
            ThreadUtils.sleep(Constantes.DELAY);
        }

        do {
            System.out.println(this.manos);
            if (this.manos > 0) {
                this.juego.getGameSave().guardar(this);
                System.out.println("guardando");
            }

            // Primera juegan los jugadores
            for (Jugador jugador : getJugadores(Jugador.Estado.ACTIVO)) {
                broadcast(new PlayerTurnStartPacket(jugador)); //Enviamos el mensaje de que empieza el turno del jugador

                for (int i = 0; Constantes.CARTAS_RONDA > i; i++) {
                    Carta carta = this.mazo.sacarCarta(); // Sacamos una carta del mazo

                    jugador.getMazo().nuevaCarta(carta); //A??adimos la carta a las cartas del jugador

                    broadcast(new PlayerDrawCardPacket(jugador, carta)); //Mostramos la carta que ha sacado el jugador

                    ThreadUtils.sleep(Constantes.DELAY);
                }

                int puntosJugador = jugador.getMazo().contarPuntosReal();
                broadcast(new PlayerPointsPacket(jugador, puntosJugador));

                if (puntosJugador < Constantes.PUNTOS_GANAR) { //Si el jugador tiene menos puntos del m??ximo le preguntamos si quieres seguir jugando
                    if (jugador.getSaldo() >= (jugador.getApuesta() * 2)) { //El jugador se puede permitir doblar la apuesta
                        boolean doblarApuesta = doblarApuesta(jugador);

                        if (doblarApuesta) {
                            jugador.setDoblarApuesta(true);
                            broadcast(new PlayerDoubleBetPacket(jugador, jugador.getApuesta() * 2));
                        }
                    }

                    Controlador.Respuesta respuesta;

                    do {
                        respuesta = jugar(jugador);

                        if (respuesta == Controlador.Respuesta.PEDIR_CARTA) { //El jugador pide carta as?? que le damos una m??s
                            broadcast(new PlayerAskCardPacket(jugador));

                            Carta carta = this.mazo.sacarCarta();

                            jugador.getMazo().nuevaCarta(carta);
                            puntosJugador = jugador.getMazo().contarPuntosReal();

                            broadcast(new PlayerDrawCardPacket(jugador, carta));
                            broadcast(new PlayerPointsPacket(jugador, puntosJugador));
                        } else if (respuesta == Controlador.Respuesta.SALTAR) { //Si el jugador decide saltar su turno simplemente seguimops
                            broadcast(new PlayerStandPacket(jugador, puntosJugador));
                        }

                        ThreadUtils.sleep(Constantes.DELAY);
                    } while (respuesta == Controlador.Respuesta.PEDIR_CARTA && puntosJugador < Constantes.PUNTOS_GANAR);
                }

                if (puntosJugador == Constantes.PUNTOS_GANAR) { //El jugador gana el turno
                    jugador.setEstado(Jugador.Estado.GANA);

                    broadcast(new PlayerWinTurnPacket(jugador, puntosJugador));

                    ThreadUtils.sleep(Constantes.DELAY);
                } else if (puntosJugador > Constantes.PUNTOS_GANAR) {
                    jugador.setEstado(Jugador.Estado.PIERDE);

                    broadcast(new PlayerLoseTurnPacket(jugador, puntosJugador));

                    ThreadUtils.sleep(Constantes.DELAY);
                }
            }

            List<Jugador> activos = getJugadores(Jugador.Estado.ACTIVO);

            //Comprobamos si queda alg??n jugador activo, sino ha perdido la CPU
            if (activos.size() > 0) {
                //Ahora juega la m??quina

                broadcast(new PlayerTurnStartPacket(this.cpu)); //Enviamos el mensaje de que empieza el turno de la m??quina

                //Sacamos las cartas visibles de la CPU
                for (int i = 0; Constantes.CARTAS_VISIBLES_CPU > i; i++) {
                    Carta carta = this.mazo.sacarCarta();

                    this.cpu.getMazo().nuevaCarta(carta);
                    broadcast(new PlayerDrawCardPacket(this.cpu, carta)); //Mostramos la carta que ha sacado la CPU

                    ThreadUtils.sleep(Constantes.DELAY);
                }

                //Sacamos las cartas ocultas de la CPU
                for (int i = 0; Constantes.CARTAS_OCULTAS_CPU > i; i++) {
                    Carta carta = this.mazo.sacarCarta();
                    carta.setVisible(false);

                    this.cpu.getMazo().nuevaCarta(carta);
                    broadcast(new PlayerDrawCardPacket(this.cpu, carta)); //Mostramos la carta que ha sacado la CPU

                    ThreadUtils.sleep(Constantes.DELAY);
                }

                if (this.cpu.getSaldo() > (this.cpu.getApuesta() * 2)) {
                    boolean doblarApuesta = doblarApuesta(this.cpu);

                    if (doblarApuesta) {
                        this.cpu.setDoblarApuesta(true);
                        broadcast(new PlayerDoubleBetPacket(this.cpu, this.cpu.getApuesta() * 2));
                    }
                }

                //Comprobamos los puntos de la CPU
                int puntosCPU = this.cpu.getMazo().contarPuntosReal();
                broadcast(new PlayerPointsPacket(this.cpu, puntosCPU));

                if (puntosCPU < Constantes.PUNTOS_GANAR) {
                    Controlador.Respuesta respuesta;

                    do {
                        respuesta = jugar(this.cpu);

                        if (respuesta == Controlador.Respuesta.PEDIR_CARTA) { //El jugador pide carta as?? que le damos una m??s
                            broadcast(new PlayerAskCardPacket(this.cpu));
                            Carta carta = this.mazo.sacarCarta();

                            this.cpu.getMazo().nuevaCarta(carta);
                            puntosCPU = this.cpu.getMazo().contarPuntosReal();

                            broadcast(new PlayerDrawCardPacket(this.cpu, carta));
                            broadcast(new PlayerPointsPacket(this.cpu, puntosCPU));
                        } else if (respuesta == Controlador.Respuesta.SALTAR) { //Si el jugador decide saltar su turno simplemente seguimops
                            broadcast(new PlayerStandPacket(this.cpu, puntosCPU));
                        }
                    } while (respuesta == Controlador.Respuesta.PEDIR_CARTA && puntosCPU < Constantes.PUNTOS_GANAR);
                }

                this.estadoMesa = EstadoMesa.DECIDE;
                broadcast(new GameStatePacket(this.estadoMesa));
                ThreadUtils.sleep(Constantes.DELAY);

                //Buscamos los jugadores que no han perdido o ganado!
                for (Jugador jugador : activos) {
                    int puntosJugador = jugador.getMazo().contarPuntosReal();

                    if ((puntosJugador <= Constantes.PUNTOS_GANAR && (puntosJugador > puntosCPU || puntosCPU > Constantes.PUNTOS_GANAR)) ||
                            (puntosJugador >= Constantes.PUNTOS_GANAR && puntosCPU > Constantes.PUNTOS_GANAR)) { //El jugador gana a la CPU

                        jugador.setEstado(Jugador.Estado.GANA);

                        broadcast(new PlayerWinTurnPacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    } else if (puntosJugador < puntosCPU || puntosJugador > Constantes.PUNTOS_GANAR) { //El jugador pierde contra la CPU
                        jugador.setEstado(Jugador.Estado.PIERDE);

                        broadcast(new PlayerLoseTurnPacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    } else { //El jugador empata contra la CPU
                        jugador.setEstado(Jugador.Estado.EMPATE);

                        broadcast(new PlayerTiePacket(jugador, puntosJugador));

                        ThreadUtils.sleep(Constantes.DELAY);
                    }
                }
            }

            this.estadoMesa = EstadoMesa.PAY;
            broadcast(new GameStatePacket(this.estadoMesa));
            ThreadUtils.sleep(Constantes.DELAY);

            //Damos la apuesta a los ganadores
            for (Jugador ganador : getJugadores(Jugador.Estado.GANA)) {
                double bonificador = (ganador.getMazo().contarPuntosReal() == Constantes.PUNTOS_GANAR ?
                        Constantes.BONIFICADOR_BLACK_JACK : Constantes.BONIFICADOR_NORMAL);

                int apuesta = ((int) Math.ceil(ganador.getApuesta() * bonificador));
                if (ganador.isDoblarApuesta()) {
                    apuesta = apuesta * 2;
                }

                ganador.setSaldo(ganador.getSaldo() + apuesta);
                this.cpu.setSaldo(this.cpu.getSaldo() - apuesta);

                ganador.setManosGanadas(ganador.getManosGanadas() + 1);

                broadcast(new PlayerBetPacket(ganador, apuesta, bonificador, true));
            }

            //Quitamos la apuesta los perdedores
            for (Jugador perdedor : getJugadores(Jugador.Estado.PIERDE)) {
                int apuesta = perdedor.getApuesta();
                if (perdedor.isDoblarApuesta()) {
                    apuesta = apuesta * 2;
                }

                perdedor.setSaldo(perdedor.getSaldo() - apuesta);
                this.cpu.setSaldo(this.cpu.getSaldo() + apuesta);

                this.cpu.setManosGanadas(this.cpu.getManosGanadas() + 1);

                broadcast(new PlayerBetPacket(perdedor, apuesta, Constantes.BONIFICADOR_NORMAL, false));
            }

            this.estadoMesa = EstadoMesa.FINISHING;
            broadcast(new GameStatePacket(this.estadoMesa));
            ThreadUtils.sleep(Constantes.DELAY);

            //Devolvemos las cartas al Mazo principal
            for (Jugador jugador : getJugadoresExcepto(Jugador.Estado.FUERA)) {
                jugador.getMazo().getCartas().forEach(this.mazo::nuevaCarta); //Quitamos todas las cartas de los jugadores
                jugador.getMazo().limpiarCartas(); //Borramos las cartas de la cpu
            }

            this.cpu.getMazo().getCartas().forEach(this.mazo::nuevaCarta); //Quitamos todas las cartas a la cpu
            this.cpu.getMazo().limpiarCartas(); //Borramos las cartas de la cpu
            this.mazo.getCartas().forEach(e -> e.setVisible(true)); //Hacemos todas las cartas visibles de nuevo

            //Mezclamos las cartas y aumentamos el contador de manos jugadas
            this.mazo.mezclar();
            this.manos = this.manos + 1;

            //Miramos los jugadores que se han quedado sin suficiente dinero para la puesta
            for (Jugador jugador : getJugadoresExcepto(Jugador.Estado.FUERA)) {
                if (jugador.getSaldo() < jugador.getApuesta()) {
                    jugador.setEstado(Jugador.Estado.FUERA);

                    broadcast(new PlayerOutPacket(jugador));
                } else {
                    jugador.setDoblarApuesta(false);
                    jugador.setEstado(Jugador.Estado.ACTIVO);
                }
            }

            //Comprobamos si queda alg??n jugador en la partida
            if (getJugadores(Jugador.Estado.ACTIVO).isEmpty()) { //No queda ning??n jugador, la partida ha terminado
                this.estadoMesa = EstadoMesa.PARADA;

                broadcast(new GameStatePacket(this.estadoMesa));
                ThreadUtils.sleep(Constantes.DELAY);
            } else if (this.cpu.getSaldo() < this.cpu.getApuesta()) { //La CPU no tiene m??s saldo, la partida ha terminado
                this.estadoMesa = EstadoMesa.PARADA;

                broadcast(new PlayerOutPacket(this.cpu));
                broadcast(new GameStatePacket(this.estadoMesa));
                ThreadUtils.sleep(Constantes.DELAY);
            } else { //La partida continua
                this.estadoMesa = EstadoMesa.ACTIVA;

                broadcast(new GameStatePacket(this.estadoMesa));
                ThreadUtils.sleep(Constantes.DELAY);
            }
        } while (this.estadoMesa == EstadoMesa.ACTIVA);
    }
}
