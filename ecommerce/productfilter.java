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

public class productfilter extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "******";
ResultSet rs,rr1,rr2,rr3;
String suppid,email;
public productfilter(String email,String supid)
{
	setBackground(new Color(  255, 255, 204));
    this.email=email;
	this.suppid=supid;
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
        JTable table = new JTable(data1, columnNames1);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(232, 5, 500, 200);
        
        JTable table1 = new JTable(data2, columnNames2);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBounds(232, 210, 500, 200);
        
        JTable table2 = new JTable(data3, columnNames3);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBounds(232, 420, 500, 200);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
		mybutton btnBack = new mybutton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					admin cc=new admin(email);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnBack.setBounds(355, 625, 89, 23);
		panel.add(btnBack);
		
        panel.add(scrollPane);
        panel.add(scrollPane1);
        panel.add(scrollPane2);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setSize(900,200);
        frame.setVisible(true);
    }
    private Object[][] data1;
    private Object[][] data2;
    private Object[][] data3;
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
	      String ss="select products.ProductID,products.ProductName,products.Price,products.Category,tv.Size,tv.Resolution,tv.USB_port"+
	                " from products,tv,supplies "+
	    		    "where products.ProductID=tv.ProductID and tv.ProductID=supplies.ProductID and supplies.SupplierID='"+suppid+"'";
	      rr1=st.executeQuery(ss);
	      
	      Statement stmt = conn.createStatement(); 
	      rs=stmt.executeQuery(ss);
	     
    	int lines=0;
        while (rs.next())
        	lines++;
        data1=new Object[lines][7];
        
        st = conn.createStatement(); 
	    ss="select products.ProductID,products.ProductName,products.Price,products.Category,ac.Capacity,ac.Voltage,ac.MIN_Temp"+
	                " from products,ac,supplies "+
	    		    "where products.ProductID=ac.ProductID and ac.ProductID=supplies.ProductID and supplies.SupplierID='"+suppid+"'";
	     rr2=st.executeQuery(ss);
	      
	     stmt = conn.createStatement(); 
	     rs=stmt.executeQuery(ss);
	     
  	    lines=0;
        while (rs.next())
      	  lines++;
        data2=new Object[lines][7];
        
        st = conn.createStatement(); 
	    ss="select products.ProductID,products.ProductName,products.Price,products.Category,mobile.RAM,mobile.Battery,mobile.Display\n"+
	                "from products,mobile,supplies\n"+
	    		    "where products.ProductID=mobile.ProductID and mobile.ProductID=supplies.ProductID and supplies.SupplierID='"+suppid+"';";
	     rr3=st.executeQuery(ss);
	      
	     stmt = conn.createStatement(); 
	     rs=stmt.executeQuery(ss);
	     
  	    lines=0;
        while (rs.next())
      	  lines++;
        data3=new Object[lines][7];
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr1.next())
        {
        	String prodid=rr1.getString("ProductID");
        	String prodname=rr1.getString("ProductName");
        	int prodprice=rr1.getInt("Price");
        	String prodcat=rr1.getString("Category");
        	int prodsize=rr1.getInt("Size");
        	int prodResol=rr1.getInt("Resolution");
        	String prodport=rr1.getString("USB_port");
        	
        	data1[i][0]=prodid;
            data1[i][1]=prodname;
            data1[i][2]=prodprice;
            data1[i][3]=prodcat;
            data1[i][4]=prodsize;
            data1[i][5]=prodResol;
            data1[i][6]=prodport;
            
            i++;
        }
        i=0;
        while(rr2.next())
        {
        	String prodid=rr2.getString("ProductID");
        	String prodname=rr2.getString("ProductName");
        	int prodprice=rr2.getInt("Price");
        	String prodcat=rr2.getString("Category");
        	Double prodsize=rr2.getDouble("Capacity");
        	int prodResol=rr2.getInt("Voltage");
        	int prodport=rr2.getInt("MIN_Temp");
        	
        	data2[i][0]=prodid;
            data2[i][1]=prodname;
            data2[i][2]=prodprice;
            data2[i][3]=prodcat;
            data2[i][4]=prodsize;
            data2[i][5]=prodResol;
            data2[i][6]=prodport;
            
            i++;
        }
        i=0;
        while(rr3.next())
        {
        	String prodid=rr3.getString("ProductID");
        	String prodname=rr3.getString("ProductName");
        	int prodprice=rr3.getInt("Price");
        	String prodcat=rr3.getString("Category");
        	int prodsize=rr3.getInt("RAM");
        	int prodResol=rr3.getInt("Battery");
        	int prodport=rr3.getInt("Display");
        	
        	data3[i][0]=prodid;
            data3[i][1]=prodname;
            data3[i][2]=prodprice;
            data3[i][3]=prodcat;
            data3[i][4]=prodsize;
            data3[i][5]=prodResol;
            data3[i][6]=prodport;
            
            i++;
        }
        
    }

    String[] columnNames1 = {"Product ID", "Product Name","Product price","Category","Size","Resolution","USB Port"};
    String[] columnNames2 = {"Product ID", "Product Name","Product price","Category","Capacity","Voltage","MIN Temp"};
    String[] columnNames3 = {"Product ID", "Product Name","Product price","Category","RAM","Battery","Display"};
}