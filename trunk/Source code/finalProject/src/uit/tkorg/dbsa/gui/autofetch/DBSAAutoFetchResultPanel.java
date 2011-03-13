package uit.tkorg.dbsa.gui.autofetch;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.cores.autofetch.DBSAAutoFetchResult;
import uit.tkorg.dbsa.gui.fetcher.MyJTable;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAAutoFetchResultPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable resultJTable;
	private JScrollPane tableResultJScrollPane;
	private JPanel tableResultJPanel;
	private JButton closeJButton;
	private JPanel actionsJPanel;
	private JButton saveJButton;

	private DefaultTableModel model;
	
	
	
	public DBSAAutoFetchResultPanel() {
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Frame parent) {
		super(parent);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Window parent) {
		super(parent);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DBSAAutoFetchResultPanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle("Ket qua tu dong thu thap du lieu cua he thong");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getTableResultJPanel(), new Constraints(new Bilateral(4, 6, 812), new Bilateral(7, 87, 10, 373)));
		add(getActionsJPanel(), new Constraints(new Bilateral(4, 6, 0), new Leading(448, 76, 10, 10)));
		setSize(822, 529);
	}

	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Save to file");
		}
		return saveJButton;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 125, 10, 10), new Leading(0, 40, 10, 10)));
			actionsJPanel.add(getSaveJButton(), new Constraints(new Trailing(155, 125, 12, 12), new Leading(0, 40, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Close");
		}
		return closeJButton;
	}

	private JPanel getTableResultJPanel() {
		if (tableResultJPanel == null) {
			tableResultJPanel = new JPanel();
			tableResultJPanel.setBorder(BorderFactory.createTitledBorder(null, "Danh sach ket qua", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			tableResultJPanel.setLayout(new GroupLayout());
			tableResultJPanel.add(getTableResultJScrollPane(), new Constraints(new Bilateral(3, 2, 797), new Bilateral(-3, 3, 10, 347)));
		}
		return tableResultJPanel;
	}

	private JScrollPane getTableResultJScrollPane() {
		if (tableResultJScrollPane == null) {
			tableResultJScrollPane = new JScrollPane();
			tableResultJScrollPane.setViewportView(getresultJTable());
		}
		return tableResultJScrollPane;
	}

	/*
	 * Ham tao Jtable
	 */
	public JTable createResultJTable(){
		model = new DefaultTableModel(getTableData(DBSAAutoFetchResult.getRowNumber(),
				DBSAAutoFetchResult.getPubTitle(), DBSAAutoFetchResult.getAuthor(), 
				DBSAAutoFetchResult.getHyperLink(), DBSAAutoFetchResult.getYear(), 
				DBSAAutoFetchResult.getAbstract(), DBSAAutoFetchResult.getPublisher(), 
				DBSAAutoFetchResult.getMark(), DBSAAutoFetchResult.getIsDuplicate(), 
				DBSAAutoFetchResult.getID()), getColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, URL.class, String.class, String.class, String.class, Boolean.class, Boolean.class, Integer.class};

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		
		};
		
		MyJTable table = new MyJTable(model);
		//Sap xep noi dung cac dong trong table theo thu tu alpha B.
		//Cho phep sap xep theo tu cot rieng biet
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		/*
		 * Set width of table column 
		 */
		table.setShowGrid(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(25);
		//table.getColumn("links")//;
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
		
		TableCellRenderer tcr = table.getDefaultRenderer(Integer.class);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tcr; 
		renderer.setHorizontalAlignment(SwingConstants.CENTER);  
		
		table.getColumn(("duplicate")).setWidth(0);
		table.getColumn(("duplicate")).setMinWidth(0);
		table.getColumn(("duplicate")).setMaxWidth(0);
		
		table.getColumn(("id")).setWidth(0);
		table.getColumn(("id")).setMinWidth(0);
		table.getColumn(("id")).setMaxWidth(0);
		
		for(int i = 0; i < 8; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(30);
			}else if(i == 1){
				col.setPreferredWidth(300);
			}else if(i == 2){
				col.setPreferredWidth(200);
			}else if(i == 3){
				col.setPreferredWidth(150);
			}else if(i == 4){
				col.setPreferredWidth(60);
			}else if (i == 5){
				col.setPreferredWidth(150);
			}else if(i == 6){
				col.setPreferredWidth(80);
			}else if(i == 7){
				col.setPreferredWidth(30);
			}
		}
		
		
		return table;
	}
	
	boolean checkRemovedFirst = false;
	
	private JTable getresultJTable() {
		if (resultJTable == null) {
			
			resultJTable = createResultJTable();
			model.removeRow(0);
		}else if(resultJTable != null){
			DBSAAutoFetchResult.insertToJTable(resultJTable, model);
			
			if(resultJTable.getModel().getValueAt(0, 2).toString().replaceAll(" ", "").equals("")){
				checkRemovedFirst =  true;				
				model.removeRow(0);
							
			}		
		}
		return resultJTable;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getColumnName(){
		String [] columnNames = { DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher"),("X"), 
				("duplicate"), "id"};
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate, int idPub){
		
		Object [][] data = {addTableData(rowNumber, title, author, hyperLink, year, abstracts, publisher, isMark, duplicate, idPub)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate, int idPub){
		Object [] dataRow =  {rowNumber, title, author, hyperLink,year,abstracts, publisher,isMark, duplicate, idPub};
		
		return dataRow;
	}
}
