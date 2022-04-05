/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheetproject;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
	public class employee_updateform extends JFrame implements ActionListener,ItemListener{
		public static employee_updateform myselfupdateform=null;
		//public static void main(String []arr)
		//{
			//new updateform();
		//	}	
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String name="";
		String dob="",dd="",mm="",yy="";
		int id=0;
		String sex="";
		int d=1,m=1,y=1950;
		double salary=0;
		JLabel lb=new JLabel("UPDATE FORM");
		
		JTextField t1=new JTextField();
		JTextField t2=new JTextField();
		JTextField t3=new JTextField();
		JTextField t4=new JTextField();
		JTextField t5=new JTextField();
	
		JComboBox comgender=new JComboBox();
		JComboBox comId=new JComboBox();
		JComboBox comyear=new JComboBox();
		JComboBox commonth=new JComboBox();
		JComboBox com28=new JComboBox();
		JComboBox com29=new JComboBox();
		JComboBox com30=new JComboBox();
		JComboBox com31=new JComboBox();
        JLabel lb1=new JLabel("Select Id :");
		
		JButton showdetailsbt=new JButton("SHOW DETAILS");
		JButton updatebt=new JButton("UPDATE");
		JButton backbt=new JButton("BACK");
		void openConnection()
		{
			try
			{
		Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");}
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
		public employee_updateform()
		{
			setSize(600,600);
			setLocation(100,100);
			setLayout(null);
			
			 lb.setBounds(290,20,100,20);
		     lb1.setBounds(20,70,150,20);
		    
		    t1.setBounds(20,110,150,20);
		    t2.setBounds(20,150,150,20);
		  //  t3.setBounds(20,190,150,20);
		    comyear.setBounds(20,190,60,20);
			commonth.setBounds(90,190,40,20);
			com28.setBounds(140,190,40,20);
			com29.setBounds(140,190,40,20);
			com30.setBounds(140,190,40,20);
			com31.setBounds(140,190,40,20);
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
			
			add(comyear);
			add(commonth);
			add(com28);
			add(com29);
			add(com30);
			add(com31);
		    t4.setBounds(20,230,150,20);
		    comgender.setBounds(20,270,150,20);
		    comgender.addItemListener(this);
		    comgender.addItem("MALE");
			comgender.addItem("FEMALE");
			comgender.addItem("OTHER");
			comgender.setVisible(false);
		    comId.setBounds(200,70,150,20);
			showdetailsbt.setBounds(200,110,150,20);
			updatebt.setBounds(200,150,150,20);
			backbt.setBounds(200,190,150,20);
			
			add(lb);
			add(lb1);
			add(comId);
					
		    add(t1);
		    add(t2);
			add(t4);
			add(comgender);
		
			add(showdetailsbt);
			add(backbt);
			add(updatebt);
			
			  setVisible(true);
				showdetailsbt.addActionListener(this);
				updatebt.addActionListener(this);
				backbt.addActionListener(this);
				 comId.addItemListener(this);
				 comyear.addItemListener(this);
				 commonth.addItemListener(this);
				 com28.addItemListener(this);
				 com29.addItemListener(this);
				 com30.addItemListener(this);
				 com31.addItemListener(this);
				 comgender.addItemListener(this);
				  try
			    	{
			    		openConnection();
			    		st=con.createStatement();
			    		rs=st.executeQuery("select * from emp");
			    		if(rs.next())
			    		{
			    			do
			    			{
			    				
			    				id=rs.getInt("id");
			    				comId.addItem(""+id);
			    			}while(rs.next());
			    		}
			    		closeConnection();
			    	}
			    	catch(Exception ee){
		     }
			 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		 public void actionPerformed(ActionEvent e){
			 
			 if(e.getActionCommand().equals("BACK"))
	    		{
	    			employee_updateform.myselfupdateform.dispose();
                                employee_updateform.myselfupdateform=null;
	    			employee_entryform.myselfentryform.setVisible(true);	
	    		}
			 if(e.getActionCommand().equals("UPDATE"))
	      	 {
				 name=t1.getText().toUpperCase().trim();
				 salary=Double.parseDouble(t4.getText().trim());
				 dob=y+"-"+m+"-"+d;
				 sex=comgender.getSelectedItem().toString().toUpperCase().trim();
				 try
				 {
					 openConnection();
						st=con.createStatement();
						String qry="update emp set name='"+name+"', dob='"+dob+"', salary="+salary+", gender='"+sex+"' where id="+id+"";
						st.executeUpdate(qry);
						//rs=st.executeQuery("Select * from emp order by id desc limit 1");
						//if (rs.next())
						//{set 
					//id=rs.getInt("id");
					//lb6.setText(""+id);
					//	}
						//else
						//	lb6.setText("");
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
comgender.setSelectedIndex(0);
closeConnection();
}
catch(Exception ee)
{
System.out.println("ee"+ee.getMessage()); 
}
//	txt=(String)com1.getSelectedItem();
//	lb4.setText(txt);
				
}
if(e.getActionCommand().equals("SHOW DETAILS"))
{
dd="";mm="";yy="";
try
{	
id=Integer.parseInt(comId.getSelectedItem().toString());
openConnection();
st=con.createStatement();
rs=st.executeQuery("select * from emp where id="+id+"");
if(rs.next())
{	
name=rs.getString("name");
id=rs.getInt("id");
dob=rs.getDate("dob").toString().trim();
yy=""+dob.charAt(0)+dob.charAt(1)+dob.charAt(2)+dob.charAt(3);
mm=""+dob.charAt(5)+dob.charAt(6);
dd=""+dob.charAt(8)+dob.charAt(9);
y=Integer.parseInt(yy);
m=Integer.parseInt(mm);
d=Integer.parseInt(dd);	 
mm=""+m;
dd=""+d;
//	 System.out.println(yy+mm+dd+y+m+d);
comyear.setSelectedItem(yy);
commonth.setSelectedItem(mm);
if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
{
com28.setVisible(false);
com29.setVisible(false);
com30.setVisible(false);
com31.setVisible(true);
com31.setSelectedItem(dd);
}
else
if(m==4||m==6||m==9||m==11)
{
com28.setVisible(false);
com29.setVisible(false);
com31.setVisible(false);
com30.setVisible(true);
com30.setSelectedItem(dd);
 }
 else
if(m==2&&y%100==0&&y%400==0||y%100!=0&&y%4==0)
{
com28.setVisible(false);
com30.setVisible(false);
com31.setVisible(false);
com29.setVisible(true);
com29.setSelectedItem(dd);
}
else
if(m==2)
{
	com29.setVisible(false);
	com30.setVisible(false);
	com31.setVisible(false);
	com28.setVisible(true);
	com28.setSelectedItem(dd);
	}
			for(int i=0;i<10;i++)
			{
								
			}
			sex=rs.getString("gender");
			salary=rs.getDouble("salary");
			t1.setText(""+name);
			t2.setText(""+id);
			t3.setText(""+dob);
			t4.setText(""+salary);
			comgender.setVisible(true);
			comgender.setSelectedItem(sex);
		//	t5.setText(""+sex);
		}
		closeConnection();
		st=null;
	}
catch(Exception ee)
{
} 
	    }
	}
	
public void itemStateChanged(ItemEvent ie){
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
