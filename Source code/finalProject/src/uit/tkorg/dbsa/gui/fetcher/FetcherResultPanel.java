package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
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

import uit.tkorg.dbsa.actions.database.CheckExist;
import uit.tkorg.dbsa.actions.database.InsertDBSAPublication;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.model.DBSAPublication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static MyJTable resultsJTable;
	private JScrollPane resultsJScrollPane;
	private JPanel actionsJPanel;
	private JButton closeJButton;
	private static JButton deleteJButton;
	private static JButton saveJButton;
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
	private static int year = 0;
	private static String abstracts = "";
	private static String publisher = "";
	private static boolean mark = false;
	
	private static DefaultTableModel model;
	
	private static ArrayList<DBSAPublication> dbsaPublication = new ArrayList<DBSAPublication>();
	
	private static ArrayList<Integer> numberArray = new ArrayList<Integer>();
	
	private boolean checkClickOnJTabel = false; 
	
	public FetcherResultPanel() {
		initComponents();
	
	}

	public FetcherResultPanel(int i){
		
	}
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(3, 3, 313), new Trailing(3, 60, 10, 10)));
		add(getResultsJScrollPane(), new Constraints(new Bilateral(3, 3, 31), new Bilateral(4, 310, 10, 170)));
		add(getEntryJPanel(), new Constraints(new Bilateral(3, 3, 629), new Trailing(65, 10, 10)));
		setSize(649, 484);
	}

	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
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
			jScrollPane4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jScrollPane4.setViewportView(getAbstractJTextArea());
		}
		return jScrollPane4;
	}

	private JTextArea getAbstractJTextArea() {
		if (abstractJTextArea == null) {
			abstractJTextArea = new JTextArea();
			abstractJTextArea.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return abstractJTextArea;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
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
			jScrollPane2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
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
			jScrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
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

	//public void setDefaultTableModel(DefaultTableModel value){
	//	model = value;
	//}
	
	public DefaultTableModel getDefaultTableModel(){
		return model;
	}
	/*
	 * Ham tao Jtable
	 */
	public MyJTable createResultJTable(){
		model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark()), getColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, Integer.class, String.class, String.class, Boolean.class, };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			
			/*public Class getColumnClass(int col) {
				if (col == 6) return Boolean.class;
				else return Object.class;
			}*/
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
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
		
		TableCellRenderer tcr = table.getDefaultRenderer(Integer.class);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tcr; 
		renderer.setHorizontalAlignment(SwingConstants.CENTER);  
		
		for(int i = 0; i < 6; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
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
			}else if(i == 6){
				col.setPreferredWidth(5);
			}
		}
		
		
		return table;
	}
	
	/*
	 * ham lay gia tri cho table
	 * @return JTable
	 */
	boolean checkRemovedFirst = false;
	public JTable getResultsJTable() {
		
		if (resultsJTable == null) {
			
			resultsJTable = createResultJTable();			
		}else if(resultsJTable != null){
			
			for(int i = 0; i < getRowNumber(); i++){				
				if((i + 1) == getRowNumber()){
					//
					if(resultsJTable.getRowCount() == 1 && checkRemovedFirst == false){
						Object [] data = {resultsJTable.getRowCount(), getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark()};
						model.insertRow(resultsJTable.getRowCount(), data );
						
						DBSAPublication dbsa = new DBSAPublication();
						dbsa.setId(resultsJTable.getRowCount());
						dbsa.setTitle(getTitle());
						dbsa.setAuthors(getAuthor());
						dbsa.setYear(getYear());
						dbsa.setAbstractPub(getAbstract());
						dbsa.setPublisher(getPublisher());
						
						dbsaPublication.add(dbsa);
						
					}else{
						Object [] data = {resultsJTable.getRowCount() + 1, getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark()};
						model.insertRow(resultsJTable.getRowCount(), data );
						//resultsJTable.addRowToPaint(10, Color.red);
						
						DBSAPublication dbsa = new DBSAPublication();
						dbsa.setId(resultsJTable.getRowCount() + 1);
						dbsa.setTitle(getTitle());
						dbsa.setAuthors(getAuthor());
						dbsa.setYear(getYear());
						dbsa.setAbstractPub(getAbstract());
						dbsa.setPublisher(getPublisher());
						dbsaPublication.add(dbsa);
					}			
				}				
			}	
			
			if(!saveJButton.isEnabled())
				saveJButton.setEnabled(true);
			int maxResult = FetcherPanel.getAcmResultNumber() + FetcherPanel.getCiteResultNumber() + FetcherPanel.getIeeeResultNumber();
			
			if(resultsJTable.getRowCount() >= maxResult){
				CheckExist check = new CheckExist();
				
				numberArray = (ArrayList<Integer>) check.CheckTitlePublications(dbsaPublication).clone();
				System.out.println("trung lap " + numberArray.size());
				
				for(int i = 0; i < numberArray.size(); i++)
				{
					resultsJTable.addRowToPaint(numberArray.get(i), Color.red);
					//resultsJTable.setValueAt(true, numberArray.get(i), 6);
					System.out.println("trung lap gia tri " + numberArray.get(i));
					//setForeground(Color.red);
				}
			//	resultsJTable.repaint();
			}
			
			if(resultsJTable.getModel().getValueAt(0, 2).toString().replaceAll(" ", "").equals("")){
				checkRemovedFirst =  true;
				model.removeRow(0);
			}
		}
			
		resultsJTable.addMouseListener(new MouseAdapter() {			
			public void mousePressed(MouseEvent event) {
				if(event.getClickCount()==1)
					resultJTableMousePressed();
				checkClickOnJTabel = true;
			}
		});
		
//		if(resultsJTable.getRowCount() > 0){
//			titleJTextArea.setText(resultsJTable.getModel().getValueAt(0, 1).toString());
//			authorsJTextArea.setText(resultsJTable.getModel().getValueAt(0, 2).toString());
//			yearJTextArea.setText(resultsJTable.getModel().getValueAt(0, 3).toString());
//			abstractJTextArea.setText(resultsJTable.getModel().getValueAt(0, 4).toString());
//			publisherJTextArea.setText(resultsJTable.getModel().getValueAt(0, 5).toString());		
//		}
		
		return resultsJTable;
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
	public  Object [][] getTableData(int rowNumber, String title, String author, int year, String abstracts, String publisher, boolean isMark){
		
		Object [][] data = {addTableData(rowNumber, title, author, year, abstracts, publisher, isMark)};
		
		return data;
		
	}
	
//	public  void getRowData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
//		
//		model.insertRow(rowNumber,  addTableData(rowNumber, title, author, year, abstracts, publisher, isMark));	
//		
//	}
	
	
	public  Object [] addTableData(int rowNumber, String title, String author, int year, String abstracts, String publisher, boolean isMark){
		Object [] dataRow =  {getRowNumber(), getTitle(), getAuthor(), getYear(), getAbstract(), getPublisher(), getMark()};
		
		return dataRow;
	}
	
	private JPanel getEntryJPanel() {
		if (entryJPanel == null) {
			entryJPanel = new JPanel();
			entryJPanel.setBorder(BorderFactory.createTitledBorder(null, "Entry detail", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			entryJPanel.setLayout(new GroupLayout());
			entryJPanel.add(getYearJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(80, 30, 10, 10)));
			entryJPanel.add(getAbstractJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(114, 52, 10, 10)));
			entryJPanel.add(getAuthorsJLabel(), new Constraints(new Leading(8, 78, 45, 26), new Leading(45, 30, 12, 12)));
			entryJPanel.add(getTitleJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(10, 30, 12, 12)));
			entryJPanel.add(getJScrollPane2(), new Constraints(new Bilateral(90, 12, 22), new Leading(45, 30, 12, 12)));
			entryJPanel.add(getJScrollPane1(), new Constraints(new Bilateral(90, 12, 22), new Leading(10, 30, 10, 10)));
			entryJPanel.add(getJScrollPane3(), new Constraints(new Bilateral(89, 12, 532), new Leading(80, 30, 10, 10)));
			entryJPanel.add(getPublisherJLabel(), new Constraints(new Leading(8, 78, 46, 27), new Leading(170, 30, 10, 10)));
			entryJPanel.add(getJScrollPane5(), new Constraints(new Bilateral(89, 12, 532), new Leading(170, 30, 10, 10)));
			entryJPanel.add(getJScrollPane4(), new Constraints(new Bilateral(89, 12, 532), new Leading(114, 52, 12, 12)));
		}
		return entryJPanel;
	}

	boolean abc = false;
	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Save");
			saveJButton.setEnabled(false);
			
			saveJButton.addActionListener(new ActionListener(){

				@SuppressWarnings("static-access")
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					

					//default icon, custom title
					int n = 0;
					boolean checkInsert = false;
					
					if(numberArray.size() > 0){
						n = JOptionPane.showConfirmDialog(
					    DBSAApplication.dbsaJFrame, "Do you want do delete article duplicate before save to database?",
					    "An Question", JOptionPane.YES_NO_OPTION);
					
						if(n == JOptionPane.YES_OPTION){
							JOptionPane.showMessageDialog(null, "Select rows which you want to delete, after press 'Delete' button");
						}else if(n == JOptionPane.NO_OPTION){
							checkInsert = insertToDatabase();
						}
					}else{
						checkInsert = insertToDatabase();
					}
					
					if(checkInsert == true){
						JOptionPane.showMessageDialog(null, "Data is added successfully!");
					}
				}
				
			});
		}
		return saveJButton;
	}
	
	@SuppressWarnings("static-access")
	public boolean insertToDatabase(){
		boolean checkInsert = false;
		try{
			InsertDBSAPublication insert = new InsertDBSAPublication();
			insert.InsertPublication(dbsaPublication);
			
			System.out.println(resultsJTable.getRowCount());
			for(int i = resultsJTable.getRowCount() - 1; i >= 0; i--){
				model.removeRow(i);
			}
			
			titleJTextArea.setText("");
			authorsJTextArea.setText("");
			yearJTextArea.setText("");
			abstractJTextArea.setText("");
			publisherJTextArea.setText("");
			
			saveJButton.setEnabled(false);
			checkInsert = true;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return checkInsert;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setText("Delete");
			deleteJButton.setEnabled(false);
			deleteJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					removeRowsIsSelected();
					
				}
				
			});
		}
		return deleteJButton;
	}

	

	private void removeRowsIsSelected() {
		// TODO Auto-generated method stub
		int check = 0;
		for(int i = resultsJTable.getRowCount()-1; i >= 0; i--){
			
			if(resultsJTable.getModel().getValueAt(i, 6).toString().equals("true")){
				check ++;
				System.out.println(resultsJTable.getModel().getValueAt(i, 6).toString());
				model.removeRow(i);
				
				resultsJTable.addRowToPaint(i, Color.white);
				
				System.out.println(numberArray.size());
				System.out.println("i " + i);
				for(int j = 0; j < numberArray.size(); j++){
					
					if(numberArray.get(j) > i){
						
						resultsJTable.addRowToPaint(numberArray.get(j), Color.white);
						resultsJTable.addRowToPaint(numberArray.get(j) - 1, Color.red);
						numberArray.set(j, numberArray.get(j) - 1);
						
					}else if(numberArray.get(j) == i){
					
						numberArray.remove(j);
						for(int k = 0; k < numberArray.size(); k++){
							if(numberArray.get(k) > j){
								resultsJTable.addRowToPaint(numberArray.get(k), Color.white);
								resultsJTable.addRowToPaint(numberArray.get(k) - 1, Color.red);
								numberArray.set(k, numberArray.get(k) - 1);
							}
						}
					}
					
					
				}
				
				if(check == 0){
					JOptionPane.showMessageDialog(null, "Ban chua chon bai bao can xoa!");
				}
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
					// TODO Auto-generated method stub
					DBSAApplication.dbsaJFrame.dispose();
				}
				
			});
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

	public JScrollPane getResultsJScrollPane() {
		if (resultsJScrollPane == null) {
			resultsJScrollPane = new JScrollPane();
			resultsJScrollPane.setBorder(BorderFactory.createTitledBorder("Results list"));
			resultsJScrollPane.setViewportView(getResultsJTable());
		}
		return resultsJScrollPane;
	}

	private  void resultJTableMousePressed() {
		/*
		 * get row number is selected
		 */
		//rowNumberSelected
		int n  = resultsJTable.getSelectedRow();
		System.out.println( "row is selected " + n + "\n + Row number " + resultsJTable.getRowCount());
		
		//if(model.getValueAt(n, 6).toString().equals("true")){
			deleteJButton.setEnabled(true);
	//	}
		
		if(n >= 0 && checkClickOnJTabel == true){
			checkClickOnJTabel = false;
			
			titleJTextArea.setText(model.getValueAt(n, 1).toString());
			authorsJTextArea.setText(model.getValueAt(n, 2).toString());
			yearJTextArea.setText(model.getValueAt(n, 3).toString());
			abstractJTextArea.setText(model.getValueAt(n, 4).toString());
			publisherJTextArea.setText(resultsJTable.getModel().getValueAt(n, 5).toString());
		}else if(n == -1){
			JOptionPane.showMessageDialog(null, "No row is selected!");
		}
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
	
	public  void setMark(boolean isMark){
		mark = isMark;
	}
	
	public  boolean getMark(){
		return mark;
	}
}
