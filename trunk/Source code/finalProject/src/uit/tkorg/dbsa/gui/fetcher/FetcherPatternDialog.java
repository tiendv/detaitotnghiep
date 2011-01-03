package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
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

import uit.tkorg.dbsa.gui.main.DBSAApplication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPatternDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox digitalLibraryComboBox;
	private JLabel chooseDLJLabel;
	private JButton closeJButton;
	private JButton saveJButton;
	private JPanel actionsJPanel;
	private JTable patternJTable;
	private JScrollPane fetcherJTableJScrollPane;
	private JLabel dialogNameJLabel;
	private JPanel chooseDLJPanel;
	
	private JFrame dbsaJFrame;
	private int xLocation;
	private int yLocation;
	private int width = 900;
	private int height = 700;
	private JPanel mainJPanel;
	private JLabel patternNameJLabel;
	private JTextField patternNameJTextField;
	private JTextField patternValueJTextField;
	private JLabel patternValueJLabel;
	private JLabel patternDescriptionJLabel;
	private JTextField patternDescriptionJTextField;
	
	private static DefaultTableModel model;
	private int number = 0;
	private String patternName;
	private String patternValue;
	private String patternDescription;
	private JButton setDefaultJButton;
	
	public FetcherPatternDialog() {
		initComponents();
	}

	public FetcherPatternDialog(JFrame mainFrame) {
		super(mainFrame, true);
		dbsaJFrame = mainFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth()-width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight()-height)/2;
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getDialogNameJLabel(), new Constraints(new Bilateral(335, 331, 220), new Leading(24, 34, 227, 618)));
		add(getActionsJPanel(), new Constraints(new Bilateral(12, 12, 288), new Trailing(10, 70, 10, 581)));
		add(getMainJPanel(), new Constraints(new Bilateral(12, 12, 880), new Bilateral(64, 92, 10, 505)));
		setSize(905, 700);
		setLocation(xLocation, yLocation);
	}

	private JButton getSetDefaultJButton() {
		if (setDefaultJButton == null) {
			setDefaultJButton = new JButton();
			setDefaultJButton.setText("Set Default");
		}
		return setDefaultJButton;
	}

	private JTextField getDescriptionJTextField() {
		if (patternDescriptionJTextField == null) {
			patternDescriptionJTextField = new JTextField();
			patternDescriptionJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
		}
		return patternDescriptionJTextField;
	}

	private JLabel getDescriptionJLabel() {
		if (patternDescriptionJLabel == null) {
			patternDescriptionJLabel = new JLabel();
			patternDescriptionJLabel.setText("Description");
			patternDescriptionJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return patternDescriptionJLabel;
	}

	private JLabel getPatternValueJLabel() {
		if (patternValueJLabel == null) {
			patternValueJLabel = new JLabel();
			patternValueJLabel.setText("Pattern value");
			patternValueJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return patternValueJLabel;
	}

	private JTextField getPatternValueJTextField() {
		if (patternValueJTextField == null) {
			patternValueJTextField = new JTextField();
			patternValueJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			//patternValueJTextField.setEditable(false);
		}
		return patternValueJTextField;
	}

	private JTextField getPatternNameJTextField() {
		if (patternNameJTextField == null) {
			patternNameJTextField = new JTextField();
			patternNameJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			patternNameJTextField.setEditable(false);
		}
		return patternNameJTextField;
	}

	private JLabel getPatternNameJLabel() {
		if (patternNameJLabel == null) {
			patternNameJLabel = new JLabel();
			patternNameJLabel.setText("Pattern name:");
			patternNameJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return patternNameJLabel;
	}

	private JPanel getMainJPanel() {
		if (mainJPanel == null) {
			mainJPanel = new JPanel();
			mainJPanel.setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			mainJPanel.setLayout(new GroupLayout());
			mainJPanel.add(getChooseDLJPanel(), new Constraints(new Leading(12, 846, 12, 12), new Leading(12, 60, 47, 438)));
			mainJPanel.add(getPatternNameJLabel(), new Constraints(new Leading(12, 132, 28, 87), new Leading(361, 29, 12, 12)));
			mainJPanel.add(getPatternNameJTextField(), new Constraints(new Bilateral(159, 12, 4), new Leading(361, 28, 12, 12)));
			mainJPanel.add(getPatternValueJTextField(), new Constraints(new Leading(159, 699, 12, 12), new Leading(401, 28, 12, 12)));
			mainJPanel.add(getPatternValueJLabel(), new Constraints(new Leading(12, 132, 12, 12), new Leading(401, 28, 12, 12)));
			mainJPanel.add(getDescriptionJLabel(), new Constraints(new Leading(12, 132, 12, 12), new Leading(441, 28, 12, 12)));
			mainJPanel.add(getDescriptionJTextField(), new Constraints(new Leading(159, 699, 12, 12), new Leading(441, 28, 12, 12)));
			mainJPanel.add(getFetcherJTableJScrollPane(), new Constraints(new Bilateral(12, 12, 846), new Leading(76, 267, 12, 12)));
		}
		return mainJPanel;
	}

	private JPanel getChooseDLJPanel() {
		if (chooseDLJPanel == null) {
			chooseDLJPanel = new JPanel();
			chooseDLJPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			chooseDLJPanel.setLayout(new GroupLayout());
			chooseDLJPanel.add(getChooseDLJLabel(), new Constraints(new Leading(9, 181, 10, 10), new Leading(15, 26, 10, 10)));
			chooseDLJPanel.add(getDigitalLibraryComboBox(), new Constraints(new Leading(202, 219, 12, 12), new Leading(16, 12, 12)));
			
		}
		return chooseDLJPanel;
	}

	private JLabel getDialogNameJLabel() {
		if (dialogNameJLabel == null) {
			dialogNameJLabel = new JLabel();
			dialogNameJLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			dialogNameJLabel.setText("   Change fetcher pattern");
			dialogNameJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return dialogNameJLabel;
	}

	private JScrollPane getFetcherJTableJScrollPane() {
		if (fetcherJTableJScrollPane == null) {
			fetcherJTableJScrollPane = new JScrollPane();
			fetcherJTableJScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			fetcherJTableJScrollPane.setViewportView(getPatternJTable());
		}
		return fetcherJTableJScrollPane;
	}


	/*
	 * Ham tao Jtable
	 */
	public JTable createPatternJTable(){
		model = new DefaultTableModel(getTableData(getNumber(), getPatternName(), getPatternValue(), getPatternDescriptionn()), getColumnName()) {
		private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, String.class,  };
	
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
		//table.setRowHeight(25);
			
		for(int i = 0; i < 4; i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(40);
			}else if(i == 1){
				col.setPreferredWidth(320);
			}else if(i == 2){
				col.setPreferredWidth(320);
			}else if(i == 3){
				col.setPreferredWidth(320);
			}
		}
		
		return table;
	}
	
	private JTable getPatternJTable() {
		if (patternJTable == null) {
			patternJTable = createPatternJTable();
			
			for(int i = patternJTable.getRowCount() - 1; i >= 0; i--){
				model.removeRow(i);
			}
			
			ArrayList<String> mpatternName = new ArrayList<String>();
			mpatternName = DBSAApplication.dbsaFetcherPattern.getPatternNameList("ACM");
			for (int i = 0; i < mpatternName.size(); i++) {
				
				String ptValue = DBSAApplication.dbsaFetcherPattern.getPatternValue(mpatternName.get(i));
				String ptDesciption = DBSAApplication.dbsaFetcherPattern.getPatternDescription(mpatternName.get(i));

				Object []data1 = {patternJTable.getRowCount(), mpatternName.get(i), ptValue, ptDesciption};
				model.insertRow(patternJTable.getRowCount(), data1);
			}
			
		}else{
			Object [] data = {patternJTable.getRowCount(), getPatternName(), getPatternValue(), getPatternDescriptionn()};
			model.insertRow(patternJTable.getRowCount(), data );
			
			
			
		}
		patternJTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowIsSelected = patternJTable.getSelectedRow();
				patternNameJTextField.setText(model.getValueAt(rowIsSelected, 1).toString());
				patternValueJTextField.setText(model.getValueAt(rowIsSelected, 2).toString());
				patternDescriptionJTextField.setText(model.getValueAt(rowIsSelected, 3).toString());
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
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
		
		return patternJTable;
	}

	/*
	 * Get Column name
	 */
	private  String [] getColumnName(){
		String [] columnNames = { /*DBSAResourceBundle.res.getString*/("no"), /*DBSAResourceBundle.res.getString*/("pattern.name"), 
				/*DBSAResourceBundle.res.getString*/("pattern.value"), /*DBSAResourceBundle.res.getString*/("description"), };
			
		return columnNames;
	}
	
	/*
	 * Ham input data cho table
	 * @return Object [][]
	 */
	public  Object [][] getTableData(int mNumber, String mPatternName, String mPatternValue, String mPatternDesciption){
		
		Object [][] data = {addTableData( mNumber, mPatternName, mPatternValue, mPatternDesciption)};
		
		return data;
		
	}
	
	public  Object [] addTableData(int mNumber, String mPatternName, String mPatternValue, String mPatternDesciption){
		Object [] dataRow =  {getNumber(), getPatternName(), getPatternValue(), getPatternDescriptionn()};
		
		return dataRow;
	}
	
	
	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 118, 12, 12), new Leading(6, 29, 10, 10)));
			actionsJPanel.add(getSaveJButton(), new Constraints(new Trailing(148, 118, 12, 12), new Leading(6, 29, 12, 12)));
			actionsJPanel.add(getSetDefaultJButton(), new Constraints(new Trailing(284, 118, 12, 12), new Leading(6, 29, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Save changes");
			saveJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					DBSAApplication.dbsaFetcherPattern.changePatternInfo(patternNameJTextField.getText(),
							patternValueJTextField.getText(),
							patternDescriptionJTextField.getText());
					
					DBSAApplication.dbsaFetcherPattern.savePatternFile();
				}
				
			});
		}
		return saveJButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Close");
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dispose();
				}
				
			});
		}
		return closeJButton;
	}

	private JLabel getChooseDLJLabel() {
		if (chooseDLJLabel == null) {
			chooseDLJLabel = new JLabel();
			chooseDLJLabel.setText("Choose digital library:");
		}
		return chooseDLJLabel;
	}

	private JComboBox getDigitalLibraryComboBox() {
		if (digitalLibraryComboBox == null) {
			digitalLibraryComboBox = new JComboBox();
			digitalLibraryComboBox.setModel(new DefaultComboBoxModel(
					new Object[] { "ACM digital library", "IEEExplore digital library" }));
			digitalLibraryComboBox.setDoubleBuffered(false);
			digitalLibraryComboBox.setBorder(null);
			
			digitalLibraryComboBox.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					if (digitalLibraryComboBox.getSelectedItem() == "ACM digital library") {
						
						for(int i = patternJTable.getRowCount() - 1; i >= 0; i--){
							model.removeRow(i);
						}
						ArrayList<String> mpatternName = new ArrayList<String>();
						mpatternName = DBSAApplication.dbsaFetcherPattern.getPatternNameList("ACM");
						for (int i = 0; i < mpatternName.size(); i++) {
							
							String ptValue = DBSAApplication.dbsaFetcherPattern.getPatternValue(mpatternName.get(i));
							String ptDesciption = DBSAApplication.dbsaFetcherPattern.getPatternDescription(mpatternName.get(i));

							Object []data = {patternJTable.getRowCount(), mpatternName.get(i), ptValue, ptDesciption};
							model.insertRow(patternJTable.getRowCount(), data);
						}
					}
					else if (digitalLibraryComboBox.getSelectedItem() == "IEEExplore digital library") {

						for(int i = patternJTable.getRowCount() - 1; i >= 0; i--){
							model.removeRow(i);
						}
						
						ArrayList<String> ptName = new ArrayList<String>();
						ptName = DBSAApplication.dbsaFetcherPattern.getPatternNameList("IEEE");
						for (int i = 0; i < ptName.size(); i++) {
							
							String ptValue = DBSAApplication.dbsaFetcherPattern.getPatternValue(ptName.get(i));
							String ptDesciption = DBSAApplication.dbsaFetcherPattern.getPatternDescription(ptName.get(i));
							
							Object []data = {patternJTable.getRowCount(), ptName.get(i), ptValue, ptDesciption};
							model.insertRow(patternJTable.getRowCount(), data);
						}
					}
				}
			});
		}
		return digitalLibraryComboBox;
	}

	public void setNumber(int mNumber){
		number = mNumber;
	}
	public int getNumber(){
		return number;
	}
	
	public void setPatternName(String ptName){
		patternName = ptName;
	}
	public String getPatternName(){
		return patternName;
	}
	
	public void setPatternValue(String mPatternValue){
		patternValue = mPatternValue;
	}
	public String getPatternValue(){
		return patternValue;
	}
	
	public void setPatternDescription(String mDescription){
		patternDescription = mDescription;
	}
	public String getPatternDescriptionn(){
		return patternDescription;
	}
}
