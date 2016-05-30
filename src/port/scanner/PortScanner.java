package port.scanner;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScanner {

  public static void main(String[] args) throws UnknownHostException, InterruptedException {

    String host = "127.0.0.1";

    
      //InetAddress theAddress = InetAddress.getByName(host);
      Socket connection = null;
      for (int i = 1; i < 1024; i++) {
    	//Reseteamos a null en cada puerto
    	connection = null;
    	System.out.println(i);

        try {
	        connection = new Socket(host, i);
	        
        } catch (Exception ex) {
            //System.err.println(ex);
        }
        finally{
        	if(connection != null){
        		System.out.println("There is a server on port " + i + " of " + host);
	        	try {
					connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
        }	
    
  }
      
  }
}
