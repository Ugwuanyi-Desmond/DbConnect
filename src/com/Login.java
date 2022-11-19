package com;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 359);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 239, 213));
		panel.setBounds(0, 0, 434, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 5, 414, 55);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Lucida Fax", Font.BOLD, 15));
		lblNewLabel_1.setBounds(76, 100, 98, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Lucida Fax", Font.BOLD, 15));
		lblNewLabel_2.setBounds(76, 143, 84, 20);
		panel.add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setBorder(UIManager.getBorder("TextField.border"));
		txtUsername.setFont(new Font("Lucida Fax", Font.PLAIN, 15));
		txtUsername.setBounds(184, 93, 168, 33);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(UIManager.getBorder("TextField.border"));
		txtPassword.setFont(new Font("Lucida Fax", Font.PLAIN, 15));
		txtPassword.setBounds(184, 137, 168, 33);
		panel.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
					Statement stmt = con.createStatement();
					String sql = "select * from demodb where Username = '" + txtUsername.getText()+"' and Password = '" + txtPassword.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Succesfull...");
					}
					else{
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Oops", JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
						txtUsername.requestFocusInWindow();
						}
					con.close();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnLogin.setFocusable(false);
		btnLogin.setBackground(new Color(255, 218, 185));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(184, 220, 75, 23);
		panel.add(btnLogin);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Are you Sure You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION);
			if(a == 0) {
				System.exit(0);
			}
			
			}
		});
		btnClose.setFocusable(false);
		btnClose.setBackground(new Color(255, 218, 185));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(276, 220, 75, 23);
		panel.add(btnClose);
	}
}
