package edu.uees.refactor.service;

/**
 * Encapsula la responsabilidad de enviar notificaciones de reserva.
 */
public class NotificadorReserva {

    public void notificar(String correo) {
        System.out.println(
                "Correo enviado a " + correo
        );
    }
}
