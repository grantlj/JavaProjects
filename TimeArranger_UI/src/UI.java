import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class UI extends JFrame
{	
	private static final long serialVersionUID = 1L;
	//Declaration here.--------------------------------------------------------------------------------
	public static JFrame jf=new JFrame("Time Arranger");
	
	public static String entPath="img/Enter.jpg";
	public static Icon entic=new ImageIcon(entPath);
	public static JButton jbEnter=new JButton(entic);
	
	public static String dlPath="img/Download.jpg";
	public static Icon dlic=new ImageIcon(dlPath);
	public static JButton jbDl=new JButton(dlic);
	
	public static String uplPath="img/Upload.jpg";
	public static Icon uplic=new ImageIcon(uplPath);
	public static JButton jbUpl=new JButton(uplic);
	
	public static JLabel jlUser=new JLabel();
	public static JLabel jlPsd=new JLabel();
	public static JLabel jlWrong=new JLabel();
	
	public static JLabel jlPro=new JLabel();
	public static JLabel jlFTP=new JLabel();
	
	public static JLabel jlTip=new JLabel();
	public static JLabel jlSat=new JLabel();
	public static JLabel jlSun=new JLabel();
	
	public static JLabel jlweek=new JLabel();
	public static JLabel jllectrue=new JLabel();
	public static JLabel jllesson=new JLabel();
	
	public static JTextField jtfUser=new JTextField();
	public static JPasswordField jpfPsd=new JPasswordField();
	
	public static JCheckBox jcbSatM=new JCheckBox("08:00-12:00", new ImageIcon("img/CheckBox.jpg"));
	public static JCheckBox jcbSatA=new JCheckBox("14:00-18:00", new ImageIcon("img/CheckBox.jpg"));
	public static JCheckBox jcbSatN=new JCheckBox("19:00-22:00", new ImageIcon("img/CheckBox.jpg"));
	public static JCheckBox jcbSunM=new JCheckBox("08:00-12:00", new ImageIcon("img/CheckBox.jpg"));
	public static JCheckBox jcbSunA=new JCheckBox("14:00-18:00", new ImageIcon("img/CheckBox.jpg"));
	public static JCheckBox jcbSunN=new JCheckBox("19:00-22:00", new ImageIcon("img/CheckBox.jpg"));
	
//Judge if the user and password are all right.----------------------------------------------------------
	@SuppressWarnings("deprecation")
	public void userPassword()
	{
		submiter.username=jtfUser.getText();
		submiter.pwd=jpfPsd.getText();
		if (submiter.login())
		{
			submiter.connectFtp();
			jf.setSize(447, 196);
			jlWrong.setText("");
			jtfUser.setEnabled(false);
			jpfPsd.setEnabled(false);
		}
		else
		{
			jlWrong.setText("!");
		}
	}

//Press Enter Button.----------------------------------------------------------------------------------------
	class JBEnterPress implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			userPassword();
			jcbSatM.setEnabled(false);
			jcbSatA.setEnabled(false);
			jcbSatN.setEnabled(false);
			jcbSunM.setEnabled(false);
			jcbSunA.setEnabled(false);
			jcbSunN.setEnabled(false);
			jbUpl.setEnabled(false);
		}
	}
	public UI.JBEnterPress jbEnterPress=new UI.JBEnterPress();

//Press Enter Key in JTFPsd.----------------------------------------------------------------------------------

	class JPFPsdKeyPress  implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyChar()==KeyEvent.VK_ENTER)
				jbEnter.doClick();
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	public UI.JPFPsdKeyPress jpfPsdKeyPress=new UI.JPFPsdKeyPress();  
	
//Press Upload Button.------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	class JBUplPress implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				submiter.saveToFile(submiter.week+"_"+submiter.username+".dat");
				jlPro.setText("Saved file successful.");
				jlPro.setForeground(Color.BLUE);
				Thread.sleep(500);
				submiter.uploadFileToFtp(submiter.week+"_"+submiter.username+".dat");
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			catch (InterruptedException se)
			{
				se.printStackTrace();
			}
		}		
	}
	public UI.JBUplPress jbUplPress=new UI.JBUplPress();
	
//Press Download Button.---------------------------------------------------------------------------------
	class JBDlPress implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			submiter.getInfoFromFtp();
			submiter.showClassInfo();
		}
	}
	public UI.JBDlPress jbDlPress=new UI.JBDlPress();
	
//Press Saturday Morning CheckBox.----------------------------------------------------------------------
	class JCBSatM implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSatM.isSelected())
			{
				jcbSatM.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[1]=true;
			}
			else
			{
				jcbSatM.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[1]=false;
			}
		}
	}
	public JCBSatM jcbSatMCheck=new JCBSatM();
	
//Press Saturday Afternoon CheckBox.--------------------------------------------------------------------
	class JCBSatA implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSatA.isSelected())
			{
				jcbSatA.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[2]=true;
			}
			else
			{
				jcbSatA.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[2]=false;
			}
		}
	}
	public JCBSatA jcbSatACheck=new JCBSatA();
	
