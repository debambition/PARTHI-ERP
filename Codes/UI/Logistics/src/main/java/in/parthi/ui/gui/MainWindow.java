package in.parthi.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

import in.parthi.core.model.product.Product;
import in.parthi.core.model.transaction.Transaction;
import in.parthi.core.service.ProductService;
import in.parthi.core.service.TransactionService;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainWindow {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JTabbedPane paneAddProduct;
	JLabel lblOperation;
	JLabel lblStatus;
	JButton btnSubmitAddProduct;
	JLabel lblCategory;

	private JTextField textName;
	JLabel lblName;

	private JTextField textDescription;
	JLabel lblDescription;
	JButton btnSubmitAddTransaction;
	JLabel lbParticular;
	JLabel lbTransactionType;
	private JPanel panelAddProduct;
	private JPanel panelAddExpense;
	private JPanel panelAddSales;
	private JPanel panelProductReturn;
	private JPanel panelCustomer;
	private JLabel lblProductId;
	private JTextField textProductId;
	private JTextField textCategory;
	private JTextField textCostPrice;
	private JLabel lblCostPrice;
	private JLabel lblMrp;
	private JTextField textMrp;
	private JLabel lblCheckInDate;
	
	LocalDate today = LocalDate.now();
	
	//Add transaction form
	private JTextField textTransactionId;
	private JLabel lblTransactionId;
	private JTextField textAmount;
	private JLabel lblAmount;



	ProductService productService = new ProductService();
	private JTextField textCheckInDate;
	private JLabel lblNewLabel;
	private JLabel lblFinalPrdId;
	
	
	TransactionService transactionService = new TransactionService();
	

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
		frame = new JFrame("Parthi Logistics");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 794, 614);
	
		frame.getContentPane().setLayout(null);

		// Create JTabbedPane with vertical tab placement
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 0, 780, 570);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));

		frame.getContentPane().add(tabbedPane);

		paneAddProduct = new JTabbedPane(JTabbedPane.TOP);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		
		panelAddProduct = new JPanel();
		tabbedPane.addTab("Add Product", panelAddProduct);
		panelAddProduct.setLayout(null);
		//------------------Product Form Started
		
		lblProductId = new JLabel("Product Id");
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProductId.setBounds(100, 45, 96, 24);
		panelAddProduct.add(lblProductId);
		
		textProductId = new JTextField();
		textProductId.setToolTipText("Give Product ID in format:  \"SA-\"");
		textProductId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String prdId = productService.getNextProductId(textProductId.getText().toUpperCase());
				lblFinalPrdId.setText(prdId);
				frame.repaint();
			}
		});
		textProductId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textProductId.setBounds(207, 45, 147, 24);
		panelAddProduct.add(textProductId);
		textProductId.setColumns(10);
		
		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(100, 79, 96, 24);
		panelAddProduct.add(lblCategory);
		
		textCategory = new JTextField();
		textCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCategory.setColumns(10);
		textCategory.setBounds(207, 79, 147, 24);
		panelAddProduct.add(textCategory);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(100, 113, 96, 28);
		panelAddProduct.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textName.setBounds(207, 113, 147, 28);
		panelAddProduct.add(textName);
		textName.setColumns(10);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescription.setBounds(100, 154, 96, 28);
		panelAddProduct.add(lblDescription);
		
		textDescription = new JTextField();
		textDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textDescription.setColumns(10);
		textDescription.setBounds(207, 151, 147, 28);
		panelAddProduct.add(textDescription);
		
		lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCostPrice.setBounds(100, 192, 96, 28);
		panelAddProduct.add(lblCostPrice);
		
		textCostPrice = new JTextField();
		textCostPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCostPrice.setColumns(10);
		textCostPrice.setBounds(207, 194, 147, 28);
		panelAddProduct.add(textCostPrice);
		
		lblMrp = new JLabel("MRP");
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMrp.setBounds(100, 230, 96, 28);
		panelAddProduct.add(lblMrp);
		
		textMrp = new JTextField();
		textMrp.setFont(new Font("Dialog", Font.PLAIN, 12));
		textMrp.setColumns(10);
		textMrp.setBounds(207, 232, 147, 28);
		panelAddProduct.add(textMrp);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddProduct.setBounds(207, 321, 147, 28);
		panelAddProduct.add(btnAddProduct);
		
		lblCheckInDate = new JLabel("Checkin Date");
		lblCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCheckInDate.setBounds(100, 268, 96, 28);
		panelAddProduct.add(lblCheckInDate);
		
		textCheckInDate = new JTextField();
		textCheckInDate.setEditable(false);
		textCheckInDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCheckInDate.setColumns(10);
		textCheckInDate.setBounds(207, 270, 96, 28);
		textCheckInDate.setText(today.format(formatter));
		panelAddProduct.add(textCheckInDate);
		
		lblStatus = new JLabel("STATUS: ");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(25, 402, 492, 36);
		panelAddProduct.add(lblStatus);
		
		lblFinalPrdId = new JLabel("");
		lblFinalPrdId.setBounds(382, 45, 72, 24);
		panelAddProduct.add(lblFinalPrdId);
		
		
		
		//------------------Product Form Ended
		
		panelAddExpense = new JPanel();
		tabbedPane.addTab("Add Expense", null, panelAddExpense, null);
		panelAddExpense.setLayout(null);

		//------------------Transaction Form started
		
		lblTransactionId = new JLabel("Transaction Id");
		lblTransactionId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransactionId.setBounds(100, 45, 96, 24);
		panelAddExpense.add(lblTransactionId);

		textTransactionId = new JTextField();
		textTransactionId.setToolTipText("Transaction id is auto generated");
