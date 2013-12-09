package p4;

import java.io.File;
import java.io.FileNotFoundException;



public class PortablePixelMapper {
    private static String inFile,outFile;
    private static int width,height;
	
    @SuppressWarnings("unused")
	private static boolean checkArguments(String[] args)
    {
    	if (args.length!=4) return false;
    	try
    	{
    		int t;
    		t=Integer.parseInt(args[1]);
    		t=Integer.parseInt(args[2]);
    	}
    	catch (Exception e)
    	{
    		return false;
    	}
    	
    	if (!new File(args[0]).exists())
    		return false;
    	
    	if (args[3].toLowerCase().indexOf(".ppm")==-1)
    		return false;
    	
    	return true;
    	
    }
    
    private static void showUsageMessage()
    {
    	System.out.println("Usage:");
    	System.out.println("  java PortablePixelMapper <inFile> <width> <height> <outFile>");
    	System.out.println();
    	System.out.println();
    	System.out.println("  <inFile> is the name of an input file with svg shape information(MUST EXIST)");
    	System.out.println("  <width> is the result image width in pixels");
    	System.out.println("  <height> is the result image height in pixels");
    	System.out.println("  <outFile> is the name of the ppm file to create(MUST be *.ppm)");
        System.out.println("Example:");
    	System.out.println("  java PortablePixelMapper sample01.txt 100 100 image01.ppm");
    	System.out.println();
    }
    
    public static void main(String[] args)
   {
	   
	   if (checkArguments(args))
	   {
	     inFile=args[0];
	     outFile=args[3];
	     width=Integer.parseInt(args[1]);
	     height=Integer.parseInt(args[2]);
	   
	     Grid myGrid=new Grid(width,height);
	     SVGReader mySVG=new SVGReader(inFile);
	     PPMWriter myPPM=new PPMWriter(outFile);
	  
	     try
	     {
	       mySVG.loadFromFile();
	     } catch (Exception e)
	     {
	    	 System.out.println("Error: Fail to load data from file.");
	     }
	     
	     System.out.println("Load data from file: "+inFile+" Completed...");
	    
	     myGrid.draw();
	     
	     System.out.println("Generate grid Completed...");
	   
	     try {
		   myPPM.saveToFile(myGrid);
		   System.out.println("Save gird to file: "+outFile+" Completed...");
	      } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
	    	e.printStackTrace();
	      }
	   }
	   else
		   showUsageMessage();
	   System.out.println("Prgoram Exited...");
	}
}
