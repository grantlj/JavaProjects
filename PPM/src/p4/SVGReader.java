package p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SVGReader {
  private String inFile;
  
  public void loadFromFile()
  {
	  File file=new File(inFile);
	  try {
		Scanner sc=new Scanner(file);
		
		while (sc.hasNextLine())
		{
			String type;
			int shapeType;
			int r,g,b;
			double x_org,y_org;
			double arg_1=-32767,arg_2=-32767;
			type=sc.next();
			r=sc.nextInt();g=sc.nextInt();b=sc.nextInt();
			x_org=sc.nextDouble();y_org=sc.nextDouble();
			
			if (type.equals("rectangle") || type.equals("ring"))
			{
				arg_1=sc.nextDouble();arg_2=sc.nextDouble();
				if (type.equals("rectangle")) shapeType=1;
				else shapeType=4;
			}
			else
			{
				arg_1=sc.nextDouble();
				if (type.equals("square"))
					shapeType=2;
				else
					shapeType=3;
			}
			
			ShapeRec.handleNewShape(shapeType, r, g, b, x_org,y_org, arg_1, arg_2);
		}
	
		sc.close();
		
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  SVGReader(String s)
  {
	  this.inFile=s;
  }
}
