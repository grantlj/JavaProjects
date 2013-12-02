
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Main extends Applet {
	private static final long serialVersionUID = 1L;
    String sign="=";
    boolean isNum1=true,isNum2=false;
    JPanel panel; double result=0;
    ActionListener command;
    JTextField jtext=new JTextField("0");
  
    String tmpA,tmpB;
    String tmpMethod;
  
    Tresult Saver;
    
    MyLog log;
        
  public void addButton(String ac,ActionListener recver){
    JButton button=new JButton(ac);
    button.addActionListener(recver);
    button.setFont(new Font(" ", ALLBITS, 20));
    button.setBackground(Color.green);
    button.setSize(30,50);
    panel.add(button);
  } 
  
  public void calculate(double x){
    
	
	tmpA=String.valueOf(result);
    tmpB=String.valueOf(x);
    tmpMethod=sign;
    
    log.loger.debug("User calculate: Element A:"+tmpA+" Element B:"+tmpB+" Method:"+tmpMethod);
   
    if (!tmpMethod.equals("=")) 
    	Saver.add(tmpA, tmpB, tmpMethod);
    
    try {
		Saver.saveToFile("record.dat");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //Saver.show();
    
    
	if(sign.equals("+"))     
		 result +=x;
    else if(sign.equals("-"))  
    		result -=x;
    
    else if(sign.equals("*"))  
    	    result *=x;
    
    else if(sign.equals("/"))  
    	    result /=x;
   
    else if(sign.equals("1/x"))
    {  
    	if(x==0)
          {    
    		jtext.setText("Can't divided by 0!");
    		return;
          }
        else 
        	result=1/x;
        isNum1=true;
     }
   
    else if(sign.equals("平方根"))
    {  if(x>=0)
         { 
    	   result =Math.sqrt(x);
    	   isNum1=true;
    	 }
        else
         { 
        	jtext.setText("Must over 0!");
            return;
         }
     }
    else if(sign.equals("="))
    { 
    	result=x;
    	isNum2=true;
    }
    
    jtext.setText(""+result);
 
 }
  
  public void init () {
    Saver=new Tresult();
    
   log=MyLog.getLoger();
	
    setLayout(new BorderLayout());
    jtext.setBackground(Color.pink);
    jtext.setHorizontalAlignment(SwingConstants.RIGHT);     
    jtext.setSize(500,500);
    jtext.setLocation(100,50);
    add(jtext,BorderLayout.NORTH);
    
    log.loger.debug("Initial program OK.");
    
    
    command=new ActionListener()
    {
      
    	public void actionPerformed(ActionEvent event)
    	{
          String labe=event.getActionCommand();
          log.loger.debug(labe+" Pressed.");
          
          if(labe.equals("清除"))
            {
        	  jtext.setText("0");
              result=0;
              isNum1=true;
              isNum2=false;
              sign="=";
            }
        
          else if(labe.charAt(0)>='0'&&labe.charAt(0)<='9'||labe.equals("."))
           {
        	 if(isNum1)
             jtext.setText(labe);
             else 
            	 jtext.setText(jtext.getText()+labe);
             isNum1=false;
           }
      
          else if(labe.equals("Backspace"))
        {
           String s=jtext.getText();
           if(s.length()>=2)
            {
        	   String sc=s.substring(0,s.length()-1);
               jtext.setText(sc);
            }
            else 
            {
              jtext.setText("0");
              isNum1=true;
            }
       }
      
       else if(labe.equals("1/x")||labe.equals("平方"))
       {
        double x=Double.parseDouble(jtext.getText());
        sign=labe;
        calculate(x);        
        isNum1=true;
       }
       else {
              if(isNum1)
              {
                if(!isNum2)
                {
                  if(labe.equals("-"))
                    { 
                	  jtext.setText(labe);
                      isNum1=false;
                    }
                  else 
                	  sign=labe;
                 }
                else sign=labe;
              }
            else
            {  
            	double  x=Double.parseDouble(jtext.getText());
                calculate(x);
                sign=labe;
                isNum1=true;
            } 
          }
       }
 };
       panel = new JPanel();
	   panel.setLayout(new GridLayout(5,4));
       addButton("清除",command);
       addButton("平方根",command);
       addButton("Backspace",command);
       addButton("1/x",command); 
       String buttons = "789/456*123-0.=+";
       for (int i=0;i<buttons.length();i++)
          addButton(  buttons.substring(i,i+1),command);
       add(panel, "Center");
       log.loger.debug("Set keys and layout OK.");
    }
}
