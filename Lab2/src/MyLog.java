import org.apache.log4j.*;
public class MyLog {
  public Logger loger;
  private static MyLog log;
  
  private MyLog()
  {
	  String filePath=this.getClass().getResource("/").getPath();
	  filePath=filePath.substring(1).replace("bin", "src");
	  loger=Logger.getLogger(this.getClass());
	  PropertyConfigurator.configure(filePath+"log4j.properties");
  }
  
  static MyLog getLoger()
  {
	  if (log!=null)
		  return log;
	  else
		  return new MyLog();
  }
}
