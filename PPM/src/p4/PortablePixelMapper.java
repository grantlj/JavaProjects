package p4;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PortablePixelMapper {
    private static String inFile,outFile;
    private static int width,height;
    private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args)
   {
	   inFile=sc.nextLine();
	   outFile=sc.nextLine();
	   width=sc.nextInt();
	   height=sc.nextInt();  
	   
	   Grid myGrid=new Grid(width,height);
	   SVGReader mySVG=new SVGReader(inFile);
	   PPMWriter myPPM=new PPMWriter(outFile);
	  
	   mySVG.loadFromFile();
	   myGrid.draw();
	   
	   try {
		myPPM.saveToFile(myGrid);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	}
}
