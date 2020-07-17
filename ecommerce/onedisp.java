package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class onedisp extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "******";
	   
ResultSet rs,rr;
String ss,email;
public onedisp(String ss,String mail)
{
	setBackground(new Color(  255, 255, 204));

	this.ss=ss;
	this.email=mail;
	show();
}

    public void show() {
    	try {
			count();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			initialiseObject();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(232, 5, 452, 427);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        mybutton btnBack = new mybutton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			try {
				admin ad =new admin(email);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnBack.setBounds(355, 625, 89, 23);
		panel.add(btnBack);
        
        panel.add(scrollPane);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setSize(900,200);
        frame.setVisible(true);
    }
    private Object[][] data;
    public static int total=0;
    public void count() throws SQLException
    {
    	 try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      
	      Statement st = conn.createStatement(); 
	      rr=st.executeQuery(ss.toString());
	      
	      Statement stmt = conn.createStatement(); 
	      ResultSet rs=stmt.executeQuery(ss.toString());
	     
    	int lines=0;
        while (rs.next())
        	lines++;
        data=new Object[lines][3];
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	String prodid=rr.getString("OrderID");
        	int prodname=rr.getInt("Amount");
        	String prodpr=rr.getString("placed_date");
        	
        	data[i][0]=prodid;
            data[i][1]=prodname;
            data[i][2]=prodpr;
            
            i++;
        }     
    }
    String[] columnNames = {"Order ID", "Amount", "OrderDate"};
}
