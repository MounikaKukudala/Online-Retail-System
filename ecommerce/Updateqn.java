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

public class Updateqn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1,textField_2;
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Updateqn dialog = new Updateqn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Updateqn() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
    	contentPanel.setBackground(new Color(  255, 255, 204));

		setVisible(true);
		
		JLabel lblID = new JLabel("Product ID");
		lblID.setBounds(110, 92, 92, 20);
		contentPanel.add(lblID);
		
		textField_1 = new JTextField();
		textField_1.setBounds(224, 89, 170, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblqn = new JLabel("Quantity");
		lblqn.setBounds(110, 129, 92, 20);
		contentPanel.add(lblqn);
		
		textField_2 = new JTextField();
		textField_2.setBounds(224, 129, 170, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		
		mybutton okButton = new mybutton("Update");
		okButton.setBounds(224,160,80,20);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							  
						      if(textField_1.getText().equals(""))
									JOptionPane.showMessageDialog(null, "ID Field can't be empty");
							  else if(textField_2.getText().equals(""))
									JOptionPane.showMessageDialog(null, "Quantity Field can't be empty");
							  else {
								  Class.forName(JDBC_DRIVER);
							      conn = DriverManager.getConnection(DB_URL,USER,PASS);
							      stmt = conn.createStatement();
							      int c=0;
								  ResultSet  rr=stmt.executeQuery("select count(*) as count from products where ProductID='"+textField_1.getText()+"'");
								     while(rr.next())
									 {
										 c=rr.getInt("count");
									 }
								    
								     if(c>0) {
								    	           
											      String sqll = "UPDATE products "+
								                                "SET Quantity=Quantity+"+textField_2.getText()+
								                                " where ProductID='"+textField_1.getText()+"'";
								    		      System.out.println(sqll);
								    		      PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sqll);
								    		      preparedStatement.executeUpdate();
								    		      JOptionPane.showMessageDialog(null, "Successfully Updated!");
					                              conn.close();
								    		       dispose();
								     }
								     else {
								    	 JOptionPane.showMessageDialog(null, "Enter correct  Product ID");
								     }
							  }}
									catch(Exception ec)
									{}
							      
					}			  
				});
				contentPanel.add(okButton);	    		      
						     
				
				mybutton cancelButton = new mybutton("Cancel");
				cancelButton.setBounds(314,160 , 80, 20);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				contentPanel.add(cancelButton);	
	}
}
