package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PrintCalendar implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	public ArrayList<JPanel> subPanels = new ArrayList<JPanel>();
	public ArrayList<String> dataMonths = new ArrayList<String>();
	public ArrayList<Integer> dateOfMonth = new ArrayList<Integer>();
	private JButton btnLeft,btnRight;
	private JComboBox<String> cbbMonth;
	public int month = 1,year;
	private JLabel lblNewLabel;
	
	public void addDataMonths(String s) {
		String data[] = s.split("/");
		for(String msg : data) {
			dataMonths.add(msg);
		}
	}
	public void addDateOfMonth(int index) {
		dateOfMonth.clear();
		String temp[] = dataMonths.get(index).split("-");
		for(String s : temp) {
			try {
				if(isNumber(s)) {
					dateOfMonth.add(Integer.parseInt(s));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintCalendar window = new PrintCalendar("");
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
	public PrintCalendar(String msg) {
		addDataMonths(msg);
		year = Integer.parseInt(dataMonths.get(dataMonths.size()-1));
		initialize();
		addGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 854, 531);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		initPanel();
		frame.getContentPane().add(panel);
		
		btnLeft = new JButton("<<");
		btnLeft.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnLeft.setBounds(23, 218, 99, 48);
		btnLeft.addActionListener(this);
		frame.getContentPane().add(btnLeft);
		
		btnRight = new JButton(">>");
		btnRight.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnRight.setBounds(732, 218, 85, 48);
		btnRight.addActionListener(this);
		frame.getContentPane().add(btnRight);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 820, 2);
		frame.getContentPane().add(separator);
		
		btnLeft.setEnabled(false);
		
		lblNewLabel = new JLabel("Năm "+year);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(676, 10, 141, 39);
		frame.getContentPane().add(lblNewLabel);
		
		cbbMonth = new JComboBox<String>();
		cbbMonth.setForeground(Color.RED);
		cbbMonth.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbbMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		cbbMonth.setBounds(343, 10, 169, 39);
		cbbMonth.addActionListener(this);
		frame.getContentPane().add(cbbMonth);
		frame.setVisible(true);
		
	}
	
	private void initPanel() {
		this.panel = new JPanel();
		this.panel.setBounds(154, 72, 568, 384);
		this.panel.setLayout(new GridLayout(7,7));
		String[] dayOfWeeks = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(int i = 0;i <=6;i++) {
			JLabel lbl = new JLabel();
			lbl.setText(dayOfWeeks[i]);
			lbl.setFont(new Font("arial", Font.BOLD, 16));
			this.panel.add(lbl);
		}
		
		for(int i=8;i<=49;i++) {
			JPanel panel1 = new JPanel(new GridLayout(2,1));
			panel1.add(new JLabel("label"));
			panel1.add(new JLabel(""));
			subPanels.add(panel1);
			this.panel.add(panel1);
		}
	}
	private void addGUI() {
		resetPanel();
		addDateOfMonth(month-1);
		
		
		for(int i = 0; i < dateOfMonth.size();i++) {
			JLabel label = (JLabel)subPanels.get(i).getComponent(0);
			JLabel label2 = (JLabel)subPanels.get(i).getComponent(1);
			if(dateOfMonth.get(i) == 0) {
				label.setText("");
			}else {
				label.setText(dateOfMonth.get(i)+"");
				label.setFont(new Font("arial", Font.BOLD, 14));
				label2.setText(LichAm.convertSolar2Lunar(dateOfMonth.get(i), month, year));
				label2.setFont(new Font("arial", Font.ITALIC, 11));
			}
		}
	}
	private void resetPanel() {		
		for(JPanel panel : subPanels) {
			for(Component comp : panel.getComponents()) {
				JLabel label= (JLabel)comp;
				label.setText("");
			}
		}
	}
	
	void setEnableButton() {
		if (month == 1) {
			btnLeft.setEnabled(false);
		} else if (month > 1) {
			btnLeft.setEnabled(true);
		}

		if (month == 12) {
			btnRight.setEnabled(false);
		} else if (month < 12) {
			btnRight.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLeft) {
			month--;
		}
		if (e.getSource() == btnRight) {
			month++;
		}
		if(e.getSource() == cbbMonth) {
			month = cbbMonth.getSelectedIndex() + 1;
		}
		setEnableButton();
		addGUI();
		cbbMonth.setSelectedIndex(month - 1);
//		setStartDate(1, month, year);
	}
	
	public boolean isNumber(String s) {
		boolean check = true;
		try {
			int number = Integer.parseInt(s);
		}catch(Exception e) {
			check = false;
		}
		return check;
	}
	public boolean isPanel(Component cmp) {
		boolean check = true;
		try {
			JPanel panel = (JPanel)cmp;
		}catch(Exception e) {
			check = false;
		}
		return check;
	}
}
