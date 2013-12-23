import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class TestMain {
  @SuppressWarnings("deprecation")
public static void main(String[] args) throws IOException
  {
	  HSSFWorkbook wb=new HSSFWorkbook();
	  FileOutputStream fileOut=new FileOutputStream("test.xls");
	  
	  HSSFSheet sheet=wb.createSheet("sheet1");
	  for (int i=0;i<30;i++)
	  {
		  HSSFRow row=sheet.createRow((short)i);
	      for (int j=0;j<20;j++)
	      {
	        HSSFCell cell=row.createCell((short)j);
	        cell.setCellValue("ÖÐÎÄ:"+i+"col:"+j);
	      }
	  }
	  
	  
	  wb.write(fileOut);
	  fileOut.close();
  }
}
