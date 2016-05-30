package semaphore.sockets;

/*
 * Javier Abell�n. 27 de noviembre de 2003
 *
 * SocketCliente.java
 *
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Clase que crea un socket cliente, establece la conexi�n y lee los datos
 * del servidor, escribi�ndolos en pantalla.
 */
public class SocketCliente
 {
     /** Programa principal, crea el socket cliente */
     public static void main (String [] args)
     {
         new SocketCliente();
     }
     ObjectOutputStream oos = null;
     /**
      * Crea el socket cliente y lee los datos
      */
     
     public SocketCliente()
     {
         
         try
         {
             /* Se crea el socket cliente */
             Socket socket = new Socket ("localhost", 35557);
             System.out.println ("conectado");
             oos = new ObjectOutputStream(socket.getOutputStream());
             DatoSocket dato = new DatoSocket(17, "David");
             dato.setAcertado(false);
             System.out.println("Introduce numero apuesta:");
             Scanner scan = new Scanner(System.in);
             dato.setNumero(scan.nextInt());
             System.out.println("Sending request to Socket Server");
             oos.writeObject(dato);
                     
             /* Se obtiene un stream de lectura para leer objetos */
             ObjectInputStream bufferObjetos =
                new ObjectInputStream (socket.getInputStream());
             
             /* Se lee un Datosocket que nos env�a el servidor y se muestra 
              * en pantalla */
       
             DatoSocket dato2 = (DatoSocket)bufferObjetos.readObject();
           
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
}