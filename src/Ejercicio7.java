import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String FICHERO_ORIGEN = "precio.dat";
    private static final String FICHERO_DESTINO = "descuento.dat";

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduzca el porcentaje de descuento: ");
        double descuentoIntroducido = teclado.nextDouble();

        try (RandomAccessFile ficheroOrigen = new RandomAccessFile(
                RUTA_FICHERO + FICHERO_ORIGEN, "r");
            RandomAccessFile ficheroDestino = new RandomAccessFile(
                    RUTA_FICHERO + FICHERO_DESTINO, "rw")){

            ficheroOrigen.seek(0);
            ficheroDestino.setLength(0);

            while (ficheroOrigen.getFilePointer() < ficheroOrigen.length()) {

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += ficheroOrigen.readChar();
                }

                String dni = "";
                for (int i = 0; i < 9; i++) {
                    dni += ficheroOrigen.readChar();
                }

                int clases = ficheroOrigen.readInt();

                double precio = ficheroOrigen.readDouble();

                double descuento = precio - ((precio * descuentoIntroducido) / 100);

                ficheroDestino.writeChars(dni);
                ficheroDestino.writeDouble(precio);
                ficheroDestino.writeDouble(descuento);
            }

            ficheroDestino.seek(0);

            while (ficheroDestino.getFilePointer() < ficheroDestino.length()) {

                String dni = "";
                for (int i = 0; i < 9; i++) {
                    dni += ficheroDestino.readChar();
                }

                double precio = ficheroDestino.readDouble();

                double descuento = ficheroDestino.readDouble();

                System.out.println(dni + " | " + precio + "€ | " + descuento + "€");
            }

        }catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
