import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio_2 {
    public static class Empleado {
        private final String nombre;
        private final String depto;
        private final double salario;

        Empleado(String nombre, String depto, double salario) {
            this.nombre = nombre;
            this.depto = depto;
            this.salario = salario;
        }

        String nombre() { return nombre; }
        String depto() { return depto; }
        double salario() { return salario; }
    }
    List<Empleado> empleados = Arrays.asList(
        new Empleado("Ana", "TI", 5000),
        new Empleado("Beto", "TI", 6000),
        new Empleado("Carla", "RRHH", 4500),
        new Empleado("Diego", "Ventas", 5500)
    );
    Map<String, List<Empleado>> porDepto = empleados.stream()
        .collect(Collectors.groupingBy(Empleado::depto));
}
