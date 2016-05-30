/**
 * Javier Abell�n, 16 Mayo 2006
 */
package chuidiang.ejemplos;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Servidor de udp que se pone a la escucha de DatagramPacket que contengan
 * dentro DatoUdp y los escribe en pantalla.
 * 
 * @author Chuidiang
 */
public class ServidorUdp
{

    /**
     * Prueba del prorama ServidorUdp
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        new ServidorUdp();
    }

    /**
     * Crea una instancia de esta clase, poniendose a la escucha del puerto
     * definido en Constantes y escribe en pantalla todos los mensajes que le
     * lleguen.
     */
    public ServidorUdp()
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


            // La IP es la local, el puerto es en el que el servidor est� 
            // escuchando.
            DatagramSocket socket = new DatagramSocket(
            		Integer.parseInt(propiedades.getProperty("PUERTO_DEL_SERVIDOR")), InetAddress
                            .getByName("localhost"));

            // Un DatagramPacket para recibir los mensajes.
            DatagramPacket dato = new DatagramPacket(new byte[100], 100);

            // Bucle infinito.
            while (true)
            {
                // Se recibe un dato y se escribe en pantalla.
                socket.receive(dato);
                System.out.print("Recibido dato de "
                        + dato.getAddress().getHostName() + " : ");
                
                // Conversion de los bytes a DatoUdp
                DatoUdp datoRecibido = DatoUdp.fromByteArray(dato.getData());
                System.out.println(datoRecibido.cadenaTexto);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
