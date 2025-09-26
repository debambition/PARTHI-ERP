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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

import in.parthi.common.PaymentMode;
import in.parthi.common.Properties;
import in.parthi.common.TransactionCategory;
import in.parthi.common.TransactionType;
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
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {

	private JFrame frame;
	private JTabbedPane tabbedPane;

	//Add Product components
	private JTabbedPane paneAddProduct;
	JLabel lblOperation;
	JLabel lblAddPrdStatus;
	private JLabel lblReturnPrdStatus;
	JButton btnSubmitAddProduct;
	JLabel lblPrdCategory;
	private JLabel lblReturnCategory;
	private JTextField textName;
	private JTextField textReturnName;
	JLabel lblPrdName;
	private JLabel lblReturnName;
	private JTextField textDescription;
	private JTextField textReturnDescription;
	JLabel lblPrdDescription;
	private JLabel lblReturnDescription;
	JButton btnSubmitAddTransaction;
	JLabel lbParticular;
	JLabel lbTransactionType;
	private JPanel panelAddProduct;
	private JPanel panelAddTransaction;
	private JPanel panelAddSales;
	private JPanel panelProductReturn;
	private JPanel panelCustomer;
	private JLabel lblProductId;
	private JTextField textProductId;
	private JTextField textCategory;
	private JTextField textReturnCategory;
	private JTextField textCostPrice;
	private JTextField textReturnCostPrice;
	private JLabel lblCostPrice;
	private JLabel lblReturnCostPrice;
	private JLabel lblMrp;
	private JLabel lblReturnMrp;
	private JTextField textMrp;
	private JTextField textReturnMrp;
	private JLabel lblCheckInDate;
	private JDateChooser checkinDate;
	private JButton btnAddProduct;
	private JButton btnReturnProduct;
	private JDateChooser returnDate;

	//Transaction components
	private JTextField textTxnAmount;
	private JLabel lblAmount;
	private JTextField textTnxDescription;
	private JLabel lblTxnDescription;
	private JTextField textInvoice;
	private JLabel lblInvoice;
	private JTextField textParticular;
	private JLabel lblParticular;
	private JComboBox comboBoxTxnpaymentMode;
	private JLabel lblpaymentMode;	
	private JLabel lblHeaderAddProduct;
	private JLabel lblTxnCategory;
	private JComboBox comboBoxTxnCategory;
	private JLabel lblTxnType;
	private JComboBox comboBoxTxnType;
	private JLabel lblTxnDate;
	private JDateChooser txnDate;
	private JButton btnAddTransaction;
	private JLabel lblAddTxnStatus;

	//Return Product Component
	private JLabel lblReturnPrdID;
	private JTextField textReturnPrdId;

	//------------------------------------------
	LocalDate todayDateTime = LocalDate.now();
	Date today = Date.from(todayDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());

	ProductService productService = new ProductService();
	private JLabel lblNewLabel;
	private JLabel lblFinalPrdId;


	TransactionService transactionService = new TransactionService();
	private JTextField textReturnCheckinDate;
	private JLabel lblReturnDate;




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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Acceleratron\\IT-Induction\\parthi\\parthi-erp\\Codes\\UI\\Logistics\\src\\main\\resources\\image\\logo.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 794, 614);

		frame.getContentPane().setLayout(null);

		// Create JTabbedPane with vertical tab placement
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 0, 770, 570);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));

		frame.getContentPane().add(tabbedPane);

		//------------------Section for Add Products
		panelAddProduct = new JPanel();
		tabbedPane.addTab("Add Product", panelAddProduct);
		panelAddProduct.setLayout(null);
		//Adding all the components for add products in a function call.
		setAddProductForm();

		//------------------Section for Add Transaction
		panelAddTransaction = new JPanel();
		tabbedPane.addTab("Add Transaction", null, panelAddTransaction, null);
		panelAddTransaction.setLayout(null);
		//Adding all the components for add transaction in a function call.
		setAddTransaction();

		//------------------Sales Form

		panelAddSales = new JPanel();
		tabbedPane.addTab("Add Sales", null, panelAddSales, null);
		panelAddSales.setLayout(null);



		panelCustomer = new JPanel();
		tabbedPane.addTab("Customer Return", null, panelCustomer, null);
		panelCustomer.setLayout(null);

		//------------------Add return Form
		panelProductReturn = new JPanel();
		tabbedPane.addTab("Product Return", null, panelProductReturn, null);
		panelProductReturn.setLayout(null);
		returnProductForm();
		

	}

	//Creating the form for add products.
	private void setAddProductForm() {
		//------------------Product Form Started

		lblHeaderAddProduct = new JLabel("Add Product");
		lblHeaderAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderAddProduct.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeaderAddProduct.setBounds(55, 0, 286, 35);
		panelAddProduct.add(lblHeaderAddProduct);

		lblProductId = new JLabel("Product Id");
		lblProductId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
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

		lblFinalPrdId = new JLabel("");
		lblFinalPrdId.setBounds(382, 45, 72, 24);
		panelAddProduct.add(lblFinalPrdId);	

		lblPrdCategory = new JLabel("Category");
		lblPrdCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdCategory.setBounds(100, 79, 96, 24);
		panelAddProduct.add(lblPrdCategory);

		textCategory = new JTextField();
		textCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCategory.setColumns(10);
		textCategory.setBounds(207, 79, 147, 24);
		panelAddProduct.add(textCategory);

		lblPrdName = new JLabel("Name");
		lblPrdName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdName.setBounds(100, 113, 96, 28);
		panelAddProduct.add(lblPrdName);

		textName = new JTextField();
		textName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textName.setBounds(207, 113, 147, 28);
		panelAddProduct.add(textName);
		textName.setColumns(10);

		lblPrdDescription = new JLabel("Description");
		lblPrdDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdDescription.setBounds(100, 154, 96, 28);
		panelAddProduct.add(lblPrdDescription);

		textDescription = new JTextField();
		textDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textDescription.setColumns(10);
		textDescription.setBounds(207, 151, 147, 28);
		panelAddProduct.add(textDescription);

		lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostPrice.setBounds(100, 192, 96, 28);
		panelAddProduct.add(lblCostPrice);

		textCostPrice = new JTextField();
		textCostPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCostPrice.setColumns(10);
		textCostPrice.setBounds(207, 194, 147, 28);
		panelAddProduct.add(textCostPrice);

		lblMrp = new JLabel("MRP");
		lblMrp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrp.setBounds(100, 230, 96, 28);
		panelAddProduct.add(lblMrp);

		textMrp = new JTextField();
		textMrp.setFont(new Font("Dialog", Font.PLAIN, 12));
		textMrp.setColumns(10);
		textMrp.setBounds(207, 232, 147, 28);
		panelAddProduct.add(textMrp);

		lblCheckInDate = new JLabel("Checkin Date");
		lblCheckInDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCheckInDate.setBounds(81, 268, 115, 28);
		panelAddProduct.add(lblCheckInDate);

		checkinDate = new JDateChooser();
		checkinDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkinDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkinDate.setDateFormatString("dd-MM-yyyy");
		checkinDate.setDate(today);
		checkinDate.setBounds(207, 270, 147, 28);
		panelAddProduct.add(checkinDate);


		btnAddProduct = new JButton("Add Product");
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddProduct.setBounds(207, 342, 147, 28);
		panelAddProduct.add(btnAddProduct);

		lblAddPrdStatus = new JLabel("STATUS: ");
		lblAddPrdStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddPrdStatus.setBounds(25, 402, 492, 36);
		panelAddProduct.add(lblAddPrdStatus);

		//------------Add Product Form actions-------------------

		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Collect product details from input fields
				addProduct();
				frame.repaint();
			}
		});
	}

	private void addProduct() {
		try {
			Product	product = new Product();
			ProductService productService = new ProductService();
			String id = lblFinalPrdId.getText().toUpperCase();
			if (id == null || id.trim().length() <1)
				throw new Exception("Product ID cannot be blank");

			String category = textReturnCategory.getText().toUpperCase();
			if (category == null || category.trim().length() <1)
				throw new Exception("Product category cannot be blank");

			String name = textReturnName.getText().toUpperCase();
			if (name == null || name.trim().length() <1)
				throw new Exception("Product name cannot be blank");

			String description = textReturnDescription.getText();
			try {
				double costPrice = Double.parseDouble(textReturnCostPrice.getText());
				product.setCostPrice(costPrice);
			}catch(Exception e) {
				throw new Exception("Please enter a valid decimal number for Cost Price");
			}
			try {

				double mrp = Double.parseDouble(textReturnMrp.getText());
				product.setMrp(mrp);
			}catch(Exception e) {
				throw new Exception("Please enter a valid decimal number for MRP");
			}
			LocalDate stockinDate = checkinDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (stockinDate.isAfter(LocalDate.now())) {
				throw new Exception("Please Select a past date or today's date");
			}
			product.setStockInDate(stockinDate);
			product.setId(id);
			product.setCategory(category);
			product.setName(name);
			product.setDescription(description);
			product.setStatus(Properties.STATUS_AVAILABLE);


			String response = productService.addProduct(product);
			lblAddPrdStatus.setText("STATUS:: "+response);
			lblAddPrdStatus.setForeground(Color.BLACK);

			//Set the form to blank for new product
			textProductId.setText("");
			textReturnCategory.setText("");
			textReturnName.setText("");
			textReturnDescription.setText("");
			textReturnCostPrice.setText("");
			textReturnMrp.setText("");
			checkinDate.setDate(today);
			lblFinalPrdId.setText("");
		}catch (Exception e) {
			lblAddPrdStatus.setText("ERROR:: "+e.getLocalizedMessage());
			lblAddPrdStatus.setForeground(Color.RED);
		}
	}

	//Creating the form for add Transaction.
	private void setAddTransaction() {
		lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmount.setBounds(100, 79, 96, 24);
		panelAddTransaction.add(lblAmount);

		textTxnAmount = new JTextField();
		textTxnAmount.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTxnAmount.setColumns(10);
		textTxnAmount.setBounds(229, 79, 147, 24);
		panelAddTransaction.add(textTxnAmount);

		lblTxnDescription= new JLabel("Description");
		lblTxnDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTxnDescription.setBounds(100, 113, 96, 28);
		panelAddTransaction.add(lblTxnDescription);

		textTnxDescription = new JTextField();
		textTnxDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTnxDescription.setBounds(229, 116, 147, 28);
		panelAddTransaction.add(textTnxDescription);
		textName.setColumns(10);

		lblInvoice = new JLabel("Invoice");
		lblInvoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInvoice.setBounds(100, 154, 96, 28);
		panelAddTransaction.add(lblInvoice);

		textInvoice = new JTextField();
		textInvoice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textInvoice.setColumns(10);
		textInvoice.setBounds(229, 154, 147, 28);
		panelAddTransaction.add(textInvoice);

		lblParticular = new JLabel("Particular");
		lblParticular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParticular.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblParticular.setBounds(100, 192, 96, 28);
		panelAddTransaction.add(lblParticular);

		textParticular = new JTextField();
		textParticular.setFont(new Font("Dialog", Font.PLAIN, 12));
		textParticular.setColumns(10);
		textParticular.setBounds(229, 192, 147, 28);
		panelAddTransaction.add(textParticular);

		lblpaymentMode = new JLabel("Payment mode");
		lblpaymentMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpaymentMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblpaymentMode.setBounds(100, 230, 96, 28);
		panelAddTransaction.add(lblpaymentMode);

		comboBoxTxnpaymentMode = new JComboBox();
		comboBoxTxnpaymentMode.setModel(new DefaultComboBoxModel<>(PaymentMode.values()));
		comboBoxTxnpaymentMode.setBounds(229, 230, 147, 28);
		panelAddTransaction.add(comboBoxTxnpaymentMode);

		lblTxnCategory = new JLabel("Category");
		lblTxnCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTxnCategory.setBounds(100, 271, 96, 28);
		panelAddTransaction.add(lblTxnCategory);

		comboBoxTxnCategory = new JComboBox();
		comboBoxTxnCategory.setModel(new DefaultComboBoxModel<>(TransactionCategory.values()));
		comboBoxTxnCategory.setBounds(229, 271, 147, 28);
		panelAddTransaction.add(comboBoxTxnCategory);

		lblTxnType = new JLabel("Type");
		lblTxnType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTxnType.setBounds(100, 312, 96, 28);
		panelAddTransaction.add(lblTxnType);

		comboBoxTxnType = new JComboBox();
		comboBoxTxnType.setModel(new DefaultComboBoxModel<>(TransactionType.values()));
		comboBoxTxnType.setBounds(229, 314, 147, 24);
		panelAddTransaction.add(comboBoxTxnType);

		lblTxnDate = new JLabel("Transaction Date");
		lblTxnDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTxnDate.setBounds(58, 353, 138, 28);
		panelAddTransaction.add(lblTxnDate);

		txnDate = new JDateChooser();
		txnDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txnDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		txnDate.setDateFormatString("dd-MM-yyyy");
		txnDate.setDate(today);
		txnDate.setBounds(229, 353, 147, 28);
		panelAddTransaction.add(txnDate);


		btnAddTransaction = new JButton("Add Transaction");
		btnAddTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddTransaction.setBounds(229, 426, 147, 28);
		panelAddTransaction.add(btnAddTransaction);

		lblAddTxnStatus = new JLabel("STATUS: ");
		lblAddTxnStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddTxnStatus.setBounds(12, 481, 492, 36);
		panelAddTransaction.add(lblAddTxnStatus);

		//------------Add Transaction Form actions-------------------

		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Collect product details from input fields
				addTransaction();
				frame.repaint();
			}
		});
	}

	private void addTransaction() {
		TransactionService transactionService = new TransactionService();
		Transaction transaction = new Transaction();
		try {
			try {
				transaction.setAmount(Double.parseDouble(textTxnAmount.getText()));
			}catch(Exception ex) {
				throw new Exception("Please enter a valid decimal value for Transaction Amount");
			}
			if(textTnxDescription.getText() == null || textTnxDescription.getText().trim().length() < 1)
				throw new Exception("Description cannot be blank");
			transaction.setDescription(textTnxDescription.getText());	
			transaction.setInvoice(textInvoice.getText());
			transaction.setParticular(textParticular.getText());
			transaction.setPaymentMode(comboBoxTxnpaymentMode.getSelectedItem().toString());
			transaction.setTxnCategory(comboBoxTxnCategory.getSelectedItem().toString());
			transaction.setTxnType(comboBoxTxnType.getSelectedItem().toString());
			LocalDate transactionDate = txnDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (transactionDate.isAfter(LocalDate.now())) {
				throw new Exception("Please Select a past date or today's date");
			}
			transaction.setTransactionDate(transactionDate);

			String response  = transactionService.addTransaction(transaction);
			lblAddTxnStatus.setText("STATUS: " +response);
			lblAddTxnStatus.setForeground(Color.BLACK);

			//Reset the transaction form
			textTxnAmount.setText("");
			textTnxDescription.setText("");
			textInvoice.setText("");
			textParticular.setText("");
			txnDate.setDate(today);
		}catch (Exception exception) {
			lblAddTxnStatus.setText("ERROR:: "+exception.getLocalizedMessage());
			lblAddTxnStatus.setForeground(Color.RED);
		}
	}

	private void returnProductForm() {
		lblReturnPrdID = new JLabel("Product ID");
		lblReturnPrdID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnPrdID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReturnPrdID.setBounds(87, 107, 96, 24);
		panelProductReturn.add(lblReturnPrdID);

		textReturnPrdId = new JTextField();
		textReturnPrdId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnPrdId.setColumns(10);
		textReturnPrdId.setBounds(207, 107, 147, 24);
		panelProductReturn.add(textReturnPrdId);

		lblReturnCategory = new JLabel("Category");
		lblReturnCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnCategory.setBounds(87, 138, 96, 24);
		panelProductReturn.add(lblReturnCategory);

		textReturnCategory = new JTextField();
		textReturnCategory.setEditable(false);
		textReturnCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCategory.setColumns(10);
		textReturnCategory.setBounds(207, 139, 147, 24);
		panelProductReturn.add(textReturnCategory);

		lblReturnName = new JLabel("Name");
		lblReturnName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnName.setBounds(87, 175, 96, 28);
		panelProductReturn.add(lblReturnName);

		textReturnName = new JTextField();
		textReturnName.setEditable(false);
		textReturnName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnName.setBounds(207, 175, 147, 28);
		panelProductReturn.add(textReturnName);
		textReturnName.setColumns(10);

		lblReturnDescription = new JLabel("Description");
		lblReturnDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnDescription.setBounds(87, 216, 96, 28);
		panelProductReturn.add(lblReturnDescription);

		textReturnDescription = new JTextField();
		textReturnDescription.setEditable(false);
		textReturnDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnDescription.setColumns(10);
		textReturnDescription.setBounds(207, 217, 147, 28);
		panelProductReturn.add(textReturnDescription);

		lblReturnCostPrice = new JLabel("Cost Price");
		lblReturnCostPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnCostPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnCostPrice.setBounds(87, 257, 96, 28);
		panelProductReturn.add(lblReturnCostPrice);

		textReturnCostPrice = new JTextField();
		textReturnCostPrice.setEditable(false);
		textReturnCostPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCostPrice.setColumns(10);
		textReturnCostPrice.setBounds(207, 258, 147, 28);
		panelProductReturn.add(textReturnCostPrice);

		lblReturnMrp = new JLabel("MRP");
		lblReturnMrp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnMrp.setBounds(87, 298, 96, 28);
		panelProductReturn.add(lblReturnMrp);

		textReturnMrp = new JTextField();
		textReturnMrp.setEditable(false);
		textReturnMrp.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnMrp.setColumns(10);
		textReturnMrp.setBounds(207, 299, 147, 28);
		panelProductReturn.add(textReturnMrp);

		lblCheckInDate = new JLabel("Checkin Date");
		lblCheckInDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCheckInDate.setBounds(70, 339, 115, 28);
		panelProductReturn.add(lblCheckInDate);


		btnReturnProduct = new JButton("Return Product");
		btnReturnProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReturnProduct.setBounds(207, 434, 147, 28);
		panelProductReturn.add(btnReturnProduct);

		lblReturnPrdStatus = new JLabel("STATUS: ");
		lblReturnPrdStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReturnPrdStatus.setBounds(28, 496, 492, 36);
		panelProductReturn.add(lblReturnPrdStatus);

		textReturnCheckinDate = new JTextField();
		textReturnCheckinDate.setEditable(false);
		textReturnCheckinDate.setText((String) null);
		textReturnCheckinDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCheckinDate.setColumns(10);
		textReturnCheckinDate.setBounds(207, 340, 147, 28);
		panelProductReturn.add(textReturnCheckinDate);

		lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnDate.setBounds(70, 390, 115, 28);
		panelProductReturn.add(lblReturnDate);
		
		returnDate = new JDateChooser();
		returnDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		returnDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		returnDate.setDateFormatString("dd-MM-yyyy");
		returnDate.setDate(today);
		returnDate.setBounds(207, 393, 147, 28);
		panelProductReturn.add(returnDate);
		
		textReturnPrdId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				getReturnProductDetails();
				frame.repaint();
			}
		});
	}
	
	private void getReturnProductDetails() {
		Product product = productService.getProduct(textReturnPrdId.getText().toUpperCase());
		if(product == null) {
			lblReturnPrdStatus.setText("STATUS: Product with ID "+textReturnPrdId.getText().toUpperCase()+" not found");
		}else {
			textReturnCategory.setText(product.getCategory());
			textReturnName.setText(product.getName());
			textReturnDescription.setText(product.getDescription());
			textReturnCostPrice.setText(Double.toString(product.getCostPrice()));
			textReturnMrp.setText(Double.toString(product.getMrp()));
			textReturnCheckinDate.setText(product.getStockInDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lblReturnPrdStatus.setText("STATUS: "+product.getStatus());
		}
		
		
	}
}

