/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheetproject;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
//import sheet10.employee_searchform;
/**
  * @author yogesh
 */
public class employee_entryform extends JFrame implements ActionListener,ItemListener{
   public  static employee_entryform myselfentryform=null;
     int x1=50,y1=500;
      //  static JLabel lb7=new JLabel("Yogesh Kumar");
    public static void main(String []arr)
	{
		myselfentryform=new employee_entryform();
                
		}	
public void paint(Graphics g)
	    {
		 
	      super.paint(g);
	     Graphics2D g2 = (Graphics2D)g;
	        Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN,20);
	        g2.setFont(font);
	        g2.setColor(Color.red);
	        g2.drawString("VANSH KALRA",x1,y1);
	        try
	        {
	        	Thread.sleep(5);
	        	}
	        catch(Exception ex)
	        {}
	        x1+=1;

	      if(x1>this.getWidth ())	 
	        {
            x1=50;
	        } 
	       repaint();
	    }
   
    Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	String name="";
	String dob="";
	int id=0;
	String sex="";
	int d=1,m=1,y=1950;
	double salary=0;
	JLabel lb=new JLabel("Entery Form");
	JLabel lb1=new JLabel("Enter Name: ");
	JLabel lb2=new JLabel("Select Date of Birth:");
	JLabel lb3=new JLabel("Enter Salary");
	JLabel lb4=new JLabel("Select Gender");
	JLabel lb5=new JLabel("Your ID Is:");
	JLabel lb6=new JLabel("");
    

	JTextField t1=new JTextField();//for name
	JTextField t2=new JTextField();//for salary

	JComboBox comyear=new JComboBox();
	JComboBox commonth=new JComboBox();
	JComboBox com28=new JComboBox();
	JComboBox com29=new JComboBox();
	JComboBox com30=new JComboBox();
	JComboBox com31=new JComboBox();
	JComboBox comgender=new JComboBox();
	
