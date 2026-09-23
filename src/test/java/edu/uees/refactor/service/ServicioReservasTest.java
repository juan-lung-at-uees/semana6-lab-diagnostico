package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ServicioReservasTest {

    private ServicioReservas servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioReservas();
    }

    private Reserva crearReservaBase(String tipo, String correo, LocalDateTime inicio, LocalDateTime fin) {
        return new Reserva(
                "R-TEST",
                correo,
                inicio,
                fin,
                tipo
        );
    }

    @Test
    @DisplayName("LB-01: Reserva NORMAL válida se confirma y devuelve tarifa completa (40.0)")
    void reservaValidaSeConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("NORMAL", "ana@uees.edu.ec", inicio, inicio.plusHours(1));

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40.0, total, 0.001);
    }

    @Test
    @DisplayName("LB-02: Reserva VIP válida se confirma y devuelve tarifa con 15% de descuento (34.0)")
    void reservaVipValidaSeConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("VIP", "ana@uees.edu.ec", inicio, inicio.plusHours(1));

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(34.0, total, 0.001);
    }

    @Test
    @DisplayName("LB-03: Correo sin '@' es rechazado, retorna 0.0 y permanece PENDIENTE")
    void correoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("NORMAL", "correo_incorrecto", inicio, inicio.plusHours(1));

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        assertEquals(0.0, total, 0.001);
    }

    @Test
    @DisplayName("LB-04: Periodo inválido (fin <= inicio) es rechazado, retorna 0.0 y permanece PENDIENTE")
    void periodoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("NORMAL", "ana@uees.edu.ec", inicio, inicio); // fin igual a inicio

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        assertEquals(0.0, total, 0.001);
    }

    @Test
    @DisplayName("LB-05: Límite exacto de 2 horas de anticipación se procesa correctamente")
    void anticipacionValidaProcesaCorrectamente() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("NORMAL", "ana@uees.edu.ec", inicio, inicio.plusHours(1));

        // Act
        double total = servicio.procesar(reserva, 2);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40.0, total, 0.001);
    }

    @Test
    @DisplayName("LB-06: Anticipación de 1 hora es rechazada, retorna 0.0 y permanece PENDIENTE")
    void anticipacionInvalidaRetornaCeroYNoConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = crearReservaBase("NORMAL", "ana@uees.edu.ec", inicio, inicio.plusHours(1));

        // Act
        double total = servicio.procesar(reserva, 1);

        // Assert
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        assertEquals(0.0, total, 0.001);
    }

    @Test
    @DisplayName("Condición especial: Reserva nula retorna 0.0 sin lanzar NullPointerException")
    void reservaNulaRetornaCero() {
        // Arrange
        Reserva reserva = null;

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(0.0, total, 0.001);
    }
}