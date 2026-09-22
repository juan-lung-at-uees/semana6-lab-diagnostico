# Fase C | Línea base manual

Completa los seis escenarios **sin refactorizar el diseño**.

| ID | Escenario | Entrada principal | Estado | Retorno | Mensajes / excepción |
|---|---|---|---|---|---|
| LB-01 | NORMAL válida | NORMAL, correo válido, 5h | _CONFIRMADA_ | _40.0_ | `Guardando reserva R-001`<br>`Correo enviado a ana@uees.edu.ec`<br>`Estado: CONFIRMADA`<br>`Total: 40.0` |
| LB-02 | VIP válida | VIP, correo válido, 5h | _CONFIRMADA_ | _34.0_ | `Guardando reserva R-001`<br>`Correo enviado a ana@uees.edu.ec`<br>`Estado: CONFIRMADA`<br>`Total: 34.0` |
| LB-03 | Correo inválido | "incorrecto" | _PENDIENTE_ | _0.0_ | `Estado: PENDIENTE`<br>`Total: 0.0`<br>_(falla silenciosa)_ |
| LB-04 | Periodo inválido | fin <= inicio | _PENDIENTE_ | _0.0_ | `Estado: PENDIENTE`<br>`Total: 0.0`<br>_(falla silenciosa)_ |
| LB-05 | Límite válido | 2h anticipación | _CONFIRMADA_ | _40.0_ | `Guardando reserva R-001`<br>`Correo enviado a ana@uees.edu.ec`<br>`Estado: CONFIRMADA`<br>`Total: 40.0` |
| LB-06 | Límite inválido | 1h anticipación | _PENDIENTE_ | _0.0_ | `Estado: PENDIENTE`<br>`Total: 0.0`<br>_(falla silenciosa)_ |

## Preguntas

1. ¿Qué valores cambian entre NORMAL y VIP?

   _Cambia únicamente el monto devuelto por `procesar()`: 40.0 para tipo NORMAL y 34.0 para VIP (15% de descuento aplicado)._

2. ¿Qué casos dejan la reserva en PENDIENTE?
   
   _Todos los casos con entradas no procesables o inválidas: objeto `Reserva` nulo, correo nulo o sin '@', rango de fechas donde la fecha de fin no es posterior a la de inicio, y anticipación menor a 2 horas (`horasAnticipacion < 2`)._

3. ¿Qué devuelve `procesar()` cuando una entrada no es procesable?
   
   _Devuelve `0.0`._

4. ¿Existe alguna excepción visible en el flujo actual?
   
   _No. El sistema maneja las validaciones fallidas con retornos silenciosos (*silent failures*), devolviendo 0.0 sin lanzar ninguna excepción explícita._

5. ¿Qué mensajes aparecen solo cuando la reserva se confirma?
   
   _Los mensajes_ `Estado: CONFIRMADA`