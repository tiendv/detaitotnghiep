package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.tools.ant.util.DateUtils;
import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import uit.tkorg.dbsa.actions.database.DeletePublicaitonInDBSA;
import uit.tkorg.dbsa.actions.database.DeleteSubject;
import uit.tkorg.dbsa.actions.database.LoadPublicaitonFromDBLP;
import uit.tkorg.dbsa.actions.database.LoadPublicationsFromDBSA;
import uit.tkorg.dbsa.actions.database.LoadSubject;
import uit.tkorg.dbsa.actions.database.SearchPublicaitonWithAuthorField;
import uit.tkorg.dbsa.actions.database.SearchPublicaitonWithKeyWordInTitle;
import uit.tkorg.dbsa.gui.fetcher.MyJTable;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.gui.main.DBSAStatusBar;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;
import uit.tkorg.dbsa.model.Subject;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DatabaseManagementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTable publicationJTable;
	private static JScrollPane databaseJTableInJScrollPane;

	private static DefaultTableModel publicationModel;
	private static DefaultTableModel subjectModel;
	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String links ="";
	private static String publisher = "";
	private static boolean mark = false;
	
	private static JButton closeJButton;
	private static JButton resetShowDataJButton;
	private static JButton deletePublicationJButton;
	private static JButton selectAllPubJButton;
	private static JButton addSubjectJButton;
	private static JPanel subjectActionsJPanel;
	private static JTable subjectJTable;
	private static JScrollPane subjectContentJScrollPane;
	
	private static int subjectNumberRow = 0;
	private static int id = 0;
	private static int subjectID = 0;
	private static String subjectName = "";
	private static boolean subjectMark = false;
	
	private boolean checkRemoveRow = false;
	DeletePublicaitonInDBSA deletePublication = new DeletePublicaitonInDBSA();
	
	DeleteSubject deleteSubject = new DeleteSubject();
	
	private ArrayList<DBSAPublication> dbsaPublicationList = new ArrayList<DBSAPublication>();
	ArrayList<DBSAPublication> dbsaPublicationUpdateList = new ArrayList<DBSAPublication>();
	
	private ArrayList<Subject> dbsaSubjectList = new ArrayList<Subject>();
	
	private DBSAStatusBar dbsaStatus = new DBSAStatusBar();
	public JTabbedPane dbsaTabFrame = null;
	
	

	int check = 0;
	private static JPanel publicationActionsJPanel;
	private static JButton deleteSubjectJButton;
	private static JButton showDBLPDBButton;
	private static JButton showFetcherDBButton;
	private static JButton searchInDBJButton;
	
	public DatabaseManagementPanel(JTabbedPane dbsa) {
		super();
		initComponents();
		this.dbsaTabFrame = dbsa;
	}

	private void initComponents() {
		
		setLayout(new GroupLayout());
		add(getSubjectActionsJPanel(), new Constraints(new Bilateral(0, 1, 608), new Trailing(7, 10, 10)));
		add(getSubjectContentJScrollPane(), new Constraints(new Bilateral(0, 1, 31), new Trailing(74, 141, 10, 190)));
		add(getPublicationActionsJPanel(), new Constraints(new Bilateral(0, 1, 905), new Trailing(213, 10, 129)));
		add(getdatabaseJTableInJScrollPane(), new Constraints(new Bilateral(1, 2, 706), new Bilateral(0, 282, 10, 123)));
		setSize(916, 431);
		updateTextsOfComponents();
	}
	
	private JButton getSearchInDBJButton() {
		if (searchInDBJButton == null) {
			searchInDBJButton = new JButton();			
			searchInDBJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					SearchInDatabasePanel searchPnl = new SearchInDatabasePanel(DBSAApplication.dbsaJFrame);
					searchPnl.setVisible(true);					
				}				
			});
			searchInDBJButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("search.in.database"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
				
			});
		}
		return searchInDBJButton;
	}
	
	public Date addDays() {
		  Calendar cal = Calendar.getInstance();
		  
		  return cal.getTime();
		}
	
	private JButton getShowFetcherDBButton() {
		if (showFetcherDBButton == null) {
			showFetcherDBButton = new JButton();			
			showFetcherDBButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
					deleteDataOnTable(publicationJTable, publicationModel);
					
					ConfigToShowFetcherDBPanel configPnl = new ConfigToShowFetcherDBPanel(DBSAApplication.dbsaJFrame);
					
					configPnl.setVisible(true);
					
					publicationJTable.getColumn("Mark").setWidth(100);
					publicationJTable.getColumn("Mark").setMinWidth(100);
					publicationJTable.getColumn("Mark").setMaxWidth(100);
										
					publicationJTable.getColumn("Mark").setWidth(100);
					publicationJTable.getColumn("Mark").setMinWidth(100);
					publicationJTable.getColumn("Mark").setMaxWidth(100);
					
					resetShowDataJButton.setVisible(true);
					selectAllPubJButton.setVisible(true);
					deletePublicationJButton.setVisible(true);
				}
				
			});
			
			showFetcherDBButton.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("show.fetcher.database"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return showFetcherDBButton;
	}

	LoadPublicaitonFromDBLP dblpDB = new LoadPublicaitonFromDBLP();
	private JButton getShowDBLPDBButton() {
		if (showDBLPDBButton == null) {
			showDBLPDBButton = new JButton();
			showDBLPDBButton.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteDataOnTable(publicationJTable, publicationModel);
					
					ConfigToShowDBPanel configPnl = new ConfigToShowDBPanel(DBSAApplication.dbsaJFrame);
					configPnl.setVisible(true);				
				}
				
			});
			
			showDBLPDBButton.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("show.dblp.database"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return showDBLPDBButton;
	}
	
	public void ShowDBLPDatabase(int numberResult, int beginResult){
		ArrayList<Publication> updateDatabase = new ArrayList<Publication>();
		
		updateDatabase = dblpDB.getPublicationsWithIDSpace(beginResult, beginResult + numberResult);

		if (updateDatabase != null) {
			for (int i = 1; i < updateDatabase.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataDBLPToDatabaseJTable(updateDatabase.get(i)));
			}
			if (checkRemoveRow == false) {
				checkRemoveRow = true;
				
			}						
			
			publicationJTable.getColumn("Mark").setWidth(0);
			publicationJTable.getColumn("Mark").setMinWidth(0);
			publicationJTable.getColumn("Mark").setMaxWidth(0);
			
			resetShowDataJButton.setVisible(false);
			selectAllPubJButton.setVisible(false);
			deletePublicationJButton.setVisible(false);
		}
	}
	
	public void ShowDBLPDatabaseByDate(String date){
		ArrayList<Publication> updateDatabase = new ArrayList<Publication>();
		
		updateDatabase = dblpDB.getPublicationsWithDate(date);

		if (updateDatabase != null) {
			for (int i = 1; i < updateDatabase.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataDBLPToDatabaseJTable(updateDatabase.get(i)));
			}
			if (checkRemoveRow == false) {
				checkRemoveRow = true;
				
			}						
			
			publicationJTable.getColumn("Mark").setWidth(0);
			publicationJTable.getColumn("Mark").setMinWidth(0);
			publicationJTable.getColumn("Mark").setMaxWidth(0);
			
			resetShowDataJButton.setVisible(false);
			selectAllPubJButton.setVisible(false);
			deletePublicationJButton.setVisible(false);
		}
	}
	
	public void ShowFetcherDatabase(){
		ArrayList<DBSAPublication> updateDatabase = new ArrayList<DBSAPublication>();
		updateDatabase = LoadPublicationsFromDBSA.getPaper();
							
		if (updateDatabase != null) {
								
			for (int i = 0; i < updateDatabase.size(); i++) {
				selectAllPubJButton.setEnabled(true);
				
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(updateDatabase.get(i)));
			}
			if (checkRemoveRow == false) {
				checkRemoveRow = true;
				
			}
		}
	}
	
	public void ShowFetcherDatabaseByDate(String date){
		ArrayList<DBSAPublication> updateDatabase = new ArrayList<DBSAPublication>();
		updateDatabase = LoadPublicationsFromDBSA.getPublicationsWithDate(date);
							
		if (updateDatabase != null) {
								
			for (int i = 0; i < updateDatabase.size(); i++) {
				selectAllPubJButton.setEnabled(true);
				
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(updateDatabase.get(i)));
			}
			if (checkRemoveRow == false) {
				checkRemoveRow = true;
				
			}
		}else{
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("no.results.found"));
		}
	}
	
	SearchPublicaitonWithAuthorField searchPubByAuthor = new SearchPublicaitonWithAuthorField();
	SearchPublicaitonWithKeyWordInTitle  searchPubByTitle = new SearchPublicaitonWithKeyWordInTitle();
	
	public void SearchResultInDatabase(String keyword, int searchBy){
		ArrayList<Publication> result_DBLP = new ArrayList<Publication>();
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();		
		
		
		if(searchBy == 0){
			result = searchPubByAuthor.getDBSAPublicaitonWithAuthorName(keyword);
		}
		if (result != null) {
			for (int i = 1; i < result.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
			}
			
		}
		
		if(searchBy == 0){
			result_DBLP = searchPubByAuthor.getDBLPPublicaitonWithAuthorName(keyword);
		}
		if (result_DBLP != null) {
			for (int i = 1; i < result_DBLP.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
			}			
		}
		
		if(searchBy == 1){
			result = searchPubByTitle.getDBSAPublicaitonWithTitle(keyword);
		}
		if (result != null) {
			for (int i = 1; i < result.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(result.get(i)));
			}
			
		}
		
		if(searchBy == 1){
			result_DBLP = searchPubByTitle.getDBLPPublicaitonWithTitle(keyword);
		}
		if (result_DBLP != null) {
			for (int i = 1; i < result_DBLP.size(); i++) {
				selectAllPubJButton.setEnabled(true);							
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataDBLPToDatabaseJTable(result_DBLP.get(i)));
			}			
		}
		
		
		publicationJTable.getColumn("Mark").setWidth(0);
		publicationJTable.getColumn("Mark").setMinWidth(0);
		publicationJTable.getColumn("Mark").setMaxWidth(0);
		
		resetShowDataJButton.setVisible(false);
		selectAllPubJButton.setVisible(false);
		deletePublicationJButton.setVisible(false);
	}
	
	public void updateTextsOfComponents(){
		databaseJTableInJScrollPane.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("database.management"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		selectAllPubJButton.setText(DBSAResourceBundle.res.getString("select.all"));
		deleteSubjectJButton.setText(DBSAResourceBundle.res.getString("delete.sub"));
		publicationActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("publication.actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		subjectContentJScrollPane.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("subject.table"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
				Font.BOLD, 12), new Color(51, 51, 51)));
		getSubjectColumnName();
		subjectActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("subject.actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
				"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		addSubjectJButton.setText(DBSAResourceBundle.res.getString("add.subject"));
		deletePublicationJButton.setText(DBSAResourceBundle.res.getString("delete.pub"));
		showDBLPDBButton.setText(DBSAResourceBundle.res.getString("show.dblp.db"));
		showFetcherDBButton.setText(DBSAResourceBundle.res.getString("show.fetcher.db"));
		resetShowDataJButton.setText(DBSAResourceBundle.res.getString("refresh"));
		closeJButton.setText("   " + DBSAResourceBundle.res.getString("close") + "   ");
		searchInDBJButton.setText(DBSAResourceBundle.res.getString("search.in.database"));
		databaseJTableInJScrollPane.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("database"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		getDatabaseColumnName();

		searchInDBJButton.setToolTipText(DBSAResourceBundle.res.getString("search.in.database"));
		showFetcherDBButton.setToolTipText(DBSAResourceBundle.res.getString("show.fetcher.database"));
		showDBLPDBButton.setToolTipText(DBSAResourceBundle.res.getString("show.dblp.database"));
	}

	private JButton getselectAllPubJButton() {
		if (selectAllPubJButton == null) {
			selectAllPubJButton = new JButton();
			selectAllPubJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					//dbsaTabFrame.setSelectedIndex(2);
					for(int i = 0; i < publicationJTable.getRowCount(); i++){
						
						publicationJTable.getModel().setValueAt(true, i, 7);
					}
					if(publicationJTable.getRowCount() == 0){
						selectAllPubJButton.setEnabled(false);
					}else
						deletePublicationJButton.setEnabled(true);
				}
				
			});
			selectAllPubJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("select.all.the.article"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return selectAllPubJButton;
	}
	
	private static boolean checkToDel = false;
	private JButton getDeleteSubjectJButton() {
		
	
		
		if (deleteSubjectJButton == null) {
			deleteSubjectJButton = new JButton();
			deleteSubjectJButton.setEnabled(false);
			
			deleteSubjectJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					checkToDel = true;
					// TODO Auto-generated method stub
					for (int i = subjectJTable.getRowCount() - 1; i >= 0; i--) {
						ArrayList<Subject> subjectList = new ArrayList<Subject>();
						if (subjectModel.getValueAt(i, 3) != null && subjectModel.getValueAt(i, 3).toString().equals("true")) {
							Subject subject = new Subject();
							subject.setId(Integer.parseInt(subjectModel.getValueAt(i, 1).toString()));
							
							if(subject.getId() > 43){
								subjectList.add(subject);
								deleteSubject.deleteSubject(subject);
								
								subjectModel.removeRow(i);
							}else{
								subjectModel.setValueAt(false ,i, 3);
								
								if(checkToDel == true){
									JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("can.not.delete.the.subject"));
									checkToDel = false;
								}
							}
						}
					}
					for (int j = 0; j < subjectJTable.getRowCount(); j++) {
						subjectJTable.getModel().setValueAt(j + 1, j, 0);
					}
					
					deleteSubjectJButton.setEnabled(false);				
				}				
			});
			
			deleteSubjectJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("delete.all.the.subjects.is.selected"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return deleteSubjectJButton;
	}

	/*
	 * Ham update data tu JTable xuong database
	 * @return DBSA Publication List
	 */
	public ArrayList<DBSAPublication> UpdatePublicationDatabase(){
	
		for(int i = 0; i < publicationJTable.getRowCount(); i++){
			DBSAPublication dbsaPublication = new DBSAPublication();
			
			dbsaPublication.setId(Integer.parseInt(publicationJTable.getModel().getValueAt(i, 8).toString()));
			dbsaPublication.setTitle(publicationJTable.getModel().getValueAt(i, 1).toString());
			dbsaPublication.setAuthors(publicationJTable.getModel().getValueAt(i, 2).toString());
			dbsaPublication.setLinks(publicationJTable.getModel().getValueAt(i, 3).toString());
			dbsaPublication.setYear(Integer.parseInt(publicationJTable.getModel().getValueAt(i, 4).toString()));
			dbsaPublication.setAbstractPub(publicationJTable.getModel().getValueAt(i, 5).toString());
			dbsaPublication.setPublisher(publicationJTable.getModel().getValueAt(i, 6).toString());
			System.out.println(dbsaPublication.getAuthors());
			
			dbsaPublicationUpdateList.add(dbsaPublication);
		}
		
//		for(int j = 0; j < dbsaPublicationUpdateList.size(); j++){
//			System.out.println(dbsaPublicationUpdateList.get(j).getAuthors());
//		}
		return dbsaPublicationUpdateList;
	}
	
	
	private JPanel getPublicationActionsJPanel() {
		if (publicationActionsJPanel == null) {
			publicationActionsJPanel = new JPanel();
			publicationActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "publication.actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			publicationActionsJPanel.setLayout(new GroupLayout());
			publicationActionsJPanel.add(getShowDBLPDBButton(), new Constraints(new Trailing(12, 161, 10, 10), new Leading(0, 29, 12, 12)));
			publicationActionsJPanel.add(getShowFetcherDBButton(), new Constraints(new Trailing(191, 162, 12, 12), new Leading(0, 29, 12, 12)));
			publicationActionsJPanel.add(getSearchInDBJButton(), new Constraints(new Trailing(371, 162, 361, 384), new Leading(0, 29, 12, 12)));
			publicationActionsJPanel.add(getResetShowDataJButton(), new Constraints(new Trailing(551, 99, 241, 247), new Leading(-1, 30, 12, 12)));
			publicationActionsJPanel.add(getDeletePublicationJButton(), new Constraints(new Trailing(668, 99, 118, 130), new Leading(0, 30, 12, 12)));
			publicationActionsJPanel.add(getselectAllPubJButton(), new Constraints(new Trailing(785, 97, 12, 12), new Leading(0, 29, 12, 12)));
		}
		return publicationActionsJPanel;
	}

	private JScrollPane getSubjectContentJScrollPane() {
		if (subjectContentJScrollPane == null) {
			subjectContentJScrollPane = new JScrollPane();
			subjectContentJScrollPane.setViewportView(getSubjectJTable());
		}
		return subjectContentJScrollPane;
	}

	private JTable createSubjectJTable(){
		
		subjectModel = new DefaultTableModel(getSubjectTableData(getSubjectNumber(), getSubjectID(), getSubjectName(), getSubjectMark()), getSubjectColumnName()){
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, Integer.class, String.class, Boolean.class};

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		
		JTable table = new JTable(subjectModel);
		
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
		
		for(int i = 0; i < 3; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(100);
			}else if(i == 1){
				col.setPreferredWidth(300);
			}else if(i == 2){
				col.setPreferredWidth(600);
			}
		}
		
		return table;
	}
	
	public  Object [][] getSubjectTableData(int rowNumber, int subID, String subjectName, boolean subjectMark){
		
		Object [][] data = {addSubjectTableData(rowNumber, subID, subjectName, subjectMark)};
		
		return data;
		
	}
	
	public  Object [] addSubjectTableData(int rowNumber,int subID, String subjectName, boolean subjectMark){
		Object [] dataRow =  {rowNumber, subID, subjectName, subjectMark};
		
		return dataRow;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getSubjectColumnName(){
		String [] columnNames = {DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("subject.id"), 
				DBSAResourceBundle.res.getString("subject.name"), DBSAResourceBundle.res.getString("subject.mark")};
			
		return columnNames;
	}
	
	private Object[] addDataToSubjectTable(Subject subject){
		Object []subjectData = {subjectJTable.getRowCount() + 1, subject.getId(), subject.getSbj_name()};
		
		return subjectData;
	}
	
	int checkMarkSubject = 0;
	
	public JTable getSubjectJTable() {
		
		dbsaSubjectList = LoadSubject.getSubject();
		if(subjectJTable == null) {
			subjectJTable = createSubjectJTable();
			subjectModel.removeRow(0);
		}
		if(dbsaSubjectList != null){
			for(int i = 0; i < dbsaSubjectList.size(); i++){
		
				subjectModel.insertRow(subjectJTable.getRowCount(), addDataToSubjectTable(dbsaSubjectList.get(i)));
			}	
		}
		
		subjectJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkMarkSubject = 0;
				if(subjectJTable != null){
					
					for(int j = 0; j < subjectJTable.getRowCount(); j++){
						if(subjectJTable.getModel().getValueAt(j, 3) != null
							&& subjectJTable.getModel().getValueAt(j, 3).toString().equals("true")){
							checkMarkSubject ++;
						}
					}
				}
				if(checkMarkSubject != 0){
					deleteSubjectJButton.setEnabled(true);
				}else{
					deleteSubjectJButton.setEnabled(false);
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
	
		return subjectJTable;
	}
	
	public void InsertSubject(int rowCount, int id, String subjectName){
		Object []data = {rowCount + 1, id, subjectName};
		subjectModel.insertRow(rowCount , data);
	}

	private JPanel getSubjectActionsJPanel() {
		if (subjectActionsJPanel == null) {
			subjectActionsJPanel = new JPanel();
			subjectActionsJPanel.setLayout(new GroupLayout());
			subjectActionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 99, 776, 781), new Leading(0, 29, 12, 12)));
			subjectActionsJPanel.add(getDeleteSubjectJButton(), new Constraints(new Trailing(129, 100, 663, 663), new Leading(0, 29, 12, 12)));
			subjectActionsJPanel.add(getAddJButton(), new Constraints(new Trailing(247, 12, 12), new Leading(0, 29, 12, 12)));
		}
		return subjectActionsJPanel;
	}

	private JButton getAddJButton() {
		if (addSubjectJButton == null) {
			addSubjectJButton = new JButton();
			//addSubjectJButton.setEnabled(false);
			
			addSubjectJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					InsertSubjectFrame insertSubjectFrame = new InsertSubjectFrame(DBSAApplication.dbsaJFrame);
					
					if(subjectJTable == null || subjectJTable.getRowCount() == 0){
						insertSubjectFrame.setTextfieldValue( 1, 1);
					}else{
						insertSubjectFrame.setTextfieldValue(subjectJTable.getModel().getRowCount() + 1, Integer.parseInt(subjectJTable.getModel().getValueAt(subjectJTable.getRowCount() - 1, 1).toString()) + 1);
					}
					insertSubjectFrame.setVisible(true);

				}
			});
			
			addSubjectJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("add.new.subject"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return addSubjectJButton;
	}

	private JButton getDeletePublicationJButton() {
		if (deletePublicationJButton == null) {
			deletePublicationJButton = new JButton();
			deletePublicationJButton.setEnabled(false);
			deletePublicationJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					
					int n = JOptionPane.showConfirmDialog(
						    DBSAApplication.dbsaJFrame, DBSAResourceBundle.res.getString("ask.before.delete.publication"),
						    "An Question", JOptionPane.YES_NO_OPTION);
						
					if(n == JOptionPane.YES_OPTION){
							removeRowsIsSelected();
					}else if(n == JOptionPane.NO_OPTION){
							
					}
				}
			});
			
			deletePublicationJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("delete.all.the.articles.is.selected"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return deletePublicationJButton;
	}

	public void removeRowsIsSelected(){
		for (int i = publicationJTable.getRowCount() - 1; i >= 0; i--){
			ArrayList<DBSAPublication> deletePublicationList = new ArrayList<DBSAPublication>();
			if (publicationModel.getValueAt(i, 7) != null && publicationModel.getValueAt(i, 7).toString().equals("true")) {
				DBSAPublication dbsaPublication = new DBSAPublication();
				dbsaPublication.setId(Integer.parseInt(publicationModel.getValueAt(i, 8).toString()));
				
				publicationModel.removeRow(i);
				
				deletePublicationList.add(dbsaPublication);
				deletePublication.deletePublication(dbsaPublication);
			}
		}
		if(publicationJTable.getRowCount() == 0){
			deletePublicationJButton.setEnabled(false);
		}
		for (int j = 0; j < publicationJTable.getRowCount(); j++) {
			publicationJTable.getModel().setValueAt(j + 1, j, 0);
		}
	
	}
	
	private JButton getResetShowDataJButton() {
		if (resetShowDataJButton == null) {
			resetShowDataJButton = new JButton();
			resetShowDataJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					deleteDataOnTable(publicationJTable, publicationModel);
					ArrayList<DBSAPublication> updateDatabase = new ArrayList<DBSAPublication>();
					updateDatabase = LoadPublicationsFromDBSA.getPaper();
					if (updateDatabase != null) {
						for (int i = 0; i < updateDatabase.size(); i++) {
							selectAllPubJButton.setEnabled(true);
							
							publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(updateDatabase.get(i)));
						}
						if (checkRemoveRow == false) {
							checkRemoveRow = true;
							
						}
						
					}
				}
			});
			
			resetShowDataJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("refresh.article.list"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return resetShowDataJButton;
	}

	public void deleteDataOnTable(JTable table, DefaultTableModel model){
		table = new JTable(model);
		for(int i = table.getRowCount() - 1; i >= 0; i--){
			model.removeRow(i);
		}
	}
	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DBSAApplication.dbsaJFrame.dispose();
				}				
			});
			
			closeJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("press.to.close.program"));
				}
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return closeJButton;
	}

	private JScrollPane getdatabaseJTableInJScrollPane() {
		if (databaseJTableInJScrollPane == null) {
			databaseJTableInJScrollPane = new JScrollPane();			
			databaseJTableInJScrollPane.setAlignmentX(1.0f);
			databaseJTableInJScrollPane.setViewportView(getDatabaseJTable());
		}
		return databaseJTableInJScrollPane;
	}

	public DefaultTableModel getDefaultTableModel(){
		return publicationModel;
	}
	
	private MyJTable createDatabaseJTable(){
	
		publicationModel  = new DefaultTableModel(getTableDatabase(getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher(), getMark(), getID()), getDatabaseColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class,  String.class, String.class, String.class, String.class, Boolean.class, Integer.class};

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		
		MyJTable table = new MyJTable(publicationModel);
		
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
		table.getColumn(DBSAResourceBundle.res.getString("id")).setWidth(0);
		table.getColumn(DBSAResourceBundle.res.getString("id")).setMinWidth(0);
		table.getColumn(DBSAResourceBundle.res.getString("id")).setMaxWidth(0);
		
		for(int i = 0; i < 6; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(5);
			}else if(i == 1){
				col.setPreferredWidth(250);
			}else if(i == 2){
				col.setPreferredWidth(150);
			}else if(i == 3){
				col.setPreferredWidth(150);
			}else if(i == 4){
				col.setPreferredWidth(20);
			}else if(i == 5){
				col.setPreferredWidth(500);
			}else if (i == 6){
				col.setPreferredWidth(100);
			}else if (i == 7){
				col.setPreferredWidth(5);
			}
		}
		
		return table;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getDatabaseColumnName(){
		String [] columnNames = {DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher"),"Mark", 
				DBSAResourceBundle.res.getString("id")};
		
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableDatabase(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher, boolean isMark, int id){
		
		Object [][] data = {addTableDatabase(rowNumber, title, author, links, year, abstracts, publisher, isMark, id)};
		
		return data;
		
	}
	
	public  Object [] addTableDatabase(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher, boolean isMark, int id){
		Object [] dataRow =  {getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher(), getMark(), getID()};
		
		return dataRow;
	}
	
	
	private JTable getDatabaseJTable() {
		if(publicationJTable == null) {
			publicationJTable =  createDatabaseJTable();
			publicationModel.removeRow(0);
		}
		dbsaPublicationList = LoadPublicationsFromDBSA.getPaper();
		if(dbsaPublicationList != null){
			for(int i = 0; i < dbsaPublicationList.size(); i++){
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(dbsaPublicationList.get(i)));
				
			}			
		}
		
		publicationJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				check = 0;
				if(publicationJTable != null){
					for(int i = 0; i < publicationJTable.getRowCount(); i++){
						
						if(publicationModel.getValueAt(i, 7) != null 
						&& publicationModel.getValueAt(i, 7).toString().equals("true")){
							
							check ++;					
						}
					}
					
					if(check == 0){
						deletePublicationJButton.setEnabled(false);
					}else{
						deletePublicationJButton.setEnabled(true);
					}
				}
			}

			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			
		});
		
		return publicationJTable;
	}

	private Object[] addDataToDatabaseJTable(DBSAPublication dbsaPublication){
		
		String year;
		if(dbsaPublication.getYear() == 0){
			year = "";
		}else{
			year = dbsaPublication.getYear() + "";
		}
		
		Object []data = {publicationJTable.getRowCount() + 1, dbsaPublication.getTitle(), dbsaPublication.getAuthors(), dbsaPublication.getLinks(),
				year, dbsaPublication.getAbstractPub(), dbsaPublication.getPublisher(),false ,dbsaPublication.getId()};
		
		return data;
	}
	
	/**
	 * 
	 * @param dblpPublication
	 * @return
	 */
	
	private Object[] addDataDBLPToDatabaseJTable(Publication dblpPub){
		
		String _title = "";
		String _author = "";
		String _link = "";
		String _year = "";
		String _abstract = "";
		String _publisher ="";
		int _id = 0;
		
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
		
		id = dblpPub.getId();
				
		Object []data = {publicationJTable.getRowCount() + 1, _title, _author, _link,
				_year, _abstract, _publisher, false, id };
		
		return data;
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
	
	public  void setMark(boolean isMark){
		mark = isMark;
	}
	
	public  boolean getMark(){
		return mark;
	}
	
	public  void setSubjectNumber(int number){
		subjectNumberRow = number;
	}
	
	public   int getSubjectNumber(){
		return subjectNumberRow;
	}
	
	public  void setSubjectID(int number){
		subjectID = number;
	}
	
	public   int getSubjectID(){
		return subjectID;
	}
	
	public  void setSubjectName(String name){
		subjectName = name;
	}
	
	public  String getSubjectName(){
		return subjectName;
	}
	
	public   int getID(){
		return id;
	}
	
	public  void setID(int ID){
		id = ID;
	}
	
	public  void setSubjectMark(boolean isSubjectMark){
		subjectMark = isSubjectMark;
	}
	
	public  boolean getSubjectMark(){
		return subjectMark;
	}
	
}
