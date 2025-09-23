package in.parthi.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import in.parthi.core.service.ProductService;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	JLabel lblOperation;
	JLabel lblStatus;
	//Add Product Form
	private JTextField textProductId;
	JLabel lblProductId;
	JButton btnSubmitAddProduct;
	//Add Product End

	//Add Product Form
	private JTextField textTransactionId;
	JLabel lblTransactionId;
	JButton btnSubmitAddTransaction;
	//Add Product End

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 794, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblOperation = new JLabel("-");
		lblOperation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOperation.setBounds(362, 18, 359, 37);
		frame.getContentPane().add(lblOperation);

		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddProduct.setBounds(21, 106, 172, 31);
		frame.getContentPane().add(btnAddProduct);

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddExpense.setBounds(21, 159, 172, 31);
		frame.getContentPane().add(btnAddExpense);

		JButton btnAddSales = new JButton("Add Sales");
		btnAddSales.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddSales.setBounds(21, 214, 172, 31);
		frame.getContentPane().add(btnAddSales);

		JButton btnReturnProduct = new JButton("Return Product");
		btnReturnProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReturnProduct.setBounds(21, 269, 172, 31);
		frame.getContentPane().add(btnReturnProduct);

		JButton btnCustomerReturn = new JButton("Customer Return");
		btnCustomerReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCustomerReturn.setBounds(21, 318, 172, 31);
		frame.getContentPane().add(btnCustomerReturn);

		lblStatus = new JLabel("STATUS: ");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatus.setBackground(Color.GRAY);
		lblStatus.setBounds(10, 513, 760, 54);
		frame.getContentPane().add(lblStatus);

		JLabel lblParthiLogistics = new JLabel("Parthi Logistics");
		lblParthiLogistics.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblParthiLogistics.setBackground(Color.GRAY);
		lblParthiLogistics.setBounds(20, 18, 320, 54);
		frame.getContentPane().add(lblParthiLogistics);

		// Product Form Started
		textProductId = new JTextField();
		textProductId.setBounds(574, 107, 128, 31);
		frame.getContentPane().add(textProductId);
		textProductId.setColumns(10);
		textProductId.setVisible(false);

		lblProductId = new JLabel("Product Id");
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProductId.setBounds(438, 105, 96, 31);
		frame.getContentPane().add(lblProductId);
		lblProductId.setVisible(false);

		btnSubmitAddProduct = new JButton("Submit");
		btnSubmitAddProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmitAddProduct.setBounds(617, 482, 85, 21);
		frame.getContentPane().add(btnSubmitAddProduct);
		btnSubmitAddProduct.setVisible(false);
		// Product Form Ended

		// Add Expense
		//		 Product Form Started
		textTransactionId = new JTextField();
		textTransactionId.setBounds(574, 107, 128, 31);
		frame.getContentPane().add(textTransactionId);
		textTransactionId.setColumns(10);
		textTransactionId.setVisible(false);

		lblTransactionId = new JLabel("Transaction Id");
		lblTransactionId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionId.setBounds(417, 106, 123, 31);
		frame.getContentPane().add(lblTransactionId);
		lblTransactionId.setVisible(false);

		btnSubmitAddTransaction = new JButton("Submit");
		btnSubmitAddTransaction.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmitAddTransaction.setBounds(617, 482, 85, 21);
		frame.getContentPane().add(btnSubmitAddTransaction);
		btnSubmitAddTransaction.setVisible(false);
		// End Add Expense

		//Action for the buttons started
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideAll();
				showAddProduct();
				frame.repaint();		
			}
		});

		btnSubmitAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductService productService = new ProductService();
				String id = "";
				id = textProductId.getText();
				String response  = productService.addMyProduct(id);
				lblStatus.setText(response);
			}
		});

		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideAll();
				showAddTransaction();
				frame.repaint();	
			}
		});

		btnAddSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("Add Sales");
			}
		});

		btnReturnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("Return Product");
			}
		});

		btnCustomerReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("Customer Return");
			}
		});
	}

	private void showAddProduct() {
		//Make the form visible
		lblOperation.setText("Add Product");
		textProductId.setVisible(true);
		lblProductId.setVisible(true);
		btnSubmitAddProduct.setVisible(true);
	}
	
	private void showAddTransaction() {
		//Make the form visible
		lblOperation.setText("Add Transaction");
		textTransactionId.setVisible(true);
		lblTransactionId.setVisible(true);
		btnSubmitAddTransaction.setVisible(true);
	}

	private void hideAll() {
		lblStatus.setText("STATUS:");
		
		textProductId.setVisible(false);
		lblProductId.setVisible(false);
		btnSubmitAddProduct.setVisible(false);
		
		
		textTransactionId.setVisible(false);
		lblTransactionId.setVisible(false);
		btnSubmitAddTransaction.setVisible(false);
	}
}