JButton addbt=new JButton("ADD");
JButton searchbt=new JButton("SEARCH");
JButton updatebt=new JButton("UPDATE");
JButton deletebt=new JButton("DELETE");
JButton exitbt=new JButton("EXIT");
void openConnection()
{
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
	}
	catch(ClassNotFoundException e)
	{
		System.out.println("Invalid driver"+e.getMessage());
		System.exit(0);
	}
	catch (SQLException e)
	{
        System.out.println("Invalid dsn"+e.getMessage());
		System.exit(0);	
	}
}
void closeConnection()
{
	try{
	
		con.close();
	}
	catch(Exception e)
	{
	
	}
}

 public employee_entryform()
{
	setSize(600,600);
	setLocation(100,100);
	setLayout(null);
	
	lb.setBounds(290,20,100,20);
	lb1.setBounds(20,70,150,20); 
    lb2.setBounds(20,110,150,20);
    lb3.setBounds(20,140,150,20);
    lb4.setBounds(20,180,150,20);
    lb5.setBounds(20,220,150,20);
    lb6.setBounds(200,220,150,20);
   // lb7.setBounds(x1,y1,150,20);
   // lb7.setFont(Font.);
  //  add(lb7);
    t1.setBounds(200,70,150,20);
    t2.setBounds(200,140,150,20);
 
    comyear.setBounds(200,110,90,20);
	commonth.setBounds(300,110,60,20);
	com28.setBounds(370,110,60,20);
	com29.setBounds(370,110,60,20);
	com30.setBounds(370,110,60,20);
	com31.setBounds(370,110,60,20);
	for(int i=1950;i<=2050;i++)
		comyear.addItem(""+i);
	for(int a=1;a<=12;a++)
    commonth.addItem(""+a);
	for(int b=1;b<=28;b++)
		com28.addItem(""+b);
	for(int c=1;c<=29;c++)
		com29.addItem(""+c);
	for(int d=1;d<=30;d++)
		com30.addItem(""+d);
	for(int e=1;e<=31;e++)
		com31.addItem(""+e);
		
	comgender.setBounds(200,180,150,20);
	comgender.addItem("MALE");
	comgender.addItem("FEMALE");
	comgender.addItem("OTHER");
	
	addbt.setBounds(30,280,100,20);
	searchbt.setBounds(150,280,100,20);
	updatebt.setBounds(270,280,100,20);
	deletebt.setBounds(390,280,100,20);
	exitbt.setBounds(30,320,100,20);

	add(lb);
	add(lb1);
	add(lb2);
	add(lb3);
	add(lb4);
	add(lb5);
	add(lb6);
	
    add(t1);
    add(t2);
	
	add(comyear);
	add(commonth);
	add(com28);
	add(com29);
	add(com30);
	add(com31);
	add(comgender);
	
	add(addbt);
	add(searchbt);
	add(exitbt);
	add(updatebt);
	add(deletebt);
   
    t1.requestFocus();
	
	addbt.addActionListener(this);
	searchbt.addActionListener(this);
	updatebt.addActionListener(this);
	deletebt.addActionListener(this);
	exitbt.addActionListener(this);
	
    comyear.addItemListener(this);
    commonth.addItemListener(this);
    com28.addItemListener(this);
    com29.addItemListener(this);
    com30.addItemListener(this);
    com31.addItemListener(this);
    comgender.addItemListener(this);
    
    com28.setVisible(false);
	 com29.setVisible(false);
	 com30.setVisible(false);
	 com31.setVisible(true);
    
     setVisible(true);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
}
public void actionPerformed(ActionEvent e)
 {
     
      if(e.getActionCommand().equals("UPDATE"))
		{
		employee_updateform.myselfupdateform = new employee_updateform();
		employee_updateform.myselfupdateform.setVisible(true);
		employee_entryform.myselfentryform.setVisible(false);
		}
     
	 if(e.getActionCommand().equals("DELETE"))
		{
			employee_deleteform.myselfdeleteform = new employee_deleteform();
			employee_deleteform.myselfdeleteform.setVisible(true);
			employee_entryform.myselfentryform.setVisible(false);
		}
    
	 if(e.getActionCommand().equals("SEARCH"))
		{
                    
		employee_searchform.myselfsearchform = new employee_searchform();
		employee_searchform.myselfsearchform.setVisible(true);
		employee_entryform.myselfentryform.setVisible(false);
                    
		}

      if(e.getActionCommand().equals("EXIT"))
		{
                 System.exit(0);
                }
	 if(e.getActionCommand().equals("ADD"))
		{
		 name=t1.getText().toUpperCase().trim();
		 salary=Double.parseDouble(t2.getText().trim());
		 dob=y+"-"+m+"-"+d;
		 sex=comgender.getSelectedItem().toString().toUpperCase().trim();
		 try
		 {
			 openConnection();
				st=con.createStatement();
				String qry="insert into emp values(0,'"+name+"','"+dob+"',"+salary+",'"+sex+"')";
				st.executeUpdate(qry);
				rs=st.executeQuery("Select * from emp order by id desc limit 1");
				if (rs.next())
				{
			id=rs.getInt("id");
			lb6.setText(""+id);
				}
				else
					lb6.setText("");
						
					
				closeConnection();
		 }
		 catch(Exception ee)
		 {
			 
		 }
	
		}
 }
 public void itemStateChanged(ItemEvent ie)
	{
	 sex=comgender.getSelectedItem().toString();
	 y=Integer.parseInt(comyear.getSelectedItem().toString());
	 m=Integer.parseInt(commonth.getSelectedItem().toString());
	 if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
	 {
		 com28.setVisible(false);
		 com29.setVisible(false);
		 com30.setVisible(false);
		 com31.setVisible(true);
		 d=Integer.parseInt(com31.getSelectedItem().toString()); 
	 }
	 else
		 if(m==4||m==6||m==9||m==11)
		 {
			 com28.setVisible(false);
			 com29.setVisible(false);
			 com31.setVisible(false);
			 com30.setVisible(true);
			 d=Integer.parseInt(com30.getSelectedItem().toString()); 
		 }
		 else
			 if(m==2&&y%100==0&&y%400==0||y%100!=0&&y%4==0)
			 {
				 com28.setVisible(false);
				 com30.setVisible(false);
				 com31.setVisible(false);
				 com29.setVisible(true);
				 d=Integer.parseInt(com29.getSelectedItem().toString()); 
			 }
			 else
				 if(m==2)
				 {
					 com29.setVisible(false);
					 com30.setVisible(false);
					 com31.setVisible(false);
					 com28.setVisible(true);
					 d=Integer.parseInt(com28.getSelectedItem().toString()); 
				 }
		repaint();
	}
}
