package uit.tkorg.dbsa.gui.fetcher;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTable resultsJTable;
	private JScrollPane resultsJScrollPane;
	private JPanel actionsJPanel;
	private JButton closeJButton;
	private JButton deleteJButton;
	private JButton saveJButton;
	private JPanel entryJPanel;
	private JLabel authorsJLabel;
	private JLabel yearJLabel;
	private JLabel abstractJLabel;
	private JLabel publisherJLabel;
	private JLabel titleJLabel;
	private JTextArea titleJTextArea;
	private JScrollPane jScrollPane1;
	private JTextArea authorsJTextArea;
	private JScrollPane jScrollPane2;
	private JTextArea yearJTextArea;
	private JScrollPane jScrollPane3;
	private JTextArea abstractJTextArea;
	private JScrollPane jScrollPane4;
	private JTextArea publisherJTextArea;
	private JScrollPane jScrollPane5;
	
	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static String year = "";
	private static String abstracts = "";
	private static String publisher = "";
	private static boolean mark = false;
	
	private static DefaultTableModel model;
	
	public FetcherResultPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(3, 3, 313), new Trailing(3, 60, 10, 10)));
		add(getEntryJPanel(), new Constraints(new Bilateral(3, 3, 127), new Trailing(69, 309, 67, 148)));
		add(getResultsJScrollPane(), new Constraints(new Bilateral(3, 3, 31), new Bilateral(4, 384, 51, 426)));
		setSize(631, 551);
		
		if(resultsJTable != null){
			titleJTextArea.setText(resultsJTable.getModel().getValueAt(0, 1).toString());
			authorsJTextArea.setText(resultsJTable.getModel().getValueAt(0, 2).toString());
			yearJTextArea.setText(resultsJTable.getModel().getValueAt(0, 3).toString());
			abstractJTextArea.setText(resultsJTable.getModel().getValueAt(0, 4).toString());
			publisherJTextArea.setText(resultsJTable.getModel().getValueAt(0, 5).toString());		

		}
	}

	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setViewportView(getPublisherJTextArea());
		}
		return jScrollPane5;
	}

	private JTextArea getPublisherJTextArea() {
		if (publisherJTextArea == null) {
			publisherJTextArea = new JTextArea();
		}
		return publisherJTextArea;
	}

	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setViewportView(getAbstractJTextArea());
		}
		return jScrollPane4;
	}

	private JTextArea getAbstractJTextArea() {
		if (abstractJTextArea == null) {
			abstractJTextArea = new JTextArea();
		}
		return abstractJTextArea;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getYearJTextArea());
		}
		return jScrollPane3;
	}

	private JTextArea getYearJTextArea() {
		if (yearJTextArea == null) {
			yearJTextArea = new JTextArea();
		}
		return yearJTextArea;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getAuthorsJTextArea());
		}
		return jScrollPane2;
	}

	private JTextArea getAuthorsJTextArea() {
		if (authorsJTextArea == null) {
			authorsJTextArea = new JTextArea();
		}
		return authorsJTextArea;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTitleJTextArea());
		}
		return jScrollPane1;
	}

	private JTextArea getTitleJTextArea() {
		if (titleJTextArea == null) {
			titleJTextArea = new JTextArea();
		}
		return titleJTextArea;
	}

	private JLabel getTitleJLabel() {
		if (titleJLabel == null) {
			titleJLabel = new JLabel();
			titleJLabel.setText("  Title");
			titleJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return titleJLabel;
	}

	private JLabel getPublisherJLabel() {
		if (publisherJLabel == null) {
			publisherJLabel = new JLabel();
			publisherJLabel.setText("  Publisher");
			publisherJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return publisherJLabel;
	}

	private JLabel getAbstractJLabel() {
		if (abstractJLabel == null) {
			abstractJLabel = new JLabel();
			abstractJLabel.setText("  Abstract");
			abstractJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return abstractJLabel;
	}

	private JLabel getYearJLabel() {
		if (yearJLabel == null) {
			yearJLabel = new JLabel();
			yearJLabel.setText("  Year");
			yearJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return yearJLabel;
	}

	private JLabel getAuthorsJLabel() {
		if (authorsJLabel == null) {
			authorsJLabel = new JLabel();
			authorsJLabel.setText("  Authors");
			authorsJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return authorsJLabel;
	}

	public static void setDefaultTableModel(DefaultTableModel value){
		model = value;
	}
	
	public static DefaultTableModel getDefaultTableModel(){
		return model;
	}
	
	public JTable getResultsJTable() {
		
		if (resultsJTable == null) {
			
		
			model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark()), getColumnName()) {
			private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, String.class, String.class, String.class, Boolean.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			};
			
			resultsJTable = new JTable();
			setDefaultTableModel(model);
			resultsJTable.setModel(getDefaultTableModel());
			
			//Sap xep noi dung cac dong trong table theo thu tu alpha B.
			//Cho phep sap xep theo tu cot rieng biet
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(resultsJTable.getModel());
			resultsJTable.setRowSorter(sorter);
			
			/*
			 * Set width of table column 
			 */
			resultsJTable.setShowGrid(true);
			resultsJTable.setShowVerticalLines(true);
			resultsJTable.setShowHorizontalLines(true);
			
			for(int i = 0; i < 6; i++){
				TableColumn col = resultsJTable.getColumnModel().getColumn(i);
				if(i == 0){
					col.setPreferredWidth(5);
				}else if(i == 1){
					col.setPreferredWidth(250);
				}else if(i == 2){
					col.setPreferredWidth(150);
				}else if(i == 3){
					col.setPreferredWidth(20);
				}else if(i == 4){
					col.setPreferredWidth(500);
				}else if (i == 5){
					col.setPreferredWidth(100);
				}else{
					col.setPreferredWidth(10);
				}
			}
			
		}else if(resultsJTable != null){

			resultsJTable = new JTable();
			resultsJTable.setModel(model);
		
			
			for(int i = 0; i < getRowNumber(); i++){
				
				if((i + 1) == getRowNumber()){

					insertData(getRowNumber(), getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark());
				}
				
			}
			
		}
			
		resultsJTable.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent event) {
				resultJTableMouseMouseClicked(event);
			}
		});
		
		
		return resultsJTable;
	}
	
	private void insertData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
		model.insertRow(rowNumber, addTableData(rowNumber ,title, author, year, abstracts, publisher, isMark));
		
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private  String [] getColumnName(){
		String [] columnNames = { "No.", "Title", "Authors", "Year", "Abstract", "Publisher", "Mark", };
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
		
		Object [][] data = {addTableData(rowNumber, "title awef", "author", "year", abstracts, publisher, isMark)};
		
		return data;
		
	}
	
