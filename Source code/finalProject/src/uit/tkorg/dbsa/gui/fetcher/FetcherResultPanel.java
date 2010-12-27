package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
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
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.gui.main.DBSATabPanel;
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
	private static String link = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String publisher = "";
	private static boolean mark = false;
	private static URL hyperlink = null;
	
	private static DefaultTableModel model;
	
	private static ArrayList<DBSAPublication> dbsaPublicationCheckList = new ArrayList<DBSAPublication>();
	
	private static ArrayList<DBSAPublication> dbsaPublicationList = new ArrayList<DBSAPublication>();
	
	private static ArrayList<Integer> numberArray = new ArrayList<Integer>();
	private static int duplicateNumber = 0;
	private static boolean isDuplicate = false;
	private static boolean duplicationArtilce = false;
	private JTabbedPane dbsaTabFrame = null;
	
	public FetcherResultPanel(DBSATabPanel dbsa) {
		initComponents();
		dbsaTabFrame = dbsa;
	
	}

	public FetcherResultPanel(int i){
		
	}
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(3, 3, 313), new Trailing(3, 60, 10, 10)));
		add(getResultsJScrollPane(), new Constraints(new Bilateral(3, 3, 31), new Bilateral(4, 338, 10, 142)));
		add(getEntryJPanel(), new Constraints(new Bilateral(3, 3, 141), new Trailing(61, 277, 10, 146)));
		setSize(649, 484);
		
