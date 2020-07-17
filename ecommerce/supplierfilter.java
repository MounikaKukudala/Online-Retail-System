package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class supplierfilter extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "******";
ResultSet rs,rr;
String ss;
String email;
public supplierfilter(String xx,String mail)
{
	this.setBackground(new Color(  255, 255, 204));

	this.ss=xx;
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
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					admin c=new admin(email);
				} catch ( SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnBack.setBounds(10, 286, 90, 24);
        panel.add(btnBack);
        
        panel.add(scrollPane);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
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
	      String sx="select * from suppliers,supplies,products where suppliers.SupplierID=supplies.SupplierID and supplies.ProductID=products.ProductID and products.Category='"+ss+"'";
	      Statement st = conn.createStatement(); 
	      rr=st.executeQuery(sx);
	      
	      Statement stmt = conn.createStatement(); 
	      ResultSet rs=stmt.executeQuery(sx);
	     
    	int lines=0;
        while (rs.next())
        	lines++;
        data=new Object[lines][4];
        System.out.println(lines);
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	String supdid=rr.getString("SupplierID");
        	String supname=rr.getString("SupplierName");
        	Date supadd=rr.getDate("joined");
        	
        	data[i][0]=supdid;
            data[i][1]=supname;
            data[i][2]=supadd;
            
            i++;
        }      
    }
    String[] columnNames = {"Supplier ID", "Supplier Name", "Joined"};
}