//	public  void getRowData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
//		
//		model.insertRow(rowNumber,  addTableData(rowNumber, title, author, year, abstracts, publisher, isMark));	
//		
//	}
	
	
	public  Object [] addTableData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
		Object [] dataRow =  {rowNumber, getTitle(), author, year, abstracts, publisher, isMark};
		
		return dataRow;
	}
	
	private JPanel getEntryJPanel() {
		if (entryJPanel == null) {
			entryJPanel = new JPanel();
			entryJPanel.setBorder(BorderFactory.createTitledBorder("Entry detail"));
			entryJPanel.setLayout(new GroupLayout());
			entryJPanel.add(getAuthorsJLabel(), new Constraints(new Leading(3, 78, 12, 12), new Leading(50, 46, 12, 12)));
			entryJPanel.add(getTitleJLabel(), new Constraints(new Leading(3, 78, 12, 12), new Leading(2, 46, 12, 12)));
			entryJPanel.add(getJScrollPane2(), new Constraints(new Bilateral(83, 12, 22), new Leading(50, 46, 12, 12)));
			entryJPanel.add(getYearJLabel(), new Constraints(new Leading(3, 78, 12, 12), new Leading(98, 46, 12, 12)));
			entryJPanel.add(getJScrollPane3(), new Constraints(new Bilateral(83, 12, 22), new Leading(98, 46, 12, 12)));
			entryJPanel.add(getAbstractJLabel(), new Constraints(new Leading(3, 78, 12, 12), new Leading(146, 84, 10, 10)));
			entryJPanel.add(getJScrollPane4(), new Constraints(new Bilateral(83, 12, 22), new Leading(146, 84, 12, 12)));
			entryJPanel.add(getPublisherJLabel(), new Constraints(new Leading(3, 78, 12, 12), new Leading(232, 46, 10, 10)));
			entryJPanel.add(getJScrollPane5(), new Constraints(new Bilateral(83, 12, 22), new Leading(232, 46, 12, 12)));
			entryJPanel.add(getJScrollPane1(), new Constraints(new Bilateral(83, 12, 22), new Leading(2, 46, 12, 12)));
		}
		return entryJPanel;
	}

	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Save");
		}
		return saveJButton;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setText("Delete");
		}
		return deleteJButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Close");
		}
		return closeJButton;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 81, 12, 12), new Leading(0, 26, 10, 8)));
			actionsJPanel.add(getSaveJButton(), new Constraints(new Trailing(210, 80, 12, 12), new Leading(0, 12, 12)));
			actionsJPanel.add(getDeleteJButton(), new Constraints(new Trailing(111, 81, 12, 12), new Leading(0, 26, 10, 8)));
		}
		return actionsJPanel;
	}

	private JScrollPane getResultsJScrollPane() {
		if (resultsJScrollPane == null) {
			resultsJScrollPane = new JScrollPane();
			resultsJScrollPane.setBorder(BorderFactory.createTitledBorder("Results list"));
			resultsJScrollPane.setViewportView(getResultsJTable());
		}
		return resultsJScrollPane;
	}

	private  void resultJTableMouseMouseClicked(MouseEvent event) {
		/*
		 * get row number is select
		 */
		
		int rowNumber = resultsJTable.getSelectedRow();
		System.out.println( "row number " + rowNumber + resultsJTable.getRowCount());
		
		if(rowNumber >= 0){
			titleJTextArea.setText(resultsJTable.getModel().getValueAt(rowNumber, 1).toString());
			authorsJTextArea.setText(resultsJTable.getModel().getValueAt(rowNumber, 2).toString());
			yearJTextArea.setText(resultsJTable.getModel().getValueAt(rowNumber, 3).toString());
			abstractJTextArea.setText(resultsJTable.getModel().getValueAt(rowNumber, 4).toString());
			publisherJTextArea.setText(resultsJTable.getModel().getValueAt(rowNumber, 5).toString());
		}
	}
	
	/*
	 * 
	 * set & get paper bibliography 
	 */
	
	public static void setRowNumber(int number){
		rowNumber = number;
	}
	
	public  static int getRowNumber(){
		return rowNumber;
	}
	
	public static void setTitle(String titleString){
		title = titleString;
	}

	public  static String getTitle(){
		return title;
	}
	
	public static void setAuthor(String authorString){
		author = authorString;
	}

	public  String getAuthor(){
		return author;
	}
	
	public static void setYear(String yearString){
		year = yearString;
	}

	public static String getYear(){
		return year;
	}
	
	public static void setAbstract(String abstractString){
		abstracts = abstractString;
	}

	public static String getAbstract(){
		return abstracts;
	}
	
	public static void setPublisher(String publisherString){
		publisher = publisherString;
	}

	public static String getPublisher(){
		return publisher;
	}
	
	public static void setMark(boolean isMark){
		mark = isMark;
	}
	
	public static boolean getMark(){
		return mark;
	}
}
