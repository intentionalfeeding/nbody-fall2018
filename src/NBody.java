	

/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
		public static double readRadius(String fname){
		Scanner s = null;
		try {
			s = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.nextLine();
		return s.nextDouble();
		
	}
	public static Body[] readBodies(String fname){
		Scanner s = null;
		try {
			s = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int number = s.nextInt();
		Body[] planet = new Body[number];
		s.nextDouble();
		for (int k=0; k < number; k++){
			planet[k]=new Body(s.nextDouble(),s.nextDouble(),s.nextDouble(),s.nextDouble(),s.nextDouble(),s.next());
		}
		return planet;
	}
	
	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Body[] bodies = readBodies(pfile);
		double radius = readRadius(pfile);
		
		StdDraw.setScale(-readRadius(pfile), readRadius(pfile));
		StdDraw.picture(0, 0, "images/starfield.jpg");
		Scanner s = new Scanner(pfile);
		int number = s.nextInt();
		for (int k=0; k < number; k++){
			bodies[k].draw();
		}
		double time = 0;
		while (time < T){
			double[] xForces = new double[5];
			double[] yForces = new double[5];
		for (int k=0; k < number; k++){
			xForces[k] = bodies[k].calcNetForceExertedByX(bodies);
			yForces[k] = bodies[k].calcNetForceExertedByY(bodies);
		}
		for (int k=0; k < number; k++){
			bodies[k].update(dt, xForces[k], yForces[k]);
		}
		StdDraw.setScale(-readRadius(pfile), readRadius(pfile));
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int k=0; k < number; k++){
			bodies[k].draw();
		}
		StdDraw.show(100);
		time += dt;
		}
	
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
