package ecommerce;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class categories extends JFrame {
//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	mybutton btnTV, btnAC, btnMOBILE;
	private JPanel contentPane;
	String email;
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
	
	public categories(String email) throws ClassNotFoundException {
		this.email=email;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setBackground(new Color(  255, 255, 204));

		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		contentPane.setLayout(null);
		
		
		JLabel lblSelectCategory = new JLabel("Select Category : ");
		lblSelectCategory.setBounds(441, 112, 110, 14);
		contentPane.add(lblSelectCategory);
		
		btnTV = new mybutton("TV");
		btnTV.setBounds(233, 179, 89, 23);
		btnTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s="select products.ProductID,products.ProductName,products.Price,tv.Size,tv.Resolution,tv.USB_port,products.Quantity from tv,products where products.ProductID=tv.ProductID and products.Quantity>0";
				TV t = new TV(email,s);
				t.setVisible(true);
			    dispose();
				//System.out.println("tv");
			}
		});
		contentPane.add(btnTV);
		
		btnAC = new mybutton("AC");
		btnAC.setBounds(441, 179, 89, 23);
		btnAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String s="select products.ProductID,products.ProductName,products.Price,AC.Capacity,AC.Voltage,AC.MIN_TEMP,products.Quantity from AC,products where products.ProductID=AC.ProductID and products.Quantity>0";
				    AC ac = new AC(email,s);
					ac.setVisible(true);
				    dispose();
				//System.out.println("ac");
			}
		});
		contentPane.add(btnAC);
		
		mybutton btnMOBILE = new mybutton("MOBILE");
		btnMOBILE.setBounds(652, 179, 89, 23);
		btnMOBILE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qr="select products.ProductID,products.ProductName,products.Price,mobile.RAM,mobile.battery,mobile.Display,products.Quantity from mobile,products where products.ProductID=mobile.ProductID and products.Quantity>0"; 
				Mobile mob= new Mobile(email,qr);
				mob.setVisible(true);
			    dispose();
				//System.out.println("mobile");
		    }
		});
		contentPane.add(btnMOBILE);
		
    	JLabel lblWelcome = new JLabel("Welcome, "+email);
		lblWelcome.setBounds(24, 27, 312, 56);
		contentPane.add(lblWelcome);
		
		mybutton btnHome = new mybutton("Logout");
		btnHome.setBounds(1140, 63, 89, 23);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main m=new main();
				dispose();
			}
		});
		contentPane.add(btnHome);
	     
		mybutton btnMyOrders = new mybutton("My Orders");
		btnMyOrders.setBounds(1010, 63, 120, 23);
		btnMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s="select OrderID,OrderStatus,Amount,ShipperID from orders,customers where orders.CustomerID=customers.CustomerID and customers.Email='"+email+"'";
			    ordercust oc=new ordercust(email,s);
				//System.out.println(s);
			} 
		});
		contentPane.add(btnMyOrders);
		  
				mybutton btnCart = new mybutton("View Cart");
				btnCart.setBounds(878, 63, 120, 23);
				btnCart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					    viewCart oc=new viewCart(email);
						//System.out.println(email);
					}
				});
				contentPane.add(btnCart);
	
}
}