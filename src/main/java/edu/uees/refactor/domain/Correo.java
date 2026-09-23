package edu.uees.refactor.domain;

/**
 * Value Object que encapsula la validez e invariante de una dirección de correo electrónico.
 */
public record Correo(String valor) {

    public boolean esValido() {
        return valor != null && valor.contains("@");
    }
}
