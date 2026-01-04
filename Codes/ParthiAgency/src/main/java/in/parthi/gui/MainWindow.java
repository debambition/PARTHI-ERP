package in.parthi.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Color;

import in.parthi.common.PaymentMode;
import in.parthi.common.Properties;
import in.parthi.common.TransactionCategory;
import in.parthi.common.TransactionType;
import in.parthi.core.model.AddProductTransaction;
import in.parthi.core.model.Customer;
import in.parthi.core.model.Product;
import in.parthi.core.model.Transaction;
import in.parthi.core.service.CustomerService;
import in.parthi.core.service.PaymentService;
import in.parthi.core.service.ProductService;
import in.parthi.core.service.TransactionService;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class MainWindow {
	private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);

	private JFrame frame;
	// Add Product components
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
	private JPanel panelProductReturn;
	private JPanel panelCustomerReturn;
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
	private JComboBox comboBoxReturnpaymentMode;
	private JButton btnGetPrd;
	private JLabel lblFinalPrdId;
	private JComboBox comboBoxAddPrdpayMode;

	// Transaction components
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
	private JLabel lblReturnpaymentMode;
	private JTextField textReturnCheckinDate;
	private JLabel lblReturnDate;
	private JTextField textCustomerNumber;
	private JTextField textCustomerName;
	private JTextField textCustomerAddress;
	private JLabel lblCustomerId;
	private JLabel lblCustomerName;
	private JLabel lblCustomerAddress;
	private JLabel lblTxnSellingPrice;
	private JTextField textTxnSellingPrice;
	private JTextField textDeposite;
	private JLabel lblDeposite;

	// Return Product Component
	private JLabel lblReturnPrdID;
	private JTextField textReturnPrdId;
	private JTextField textCustReturnProductId;
	private JTextField textCustReturnCategory;
	private JTextField textCustReturnPrdName;
	private JTextField textCustReturnDescription;
	private JTextField textCustReturnPaid;
	private JLabel lblCustReturnStatus;
	private JTextField textCustReturnStatus;

	// ------------------------------------------
	LocalDate todayDateTime = LocalDate.now();
	Date today = Date.from(todayDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());

	ProductService productService = new ProductService();
	TransactionService transactionService = new TransactionService();
	CustomerService customerService = new CustomerService();
	PaymentService paymentService = new PaymentService();
	Customer customer;
	private JTextField textProductInvoice;
	private JLabel lblVendor;
	private JTextField textVendor;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Thread workerThread = new Thread(() -> {
			Properties.getDBConnection();
		});
		workerThread.start();

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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Will run before JVM exits
				Properties.closeDBConnection();
				logger.info("Application is closed after closing the DB connections");
			}
		});

		frame.getContentPane().setLayout(null);

		// Create JTabbedPane with vertical tab placement
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBackground(new Color(185, 156, 145));
		tabbedPane.setBounds(0, 0, 770, 570);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));

		frame.getContentPane().add(tabbedPane);

		// ------------------Section for Add Transaction
		panelAddTransaction = new JPanel();
		panelAddTransaction.setBackground(new Color(216, 166, 133));
		panelAddTransaction.setForeground(Color.GRAY);
		tabbedPane.addTab("Add Transaction", null, panelAddTransaction, "Add any Transactions");
		tabbedPane.setBackgroundAt(0, new Color(192, 192, 192));
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		panelAddTransaction.setLayout(null);
		setAddTransactionForm();

		// ------------------Section for Add Products
		panelAddProduct = new JPanel();
		panelAddProduct.setBackground(new Color(216, 166, 133));
		tabbedPane.addTab("Add Product", panelAddProduct);
		tabbedPane.setBackgroundAt(1, new Color(192, 192, 192));
		tabbedPane.setForegroundAt(1, new Color(0, 0, 0));
		panelAddProduct.setLayout(null);
		setAddProductForm();

		// ------------------Add Product return Form
		panelProductReturn = new JPanel();
		panelProductReturn.setBackground(new Color(216, 166, 133));
		tabbedPane.addTab("Product Return", null, panelProductReturn, null);
		tabbedPane.setBackgroundAt(2, new Color(192, 192, 192));
		panelProductReturn.setLayout(null);
		returnProductForm();

		// ------------------Add Customer Product return Form
		panelCustomerReturn = new JPanel();
		panelCustomerReturn.setBackground(new Color(216, 166, 133));
		tabbedPane.addTab("Customer Return", null, panelCustomerReturn, null);
		tabbedPane.setBackgroundAt(3, new Color(192, 192, 192));
		tabbedPane.setForegroundAt(3, new Color(0, 0, 0));
		panelCustomerReturn.setLayout(null);
		setCustomerReturnForm();

	}

	// Creating the form for add products.
	private void setAddProductForm() {
		// ------------------Product Form Started

		lblHeaderAddProduct = new JLabel("Add Product");
		lblHeaderAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderAddProduct.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeaderAddProduct.setBounds(160, 11, 286, 35);
		panelAddProduct.add(lblHeaderAddProduct);

		lblProductId = new JLabel("Product Id");
		lblProductId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProductId.setBounds(179, 117, 96, 24);
		panelAddProduct.add(lblProductId);

		textProductId = new JTextField();
		textProductId.setToolTipText("Give Product ID in format:  \"SA-\"");
		
		textProductId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textProductId.setBounds(286, 117, 147, 24);
		panelAddProduct.add(textProductId);
		textProductId.setColumns(10);

		lblFinalPrdId = new JLabel("");
		lblFinalPrdId.setBounds(443, 117, 72, 24);
		panelAddProduct.add(lblFinalPrdId);

		lblPrdCategory = new JLabel("Category");
		lblPrdCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdCategory.setBounds(179, 151, 96, 24);
		panelAddProduct.add(lblPrdCategory);

		textCategory = new JTextField();
		textCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCategory.setColumns(10);
		textCategory.setBounds(286, 151, 147, 24);
		panelAddProduct.add(textCategory);

		lblPrdName = new JLabel("Name");
		lblPrdName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdName.setBounds(179, 185, 96, 28);
		panelAddProduct.add(lblPrdName);

		textName = new JTextField();
		textName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textName.setBounds(286, 185, 147, 28);
		panelAddProduct.add(textName);
		textName.setColumns(10);

		lblPrdDescription = new JLabel("Description");
		lblPrdDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrdDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrdDescription.setBounds(179, 226, 96, 28);
		panelAddProduct.add(lblPrdDescription);

		textDescription = new JTextField();
		textDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textDescription.setColumns(10);
		textDescription.setBounds(286, 223, 147, 28);
		panelAddProduct.add(textDescription);

		lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostPrice.setBounds(179, 264, 96, 28);
		panelAddProduct.add(lblCostPrice);

		textCostPrice = new JTextField();
		textCostPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCostPrice.setColumns(10);
		textCostPrice.setBounds(286, 266, 147, 28);
		panelAddProduct.add(textCostPrice);

		lblMrp = new JLabel("MRP");
		lblMrp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrp.setBounds(179, 302, 96, 28);
		panelAddProduct.add(lblMrp);

		textMrp = new JTextField();
		textMrp.setFont(new Font("Dialog", Font.PLAIN, 12));
		textMrp.setColumns(10);
		textMrp.setBounds(286, 304, 147, 28);
		panelAddProduct.add(textMrp);

		lblCheckInDate = new JLabel("Checkin Date");
		lblCheckInDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCheckInDate.setBounds(160, 387, 115, 28);
		panelAddProduct.add(lblCheckInDate);

		checkinDate = new JDateChooser();
		checkinDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkinDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkinDate.setDateFormatString("dd-MM-yyyy");
		checkinDate.setDate(today);
		checkinDate.setBounds(286, 389, 147, 28);
		panelAddProduct.add(checkinDate);


		btnAddProduct = new JButton("Add Product");
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddProduct.setBounds(286, 465, 147, 28);
		panelAddProduct.add(btnAddProduct);

		lblAddPrdStatus = new JLabel("STATUS: ");
		lblAddPrdStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddPrdStatus.setBounds(26, 503, 603, 36);
		panelAddProduct.add(lblAddPrdStatus);

		JLabel lblpaymentMode_1 = new JLabel("Payment mode");
		lblpaymentMode_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpaymentMode_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpaymentMode_1.setBounds(90, 349, 163, 28);
		panelAddProduct.add(lblpaymentMode_1);

		comboBoxAddPrdpayMode = new JComboBox();
		comboBoxAddPrdpayMode.setModel(new DefaultComboBoxModel<>(PaymentMode.values()));
		comboBoxAddPrdpayMode.setBounds(286, 349, 147, 28);
		panelAddProduct.add(comboBoxAddPrdpayMode);
		// ------------Add Product Form actions-------------------

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
			Product product = new Product();
			ProductService productService = new ProductService();
			String id = textProductId.getText().toUpperCase();
			if (id == null || id.trim().length() < 1)
				throw new Exception("Product ID cannot be blank");

			String category = textCategory.getText().toUpperCase();
			if (category == null || category.trim().length() < 1)
				throw new Exception("Product category cannot be blank");

			String name = textName.getText().toUpperCase();
			if (name == null || name.trim().length() < 1)
				throw new Exception("Product name cannot be blank");

			String description = textDescription.getText();
			try {
				double costPrice = Double.parseDouble(textCostPrice.getText());
				product.setCostPrice(costPrice);
			} catch (Exception e) {
				throw new Exception("Please enter a valid decimal number for Cost Price");
			}
			try {

				double mrp = Double.parseDouble(textMrp.getText());
				product.setMrp(mrp);
			} catch (Exception e) {
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

			lblAddPrdStatus.setText("STATUS:: " + response);
			lblAddPrdStatus.setForeground(Color.BLACK);

			// Set the form to blank for new product
			resetAddProductForm();

		} catch (Exception e) {
			lblAddPrdStatus.setText("ERROR:: " + e.getLocalizedMessage());
			lblAddPrdStatus.setForeground(Color.RED);
		}
	}

	// Creating the form for add Transaction.
	private void setAddTransactionForm() {
		lblAmount = new JLabel("Amount Paid");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAmount.setBounds(109, 321, 153, 24);
		panelAddTransaction.add(lblAmount);

		textTxnAmount = new JTextField();
		textTxnAmount.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTxnAmount.setColumns(10);
		textTxnAmount.setBounds(295, 322, 147, 24);
		panelAddTransaction.add(textTxnAmount);

		lblTxnDescription = new JLabel("Description");
		lblTxnDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTxnDescription.setBounds(166, 254, 96, 28);
		panelAddTransaction.add(lblTxnDescription);

		textTnxDescription = new JTextField();
		textTnxDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				switch (comboBoxTxnCategory.getSelectedItem().toString()) {
					case "TRAVEL":
						break;
					case "FOOD":
						break;
					case "SALARY":
						if (textParticular.getText().trim().length() > 0)
							textTnxDescription.setText("Salary paid to " + textParticular.getText() + " for month " + LocalDate.now().getMonth());
						break;
					case "OPERATIONAL COST":
						break;
					case "PRODUCT COST":
						break;
					case "ACCESSORY":
						break;
					case "ADVERTISEMENT":
						break;
					case "CAMPAIGN COST":
						break;
					case "MISC COST":
						break;
					case "INVESTMENT RETURN":
						break;
					case "BANK COST":
						break;
					case "INSTALLMENT":
						if (textParticular.getText().trim().length() > 0)
							textTnxDescription.setText("Installment for " + textParticular.getText().toUpperCase() + " by " + textCustomerName.getText());
						break;
					case "SALES":
						if (textParticular.getText().trim().length() > 0)
							textTnxDescription.setText("Product with ID " + textParticular.getText().toUpperCase() + " Sold to " + textCustomerName.getText());
						break;
					case "PRODUCT RETURN":
						break;
					case "INVESTMENT":
						break;
					default:
				}

			}
		});
		textTnxDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTnxDescription.setBounds(295, 256, 334, 24);
		panelAddTransaction.add(textTnxDescription);

		lblInvoice = new JLabel("Invoice");
		lblInvoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInvoice.setBounds(166, 192, 96, 24);
		panelAddTransaction.add(lblInvoice);

		textInvoice = new JTextField();
		textInvoice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textInvoice.setColumns(10);
		textInvoice.setBounds(295, 190, 147, 22);
		panelAddTransaction.add(textInvoice);

		lblParticular = new JLabel("Particular");
		lblParticular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParticular.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblParticular.setBounds(166, 220, 96, 28);
		panelAddTransaction.add(lblParticular);

		textParticular = new JTextField();
		textParticular.setFont(new Font("Dialog", Font.PLAIN, 12));
		textParticular.setColumns(10);
		textParticular.setBounds(295, 222, 147, 24);
		panelAddTransaction.add(textParticular);

		lblpaymentMode = new JLabel("Payment mode");
		lblpaymentMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpaymentMode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpaymentMode.setBounds(99, 356, 163, 28);
		panelAddTransaction.add(lblpaymentMode);

		comboBoxTxnpaymentMode = new JComboBox();
		comboBoxTxnpaymentMode.setModel(new DefaultComboBoxModel<>(PaymentMode.values()));
		comboBoxTxnpaymentMode.setBounds(295, 356, 147, 28);
		panelAddTransaction.add(comboBoxTxnpaymentMode);

		lblTxnCategory = new JLabel("Category");
		lblTxnCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTxnCategory.setBounds(166, 48, 96, 28);
		panelAddTransaction.add(lblTxnCategory);

		comboBoxTxnCategory = new JComboBox();
		comboBoxTxnCategory.setModel(new DefaultComboBoxModel<>(TransactionCategory.getLabels()));
		comboBoxTxnCategory.setBounds(295, 48, 147, 28);
		panelAddTransaction.add(comboBoxTxnCategory);

		lblTxnType = new JLabel("Type");
		lblTxnType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTxnType.setBounds(166, 395, 96, 28);
		panelAddTransaction.add(lblTxnType);

		comboBoxTxnType = new JComboBox();
		comboBoxTxnType.setModel(new DefaultComboBoxModel<>(TransactionType.values()));
		comboBoxTxnType.setBounds(295, 398, 147, 24);
		comboBoxTxnType.setSelectedItem(TransactionType.DEBIT);
		panelAddTransaction.add(comboBoxTxnType);

		lblTxnDate = new JLabel("Transaction Date");
		lblTxnDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTxnDate.setBounds(124, 436, 138, 28);
		panelAddTransaction.add(lblTxnDate);

		txnDate = new JDateChooser();
		txnDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txnDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		txnDate.setDateFormatString("dd-MM-yyyy");
		txnDate.setDate(today);
		txnDate.setBounds(295, 436, 147, 28);
		panelAddTransaction.add(txnDate);


		btnAddTransaction = new JButton("Add Transaction");
		btnAddTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddTransaction.setBounds(295, 474, 218, 28);

		panelAddTransaction.add(btnAddTransaction);

		lblAddTxnStatus = new JLabel("STATUS: ");
		lblAddTxnStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddTxnStatus.setBounds(10, 514, 619, 36);
		panelAddTransaction.add(lblAddTxnStatus);


		lblCustomerId = new JLabel("Customer Mobile Number");
		lblCustomerId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerId.setBounds(89, 86, 173, 24);
		lblCustomerId.setVisible(false);
		panelAddTransaction.add(lblCustomerId);

		textCustomerNumber = new JTextField();
		textCustomerNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustomerNumber.setColumns(10);
		textCustomerNumber.setBounds(295, 86, 147, 24);
		textCustomerNumber.setVisible(false);
		panelAddTransaction.add(textCustomerNumber);

		lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerName.setBounds(99, 121, 163, 24);
		lblCustomerName.setVisible(false);
		panelAddTransaction.add(lblCustomerName);

		textCustomerName = new JTextField();
		textCustomerName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustomerName.setColumns(10);
		textCustomerName.setBounds(295, 121, 147, 24);
		textCustomerName.setVisible(false);
		panelAddTransaction.add(textCustomerName);

		lblCustomerAddress = new JLabel("Customer Address");
		lblCustomerAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerAddress.setBounds(124, 156, 138, 24);
		lblCustomerAddress.setVisible(false);
		panelAddTransaction.add(lblCustomerAddress);

		textCustomerAddress = new JTextField();
		textCustomerAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustomerAddress.setColumns(10);
		textCustomerAddress.setBounds(295, 156, 147, 24);
		textCustomerAddress.setVisible(false);
		panelAddTransaction.add(textCustomerAddress);

		lblTxnSellingPrice = new JLabel("Selling Price");
		lblTxnSellingPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxnSellingPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTxnSellingPrice.setBounds(138, 287, 124, 24);
		lblTxnSellingPrice.setVisible(false);
		panelAddTransaction.add(lblTxnSellingPrice);

		textTxnSellingPrice = new JTextField();
		textTxnSellingPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTxnSellingPrice.setColumns(10);
		textTxnSellingPrice.setBounds(295, 288, 147, 24);
		textTxnSellingPrice.setVisible(false);
		panelAddTransaction.add(textTxnSellingPrice);

		lblDeposite = new JLabel("Deposite");
		lblDeposite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeposite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDeposite.setBounds(452, 321, 71, 24);
		lblDeposite.setVisible(false);
		panelAddTransaction.add(lblDeposite);

		textDeposite = new JTextField();
		textDeposite.setEditable(false);
		textDeposite.setFont(new Font("Dialog", Font.PLAIN, 12));
		textDeposite.setColumns(10);
		textDeposite.setBounds(533, 321, 96, 24);
		textDeposite.setVisible(false);
		panelAddTransaction.add(textDeposite);

		// ------------Add Transaction Form actions-------------------

		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Collect product details from input fields
				addTransaction();
				frame.repaint();
			}
		});

		comboBoxTxnCategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (comboBoxTxnCategory.getSelectedItem().toString()) {
					case "SALARY":
					case "TRAVEL":
					case "FOOD":
					case "OPERATIONAL COST":
					case "PRODUCT COST":
					case "ACCESSORY":
					case "ADVERTISEMENT":
					case "CAMPAIGN COST":
					case "MISC COST":
					case "INVESTMENT RETURN":
					case "BANK COST":
						textCustomerAddress.setVisible(false);
						lblCustomerAddress.setVisible(false);
						textCustomerName.setVisible(false);
						lblCustomerName.setVisible(false);
						textCustomerNumber.setVisible(false);
						lblCustomerId.setVisible(false);
						lblTxnSellingPrice.setVisible(false);
						textTxnSellingPrice.setVisible(false);
						lblParticular.setText("Particulars");
						lblDeposite.setVisible(false);
						textDeposite.setVisible(false);
						comboBoxTxnType.setSelectedItem(TransactionType.DEBIT);
						break;
					case "INSTALLMENT":
						textCustomerNumber.setVisible(true);
						lblCustomerId.setVisible(true);
						lblTxnSellingPrice.setVisible(false);
						textTxnSellingPrice.setVisible(false);
						textCustomerAddress.setVisible(true);
						textCustomerAddress.setEditable(false);
						lblCustomerAddress.setVisible(true);
						textCustomerName.setVisible(true);
						textCustomerName.setEditable(false);
						lblCustomerName.setVisible(true);
						lblParticular.setText("Product Id");
						lblDeposite.setVisible(false);
						textDeposite.setVisible(false);
						comboBoxTxnType.setSelectedItem(TransactionType.CREDIT);
						break;
					case "SALES":
						textCustomerNumber.setVisible(true);
						lblCustomerId.setVisible(true);
						lblTxnSellingPrice.setVisible(true);
						textTxnSellingPrice.setVisible(true);
						textCustomerAddress.setVisible(true);
						textCustomerAddress.setEditable(false);
						lblCustomerAddress.setVisible(true);
						textCustomerName.setVisible(true);
						textCustomerName.setEditable(false);
						lblCustomerName.setVisible(true);
						lblParticular.setText("Product Id");
						lblDeposite.setVisible(false);
						textDeposite.setVisible(false);
						comboBoxTxnType.setSelectedItem(TransactionType.CREDIT);
						break;
					case "PRODUCT RETURN":
					case "INVESTMENT":
						lblTxnSellingPrice.setVisible(false);
						textTxnSellingPrice.setVisible(false);
						textCustomerAddress.setVisible(false);
						lblCustomerAddress.setVisible(false);
						textCustomerName.setVisible(false);
						lblCustomerName.setVisible(false);
						textCustomerNumber.setVisible(false);
						lblCustomerId.setVisible(false);
						lblParticular.setText("Particulars");
						lblDeposite.setVisible(false);
						textDeposite.setVisible(false);
						comboBoxTxnType.setSelectedItem(TransactionType.CREDIT);
						break;
					default:
				}

				// set the button name
				btnAddTransaction.setText("Add " + comboBoxTxnCategory.getSelectedItem());

			}
		});

		textCustomerNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				customer = customerService.getCustomer(textCustomerNumber.getText());
				if (customer == null) {
					textCustomerName.setEditable(true);
					textCustomerAddress.setEditable(true);
					lblDeposite.setVisible(false);
					textDeposite.setVisible(false);
					customer = new Customer();
					customer.setCustomerNumber(textCustomerNumber.getText());
					lblAddTxnStatus.setText("STATUS: Add name and address of new customer");
				} else {
					textCustomerName.setText(customer.getName());
					textCustomerAddress.setText(customer.getAddress());
					lblDeposite.setVisible(true);
					textDeposite.setVisible(true);
					textDeposite.setText(Double.toString(customer.getDepositeAmount()));

					lblAddTxnStatus.setText("STATUS: Existing Customer");
				}
			}
		});
	}

	private void addTransaction() {
		TransactionService transactionService = new TransactionService();
		Transaction transaction = new Transaction();
		double sellingPrice = 0;
		String response = "";
		try {
			transaction.setTxnCategory(comboBoxTxnCategory.getSelectedItem().toString());

			try {
				transaction.setAmount(Double.parseDouble(textTxnAmount.getText()));
			} catch (Exception ex) {
				throw new Exception("Please enter a valid decimal value for Transaction Amount");
			}
			if (textTnxDescription.getText() == null || textTnxDescription.getText().trim().length() < 1)
				throw new Exception("Description cannot be blank");
			transaction.setDescription(textTnxDescription.getText());
			if ((transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString()))) {
				if (textInvoice.getText() == null || textInvoice.getText().trim().length() < 1) {
					throw new Exception("Invoice cannot be blank for sales and installment");
				}
			}
			if ((transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString()) || transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.INSTALLMENT.toString()))) {
				if (textParticular.getText() == null || textParticular.getText().trim().length() < 1) {
					throw new Exception("Product Id cannot be blank for sales and installment");
				}
			}
			transaction.setInvoice(textInvoice.getText());
			transaction.setParticular(textParticular.getText().toUpperCase());
			transaction.setPaymentMode(comboBoxTxnpaymentMode.getSelectedItem().toString());
			transaction.setTxnType(comboBoxTxnType.getSelectedItem().toString());
			LocalDate transactionDate = txnDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (transactionDate.isAfter(LocalDate.now())) {
				throw new Exception("Please Select a past date or today's date");
			}
			transaction.setTransactionDate(transactionDate);
			if ((transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString()))) {
				try {
					sellingPrice = Double.parseDouble(textTxnSellingPrice.getText());
				} catch (Exception ex) {
					throw new Exception("Enter a valid decimal value for selling price");
				}
			}

			if ((transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString()) || transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.INSTALLMENT.toString()))) {
				if ((textCustomerName.getText() == null || textCustomerName.getText().trim().length() < 1)) {
					throw new Exception("For Sales & Installment entry customer detail cannot be blank");
				}
				customer.setName(textCustomerName.getText());
				customer.setAddress(textCustomerAddress.getText());

				response = transactionService.addSaleTransaction(transaction, customer, sellingPrice);

			} else {
				response = transactionService.addTransaction(transaction);
			}

			lblAddTxnStatus.setText("STATUS: " + response);
			lblAddTxnStatus.setForeground(Color.BLACK);

			resetTransactionForm();

		} catch (Exception exception) {
			lblAddTxnStatus.setText("ERROR:: " + exception.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, "STATUS: " + exception.getLocalizedMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
			lblAddTxnStatus.setForeground(Color.RED);
		}
	}

	private void returnProductForm() {
		lblReturnPrdID = new JLabel("Product ID");
		lblReturnPrdID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnPrdID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReturnPrdID.setBounds(87, 62, 96, 24);
		panelProductReturn.add(lblReturnPrdID);

		textReturnPrdId = new JTextField();
		textReturnPrdId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnPrdId.setColumns(10);
		textReturnPrdId.setBounds(207, 62, 147, 24);
		panelProductReturn.add(textReturnPrdId);

		lblReturnCategory = new JLabel("Category");
		lblReturnCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnCategory.setBounds(87, 96, 96, 24);
		panelProductReturn.add(lblReturnCategory);

		textReturnCategory = new JTextField();
		textReturnCategory.setEditable(false);
		textReturnCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCategory.setColumns(10);
		textReturnCategory.setBounds(207, 96, 147, 24);
		panelProductReturn.add(textReturnCategory);

		lblReturnName = new JLabel("Name");
		lblReturnName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnName.setBounds(87, 130, 96, 28);
		panelProductReturn.add(lblReturnName);

		textReturnName = new JTextField();
		textReturnName.setEditable(false);
		textReturnName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnName.setBounds(207, 131, 147, 28);
		panelProductReturn.add(textReturnName);
		textReturnName.setColumns(10);

		lblReturnDescription = new JLabel("Description");
		lblReturnDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnDescription.setBounds(87, 168, 96, 28);
		panelProductReturn.add(lblReturnDescription);

		textReturnDescription = new JTextField();
		textReturnDescription.setEditable(false);
		textReturnDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnDescription.setColumns(10);
		textReturnDescription.setBounds(207, 169, 335, 28);
		panelProductReturn.add(textReturnDescription);

		lblReturnCostPrice = new JLabel("Cost Price");
		lblReturnCostPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnCostPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnCostPrice.setBounds(87, 206, 96, 28);
		panelProductReturn.add(lblReturnCostPrice);

		textReturnCostPrice = new JTextField();
		textReturnCostPrice.setEditable(false);
		textReturnCostPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCostPrice.setColumns(10);
		textReturnCostPrice.setBounds(207, 207, 147, 28);
		panelProductReturn.add(textReturnCostPrice);

		lblReturnMrp = new JLabel("MRP");
		lblReturnMrp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnMrp.setBounds(87, 244, 96, 28);
		panelProductReturn.add(lblReturnMrp);

		textReturnMrp = new JTextField();
		textReturnMrp.setEditable(false);
		textReturnMrp.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnMrp.setColumns(10);
		textReturnMrp.setBounds(207, 245, 147, 28);
		panelProductReturn.add(textReturnMrp);

		lblCheckInDate = new JLabel("Checkin Date");
		lblCheckInDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCheckInDate.setBounds(70, 282, 115, 28);
		panelProductReturn.add(lblCheckInDate);


		btnReturnProduct = new JButton("Return Product");
		btnReturnProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReturnProduct.setBounds(207, 397, 147, 28);
		panelProductReturn.add(btnReturnProduct);

		lblReturnPrdStatus = new JLabel("STATUS: ");
		lblReturnPrdStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReturnPrdStatus.setBounds(22, 450, 600, 82);
		panelProductReturn.add(lblReturnPrdStatus);

		textReturnCheckinDate = new JTextField();
		textReturnCheckinDate.setEditable(false);
		textReturnCheckinDate.setText((String) null);
		textReturnCheckinDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		textReturnCheckinDate.setColumns(10);
		textReturnCheckinDate.setBounds(207, 283, 147, 28);
		panelProductReturn.add(textReturnCheckinDate);

		lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnDate.setBounds(70, 320, 115, 28);
		panelProductReturn.add(lblReturnDate);

		returnDate = new JDateChooser();
		returnDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		returnDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		returnDate.setDateFormatString("dd-MM-yyyy");
		returnDate.setDate(today);
		returnDate.setBounds(207, 321, 147, 28);
		panelProductReturn.add(returnDate);

		btnGetPrd = new JButton("Check Id");
		btnGetPrd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGetPrd.setBounds(397, 63, 106, 23);
		panelProductReturn.add(btnGetPrd);

		lblReturnpaymentMode = new JLabel("Payment mode");
		lblReturnpaymentMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReturnpaymentMode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnpaymentMode.setBounds(36, 358, 147, 28);
		panelProductReturn.add(lblReturnpaymentMode);

		comboBoxReturnpaymentMode = new JComboBox();
		comboBoxReturnpaymentMode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxReturnpaymentMode.setBounds(207, 359, 147, 28);
		comboBoxReturnpaymentMode.setModel(new DefaultComboBoxModel<>(PaymentMode.values()));
		panelProductReturn.add(comboBoxReturnpaymentMode);

		btnGetPrd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getReturnProductDetails();
				frame.repaint();
			}
		});

		btnReturnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnProduct();
			}
		});
	}

	private void getReturnProductDetails() {
		Product product = productService.getProduct(textReturnPrdId.getText().toUpperCase());
		if (product == null) {
			lblReturnPrdStatus.setText("STATUS: Product with ID " + textReturnPrdId.getText().toUpperCase() + " not found");
			resetReturnForm();
		} else {
			textReturnCategory.setText(product.getCategory());
			textReturnName.setText(product.getName());
			textReturnDescription.setText(product.getDescription());
			textReturnCostPrice.setText(Double.toString(product.getCostPrice()));
			textReturnMrp.setText(Double.toString(product.getMrp()));
			textReturnCheckinDate.setText(product.getStockInDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lblReturnPrdStatus.setText("STATUS: " + product.getStatus());
		}
	}

	private void returnProduct() {
		if (lblReturnPrdStatus.getText().contains(Properties.STATUS_AVAILABLE)) {
			try {
				String response = productService.returnToVendor(textReturnPrdId.getText().toUpperCase(), returnDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						comboBoxReturnpaymentMode.getSelectedItem().toString());
				lblReturnPrdStatus.setText("STATUS: " + response);
				textReturnPrdId.setText("");
				resetReturnForm();

			} catch (Exception ex) {
				lblReturnPrdStatus.setText("STATUS: " + ex.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, "STATUS: " + ex.getLocalizedMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, lblReturnPrdStatus.getText() + "\nProduct is unavailable for returned", "Return Failed", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setCustomerReturnForm() {
		JLabel lblCustReturnProductId = new JLabel("Product Id");
		lblCustReturnProductId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnProductId.setBounds(191, 94, 96, 24);
		panelCustomerReturn.add(lblCustReturnProductId);

		textCustReturnProductId = new JTextField();
		textCustReturnProductId.setToolTipText("Give Product ID in format:  \"SA-\"");
		textCustReturnProductId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnProductId.setColumns(10);
		textCustReturnProductId.setBounds(298, 94, 147, 24);
		panelCustomerReturn.add(textCustReturnProductId);

		JLabel lblCustReturnCategory = new JLabel("Category");
		lblCustReturnCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnCategory.setBounds(191, 128, 96, 24);
		panelCustomerReturn.add(lblCustReturnCategory);

		JLabel lblCustReturnName = new JLabel("Name");
		lblCustReturnName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnName.setBounds(191, 162, 96, 28);
		panelCustomerReturn.add(lblCustReturnName);

		JLabel lblCustReturnDescription = new JLabel("Description");
		lblCustReturnDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnDescription.setBounds(191, 203, 96, 28);
		panelCustomerReturn.add(lblCustReturnDescription);

		textCustReturnCategory = new JTextField();
		textCustReturnCategory.setEditable(false);
		textCustReturnCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnCategory.setColumns(10);
		textCustReturnCategory.setBounds(298, 128, 147, 24);
		panelCustomerReturn.add(textCustReturnCategory);

		textCustReturnPrdName = new JTextField();
		textCustReturnPrdName.setEditable(false);
		textCustReturnPrdName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnPrdName.setColumns(10);
		textCustReturnPrdName.setBounds(298, 162, 147, 28);
		panelCustomerReturn.add(textCustReturnPrdName);

		textCustReturnDescription = new JTextField();
		textCustReturnDescription.setEditable(false);
		textCustReturnDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnDescription.setColumns(10);
		textCustReturnDescription.setBounds(298, 200, 147, 28);
		panelCustomerReturn.add(textCustReturnDescription);

		JLabel lblCustReturnPaid = new JLabel("Amount Paid");
		lblCustReturnPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnPaid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnPaid.setBounds(191, 294, 96, 28);
		panelCustomerReturn.add(lblCustReturnPaid);

		textCustReturnPaid = new JTextField();
		textCustReturnPaid.setEditable(false);
		textCustReturnPaid.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnPaid.setColumns(10);
		textCustReturnPaid.setBounds(298, 291, 147, 28);
		panelCustomerReturn.add(textCustReturnPaid);

		JButton btnCustReturnProduct = new JButton("Return Product");
		btnCustReturnProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCustReturnProduct.setBounds(298, 341, 147, 24);
		panelCustomerReturn.add(btnCustReturnProduct);

		JLabel lblCustReturnPrdStatus = new JLabel("STATUS: ");
		lblCustReturnPrdStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustReturnPrdStatus.setBounds(10, 469, 624, 82);
		panelCustomerReturn.add(lblCustReturnPrdStatus);

		lblCustReturnStatus = new JLabel("Status");
		lblCustReturnStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustReturnStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustReturnStatus.setBounds(191, 244, 96, 28);
		panelCustomerReturn.add(lblCustReturnStatus);

		textCustReturnStatus = new JTextField();
		textCustReturnStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCustReturnStatus.setEditable(false);
		textCustReturnStatus.setColumns(10);
		textCustReturnStatus.setBounds(298, 241, 147, 28);
		panelCustomerReturn.add(textCustReturnStatus);

		textCustReturnProductId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textCustReturnProductId.getText() != null && textCustReturnProductId.getText().trim().length() > 0) {
					try {
						Product product = productService.getProduct(textCustReturnProductId.getText().toUpperCase());
						double paidAmount = paymentService.getPaidAmount(textCustReturnProductId.getText().toUpperCase());
						if (product == null) {
							throw new Exception("Product with product ID " + textCustReturnProductId.getText().toUpperCase() + " not found");
						} else {
							textCustReturnCategory.setText(product.getCategory());
							textCustReturnDescription.setText(product.getDescription());
							textCustReturnPrdName.setText(product.getName());
							textCustReturnStatus.setText(product.getStatus());
							textCustReturnPaid.setText(Double.toString(paidAmount));
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "STATUS: " + ex.getLocalizedMessage(), "Warning", JOptionPane.WARNING_MESSAGE);

					}
				}
			}
		});

		btnCustReturnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String response = paymentService.returnFromCustomer(textCustReturnProductId.getText().toUpperCase());
					lblCustReturnPrdStatus.setText("STATUS: " + response);
					resetCustomerReturnForm();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
	}

	private void resetReturnForm() {
		textReturnCategory.setText("");
		textReturnName.setText("");
		textReturnDescription.setText("");
		textReturnCostPrice.setText("");
		textReturnMrp.setText("");
		textReturnCheckinDate.setText("");
	}

	private void resetCustomerReturnForm() {
		textCustReturnProductId.setText("");
		textCustReturnCategory.setText("");
		textCustReturnPrdName.setText("");
		textCustReturnDescription.setText("");
		textCustReturnStatus.setText("");
		textCustReturnPaid.setText("");
	}

	private void resetTransactionForm() {
		// Reset the transaction form
		textTxnAmount.setText(null);
		textTnxDescription.setText(null);
		textInvoice.setText(null);
		textParticular.setText(null);
		txnDate.setDate(today);
		textCustomerNumber.setText(null);
		textCustomerName.setText(null);
		textCustomerName.setEditable(false);
		textCustomerAddress.setText(null);
		textCustomerAddress.setEditable(false);
		lblDeposite.setVisible(false);
		textDeposite.setVisible(false);
		textDeposite.setText(null);

	}

	private void resetAddProductForm() {
		textProductId.setText("");
		textCategory.setText("");
		textName.setText("");
		textDescription.setText("");
		textCostPrice.setText("");
		textMrp.setText("");
		checkinDate.setDate(today);
		lblFinalPrdId.setText("");
	}
}

