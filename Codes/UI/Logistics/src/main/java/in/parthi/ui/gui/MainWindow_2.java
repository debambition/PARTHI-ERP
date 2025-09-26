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

import in.parthi.core.model.product.Product;
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

public class MainWindow_2 {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JTabbedPane paneAddProduct;
	JLabel lblOperation;
	JLabel lblAddPrdStatus;
	JButton btnSubmitAddProduct;
	JLabel lblPrdCategory;

	private JTextField textName;
	JLabel lblPrdName;

	private JTextField textDescription;
	JLabel lblPrdDescription;
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
	private JTextField textCostPrice;
	private JLabel lblCostPrice;
	private JLabel lblMrp;
	private JTextField textMrp;
	private JLabel lblCheckInDate;
	private JDateChooser checkinDate;
	private JButton btnAddProduct;

	LocalDate todayDateTime = LocalDate.now();
	Date today = Date.from(todayDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());

	//Add transaction form
	private JTextField textTransactionId;
	private JLabel lblTransactionId;



	ProductService productService = new ProductService();
	private JLabel lblNewLabel;
	private JLabel lblFinalPrdId;


	TransactionService transactionService = new TransactionService();
	private JLabel lblHeaderAddProduct;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow_2 window = new MainWindow_2();
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
	public MainWindow_2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Parthi Logistics");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Acceleratron\\IT-Induction\\parthi\\parthi-erp\\Codes\\UI\\Logistics\\src\\main\\resources\\images\\logo.png"));
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

		lblTransactionId = new JLabel("Transaction Id");
		lblTransactionId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransactionId.setBounds(100, 45, 96, 24);
		panelAddTransaction.add(lblTransactionId);

		textTransactionId = new JTextField();
		textTransactionId.setToolTipText("Transaction id is auto generated");
		textTransactionId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textTransactionId.setBounds(207, 45, 147, 24);
		panelAddTransaction.add(textTransactionId);
		textTransactionId.setColumns(10);

		JButton btnAddTransaction = new JButton("Add Transaction");
		btnAddTransaction.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddTransaction.setBounds(207, 321, 147, 28);
		panelAddTransaction.add(btnAddTransaction);

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



		//------------Add Transaction Form actions-------------------
		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionService transactionService = new TransactionService();
				String id = "";
				//				String particular = "";
				//				String transactionType = "";
				//				String paymentMode = "";
				//				String category ="";
				//				double amount =0;
				id = textTransactionId.getText();
				//				particular = textParticular.getText();
				//				transactionType = textTransactionType.getText();
				//				paymentMode = textPaymentMode.getText();
				//				category = textTransactionCategory.getText();
				//				amount = Double.parseDouble(textAmount.getText());

				String response  = transactionService.addMyTransaction(id);
				lblAddPrdStatus.setText("STATUS: " +response);
			}
		});
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
			
			String category = textCategory.getText().toUpperCase();
			if (category == null || category.trim().length() <1)
				throw new Exception("Product category cannot be blank");
			
			String name = textName.getText().toUpperCase();
			if (name == null || name.trim().length() <1)
				throw new Exception("Product name cannot be blank");
			
			String description = textDescription.getText();
			try {
				double costPrice = Double.parseDouble(textCostPrice.getText());
				product.setCostPrice(costPrice);
			}catch(Exception e) {
				throw new Exception("Please enter a valid decimal number for Cost Price");
			}
			try {

				double mrp = Double.parseDouble(textMrp.getText());
				product.setMrp(mrp);
			}catch(Exception e) {
				throw new Exception("Please enter a valid decimal number for MRP");
			}
			LocalDate stockinDate = checkinDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			product.setStockInDate(stockinDate);
			product.setId(id);
			product.setCategory(category);
			product.setName(name);
			product.setDescription(description);

			

			String response = productService.addProduct(product);
			lblAddPrdStatus.setText("STATUS:: "+response);
			lblAddPrdStatus.setForeground(Color.BLACK);

			//Set the form to blank for new product
			textProductId.setText("");
			textCategory.setText("");
			textName.setText("");
			textDescription.setText("");
			textCostPrice.setText("");
			textMrp.setText("");
			checkinDate.setDate(today);
			lblFinalPrdId.setText("");
		}catch (Exception e) {
			lblAddPrdStatus.setText("ERROR:: "+e.getLocalizedMessage());
			lblAddPrdStatus.setForeground(Color.RED);
		}
	}
}
