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
	public class employee_deleteform extends JFrame implements ActionListener,ItemListener{
		public static employee_deleteform myselfdeleteform=null;	

		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String name="";
		String dob="";
		int id=0;
		String sex="";
		int d=1,m=1,y=1950;
		double salary=0;
		JLabel lb=new JLabel("DELETE FORM");
		
		JComboBox comId=new JComboBox();
		JLabel lb1=new JLabel("Select Id :");
		
		JButton showalldatabt=new JButton("BACK");
		JButton showidbt=new JButton("SHOW DETAILS");
		JButton deletebt=new JButton("DELETE");
		JLabel lb2=new JLabel("");
		JLabel lb3=new JLabel("");
		JLabel lb4=new JLabel("");
		JLabel lb5=new JLabel("");
		JLabel lb6=new JLabel("");
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
       public employee_deleteform()
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
	        lb6.setBounds(20,240,150,20);
	        
	        comId.setBounds(200,70,150,20);
	        showalldatabt.setBounds(30,280,150,20);
	    	showidbt.setBounds(200,280,150,20);
	    	deletebt.setBounds(370,280,150,20);
	    	
	    	add(lb);
	    	add(lb1);
	    	
	    	add(lb2);
	    	add(lb3);
	    	add(lb4);
	    	add(lb5);
	    	add(lb6);
	    	
	    	add(comId);
	    	
	    	add(showalldatabt);
	    	add(showidbt);
	    	add(deletebt);
	    	comId.addItemListener(this);
	        showalldatabt.addActionListener(this);
	    	showidbt.addActionListener(this);
	    	deletebt.addActionListener(this);  
	        setVisible(true);
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
	         
	        
	        }
       public void actionPerformed(ActionEvent e)
       {
    	   if(e.getActionCommand().equals("BACK"))
    		{
    			employee_deleteform.myselfdeleteform.dispose();
                        employee_deleteform.myselfdeleteform=null;
    			employee_entryform.myselfentryform.setVisible(true);	
    		}
       
    	   if(e.getActionCommand().equals("DELETE"))
        	 {
    		   try
    		   {
    			openConnection();
				st=con.createStatement();
				st.executeUpdate("delete from emp where id="+id+"");
				closeConnection();
				st=null;
    		   }
    		   catch(Exception ee)
   			{
   		} 
    		  // comId.removeAll();
    		   comId.removeAllItems();
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
    		   lb2.setText("");
				lb3.setText("");
				lb4.setText("");
				lb5.setText("");
				lb6.setText("");	   
        	 }
      	 if(e.getActionCommand().equals("SHOW DETAILS"))
      	 {
      		try
			{	id=Integer.parseInt(comId.getSelectedItem().toString());
				openConnection();
				st=con.createStatement();
				rs=st.executeQuery("select * from emp where id="+id+"");
				if(rs.next())
				{
					
						name=rs.getString("name");
						id=rs.getInt("id");
						dob=rs.getDate("dob").toString();
						sex=rs.getString("gender");
						salary=rs.getDouble("salary");
						lb2.setText("Name Is :"+name);
						lb3.setText("Id      :"+id);
						lb4.setText("DOB     :"+dob);
						lb5.setText("Salary  :"+salary);
						lb6.setText("Sex     :"+sex);
			
				}
				closeConnection();
				st=null;
			}
			catch(Exception ee)
			{
		} 
      	 }
       }
       public void itemStateChanged(ItemEvent ie)
   	{
    		repaint();
   	}
 
	}
