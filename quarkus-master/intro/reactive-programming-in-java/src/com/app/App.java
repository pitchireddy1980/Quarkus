package com.app;

import java.util.Scanner;

/*
 * 
 *  s/w i.e thread, 
 *  
 *  	-> computation
 *      -> IO
 *      
 *      
 *      how many threads can i create in java appln?
 *      
 *      
 *      	
 *                             # of cpus       e.g 8
 *      # of threads  <=      -------------------------
 *                            blocking-factor   1   0.5
 *                            
 *                            
 *                            
 *      blocking-factor = 0 - 1
 *      
 *                            
 *                            
 *                            
 * 
 */

public class App {

	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName());

		doIO(); // req-1
		doComputation(); // req-2

	}

	private static void doIO() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " initiated IO");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name?");
		String myName = scanner.nextLine();
		System.out.println("hello " + myName);
		scanner.close();
		System.out.println(name + " completed IO");
	}

	private static void doComputation() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " initiated computation");
		while (true) {
		}
	}

}
