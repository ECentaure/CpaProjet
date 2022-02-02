package algorithms;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {

	
	  public Circle algoNaifCalculCercle(ArrayList<Point> points) {
	    if (points.isEmpty()) {
	      return null;
	    }

	    Point center=points.get(0);
	    int radius=100;
	    
	    

	    return new Circle(center,radius);
	  }
	  
	  public Point choose_random_point(ArrayList<Point> P) {
		  Random r = new Random();
		  int low = 0;
		  int high = P.size() - 1;
		  int result = r.nextInt(high-low) + low;
		  return P.get(result);
	  }

	  public boolean withinCircle(Circle c, Point p) {
		  return p.x <= c.getCenter().x + c.getRadius() && p.x >=  c.getCenter().x - c.getRadius() && p.x <= c.getCenter().y + c.getRadius() && p.x >=  c.getCenter().y - c.getRadius();
	  }
	  
	  public Circle b_calculCercleWelzl(ArrayList<Point> P, ArrayList<Point> R) {
		  Circle D;
		  if (P.isEmpty() || R.size() == 3) {
			  D = new Circle( R.get(0) , R.size()) ; // D est b_md le disque minimum contenant tout les points
		  }else {
			  Point p = choose_random_point(P);
			  D = b_calculCercleWelzl(P , R);
			  if ( withinCircle(D, p)) {
				  R.add(p);
				  D = b_calculCercleWelzl(P , R );
			  }
		  }
		  return D;
	  }
	  
	  public Circle calculCercleWelzl(ArrayList<Point> P) {
		  Circle D; 
		  if (P.isEmpty()) {
			  D = new Circle(new Point(),0);
		  } else {
			  Point p = choose_random_point(P);
			   P.remove(p);
			  D = calculCercleWelzl(P);
			  if (!withinCircle(D,p)) {
				  ArrayList<Point> single_point = new ArrayList<Point>();
				  single_point.add(p);
				  D = b_calculCercleWelzl(P,single_point);
			  }
		  }
		  return D;
	  }
	  
	  public ArrayList<Point> fileReading() {
		  String address = "Varoumas_benchmark/samples/";
		  String[] test_file_names = {"test-1.points","test-10.points","test-100.points","test-1000.points","test-1001.points"};
		  ArrayList<File> files = new ArrayList<File>();
		  ArrayList<Point> points = new ArrayList<Point>();
		 try {
		  for ( String file_name : test_file_names ) {
			  files.add(new File(address+file_name));
		      Scanner myReader = new Scanner(files.get(0));
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String[] coordinates = data.split(" ");
		        Point p = new Point(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1]));
		        points.add(p);
		        System.out.println(data);
		        }
		  }
		 } catch (Exception e) {
			 System.out.print(e.getMessage());
		 }
		 
		  return points;
		  
		  

	  }

}
