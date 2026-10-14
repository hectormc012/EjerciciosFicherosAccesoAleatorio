import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1YEjercicio2 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            fichero.setLength(0);

            for (int i = 0; i < 6; i++) {
                fichero.writeInt(1);
            }

            for (int i = 0; i < 10; i++) {
                fichero.writeInt(2);
            }

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {
                int numero = fichero.readInt();
                System.out.print(numero + " ");
            }

            System.out.println();

            System.out.println("Introduzca un número entre 0 y 9:");
            int numeroIntroducido = teclado.nextInt();

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {

                int numero = fichero.readInt();

                if (numero == 2) {
                    fichero.seek(fichero.getFilePointer() - 4);
                    fichero.writeInt(numeroIntroducido);
                }
            }

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {
                int numero = fichero.readInt();
                System.out.print(numero + " ");
            }

            System.out.println();

            System.out.println("Introuduzca otro número entre 0 y 9: ");
            int numeroIntroducido2 = teclado.nextInt();

            System.out.println("Cuántas veces quieres que se lea este número: ");
            int cantidad =  teclado.nextInt();

            for (int i = 0; i < cantidad; i++) {
                fichero.writeInt(numeroIntroducido2);
            }

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {
                int numero = fichero.readInt();
                System.out.print(numero + " ");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}