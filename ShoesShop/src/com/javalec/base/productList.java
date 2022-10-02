package com.javalec.base;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.productListDao;
import com.javalec.dto.productListDto;
import com.javalec.util.DBConnect;

public class productList {

	private JFrame frmShoesShop;
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JScrollPane scrollPane;
	private JTable Inner_Table;
	private JLabel lblProductId;
	private JTextField tfProductId;
	private JLabel lblPBrand;
	private JTextField tfPBrand;
	private JLabel lblPPrice;
	private JTextField tfPPrice;

	// --Table Definition
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JButton btnOk;
	private JTextField tfOCount;
	private JLabel lblOCount;
	private JLabel lblNewLabel;
	private JButton btnMyPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					productList window = new productList();
					window.frmShoesShop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public productList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShoesShop = new JFrame();
		frmShoesShop.getContentPane().setBackground(new Color(255, 215, 255));
		frmShoesShop.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		frmShoesShop.setTitle("Shoes Shop");
		frmShoesShop.setBounds(100, 100, 485, 480);
		frmShoesShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShoesShop.getContentPane().setLayout(null);
		frmShoesShop.getContentPane().add(getCbSelection());
		frmShoesShop.getContentPane().add(getTfSelection());
		frmShoesShop.getContentPane().add(getBtnQuery());
		frmShoesShop.getContentPane().add(getScrollPane());
		frmShoesShop.getContentPane().add(getLblProductId());
		frmShoesShop.getContentPane().add(getTfProductId());
		frmShoesShop.getContentPane().add(getLblPBrand());
		frmShoesShop.getContentPane().add(getTfPBrand());
		frmShoesShop.getContentPane().add(getLblPPrice());
		frmShoesShop.getContentPane().add(getTfPPrice());
		frmShoesShop.getContentPane().add(getBtnOk());
		frmShoesShop.getContentPane().add(getTfOCount());
		frmShoesShop.getContentPane().add(getLblOCount());
		frmShoesShop.getContentPane().add(getLblNewLabel());
		frmShoesShop.getContentPane().add(getBtnMyPage());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "상품명", "브랜드명" }));
			cbSelection.setBounds(36, 46, 97, 29);
		}
		return cbSelection;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(129, 48, 208, 21);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}

	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnQuery.setBounds(339, 47, 97, 23);
		}
		return btnQuery;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 87, 400, 154);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}

	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						tableClick();
					}
				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table);
		}
		return Inner_Table;
	}

	private JLabel getLblProductId() {
		if (lblProductId == null) {
			lblProductId = new JLabel("상품명");
			lblProductId.setBounds(57, 253, 105, 15);
		}
		return lblProductId;
	}

	private JTextField getTfProductId() {
		if (tfProductId == null) {
			tfProductId = new JTextField();
			tfProductId.setEditable(false);
			tfProductId.setColumns(10);
			tfProductId.setBounds(184, 250, 191, 21);
		}
		return tfProductId;
	}

	private JLabel getLblPBrand() {
		if (lblPBrand == null) {
			lblPBrand = new JLabel("브랜드명");
			lblPBrand.setBounds(57, 283, 105, 15);
		}
		return lblPBrand;
	}

	private JTextField getTfPBrand() {
		if (tfPBrand == null) {
			tfPBrand = new JTextField();
			tfPBrand.setEditable(false);
			tfPBrand.setColumns(10);
			tfPBrand.setBounds(184, 280, 191, 21);
		}
		return tfPBrand;
	}

	private JLabel getLblPPrice() {
		if (lblPPrice == null) {
			lblPPrice = new JLabel("가격");
			lblPPrice.setBounds(57, 313, 105, 15);
		}
		return lblPPrice;
	}

	private JTextField getTfPPrice() {
		if (tfPPrice == null) {
			tfPPrice = new JTextField();
			tfPPrice.setEditable(false);
			tfPPrice.setColumns(10);
			tfPPrice.setBounds(184, 310, 191, 21);
		}
		return tfPPrice;
	}

	private JTextField getTfOCount() {
		if (tfOCount == null) {
			tfOCount = new JTextField();
			tfOCount.setColumns(10);
			tfOCount.setBounds(184, 340, 191, 21);
		}
		return tfOCount;
	}

	private JLabel getLblOCount() {
		if (lblOCount == null) {
			lblOCount = new JLabel("구매 수량");
			lblOCount.setBounds(57, 343, 105, 15);
		}
		return lblOCount;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("상품 목록");
			lblNewLabel.setBounds(215, 20, 61, 16);
		}
		return lblNewLabel;
	}
	private JButton getBtnMyPage() {
		if (btnMyPage == null) {
			btnMyPage = new JButton("마이페이지");
			btnMyPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnMyPage.setBounds(45, 375, 117, 29);
		}
		return btnMyPage;
	}

	// function
	private void clearColumn() {
		tfProductId.setText("");
		tfPBrand.setText("");
		tfPPrice.setText("");
		tfOCount.setText("");
	}
	
	private void conditionQuery() {
		int i = cbSelection.getSelectedIndex();
		String conditionQueryColumn = "";
		switch(i) {
			case 0:
				conditionQueryColumn = "productid";
				break;
			case 1:
				conditionQueryColumn = "pbrand";
				break;
			default:
				break;
		}
		
		tableInit();
		clearColumn();
		conditionQueryAction(conditionQueryColumn);
	}
	
	private void conditionQueryAction(String conditionQueryColumn) {
		productListDao dao = new productListDao(conditionQueryColumn, tfSelection.getText());
		ArrayList<productListDto> dtoList = dao.conditionList();
		
		int listCount = dtoList.size();
		
		for (int index = 0; index < listCount; index++) {
			String[] qTxt = {dtoList.get(index).getProductid(), dtoList.get(index).getPbrand(), Integer.toString(dtoList.get(index).getPprice())};
			Outer_Table.addRow(qTxt);
		}
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("구매");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertAction();
					tableInit();
					searchAction();
					clearColumn();
				}
			});
			btnOk.setBounds(268, 375, 117, 29);
		}
		return btnOk;
	}

	// Search
	private void searchAction() {
		productListDao dao = new productListDao();
		ArrayList<productListDto> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {
			String[] qTxt = { dtoList.get(index).getProductid(), dtoList.get(index).getPbrand(), Integer.toString(dtoList.get(index).getPprice()) };
			Outer_Table.addRow(qTxt);
		}
	}

	private void tableInit() {

		Outer_Table.addColumn("상품명");
		Outer_Table.addColumn("브랜드명");
		Outer_Table.addColumn("가격");

		Outer_Table.setColumnCount(3);

		int i = Outer_Table.getRowCount();
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}

		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
		int width = 120;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

	
	}
	
	private void tableClick() {
		int i = Inner_Table.getSelectedRow();
		String productid = (String) Inner_Table.getValueAt(i, 0);
		
		productListDao dao = new productListDao(productid);
		productListDto dto = dao.tableClick();
		
		tfProductId.setText(dto.getProductid());
		tfPBrand.setText(dto.getPbrand());
		tfPPrice.setText(Integer.toString(dto.getPprice()));
	}
	
	private void insertAction() {
		productListDao dao = new productListDao();
		dao.insertAction(tfProductId.getText(), tfPPrice.getText(), tfOCount.getText());

	}
	
}