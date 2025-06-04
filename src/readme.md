# Guía Rápida de Java Streams API y Manejo de Errores

Este documento resume los conceptos clave de la API Stream de Java y las estrategias para el manejo de errores al utilizarlas.

## 1. Programación Funcional con Java Streams

-   **Concepto**: Enfoque declarativo para procesar secuencias de datos. Describe *qué* hacer, no *cómo*.
-   **Principios**: Inmutabilidad (generalmente), funciones como ciudadanos de primera clase, minimización de efectos secundarios.
-   **Stream**: Una secuencia de elementos que soporta operaciones agregadas. No almacena datos y no modifica la fuente original.
-   **Operaciones**:
    -   **Intermedias**: Devuelven un nuevo stream (ej: `filter`, `map`). Son *lazy* (perezosas).
    -   **Terminales**: Producen un resultado o efecto secundario (ej: `collect`, `forEach`). Consumen el stream.

## 2. Interfaces Funcionales Clave (`java.util.function`)

Son la base para las expresiones lambda en los streams.

-   **`Predicate<T>`**: `boolean test(T t)`
    -   Uso: Filtrar elementos. Devuelve `true` si el elemento cumple una condición.
    -   Ej: `elemento -> elemento > 10`
-   **`Function<T, R>`**: `R apply(T t)`
    -   Uso: Transformar un elemento de tipo `T` a uno de tipo `R`.
    -   Ej: `String::length` o `usuario -> usuario.getNombre()`
-   **`Consumer<T>`**: `void accept(T t)`
    -   Uso: Realizar una acción con un elemento sin devolver nada (puede tener efectos secundarios).
    -   Ej: `System.out::println`
-   **`Supplier<T>`**: `T get()`
    -   Uso: Proveer un valor de tipo `T` sin tomar argumentos.
    -   Ej: `() -> new ArrayList<>()`
-   **`UnaryOperator<T>`** (extiende `Function<T, T>`): `T apply(T t)`
    -   Uso: Transformar un elemento en otro del mismo tipo.
    -   Ej: `String::toUpperCase`
-   **`BinaryOperator<T>`** (extiende `BiFunction<T, T, T>`): `T apply(T t1, T t2)`
    -   Uso: Combinar dos elementos del mismo tipo para producir un resultado del mismo tipo (fundamental en `reduce`).
    -   Ej: `(a, b) -> a + b`
-   **`BiFunction<T, U, R>`**: `R apply(T t, U u)`
    -   Uso: Toma dos argumentos (`T`, `U`) y produce un resultado `R`.
    -   Ej: `(mapa, clave) -> mapa.get(clave)`
-   **`BiPredicate<T, U>`**: `boolean test(T t, U u)`
    -   Uso: Similar a `Predicate` pero con dos argumentos.
    -   Ej: `(str, prefijo) -> str.startsWith(prefijo)`
-   **`BiConsumer<T, U>`**: `void accept(T t, U u)`
    -   Uso: Similar a `Consumer` pero con dos argumentos.
    -   Ej: `(mapa, clave) -> System.out.println(clave + "=" + mapa.get(clave))`

## 3. Operaciones de Stream Fundamentales

### Operaciones Intermedias (Lazy)

-   **`filter(Predicate<T> predicate)`**: Selecciona elementos que cumplen la condición.
-   **`map(Function<T, R> mapper)`**: Transforma cada elemento. Puede cambiar el tipo del elemento.
-   **`flatMap(Function<T, Stream<R>> mapper)`**: Transforma cada elemento en un `Stream` y luego "aplana" todos esos streams en uno solo. Útil para mapear un elemento a cero, uno o múltiples elementos.
-   **`distinct()`**: Elimina elementos duplicados (usa `equals()`).
-   **`sorted()` / `sorted(Comparator<T> comparator)`**: Ordena los elementos.
-   **`peek(Consumer<T> action)`**: Ejecuta una acción en cada elemento a medida que fluye, principalmente para depuración. No modifica el stream.

