# Fase L | Plan priorizado de refactorización

No implementes todavía.

| Orden | Cambio | Por qué primero / después | Pruebas requeridas | Dependencias |
|---:|---|---|---|---|
| 1 | _Construir suite de pruebas de caracterización_ | _Establece la red de seguridad fundamental antes de modificar cualquier línea en el código de producción._ | _Todos los escenarios LB-01 a LB-06_ | _Ninguna o sólo dependencias para pruebas (JUnit y otras)_ |
| 2 | _Extraer política de cálculo de tarifas | Aislar la lógica matemática pura no tiene dependencias externas e incrementa la cohesión con bajo riesgo._ | `vipConservaResultadoActual()` | _Suite de caracterización inicial_ |
| 3 | _Introducir Value Objects (`Email`, `Periodo`)_ | _Encapsula las reglas sintácticas y validaciones de datos primitivos eliminando comprobaciones duplicadas en el servicio._ | `correoSinArrobaRetornaCero()`, `periodoInvalidoNoProcesa()` | _Pruebas de validación_ |
| 4 | _Extraer Notificador y Repositorio (Interfaces) | Desacopla los efectos secundarios de I/O en consola permitiendo una arquitectura limpia y testable._ | `reservaValidaSeConfirma()` | _Value Objects creados_ |
| 5 | _Sustituir *Silent Failures* por Excepciones de Dominio_ | _Cambiar la estrategia de manejo de errores al final evita romper el contrato interno de los componentes refactorizados previamente._ | `datosInvalidosLanzaExcepcion()` | _Clases e interfaces extraídas_ |

## Ejemplo de razonamiento

1. Caracterizar casos actuales.
2. Extraer cálculo a método con intención.
3. Separar notificación / persistencia.
4. Introducir Value Objects.

La evaluación se centra en **la justificación**, no en repetir exactamente este orden.