
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            // Calculamos el siguiente número de orden
            int numero = (int) (fichero.length() / 48) + 1;

            System.out.println("¿Cuántos juegos quieres introducir?");
            int cantidad = teclado.nextInt();
            teclado.nextLine();

            fichero.seek(fichero.length());

            // Escribimos los registros
            for (int i = 0; i < cantidad; i++) {

                System.out.println("Introduce el nombre del juego:");
                String nombre = teclado.nextLine();

                System.out.println("Introduce la edad mínima:");
                int edad = teclado.nextInt();
                teclado.nextLine();

                System.out.println("Introduce la clasificación:");
                String clasificacion = teclado.nextLine();

                StringBuffer nombreJuego = new StringBuffer(nombre);
                nombreJuego.setLength(10);

                StringBuffer tipo = new StringBuffer(clasificacion);
                tipo.setLength(10);

                fichero.writeInt(numero);
                fichero.writeChars(nombreJuego.toString());
                fichero.writeInt(edad);
                fichero.writeChars(tipo.toString());

                numero++;
            }

            // Volvemos al principio del fichero
            fichero.seek(0);

            // Mostramos los registros
            while (fichero.getFilePointer() < fichero.length()) {

                int identificador = fichero.readInt();

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += fichero.readChar();
                }

                int edad = fichero.readInt();

                String clasificacion = "";
                for (int i = 0; i < 10; i++) {
                    clasificacion += fichero.readChar();
                }

                System.out.println(identificador + " | " + nombre.trim() + " | " + edad + " | " + clasificacion.trim());
            }

        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
    }
}
