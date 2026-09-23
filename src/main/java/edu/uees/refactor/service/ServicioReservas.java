package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;
import edu.uees.refactor.repository.ReservaRepository;

/**
 * Código heredado intencional para el Laboratorio 1.
 *
 * IMPORTANTE:
 * No refactorizar antes de completar la línea base,
 * el diagnóstico y el plan de refactorización.
 */
public class ServicioReservas {

    private final ReservaRepository repository;
    private final NotificadorReserva notificador;

    public ServicioReservas(ReservaRepository repository, NotificadorReserva notificador) {
        this.repository = repository;
        this.notificador = notificador;
    }

    public ServicioReservas() {
        this(new ReservaRepository(), new NotificadorReserva());
    }

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (esInvalida(r, horasAnticipacion)) {
            return 0;
        }

        double total = calcularTotal(r);

        repository.guardar(r.getId());
        notificador.notificar(r.getCorreo());

        r.confirmar();

        return total;
    }

    private boolean esInvalida(Reserva r, int horasAnticipacion) {
        if (r == null) return true;
        if (r.getCorreo() == null || !r.getCorreo().contains("@")) return true;
        if (r.getInicio() == null || r.getFin() == null || !r.getFin().isAfter(r.getInicio())) return true;
        return horasAnticipacion < 2;
    }
    
    private double calcularTotal(Reserva r) {
        double total = 40;

        if ("VIP".equals(r.getTipo())) {
            return total * 0.85;
        }

        return total;
    }

}
