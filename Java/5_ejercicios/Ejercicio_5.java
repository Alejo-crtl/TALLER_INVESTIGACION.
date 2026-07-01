import java.util.*;

public class Ejercicio_5 {
    public static void main(String[] args) {
        // 1. Método static de Comparator: comparing()
        List<String> frutas = new ArrayList<>(Arrays.asList("Banana", "Manzana", "Uva"));
        frutas.sort(Comparator.comparing(String::length));
        System.out.println("Ordenadas por longitud: " + frutas);

        // 2. Método default de Map: getOrDefault()
        Map<String, String> config = new HashMap<>();
        config.put("host", "localhost");
        String puerto = config.getOrDefault("port", "8080");
        System.out.println("Puerto (default): " + puerto);

        // 3. Método default de Iterable: forEach()
        List<Integer> numeros = Arrays.asList(10, 20, 30);
        System.out.print("Números: ");
        numeros.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
