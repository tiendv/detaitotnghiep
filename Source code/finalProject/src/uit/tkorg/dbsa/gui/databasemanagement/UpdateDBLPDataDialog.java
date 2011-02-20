package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
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

import uit.tkorg.dbsa.actions.database.CheckExist;
import uit.tkorg.dbsa.actions.database.DeletePublicaitonInDBSA;
import uit.tkorg.dbsa.actions.database.LoadPublicationsFromDBSA;
import uit.tkorg.dbsa.gui.fetcher.MyJTable;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.DBSAPublication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class UpdateDBLPDataDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel chooseFileLabel;
	private JButton loadFileJButton;
	private JFileChooser dblpChooserFile = new JFileChooser();
	private JTextField localDBLPFileJTextField;
	private JPanel replaceDBLPJPanel;
	private int width = 911 ;
	private int height = 500;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	private JButton replaceJButton;
	private JLabel statusLabel;
	private JPanel tableJPanel;
	private JButton closeJButton;
	private JButton deleteJButton;
	private JButton selectDupJButton;
	private JButton selectAllJButton;
	private JButton checkDupButton;
	private JPanel buttonPanel;
	private MyJTable pubDataJTable;
	private DefaultTableModel model;
	private JScrollPane tableJScrollPane; 

	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static String link = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String publisher = "";
	private static boolean mark = false;
	private static URL hyperlink = null;
	private static boolean isDuplicate = false;
	public static String digitalLibrary;
	public static int id;
	
	private static ArrayList<Integer> numberArray = new ArrayList<Integer>();
	
	DeletePublicaitonInDBSA deletePublication = new DeletePublicaitonInDBSA();
	
	public UpdateDBLPDataDialog() {
		initComponents();
	}

	public UpdateDBLPDataDialog(JFrame mainJFrame) {
		super(mainJFrame);
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}

	public UpdateDBLPDataDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public UpdateDBLPDataDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public UpdateDBLPDataDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public UpdateDBLPDataDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public UpdateDBLPDataDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public UpdateDBLPDataDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public UpdateDBLPDataDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public UpdateDBLPDataDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public UpdateDBLPDataDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public UpdateDBLPDataDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public UpdateDBLPDataDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public UpdateDBLPDataDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public UpdateDBLPDataDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public UpdateDBLPDataDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle("Update DBLP database");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getReplaceDBLPJPanel(), new Constraints(new Bilateral(4, 6, 479), new Leading(4, 96, 10, 10)));
		add(getTableJPanel(), new Constraints(new Bilateral(4, 6, 0), new Bilateral(104, 5, 10, 270)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JScrollPane getTableJScrollPane() {
		if (tableJScrollPane == null) {
			tableJScrollPane = new JScrollPane();
			tableJScrollPane.setViewportView(getpubDataJTable());
		}
		return tableJScrollPane;
	}

	/*
	 * Ham tao Jtable
	 */
	public MyJTable createResultJTable(){
		model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getHyperLink(), getYear(), getAbstract(), getPublisher(), getMark(), getIsDuplicate(), getID()), getColumnName()) {
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
	
	/*
	 * ham lay gia tri cho table
	 * @return JTable
	 */
	boolean checkRemovedFirst = false;
	int checkEnable = 0;
	private JButton clearSelectButton;
	
	public JTable getpubDataJTable() {
		
		if (pubDataJTable == null) {
			
			pubDataJTable = createResultJTable();
			model.removeRow(0);
		}else if(pubDataJTable != null){
			
			if(pubDataJTable.getModel().getValueAt(0, 2).toString().replaceAll(" ", "").equals("")){
				checkRemovedFirst =  true;				
				model.removeRow(0);
							
			}		
		}
		pubDataJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(int i = 0; i < pubDataJTable.getRowCount();i++){
					if(model.getValueAt(i, 7) != null
							&& model.getValueAt(i, 7).toString().equals("true")){
						checkEnable++;
						deleteJButton.setEnabled(true);
					}
						
					if(checkEnable == 0){
						deleteJButton.setEnabled(false);
					}
				}
			}
			public void mouseEntered(MouseEvent arg0) {
				
			}
			public void mouseExited(MouseEvent arg0) {
				
			}
			public void mousePressed(MouseEvent arg0) {
				
			}
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		
		return pubDataJTable;
	}
	
	@SuppressWarnings("unchecked")
	public void checkArticleIsDuplicated(ArrayList<DBSAPublication> dbsaPublicationCheckList){
			
		CheckExist check = new CheckExist();
		
		numberArray = (ArrayList<Integer>) check.CheckTitleSignaturePublicationsInDBLP(dbsaPublicationCheckList).clone();
	
		for(int i = 0; i < numberArray.size(); i++)
		{	
			model.setValueAt(true, numberArray.get(i), 8);
			pubDataJTable.addRowToPaint(numberArray.get(i), Color.red);
		
		}
	
	}
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getColumnName(){
		String [] columnNames = { /*DBSAResourceBundle.res.getString*/("no"), /*DBSAResourceBundle.res.getString*/("title"), 
				/*DBSAResourceBundle.res.getString*/("authors"), /*DBSAResourceBundle.res.getString*/("link"),
				/*DBSAResourceBundle.res.getString*/("year"),/*DBSAResourceBundle.res.getString*/("abstract"), 
				/*DBSAResourceBundle.res.getString*/("publisher"),/*DBSAResourceBundle.res.getString*/("X"), 
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

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPanel.setLayout(new GroupLayout());
			buttonPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 128, 678, 736), new Leading(12, 12, 12)));
			buttonPanel.add(getDeleteJButton(), new Constraints(new Trailing(158, 128, 564, 590), new Leading(12, 12, 12)));
			buttonPanel.add(getClearSelectButton(), new Constraints(new Trailing(304, 128, 442, 444), new Leading(12, 12, 12)));
			buttonPanel.add(getSelectDupJButton(), new Constraints(new Trailing(450, 128, 257, 298), new Leading(12, 12, 12)));
			buttonPanel.add(getSelectAllJButton(), new Constraints(new Trailing(596, 128, 150, 152), new Leading(12, 12, 12)));
			buttonPanel.add(getCheckDupButton(), new Constraints(new Trailing(742, 128, 12, 12), new Leading(12, 12, 12)));
		}
		return buttonPanel;
	}

	private JButton getCheckDupButton() {
		if (checkDupButton == null) {
			checkDupButton = new JButton();
			checkDupButton.setText("Check Duplicate");
			checkDupButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(pubDataJTable.getRowCount() > 0)
						for(int i = pubDataJTable.getRowCount(); i >= 0; i--){
							model.removeRow(i);
						}
					
					LoadPublicationsFromDBSA loadPublication = new LoadPublicationsFromDBSA();
					ArrayList<DBSAPublication> dbsaPublicationList = loadPublication.getPaper();
					
					for(int i = 0; i < dbsaPublicationList.size(); i++){
						
						if(dbsaPublicationList.get(i).getTitle() != null)
							setPubTitle(dbsaPublicationList.get(i).getTitle());
						else
							setPubTitle("");
						
						if(dbsaPublicationList.get(i).getAuthors() != null)
							setAuthor(dbsaPublicationList.get(i).getAuthors());
						else
							setAuthor("");
						
						if(dbsaPublicationList.get(i).getLinks() != null)
							setLink(dbsaPublicationList.get(i).getLinks());
						else
							setLink("");
						
						String year;
						if(dbsaPublicationList.get(i).getYear() != 0){
							setYear(dbsaPublicationList.get(i).getYear());
							year = getYear() + "";
						}
						else{
							setYear(0);
							year = "";
						}
						
						if(dbsaPublicationList.get(i).getAbstractPub() != null)
							setAbstract(dbsaPublicationList.get(i).getAbstractPub());
						else
							setAbstract("");
						
						if(dbsaPublicationList.get(i).getPublisher() != null)
							setPublisher(dbsaPublicationList.get(i).getPublisher());
						else
							setPublisher("");
						
						setID(dbsaPublicationList.get(i).getId());
										
						Object [] data = {pubDataJTable.getRowCount() + 1, getPubTitle(), getAuthor(), getLink(), year, getAbstract(), getPublisher(), getMark(), getIsDuplicate(), getID()};
						model.insertRow(pubDataJTable.getRowCount(), data );
					}
					
					checkArticleIsDuplicated(dbsaPublicationList);
				}
				
			});
		}
		return checkDupButton;
	}

	private JButton getSelectAllJButton() {
		if (selectAllJButton == null) {
			selectAllJButton = new JButton();
			selectAllJButton.setText("Select All");
			selectAllJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(int i = 0; i < pubDataJTable.getRowCount(); i++){
						pubDataJTable.getModel().setValueAt(true, i, 7);
						deleteJButton.setEnabled(true);
						
					}
					
					if(pubDataJTable.getRowCount() == 0){
						selectAllJButton.setEnabled(false);
						selectDupJButton.setEnabled(false);
						deleteJButton.setEnabled(false);
						
					}					
				}
				
			});
		}
		return selectAllJButton;
	}

	private JButton getSelectDupJButton() {
		if (selectDupJButton == null) {
			selectDupJButton = new JButton();
			selectDupJButton.setText("Select Duplicate");
			selectDupJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(int i = 0; i < pubDataJTable.getRowCount(); i++){
						if(pubDataJTable.getModel().getValueAt(i, 8) != null
								&&pubDataJTable.getModel().getValueAt(i, 8).toString().equals("true")){
							pubDataJTable.getModel().setValueAt(true, i, 7);
							deleteJButton.setEnabled(true);
							
						}else{
							pubDataJTable.getModel().setValueAt(false, i, 7);
						}
					}
					
					if(pubDataJTable.getRowCount() == 0){
						selectAllJButton.setEnabled(false);
						selectDupJButton.setEnabled(false);
						deleteJButton.setEnabled(false);
						
					}
				}
			});
		}
		return selectDupJButton;
	}

	private JButton getClearSelectButton() {
		if (clearSelectButton == null) {
			clearSelectButton = new JButton();
			clearSelectButton.setText("Clear select");
			clearSelectButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(int i = 0; i < pubDataJTable.getRowCount(); i++){
						model.setValueAt(false, i, 7);
						deleteJButton.setEnabled(false);
					}
				}
				
			});
		}
		return clearSelectButton;
	}
	
	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setText("Delete");
			deleteJButton.setEnabled(false);
			deleteJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					removeRowsIsSelected();
				}
				
			});
		}
		return deleteJButton;
	}

	private void removeRowsIsSelected() {
		
		for (int i = pubDataJTable.getRowCount() - 1; i >= 0; i--){
			ArrayList<DBSAPublication> deletePublicationList = new ArrayList<DBSAPublication>();
			if (model.getValueAt(i, 7) != null
					&& model.getValueAt(i, 7).toString().equals("true")) {
				DBSAPublication dbsaPublication = new DBSAPublication();
				dbsaPublication.setId(Integer.parseInt(model.getValueAt(i, 9).toString()));
				
				model.removeRow(i);
				
				deletePublicationList.add(dbsaPublication);
				deletePublication.deletePublication(dbsaPublication);
			}
		}
		if(pubDataJTable.getRowCount() == 0){
			deleteJButton.setEnabled(false);
		}
		for (int j = 0; j < pubDataJTable.getRowCount(); j++) {		
			pubDataJTable.addRowToPaint(j, Color.white);
			pubDataJTable.getModel().setValueAt(j+1, j, 0);

			if(pubDataJTable.getModel().getValueAt(j, 8) != null
				&&pubDataJTable.getModel().getValueAt(j, 8).toString().equals("true")){
		
				pubDataJTable.addRowToPaint(j, Color.red);
			}
		}
			
		
	}
	
	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Close");
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dispose();
				}				
			});
		}
		return closeJButton;
	}

	private JPanel getTableJPanel() {
		if (tableJPanel == null) {
			tableJPanel = new JPanel();
			tableJPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			tableJPanel.setLayout(new GroupLayout());
			tableJPanel.add(getButtonPanel(), new Constraints(new Bilateral(4, 5, 672), new Trailing(4, 10, 204)));
			tableJPanel.add(getTableJScrollPane(), new Constraints(new Bilateral(4, 6, 22), new Bilateral(3, 70, 26, 403)));
		}
		return tableJPanel;
	}

	private JLabel getStatusLabel() {
		if (statusLabel == null) {
			statusLabel = new JLabel();
			statusLabel.setForeground(Color.red);
			statusLabel.setText("You haven't chosen DBLP database file...");
		}
		return statusLabel;
	}

	private JButton getReplaceJButton() {
		if (replaceJButton == null) {
			replaceJButton = new JButton();
			replaceJButton.setText("Replace DBLP data");
		}
		return replaceJButton;
	}

	private JPanel getReplaceDBLPJPanel() {
		if (replaceDBLPJPanel == null) {
			replaceDBLPJPanel = new JPanel();
			replaceDBLPJPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			replaceDBLPJPanel.setLayout(new GroupLayout());
			replaceDBLPJPanel.add(getReplaceJButton(), new Constraints(new Trailing(12, 10, 10), new Leading(26, 12, 12)));
			replaceDBLPJPanel.add(getChooseFileJLabel(), new Constraints(new Leading(7, 456, 12, 12), new Leading(4, 21, 10, 10)));
			replaceDBLPJPanel.add(getStatusLabel(), new Constraints(new Bilateral(7, 12, 229), new Bilateral(59, 12, 16)));
			replaceDBLPJPanel.add(getLoadFileJButton(), new Constraints(new Trailing(172, 142, 23, 537), new Leading(26, 34, 34)));
			replaceDBLPJPanel.add(getLocalFileJTextField(), new Constraints(new Bilateral(7, 332, 4), new Leading(26, 27, 10, 10)));
		}
		return replaceDBLPJPanel;
	}

	private JTextField getLocalFileJTextField() {
		if (localDBLPFileJTextField == null) {
			localDBLPFileJTextField = new JTextField();			
		}
		return localDBLPFileJTextField;
	}

	private JButton getLoadFileJButton() {
		if (loadFileJButton == null) {
			loadFileJButton = new JButton();
			loadFileJButton.setText("Load file");
			loadFileJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {					
					File file = getDblpChooserFile();
					
					localDBLPFileJTextField.setText(file.getPath());
					statusLabel.setText("Please, press \"Replace\" button to replace your new DBLP database. ");
				}
				
			});
		}
		return loadFileJButton;
	}

	private File getDblpChooserFile(){
		int returnVal = dblpChooserFile.showOpenDialog(UpdateDBLPDataDialog.this);
		File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
             file = dblpChooserFile.getSelectedFile();
            
        } else {
            JOptionPane.showMessageDialog(null, "Open command cancelled by user.");
        }
        
        return file;
	}
	
	private JLabel getChooseFileJLabel() {
		if (chooseFileLabel == null) {
			chooseFileLabel = new JLabel();
			chooseFileLabel.setText("Please, choose and load DBLP database file.");
		}
		return chooseFileLabel;
	}
	
	/*
	 * 
	 * set & get paper bibliography 
	 */
	
	public  void setRowNumber(int number){
		rowNumber = number;
	}
	
	public   int getRowNumber(){
		return rowNumber;
	}
	
	public  void setPubTitle(String titleString){
		title = titleString;
	}

	public   String getPubTitle(){
		return title;
	}
	
	public  void setAuthor(String authorString){
		author = authorString;
	}

	public  String getAuthor(){
		return author;
	}
	
	public void setLink(String linkString){
		link = linkString;
	}
	
	public String getLink(){
		return link;
	}
	
	@SuppressWarnings("static-access")
	public  void setYear(int year){
		this.year = year;
	}

	public  int getYear(){
		return year;
	}
	
	public  void setAbstract(String abstractString){
		abstracts = abstractString;
	}

	public  String getAbstract(){
		return abstracts;
	}
	
	public  void setPublisher(String publisherString){
		publisher = publisherString;
	}

	public  String getPublisher(){
		return publisher;
	}
	
	public  void setMark(boolean isMark){
		mark = isMark;
	}
	
	public  boolean getMark(){
		return mark;
	}
	
	@SuppressWarnings("static-access")
	public void setHyperLink(String hyperLink){
		try {
			this.hyperlink = new URL(hyperLink);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public URL getHyperLink(){
		return hyperlink;
	}
	
	public void setIsDuplicate(boolean duplicate){
		isDuplicate = duplicate;
	}
	
	public boolean getIsDuplicate(){
		return isDuplicate;
	}
	
	public void setDigitalLibrary(String dl){
		digitalLibrary = dl;
	}
	
	public String getDigitalLibrary(){
		return digitalLibrary;
	}
	public   int getID(){
		return id;
	}
	
	public  void setID(int id){
		this.id = id;
	}

}
