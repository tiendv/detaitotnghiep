package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import jxl.write.DateTime;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.actions.database.LoadPublicaitonFromDBLP;
import uit.tkorg.dbsa.actions.database.SearchPublicationInDatabase;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SearchInDatabasePanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel keywordJLabel;
	private JLabel searchByJLabel;
	private JTextField keywordJTextField;
	private JPanel inputJPanel;
	private JPanel actionsJPanel;
	private JButton closeJButton;
	private JButton searchJButton;

	private int width = 1049 ;
	private int height = 638;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	private JPanel resultJPanel;
	private JTable resultJTable;
	private JScrollPane resultScrollPane;
	
	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String links ="";
	private static String publisher = "";
	
	private static DefaultTableModel publicationModel;
	private JTable tempJTable = new JTable() ;
	
	public SearchInDatabasePanel() {
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(JFrame mainJFrame) {
		super(mainJFrame, true);
		
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}
	
	public SearchInDatabasePanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle(DBSAResourceBundle.res.getString("title.search.paper.in.db"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(0, 1, 0), new Trailing(10, 10, 128)));
		add(getInputJPanel(), new Constraints(new Bilateral(0, 225, 813), new Leading(0, 126, 10, 10)));
		add(getFilterJPanel(), new Constraints(new Trailing(1, 206, 10, 10), new Leading(2, 122, 153, 504)));
		add(getresultJPanel(), new Constraints(new Bilateral(0, 1, 0), new Bilateral(136, 89, 52, 429)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JComboBox getBeginYearJComboBox() {
		if (beginYearJComboBox == null) {
			beginYearJComboBox = new JComboBox();
			beginYearJComboBox.setDoubleBuffered(false);
			beginYearJComboBox.setBorder(null);
			createYearCombobox(beginYearJComboBox);
			beginYearJComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
			        int petName = Integer.parseInt(cb.getSelectedItem().toString());
		        	System.out.println(petName);
				}
				
			});
		}
		return beginYearJComboBox;
	}

	private JComboBox getEndYearJComboBox() {
		if (endYearJComboBox == null) {
			endYearJComboBox = new JComboBox();
			
			endYearJComboBox.setDoubleBuffered(false);
			endYearJComboBox.setBorder(null);
			createYearCombobox(endYearJComboBox);
			endYearJComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
			        
			        int _beginYearIsSelected = Integer.parseInt(beginYearJComboBox.getSelectedItem().toString());
			        int _endYearIsSelected = Integer.parseInt(cb.getSelectedItem().toString());
			        
			        if(sinceYearCheckBox.isSelected()){
			        	if(_beginYearIsSelected > _endYearIsSelected){
			        		JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("invalid.year"));
			        	}else{
			        		filterSearchResult(tempJTable, publicationModel, _beginYearIsSelected, _endYearIsSelected);
			        	}
			        }
				}				
			});
		}
		return endYearJComboBox;
	}

	private JCheckBox getSinceYearCheckBox() {
		if (sinceYearCheckBox == null) {
			sinceYearCheckBox = new JCheckBox();
			sinceYearCheckBox.setText(DBSAResourceBundle.res.getString("since.year"));
			
		}
		return sinceYearCheckBox;
	}

	private JComboBox getYearJComboBox() {
		if (yearJComboBox == null) {
			yearJComboBox = new JComboBox();
			yearJComboBox.setDoubleBuffered(false);
			yearJComboBox.setBorder(null);
			createYearCombobox(yearJComboBox);
			yearJComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
			        int _yearIsSelected = Integer.parseInt(cb.getSelectedItem().toString());
		        
			        if(yearJCheckBox.isSelected()){
			        	filterSearchResult(tempJTable, publicationModel, _yearIsSelected);
			        }
				}
				
			});
		}
		return yearJComboBox;
	}
	
	//Ham khoi tao danh sach nam
	private void createYearCombobox(JComboBox tempCombo){
				
		Calendar now = Calendar.getInstance();
		
		for(int i = 1950; i <= now.get(Calendar.YEAR); i++){
			tempCombo.addItem(i); 
		}		
	}
	
	private void filterSearchResult(JTable _table, DefaultTableModel _tableModel, int _year){
		
		int year = 0;
		for(int i = _table.getRowCount() - 1; i >= 0; i--){
			try{
				year = Integer.parseInt(_tableModel.getValueAt(i, 4).toString());
				if(year != 0){	
					if(year != _year){
						_tableModel.removeRow(i);
					}
				}
			}catch (NumberFormatException e) {
				
				//JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.year.keyowrd.is.number"));
			}
		}
	}
	
