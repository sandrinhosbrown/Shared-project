/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.concurrent.*;

public class Car implements Runnable{
	private final Semaphore s;
	private final String brand;
	
	Car(Semaphore s, String brand){
		this.brand=brand;
		this.s=s;
	}
	
	@Override
	public void run() {
		try{
			s.acquire();
                        int a = 0;
                        if(brand.equals("Nissan"))
                           a = 3/0;
			System.out.println("The semaphore for the car "+brand+" is green!");
			Thread.sleep(1000);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//
                      s.release();
		}
	}

}
