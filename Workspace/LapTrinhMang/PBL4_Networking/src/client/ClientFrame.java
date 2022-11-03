package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class ClientFrame implements ActionListener {

	private JFrame frame;
	private JTextField txtYear;
	private JButton btnSend;
	private JLabel lblmsg;
	public Socket soc;
	public DataInputStream dis;
	public DataOutputStream dos;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame() {
		initialize();
		try {
			soc = new Socket("localhost",5000);
			dis = new DataInputStream(soc.getInputStream());
			dos = new DataOutputStream(soc.getOutputStream());
			new ThreadClient(this).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 661, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Input a year");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 130, 127, 50);
		frame.getContentPane().add(lblNewLabel);
		
		txtYear = new JTextField();
		txtYear.setBounds(124, 129, 287, 50);
		frame.getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Chương trình Client");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1.setBounds(216, 10, 230, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnSend = new JButton("Send input");
		btnSend.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSend.setBounds(433, 130, 143, 49);
		btnSend.addActionListener(this);
		frame.getContentPane().add(btnSend);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 637, 2);
		frame.getContentPane().add(separator);
		
		lblmsg = new JLabel("");
		lblmsg.setForeground(Color.RED);
		lblmsg.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblmsg.setBounds(203, 265, 361, 36);
		frame.getContentPane().add(lblmsg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSend) {
			if(txtYear.getText().equals("")) {
				lblmsg.setText("Please enter a year");
			}else {
				if(isNumber(txtYear.getText()) == true) {
					try {
						this.dos.writeUTF(txtYear.getText());
						this.frame.dispose();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}else {
					lblmsg.setText("Input is not a year");
				}
			}
		}
		
	}
	public boolean isNumber(String s) {
		boolean check = true;
		try {
			int number = Integer.parseInt(s);
			if(number <= 0) {
				check = false;
			}
		}catch(Exception e) {
			check = false;
		}
		return check;
	}
}
