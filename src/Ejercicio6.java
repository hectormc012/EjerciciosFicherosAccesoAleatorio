import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String FICHERO_ORIGEN = "practicas.dat";
    private static final String FICHERO_DESTINO = "precio.dat";

    public static void main(String[] args) {

        try (RandomAccessFile ficheroOrigen = new RandomAccessFile(
                RUTA_FICHERO + FICHERO_ORIGEN, "r");
            RandomAccessFile ficheroDestino = new RandomAccessFile(
                    RUTA_FICHERO + FICHERO_DESTINO, "rw")) {

            ficheroOrigen.seek(0);
            ficheroDestino.setLength(0);

            while (ficheroOrigen.getFilePointer() < ficheroOrigen.length()) {

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += ficheroOrigen.readChar();
                }

                String apellidos = "";
                for (int i = 0; i < 35; i++) {
                    apellidos += ficheroOrigen.readChar();
                }

                String dni = "";
                for (int i = 0; i < 9; i++) {
                    dni += ficheroOrigen.readChar();
                }

                int vehiculo = ficheroOrigen.readInt();

                int clases = ficheroOrigen.readInt();

                double precio = clases * 45.7;

                ficheroDestino.writeChars(nombre);
                ficheroDestino.writeChars(dni);
                ficheroDestino.writeInt(clases);
                ficheroDestino.writeDouble(precio);
            }

            ficheroDestino.seek(0);
            while (ficheroDestino.getFilePointer() < ficheroDestino.length()) {

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += ficheroDestino.readChar();
                }

                String dni = "";
                for (int i = 0; i < 9; i++) {
                    dni += ficheroDestino.readChar();
                }

                int clases = ficheroDestino.readInt();

                double precio = ficheroDestino.readDouble();

                System.out.println(nombre + " | " + dni + " | " + clases + " | " + precio + "€");
            }

        }catch (IOException e) {
            System.out.println("Error");
        }
    }
}
