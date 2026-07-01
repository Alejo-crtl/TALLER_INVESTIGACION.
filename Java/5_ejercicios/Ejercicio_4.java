public class Ejercicio_4 {
    public static void main(String[] args) {
        System.out.println("'   '.isBlank() = " + "   ".trim().isEmpty());
        System.out.println("strip(): '" + "  Hola Mundo  ".trim() + "'");

        StringBuilder repeat = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            repeat.append("--");
        }
        System.out.println("repeat(10): " + repeat);

        String multilinea = "linea1\nlinea2\nlinea3";
        System.out.println("Líneas en mayúsculas:");
        for (String linea : multilinea.split("\\n")) {
            System.out.println(linea.toUpperCase());
        }

        System.out.println("Con sangría:");
        for (String linea : "Texto".split("\\n")) {
            System.out.println("    " + linea);
        }
    }
}

