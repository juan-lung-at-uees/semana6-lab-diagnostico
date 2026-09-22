# Fase D | Mapa actual de responsabilidades

| Fragmento | Responsabilidad observada | Clase actual |
|---|---|---|
| Validar null/correo/periodo/anticipación | Validación | ServicioReservas |
| Calcular total y descuento VIP | Cálculo de tarifa | ServicioReservas |
| Imprimir "Guardando reserva" | Persistencia simulada | ServicioReservas |
| Imprimir "Correo enviado" | Notificación simulada | ServicioReservas |
| Cambiar estado a CONFIRMADA | Cambio de estado de dominio | Reserva |

## Mapa conceptual

```text
ServicioReservas
├── valida entrada
├── interpreta correo
├── interpreta periodo
├── decide anticipación
├── calcula precio
├── conoce descuento VIP
├── simula persistencia
├── simula notificación
└── ordena confirmar Reserva

Reserva
└── mantiene estado
```

**Pregunta clave: ¿cuántas razones diferentes podría tener `ServicioReservas` para cambiar?**

_`ServicioReservas` viola el Principio de Responsabilidad Única (SRP) y tiene al menos 5 razones distintas para cambiar:_

_1. Cambios en las reglas de validación: Si se modifican los criterios de sintaxis del correo, la lógica de validación de períodos o las horas de anticipación mínimas._

_2. Cambios en la política comercial de precios: Si cambia la tarifa base (actualmente 40) o se altera el porcentaje de descuento para usuarios VIP (actualmente 15%)._

_3. Cambios en el mecanismo de persistencia: Si el guardado de la reserva deja de ser una simulación en consola y requiere integración con base de datos, JPA o una API externa._

_4. Cambios en el canal de notificaciones: Si el envío de correos pasa a implementarse mediante un servicio SMTP, un servicio de colas o un cliente de e-mail real._

_5. Cambios en el flujo de orquestación: Si el proceso global de la reserva requiere nuevos pasos o estados intermedios antes de la confirmación._