### Operaciones Terminales (Eager)

-   **`forEach(Consumer<T> action)`**: Realiza una acción por cada elemento.
-   **`collect(Collector<T, A, R> collector)`**: Operación de reducción mutable muy versátil.
    -   Comunes: `Collectors.toList()`, `toSet()`, `toMap()`, `groupingBy()`, `joining()`, `summarizingInt()`, etc.
-   **`reduce(T identity, BinaryOperator<T> accumulator)`**: Combina elementos en un solo resultado.
-   **`reduce(BinaryOperator<T> accumulator)`**: Versión sin identidad, devuelve `Optional<T>`.
-   **`count()`**: Devuelve el número de elementos.
-   **`anyMatch(Predicate<T> predicate)`**: `true` si al menos un elemento cumple. (Cortocircuito)
-   **`allMatch(Predicate<T> predicate)`**: `true` si todos los elementos cumplen. (Cortocircuito)
-   **`noneMatch(Predicate<T> predicate)`**: `true` si ningún elemento cumple. (Cortocircuito)
-   **`findFirst()`**: Devuelve un `Optional<T>` con el primer elemento.
-   **`findAny()`**: Devuelve un `Optional<T>` con cualquier elemento (útil en paralelo).

## 4. Manejo de Errores en Streams

### a. `NullPointerException` en Lambdas
   - **Prevención**: `stream.filter(Objects::nonNull)` antes de la operación riesgosa.
   - **Manejo**: Dentro de la lambda, verificar si es `null` y devolver un valor por defecto o realizar una acción específica.
     ```java
     // .map(obj -> obj == null ? "default" : obj.toString())
     ```

### b. Excepciones No Comprobadas (Unchecked) en Lambdas
   (Ej: `NumberFormatException`, `ArithmeticException`)
   - **Captura local**: `try-catch` dentro de la lambda. Devolver un valor especial (ej: `null`, `Optional.empty()`) y luego filtrar o procesar.
     ```java
     // .map(str -> { try { return Integer.parseInt(str); } catch (NFE e) { return null; } })
     // .filter(Objects::nonNull)
     ```
   - **Uso de `Optional`**: Mapear a `Optional<R>` y luego usar `flatMap(Optional::stream)` para descartar los vacíos.

### c. Excepciones Comprobadas (Checked) en Lambdas
   Las interfaces funcionales estándar no las soportan directamente.
   - **Envolver**: Capturar la checked exception y lanzarla como una `RuntimeException`.
     ```java
     // .map(item -> { try { return operationWithCheckedException(item); } catch (IOException e) { throw new RuntimeException(e); } })
     ```
   - **Función Wrapper**: Crear un método estático que tome una lambda "peligrosa" y devuelva una `Function` estándar que maneje la excepción.

### d. `IllegalStateException` en `Collectors.toMap()`
   Ocurre con claves duplicadas sin una estrategia de fusión.
   - **Solución**: Proveer una función de merge a `Collectors.toMap()`.
     ```java
     // .collect(Collectors.toMap(keyMapper, valueMapper, (valorExistente, nuevoValor) -> nuevoValor)); // o alguna lógica de merge
     ```

### e. `NoSuchElementException` con `Optional.get()`
   Ocurre al llamar a `get()` en un `Optional` vacío.
   - **Solución**: Usar métodos seguros de `Optional`.
     - `orElse(defaultValue)`
     - `orElseGet(supplierDefaultValue)`
     - `orElseThrow(() -> new CustomException())`
     - `ifPresent(consumer)`
     - `isPresent()`

### f. Manejo de Recursos (Streams de I/O)
   Streams como `Files.lines()` deben cerrarse.
   - **Solución**: Usar `try-with-resources`.
     ```java
     // try (Stream<String> lines = Files.lines(Paths.get("archivo.txt"))) {
     //     lines.forEach(System.out::println);
     // } catch (IOException e) { /* ... */ }
     ```

---
Este resumen debería ayudarte a tener una referencia rápida de los puntos más importantes.