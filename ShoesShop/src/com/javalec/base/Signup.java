package com.javalec.base;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.javalec.dao.LoginDao;
import com.javalec.dto.LoginDto;

public class Signup {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField tfCustid;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JTextField tfCname;
	private JButton btnNewButton;
	private JPasswordField pfCpassword;
	private JButton btnDuplicate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup window = new Signup();
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
	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원가입");
		frame.setBounds(100, 100, 720, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTfCustid());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getLblNewLabel_1_1());
		frame.getContentPane().add(getTfCname());
		frame.getContentPane().add(getBtnNewButton());
		frame.getContentPane().add(getPfCpassword());
		frame.getContentPane().add(getBtnDuplicate());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID :");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(55, 73, 124, 37);
		}
		return lblNewLabel;
	}

	private JTextField getTfCustid() {
		if (tfCustid == null) {
			tfCustid = new JTextField();
			tfCustid.setBounds(225, 73, 219, 43);
			tfCustid.setColumns(10);
		}
		return tfCustid;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("비밀번호");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(55, 150, 124, 48);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("이름");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setBounds(55, 239, 124, 48);
		}
		return lblNewLabel_1_1;
	}

	private JTextField getTfCname() {
		if (tfCname == null) {
			tfCname = new JTextField();
			tfCname.setColumns(10);
			tfCname.setBounds(225, 239, 219, 43);
		}
		return tfCname;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("회원가입");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertAction();
				}
			});
			btnNewButton.setBounds(471, 446, 163, 48);
		}
		return btnNewButton;
	}

	private JPasswordField getPfCpassword() {
		if (pfCpassword == null) {
			pfCpassword = new JPasswordField();
			pfCpassword.setBounds(225, 150, 219, 48);
		}
		return pfCpassword;
	}

	private JButton getBtnDuplicate() {
		if (btnDuplicate == null) {
			btnDuplicate = new JButton("중복확인");
			btnDuplicate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkDuplicate();
				}
			});
			btnDuplicate.setBounds(471, 67, 163, 48);
		}
		return btnDuplicate;
	}

	private void insertAction() {

		String custid = tfCustid.getText();
		String cpassword = pfCpassword.getText();
		String cname = tfCname.getText();

		LoginDao dao = new LoginDao(custid, cpassword, cname);

		boolean ok = dao.insertAction();

		if (ok == true) {
			JOptionPane.showMessageDialog(null, "회원가입을 하였습니다.");
			frame.setVisible(false);
			Login.main(null);
		} else {
			JOptionPane.showMessageDialog(null, "DB문제입니다 행정실에 문의하세요!");

			tfCustid.setText("");
			pfCpassword.setText("");
			tfCname.setText("");

		}

	}

	private void checkDuplicate() {

		LoginDao logindao = new LoginDao();
		ArrayList<LoginDto> dtoList = logindao.checkDuplicate();
		String id = tfCustid.getText();
		
		int notDuplicate = 0; 

		for (int index = 0; index < dtoList.size(); index++) {
			if (dtoList.get(index).getCustid().equals(id)) {

				JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다.");
				tfCustid.setText("");
				break;
				
			}
			
			notDuplicate += 1;
		}
		
		if(notDuplicate == dtoList.size()) {
			JOptionPane.showMessageDialog(null, "아이디를 사용할 수 있습니다.");
		}
		
	}

}// End
