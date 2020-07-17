package ecommerce;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main f=new main();
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setBackground(new Color(255, 255, 204));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		mybutton btnLogin = new mybutton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login nF = new login();
		         nF.setVisible(true);
		         dispose(); 
				System.out.println("login clicked");
			}
		});
		btnLogin.setBounds(614, 256, 135, 62);
		contentPane.add(btnLogin);
		
		mybutton btnRegister = new mybutton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  register nF = new register();
			         nF.setVisible(true);
			         dispose();
				System.out.println("register clicked");
			}
		});
		btnRegister.setBounds(614, 344, 135, 62);
		contentPane.add(btnRegister);
	}
}
