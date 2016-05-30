package semaphore.sockets;

/*
 * Javier Abell�n. 19 de noviembre de 2003
 *
 * SocketServidor.java
 * Ejemplo de un socket servidor m�nimo en java.
 */

import java.net.*;
import java.io.*;

/**
 * Clase principal que instancia un socket servidor, acepta una conexion
 * de un cliente y le envia un entero y una cadena de caracteres.
 */
public class SocketServidor
{    
    public static void main (String [] args)
    {
        // Se instancia la clase principal para que haga todo lo que tiene que
        // hacer el ejemplo
        new SocketServidor();
    }
    
    

     /**
      * Constructor por defecto. Hace todo lo que hace el ejemplo.
      */
    public SocketServidor()
    {
        try
        {
            // Se crea un socket servidor atendiendo a un determinado puerto.
            // Por ejemplo, el 35557.
            ServerSocket socket = new ServerSocket (35557);
            int count = 0;
            //Creamos un numero aleatorio entre 0 y 10
            boolean acertado = false;
            int random = (int )(Math.random() * 10 + 1);
            System.out.println(random);
            // Se acepata una conexi�n con un cliente. Esta llamada se queda
            // bloqueada hasta que se arranque el cliente.
            while(true){
	            System.out.println ("Esperando cliente");
	            Socket cliente = socket.accept();
	            count++;
	            System.out.println ("Conectado con cliente de " + cliente.getInetAddress() + "Num " + count);
	            
	            DatoSocket message = (DatoSocket) DatoSocket.fromInputStream(cliente.getInputStream());
	    
	            System.out.println("Message Received: " + message.getNombre() + " y tienes " + message.getEdad() + "años. Eres el " + count);
	            if(message.getNumero() == random){
	                acertado = true;
	                random = (int )(Math.random() * 10 + 1);
	                count = 0;
	                System.out.println("Acertado en " + count  + " intentos. Nuevo numero" + random);
	            }else{
	                acertado = false;
	            }
	            
	            // Se hace que el cierre del socket sea "gracioso". Esta llamada s�lo
	            // es necesaria si cerramos el socket inmediatamente despu�s de
	            // enviar los datos (como en este caso).
	            // setSoLinger() a true hace que el cierre del socket espere a que
	            // el cliente lea los datos, hasta un m�ximo de 10 segundos de espera.
	            // Si no ponemos esto, el socket se cierra inmediatamente y si el 
	            // cliente no ha tenido tiempo de leerlos, los datos se pierden.
	            cliente.setSoLinger (true, 10);
	            
	            // Se prepara un flujo de salida para objetos y un objeto para enviar*/
	            DatoSocket dato = message;
	            message.setAcertado(acertado);
	            ObjectOutputStream bufferObjetos = 
	                new ObjectOutputStream (cliente.getOutputStream());
	            
	            // Se env�a el objeto 
	            bufferObjetos.writeObject(dato);
	            //System.out.println ("Enviado " + dato.toString());
	            
	            // Se cierra el socket con el cliente.
	            // La llamada anterior a setSoLinger() har�
	            // que estos cierres esperen a que el cliente retire los datos.
	            cliente.close();
	          System.out.println ("Enviado " + dato.toString());
            }
            // Se cierra el socket encargado de aceptar clientes. Ya no
            // queremos m�s.
            //socket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