//Press Saturday Night CheckBox.-------------------------------------------------------------------------
	class JCBSatN implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSatN.isSelected())
			{
				jcbSatN.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[3]=true;
			}
			else
			{
				jcbSatN.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[3]=false;
			}
		}
	}
	public JCBSatN jcbSatNCheck=new JCBSatN();
	
//Press Saturday Morning CheckBox.---------------------------------------------------------------------
	class JCBSunM implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSunM.isSelected())
			{
				jcbSunM.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[4]=true;
			}
			else
			{
				jcbSunM.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[4]=false;
			}
		}
	}
	public JCBSunM jcbSunMCheck=new JCBSunM();
	
//Press Saturday Afternoon CheckBox.-------------------------------------------------------------------
	class JCBSunA implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSunA.isSelected())
			{
				jcbSunA.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[5]=true;
			}
			else
			{
				jcbSunA.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[5]=false;
			}
		}
	}
	public JCBSunA jcbSunACheck=new JCBSunA();
	
//Press Saturday Night CheckBox.-------------------------------------------------------------------------
	class JCBSunN implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (jcbSunN.isSelected())
			{
				jcbSunN.setIcon(new ImageIcon("img/CheckBoxChecked.jpg"));
				submiter.time[6]=true;
			}
			else
			{
				jcbSunN.setIcon(new ImageIcon("img/CheckBox.jpg"));
				submiter.time[6]=false;
			}
		}
	}
	public JCBSunN jcbSunNCheck=new JCBSunN();
	
//This is layouts info.----------------------------------------------------------------------------------------
	public UI()
	{	
		submiter.initProgram();
		submiter.initFtp();
		
		jf.setSize(447, 74);		//JFrame.
		jf.setLocation(350, 200);
		jf.setLayout(null);
		
		jbEnter.setBounds(363, 10, 65, 25);		//JButton Enter.
		jf.add(jbEnter);
		jbDl.setBounds(363, 90, 65, 25);		//JButton Download.
		jf.add(jbDl);
		jbUpl.setBounds(363, 128, 65, 25);		//JButton Upload.
		jf.add(jbUpl);

		jlUser.setBounds(10, 2, 40, 40);
		jlUser.setText("User:");
		jf.add(jlUser);
		jlPsd.setBounds(170, 2, 80, 40);
		jlPsd.setText("Password: ");
		jf.add(jlPsd);
		jlWrong.setBounds(433, 2, 40, 40);		//JLabel Wrong.
		jlWrong.setForeground(Color.RED);
		jf.add(jlWrong);
		
		jlPro.setBounds(10, 32, 200, 40);
		jf.add(jlPro);	
		jlFTP.setBounds(170, 32, 250, 40);
		jf.add(jlFTP);
		
		jlTip.setBounds(11, 58, 450, 40);
		jlTip.setText("Check the box if you have free time, then click Upload.");
		jlTip.setForeground(Color.GRAY);
		jf.add(jlTip);
		jlSat.setBounds(10, 82, 70, 40);
		jlSat.setText("Saturday: ");
		jf.add(jlSat);
		jlSun.setBounds(10, 120, 70, 40);
		jlSun.setText("Sunday: ");
		jf.add(jlSun);
		
		jlweek.setBounds(10, 165, 200, 40);
		jlweek.setText("Week: "+submiter.week);
		jf.add(jlweek);
		jllectrue.setBounds(10, 205, 200, 40);
		jllectrue.setText("Lectrue: "+submiter.lecture);
		jf.add(jllectrue);
		jllesson.setBounds(10, 245, 200, 40);
		jllesson.setText("Lesson: "+submiter.lesson);
		jf.add(jllesson);
		
		jtfUser.setBounds(50, 10, 110, 25);		//JTextField User.
		jf.add(jtfUser);
		jpfPsd.setBounds(240, 10, 110, 25);		//JPasswordField Password.
		jf.add(jpfPsd);
		jcbSatM.setBounds(70, 90, 95, 25);		//JLabel Sunday.
		jf.add(jcbSatM);
		jcbSatA.setBounds(165, 90, 95, 25);		//JTextField Sunday.
		jf.add(jcbSatA);
		jcbSatN.setBounds(265, 90, 95, 25);
		jf.add(jcbSatN);
		jcbSunM.setBounds(70, 128, 95, 25);
		jf.add(jcbSunM);
		jcbSunA.setBounds(165, 128, 95, 25);
		jf.add(jcbSunA);
		jcbSunN.setBounds(265, 128, 95, 25);
		jf.add(jcbSunN);

//Here are actions.------------------------------------------------------------------------------------
		jbEnter.addActionListener(jbEnterPress);
		jbUpl.addActionListener(jbUplPress);
		jbDl.addActionListener(jbDlPress);
		jpfPsd.addKeyListener(jpfPsdKeyPress);
		jcbSatM.addItemListener( jcbSatMCheck);
		jcbSatA.addItemListener( jcbSatACheck);
		jcbSatN.addItemListener( jcbSatNCheck);
		jcbSunM.addItemListener( jcbSunMCheck);
		jcbSunA.addItemListener( jcbSunACheck);
		jcbSunN.addItemListener( jcbSunNCheck);
		
		//submiter.connectFtp();
		
		
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
	}
	
	
	public static void main(String args[])
	{
		new UI();
	}
}