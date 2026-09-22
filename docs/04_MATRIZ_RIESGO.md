# Fase J | Matriz de riesgo

| Cambio candidato | Probabilidad de romper | Impacto si rompe | Riesgo | Cómo reducirlo |
|---|---|---|---|---|
| Extraer clase de notificación | _Media_ | _Medio_ | _Medio_ | _Crear interfaz `Notificador`, mantener implementación ficticia de consola y verificar que la reserva continúe confirmándose._ |
| Introducir Correo (Value Object) | _Baja_ | _Bajo_ | _Bajo_ | _Validar sintaxis dentro del constructor y agregar pruebas unitarias aisladas para cadenas válidas e inválidas._ |
| Introducir PeriodoReserva (Value Object) | _Baja_ | _Medio_ | _Bajo_ | _Encapsular la regla `fin.isAfter(inicio)` dentro del objeto y probar bordes donde `fin == inicio`._ |
| Simplificar validaciones / Excepciones | _Alta_ | _Alto_ | _Alto_ | _Ajustar los tests de caracterización para esperar excepciones específicas sin romper contratos con clientes externos._ |
| Separar cálculo VIP | _Baja_ | _Medio_ | _Bajo_ | _Probar exhaustivamente montos resultantes para tipos `"NORMAL"` y `"VIP"` con aserciones exactas._ |

## Escala

- **Bajo:** cambio local, comportamiento bien entendido y prueba fácil de crear.
- **Medio:** afecta varias decisiones o requiere adaptar construcción de objetos.
- **Alto:** puede alterar contrato observable, flujos de error o efectos externos.