package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Código heredado intencional para el Laboratorio 1.
 *
 * IMPORTANTE:
 * No refactorizar antes de completar la línea base,
 * el diagnóstico y el plan de refactorización.
 */
public class ServicioReservas {

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (esInvalida(r, horasAnticipacion)) {
            return 0;
        }

        double total = calcularTotal(r);

        System.out.println(
                "Guardando reserva " + r.getId()
        );

        System.out.println(
                "Correo enviado a " + r.getCorreo()
        );

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
