import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;


public class FtpUtil 
{
	FtpClient ftpClient;
	public void connectServer(String server,String user,String password, String path) 
	{
	    try
	    {
		ftpClient=new FtpClient();
		ftpClient.openServer(server);
		ftpClient.login(user,password);
		if (path.length()!=0) ftpClient.cd(path);
		ftpClient.binary();
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    	System.exit(0);
	    }
	}
	
	private long upload(String filename,String newname) 
	{
		long result=0;
		TelnetOutputStream os=null;
		FileInputStream is=null;
		try
		{
			java.io.File file_in=new java.io.File(filename);
			if (!file_in.exists()) return -1;   //File not exists.
			os=ftpClient.put(newname);
			result=file_in.length();           //Upload ok, return size.
			is=new FileInputStream(file_in);
			byte[] bytes=new byte[1024];
			int c;
			while ((c=is.read(bytes))!=-1) 
			{
				os.write(bytes,0,c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		finally{
			if (is!=null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
		    if(os!=null)
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
		}

	return result;
	}
	
	public long upload(String filename) throws Exception
	{
		return upload(filename,filename);
	}
	

	public long download(String filename,String newfilename) throws Exception
	{
		long result=0;
		TelnetInputStream is=null;
		FileOutputStream os=null;
		try
		{
			is=ftpClient.get(filename);
			java.io.File outfile=new java.io.File(newfilename);
			os=new FileOutputStream(outfile);
			byte[] bytes=new byte[1024];
			int c;
			while ((c=is.read(bytes))!=-1)
			{
				os.write(bytes,0,c);
				result+=c;
			}
		} catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally {
			if (is!=null)
				is.close();
		    if (os!=null)
		    	os.close();
		}
		return result;
	}
	
	public void closeServer() throws IOException
	{
		try
		{
			if (ftpClient!=null)
				ftpClient.closeServer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
 
}
