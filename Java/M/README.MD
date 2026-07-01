INVESTIGACIÓN ADICIONAL
A1 Sealed Classes (Java 17+)
Explicación
Las sealed classes (clases selladas) permiten restringir qué clases pueden extender o implementar una clase o
interfaz. Se declaran con la palabra clave sealed y la cláusula permits. Las subclases deben ser final, sealed o
non-sealed. Trabajan perfectamente con pattern matching y records.
Ventajas
- Control explícito de la jerarquía de herencia.
- El compilador garantiza exhaustividad en switch con pattern matching.
- Modelado de dominios cerrados más seguro que enums.
Desventajas
- Requiere Java 17+.
- Todas las subclases deben estar en el mismo paquete/módulo.
- Mayor complejidad de diseño inicial.
Cuándo Utilizarlo
Para modelar jerarquías de tipos cerradas: resultados de operaciones (Success/Failure), formas geométricas,
estados de un proceso.
Ejemplo de Código
Implementación
// Jerarquía sellada
sealed interface Figura permits Circulo, Rectangulo, Triangulo {}
record Circulo   (double radio)           
implements Figura {}
record Rectangulo(double base, double alt) implements Figura {}
record Triangulo (double base, double alt) implements Figura {}
static double area(Figura f) {
return switch (f) {
case Circulo    
}
c -> Math.PI * c.radio() * c.radio();
case Rectangulo r -> r.base() * r.alt();
case Triangulo  t -> (t.base() * t.alt()) / 2;
}; // El compilador verifica exhaustividad
INVESTIGACIÓN ADICIONAL
A2 Collectors.groupingBy() (Java 8+)
Explicación
Collectors.groupingBy() es un collector de la API Streams que agrupa elementos de un stream en un Map>
según una función clasificadora. Soporta downstream collectors para contar, sumar, promediar o transformar
los grupos.
Ventajas
- Agrupa datos en una sola operación declarativa.
- Soporta agrupamiento multinivel (groupingBy dentro de groupingBy).
- Compatible con counting(), summingInt(), mapping() como downstream.
Desventajas
- Puede ser complejo con múltiples niveles de agrupamiento.
- El Map resultante es HashMap (orden no garantizado).
- Requiere comprensión previa de la API Streams.
Cuándo Utilizarlo
Para reportes, estadísticas o clasificación de datos: agrupar empleados por departamento, pedidos por estado,
etc.
Ejemplo de Código
Implementación
record Empleado(String nombre, String depto, double salario) {}
List<Empleado> empleados = List.of(
new Empleado("Ana",   "TI",  5000),
new Empleado("Beto",  "TI",  6000),
new Empleado("Carlos","RRHH",4500),
new Empleado("Diana", "RRHH",4800)
);
Map<String, List<Empleado>> porDepto =
empleados.stream().collect(Collectors.groupingBy(Empleado::depto));
Map<String, Double> salarioPromedio = empleados.stream()
.collect(Collectors.groupingBy(Empleado::depto,
Collectors.averagingDouble(Empleado::salario)));
System.out.println(salarioPromedio); // {TI=5500.0, RRHH=4650.0}
INVESTIGACIÓN ADICIONAL
A3 CompletableFuture (Java 8+)
Explicación
CompletableFuture implementa Future y CompletionStage, permitiendo programación asíncrona no bloqueante
con encadenamiento de operaciones. Permite ejecutar tareas en un thread pool, transformar resultados
(thenApply), combinar futuros (thenCombine, allOf) y manejar errores (exceptionally, handle) de forma fluida.
Ventajas
- Programación asíncrona sin callbacks profundamente anidados.
- Encadenamiento fluido de operaciones asíncronas.
- Manejo de errores centralizado con exceptionally/handle.
Desventajas
- Debugging más complejo que código síncrono.
- Stack traces pueden ser difíciles de interpretar.
- Gestión del thread pool requiere configuración cuidadosa.
Cuándo Utilizarlo
Para llamadas a servicios externos, I/O intensivo, o cuando se desea ejecutar tareas en paralelo sin bloquear
el hilo principal.
Ejemplo de Código
Implementación
// Tarea asíncrona encadenada
CompletableFuture<String> futuro = CompletableFuture
.supplyAsync(() -> buscarUsuario(1))       
// hilo pool
.thenApply(usuario -> "Hola, " + usuario) // transforma
.exceptionally(ex -> "Error: " + ex.getMessage());
String resultado = futuro.get(); // bloquea solo aquí
System.out.println(resultado);
// Combinar dos futuros
CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 42);
CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 58);
a.thenCombine(b, Integer::sum).thenAccept(System.out::println); // 100
INVESTIGACIÓN ADICIONAL
A4 String Methods Modernos (Java 11-15+)
Explicación
Java 11 y versiones posteriores añadieron nuevos métodos útiles a la clase String: isBlank() (verifica si está
vacía o solo tiene espacios), strip()/stripLeading()/stripTrailing() (versión Unicode-aware de trim()), repeat(n)
(repite n veces), lines() (stream de líneas), indent() (Java 12) y stripIndent()/translateEscapes() (Java 15).
Ventajas
- Operaciones comunes sin librerías externas (Apache Commons, etc.).
- strip() maneja correctamente Unicode (trim() solo ASCII).
- lines() retorna un Stream, integrable con la API de Streams.
Desventajas
- Requieren Java 11+ (algunos Java 12+/15+).
- Algunos equipos aún trabajan con Java 8 por compatibilidad.
- No todos los métodos están disponibles en versiones anteriores.
Cuándo Utilizarlo
Procesamiento de texto, validación de entradas, manipulación de strings sin dependencias adicionales.
Ejemplo de Código
Implementación
// Java 11+
System.out.println("  ".isBlank());          
System.out.println(" Hola ".strip());         
// true
// "Hola"
System.out.println("-".repeat(20));           
// -------------------
String multilinea = "linea1\nlinea2\nlinea3";
multilinea.lines()
.map(String::toUpperCase)
.forEach(System.out::println);
// LINEA1 / LINEA2 / LINEA3
// Java 12+
String indentado = "Texto".indent(4); // "    
Texto\n"
INVESTIGACIÓN ADICIONAL
A5 Interface Default & Static Methods (Java 8+)
Explicación
Java 8 permitió que las interfaces tuvieran métodos con implementación mediante las palabras clave default y
static. Los métodos default son heredados por las clases implementadoras y pueden sobreescribirse. Los
métodos static de interfaz son utilitarios que no se heredan. Esto habilitó la evolución de APIs sin romper
compatibilidad binaria.
Ventajas
- Permite añadir métodos a interfaces existentes sin romper implementaciones.
- Agrupa utilidades relacionadas junto a la interfaz (static).
- Habilitó la API Streams y Comparator.comparing() en Java 8.
Desventajas
- Puede generar ambigüedad al implementar dos interfaces con default del mismo nombre.
- Rompe el principio de que las interfaces solo definen contrato.
- Los métodos default con lógica compleja pueden ser difíciles de rastrear.
Cuándo Utilizarlo
Para proporcionar implementaciones opcionales o de conveniencia en interfaces; para métodos de fábrica o
utilidad en la propia interfaz.
Ejemplo de Código
Implementación
interface Saludo {
    String nombre(); // abstracto
    default String saludar() { // default
        return "Hola, " + nombre() + "!";
    }
    static Saludo de(String nombre) { // static factory
        return () -> nombre;
    }
}
Saludo s = Saludo.de("Java");
System.out.println(s.saludar()); // Hola, Java!
// Comparator.comparing() usa default methods internamente
List.of("Banana","Manzana","Uva")
    .stream()
    .sorted(Comparator.comparing(String::length))
    .forEach(System.out::println); // Uva, Banana, Manzan
