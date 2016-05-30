/**
 * Javier Abell�n, 16 Mayo 2006
 * Ejemplo de uso de socket udp en java
 */
package chuidiang.ejemplos;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Properties;

/**
 * Abre un socket udp y env�a por �l 10 mensajes consistentes en 10 clases
 * DatoUdp.
 * @author Chuidiang
 *
 */
public class ClienteUdp implements OlympicMedal
{

    /**
     * Programa de prueba. Instancia esta clase
     * @param args
     */
    public static void main(String[] args)
    {
        new ClienteUdp();
    }

    /**
     * Crea una instancia de esta clase y env�a los 10 mensajes
     *
     */
    public ClienteUdp()
    {
    	Properties propiedades = new Properties();
        InputStream entrada = null;
        try
        {
        	
            entrada = new FileInputStream("red.properties");
            // cargamos el archivo de propiedades
            propiedades.load(entrada);

            // obtenemos las propiedades y las imprimimos
            System.out.println(propiedades.getProperty("HOST_CLIENTE"));

            // La IP es la local, el puerto es en el que este cliente est�
            // escuchando.
           // DatagramSocket socket = new DatagramSocket(
                 //   PUERTO_DEL_CLIENTE, InetAddress
                   //         .getByName("127.0.0.1"));
            DatagramSocket socket = new DatagramSocket(null);
            InetSocketAddress address = new InetSocketAddress(propiedades.getProperty("HOST_CLIENTE"), Integer.parseInt(propiedades.getProperty("PUERTO_DEL_CLIENTE")));
            socket.bind(address);

            // Se instancia un DatoUdp y se convierte a bytes[]
            DatoUdp elDato = new DatoUdp("hola");
            byte[] elDatoEnBytes = elDato.toByteArray();

            // Se meten los bytes en el DatagramPacket, que es lo que se
            // va a enviar por el socket.
            // El destinatario es el servidor.
            // El puerto es por el que est� escuchando el servidor.
            DatagramPacket dato = new DatagramPacket(elDatoEnBytes,
                    elDatoEnBytes.length, InetAddress
                            .getByName(propiedades.getProperty("HOST_SERVIDOR")),
                            Integer.parseInt(propiedades.getProperty("PUERTO_DEL_SERVIDOR")));
            
            // Se env�a el DatagramPacket 10 veces, esperando 1 segundo entre
            // env�o y env�o.
            for (int i = 0; i < 10; i++)
            {
                System.out.println("Envio dato " + i);
                socket.send(dato);
                Thread.sleep(1000);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
