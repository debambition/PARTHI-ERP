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
import in.parthi.core.service.TransactionService;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	JLabel lblOperation;
	JLabel lblStatus;
	//Add Product Form
	private JTextField textProductId;
	JLabel lblProductId;
	JButton btnSubmitAddProduct;
	
	private JTextField textCategory;
	 JLabel lblCategory;
	
	private JTextField textName;
	JLabel lblName;
	
	private JTextField textDescription;
	JLabel lblDescription;
	
	private JTextField textCostPrice;
	JLabel lblCostPrice;
	
	private JTextField textSellingPrice;
	JLabel lblSellingPrice;
	
	private JTextField textMrp;
	JLabel lblMrp;
 
	//Add Product End

	//Add Transaction Form
	private JTextField textTransactionId;
	JLabel lblTransactionId;
	JButton btnSubmitAddTransaction;

	private JTextField textParticular;
	JLabel lbParticular;
	private JTextField textTransactionType;
	JLabel lbTransactionType;
	
	//Add Transaction End

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
		//
		lblStatus.setOpaque(true);
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
		
		// Category input field and label
		textCategory = new JTextField();
		textCategory.setBounds(574, 150, 128, 31);
		frame.getContentPane().add(textCategory);
		textCategory.setColumns(10);
		textCategory.setVisible(false);

		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategory.setBounds(438, 150, 96, 31);
		frame.getContentPane().add(lblCategory);
		lblCategory.setVisible(false);

		// Name input field and label
		textName = new JTextField();
		textName.setBounds(574, 190, 128, 31);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		textName.setVisible(false);

		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(438, 190, 96, 31);
		frame.getContentPane().add(lblName);
		lblName.setVisible(false);

		// Description input field and label
		textDescription = new JTextField();
		textDescription.setBounds(574, 230, 128, 31);
		frame.getContentPane().add(textDescription);
		textDescription.setColumns(10);
		textDescription.setVisible(false);

		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(438, 230, 96, 31);
		frame.getContentPane().add(lblDescription);
		lblDescription.setVisible(false);

		// Cost Price input field and label
		textCostPrice = new JTextField();
		textCostPrice.setBounds(574, 270, 128, 31);
		frame.getContentPane().add(textCostPrice);
		textCostPrice.setColumns(10);
		textCostPrice.setVisible(false);

		lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostPrice.setBounds(438, 270, 96, 31);
		frame.getContentPane().add(lblCostPrice);
		lblCostPrice.setVisible(false);

		// Selling Price input field and label
		textSellingPrice = new JTextField();
		textSellingPrice.setBounds(574, 310, 128, 31);
		frame.getContentPane().add(textSellingPrice);
		textSellingPrice.setColumns(10);
		textSellingPrice.setVisible(false);

		lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSellingPrice.setBounds(438, 310, 96, 31);
		frame.getContentPane().add(lblSellingPrice);
		lblSellingPrice.setVisible(false);

		// MRP input field and label
		textMrp = new JTextField();
		textMrp.setBounds(574, 350, 128, 31);
		frame.getContentPane().add(textMrp);
		textMrp.setColumns(10);
		textMrp.setVisible(false);

		lblMrp = new JLabel("MRP");
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrp.setBounds(438, 350, 96, 31);
		frame.getContentPane().add(lblMrp);
		lblMrp.setVisible(false);

		

		btnSubmitAddProduct = new JButton("Submit");
		btnSubmitAddProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmitAddProduct.setBounds(617, 482, 85, 21);
		frame.getContentPane().add(btnSubmitAddProduct);
		btnSubmitAddProduct.setVisible(false);
		// Product Form Ended

		// Add Expense
		//		 Transaction Form Started
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

		
	
		textParticular = new JTextField();
		textParticular.setBounds(574, 159, 128, 31);
		frame.getContentPane().add(textParticular);
		textParticular.setColumns(10);
		textParticular.setVisible(false);
		
		
		lbParticular = new JLabel("Particular");
		lbParticular.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbParticular.setBounds(417, 159, 106, 31);
		frame.getContentPane().add(lbParticular);
		lbParticular.setVisible(false);
		
		textTransactionType = new JTextField();
		textTransactionType.setBounds(574, 210, 128, 31);
		frame.getContentPane().add(textTransactionType);
		textTransactionType.setColumns(10);
		textTransactionType.setVisible(false);
		
		lbTransactionType = new JLabel("Transaction type");
		lbTransactionType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTransactionType.setBounds(417, 210, 140, 31);
		frame.getContentPane().add(lbTransactionType);
		lbTransactionType.setVisible(false);
		
		
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
				// Collect product details from input fields
		        ProductService productService = new ProductService();
		        String id = textProductId.getText();
		        String category = textCategory.getText();
		        String name = textName.getText();
		        String description = textDescription.getText();
		        double costPrice = Double.parseDouble(textCostPrice.getText());
		        double sellingPrice = Double.parseDouble(textSellingPrice.getText());
		        double mrp = Double.parseDouble(textMrp.getText());

		        // Call service method to add product
		        String response = productService.addMyProduct(id, category, name, description, costPrice, sellingPrice, mrp);
		        
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
		
		btnSubmitAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionService transactionService = new TransactionService();
				String id = "";
				String particular = "";
				String transactionType = "";
				id = textTransactionId.getText();
				particular = textParticular.getText();
				transactionType = textTransactionType.getText();
				String response  = transactionService.addMyTransaction(id,particular,transactionType);
				lblStatus.setText(response);
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
		// Show all product input fields when "Add Product" is clicked
		lblOperation.setText("Add Product");
		textProductId.setVisible(true);
		lblProductId.setVisible(true);
		btnSubmitAddProduct.setVisible(true);		
		