//		textProductId.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String prdId = productService.getNextProductId(textProductId.getText().toUpperCase());
//				lblFinalPrdId.setText(prdId);
//				frame.repaint();
//			}
//		});
		textTransactionId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTransactionId.setBounds(207, 45, 147, 24);
		panelAddExpense.add(textTransactionId);
		textTransactionId.setColumns(10);
		
		lblStatus = new JLabel("STATUS: ");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(25, 402, 492, 36);
		panelAddExpense.add(lblStatus);
		
		JButton btnAddTransaction = new JButton("Add Transaction");
		btnAddTransaction.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddTransaction.setBounds(207, 321, 147, 28);
		panelAddExpense.add(btnAddTransaction);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmount.setBounds(100, 79, 96, 24);
		panelAddExpense.add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setFont(new Font("Dialog", Font.PLAIN, 12));
		textAmount.setColumns(10);
		textAmount.setBounds(207, 79, 147, 24);
		panelAddExpense.add(textAmount);
		
		//------------------Transaction Form Ended
		
		panelAddSales = new JPanel();
		tabbedPane.addTab("Add Sales", null, panelAddSales, null);
		panelAddSales.setLayout(null);
		
		panelProductReturn = new JPanel();
		tabbedPane.addTab("Product Return", null, panelProductReturn, null);
		panelProductReturn.setLayout(null);
		
		panelCustomer = new JPanel();
		tabbedPane.addTab("Customer Return", null, panelCustomer, null);
		panelCustomer.setLayout(null);
		
		
		//------------Add Product Form actions-------------------
		
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Collect product details from input fields
		        ProductService productService = new ProductService();
		        String id = textProductId.getText();
		        String category = textCategory.getText();
		        String name = textName.getText();
		        String description = textDescription.getText();
		        double costPrice = Double.parseDouble(textCostPrice.getText());
		        double mrp = Double.parseDouble(textMrp.getText());
		        
		        Product	product = new Product();
		        product.setStockInDate(today);
		        product.setId(id);
		        product.setCategory(category);
		        product.setName(name);
		        product.setDescription(description);
		        product.setCostPrice(costPrice);		        
		        product.setMrp(mrp);
		        
		        String response = productService.addProduct(product);
		        lblStatus.setText("STATUS: "+response);

			}
			
			//------------Add Product Form actions end-------------------
			
		});
		
		
		
		//------------Add Transaction Form actions-------------------
		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionService transactionService = new TransactionService();
				String id = "";
//				String particular = "";
//				String transactionType = "";
//				String paymentMode = "";
//				String category ="";
				double amount =0;
				id = textTransactionId.getText();
//				particular = textParticular.getText();
//				transactionType = textTransactionType.getText();
//				paymentMode = textPaymentMode.getText();
//				category = textTransactionCategory.getText();
				amount = Double.parseDouble(textAmount.getText());
				
				Transaction transaction = new Transaction();
				transaction.setId(id);
				
				String response  = transactionService.addTransaction(transaction);
				lblStatus.setText("STATUS: " +response);
			}
		});
	}
}
