package ecommerce;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class register extends JFrame {

	static String custid="cust";
	static int id=1;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "******";

	   
	/**
	 * Launch the application.
	 */
	   int xx=0;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
    	contentPane.setBackground(new Color(  253, 232, 215));

		contentPane.setLayout(null);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		JLabel lblName = new JLabel("User Name");
		lblName.setBounds(57, 62, 46, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 59, 109, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTown = new JLabel("Town");
		lblTown.setBounds(57, 93, 86, 14);
		contentPane.add(lblTown);
		
		textField_2 = new JTextField();
		textField_2.setBounds(206, 90, 109, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCity = new JLabel("District");
		lblCity.setBounds(57, 124, 46, 14);
		contentPane.add(lblCity);
		
		textField_3 = new JTextField();
		textField_3.setBounds(206, 121, 109, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(57, 155, 46, 14);
		contentPane.add(lblState);
		
		textField_4 = new JTextField();
		textField_4.setBounds(206, 152, 109, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(57, 184, 46, 14);
		contentPane.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setBounds(206, 181, 109, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(57, 215, 109, 14);
		contentPane.add(lblPassword);
		
		textField_6 = new JTextField();
		textField_6.setBounds(206, 212, 109, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(57, 240, 46, 14);
		contentPane.add(lblPhone);
		
		textField_7 = new JTextField();
		textField_7.setBounds(206, 237, 109, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setBounds(168, 11, 160, 37);
		contentPane.add(lblRegistrationForm);
		
		mybutton btnRegister = new mybutton("Register!");
		btnRegister.setBounds(104, 275, 89, 23);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int aa=0;
				
				if(textField_1.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Name Field can't be empty");
				else if(textField_2.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Town Field can't be empty");
				else if(textField_3.getText().equals(""))
					JOptionPane.showMessageDialog(null, "District Field can't be empty");
				else if(textField_4.getText().equals(""))
					JOptionPane.showMessageDialog(null, "State Field can't be empty");
				else if(textField_5.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Email Field can't be empty");
				else if(textField_6.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Password Field can't be empty");
				else if(textField_7.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Phone Field can't be empty");
				else
					aa=1;
						
				if(aa==1)
				{
					
				
				try{
					   Class.forName(JDBC_DRIVER);
					   //System.out.println("in try");

				     
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      stmt = conn.createStatement();
				      st = conn.createStatement();
				      String sql;
				  
				      ResultSet rs=stmt.executeQuery("select * from customers ");
				      
				      System.out.println(rs);
				     int flag=0;
				      while(rs.next())
				      {
				    	  xx=xx+1;
				    		    String  a=rs.getString("Email");
				    		    if(a.equals(textField_5.getText()))
				    		    {
				    		    	flag=1;
				    		    }	    
				     }
				    		      
				   if(flag==0)
				   {//('cust111','myWish','7619048338','gurs','kannauj','up','may@gmail.com','xyz');
					   String sqll = "insert into customers" +
	    		    	        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    		    	System.out.println(xx);
	    		    	PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sqll);
	    		    	String mail=textField_5.getText();
	    		    	
	    		    	xx=xx+1;
	    		    	ps.setString(1,"110"+xx);
	    		    	ps.setString(2,textField_1.getText());
	    		    	ps.setString(3,textField_7.getText());
	    		    	ps.setString(4,textField_2.getText());
	    		    
	    		    	ps.setString(5, textField_3.getText());
	    		    	ps.setString(6, textField_4.getText());
	    		    	ps.setString(7, textField_5.getText());
	    		    	ps.setString(8, textField_6.getText());

	    		    	String sql1= "insert into cart" +
	    		    	             " VALUES (?,?)";
	    		    	PreparedStatement ps1 = (PreparedStatement)conn.prepareStatement(sql1);
	    		    	ps1.setString(1,"cart"+xx);
	    		    	ps1.setString(2,"cust"+xx);
	    		    	int s=0;
	    		    	//System.out.println(sqll);
	    		    	s=ps.executeUpdate();
	    		    	s=ps1.executeUpdate();
	    		    	conn.close();
	    		    	categories m=new categories(mail);
	    		    	m.setVisible(true);
	    		    	dispose();
				   }
				   else
	    		    {
	    		    	JOptionPane.showMessageDialog(null, "User already exists");
	    		    }
				}
				catch(Exception e)
					{}
				}
			}
		});
		contentPane.add(btnRegister);
		
		mybutton btnHome = new mybutton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main m=new main();
		    	m.setVisible(true);
		    	dispose();
			}
		});
		btnHome.setBounds(203, 275, 89, 23);
		contentPane.add(btnHome);
	}
}