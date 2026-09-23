# Reflexión técnica final

Extensión sugerida: **250–350 palabras**.

### Actividad 1
Responde:

1. ¿Qué problema de diseño genera mayor riesgo y por qué?<br>_El problema de diseño que genera mayor riesgo es la concentración excesiva de responsabilidades en `ServicioReservas`, combinada con el manejo de errores mediante retornos silenciosos (*silent failures*). Mezclar la lógica comercial con salidas a consola e interpretar cualquier fallo retornando `0.0` oculta problemas serios en producción y complica la evolución del sistema._

2. ¿Qué problema parece fácil de corregir, pero podría alterar comportamiento?<br>_Reemplazar esos retornos `0.0` por Excepciones de dominio parece un ajuste facilísimo de hacer, pero tiene el peligro latente de alterar el comportamiento observable. Si algún cliente externo ya consume la clase y espera recibir `0.0` para controlar su flujo ante una entrada inválida, lanzarle una excepción rompería su ejecución abruptamente._

3. ¿Qué pruebas son indispensables antes de tocar el código?<br>_Antes de tocar una sola línea de código, las pruebas indispensables son las de caracterización basadas en nuestra línea base (LB-01 a LB-06). Necesitamos congelar el estado actual: verificar que una reserva VIP devuelva 34.0, que un correo sin `@` retorne 0.0 y que una anticipación menor a 2 horas deje la reserva en `PENDIENTE`._

4. ¿Qué responsabilidad moverías primero?<br>_La primera responsabilidad que movería es el cálculo de tarifas y descuentos VIP. Es una lógica de negocio pura, sin dependencias de I/O ni efectos secundarios._ 

5. ¿Qué evidencia usarías para defender esa decisión?<br>_Para defender técnicamente esta decisión, presentaría como evidencia la Matriz de Diagnóstico junto a una suite de pruebas automatizadas totalmente en verde, demostrando que aislar la matemática de precios no altera las respuestas del sistema._

6. ¿Qué diferencia existe entre refactorizar y realizar un cambio funcional?<br>_La diferencia fundamental entre refactorizar y realizar un cambio funcional radica en el contrato externo. Refactorizar reorganiza el diseño y la estructura interna del código para hacerlo más legible, mantenible y testable, garantizando que el comportamiento observable sea idéntico. Un cambio funcional, en contraste, modifica o añade reglas de negocio, alterando los resultados o efectos que el usuario y los clientes perciben._

### Actividad 2
1. ¿Qué prueba consideras más importante para comenzar la refactorización?<br>_La prueba del escenario feliz VIP (`reservaValidaSeConfirma`), ya que combina la ejecución de la regla de descuento del negocio, la actualización de estado de la entidad y la emisión de efectos secundarios en consola. Proteger el caso más complejo asegura que los caminos clave del sistema no sufran regresiones tempranas._   

2. ¿Qué comportamiento fue más difícil de caracterizar?<br>_El comportamiento ante errores de validación (como correo o período inválido), debido a que el sistema utiliza un fallo silencioso (**silent failure**) que retorna un número (`0.0`) sin alterar el estado de la reserva ni lanzar excepciones. Fue necesario verificar simultáneamente tanto el retorno numérico como el estado que permanecía en `PENDIENTE`._

3. ¿Qué diferencia existe entre comprobar un valor retornado y comprobar el estado de un objeto?<br>_Comprobar un valor retornado evalúa una salida pura/funcional del método (ej. el precio `40.0` o `34.0`). Comprobar el estado de un objeto evalúa los efectos secundarios e mutaciones internas causadas en el dominio (ej. verificar que `reserva.getEstado()` pasó de `PENDIENTE` a `CONFIRMADA`)._

4. ¿Cómo detectó JUnit la regresión provocada durante el experimento?<br>_JUnit detectó la falla al ejecutar la aserción `assertEquals(34.0, total, 0.001)`. Al cambiar el factor a `0.80`, el método retornó `32.0`, por lo que la aserción falló al no coincidir el valor esperado con el real dentro del margen de tolerancia especificado._

5. ¿Qué riesgo existiría si realizaras varias refactorizaciones antes de volver a ejecutar las pruebas?<br>_El riesgo principal es el efecto **bola de nieve**: si acumulas múltiples cambios estructurales y una prueba falla, se vuelve extremadamente difícil identificar cuál de todos los cambios provocó la regresión. Ejecutar las pruebas en ciclos cortos paso a paso permite aislar el error al instante._

## Checklist
### Actividad 1
- [X] Proyecto base compila y ejecuta.
- [X] Seis escenarios de línea base.
- [X] Mapa de responsabilidades.
- [X] Mínimo cinco problemas diagnosticados.
- [X] Matriz de riesgo.
- [X] Pruebas propuestas.
- [X] Plan priorizado.
- [X] Commit Git del estado inicial.
- [X] Reflexión técnica.
### Actividad 2
- [X] pom.xml con JUnit 5.
- [X] ServicioReservaTest.java.
- [X] Mínimo seis pruebas relevantes; siete recomendado.
- [X] AAA reconocible y nombres expresivos.
- [X] Evidencia de mvn clean test en verde.
- [X] Commit dedicado a pruebas.
- [X] Micro-refactorización Extract Method protegida.
- [X] Commit dedicado a la micro-refactorización.
- [X] Captura o texto breve de la regresión intencional detectada.
- [X] Reflexión técnica de 250–350 palabras.
