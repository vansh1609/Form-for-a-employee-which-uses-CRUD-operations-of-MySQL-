/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheetproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
/**
 * @author yogesh
 */
public class employee_searchform extends JFrame implements ActionListener,ItemListener{
    public static employee_searchform myselfsearchform=null;
  /*  
 public static void main(String []arr)
	{
		myselfsearchform=new employee_searchform();
	}
 */
Connection con=null;
Statement st=null;
ResultSet rs=null;

String[] cols={"name","dob","id","sex","salary"};
DefaultTableModel model=new DefaultTableModel(cols,0);
JTable table=new JTable(model);
String name="";
String dob="";
int id=0;
String sex="";
int d=1,m=1,y=1950;
double salary=0;

JLabel lb=new JLabel("SEARCH FORM");

JComboBox comId=new JComboBox();
JLabel lb1=new JLabel("Select Id :");

JButton showalldatabt=new JButton("SHOW ALL DATA");
JButton showidbt=new JButton("SHOW ID");
JButton removeallbt=new JButton("REMOVE ALL");
JButton backbt=new JButton("BACK");
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

public employee_searchform()
{
	setSize(600,600);
        setLocation(100,100);
	setLayout(null);
	lb.setBounds(290,20,100,20);
	lb1.setBounds(20,70,150,20); 
	
	comId.setBounds(200,70,150,20);
	showalldatabt.setBounds(30,360,150,20);
	showidbt.setBounds(200,360,150,20);
	removeallbt.setBounds(370,360,150,20);
	backbt.setBounds(30,400,150,20);
	
	table.setBackground(Color.green);
	table.setGridColor(Color.MAGENTA);
	JScrollPane scrollPane=new JScrollPane(table);
	 scrollPane.setBounds(5,110,300,200);
	 scrollPane.setBackground(Color.CYAN);
	 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	add(scrollPane);
	add(lb);
	add(lb1);
	add(comId);
	
	add(showalldatabt);
	add(showidbt);
	add(removeallbt);
	add(backbt);
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
	catch(Exception ee)
	{
}
	
	showalldatabt.addActionListener(this);
	showidbt.addActionListener(this);
	backbt.addActionListener(this);
	removeallbt.addActionListener(this);
	comId.addItemListener(this);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
public void actionPerformed(ActionEvent e)
{
    
	if(e.getActionCommand().equals("BACK"))
	{
            try
                    {
		employee_searchform.myselfsearchform.dispose();
                employee_searchform.myselfsearchform=null;
		employee_entryform.myselfentryform.setVisible(true);	
                  }
                    catch(Exception ee)
                    {
                       System.out.println(ee.getMessage()); 
                    }
	}
	if(e.getActionCommand().equals("SHOW ALL DATA"))
	{
		int c=table.getRowCount();
		for(int i=0;i<c;i++)
		{
		model.removeRow(0);
	    }
		try
		{
			openConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from emp");
			if(rs.next())
			{
				do
				{
					name=rs.getString("name");
					id=rs.getInt("id");
					dob=rs.getDate("dob").toString();
					sex=rs.getString("gender");
					salary=rs.getDouble("salary");
					Object[] row={name,dob,id,sex,salary};
					model.addRow(row);
				}while(rs.next());
			}
			closeConnection();
		}
		catch(Exception ee)
		{
	}
	}
		if(e.getActionCommand().equals("REMOVE ALL"))
		{
			int a=table.getRowCount();
			for(int i=0;i<a;i++)
			{
				model.removeRow(0);
			}
		}
			if(e.getActionCommand().equals("SHOW ID"))
			{
                       //     System.out.println("bbb");
				int a=table.getRowCount();
				for(int i=0;i<a;i++)
				{
					model.removeRow(0);
				}
		
			try
			{	id=Integer.parseInt(comId.getSelectedItem().toString());
                       //  System.out.println("id  "+id);
				openConnection();
				st=con.createStatement();
				rs=st.executeQuery("select * from emp where id="+id+"");
				if(rs.next())
				{
					
						name=rs.getString("name");
                                             //    System.out.println("name  "+name);
						id=rs.getInt("id");
						dob=rs.getDate("dob").toString();
						sex=rs.getString("gender");
						salary=rs.getDouble("salary");
						Object[] row={name,dob,id,sex,salary};
						model.addRow(row);
			
				}
				closeConnection();
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

