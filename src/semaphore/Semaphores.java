/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.concurrent.Semaphore;

public class Semaphores {
	public static void main(String args[]){
		Semaphore sem=new Semaphore(3, true);
		String brands[]={"Toyota","Nissan","Chevrolet","Alfa Romeo","Jaguar","Mercedez Benz","Renault","Ford"};
		Car carros[]=new Car[brands.length];
		for(int i=0;i<brands.length;i++){
			carros[i]=new Car(sem,brands[i]);
			
			new Thread(carros[i]).start();
		}
	}
}