private void filterSearchResult(JTable _table, DefaultTableModel _tableModel, int _beginYear, int _endYear){
		
	int year = 0;
	for(int i = _table.getRowCount() - 1 ; i >= 0; i--){
		try{
			year = Integer.parseInt(_tableModel.getValueAt(i, 4).toString());
			if(year != 0){	
				if(year < _beginYear || year > _endYear){
					_tableModel.removeRow(i);
				}
			}
		}catch (NumberFormatException e) {
			
			//JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.year.keyowrd.is.number"));
		}
	}
	}

	private JPanel getFilterJPanel() {
		if (filterJPanel == null) {
			filterJPanel = new JPanel();
			filterJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("filter"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
					12), new Color(51, 51, 51)));
			filterJPanel.setLayout(new GroupLayout());
			filterJPanel.add(getYearJCheckBox(), new Constraints(new Leading(8, 67, 10, 10), new Leading(0, 8, 8)));
			filterJPanel.add(getSinceYearCheckBox(), new Constraints(new Leading(8, 126, 10, 10), new Leading(33, 8, 8)));
			filterJPanel.add(getYearJComboBox(), new Constraints(new Trailing(12, 82, 10, 10), new Leading(0, 12, 12)));
			filterJPanel.add(getEndYearJComboBox(), new Constraints(new Trailing(12, 82, 12, 12), new Leading(67, 12, 12)));
			filterJPanel.add(getBeginYearJComboBox(), new Constraints(new Bilateral(12, 100, 60), new Leading(67, 12, 12)));
		}
		return filterJPanel;
	}

	private JCheckBox getPublisherJCheckBox() {
		if (publisherJCheckBox == null) {
			publisherJCheckBox = new JCheckBox();
			publisherJCheckBox.setText(DBSAResourceBundle.res.getString("publisher"));
		}
		return publisherJCheckBox;
	}

	private JCheckBox getAbstractCheckBox() {
		if (abstractCheckBox == null) {
			abstractCheckBox = new JCheckBox();
			abstractCheckBox.setText(DBSAResourceBundle.res.getString("abstract"));
		}
		return abstractCheckBox;
	}

	private JCheckBox getYearJCheckBox() {
		if (yearJCheckBox == null) {
			yearJCheckBox = new JCheckBox();
			yearJCheckBox.setText(DBSAResourceBundle.res.getString("year"));
		}
		return yearJCheckBox;
	}

	private JCheckBox getAuthorJCheckBox() {
		if (authorJCheckBox == null) {
			authorJCheckBox = new JCheckBox();
			authorJCheckBox.setText(DBSAResourceBundle.res.getString("authors"));
		}
		return authorJCheckBox;
	}

	private JCheckBox getTitleJCheckBox() {
		if (titleJCheckBox == null) {
			titleJCheckBox = new JCheckBox();
			titleJCheckBox.setText(DBSAResourceBundle.res.getString("title"));
		}
		return titleJCheckBox;
	}

	private JScrollPane getresultScrollPane() {
		if (resultScrollPane == null) {
			resultScrollPane = new JScrollPane();
			resultScrollPane.setViewportView(getSearchResultJTable());
		}
		return resultScrollPane;
	}

	private JTable getSearchResultJTable() {
		if(resultJTable == null) {
			resultJTable =  createDatabaseJTable();
			publicationModel.removeRow(0);
		}
		
		return resultJTable;
	}
	
	private JTable createDatabaseJTable(){
		
		publicationModel  = new DefaultTableModel(getTableDatabase(getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher()), getDatabaseColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class,  String.class, String.class, String.class, String.class};

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		
		JTable table = new JTable(publicationModel);
		
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
		
		for(int i = 0; i < 7; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(50);
			}else if(i == 1){
				col.setPreferredWidth(250);
			}else if(i == 2){
				col.setPreferredWidth(150);
			}else if(i == 3){
				col.setPreferredWidth(150);
			}else if(i == 4){
				col.setPreferredWidth(100);
			}else if(i == 5){
				col.setPreferredWidth(400);
			}else if(i == 6){
				col.setPreferredWidth(100);
			}
		}
		
		return table;
	}
	
	public DefaultTableModel getDefaultTableModel(){
		return publicationModel;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getDatabaseColumnName(){
		String [] columnNames = {DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher")};
		
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableDatabase(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher){
		
		Object [][] data = {addTableDatabase(rowNumber, title, author, links, year, abstracts, publisher)};
		
		return data;
		
	}
	
	public  Object [] addTableDatabase(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher){
		Object [] dataRow =  {getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher()};
		
		return dataRow;
	}
	
	//SearchPublicaitonWithAuthorField searchPubByAuthor = new SearchPublicaitonWithAuthorField();
	SearchPublicationInDatabase  searchPub = new SearchPublicationInDatabase();
	private JCheckBox titleJCheckBox;
	private JCheckBox authorJCheckBox;
	private JCheckBox yearJCheckBox;
	private JCheckBox abstractCheckBox;
	private JCheckBox publisherJCheckBox;
	private JPanel filterJPanel;
	private JComboBox yearJComboBox;
	private JCheckBox sinceYearCheckBox;
	private JComboBox endYearJComboBox;
	private JComboBox beginYearJComboBox;
		
	public void SearchResultInDatabase(String keyword){
		ArrayList<Publication> result_DBLP = new ArrayList<Publication>();
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();		
		
		/**
		 * Search by author name 
		 * **/
		if(authorJCheckBox.isSelected()){
			result = searchPub.getDBSAPublicaitonWithAuthorName(keyword);
			result_DBLP = searchPub.getDBLPPublicaitonWithAuthorName(keyword);
			
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {							
					publicationModel.insertRow(resultJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
				}				
			}
			if (result_DBLP != null) {
				for (int i = 0; i < result_DBLP.size(); i++) {					
					publicationModel.insertRow(resultJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
				}			
			}
		}
		
		/**
		 * Search by title 
		 * **/
		if(titleJCheckBox.isSelected()){
			result = searchPub.getDBSAPublicaitonWithTitle(keyword);
			result_DBLP = searchPub.getDBLPPublicaitonWithTitle(keyword);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {							
					publicationModel.insertRow(resultJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
				}			
			}
			if (result_DBLP != null) {
				for (int i = 0; i < result_DBLP.size(); i++) {						
					publicationModel.insertRow(resultJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
				}			
			}
		}
		
		/**
		 * Search by abstract
		 * **/
		if(abstractCheckBox.isSelected()){
			result = searchPub.getDBSAPublicaitonWithAbtract(keyword);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {							
					publicationModel.insertRow(resultJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
				}			
			}
		}				
		
		/**
		 * Search by publisher
		 * **/
		if(publisherJCheckBox.isSelected()){
			result = searchPub.getDBSAPublicaitonWithPublisher(keyword);
			result_DBLP = searchPub.getDBLPPublicaitonWithPublisher(keyword);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {							
					publicationModel.insertRow(resultJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
				}			
			}
			if (result_DBLP != null) {
				for (int i = 0; i < result_DBLP.size(); i++) {						
					publicationModel.insertRow(resultJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
				}			
			}
		}		
		
		/**
		 * Search by year 
		 * **/
		if(yearJCheckBox.isSelected()){
			int _year = 0;
			try{
				_year = Integer.parseInt(keyword);
				if(_year != 0){	
					result = searchPub.getDBSAPublicaitonWithYear(_year);
					result_DBLP = searchPub.getDBLPPublicaitonWithYear(_year);
				}
			}catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.year.keyowrd.is.number"));
			}
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {							
					publicationModel.insertRow(resultJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
				}			
			}
			if (result_DBLP != null) {
				for (int i = 0; i < result_DBLP.size(); i++) {						
					publicationModel.insertRow(resultJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
				}			
			}			
		}		
		tempJTable = resultJTable;
		if(resultJTable.getRowCount() <= 0){
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("your.search") + " - " + keywordJTextField.getText()  + " - " + DBSAResourceBundle.res.getString("did.not.macth.any.document"));
		}
	}
	
	private Object[] addDataDBLPToDatabaseJTable(Publication dblpPub){
		
		String _title = "";
		String _author = "";
		String _link = "";
		String _year = "";
		String _abstract = "";
		String _publisher ="";
			
		if(dblpPub.getTitle() != null){
			_title = dblpPub.getTitle();
		}
		
		if(LoadPublicaitonFromDBLP.getPublicaitonAuthorWith(dblpPub.getId()) != null ){
			_author = LoadPublicaitonFromDBLP.getPublicaitonAuthorWith(dblpPub.getId());
		}
		
		if(dblpPub.getUrl() != null){
			_link = dblpPub.getUrl();
		}
		
		if(dblpPub.getYear() == 0){
			_year = "";
		}else{
			_year = dblpPub.getYear() + "";
		}
		
		if(dblpPub.getPublisher() != null){
			_publisher = dblpPub.getPublisher();
		}		
		
		Object []data = {resultJTable.getRowCount() + 1, _title, _author, _link,
				_year, _abstract, _publisher };
		
		return data;
	}

	private Object[] addDataToDatabaseJTable(DBSAPublication dbsaPublication){
		
		String year;
		if(dbsaPublication.getYear() == 0){
			year = "";
		}else{
			year = dbsaPublication.getYear() + "";
		}
		
		Object []data = {resultJTable.getRowCount() + 1, dbsaPublication.getTitle(), dbsaPublication.getAuthors(), dbsaPublication.getLinks(),
				year, dbsaPublication.getAbstractPub(), dbsaPublication.getPublisher()};
		
		return data;
	}
	

	private JPanel getresultJPanel() {
		if (resultJPanel == null) {
			resultJPanel = new JPanel();
			resultJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("search.result"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			resultJPanel.setLayout(new GroupLayout());
			resultJPanel.add(getresultScrollPane(), new Constraints(new Bilateral(1, 0, 22), new Bilateral(0, 0, 26, 403)));
		}
		return resultJPanel;
	}

	private JButton getSearchJButton() {
		if (searchJButton == null) {
			searchJButton = new JButton();
			searchJButton.setText(DBSAResourceBundle.res.getString("search"));
			searchJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(!titleJCheckBox.isSelected() && !authorJCheckBox.isSelected() 
							&& !abstractCheckBox.isSelected() && !publisherJCheckBox.isSelected()){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.choose.fields.to.search"));
					}else{
						if(resultJTable.getRowCount() > 0){
							for(int i = resultJTable.getRowCount() - 1; i >= 0; i--){
								publicationModel.removeRow(i);
							}
						}
						searchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						if(keywordJTextField.getText() == ""){
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.key.word.to.search"));
						}else{				
							
							SearchResultInDatabase(keywordJTextField.getText());						
						}
						searchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				
			});
		}
		return searchJButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText(DBSAResourceBundle.res.getString("close"));
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
		}
		return closeJButton;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "-", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12),
					new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 102, 10, 10), new Bilateral(0, 12, 26)));
			actionsJPanel.add(getSearchJButton(), new Constraints(new Trailing(132, 102, 12, 12), new Leading(0, 35, 12, 12)));
		}
		return actionsJPanel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			inputJPanel.setBorder(BorderFactory.createTitledBorder(null, "input", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			inputJPanel.setLayout(new GroupLayout());
			inputJPanel.add(getKeywordJLabel(), new Constraints(new Leading(12, 88, 10, 10), new Leading(16, 12, 12)));
			inputJPanel.add(getAuthorJCheckBox(), new Constraints(new Leading(259, 131, 8, 8), new Leading(52, 8, 8)));
			inputJPanel.add(getSearchByJLabel(), new Constraints(new Leading(12, 99, 10, 10), new Leading(52, 25, 12, 12)));
			inputJPanel.add(getTitleJCheckBox(), new Constraints(new Leading(119, 131, 8, 8), new Leading(52, 8, 8)));
			inputJPanel.add(getKeywordJTextField(), new Constraints(new Leading(124, 547, 10, 10), new Leading(8, 36, 12, 12)));
			inputJPanel.add(getSearchJButton(), new Constraints(new Trailing(12, 102, 140, 683), new Leading(6, 36, 12, 12)));
			inputJPanel.add(getAbstractCheckBox(), new Constraints(new Leading(394, 131, 8, 8), new Leading(52, 8, 8)));
			inputJPanel.add(getPublisherJCheckBox(), new Constraints(new Leading(529, 131, 8, 8), new Leading(52, 8, 8)));
		}
		return inputJPanel;
	}

	private JTextField getKeywordJTextField() {
		if (keywordJTextField == null) {
			keywordJTextField = new JTextField();
		}
		return keywordJTextField;
	}

	private JLabel getSearchByJLabel() {
		if (searchByJLabel == null) {
			searchByJLabel = new JLabel();
			searchByJLabel.setText(DBSAResourceBundle.res.getString("search.by"));
		}
		return searchByJLabel;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setText(DBSAResourceBundle.res.getString("input.keyword"));
		}
		return keywordJLabel;
	}

	public  void setRowNumber(int number){
		rowNumber = number;
	}
	
	public   int getRowNumber(){
		return rowNumber;
	}
	
	public  void setTitle(String titleString){
		title = titleString;
	}

	public   String getTitle(){
		return title;
	}
	
	public  void setAuthor(String authorString){
		author = authorString;
	}

	public  String getAuthor(){
		return author;
	}
	
	public  void setYear(int _year){
		year = _year;
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
	public  void setLinks(String linksString){
		links = linksString;
	}
	
	public  String getLinks(){
		return links;
	}

}
