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
import uit.tkorg.dbsa.actions.database.InsertSubject;
import uit.tkorg.dbsa.actions.database.LoadPublicationsFromDBSA;
import uit.tkorg.dbsa.actions.database.LoadSubject;
import uit.tkorg.dbsa.gui.fetcher.MyJTable;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Subject;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DatabaseManagementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable databaseJTable;
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
	private static JButton deleteJButton;
	private static JButton addJButton;
	private JPanel subjectActionsJPanel;
	private JTable subjectJTable;
	private JScrollPane subjectContentJScrollPane;
	
	private static int subjectNumberRow = 0;
	private static int id = 0;
	private static int subjectID = 0;
	private static String subjectName = "";
	private static boolean subjectMark = false;
	
	private boolean checkRemoveRow = false;
//	private boolean checkRemoveSubjectRow = false;
	DeletePublicaitonInDBSA deletePublication = new DeletePublicaitonInDBSA();
	private ArrayList<DBSAPublication> dbsaPublicationList = new ArrayList<DBSAPublication>();
	private ArrayList<Subject> dbsaSubjectList = new ArrayList<Subject>();
	public DatabaseManagementPanel() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(null, "database.management", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		setLayout(new GroupLayout());
		add(getSubjectActionsJPanel(), new Constraints(new Bilateral(0, 1, 608), new Trailing(7, 10, 10)));
		add(getSubjectContentJScrollPane(), new Constraints(new Bilateral(0, 1, 31), new Trailing(74, 141, 10, 190)));
		add(getPublicationActionsJPanel(), new Constraints(new Bilateral(0, 1, 905), new Trailing(213, 10, 129)));
		add(getDatabaseJTaqbleInJScrollPane(), new Constraints(new Bilateral(1, 2, 706), new Bilateral(0, 282, 10, 123)));
		setSize(916, 431);
	}

	private JButton getDeleteSubjectJButton() {
		if (deleteSubjectJButton == null) {
			deleteSubjectJButton = new JButton();
			deleteSubjectJButton.setText("Delete Sub");
		}
		return deleteSubjectJButton;
	}

	private JButton getUpdateDataJButton() {
		if (updateDataJButton == null) {
			updateDataJButton = new JButton();
			updateDataJButton.setText("Update");
		}
		return updateDataJButton;
	}

	private JPanel getPublicationActionsJPanel() {
		if (publicationActionsJPanel == null) {
			publicationActionsJPanel = new JPanel();
			publicationActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Publication actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			publicationActionsJPanel.setLayout(new GroupLayout());
			publicationActionsJPanel.add(getUpdateDataJButton(), new Constraints(new Trailing(12, 97, 771, 774), new Leading(0, 29, 12, 12)));
			publicationActionsJPanel.add(getResetShowDataJButton(), new Constraints(new Trailing(127, 99, 656, 660), new Leading(0, 30, 12, 12)));
			publicationActionsJPanel.add(getDeleteJButton(), new Constraints(new Trailing(244, 99, 12, 12), new Leading(-1, 30, 12, 12)));
		}
		return publicationActionsJPanel;
	}

	private JScrollPane getSubjectContentJScrollPane() {
		if (subjectContentJScrollPane == null) {
			subjectContentJScrollPane = new JScrollPane();
			subjectContentJScrollPane.setBorder(BorderFactory.createTitledBorder(null, /*DBSAResourceBundle.res.getString*/("subject.table"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
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
//		table.getColumn(/*DBSAResourceBundle.res.getString*/("subject.mark")).setWidth(0);
//		table.getColumn(/*DBSAResourceBundle.res.getString*/("subject.mark")).setMaxWidth(0);
//		table.getColumn(/*DBSAResourceBundle.res.getString*/("subject.mark")).setMinWidth(0);
//		
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
		String [] columnNames = {/*DBSAResourceBundle.res.getString*/("no"), /*DBSAResourceBundle.res.getString*/("subject.id"), 
				/*DBSAResourceBundle.res.getString*/("subject.name"), /*DBSAResourceBundle.res.getString*/("subject.mark")};
			
		return columnNames;
	}
	
	private Object[] addDataToSubjectTable(Subject subject){
		Object []subjectData = {subjectJTable.getRowCount(), subject.getId(), subject.getSbj_name()};
		
		return subjectData;
	}
	
	int checkMarkSubject = 0;
	private JTable getSubjectJTable() {
		if (subjectJTable == null) {
			subjectJTable = createSubjectJTable();
		}
		
		dbsaSubjectList = LoadSubject.getSubject();
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
					int n = subjectJTable.getSelectedRow();
					if(n == 0)
						addJButton.setEnabled(true);
					else
						addJButton.setEnabled(false);
					
					for(int j = 0; j < subjectJTable.getRowCount(); j++){
						if(subjectJTable.getModel().getValueAt(j, 3) != null
							&& subjectJTable.getModel().getValueAt(j, 3).toString().equals("true")){
							checkMarkSubject ++;
						}
					}
				}
				if(checkMarkSubject > 0){
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

	private JPanel getSubjectActionsJPanel() {
		if (subjectActionsJPanel == null) {
			subjectActionsJPanel = new JPanel();
			subjectActionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Subject actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			subjectActionsJPanel.setLayout(new GroupLayout());
			subjectActionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 99, 776, 781), new Leading(0, 29, 12, 12)));
			subjectActionsJPanel.add(getDeleteSubjectJButton(), new Constraints(new Trailing(129, 100, 663, 663), new Leading(0, 29, 12, 12)));
			subjectActionsJPanel.add(getAddJButton(), new Constraints(new Trailing(247, 12, 12), new Leading(0, 29, 12, 12)));
		}
		return subjectActionsJPanel;
	}

	private JButton getAddJButton() {
		if (addJButton == null) {
			addJButton = new JButton();
			addJButton.setEnabled(false);
			addJButton.setText(/*DBSAResourceBundle.res.getString*/("add.subject"));
			addJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
						//int n = subjectJTable.getSelectedRow();
					if(subjectModel.getValueAt(0, 0).toString().replaceAll(" ", "").equals(0)){
						addJButton.setEnabled(true);
						
						int i = subjectModel.getRowCount();
						Object []data = {i , i, subjectModel.getValueAt(0, 2).toString()};
						subjectModel.insertRow(i, data);
						
						Subject sb = new Subject();
						sb.setSbj_name(subjectModel.getValueAt(0, 2).toString());
						
						InsertSubject insert  = new InsertSubject();
						insert.InsertSubjectOfPublication(sb);
						
						System.out.println(subjectModel.getRowCount());
					}
				}
			});
		}
		return addJButton;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setText("Delete Pub");
			deleteJButton.setEnabled(false);
			deleteJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					for (int i = databaseJTable.getRowCount() - 1; i >= 0; i--) {
						ArrayList<DBSAPublication> deletePublicationList = new ArrayList<DBSAPublication>();
						if (publicationModel.getValueAt(i, 7) != null && publicationModel.getValueAt(i, 7).toString().equals("true")) {
							DBSAPublication dbsaPublication = new DBSAPublication();
							dbsaPublication.setId(Integer.parseInt(publicationModel.getValueAt(i, 8).toString()));
							dbsaPublication.setTitle(publicationModel.getValueAt(i, 1).toString());
							dbsaPublication.setAuthors(publicationModel.getValueAt(i, 2).toString());
							dbsaPublication.setLinks(publicationModel.getValueAt(i, 3).toString());
							dbsaPublication.setYear(Integer.parseInt(publicationModel.getValueAt(i, 4).toString()));
							dbsaPublication.setAbstractPub(publicationModel.getValueAt(i, 5).toString());
							if (publicationModel.getValueAt(i, 6) == null) {
								dbsaPublication.setPublisher("");
							} else
								dbsaPublication.setPublisher(publicationModel.getValueAt(i, 6).toString());
							deletePublicationList.add(dbsaPublication);
							deletePublication.deletePublication(dbsaPublication);
							publicationModel.removeRow(i);
						}
					}
					for (int j = 0; j < databaseJTable.getRowCount(); j++) {
						databaseJTable.getModel().setValueAt(j + 1, j, 0);
					}
				}
			});
		}
		return deleteJButton;
	}

	private JButton getResetShowDataJButton() {
		if (resetShowDataJButton == null) {
			resetShowDataJButton = new JButton();
			resetShowDataJButton.setText("Reset Data");
			resetShowDataJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					deleteDataOnTable(databaseJTable, publicationModel);
					ArrayList<DBSAPublication> updateDatabase = new ArrayList<DBSAPublication>();
					updateDatabase = LoadPublicationsFromDBSA.getPaper();
					if (updateDatabase != null) {
						for (int i = 0; i < updateDatabase.size(); i++) {
							publicationModel.insertRow(databaseJTable.getRowCount(), addDataToDatabaseJTable(updateDatabase.get(i)));
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
			closeJButton.setText("   " + /*DBSAResourceBundle.res.getString*/("close") + "   ");
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
			databaseJTaqbleInJScrollPane.setBorder(BorderFactory.createTitledBorder(null, /*DBSAResourceBundle.res.getString*/("database"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
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
		table.getColumn(/*DBSAResourceBundle.res.getString*/("id")).setWidth(0);
		table.getColumn(/*DBSAResourceBundle.res.getString*/("id")).setMinWidth(0);
		table.getColumn(/*DBSAResourceBundle.res.getString*/("id")).setMaxWidth(0);
		
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
		String [] columnNames = {/*DBSAResourceBundle.res.getString*/("no"), /*DBSAResourceBundle.res.getString*/("title"), 
				/*DBSAResourceBundle.res.getString*/("authors"), /*DBSAResourceBundle.res.getString*/("link"),
				/*DBSAResourceBundle.res.getString*/("year"),/*DBSAResourceBundle.res.getString*/("abstract"), 
				/*DBSAResourceBundle.res.getString*/("publisher"),/*DBSAResourceBundle.res.getString*/("mark"), 
				/*DBSAResourceBundle.res.getString*/("id")};
		
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
		if (databaseJTable == null) {
			databaseJTable =  createDatabaseJTable();
		}
		dbsaPublicationList = LoadPublicationsFromDBSA.getPaper();
		if(dbsaPublicationList != null){
			for(int i = 0; i < dbsaPublicationList.size(); i++){
				publicationModel.insertRow(databaseJTable.getRowCount(), addDataToDatabaseJTable(dbsaPublicationList.get(i)));
				
			}
			if(checkRemoveRow == false){
				checkRemoveRow = true;
				publicationModel.removeRow(0);
			}
		}
		
		databaseJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				check = 0;
				if(databaseJTable != null){
					for(int i = 0; i < databaseJTable.getRowCount(); i++){
						
						if(publicationModel.getValueAt(i, 7) != null 
						&& publicationModel.getValueAt(i, 7).toString().equals("true")){
							
							check ++;					
						}
					}
					
					if(check == 0){
						deleteJButton.setEnabled(false);
					}else{
						deleteJButton.setEnabled(true);
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
		
		return databaseJTable;
	}

	private Object[] addDataToDatabaseJTable(DBSAPublication dbsaPublication){
		Object []data = {databaseJTable.getRowCount(), dbsaPublication.getTitle(), dbsaPublication.getAuthors(), dbsaPublication.getLinks(),
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
