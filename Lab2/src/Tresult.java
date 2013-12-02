import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Tresult implements Serializable {
	private static final long serialVersionUID = 1L;
    private String[] a,b;
    private String[] method;
    int reCount;
    public static int reMax=100;
    
    public Tresult()
    {
    	reCount=0;
    	a=new String[reMax];
    	b=new String[reMax];
    	method=new String[reMax];
    	reCount=0;
    }
    
    public void add(String numa, String numb, String meth)
    {
    	if (reCount==100)
    		reCount=0;
    	a[reCount]=numa;
    	b[reCount]=numb;
    	method[reCount]=meth;
    	reCount++;
    }
    
    public void show()
    {
    	for (int i=0;i<reCount;i++)
    		System.out.println("num1="+a[i]+"      num2="+b[i]+"      method="+method[i]);
    	System.out.println("==============");
    }
    
    public void saveToFile(String fileName) throws IOException
    {
    	
    	FileOutputStream f=new FileOutputStream(fileName);
    	ObjectOutputStream oos=new ObjectOutputStream(f);
    	oos.writeObject(this);
    	oos.flush();
    	oos.close();
    	
    }
}
