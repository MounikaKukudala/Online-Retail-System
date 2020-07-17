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

public class viewCart extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "******";
ResultSet rs,rr;
String email;
public viewCart(String email)
{
	setBackground(new Color(  255, 255, 204));

	this.email=email;
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
        
        mybutton btnBuy = new mybutton("Buy");
		btnBuy.setBounds(200, 585, 89, 23);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					buyprod bp=new buyprod(email);
					System.out.println("buying");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuy);
        
		mybutton btnBack = new mybutton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					categories cc=new categories(email);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnBack.setBounds(346, 585, 89, 23);
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
	      
	      Statement st = conn.createStatement(); 
	      String ss="select cartContains.ProductID,products.ProductName,cartContains.Quantity,products.Price\n" + 
	      		"from customers,cart,cartContains,products\n" + 
	      		"where customers.Email='"+email+"' and customers.CustomerID=cart.CustomerID\n" + 
	      		" and cart.cartid=cartContains.cartid and cartContains.ProductID=products.ProductID";
	      rr=st.executeQuery(ss);
	      
	      Statement stmt = conn.createStatement(); 
	      ResultSet rs=stmt.executeQuery(ss);
	     
    	int lines=0;
        while (rs.next())
        	lines++;
        data=new Object[lines][4];
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	String prodid=rr.getString("ProductID");
        	String prodname=rr.getString("ProductName");
        	int prodqt=rr.getInt("Quantity");
        	int prodprice=rr.getInt("Price");
        	
        	data[i][0]=prodid;
            data[i][1]=prodname;
            data[i][2]=prodqt;
            data[i][3]=prodprice;
            
            i++;
        }        
    }

    String[] columnNames = {"Product ID", "Product Name", "Quantity", "Product price"};
}