# Reflexión técnica final

Extensión sugerida: **250–350 palabras**.

Responde:

1. ¿Qué problema de diseño genera mayor riesgo y por qué?<br>_El problema de diseño que genera mayor riesgo es la concentración excesiva de responsabilidades en `ServicioReservas`, combinada con el manejo de errores mediante retornos silenciosos (*silent failures*). Mezclar la lógica comercial con salidas a consola e interpretar cualquier fallo retornando `0.0` oculta problemas serios en producción y complica la evolución del sistema._

2. ¿Qué problema parece fácil de corregir, pero podría alterar comportamiento?<br>_Reemplazar esos retornos `0.0` por Excepciones de dominio parece un ajuste facilísimo de hacer, pero tiene el peligro latente de alterar el comportamiento observable. Si algún cliente externo ya consume la clase y espera recibir `0.0` para controlar su flujo ante una entrada inválida, lanzarle una excepción rompería su ejecución abruptamente._

3. ¿Qué pruebas son indispensables antes de tocar el código?<br>_Antes de tocar una sola línea de código, las pruebas indispensables son las de caracterización basadas en nuestra línea base (LB-01 a LB-06). Necesitamos congelar el estado actual: verificar que una reserva VIP devuelva 34.0, que un correo sin `@` retorne 0.0 y que una anticipación menor a 2 horas deje la reserva en `PENDIENTE`._

4. ¿Qué responsabilidad moverías primero?<br>_La primera responsabilidad que movería es el cálculo de tarifas y descuentos VIP. Es una lógica de negocio pura, sin dependencias de I/O ni efectos secundarios._ 

5. ¿Qué evidencia usarías para defender esa decisión?<br>_Para defender técnicamente esta decisión, presentaría como evidencia la Matriz de Diagnóstico junto a una suite de pruebas automatizadas totalmente en verde, demostrando que aislar la matemática de precios no altera las respuestas del sistema._

6. ¿Qué diferencia existe entre refactorizar y realizar un cambio funcional?<br>_La diferencia fundamental entre refactorizar y realizar un cambio funcional radica en el contrato externo. Refactorizar reorganiza el diseño y la estructura interna del código para hacerlo más legible, mantenible y testable, garantizando que el comportamiento observable sea idéntico. Un cambio funcional, en contraste, modifica o añade reglas de negocio, alterando los resultados o efectos que el usuario y los clientes perciben._

## Checklist

- [X] Proyecto base compila y ejecuta.
- [X] Seis escenarios de línea base.
- [X] Mapa de responsabilidades.
- [X] Mínimo cinco problemas diagnosticados.
- [X] Matriz de riesgo.
- [X] Pruebas propuestas.
- [X] Plan priorizado.
- [X] Commit Git del estado inicial.
- [X] Reflexión técnica.