//		if(resultsJTable!= null){
//			titleJTextArea.setText(model.getValueAt(0, 1).toString());
//			authorsJTextArea.setText(model.getValueAt(0, 2).toString());
//			linkJTextArea.setText(model.getValueAt(0, 3).toString());
//			yearJTextArea.setText(model.getValueAt(0, 4).toString());
//			abstractJTextArea.setText(model.getValueAt(0, 5).toString());
//			publisherJTextArea.setText(resultsJTable.getModel().getValueAt(0, 6).toString());
//		}
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jScrollPane0.setViewportView(getLinkJTextArea());
		}
		return jScrollPane0;
	}

	private JEditorPane getLinkJTextArea() {
		if (linkJEditorPane == null) {
			linkJEditorPane = new JEditorPane("text/html","");
			linkJEditorPane.setEditable(false);
			linkJEditorPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
			linkJEditorPane.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
					try {
						URL url = new URL(linkJEditorPane.getText().replaceAll("\\<.*?>",""));
						URI uri = url.toURI();
						Desktop.getDesktop().browse(uri);
						
					}catch (URISyntaxException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					} catch (IOException ex2) {
						// TODO Auto-generated catch block
						ex2.printStackTrace();
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {	
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {	
				}

				@Override
				public void mouseReleased(MouseEvent e) {	}
				
			});
			
		}
		return linkJEditorPane;
	}

	private JLabel getLinkJLabel() {
		if (linkJLabel == null) {
			linkJLabel = new JLabel();
			linkJLabel.setText("  " + DBSAResourceBundle.res.getString("link"));
			linkJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return linkJLabel;
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
			publisherJTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
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
			abstractJTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
			abstractJTextArea.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mouseEntered(MouseEvent event) {
				}
	
				public void mouseClicked(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
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
			yearJTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
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
			authorsJTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
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
			titleJTextArea.setLineWrap(true);
			titleJTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(152, 192, 228), new Color(152, 192, 228), null, null));
		}
		return titleJTextArea;
	}

	private JLabel getTitleJLabel() {
		if (titleJLabel == null) {
			titleJLabel = new JLabel();
			titleJLabel.setText("  " + DBSAResourceBundle.res.getString("title"));
			titleJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return titleJLabel;
	}

	private JLabel getPublisherJLabel() {
		if (publisherJLabel == null) {
			publisherJLabel = new JLabel();
			publisherJLabel.setText("  " + DBSAResourceBundle.res.getString("publisher"));
			publisherJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return publisherJLabel;
	}

	private JLabel getAbstractJLabel() {
		if (abstractJLabel == null) {
			abstractJLabel = new JLabel();
			abstractJLabel.setText("  " + DBSAResourceBundle.res.getString("abstract"));
			abstractJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return abstractJLabel;
	}

	private JLabel getYearJLabel() {
		if (yearJLabel == null) {
			yearJLabel = new JLabel();
			yearJLabel.setText("  " + DBSAResourceBundle.res.getString("year"));
			yearJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return yearJLabel;
	}

	private JLabel getAuthorsJLabel() {
		if (authorsJLabel == null) {
			authorsJLabel = new JLabel();
			authorsJLabel.setText("  " + DBSAResourceBundle.res.getString("authors"));
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
		model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getHyperLink(), getYear(), getAbstract(), getPublisher(), getMark(), getIsDuplicate()), getColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, URL.class, Integer.class, String.class, String.class, Boolean.class, Boolean.class};

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
		table.setRowHeight(25);
		//table.getColumn("links")//;
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
		
		TableCellRenderer tcr = table.getDefaultRenderer(Integer.class);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tcr; 
		renderer.setHorizontalAlignment(SwingConstants.CENTER);  
		
		table.getColumn(DBSAResourceBundle.res.getString("duplicate")).setWidth(0);
		table.getColumn(DBSAResourceBundle.res.getString("duplicate")).setMinWidth(0);
		table.getColumn(DBSAResourceBundle.res.getString("duplicate")).setMaxWidth(0);
		
		for(int i = 0; i < 8; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(5);
			}else if(i == 1){
				col.setPreferredWidth(200);
			}else if(i == 2){
				col.setPreferredWidth(150);
			}else if(i == 3){
				col.setPreferredWidth(150);
			}else if(i == 4){
				col.setPreferredWidth(20);
			}else if (i == 5){
				col.setPreferredWidth(400);
			}else if(i == 6){
				col.setPreferredWidth(70);
			}else if(i == 7){
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
	int checkEnable = 0;
	
	public JTable getResultsJTable() {
		
		if (resultsJTable == null) {
			
			resultsJTable = createResultJTable();			
		}else if(resultsJTable != null){
			
			for(int i = 0; i < getRowNumber(); i++){				
				if((i + 1) == getRowNumber()){
					Object [] data = {resultsJTable.getRowCount() + 1, getTitle(), getAuthor(), getLink(), getYear(), getAbstract(), getPublisher(), getMark(), getIsDuplicate()};
					model.insertRow(resultsJTable.getRowCount(), data );
				
					DBSAPublication dbsa = new DBSAPublication();
					dbsa.setId(resultsJTable.getRowCount() + 1);
					dbsa.setTitle(getTitle());
					dbsa.setAuthors(getAuthor());
					dbsa.setLinks(getLink());
					//setHyperLink(dbsa.getLinks());
					dbsa.setYear(getYear());
					dbsa.setAbstractPub(getAbstract());
					dbsa.setPublisher(getPublisher());
					setIsDuplicate(false);
					
					dbsaPublicationCheckList.add(dbsa);
							
				}				
			}	
			
			if(!saveJButton.isEnabled())
				saveJButton.setEnabled(true);
			
			
			int maxResult = FetcherPanel.getAcmResultNumber() + FetcherPanel.getCiteResultNumber() + FetcherPanel.getIeeeResultNumber();
			
			if(resultsJTable.getRowCount() >= maxResult){
				checkArticleIsDuplicated();
				int n = 0;
				System.out.println(FetcherPanel.fetched);
				if(FetcherPanel.fetched){
					System.out.println(FetcherPanel.fetched);
					JOptionPane.showMessageDialog(null, "agfaweg");
					
					n = JOptionPane.showConfirmDialog(DBSAApplication.dbsaJFrame, DBSAResourceBundle.res.getString("do.you.want.to.show.fetcher.result"),
						    "An Question", JOptionPane.YES_NO_OPTION);
					
					if(n == JOptionPane.YES_OPTION){
						dbsaTabFrame.setSelectedIndex(1);
						
					}else if(n == JOptionPane.NO_OPTION){
						
					}
					FetcherPanel.fetched = false;
				}
				if(resultsJTable.getRowCount() > 1){
					
					titleJTextArea.setText("abc");
					//authorsJTextArea.setText(model.getValueAt(1, 2).toString());
					//linkJTextArea.setText(model.getValueAt(1, 3).toString());
					//yearJTextArea.setText(model.getValueAt(1, 4).toString());
					//abstractJTextArea.setText(model.getValueAt(1, 5).toString());
					//publisherJTextArea.setText(resultsJTable.getModel().getValueAt(1, 6).toString());
				}
			}
			
			if(resultsJTable.getModel().getValueAt(0, 2).toString().replaceAll(" ", "").equals("")){
				checkRemovedFirst =  true;
				
				model.removeRow(0);
							
			}		
		}
		
		resultsJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			
				int n  = resultsJTable.getSelectedRow();
					
				titleJTextArea.setText(model.getValueAt(n, 1).toString());
				authorsJTextArea.setText(model.getValueAt(n, 2).toString());				
				linkJEditorPane.setText("<html><body><a href='" + "'>" + model.getValueAt(n, 3).toString() + "</><hr></body></html>");
				yearJTextArea.setText(model.getValueAt(n, 4).toString());
				abstractJTextArea.setText(model.getValueAt(n, 5).toString());
				publisherJTextArea.setText(resultsJTable.getModel().getValueAt(n, 6).toString());
				
				
				for(int i = 0; i < resultsJTable.getRowCount();i++){
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
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {		
			}
			
		});
		return resultsJTable;
	}
	
	/*
	 * check article is duplicated
	 */
	
	@SuppressWarnings("unchecked")
	public boolean checkArticleIsDuplicated(){
		CheckExist check = new CheckExist();
		
		numberArray = (ArrayList<Integer>) check.CheckTitleSignaturePublications(dbsaPublicationCheckList).clone();
		duplicateNumber = numberArray.size();
		
		duplicationArtilce = false;
		for(int i = 0; i < numberArray.size(); i++)
		{
			model.setValueAt(true, numberArray.get(i), 8);
			resultsJTable.addRowToPaint(numberArray.get(i), Color.red);
			duplicationArtilce = true;
		}
		
		return duplicationArtilce;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private  String [] getColumnName(){
		String [] columnNames = { DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher"),DBSAResourceBundle.res.getString("mark"), 
				DBSAResourceBundle.res.getString("duplicate")};
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate){
		
		Object [][] data = {addTableData(rowNumber, title, author, hyperLink, year, abstracts, publisher, isMark, duplicate)};
		
		return data;
		
	}
	
//	public  void getRowData(int rowNumber, String title, String author, String year, String abstracts, String publisher, boolean isMark){
//		
//		model.insertRow(rowNumber,  addTableData(rowNumber, title, author, year, abstracts, publisher, isMark));	
//		
//	}
	
	public  Object [] addTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate){
		Object [] dataRow =  {getRowNumber(), getTitle(), getAuthor(), getHyperLink(), getYear(), getAbstract(), getPublisher(), getMark(), getIsDuplicate()};
		
		return dataRow;
	}
	
	private JPanel getEntryJPanel() {
		if (entryJPanel == null) {
			entryJPanel = new JPanel();
			entryJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("entry.detail"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			entryJPanel.setLayout(new GroupLayout());
			entryJPanel.add(getAuthorsJLabel(), new Constraints(new Leading(8, 78, 45, 26), new Leading(45, 30, 12, 12)));
			entryJPanel.add(getTitleJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(10, 30, 12, 12)));
			entryJPanel.add(getLinkJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(81, 30, 12, 12)));
			entryJPanel.add(getAbstractJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(153, 52, 12, 12)));
			entryJPanel.add(getYearJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(117, 30, 12, 12)));
			entryJPanel.add(getPublisherJLabel(), new Constraints(new Leading(8, 78, 12, 12), new Leading(211, 30, 12, 12)));
			entryJPanel.add(getJScrollPane4(), new Constraints(new Bilateral(90, 13, 25), new Leading(153, 52, 10, 10)));
			entryJPanel.add(getJScrollPane3(), new Constraints(new Bilateral(90, 13, 25), new Leading(117, 30, 10, 10)));
			entryJPanel.add(getJScrollPane0(), new Constraints(new Bilateral(90, 13, 25), new Leading(81, 30, 54, 59)));
			entryJPanel.add(getJScrollPane2(), new Constraints(new Bilateral(90, 13, 25), new Leading(45, 30, 12, 12)));
			entryJPanel.add(getJScrollPane1(), new Constraints(new Bilateral(90, 13, 25), new Leading(10, 30, 10, 10)));
			entryJPanel.add(getJScrollPane5(), new Constraints(new Bilateral(90, 13, 25), new Leading(211, 30, 10, 10)));
		}
		return entryJPanel;
	}

	boolean abc = false;
	private JLabel linkJLabel;
	private JEditorPane linkJEditorPane;
	private JScrollPane jScrollPane0;
	
	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText(DBSAResourceBundle.res.getString("save"));
			saveJButton.setEnabled(false);
			
			saveJButton.addActionListener(new ActionListener(){

				//@SuppressWarnings("static-access")
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					

					//default icon, custom title
					int n = 0;
					boolean checkInsert = false;
					//System.out.println("numberarray size = " + duplicateNumber);
					if(duplicateNumber > 0){
						n = JOptionPane.showConfirmDialog(
					    DBSAApplication.dbsaJFrame, DBSAResourceBundle.res.getString("notice.data.duplicate"),
					    "An Question", JOptionPane.YES_NO_OPTION);
					
						if(n == JOptionPane.YES_OPTION){
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("notice.select.rows.to.delete"));
						}else if(n == JOptionPane.NO_OPTION){
							checkInsert = insertToDatabase();
						}
					}
					
					if(duplicateNumber == 0){
						int k = JOptionPane.showConfirmDialog(
					    DBSAApplication.dbsaJFrame, DBSAResourceBundle.res.getString("notice.save.data"),
					    "An Question", JOptionPane.YES_NO_OPTION);
					
				
						if(k == JOptionPane.YES_OPTION){
							//insert data to database
							checkInsert = insertToDatabase();
						}else if(k == JOptionPane.NO_OPTION){
							
							//JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("notice.save.data"));
						}
					}
					
					if(checkInsert == true){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("add.database.sucessfully"));
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
			
			for(int j = 0; j < resultsJTable.getRowCount(); j++){
				
				DBSAPublication dbsaPub = new DBSAPublication();
				
				dbsaPub.setTitle(resultsJTable.getModel().getValueAt(j, 1).toString());
				dbsaPub.setAuthors(resultsJTable.getModel().getValueAt(j, 2).toString());
				dbsaPub.setLinks(resultsJTable.getModel().getValueAt(j, 3).toString());
				dbsaPub.setYear(Integer.parseInt(resultsJTable.getModel().getValueAt(j, 4).toString()));
				dbsaPub.setAbstractPub(resultsJTable.getModel().getValueAt(j, 5).toString());
				dbsaPub.setPublisher(resultsJTable.getModel().getValueAt(j, 6).toString());
				
				dbsaPublicationList.add(dbsaPub);
			}
			
			InsertDBSAPublication insert = new InsertDBSAPublication();
			insert.InsertPublication(dbsaPublicationList);
			
			//System.out.println(resultsJTable.getRowCount());
			
			for(int i = resultsJTable.getRowCount() - 1; i >= 0; i--){
				model.removeRow(i);
			}
			
			for(int k = 0; k < dbsaPublicationCheckList.size(); k++){
				dbsaPublicationCheckList.remove(k);
//				System.out.println("dbsaPublicationCheckList" + dbsaPublicationCheckList.size());
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
			deleteJButton.setText(DBSAResourceBundle.res.getString("delete"));
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
		//int check = 0;
		for(int i = resultsJTable.getRowCount()-1; i >= 0; i--){
			
			if(resultsJTable.getModel().getValueAt(i, 7) != null
				&& resultsJTable.getModel().getValueAt(i, 7).toString().equals("true")){
			
				model.removeRow(i);
				
				duplicateNumber = 0;
				
				for(int k = 0; k < resultsJTable.getRowCount(); k++){
					if(resultsJTable.getModel().getValueAt(k, 8) != null
							&&resultsJTable.getModel().getValueAt(k, 8).toString().equals("true")){
						
						duplicateNumber ++;
					}
				}
					
				for(int j = 0; j < resultsJTable.getRowCount(); j++){
					resultsJTable.addRowToPaint(j, Color.white);
					resultsJTable.getModel().setValueAt(j+1, j, 0);
					
					if(resultsJTable.getModel().getValueAt(j, 8) != null
						&&resultsJTable.getModel().getValueAt(j, 8).toString().equals("true")){
				
						resultsJTable.addRowToPaint(j, Color.red);
					}
				}
			}
		}
//		if(check == 0){
//			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("notice.choose.article.to.delete"));
//		}
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText(DBSAResourceBundle.res.getString("close"));
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
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("actions")));
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
			resultsJScrollPane.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("result.list")));
			resultsJScrollPane.setViewportView(getResultsJTable());
		}
		return resultsJScrollPane;
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
}
