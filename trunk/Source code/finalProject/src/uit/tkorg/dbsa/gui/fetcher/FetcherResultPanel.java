package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Color;
import java.awt.Cursor;
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
import uit.tkorg.dbsa.gui.main.DBSAStatusBar;
import uit.tkorg.dbsa.model.DBSAPublication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static MyJTable resultsJTable;
	private static JScrollPane resultsJScrollPane;
	private static JPanel actionsJPanel;
	private static JButton closeJButton;
	private static JButton deleteJButton;
	private static JButton saveJButton;
	private static JPanel entryJPanel;
	private static JLabel authorsJLabel;
	private static JLabel yearJLabel;
	private static JLabel abstractJLabel;
	private static JLabel publisherJLabel;
	private static JLabel titleJLabel;
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
	private static JButton selectAllButton;
	private static JButton selectAllDupButton;
	private static JLabel linkJLabel;
	private JEditorPane linkJEditorPane;
	private JScrollPane jScrollPane0;
	private static JButton clearSelectedJButton;
	
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
	public static String digitalLibrary;
	
	private static ArrayList<DBSAPublication> dbsaPublicationCheckList = new ArrayList<DBSAPublication>();
	
//	private static ArrayList<DBSAPublication> dbsaPublicationList = new ArrayList<DBSAPublication>();
	
	private static ArrayList<Integer> numberArray = new ArrayList<Integer>();
	private static int duplicateNumber = 0;
	private static boolean isDuplicate = false;
	private static boolean duplicationArtilce = false;
	
	//So lieu cho tab thong ke
	public static int num_DupInDBLP = 0;
	public static int num_DupInDatabase = 0;
	public static int num_Total = 0;
	public static int old_Num_Total = 0;
	public static int acmNumberResult = 0;
	public static int acmDupInDblp = 0;
	public static int acmNumber_Before2005 = 0;
	public static int citeseerNumberResult = 0;
	public static int citeseerDupInDblp = 0;
	public static int citeseerNumber_Before2005 = 0;
	public static int ieeeNumberResult = 0;
	public static int ieeeDupInDblp = 0;
	public static int ieeeNumber_Before2005 = 0;

	private static DBSAStatusBar dbsaStatus = new DBSAStatusBar();
	
	private JTabbedPane dbsaTabFrame = null;
	
	public FetcherResultPanel(JTabbedPane dbsa) {
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
		setSize(1019, 484);
		updateTextsOfComponents();
	}

	private JButton getCheckDuplicateJButton() {
		if (checkDuplicateJButton == null) {
			checkDuplicateJButton = new JButton();
			checkDuplicateJButton.setText(DBSAResourceBundle.res.getString("check.duplicate"));
			checkDuplicateJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Thread checkDuplicateThread = new Thread (new Runnable(){
						@Override
						public void run() {
							checkDuplicateJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							checkArticleIsDuplicated();
							
						}});
					checkDuplicateThread.start();	
					checkDuplicateThread.interrupt();
					
				}
				
			});
		}
		return checkDuplicateJButton;
	}

	private JButton getClearSelectedJButton() {
		if (clearSelectedJButton == null) {
			clearSelectedJButton = new JButton();
			clearSelectedJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(resultsJTable.getRowCount() > 0){						
						for(int i = 0; i < resultsJTable.getRowCount(); i++){							
							resultsJTable.getModel().setValueAt(false, i, 7);
						}
					}else{			
					}
					
					deleteJButton.setEnabled(false);
					saveJButton.setEnabled(false);
				}
				
			});
			
			clearSelectedJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("deselect.all.the.articles.is.selected"));
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
		return clearSelectedJButton;
	}

	public void updateTextsOfComponents(){
		linkJLabel.setText("  " + DBSAResourceBundle.res.getString("link"));
		titleJLabel.setText("  " + DBSAResourceBundle.res.getString("title"));
		publisherJLabel.setText("  " + DBSAResourceBundle.res.getString("publisher"));
		abstractJLabel.setText("  " + DBSAResourceBundle.res.getString("abstract"));
		yearJLabel.setText("  " + DBSAResourceBundle.res.getString("year"));
		authorsJLabel.setText("  " + DBSAResourceBundle.res.getString("authors"));
		entryJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("entry.detail"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
				Font.BOLD, 12), new Color(51, 51, 51)));
		saveJButton.setText(DBSAResourceBundle.res.getString("save"));
		deleteJButton.setText(DBSAResourceBundle.res.getString("delete"));
		closeJButton.setText(DBSAResourceBundle.res.getString("close"));
		actionsJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("actions")));
		resultsJScrollPane.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("result.list")));
		getColumnName();
		selectAllDupButton.setText(DBSAResourceBundle.res.getString("select.duplicate"));
		selectAllButton.setText(DBSAResourceBundle.res.getString("select.all"));
		clearSelectedJButton.setText(DBSAResourceBundle.res.getString("clear.selected"));
	}

	private JButton getSelectAllDupButton() {
		if (selectAllDupButton == null) {
			selectAllDupButton = new JButton();
			selectAllDupButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(int i = 0; i < resultsJTable.getRowCount(); i++){
						if(resultsJTable.getModel().getValueAt(i, 8) != null
								&&resultsJTable.getModel().getValueAt(i, 8).toString().equals("true")){
							resultsJTable.getModel().setValueAt(true, i, 7);
							deleteJButton.setEnabled(true);
							saveJButton.setEnabled(true);
						}else{
							resultsJTable.getModel().setValueAt(false, i, 7);
						}
					}
					
					if(resultsJTable.getRowCount() == 0){
						selectAllButton.setEnabled(false);
						selectAllDupButton.setEnabled(false);
						deleteJButton.setEnabled(false);
						saveJButton.setEnabled(false);
					}
					
				}
			});
			
			selectAllDupButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("select.all.the.duplicate.article"));
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
		return selectAllDupButton;
	}

	private JButton getSelectAllButton() {
		if (selectAllButton == null) {
			selectAllButton = new JButton();
			selectAllButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(int i =0; i < resultsJTable.getRowCount(); i++){
						resultsJTable.getModel().setValueAt(true, i, 7);
						deleteJButton.setEnabled(true);
						saveJButton.setEnabled(true);
					}
					
					if(resultsJTable.getRowCount() == 0){
						selectAllButton.setEnabled(false);
						selectAllDupButton.setEnabled(false);
						deleteJButton.setEnabled(false);
						saveJButton.setEnabled(false);
					}
				}
				
			});
			
			selectAllButton.addMouseListener(new MouseListener(){

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
		return selectAllButton;
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
				public void mouseEntered(MouseEvent e) {	
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {	
				}
				public void mouseReleased(MouseEvent e) {	}
				
			});
			
		}
		return linkJEditorPane;
	}

	private JLabel getLinkJLabel() {
		if (linkJLabel == null) {
			linkJLabel = new JLabel();
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
			titleJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return titleJLabel;
	}

	private JLabel getPublisherJLabel() {
		if (publisherJLabel == null) {
			publisherJLabel = new JLabel();
			publisherJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return publisherJLabel;
	}

	private JLabel getAbstractJLabel() {
		if (abstractJLabel == null) {
			abstractJLabel = new JLabel();
			abstractJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return abstractJLabel;
	}

	private JLabel getYearJLabel() {
		if (yearJLabel == null) {
			yearJLabel = new JLabel();
			yearJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return yearJLabel;
	}

	private JLabel getAuthorsJLabel() {
		if (authorsJLabel == null) {
			authorsJLabel = new JLabel();
			authorsJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return authorsJLabel;
	}
	
	public DefaultTableModel getDefaultTableModel(){
		return model;
	}
	/*
	 * Ham tao Jtable
	 */
	public MyJTable createResultJTable(){
		model = new DefaultTableModel(getTableData(getRowNumber(), getTitle(), getAuthor(), getHyperLink(), getYear(), getAbstract(), getPublisher(), getMark(), getIsDuplicate(), getDigitalLibrary()), getColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, URL.class, String.class, String.class, String.class, Boolean.class, Boolean.class, String.class};

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
		
		table.getColumn("dlsName").setWidth(0);
		table.getColumn("dlsName").setMinWidth(0);
		table.getColumn("dlsName").setMaxWidth(0);
		
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
			model.removeRow(0);
		}else if(resultsJTable != null){
			
			for(int i = 0; i < getRowNumber(); i++){				
				if((i + 1) == getRowNumber()){
					
					String year;
					if(getYear() == 0){
						year = "";
					}else{
						year = getYear() + "";
					}
					Object [] data = {resultsJTable.getRowCount() + 1, getTitle(), getAuthor(), getLink(), year, getAbstract(), getPublisher(), getMark(), getIsDuplicate(), getDigitalLibrary()};
					model.insertRow(resultsJTable.getRowCount(), data );				
					
					System.out.println(" row number 2->" + model.getRowCount());
					
					DBSAPublication dbsa = new DBSAPublication();
					dbsa.setId(resultsJTable.getRowCount() + 1);
					dbsa.setTitle(getTitle());
					dbsa.setAuthors(getAuthor());
					dbsa.setLinks(getLink());
					dbsa.setYear(getYear());
					dbsa.setAbstractPub(getAbstract());
					dbsa.setPublisher(getPublisher());
					
					setIsDuplicate(false);
					setDigitalLibrary(getDigitalLibrary());
					
					dbsaPublicationCheckList.add(dbsa);
							
				}				
			}	
			
		}
		
		resultsJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			
				int n  = resultsJTable.getSelectedRow();
				
				System.out.println( "selected row " + resultsJTable.getSelectedRow() );
					
				titleJTextArea.setText(model.getValueAt(n, 1).toString());
				authorsJTextArea.setText(model.getValueAt(n, 2).toString());				
				linkJEditorPane.setText("<html><body><a href='" + "'>" + model.getValueAt(n, 3).toString() + "</><hr></body></html>");
				if(model.getValueAt(n, 4).equals(null) )
					yearJTextArea.setText("");
				else
					yearJTextArea.setText(model.getValueAt(n, 4).toString());
				abstractJTextArea.setText(model.getValueAt(n, 5).toString());
				publisherJTextArea.setText(resultsJTable.getModel().getValueAt(n, 6).toString());
				
				
				for(int i = 0; i < resultsJTable.getRowCount();i++){
					
					System.out.println("row number 3-->" +  resultsJTable.getRowCount());
					if(model.getValueAt(i, 7) != null
							&& model.getValueAt(i, 7).toString().equals("true")){
						checkEnable++;
						deleteJButton.setEnabled(true);
						saveJButton.setEnabled(true);
					}
						
					if(checkEnable == 0){
						deleteJButton.setEnabled(false);
						saveJButton.setEnabled(false);
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
	
	/***
	 * 
	 * @return
	 */
	public static void getFetcherInfo(){
		
		acmNumberResult = 0;
		acmDupInDblp = 0;
		acmNumber_Before2005 = 0;
		citeseerNumberResult = 0;
		citeseerDupInDblp = 0;
		citeseerNumber_Before2005 = 0;
		ieeeNumberResult = 0;
		ieeeDupInDblp = 0;
		ieeeNumber_Before2005 = 0;
		
		num_Total = resultsJTable.getRowCount() - old_Num_Total;
		old_Num_Total += num_Total;
		
		for(int i = 0; i < num_Total; i++){
			if(resultsJTable.getModel().getValueAt(i, 9).toString().equals("ACM")){
				acmNumberResult ++;
				if(resultsJTable.getModel().getValueAt(i, 8).toString().equals("true")){
					acmDupInDblp++;
				}
				if(resultsJTable.getModel().getValueAt(i, 4) != null && resultsJTable.getModel().getValueAt(i, 4).toString() != "")
					if(Integer.parseInt(resultsJTable.getModel().getValueAt(i, 4).toString()) < 2005){
						acmNumber_Before2005++;
					}
				
			}else if(resultsJTable.getModel().getValueAt(i, 9).toString().equals("CITESEER")){
				citeseerNumberResult ++;
				if(resultsJTable.getModel().getValueAt(i, 8).toString().equals("true")){
					citeseerDupInDblp++;
				}
				if(resultsJTable.getModel().getValueAt(i, 4) != null && resultsJTable.getModel().getValueAt(i, 4).toString() != "")
					if(Integer.parseInt(resultsJTable.getModel().getValueAt(i, 4).toString()) < 2005){
					citeseerNumber_Before2005++;
					}
			}else{
				ieeeNumberResult++;
				if(resultsJTable.getModel().getValueAt(i, 8).toString().equals("true")){
					ieeeDupInDblp++;
				}
				if(resultsJTable.getModel().getValueAt(i, 4) != null && resultsJTable.getModel().getValueAt(i, 4).toString() != "")
					if(Integer.parseInt(resultsJTable.getModel().getValueAt(i, 4).toString()) < 2005){
						ieeeNumber_Before2005++;
					}
			}
						
			if(resultsJTable.getModel().getValueAt(i, 8).toString().equals("true")){
				num_DupInDBLP++;
			}
		}
		//xoa cac field cu
		DBSAApplication.statisticPanel.removeAllRow();
				
		//them cac field moi		
		DBSAApplication.statisticPanel.setParameter(DBSAResourceBundle.res.getString("statistic.the.number.result.in.a.fetcher"));
		DBSAApplication.statisticPanel.setAcmDls(acmNumberResult + "");
		DBSAApplication.statisticPanel.setCiteseerDls(citeseerNumberResult + "");
		DBSAApplication.statisticPanel.setIeeeDls(ieeeNumberResult + "");
		DBSAApplication.statisticPanel.getstatisticJTable();
		
		//thong ke so ket qua tim duoc trung trong dblp	
		float acmDupInDatabase = 0;
		float citeseerDupInDatabase = 0;
		float ieeeDupInDatabase = 0;
		
		if(acmNumberResult != 0)
			acmDupInDatabase = (float)acmDupInDblp*100/acmNumberResult;
		else 
			acmDupInDatabase = 0;
		
		if(citeseerNumberResult != 0)
			citeseerDupInDatabase = (float)citeseerDupInDblp*100/citeseerNumberResult;
		else
			citeseerDupInDatabase = 0;
		
		if(ieeeNumberResult != 0)
			ieeeDupInDatabase = (float)ieeeDupInDblp*100/ieeeNumberResult;
		else
			ieeeDupInDatabase = 0;
		
		DBSAApplication.statisticPanel.setParameter(DBSAResourceBundle.res.getString("statistic.the.number.result.duplicate.in.dblp.data"));
		DBSAApplication.statisticPanel.setAcmDls(acmDupInDblp + " " + DBSAResourceBundle.res.getString("statistic.make.up") + acmDupInDatabase + "%");
		DBSAApplication.statisticPanel.setCiteseerDls(citeseerDupInDblp + " " + DBSAResourceBundle.res.getString("statistic.make.up") + citeseerDupInDatabase + "%");
		DBSAApplication.statisticPanel.setIeeeDls(ieeeDupInDblp  + " " + DBSAResourceBundle.res.getString("statistic.make.up") + ieeeDupInDatabase + "%");
		DBSAApplication.statisticPanel.getstatisticJTable();
		
		//thong ke so ket qua tim duoc truoc nam 2005		
		float acmBefore2005 = 0;
		float citeseerBefore2005  = 0;
		float ieeeBefore2005 = 0;
		
		if(acmNumberResult != 0)
			acmBefore2005 = (float)acmNumber_Before2005*100/acmNumberResult;
		else
			acmBefore2005 = 0;
		
		if(citeseerNumberResult != 0)
			citeseerBefore2005 = (float)citeseerNumber_Before2005*100/citeseerNumberResult;
		else
			citeseerBefore2005 = 0;
		
		if(ieeeNumberResult != 0)
			ieeeBefore2005 = (float)ieeeNumber_Before2005*100/ieeeNumberResult;
		else
			ieeeBefore2005 = 0;
		
		
		DBSAApplication.statisticPanel.setParameter(DBSAResourceBundle.res.getString("statistic.the.number.result.before.2005"));
		DBSAApplication.statisticPanel.setAcmDls(acmNumber_Before2005 + " " + DBSAResourceBundle.res.getString("statistic.make.up") + acmBefore2005 + "%");
		DBSAApplication.statisticPanel.setCiteseerDls(citeseerNumber_Before2005 + " " + DBSAResourceBundle.res.getString("statistic.make.up") + citeseerBefore2005 + "%");
		DBSAApplication.statisticPanel.setIeeeDls(ieeeNumber_Before2005 + " " + DBSAResourceBundle.res.getString("statistic.make.up") + ieeeBefore2005 + "%");
		DBSAApplication.statisticPanel.getstatisticJTable();
		
		System.out.println("TOtal-"+ num_DupInDBLP + " ACM-" + acmNumberResult + "-" + acmDupInDblp + 
				" CITE-" + citeseerNumberResult + "-" + citeseerDupInDblp + " IEEE-" + ieeeNumberResult + "-" + ieeeDupInDblp);
	}
	
	/*
	 * check article is duplicated
	 */
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public boolean checkArticleIsDuplicated(){
	
		num_DupInDBLP = 0;
				
		CheckExist check = new CheckExist();
		
		numberArray = (ArrayList<Integer>) check.CheckTitleSignaturePublications(dbsaPublicationCheckList).clone();
		duplicateNumber = numberArray.size();
		
		duplicationArtilce = false;
		if(numberArray.size() > 0){
			for(int i = 0; i < numberArray.size(); i++)
			{	
				model.setValueAt(true, numberArray.get(i), 8);
				resultsJTable.addRowToPaint(numberArray.get(i), Color.red);
				duplicationArtilce = true;
				
				num_DupInDBLP ++;
			}
		}
		getFetcherInfo();
		//System.out.println(num_DupInDBLP);
		checkDuplicateJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		DBSAApplication.statisticPanel.updateStatistic(num_Total);
		return duplicationArtilce;
	}
	
	public static boolean checkArticleIsDuplicated_2(){
		
		num_DupInDBLP = 0;
				
		CheckExist check = new CheckExist();
		
		numberArray = (ArrayList<Integer>) check.CheckTitleSignaturePublications(dbsaPublicationCheckList).clone();
		duplicateNumber = numberArray.size();
		
		duplicationArtilce = false;
		for(int i = 0; i < numberArray.size(); i++)
		{				
			duplicationArtilce = true;
			
			num_DupInDBLP ++;
		}
		getFetcherInfo();
		//System.out.println(num_DupInDBLP);
		
		DBSAApplication.statisticPanel.updateStatistic(num_Total);
		return duplicationArtilce;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getColumnName(){
		String [] columnNames = { DBSAResourceBundle.res.getString("no"), DBSAResourceBundle.res.getString("title"), 
				DBSAResourceBundle.res.getString("authors"), DBSAResourceBundle.res.getString("link"),
				DBSAResourceBundle.res.getString("year"),DBSAResourceBundle.res.getString("abstract"), 
				DBSAResourceBundle.res.getString("publisher"),DBSAResourceBundle.res.getString("mark"), 
				DBSAResourceBundle.res.getString("duplicate"), "dlsName"};
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate, String dlName){
		
		Object [][] data = {addTableData(rowNumber, title, author, hyperLink, year, abstracts, publisher, isMark, duplicate, dlName)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int rowNumber, String title, String author, URL hyperLink, int year, String abstracts, String publisher, boolean isMark, boolean duplicate, String dlName){
		Object [] dataRow =  {rowNumber, title, author, hyperLink,year,abstracts, publisher,isMark, duplicate, dlName};
		
		return dataRow;
	}
	
	private JPanel getEntryJPanel() {
		if (entryJPanel == null) {
			entryJPanel = new JPanel();
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
	private JButton checkDuplicateJButton;
	
	
	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setEnabled(false);
			
			saveJButton.addActionListener(new ActionListener(){

				//@SuppressWarnings("static-access")
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					

					//default icon, custom title
					int n = 0;
					boolean checkInsert = false;
					boolean checkSelect = false;
					
					for(int i = 0; i < resultsJTable.getRowCount(); i++){
						if(resultsJTable.getModel().getValueAt(i, 7).toString().equals("true"))
							checkSelect = true;
					}
					
					if(checkSelect && duplicateNumber > 0){
						n = JOptionPane.showConfirmDialog(
					    DBSAApplication.dbsaJFrame, DBSAResourceBundle.res.getString("notice.data.duplicate"),
					    "An Question", JOptionPane.YES_NO_OPTION);
					
						if(n == JOptionPane.YES_OPTION){
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("notice.select.rows.to.delete"));
						}else if(n == JOptionPane.NO_OPTION){
							checkInsert = insertToDatabase();
						}
					}
					
					if(checkSelect && duplicateNumber == 0){
						
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
					
					if(!checkSelect){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("you.have.not.chosen.article.to.save"));
					}
					
					if(checkInsert == true){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("add.database.sucessfully"));
						saveJButton.setEnabled(false);
					}
				}
				
			});
			
			saveJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("save.all.the.articles.is.selected"));
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
		return saveJButton;
	}
	
	public boolean insertToDatabase(){
		boolean checkInsert = false;
		try{
			if(resultsJTable != null){
				if(resultsJTable.getRowCount() > 0){
					ArrayList<DBSAPublication> dbsaPublicationList = new ArrayList<DBSAPublication>();
					InsertDBSAPublication insert = new InsertDBSAPublication();
					
					for(int i = 0; i < resultsJTable.getRowCount(); i++){
						if(Boolean.parseBoolean(model.getValueAt(i, 7).toString()) == true){
							
							DBSAPublication _dbsaPub = new DBSAPublication();
													
							String _title = "";
							String _author = "";
							String _link = "";
							int year = 0;	
							String _abstract = "";
							String _publisher = "";
							
							if(resultsJTable.getModel().getValueAt(i, 1) != null){
								_title = resultsJTable.getModel().getValueAt(i, 1).toString();
							}else{
								
							}
							_dbsaPub.setTitle(_title);
							
							if(resultsJTable.getModel().getValueAt(i, 2) != null){
								_author = resultsJTable.getModel().getValueAt(i, 2).toString();
							}else{
								
							}
							_dbsaPub.setAuthors(_author);
							
							if(resultsJTable.getModel().getValueAt(i, 3) != null){
								_link = resultsJTable.getModel().getValueAt(i, 3).toString();
							}else{
								
							}
							_dbsaPub.setLinks(_link);
							
							if(resultsJTable.getModel().getValueAt(i, 4).toString().equals("")
									|| resultsJTable.getModel().getValueAt(i, 4) == null){
								year = 0;
							}else{
								year = Integer.parseInt(resultsJTable.getModel().getValueAt(i, 4).toString());
							}
							_dbsaPub.setYear(year);						
							
							if(resultsJTable.getModel().getValueAt(i, 5) != null){
								_abstract = resultsJTable.getModel().getValueAt(i, 5).toString();
							}else{
								
							}
							_dbsaPub.setAbstractPub(_abstract);
							
							if(resultsJTable.getModel().getValueAt(i, 6) != null){
								_publisher = resultsJTable.getModel().getValueAt(i, 6).toString();
							}else{
								
							}
							_dbsaPub.setPublisher(_publisher);
							
							dbsaPublicationList.add(_dbsaPub);
							
						}
					}
					
					insert.InsertPublicationList(dbsaPublicationList);
						
					if(dbsaPublicationList.size() > 0){
						for(int i = dbsaPublicationList.size() - 1; i >= 0; i--){
							dbsaPublicationList.clear();
						}
					}
										
					for(int i = resultsJTable.getRowCount() - 1; i >= 0; i--){
						if(Boolean.parseBoolean(model.getValueAt(i, 7).toString()) == true){
							model.removeRow(i);
						}
					}
					
					for(int i = 0; i < resultsJTable.getRowCount(); i++){
						resultsJTable.getModel().setValueAt(i + 1, i, 0);
					}
					
					for(int i = 0; i < resultsJTable.getRowCount(); i++){
						resultsJTable.addRowToPaint(i, Color.white);					
						resultsJTable.getModel().setValueAt(i + 1, i, 0);
						
						if(Boolean.parseBoolean(model.getValueAt(i, 8).toString()) == true){
							resultsJTable.addRowToPaint( i, Color.red);
						}
					}
					
					titleJTextArea.setText("");
					authorsJTextArea.setText("");
					yearJTextArea.setText("");
					abstractJTextArea.setText("");
					publisherJTextArea.setText("");
					linkJEditorPane.setText("");
					
					saveJButton.setEnabled(false);
					checkInsert = true;
					
				}
			}	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return checkInsert;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setEnabled(false);
			deleteJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					removeRowsIsSelected();
					deleteJButton.setEnabled(false);
				}	
			});
			
			deleteJButton.addMouseListener(new MouseListener(){

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
		return deleteJButton;
	}
		
	private void removeRowsIsSelected() {
		
		if(resultsJTable != null){
			if(resultsJTable.getRowCount() > 0 ){
				System.out.println("number  - "+ resultsJTable.getRowCount());
				for(int i = resultsJTable.getRowCount() - 1; i >= 0; i--){
					System.out.println("number " + i + " - "+ resultsJTable.getRowCount());
					if(resultsJTable.getModel().getValueAt(i, 7) != null){
						if(Boolean.parseBoolean(resultsJTable.getModel().getValueAt(i, 7).toString()) == true){
							
							resultsJTable.getModel().setValueAt(false, i, 8);
							resultsJTable.getModel().setValueAt(false, i, 7);
							model.removeRow(i);
						}
					}
				}
				
				if(old_Num_Total > 0)
					old_Num_Total --;
				
				duplicateNumber = 0;
				
				for(int k = 0; k < resultsJTable.getRowCount(); k++){
					if(resultsJTable.getModel().getValueAt(k, 8) != null
							&& resultsJTable.getModel().getValueAt(k, 8).toString().equals("true")){
						
						duplicateNumber ++;						
					}
				}
				
				for(int i = 0; i < resultsJTable.getRowCount(); i++){
					resultsJTable.addRowToPaint(i, Color.white);					
					resultsJTable.getModel().setValueAt(i + 1, i, 0);
					
					if(Boolean.parseBoolean(model.getValueAt(i, 8).toString()) == true){
						resultsJTable.addRowToPaint( i, Color.red);
					}
				}
			}
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
					dbsaStatus.setDBSAProgressMessage(("group.name"));
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		return closeJButton;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 81, 12, 12), new Leading(0, 26, 10, 8)));
			actionsJPanel.add(getDeleteJButton(), new Constraints(new Trailing(111, 81, 12, 12), new Leading(0, 26, 10, 8)));
			actionsJPanel.add(getSaveJButton(), new Constraints(new Trailing(210, 80, 12, 12), new Leading(0, 26, 12, 12)));
			actionsJPanel.add(getClearSelectedJButton(), new Constraints(new Trailing(308, 124, 495, 534), new Leading(0, 12, 12)));
			actionsJPanel.add(getSelectAllButton(), new Constraints(new Trailing(450, 124, 398, 398), new Leading(0, 12, 12)));
			actionsJPanel.add(getSelectAllDupButton(), new Constraints(new Trailing(592, 12, 12), new Leading(0, 12, 12)));
			actionsJPanel.add(getCheckDuplicateJButton(), new Constraints(new Trailing(734, 124, 12, 12), new Leading(0, 12, 12)));
		}
		return actionsJPanel;
	}

	public JScrollPane getResultsJScrollPane() {
		if (resultsJScrollPane == null) {
			resultsJScrollPane = new JScrollPane();
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
	
	public void setDigitalLibrary(String dl){
		digitalLibrary = dl;
	}
	
	public String getDigitalLibrary(){
		return digitalLibrary;
	}
}
