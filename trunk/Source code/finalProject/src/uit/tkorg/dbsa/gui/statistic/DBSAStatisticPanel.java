package uit.tkorg.dbsa.gui.statistic;

import java.net.URL;

import javax.swing.BorderFactory;
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

import uit.tkorg.dbsa.gui.main.DBSAApplication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAStatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable statisticJTable;
	private JScrollPane statistticJScrollPanel;
	private JPanel mainJPanel;
	
	private String parameters = "";
	private String acmDls = "";
	private String citeseerDls = "";
	private String ieeeDls = "";
	
	private DefaultTableModel model =  new DefaultTableModel();
	private JTextArea statisticJTextArea;
	private JScrollPane jScrollPane0;
	
	private int resultNumber = 0;
	private int dupInDBLPNumber = 0;
	private int dupInDatabase = 0;
	
	public DBSAStatisticPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getmainJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(585, 395);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getstatisticJTextArea());
		}
		return jScrollPane0;
	}

	private JTextArea getstatisticJTextArea() {
		if (statisticJTextArea == null) {
			statisticJTextArea = new JTextArea();
			statisticJTextArea.setEditable(false);
			statisticJTextArea.setText("Fetcher result statistic:\n\n 1. Keyword: " + DBSAApplication.fetcherPanel.keyword + "\n 2. Result total: \n 3. The article number is duplicated in DPLB database."
					+ "\n 4. The article number is duplicated in your database : \n ");
			statisticJTextArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return statisticJTextArea;
	}

	private JPanel getmainJPanel() {
		if (mainJPanel == null) {
			mainJPanel = new JPanel();
			mainJPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			mainJPanel.setLayout(new GroupLayout());
			mainJPanel.add(getJScrollPane0(), new Constraints(new Bilateral(0, 0, 22), new Leading(1, 140, 10, 10)));
			mainJPanel.add(getstatistticJScrollPanel(), new Constraints(new Bilateral(1, 0, 22), new Bilateral(159, 0, 26, 403)));
		}
		return mainJPanel;
	}

	private JScrollPane getstatistticJScrollPanel() {
		if (statistticJScrollPanel == null) {
			statistticJScrollPanel = new JScrollPane();
			statistticJScrollPanel.setViewportView(getstatisticJTable());
		}
		return statistticJScrollPanel;
	}

	public JTable createResultJTable(){
		model = new DefaultTableModel(getTableData(), getColumnName()) {
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
		table.setRowHeight(25);
 	
		for(int i = 0; i < 4; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(400);
			}else if(i == 1){
				col.setPreferredWidth(200);
			}else if(i == 2){
				col.setPreferredWidth(200);
			}else if(i == 3){
				col.setPreferredWidth(200);
			}
		}
		
		
		return table;
	}
	
	/*
	 * Ham tao danh sach ten cac cot trong table
	 * @return String []
	 */
	private static  String [] getColumnName(){
		String [] columnNames = { "Parameters", "ACM DL", "CiteSeer DL", "IEEExplore DL"};
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(){
		
		Object [][] data = {{getParameter(), getAcmDls(), getCiteseerDls(), getIeeeDls()}, 
							{getParameter(), getAcmDls(), getCiteseerDls(), getIeeeDls()}};
		
		return data;
		
	}
	
	public  Object [] addTableData(){
		Object [] data =  {getParameter(), getAcmDls(), getCiteseerDls(), getIeeeDls()};
		
		return data;
	}
	
	private JTable getstatisticJTable() {
		if (statisticJTable == null) {
			statisticJTable = createResultJTable();
			
		}
		return statisticJTable;
	}

	public  void setParameter(String parameter){
		parameters = parameter;
	}
	
	public   String getParameter(){
		return parameters;
	}
	
	public  void setAcmDls(String acmDl){
		acmDls = acmDl;
	}

	public   String getAcmDls(){
		return acmDls;
	}
	
	public  void setCiteseerDls(String citeseerDl){
		citeseerDls = citeseerDl;
	}

	public  String getCiteseerDls(){
		return citeseerDls;
	}
	
	public void setIeeeDls(String ieeeDl){
		ieeeDls = ieeeDl;
	}
	
	public String getIeeeDls(){
		return ieeeDls;
	}
	
}
