// A3_CompletableFuture.java
// Requiere Java 8+

import java.util.concurrent.CompletableFuture;

public class Ejercicio_3 {
    public static void main(String[] args) throws Exception {
        // Tarea asíncrona
        CompletableFuture<String> futuro = CompletableFuture
            .supplyAsync(() -> {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                return "Usuario_123";
            })
            .thenApply(nombre -> "Hola, " + nombre)
            .exceptionally(ex -> "Error: " + ex.getMessage());

        System.out.println("Resultado: " + futuro.get());

        // Combinar dos futuros
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 58);
        CompletableFuture<Integer> suma = a.thenCombine(b, Integer::sum);
        System.out.println("Suma asíncrona: " + suma.get()); // 100
    }
}
