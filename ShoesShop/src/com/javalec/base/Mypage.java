package com.javalec.base;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;

import com.javalec.dao.MypageDao;
import com.javalec.dao.productListDao;
import com.javalec.dto.MypageDto;
import com.javalec.dto.productListDto;
import com.javalec.util.LoginCustId;

public class Mypage {

	private JFrame frmMypage;
	private JLabel lblcustid;
	private JTextField tfcustid;
	private JTextField tfcname;
	private JLabel lblcname;
	private JButton btnDelete;
	private JButton btnOk;
	private JLabel lblPassword;
	private JTextField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mypage window = new Mypage();
					window.frmMypage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mypage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMypage = new JFrame();

		frmMypage.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				identity();
			}
		});
		frmMypage.setTitle("MyPage");
		frmMypage.setBounds(100, 100, 450, 300);
		frmMypage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMypage.getContentPane().setLayout(null);
		frmMypage.getContentPane().add(getLblcustid());
		frmMypage.getContentPane().add(getTfcustid());
		frmMypage.getContentPane().add(getTfcname());
		frmMypage.getContentPane().add(getLblcname());
		frmMypage.getContentPane().add(getBtnDelete());
		frmMypage.getContentPane().add(getBtnOk());
		frmMypage.getContentPane().add(getLblPassword());
		frmMypage.getContentPane().add(getTfPassword());
	}

	private JLabel getLblcustid() {
		if (lblcustid == null) {
			lblcustid = new JLabel("고객 ID:");
			lblcustid.setBounds(106, 52, 57, 15);
		}
		return lblcustid;
	}

	private JTextField getTfcustid() {
		if (tfcustid == null) {
			tfcustid = new JTextField();
			tfcustid.setEditable(false);
			tfcustid.setBounds(219, 49, 116, 21);
			tfcustid.setColumns(10);
		}
		return tfcustid;
	}

	private JTextField getTfcname() {
		if (tfcname == null) {
			tfcname = new JTextField();
			tfcname.setColumns(10);
			tfcname.setBounds(219, 146, 116, 21);
		}
		return tfcname;
	}

	private JLabel getLblcname() {
		if (lblcname == null) {
			lblcname = new JLabel("고객 이름:");
			lblcname.setBounds(106, 149, 57, 15);
		}
		return lblcname;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("탈퇴");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteAction();
				}
			});
			btnDelete.setBounds(29, 213, 97, 23);
		}
		return btnDelete;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(106, 98, 63, 15);
		}
		return lblPassword;
	}

	private JTextField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JTextField();
			tfPassword.setColumns(10);
			tfPassword.setBounds(219, 95, 116, 21);
		}
		return tfPassword;
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("확인");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = updateFieldCheck();
					if (i_chk == 0) {
						updateAction();
					}
				}
			});
			btnOk.setBounds(312, 213, 97, 23);
		}
		return btnOk;
	}

	private void clearColumn() {
		tfPassword.setText("");
	}

	private void updateAction() {
		String custid = tfcustid.getText();
		String cpassword = tfPassword.getText();
		String cname = tfcname.getText();

		MypageDao dao = new MypageDao(custid, cpassword, cname);
		Boolean ok = dao.updateAction();

		if (ok == true) {
			JOptionPane.showMessageDialog(null, tfcustid.getText() + "님의 정보가 수정되었습니다.");
			LoginCustId.setCustid(custid);

			Login.main(null);
			frmMypage.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "작업중 문제발생했습니다.");
		}
	}

	private int updateFieldCheck() {
		int i = 0;
		String message = "";

		if (tfcustid.getText().trim().length() == 0) {
			i++;
			message = "아이디를 ";
			tfcustid.requestFocus();

		} else if (tfPassword.getText().trim().length() == 0) {
			i++;
			message = "비밀번호를 ";
			tfPassword.requestFocus();
		}

		if (i > 0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
		}

		return i;

	}

	private void identity() {
		MypageDao dao = new MypageDao();
		ArrayList<MypageDto> dto = dao.selectList();
		int listCount = dto.size();

		for (int index = 0; index < listCount; index++) {

			tfcustid.setText(dto.get(index).getCustid());
			tfPassword.setText(dto.get(index).getCpassword());
			tfcname.setText(dto.get(index).getCname());
		}

	}
	
	private void deleteAction() {

		MypageDao dao = new MypageDao();
		Boolean ok = dao.deleteAction();

		if (ok == true) {
			JOptionPane.showMessageDialog(null, tfcustid.getText() + "님이 탈퇴하셨습니.");

			Login.main(null);
			frmMypage.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "작업중 문제발생했습니다.");
		}
	}

}