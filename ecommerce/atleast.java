package ecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class atleast extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
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
			atleast dialog = new atleast();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public atleast() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
    	contentPanel.setBackground(new Color(  255, 255, 204));

		setVisible(true);
		
		JLabel lblCustomerLocation = new JLabel("Orders Count:");
		lblCustomerLocation.setBounds(62, 72, 106, 14);
		contentPanel.add(lblCustomerLocation);
		
		textField = new JTextField();
		textField.setBounds(200, 69, 145, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 105, 145, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSupplierId = new JLabel("Supplier ID:");
		lblSupplierId.setBounds(62, 108, 123, 14);
		contentPanel.add(lblSupplierId);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			try {
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 st = conn.createStatement();
				 stmt = conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 HashSet al = new HashSet();
			 mybutton okButton = new mybutton("Find!");
				okButton.setBounds(200, 140, 80, 20);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String s="select x.CustomerID,x.UserName,count(x.OrderID) as count from\n" + 
								"(select customers.CustomerID,customers.UserName,orders.OrderID from\n" + 
								"customers,orders,contains,supplies \n" + 
								"where customers.CustomerID=orders.CustomerID and\n" + 
								"orders.OrderID=contains.OrderID and\n" + 
								"contains.ProductID=supplies.ProductID and\n" + 
								"supplies.SupplierID='"+textField_1.getText()+"') as x\n" + 
								"group by x.CustomerID\n" + 
								"having count>="+textField.getText()+";";
					    System.out.println(s);
						try {
							rt=st.executeQuery(s);
							while(rt.next())
						    {
						          al.add(rt.getString("UserName"));
						    }

							String out="";

							 for (Object i:al) {
							                out+= i+"\n" ;
							 }
							 JOptionPane.showMessageDialog (null, out, "Customers\n", JOptionPane.INFORMATION_MESSAGE);
							 
							 
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
}
