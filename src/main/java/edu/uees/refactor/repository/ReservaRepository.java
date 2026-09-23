package edu.uees.refactor.repository;

/**
 * Encapsula la responsabilidad de la persistencia de reservas.
 */
public class ReservaRepository {

    public void guardar(String id) {
        System.out.println(
                "Guardando reserva " + id
        );
    }
}
