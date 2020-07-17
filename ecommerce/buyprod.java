package ecommerce;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class buyprod extends JFrame {
	String custid="";
	private JPanel contentPane;
	int flag=0;
	static int shipper=0;
	static int xx=111;
	Connection conn = null;
	Statement stmt = null,sr=null,stmr=null,stx=null;
//  Database credentials
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/e-commerce";
	static final String USER = "root";
	static final String PASS = "******";
	   Statement st=null,stt=null,st2=null;
	  
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
	public buyprod(String email) throws SQLException {
		setVisible(true);
		this.email=email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setBackground(new Color(  255, 255, 204));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(103, 69, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(227, 69, 46, 14);
		
		
		JLabel lblDeliveryCharges = new JLabel("Delivery Charges:");
		lblDeliveryCharges.setBounds(48, 94, 101, 14);
		contentPane.add(lblDeliveryCharges);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(227, 94, 46, 14);
		
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(106, 119, 76, 14);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(227, 119, 46, 14);
		
		
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		

		st = conn.createStatement();
		ResultSet rt=st.executeQuery("select products.ProductID,products.Price,cartContains.Quantity from products,cartContains,cart,customers where products.ProductID=cartContains.ProductID and cartContains.cartid=cart.cartid and cart.customerID=customers.customerID and customers.Email='"+email+"'" );
		stmt = conn.createStatement();
		stt = conn.createStatement();
		stx = conn.createStatement();
		stmr = conn.createStatement();
		
		ResultSet rs=stmt.executeQuery("select * from orders");
	    while(rs.next())
	    	  xx=xx+1;   		      
		ResultSet rt2=stt.executeQuery("select * from customers where Email='"+email+"'");
		
		String orderid;
		String prodid;
		int price=0,quantity=0;
		orderid="ord"+xx;
		while(rt2.next())
		{
			custid=rt2.getString("CustomerID");
		}
		
		int totalprice=0;
		while(rt.next())
		{
			
		 prodid=rt.getString("ProductID");
		 price=rt.getInt("Price");
		 quantity=rt.getInt("Quantity");
		 totalprice+=quantity * price;
	    
	
		}
		int d=100;
	    if(totalprice>10000)
	    	d=50;
	    
	    lblNewLabel.setText(String.valueOf(totalprice));
	    lblNewLabel_1.setText(String.valueOf(d));
	    lblNewLabel_2.setText(String.valueOf(totalprice+d));
	    String sss="insert into orders VALUES (?,?,?,?,?,?,?,?,?)";
	    Random rand=new Random();
	    String shipid="ship"+(111+rand.nextInt(3));
	    PreparedStatement pp = (PreparedStatement)conn.prepareStatement(sss);
	    pp.setString(1, orderid);
	    pp.setString(2, custid);
	    pp.setString(3,shipid);
	    pp.setString(4, "xyz");
	    pp.setInt(5, totalprice);
	    pp.setString(6, "success");
	    pp.setString(7, LocalDate.now().plusDays(5).toString());
	    pp.setInt(8, d);
	    pp.setString(9, LocalDate.now().toString());
		
		
		mybutton btnConfirmCod = new mybutton("Confirm COD");
		btnConfirmCod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				pp.executeUpdate();
				ResultSet rt=st.executeQuery("select products.ProductID,products.Price,cartContains.Quantity from products,cartContains,cart,customers where products.ProductID=cartContains.ProductID and cartContains.cartid=cart.cartid and cart.customerID=customers.customerID and customers.Email='"+email+"'" );
				while(rt.next())
				{
					
				 String proid=rt.getString("ProductID");
				 int pri=rt.getInt("Price");
				 int qty=rt.getInt("Quantity");
				 String ssx="insert into contains VALUES (?,?,?)";
			    
				PreparedStatement ps = (PreparedStatement)conn.prepareStatement(ssx);

			     ps.setString(1,orderid);
			     ps.setString(2, proid);
			     ps.setInt(3, qty);
			     ps.executeUpdate();
			     ssx="update products set Quantity = Quantity-? where ProductID='"+proid+"'";
			     ps = (PreparedStatement)conn.prepareStatement(ssx);
			     ps.setInt(1, qty);
			     ps.executeUpdate();
				}
				String sd="delete from cartContains where cartid= ( select cartid from cart where CustomerID='"+custid+"')";
				PreparedStatement ps1 = (PreparedStatement)conn.prepareStatement(sd);
				ps1.executeUpdate();
				viewCart var=new viewCart(email);
				
			}catch (Exception et) {
				et.printStackTrace();
			}
			}
		});
			
	   contentPane.add(btnConfirmCod);
		
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		
		btnConfirmCod.setBounds(159, 179, 155, 23);
		contentPane.add(btnConfirmCod);
		
		JLabel lblBilling = new JLabel("Billing");
		lblBilling.setBounds(181, 23, 76, 14);
		contentPane.add(lblBilling);
	}

}

