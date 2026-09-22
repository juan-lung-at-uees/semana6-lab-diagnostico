# Fase I | Matriz de diagnóstico obligatoria

Completa al menos cinco filas con evidencias reales.

| # | Ubicación | Smell / problema | Categoría | Impacto | Candidato | Prueba necesaria |
|---:|---|---|---|---|---|---|
| 1 | _ServicioReservas (L. 17-43)_ | _Responsabilidades excesivas / God Method_ | _Responsabilidades_ | _Imposible probar la lógica de cálculo sin ejecutar salidas de consola o acoplar efectos secundarios de I/O._ | _Extract Class / Inyección de dependencias para notificación y persistencia_ | _reservaValidaSeConfirma()_ |
| 2 | _ServicioReservas (L. 24-25, 29-33)_ | _Primitive Obsession (Correo y Período)_ | _Datos_ | _La validación de correo solo busca '@' y la de fechas es dispersa; los datos viajan como primitivos o tipos genéricos sin garantizar sus invariantes._ | _Introducir Value Objects (Email, Periodo)_ | _correoInvalidoRechaza(), periodoInvalidoRechaza()_ |
| 3 | _ServicioReservas (L. 19, 27, 33, 35)_ | _Magic Values & Silent Failures_ | _Condicionales_ | _Retornar 0 ante un error enmascara fallos de validación; el invocador no puede distinguir si la tarifa fue $0 o si falló una regla de negocio._ | _Lanzar excepciones de dominio o devolver un resultado explícito de validación_ | _datosInvalidosLanzaExcepcion()_ |
| 4 | _ServicioReservas (L. 37-41)_ | _Feature Envy & Magic Numbers_ | _Negocio / Datos_ | _El precio base 40 y el factor 0.85 están embebidos en el servicio en lugar de pertenecer al modelo comercial._ | _Extraer política de precios o Enum de tipo de reserva con estrategia_ | _calculoDescuentoVipCorrecto()_ |
| 5 | _ServicioReservas (L. 17)_ | _Long Parameter List / Testabilidad rígida_ | _Testabilidad_ | _Pasar Reserva junto con horasAnticipacion fuerza a configurar parámetros sueltos en lugar de consultar la reserva o un proveedor de tiempo._ | _Encapsular contexto de reserva o inyectar reloj/proveedor de tiempo_ | _procesarReservaConTiempoSimulado()_ |
| 6 | _Reserva (L. 28-30)_ | _Modelo de Dominio Anémico_ | _Diseño_ | _Reserva actúa principalmente como un DTO pasivo cuyo estado interno es manipulado externamente por el servicio._ | _Mover reglas de transición de estado y validaciones al modelo de dominio_ | _confirmacionModificaEstadoInterno()_ |

Evita diagnósticos genéricos como:

- "código feo"
- "mala práctica"
- "viola SOLID"

Debes indicar **dónde**, **qué ocurre**, **qué riesgo produce** y **cómo podrías verificar el cambio**.