//		lblOperation.setText("Add Product");
//		textProductId.setVisible(true);
//		lblProductId.setVisible(true);
//		btnSubmitAddProduct.setVisible(true);

		textCategory.setVisible(true);
		lblCategory.setVisible(true);

		textName.setVisible(true);
		lblName.setVisible(true);

		textDescription.setVisible(true);
		lblDescription.setVisible(true);

		textCostPrice.setVisible(true);
		lblCostPrice.setVisible(true);

		textSellingPrice.setVisible(true);
		lblSellingPrice.setVisible(true);

		textMrp.setVisible(true);
		lblMrp.setVisible(true);

		
	}
	
	private void showAddTransaction() {
		//Make the form visible
		lblOperation.setText("Add Transaction");
		textTransactionId.setVisible(true);
		lblTransactionId.setVisible(true);
		textParticular.setVisible(true);  
		lbParticular.setVisible(true); 
		textTransactionType.setVisible(true);
		lbTransactionType.setVisible(true);
		btnSubmitAddTransaction.setVisible(true);
	}

	private void hideAll() {
		
		// Hide all input fields and reset status
		lblStatus.setText("STATUS:");
		
		textProductId.setVisible(false);
		lblProductId.setVisible(false);
				
		
//		textTransactionId.setVisible(false);
//		lblTransactionId.setVisible(false);
//		btnSubmitAddTransaction.setVisible(false);	
		
//		textProductId.setVisible(false);
//		lblProductId.setVisible(false);
//		btnSubmitAddProduct.setVisible(false);

		textCategory.setVisible(false);
		lblCategory.setVisible(false);

		textName.setVisible(false);
		lblName.setVisible(false);

		textDescription.setVisible(false);
		lblDescription.setVisible(false);

		textCostPrice.setVisible(false);
		lblCostPrice.setVisible(false);

		textSellingPrice.setVisible(false);
		lblSellingPrice.setVisible(false);

		textMrp.setVisible(false);
		lblMrp.setVisible(false);
		btnSubmitAddProduct.setVisible(false);

		textTransactionId.setVisible(false);
		lblTransactionId.setVisible(false);
		lbParticular.setVisible(false);
		textParticular.setVisible(false);
		textTransactionType.setVisible(false);
		lbTransactionType.setVisible(false);
		btnSubmitAddTransaction.setVisible(false);

	}
}
