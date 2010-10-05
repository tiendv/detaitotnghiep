package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JButton closeJButton;
	private JButton deleteJButton;
	private JButton saveJButton;
	private JList jList0;
	private JScrollPane jScrollPane1;

	public FetcherResultPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(3, 3, 313), new Trailing(3, 60, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Bilateral(3, 3, 31), new Bilateral(4, 252, 10, 90)));
		add(getJPanel0(), new Constraints(new Bilateral(3, 3, 0), new Trailing(67, 182, 10, 10)));
		setSize(594, 346);
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJList0());
		}
		return jScrollPane1;
	}

	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			jList0.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1, true), "Border Title", TitledBorder.LEADING, TitledBorder.TOP,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jList0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("item0");
			listModel.addElement("item1");
			listModel.addElement("item2");
			listModel.addElement("item3");
			
			jList0.setModel(listModel);
			jList0.setLayoutOrientation(JList.VERTICAL_WRAP);
			jList0.setDoubleBuffered(true);
			jList0.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
			jList0.setInheritsPopupMenu(true);
			jList0.setDragEnabled(true);
			jList0.setValueIsAdjusting(true);
		}
		return jList0;
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

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createTitledBorder("Actions"));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getCloseJButton(), new Constraints(new Trailing(12, 81, 12, 12), new Leading(0, 12, 12)));
			jPanel1.add(getDeleteJButton(), new Constraints(new Trailing(111, 81, 12, 12), new Leading(0, 12, 12)));
			jPanel1.add(getSaveJButton(), new Constraints(new Trailing(210, 80, 12, 12), new Leading(0, 12, 12)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder("Entry detail"));
			jPanel0.setAlignmentX(0.5f);
			jPanel0.setAlignmentY(0.5f);
			jPanel0.setLayout(new GridLayout(2, 2));
			jPanel0.add(getJScrollPane1());
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	
	
	private JTable getJTable0() {
		if (jTable0 == null) {
//			String [] columnNames = new String[] { "No.", "Title", "Authors", "Year", "Abstract", "Publisher", "Chk"};
//			Object [][] obj = {
//							{new Integer(1),"Ten bai bao",  "Tac gia", "nam cong bo",  "Tom tat", "To chuc", new Boolean(true)}, 
//							{new Integer(2),"Ten bai bao",  "Tac gia", "nam cong bo",  "Tom tat", "To chuc", new Boolean(false)}};
			MyTableModel table = new MyTableModel();
			jTable0 = new JTable(new MyTableModel());
			
			//JTable0.
			//jTable0.getColumn("Title").setWidth(10);
//			jTable0.getColumn(2).setWidth(10);
//			jTable0.getColumn(3).setWidth(10);
//			jTable0.getColumn(4).setWidth(10);
//			jTable0.getColumn(5).setWidth(10);
//			jTable0.getColumn(6).setWidth(10);
			
//			jTable0.setModel(new DefaultTableModel(obj, new String[] { "No.", "Title", "Authors", "Year", "Abstract", "Publisher"}) {
//				private static final long serialVersionUID = 1L;
//				
//				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
//	
//				public Class<?> getColumnClass(int columnIndex) {
//					return types[columnIndex];
//				}
//			});
		}
		return jTable0;
	}

}
