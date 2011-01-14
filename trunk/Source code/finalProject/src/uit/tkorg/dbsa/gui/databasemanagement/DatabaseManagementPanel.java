package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.actions.database.DeletePublicaitonInDBSA;
import uit.tkorg.dbsa.actions.database.DeleteSubject;
import uit.tkorg.dbsa.actions.database.LoadPublicationsFromDBSA;
import uit.tkorg.dbsa.actions.database.LoadSubject;
import uit.tkorg.dbsa.actions.database.UpdatePublicaitonsInDBSA;
import uit.tkorg.dbsa.gui.fetcher.MyJTable;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Subject;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DatabaseManagementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTable publicationJTable;
	private JScrollPane databaseJTaqbleInJScrollPane;

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
	
	private JButton closeJButton;
	private JButton resetShowDataJButton;
	private static JButton deletePublicationJButton;
	private static JButton insertPublicationJButton;
	private static JButton addSubjectJButton;
	private JPanel subjectActionsJPanel;
	private static JTable subjectJTable;
	private JScrollPane subjectContentJScrollPane;
	
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
	public DatabaseManagementPanel() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("database.management"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		setLayout(new GroupLayout());
		add(getSubjectActionsJPanel(), new Constraints(new Bilateral(0, 1, 608), new Trailing(7, 10, 10)));
		add(getSubjectContentJScrollPane(), new Constraints(new Bilateral(0, 1, 31), new Trailing(74, 141, 10, 190)));
		add(getPublicationActionsJPanel(), new Constraints(new Bilateral(0, 1, 905), new Trailing(213, 10, 129)));
		add(getDatabaseJTaqbleInJScrollPane(), new Constraints(new Bilateral(1, 2, 706), new Bilateral(0, 282, 10, 123)));
		setSize(916, 431);
	}

	private JButton getInsertPublicationJButton() {
		if (insertPublicationJButton == null) {
			insertPublicationJButton = new JButton();
			insertPublicationJButton.setText(DBSAResourceBundle.res.getString("insert"));
		}
		return insertPublicationJButton;
	}

	private JButton getDeleteSubjectJButton() {
		if (deleteSubjectJButton == null) {
			deleteSubjectJButton = new JButton();
			deleteSubjectJButton.setText(DBSAResourceBundle.res.getString("delete.sub"));
			deleteSubjectJButton.setEnabled(false);
			
			deleteSubjectJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int i = subjectJTable.getRowCount() - 1; i >= 0; i--) {
						ArrayList<Subject> subjectList = new ArrayList<Subject>();
						if (subjectModel.getValueAt(i, 3) != null && subjectModel.getValueAt(i, 3).toString().equals("true")) {
							Subject subject = new Subject();
							subject.setId(Integer.parseInt(subjectModel.getValueAt(i, 1).toString()));
							
							subjectList.add(subject);
							deleteSubject.deleteSubject(subject);
							
							subjectModel.removeRow(i);
						}
					}
					for (int j = 0; j < subjectJTable.getRowCount(); j++) {
						subjectJTable.getModel().setValueAt(j + 1, j, 0);
					}
				}
				
			});
			
		}
		return deleteSubjectJButton;
	}

	private JButton getUpdateDataJButton() {
		if (updateDataJButton == null) {
			updateDataJButton = new JButton();
			updateDataJButton.setText(DBSAResourceBundle.res.getString("update"));
			updateDataJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {	
					
					UpdatePublicaitonsInDBSA.updateListData(UpdatePublicationDatabase());
					JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("update.database.is.successfull"));
				}
				
			});
		}
		return updateDataJButton;
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
			publicationActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("publication.actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			publicationActionsJPanel.setLayout(new GroupLayout());
			publicationActionsJPanel.add(getUpdateDataJButton(), new Constraints(new Trailing(12, 97, 771, 774), new Leading(0, 29, 12, 12)));
			publicationActionsJPanel.add(getResetShowDataJButton(), new Constraints(new Trailing(127, 99, 656, 660), new Leading(0, 30, 12, 12)));
			publicationActionsJPanel.add(getDeletePublicationJButton(), new Constraints(new Trailing(244, 99, 12, 12), new Leading(-1, 30, 12, 12)));
			publicationActionsJPanel.add(getInsertPublicationJButton(), new Constraints(new Trailing(361, 97, 12, 12), new Leading(0, 29, 12, 12)));
		}
		return publicationActionsJPanel;
	}

	private JScrollPane getSubjectContentJScrollPane() {
		if (subjectContentJScrollPane == null) {
			subjectContentJScrollPane = new JScrollPane();
			subjectContentJScrollPane.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("subject.table"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			subjectContentJScrollPane.setViewportView(getSubjectJTable());
		}
		return subjectContentJScrollPane;
	}

	private JTable createSubjectJTable(){
		
		subjectModel = new DefaultTableModel(getTableData(getSubjectNumber(), getSubjectID(), getSubjectName(), getSubjectMark()), getSubjectColumnName()){
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
	
	public  Object [][] getTableData(int rowNumber, int subID, String subjectName, boolean subjectMark){
		
		Object [][] data = {addTableData(rowNumber, subID, subjectName, subjectMark)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int rowNumber,int subID, String subjectName, boolean subjectMark){
		Object [] dataRow =  {getRowNumber(), getSubjectID(), getSubjectName(), getSubjectMark()};
		
		return dataRow;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private  String [] getSubjectColumnName(){
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
		if (subjectJTable == null) {
			subjectJTable = createSubjectJTable();
			subjectModel.removeRow(0);
		}
		else if(dbsaSubjectList != null){
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
			subjectActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("subject.actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
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
			addSubjectJButton.setText(DBSAResourceBundle.res.getString("add.subject"));
			addSubjectJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					InsertSubjectFrame insertSubjectFrame = new InsertSubjectFrame(DBSAApplication.dbsaJFrame);
					
					System.out.println(subjectJTable.getRowCount());
					insertSubjectFrame.setTextfieldValue(subjectJTable.getModel().getRowCount() + 1, Integer.parseInt(subjectJTable.getModel().getValueAt(subjectJTable.getRowCount() - 1, 1).toString()) + 1);
					insertSubjectFrame.setVisible(true);

				}
			});
		}
		return addSubjectJButton;
	}

	private JButton getDeletePublicationJButton() {
		if (deletePublicationJButton == null) {
			deletePublicationJButton = new JButton();
			deletePublicationJButton.setText(DBSAResourceBundle.res.getString("delete.pub"));
			deletePublicationJButton.setEnabled(false);
			deletePublicationJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					
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
					for (int j = 0; j < publicationJTable.getRowCount(); j++) {
						publicationJTable.getModel().setValueAt(j + 1, j, 0);
					}
				}
			});
		}
		return deletePublicationJButton;
	}

	private JButton getResetShowDataJButton() {
		if (resetShowDataJButton == null) {
			resetShowDataJButton = new JButton();
			resetShowDataJButton.setText(DBSAResourceBundle.res.getString("reset.data"));
			resetShowDataJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					deleteDataOnTable(publicationJTable, publicationModel);
					ArrayList<DBSAPublication> updateDatabase = new ArrayList<DBSAPublication>();
					updateDatabase = LoadPublicationsFromDBSA.getPaper();
					if (updateDatabase != null) {
						for (int i = 0; i < updateDatabase.size(); i++) {
							publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(updateDatabase.get(i)));
						}
						if (checkRemoveRow == false) {
							checkRemoveRow = true;
							publicationModel.removeRow(0);
						}
					}
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
			closeJButton.setText("   " + DBSAResourceBundle.res.getString("close") + "   ");
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DBSAApplication.dbsaJFrame.dispose();
				}
				
			});
		}
		return closeJButton;
	}

	private JScrollPane getDatabaseJTaqbleInJScrollPane() {
		if (databaseJTaqbleInJScrollPane == null) {
			databaseJTaqbleInJScrollPane = new JScrollPane();
			databaseJTaqbleInJScrollPane.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("database"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			databaseJTaqbleInJScrollPane.setAlignmentX(1.0f);
			databaseJTaqbleInJScrollPane.setViewportView(getDatabaseJTable());
		}
		return databaseJTaqbleInJScrollPane;
	}

	public DefaultTableModel getDefaultTableModel(){
		return publicationModel;
	}
	
	private MyJTable createDatabaseJTable(){
	
		publicationModel  = new DefaultTableModel(getTableDatabase(getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher(), getMark(), getID()), getDatabaseColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class,  String.class, Integer.class, String.class, String.class, Boolean.class, Integer.class};

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
	private  String [] getDatabaseColumnName(){
		String [] columnNames = {DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher"),DBSAResourceBundle.res.getString("mark"), 
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
	
	int check = 0;
	private JPanel publicationActionsJPanel;
	private JButton updateDataJButton;
	private JButton deleteSubjectJButton;
	
	private JTable getDatabaseJTable() {
		if(publicationJTable == null) {
			publicationJTable =  createDatabaseJTable();
		}
		dbsaPublicationList = LoadPublicationsFromDBSA.getPaper();
		if(dbsaPublicationList != null){
			for(int i = 0; i < dbsaPublicationList.size(); i++){
				publicationModel.insertRow(publicationJTable.getRowCount(), addDataToDatabaseJTable(dbsaPublicationList.get(i)));
				
			}
			if(checkRemoveRow == false){
				checkRemoveRow = true;
				publicationModel.removeRow(0);
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
		Object []data = {publicationJTable.getRowCount(), dbsaPublication.getTitle(), dbsaPublication.getAuthors(), dbsaPublication.getLinks(),
				dbsaPublication.getYear(), dbsaPublication.getAbstractPub(), dbsaPublication.getPublisher(),false ,dbsaPublication.getId()};
		
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
	
	public  void setID(int id){
		this.id = id;
	}
	
	public  void setSubjectMark(boolean isSubjectMark){
		subjectMark = isSubjectMark;
	}
	
	public  boolean getSubjectMark(){
		return subjectMark;
	}
	
}
