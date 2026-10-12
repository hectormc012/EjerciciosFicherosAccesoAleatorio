import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca la edad mínima por la que quiere filtrar los juego: ");
        int edadIntroducida = sc.nextInt();

        try (RandomAccessFile fichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_FICHERO, "r")) {

            System.out.println("Estos son los juegos que están dentro de esa edad:");

            // Volvemos al principio del fichero
            fichero.seek(0);

            // Mostramos los registros
            while (fichero.getFilePointer() < fichero.length()) {

                int numero = fichero.readInt();


                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += fichero.readChar();
                }

                int edad = fichero.readInt();

                String clasificacion = "";
                for (int i = 0; i < 10; i++) {
                    clasificacion += fichero.readChar();
                }

                if (edad <= edadIntroducida) {
                    System.out.println(nombre.trim() + " | " + clasificacion.trim());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
