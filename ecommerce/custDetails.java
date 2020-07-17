package ecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class custDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	Connection conn = null;
	String email;
	//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	Statement st=null,stmt=null;
	ResultSet rt=null,rtmt=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			custDetails dialog = new custDetails();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public custDetails() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
    	contentPanel.setBackground(new Color(  255, 255, 204));

		setVisible(true);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 105, 145, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCustId = new JLabel("Customer ID:");
		lblCustId.setBounds(62, 108, 123, 14);
	    contentPanel.add(lblCustId);
			
			 mybutton okButton = new mybutton("Find!");
				okButton.setBounds(200, 140, 80, 20);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textField_1.getText().equals(""))
							JOptionPane.showMessageDialog(null, "ID Field can't be empty");
					    else {
						try {
							conn = DriverManager.getConnection(DB_URL,USER,PASS);
							 st = conn.createStatement();
							 stmt = conn.createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						String out="";
						String s="select * from customers where CustomerID='"+textField_1.getText()+"'";
					    System.out.println(s);
						try {
							rt=st.executeQuery(s);
							while(rt.next())
						    {     out=rt.getString("UserName")+"\n"+rt.getString("Email")+"\n"+rt.getString("Mobile")+"\n"+rt.getString("State")+"\n";
						         
						    }			
							JOptionPane.showMessageDialog (null, out, "Customer\n", JOptionPane.INFORMATION_MESSAGE);
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    dispose();
					}
					}
				});
				contentPanel.add(okButton);	
			
			mybutton cancelButton = new mybutton("Cancel");
			cancelButton.setBounds(290, 140, 80, 20);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});		
			contentPanel.add(cancelButton);
			
	}
}
