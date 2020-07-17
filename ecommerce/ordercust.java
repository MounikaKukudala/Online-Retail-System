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

public class ordercust extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	String email,query,query1,sx;
//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
    ResultSet rs,rr;
    String fp;
    public ordercust(String email,String ss)
    {
	     this.setBackground(new Color(  255, 255, 204));
     	 this.email=email;
     	 this.query=ss;
     	 this.fp="";
     	 this.query1="select OrderID,OrderStatus,Amount,ShipperID from orders,customers where orders.CustomerID=customers.CustomerID and customers.Email='"+email+"'";
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
        scrollPane.setBounds(260, 5, 452, 427);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(scrollPane);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        String[] sortstrings = {"none","delivered","initialised","shipped"};
        
        try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        JComboBox comboBox_1 = new JComboBox(sortstrings);
        comboBox_1.setBounds(39, 51, 104, 20);
        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String x=String.valueOf(comboBox_1.getSelectedItem());  //Assigns "Hello Nepal" to s.
                
        		if(x.equals("none"))
        		{ 
        			fp="";		
        		}
        		else if(x.equals("delivered"))
        		{
        			fp=" and orders.OrderStatus='delivered'";
        		}
        		else if(x.equals("initialised"))
        		{
        			fp=" and orders.OrderStatus='initialised'";
        		}       
        		else if(x.equals("shipped"))
        		{
        			fp=" and orders.OrderStatus='shipped'";
        		}
        	}
        });
        panel.add(comboBox_1);
 
       
        mybutton btnNewButton = new mybutton("Apply");
        btnNewButton.setBounds(121, 286, 90, 24);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 try {
        			query1=query1+fp;
					ordercust pp=new ordercust( email,query1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        panel.add(btnNewButton);
        
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					categories c=new categories(email);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnBack.setBounds(10, 286, 90, 24);
        panel.add(btnBack);
        frame.setSize(900,243);
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
	      rr=st.executeQuery(query);
	      
	      Statement stmt = conn.createStatement(); 
	      ResultSet rs=stmt.executeQuery(query);
	      System.out.println(query);
	      
    	int lines=0;
        while (rs.next())
        	lines++;
        data=new Object[lines][4];
        
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	String prodid=rr.getString("OrderID");
        	String prodname=rr.getString("OrderStatus");
        	int prodpr=rr.getInt("Amount");
        	String prodsize=rr.getString("ShipperID");
        	data[i][0]=prodid;
            data[i][1]=prodname;
            data[i][2]=prodpr;
            data[i][3]=prodsize;
            i++;
        }
        conn.close();
               
    }
        
    String[] columnNames = {"Order ID", "Order Status", "Amount", "Shipper ID"};
}




