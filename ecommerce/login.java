package ecommerce;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	String email,pswd;
	private mytextField textField;
	private JPasswordField textField_1;
	Connection conn = null;
	Statement stmt = null;

	//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
    	contentPane.setBackground(new Color(  255, 255, 204));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setBounds(639, 138, 168, 57);
		contentPane.add(lblLoginForm);
		
		JLabel lblUsername = new JLabel("E-mail ID");
		lblUsername.setBounds(530, 246, 97, 14);
		contentPane.add(lblUsername);
		textField = new mytextField("");		
		textField.setBounds(721, 243, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(530, 324, 129, 14);
		contentPane.add(lblPassword);	
		textField_1 = new JPasswordField("");
		textField_1.setBounds(721, 321, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10); 
		
		
		mybutton btnLogin = new mybutton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 email=textField.getText();
				 pswd=textField_1.getText();
				 int flag=0;
				 try {
					Class.forName(JDBC_DRIVER);

				    //System.out.println("loaded");
				     
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
					//System.out.println("connected");
					stmt = conn.createStatement();
					
					 ResultSet rs=stmt.executeQuery("select count(*) as total from admintable where AdminEmail='"+email+"'"+"and Password='"+pswd+"'"); 
					 if(rs.next()){
			    		    int  a=rs.getInt("total");
			    		    if (a==0)
			    		    	flag=0;
			    		    else
			    		    {
			    		    	flag=1;
			    		    	//System.out.println("Admin found");
			    		    }
			    	}
					 conn.close();
				 } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				 if(flag==1)	 
					{
						try {
							JOptionPane.showMessageDialog(null, "Admin Logged In!");
							admin ad=new admin(email);							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
						
					 //System.out.println("admin page");
					}
				 else
				 {
					try{
					   Class.forName(JDBC_DRIVER);
					   //System.out.println("loaded");
				     
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      //System.out.println("connected");
				      stmt = conn.createStatement();
				      ResultSet rs=stmt.executeQuery("select count(*) as total1 from customers where Email='"+email+"'"+"and Password='"+pswd+"'");
				      //System.out.println(rs);
				      while(rs.next()){
				    		    int  a=rs.getInt("total1");
				    		    if (a==0)
				    		    	JOptionPane.showMessageDialog(null, "Incorrect credentials");
				    		    else
				    		    {
				    		    	JOptionPane.showMessageDialog(null, "Logged In!");
				    		    	categories nF = new categories(email);
							        nF.setVisible(true);
							        dispose();
				    		    	//System.out.println("User found");
				    		    }
				    		    }
				    		      conn.close();
				      }
					catch(Exception e)
					{
						
					}
				 }
			}
		});
		btnLogin.setBounds(639, 467, 89, 23);
		contentPane.add(btnLogin);	
	}
}