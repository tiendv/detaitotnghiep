package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import uit.tkorg.dbsa.actions.database.InsertSubject;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.model.Subject;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DatabaseManagementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane databaseJTaqbleInJScrollPane;

	private static DefaultTableModel model;
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
	private JButton updateJButton;
	private JButton deleteJButton;
	private static JButton addJButton;
	private JPanel actionsJPanel;
	private JTable subjectJTable;
	private JScrollPane subjectContentJScrollPane;
	
	private static int subjectNumberRow = 0;
	private static int subjectID = 0;
	private static String subjectName = "";
	
	public DatabaseManagementPanel() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(null, "Database management", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(0, 1, 608), new Trailing(7, 76, 10, 10)));
		add(getSubjectContentJScrollPane(), new Constraints(new Bilateral(0, 1, 31), new Trailing(92, 144, 10, 10)));
		add(getDatabaseJTaqbleInJScrollPane(), new Constraints(new Bilateral(1, 2, 706), new Bilateral(0, 238, 10, 155)));
		setSize(787, 419);
	}

	private JScrollPane getSubjectContentJScrollPane() {
		if (subjectContentJScrollPane == null) {
			subjectContentJScrollPane = new JScrollPane();
			subjectContentJScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Subject table", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			subjectContentJScrollPane.setViewportView(getSubjectJTable());
		}
		return subjectContentJScrollPane;
	}

	private JTable createSubjectJTable(){
		
		subjectModel = new DefaultTableModel(getTableData(getSubjectNumber(), getSubjectID(), getSubjectName()), getSubjectColumnName()){
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, Integer.class, String.class, };

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
				col.setPreferredWidth(5);
			}else if(i == 1){
				col.setPreferredWidth(5);
			}else if(i == 2){
				col.setPreferredWidth(90);
			}
		}
		
		return table;
	}
	
	public  Object [][] getTableData(int rowNumber, int subID, String subjectName){
		
		Object [][] data = {addTableData(rowNumber, subID, subjectName)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int rowNumber,int subID, String subjectName){
		Object [] dataRow =  {getRowNumber(), getSubjectID(), getSubjectName()};
		
		return dataRow;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private  String [] getSubjectColumnName(){
		String [] columnNames = {"No.", "Subject ID", "Subject name"};
			
		return columnNames;
	}
	
	private JTable getSubjectJTable() {
		if (subjectJTable == null) {
			subjectJTable = createSubjectJTable();
		}
		subjectJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int n = subjectJTable.getSelectedRow();
				if(n == 0)
					addJButton.setEnabled(true);
				else
					addJButton.setEnabled(false);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int n = subjectJTable.getSelectedRow();
				System.out.println(subjectModel.getValueAt(n, 2));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		return subjectJTable;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 12, 12), new Leading(7, 10, 10)));
			actionsJPanel.add(getUpdateJButton(), new Constraints(new Trailing(111, 501, 501), new Leading(7, 12, 12)));
			actionsJPanel.add(getDeleteJButton(), new Constraints(new Trailing(221, 12, 12), new Leading(7, 12, 12)));
			actionsJPanel.add(getAddJButton(), new Constraints(new Trailing(330, 12, 12), new Leading(7, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getAddJButton() {
		if (addJButton == null) {
			addJButton = new JButton();
			addJButton.setEnabled(false);
			addJButton.setText(" Add Subject");
			addJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					//int n = subjectJTable.getSelectedRow();
					//if(subjectModel.getValueAt(0, 0).toString().replaceAll(" ", "").equals(0)){
						//addJButton.setEnabled(true);
					
					int i = subjectModel.getRowCount();
					Object []data = {i , i, subjectModel.getValueAt(0, 2).toString()};
					subjectModel.insertRow(i, data);
					
					Subject sb = new Subject();
					sb.setSbj_name(subjectModel.getValueAt(0, 2).toString());
					
					InsertSubject insert  = new InsertSubject();
					insert.InsertSubjectOfPublication(sb);
					
					System.out.println(subjectModel.getRowCount());
					//}
					
				}
				
			});
		}
		return addJButton;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setEnabled(false);
			deleteJButton.setText("   Delete    ");
		}
		return deleteJButton;
	}

	private JButton getUpdateJButton() {
		if (updateJButton == null) {
			updateJButton = new JButton();
			updateJButton.setEnabled(false);
			updateJButton.setText("   Update   ");
		}
		return updateJButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("   Close   ");
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
			databaseJTaqbleInJScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Database", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			databaseJTaqbleInJScrollPane.setAlignmentX(1.0f);
			databaseJTaqbleInJScrollPane.setViewportView(getJTable0());
		}
		return databaseJTaqbleInJScrollPane;
	}

	public DefaultTableModel getDefaultTableModel(){
		return model;
	}
	
	private JTable createDatabaseJTable(){
	
		model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher(), getMark()), getDatabaseColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class,  String.class, Integer.class, String.class, String.class, Boolean.class, };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		
		JTable table = new JTable(model);
		
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
		String [] columnNames = {"No.", "Title", "Authors", "Link", "Year", "Abstract", "Publisher", "Mark"};
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher, boolean isMark){
		
		Object [][] data = {addTableData(rowNumber, title, author, links, year, abstracts, publisher, isMark)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int rowNumber, String title, String author, String links, int year, String abstracts, String publisher, boolean isMark){
		Object [] dataRow =  {getRowNumber(), getTitle(), getAuthor(), getLinks(), getYear(), getAbstract(), getPublisher(), getMark()};
		
		return dataRow;
	}
	
	
	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 =  createDatabaseJTable();
		}
		return jTable0;
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
}