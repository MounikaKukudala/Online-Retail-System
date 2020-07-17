package ecommerce;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class admin extends JFrame {

	private JPanel contentPane;
	Connection conn = null;
	Statement stmt = null,stmr=null,stx=null,sr=null,srr=null;
	ResultSet rs=null,rt=null,rtr=null,rtx=null,rr=null,rrr=null;
//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	String email;
	public admin(String email) throws SQLException {
		setVisible(true);
		this.email=email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setBackground(new Color(  255, 255, 204));
        setContentPane(contentPane);
        
        JLabel lblWelcomeAdmin = new JLabel("Welcome, Admin!");
		lblWelcomeAdmin.setBounds(555, 102, 188, 14);
		contentPane.add(lblWelcomeAdmin);
		
        mybutton btnLogout = new mybutton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main n=new main();
				dispose();
			}
		});
		btnLogout.setBounds(1230, 38, 89, 23);
		contentPane.add(btnLogout);
		
		mybutton btnCustomersWithAt = new mybutton("Customers with at least n orders from entered Supplier.");
		btnCustomersWithAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atleast at=new atleast();
			}
		});
		btnCustomersWithAt.setBounds(408, 141, 385, 31);
		contentPane.add(btnCustomersWithAt);
		
		mybutton btnAddSupplier = new mybutton("Add Supplier");
		btnAddSupplier.setBounds(519, 229, 154, 23);
		btnAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addsupplier as=new addsupplier();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAddSupplier);
		
		mybutton btnUpdateqn = new mybutton("Update Quantity");
		btnUpdateqn.setBounds(519, 263, 154, 23);
		btnUpdateqn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updateqn as=new Updateqn();
			}
		});
		contentPane.add(btnUpdateqn);
		
		String [] pro={"none","TV","AC","Mobile"};
		JComboBox comboBox1 = new JComboBox(pro);
		comboBox1.setBounds(430, 297, 80, 23);
		contentPane.add(comboBox1);
		
		mybutton btnNewButton = new mybutton("Add Product");
		btnNewButton.setBounds(519, 297, 154, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sel=(String) comboBox1.getSelectedItem();
				if(sel.contentEquals("TV")) {
				   addTV ap=new addTV();
					System.out.println("TV");
				}
				else if(sel.contentEquals("AC")) {
					addAC ap=new addAC();
					System.out.println("AC");
				}
				else if(sel.contentEquals("Mobile")) {
					addMobile ap=new addMobile();
					System.out.println("Mobile");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select Category");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		mybutton btnCustD = new mybutton("Customer Details");
		btnCustD.setBounds(519, 331, 154, 23);
		btnCustD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				custDetails as=new custDetails();
			}
		});
		contentPane.add(btnCustD);
		
		JLabel lblSelectSuppliersWho = new JLabel("Select suppliers who supply apparel for given Category");
		lblSelectSuppliersWho.setBounds(109, 385, 354, 17);
		contentPane.add(lblSelectSuppliersWho);
		
		String [] supp={"Select Category","TV","AC","Mobile"};
		JComboBox comboBox = new JComboBox(supp);
		comboBox.setBounds(519, 383, 154, 23);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String x=String.valueOf(comboBox.getSelectedItem());  //Assigns "Hello Nepal" to s.
                 
         		if(x.equals("TV"))
         		{
         		supplierfilter sp=new supplierfilter("tv",email);
         		}
         		else if(x.equals("AC"))
         		{
         		supplierfilter sp=new supplierfilter("ac",email);
         		}
         		else if(x.equals("Mobile"))
         		{
         		supplierfilter sp=new supplierfilter("mobile",email);
         		}
        	}
	 });
		
		JLabel lblFilterProductsBy = new JLabel("Filter Products by Supplier ID");
		lblFilterProductsBy.setBounds(240, 424, 206, 17);
		contentPane.add(lblFilterProductsBy);
		
		textField = new JTextField("Supplier ID");
		textField.setBounds(519, 421, 70, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		mybutton btnNewButton_1 = new mybutton("Go");
		btnNewButton_1.setBounds(603, 421, 70, 23);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int c=0;
				try {
					Class.forName(JDBC_DRIVER);
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
					Statement st = conn.createStatement(); 
					String s="select count(*) as count from suppliers where SupplierID='"+textField.getText()+"'";
					rs=st.executeQuery(s);
					while(rs.next())
						c=rs.getInt("count");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if(c>0) {
			      productfilter pf=new productfilter(email,textField.getText());
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Enter correct Supplier ID");
			    }
			}
		});
		contentPane.add(btnNewButton_1);
		
		mybutton btnUp = new mybutton("Update Status");
		btnUp.setBounds(519, 469, 154, 23);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatus us=new updateStatus();
			
			}
			
		});
		contentPane.add(btnUp);
		
		JLabel lblListAllOrders = new JLabel("Orders placed on a particular date: ");
		lblListAllOrders.setBounds(240, 548, 210, 23);
		contentPane.add(lblListAllOrders);
		
		JTextField DateField = new JTextField("YYYY-MM-DD");
		DateField.setBounds(450,548,100,23);
		contentPane.add(DateField);
		
		JLabel lblListAll = new JLabel("Including atleast one quantity of: ");
		lblListAll.setBounds(555, 548, 195, 23);
		contentPane.add(lblListAll);
		
		JTextField IDField = new JTextField("Product ID");
		IDField.setBounds(745,548,70,23);
		contentPane.add(IDField);
		
		mybutton button = new mybutton("Go");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s="select * from orders,contains,products where orders.OrderID=contains.OrderID and contains.ProductID=products.ProductID and contains.QuantityOrdered>=1 and Placed_date='"+DateField.getText()+"' and contains.ProductID='"+IDField.getText()+"'";
				onedisp dp=new onedisp(s,email);
			}
		});
		button.setBounds(560, 580, 80, 23);
		contentPane.add(button);	
	}
}