package ecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
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

public class addMobile extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_ID,textField_Quan,textField_Display ;
	private JTextField textField_Name,textField_Price,textField_RAM,textField_Battery;
	private JPanel contentPane;
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addMobile dialog = new addMobile();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addMobile() {
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPanel.setBackground(new Color(  255, 255, 204));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setVisible(true);
		contentPanel.setLayout(null);
		
		JLabel lblSuppID = new JLabel("Supplier ID");
		lblSuppID.setBounds(99, 60, 82, 14);
		contentPanel.add(lblSuppID);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(224, 60, 91, 14);
		contentPanel.add(textField_ID);
		textField_ID.setColumns(10);
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setBounds(99, 85, 96, 14);
		contentPanel.add(lblName);
	
		textField_Name = new JTextField();
		textField_Name.setBounds(224, 85, 91, 14);
		contentPanel.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel Price = new JLabel("Price");
		Price.setBounds(99, 110, 46, 14);
		contentPanel.add(Price);
		
		textField_Price = new JTextField();
		textField_Price.setBounds(224, 110, 91, 14);
		textField_Price.setColumns(10);
		contentPanel.add(textField_Price);
		
		JLabel Quantity = new JLabel("Quantity");
		Quantity.setBounds(99, 135, 90, 14);
		contentPanel.add(Quantity);
		
		textField_Quan = new JTextField();
		textField_Quan.setBounds(224, 135, 91, 14);
		textField_Quan.setColumns(10);
		contentPanel.add(textField_Quan);
		
		JLabel RAM= new JLabel("RAM");
		RAM.setBounds(99, 160, 46, 14);
		contentPanel.add(RAM);
		textField_RAM = new JTextField();
		textField_RAM.setColumns(10);
		textField_RAM.setBounds(224, 160, 91, 14);
		contentPanel.add(textField_RAM);
		
		JLabel Battery = new JLabel("Battery");
		Battery.setBounds(99, 185, 82, 14);
		contentPanel.add(Battery);
		textField_Battery = new JTextField();
		textField_Battery.setColumns(10);
		textField_Battery.setBounds(224, 185, 91, 14);
		contentPanel.add(textField_Battery);
		
		textField_Display = new JTextField();
		textField_Display.setColumns(10);
		textField_Display.setBounds(224, 210, 91, 14);
		contentPanel.add(textField_Display);
		
		JLabel Display = new JLabel("Display");
		Display.setBounds(99, 210, 91, 14);
		contentPanel.add(Display);
			
		mybutton okButton = new mybutton("Add");
		okButton.setBounds(100, 240, 90, 14);
		okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(textField_ID.getText().equals(""))
				JOptionPane.showMessageDialog(null, "ID Field can't be empty");
			else if(textField_Name.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Name Field can't be empty");
			else if(textField_Price.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Price Field can't be empty");
			else if(textField_Quan.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Quantity Field can't be empty");
			else if(textField_RAM.getText().equals(""))
				JOptionPane.showMessageDialog(null, "RAM Field can't be empty");
			else if(textField_Battery.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Battery Field can't be empty");
			else if(textField_Display.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Display Field can't be empty");
		else
		{
			try{
				 Class.forName(JDBC_DRIVER);
							   //System.out.println("in try");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 stmt = conn.createStatement();
				 st = conn.createStatement();
				 ResultSet rr=st.executeQuery("select count(*) as count from mobile ");
						      int xx=0;
						      while(rr.next())
						      {
						    	  xx=rr.getInt("count");
						      }
						      xx++;
			    		    	
				 int c=0;
			     rr=st.executeQuery("select count(*) as count from suppliers where SupplierID='"+textField_ID.getText()+"'");
			     while(rr.next())
				 {
					 c=rr.getInt("count");
				 }
			     if(c>0) {
						      String sqll = "insert into products "+
			    		    	        " VALUES (?, ?, ?, ?, ?)";
			    		      PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sqll);
			    		    	
			    		      preparedStatement.setString(1,"mob"+(110+xx));
			    		      preparedStatement.setInt(2,Integer.valueOf(textField_Price.getText()));
			    		      preparedStatement.setString(3,"mob");
			    		      preparedStatement.setString(4,textField_Name.getText());
			    		      preparedStatement.setInt(5,Integer.valueOf(textField_Quan.getText()));
			    		      preparedStatement.executeUpdate(); 
			    		      System.out.println(xx);
			    		      
			    		      sqll="insert into MOBILE "+
			    		      " VALUES(?,?,?,?)";
			    		      preparedStatement = (PreparedStatement) conn.prepareStatement(sqll);
			    		      preparedStatement.setString(1,"mob"+(110+xx));
			    		      preparedStatement.setInt(2,Integer.valueOf(textField_RAM.getText()));
			    		      preparedStatement.setInt(3,Integer.valueOf(textField_Battery.getText()));
			    		      preparedStatement.setInt(4,Integer.valueOf(textField_Display.getText()));
			    		      preparedStatement.executeUpdate(); 
			    		      
			    		     
			    		      sqll="insert into supplies "+
			    		      " VALUES(?,?)";
			    		      preparedStatement = (PreparedStatement) conn.prepareStatement(sqll);
			    		      preparedStatement.setString(1,textField_ID.getText());
			    		      preparedStatement.setString(2,"mob"+(110+xx));
			    		      preparedStatement.executeUpdate(); 
			    		      JOptionPane.showMessageDialog(null, "Successfully added!");
                              conn.close();
			    		    	dispose();
			     }
			     else {
			    	 JOptionPane.showMessageDialog(null, "Enter correct  Supplier ID");
			     }				    		      
				}catch(Exception ec)
				{}
		}
						
			}
		});
		contentPanel.add(okButton);
		
		mybutton cancelButton = new mybutton("Cancel");
		cancelButton.setBounds(210, 240, 90, 14);
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
		contentPanel.add(cancelButton);
	}
}