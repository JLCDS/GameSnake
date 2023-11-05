# GameSnake
## Puntuación Aleatoria en el Juego Snake
Cada vez que se genera u	na nueva comida, se asigna un valor aleatorio al alimento utilizando la clase Random. 
Este valor aleatorio se almacena en la variable foodValue. Cuando la serpiente come el alimento en el método actualizar(), 
se incrementa la puntuación del jugador en función del valor aleatorio del alimento. Esto se logra sumando foodValue a la puntuación existente.
