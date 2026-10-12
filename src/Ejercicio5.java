import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {

    private static final String RUTA_FICHERO = "Ficheros/";
    private static final String NOMBRE_FICHERO = "practicas.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(
                RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            System.out.println("¿Cuántos alumnos desea introducir?");

            int cantidadAlumnos = teclado.nextInt();
            teclado.nextLine();

            fichero.seek(fichero.length());

            for (int i = 1; i <= cantidadAlumnos; i++) {

                System.out.println("Introduzca el nombre del alumno: ");
                String nombre = teclado.nextLine();

                System.out.println("Introduzca los apellidos del alumno: ");
                String apellido = teclado.nextLine();

                System.out.println("Introduzca el DNI del alumno: ");
                String dni = teclado.nextLine();

                System.out.println("Introduzca el vehículo de prácticas: ");
                int vehiculo = teclado.nextInt();

                System.out.println("Introduzca el número de clases de prácticas del alumno: ");
                int clases = teclado.nextInt();

                teclado.nextLine();

                StringBuffer nombreAlumno = new StringBuffer(nombre);
                nombreAlumno.setLength(10);

                StringBuffer apellidoAlumno = new StringBuffer(apellido);
                apellidoAlumno.setLength(35);

                StringBuffer dniAlumno = new StringBuffer(dni);
                dniAlumno.setLength(9);

                fichero.writeChars(nombreAlumno.toString());
                fichero.writeChars(apellidoAlumno.toString());
                fichero.writeChars(dniAlumno.toString());
                fichero.writeInt(vehiculo);
                fichero.writeInt(clases);

            }

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += fichero.readChar();

                }

                String apellido = "";
                for (int i = 0; i < 35; i++) {
                    apellido += fichero.readChar();
                }

                String dni = "";
                for (int i = 0; i < 9; i++) {
                    dni += fichero.readChar();
                }

                int vehiculo = fichero.readInt();

                int clases = fichero.readInt();

                System.out.println(nombre.trim() + " | " + apellido.trim() + " | " + dni.trim()  + " | " + vehiculo + " | " + clases);

            }

        }catch (IOException e) {
            System.out.println("Error");
        }
    }
}
