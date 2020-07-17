package ecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class addsupplier extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	
	//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addsupplier dialog = new addsupplier();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addsupplier() {
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPanel.setBackground(new Color(  255, 255, 204));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setVisible(true);
		JLabel lblHead = new JLabel("ADD SUPPLIER!");
		lblHead.setBounds(190, 30, 110, 20);
		contentPanel.add(lblHead);
		JLabel lblName = new JLabel("Supplier Name:");
		lblName.setBounds(100, 70, 90, 20);
		contentPanel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(200, 70, 170, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
				mybutton okButton = new mybutton("OK");
				okButton.setBounds(200, 110, 80, 20);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							   Class.forName(JDBC_DRIVER);
							   //System.out.println("in try");

						     
						      conn = DriverManager.getConnection(DB_URL,USER,PASS);
						      stmt = conn.createStatement();
						      st = conn.createStatement();
						      ResultSet rr=st.executeQuery("select count(*) as t from suppliers ");
						      int xx=0;
						      while(rr.next())
						      {
						    	  xx=rr.getInt("t");
						      }
						      String suppId="supp"+(111+xx);
						      String sqll = "insert into suppliers "+
			    		    	        " VALUES (?, ?, ?)";
			    		    	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sqll);

			    		    	preparedStatement.setString(1,suppId);
			    		    	preparedStatement.setString(2,textField.getText());
			    		    	preparedStatement.setString(3,LocalDate.now().toString());
			    		    	
			    		    	int s=0;
			    		    	s=preparedStatement.executeUpdate(); 	
			    		    	dispose();
						    		      
						      }		
							catch(Exception ec)
							{}			
					}
				});
				contentPanel.add(okButton);	
				mybutton cancelButton = new mybutton("Cancel");
				cancelButton.setBounds(290, 110, 80, 20);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});			
				contentPanel.add(cancelButton);
	}
}
