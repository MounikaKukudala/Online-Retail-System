package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TV extends JFrame {

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
    String fp,fs,up,fr;
    public TV(String email,String ss)
    {
	     this.setBackground(new Color(  255, 255, 204));
     	 this.email=email;
     	 this.query=ss;
     	 this.sx="";
     	 this.fp="";
     	 this.fs="";
     	 this.up="";
     	 this.fr="";
     	 this.query1="select products.ProductID,products.ProductName,products.Price,tv.Size,tv.Resolution,tv.USB_port,products.Quantity from tv,products where products.ProductID=tv.ProductID and products.Quantity>0";
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
        String[] sortstrings = {"none","Sort by Price"};
        String[] filterPrice= {"none","10k-20k","20k-30k","30k-40k"};
        String[] filterSize=  {"none", "20-30","30-40","40-50"};
        String[] USBPort= {"none","yes","no"};
        String[] filterResol= {"none","300-500","500-800","800-1100"};
        
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
                
        		if(x.equals("Sort by Price"))
        		{ 
        			sx=" order by Price";		
        		}
        		else if(x.equals("none"))
        		{
        			sx="";
        		}
        		        			
        	}
        });
        panel.add(comboBox_1);
        
    	JLabel lblfilterprice = new JLabel("Select Price Range");
		lblfilterprice.setBounds(39,80, 104, 14);
		panel.add(lblfilterprice);
        JComboBox comboBox_2 = new JComboBox(filterPrice);
        comboBox_2.setBounds(39, 98, 104, 20);
        
        
        comboBox_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		 String x=String.valueOf(comboBox_2.getSelectedItem());  //Assigns "Hello Nepal" to s.
                 
         		if(x.equals("10k-20k"))
         		{ 
         			fp=" and products.Price<=20000 and products.Price>=10000";
         		}
         		else if(x.equals("20k-30k"))
         		{
         			fp=" and products.Price<=30000 and products.price>=20000";
         		}
         		else if(x.equals("30k-40k"))
         		{
         			fp=" and products.Price<=40000 and products.price>=30000";
         		}
         		else if(x.equals("none"))
        		{
        			fp="";
        		}
        	}
        	});
        panel.add(comboBox_2);
        
        JLabel lblfiltersize = new JLabel("Select Size Range");
		lblfiltersize.setBounds(39,127, 104, 14);
		panel.add(lblfiltersize);
        
        JComboBox comboBox_3 = new JComboBox(filterSize);
        comboBox_3.setBounds(39, 145, 104, 20);
        
        
        comboBox_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		 String x=String.valueOf(comboBox_3.getSelectedItem());  //Assigns "Hello Nepal" to s.
                 
         		if(x.equals("20-30"))
         		{
         			fs=" and TV.Size<=30 and TV.Size>=20";		
         		}
         		else if(x.equals("30-40"))
         		{
         			fs=" and TV.Size<=40 and TV.Size>=30";		
         		}
         		else if(x.equals("40-50"))
         		{ 
         		    fs=" and TV.Size<=50 and TV.Size>=40";		
         		}
         		else if(x.equals("none"))
        		{
        			fs="";
        		}
        	}
        	});
            panel.add(comboBox_3);
            
            JLabel lblfilterport = new JLabel("Select Port");
    		lblfilterport.setBounds(39,174, 104, 14);
    		panel.add(lblfilterport);
    		
            JComboBox comboBox_4 = new JComboBox(USBPort);
            comboBox_4.setBounds(39, 192, 104, 20);
            
            
            comboBox_4.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		
            		
            		 String x=String.valueOf(comboBox_4.getSelectedItem());  //Assigns "Hello Nepal" to s.
                     
             		if(x.equals("yes"))
             		{ 
             			up=" and TV.USB_port='yes'";	
             		}
             		else if(x.equals("no"))
             		{
             			up=" and TV.USB_port='no'";		
             		}
             		else if(x.contentEquals("none")) {
             			up="";
             		}
            	}
            	});
                panel.add(comboBox_4);
                
                JLabel lblfilterreso = new JLabel("Select Resolution Range");
        		lblfilterreso.setBounds(39,221, 104, 14);
        		panel.add(lblfilterreso);
                JComboBox comboBox_5 = new JComboBox(filterResol);
                comboBox_5.setBounds(39, 239, 104, 20);
                
                
                comboBox_5.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	
                		 String x=String.valueOf(comboBox_5.getSelectedItem());  //Assigns "Hello Nepal" to s.
                         
                 		if(x.equals("300-500"))
                 		{
                 			fr=" and TV.Resolution<=500 and TV.Resolution>=300";		
                 		}
                 		else if(x.equals("500-800"))
                 		{
                 			fr=" and TV.Resolution<=800 and TV.Resolution>=500";	
                 		}
                 		else if(x.equals("800-1100"))
                 		{
                 			fr=" and TV.Resolution<=1100 and TV.Resolution>=800";	
                 		}
                 		else if(x.equals("none"))
                		{
                			fr="";
                		}
                	}
                	});
                    panel.add(comboBox_5);
        
        JLabel lblId = new JLabel("Product ID");
        lblId.setBounds(270,455, 80, 9);
        panel.add(lblId);
        textField = new JTextField();
        textField.setBounds(270, 470, 90, 24);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblQuant = new JLabel("Quantity");
		lblQuant.setBounds(370,455, 80, 9);
		panel.add(lblQuant);
		JTextField textField1 = new JTextField();
        textField1.setBounds(370, 470, 60, 24);
        panel.add(textField1);
        textField1.setColumns(10);
       
        mybutton btnNewButton = new mybutton("Apply");
        btnNewButton.setBounds(121, 286, 90, 24);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 try {
        			query1=query1+fp+fs+fr+up+sx;
					TV pp=new TV( email,query1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        panel.add(btnNewButton);
        
        JButton btnAdd = new JButton("Add to cart");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
  
					System.out.println("adding");
					try {
						Class.forName(JDBC_DRIVER);
						conn = DriverManager.getConnection(DB_URL,USER,PASS);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				     catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
					String p="select cartid from customers,cart where customers.customerID=cart.customerID and customers.Email='"+email+"'";
					Statement st = conn.createStatement(); 
				    rr=st.executeQuery(p);
				    String cartid="";
				    while(rr.next())
			        {
			        	cartid=rr.getString("cartid");
			        }
					String s1=textField.getText();
					String s2=textField1.getText();
			        if(s1.equals("")||s2.equals(""))
			        	JOptionPane.showMessageDialog(null, "Please Enter ID / quantity");
			        else {
					Statement st1 = conn.createStatement();
				    ResultSet rs1=st1.executeQuery("select count(*) as l from products where ProductId='"+s1+"' and Quantity>"+s2);
					int l=0;
				    while(rs1.next())
					{
						l=rs1.getInt("l");
					}
				    if(l>0 )
				    {   String ssx="insert into cartContains VALUES (?,?,?)";
					    PreparedStatement pp;
					     
				        pp = (PreparedStatement)conn.prepareStatement(ssx);
						 pp.setString(1,cartid);
						 pp.setString(2,s1 );
						 pp.setInt(3, Integer.parseInt(s2));
						 pp.executeUpdate();
						 
						 JOptionPane.showMessageDialog(null, "Item added Succesfully");
		
				    }
				    else
				    	JOptionPane.showMessageDialog(null, "Enter correct ID / quantity");
					    conn.close();
			        }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				
        	}
        });
        btnAdd.setBounds(450, 470, 120, 24); 
        panel.add(btnAdd);
        
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
        data=new Object[lines][9];
        
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	String prodid=rr.getString("ProductID");
        	String prodname=rr.getString("ProductName");
        	int prodpr=rr.getInt("Price");
        	int prodsize=rr.getInt("Size");
        	int prodcolor=rr.getInt("Resolution"); 
        	String prodgt=rr.getString("USB_port");
        	int prodrating=rr.getInt("Quantity");
        	data[i][0]=prodid;
            data[i][1]=prodname;
            data[i][2]=prodpr;
            data[i][3]=prodsize;
            data[i][4]=prodcolor;
            data[i][5]=prodgt;
            data[i][6]=prodrating;
            i++;
        }
        conn.close();
        
        
    }
        
    
    String[] columnNames = {"Product ID", "Product Name", "Price", "Size","Resolution","USB Port","Quantity"};
    private JTextField textField;
}



