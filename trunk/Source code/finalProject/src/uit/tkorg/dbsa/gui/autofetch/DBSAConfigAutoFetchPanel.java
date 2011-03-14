package uit.tkorg.dbsa.gui.autofetch;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.cores.autofetch.DBSAConfigAutoFetch;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAConfigAutoFetchPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel timerLabel;
	private JLabel digitalLibraryJLabel;
	private JCheckBox acmDLJCheckBox;
	private JCheckBox citeseerDLJCheckBox;
	private JCheckBox ieeeDLJCheckBox;
	private JPanel parameterJPanel;
	private JLabel newKeywordJLabel;
	private JTextField newKeywordTextField;
	private JButton addNewKeywordButton;
	private JList keywordInDBJList;
	private JScrollPane jScrollPane0;
	private JList keywordJList;
	private JScrollPane keywordScrollPane;
	private JButton moveRightColJButton;
	private JButton moveLeftJButton;
	private JButton deleteJButton;
	private JButton deleteAllJButton;
	private JPanel keywordJPanel;
	private JButton closeJButton;
	private JButton saveJButton;
	private JPanel actionJPanel;
	private JComboBox timerJComboBox;
	private int width = 870 ;
	private int height = 656;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	
	private DefaultListModel keywordInDBListModel = new DefaultListModel();
	private DefaultListModel keywordListModel = new DefaultListModel();
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public DBSAConfigAutoFetchPanel() {
		initComponents();
		
	}

	public DBSAConfigAutoFetchPanel(JFrame mainJFrame) {
		super(mainJFrame, true);
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		DBSAConfigAutoFetch.loadParameterFile();
		
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Window parent) {
		super(parent);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DBSAConfigAutoFetchPanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getParameterJPanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 113, 10, 10)));
		add(getKeywordJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(125, 98, 10, 432)));
		add(getActionJPanel(), new Constraints(new Bilateral(0, 0, 0), new Trailing(19, 10, 563)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JComboBox getTimerJComboBox() {
		if (timerJComboBox == null) {
			timerJComboBox = new JComboBox();
			timerJComboBox.setModel(new DefaultComboBoxModel(new Object[] 
			    { "1 ngay", "2 ngay", "3 ngay", "4 ngay", "5 ngay", "6 ngay", 
				"1 tuan", "2 tuan", "3 tuan", "1 thang", "2 thang", "3 thang" }));
			timerJComboBox.setDoubleBuffered(false);
			timerJComboBox.setBorder(null);
			timerJComboBox.setSelectedIndex(DBSAConfigAutoFetch.getTimerSelected() - 1);
		}
		return timerJComboBox;
	}

	private JPanel getActionJPanel() {
		if (actionJPanel == null) {
			actionJPanel = new JPanel();
			actionJPanel.setBorder(BorderFactory.createTitledBorder(null, "Hoat dong", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionJPanel.setLayout(new GroupLayout());
			actionJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 106, 10, 10), new Leading(0, 38, 10, 10)));
			actionJPanel.add(getSaveJButton(), new Constraints(new Trailing(136, 106, 12, 12), new Leading(0, 38, 12, 12)));
		}
		return actionJPanel;
	}

	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Luu");
			saveJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DBSAConfigAutoFetch.setDLCheckboxStatus(timerJComboBox.getSelectedIndex() + 1, acmDLJCheckBox.isSelected(), 
							citeseerDLJCheckBox.isSelected(), ieeeDLJCheckBox.isSelected());
					DBSAConfigAutoFetch.saveKeyword(keywordListModel);
				}
				
			});
		}
		return saveJButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Dong");
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dispose();
				}
				
			});
		}
		return closeJButton;
	}

	private JPanel getKeywordJPanel() {
		if (keywordJPanel == null) {
			keywordJPanel = new JPanel();
			keywordJPanel.setBorder(BorderFactory.createTitledBorder(null, "Chon danh sach tu khoa tim kiem", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			keywordJPanel.setLayout(new GroupLayout());
			keywordJPanel.add(getNewKeywordJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			keywordJPanel.add(getNewKeywordTextField(), new Constraints(new Leading(143, 587, 10, 10), new Leading(4, 32, 12, 12)));
			keywordJPanel.add(getAddNewKeywordButton(), new Constraints(new Bilateral(748, 12, 81), new Leading(4, 32, 12, 12)));
			keywordJPanel.add(getJScrollPane0(), new Constraints(new Leading(11, 316, 10, 10), new Bilateral(53, 12, 22)));
			keywordJPanel.add(getkeywordScrollPane(), new Constraints(new Leading(536, 314, 10, 10), new Bilateral(55, 12, 22)));
			keywordJPanel.add(getMoveRightJButton(), new Constraints(new Leading(376, 120, 12, 12), new Leading(83, 49, 10, 10)));
			keywordJPanel.add(getMoveLeftJButton(), new Constraints(new Leading(376, 120, 12, 12), new Leading(161, 49, 10, 10)));
			keywordJPanel.add(getDeleteJButton(), new Constraints(new Leading(376, 120, 12, 12), new Leading(239, 49, 10, 10)));
			keywordJPanel.add(getDeleteAllJButton(), new Constraints(new Leading(376, 120, 12, 12), new Leading(313, 50, 10, 10)));
		}
		return keywordJPanel;
	}

	private JButton getDeleteAllJButton() {
		if (deleteAllJButton == null) {
			deleteAllJButton = new JButton();
			deleteAllJButton.setText("XOA TAT CA");
			deleteAllJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					DBSAConfigAutoFetch.deleteAllKeyword(keywordListModel);
				}
				
			});
		}
		return deleteAllJButton;
	}

	private JButton getDeleteJButton() {
		if (deleteJButton == null) {
			deleteJButton = new JButton();
			deleteJButton.setText("XOA");
			deleteJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] itemsIsSelected = keywordJList.getSelectedIndices();
					DBSAConfigAutoFetch.deleteKeyword(keywordListModel, itemsIsSelected);
				}
				
			});
		}
		return deleteJButton;
	}

	private JButton getMoveLeftJButton() {
		if (moveLeftJButton == null) {
			moveLeftJButton = new JButton();
			moveLeftJButton.setText("<<");
			moveLeftJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] itemsIsSelected = keywordJList.getSelectedIndices();					
					DBSAConfigAutoFetch.moveRightToKeyword(keywordInDBListModel, keywordListModel, itemsIsSelected);
				}
				
			});
		}
		return moveLeftJButton;
	}

	private JButton getMoveRightJButton() {
		if (moveRightColJButton == null) {
			moveRightColJButton = new JButton();
			moveRightColJButton.setText(">>");
			moveRightColJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] itemsIsSelected = keywordInDBJList.getSelectedIndices();
					DBSAConfigAutoFetch.moveLeftToRightKeyword(keywordInDBListModel, keywordListModel, itemsIsSelected );
				}
				
			});
			
		}
		return moveRightColJButton;
	}

	private JScrollPane getkeywordScrollPane() {
		if (keywordScrollPane == null) {
			keywordScrollPane = new JScrollPane();
			keywordScrollPane.setViewportView(getKeywordJList());
		}
		return keywordScrollPane;
	}

	private JList getKeywordJList() {
		if (keywordJList == null) {
			keywordJList = new JList();
			keywordJList.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
						
			keywordListModel = DBSAConfigAutoFetch.addItemToRightKeywordList(keywordListModel);
			
			keywordJList.setModel(keywordListModel);
		}
		return keywordJList;
	}
	
	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getKeywordInDBJList());
		}
		return jScrollPane0;
	}

	private JList getKeywordInDBJList() {
		if (keywordInDBJList == null) {
			keywordInDBJList = new JList();
			keywordInDBJList.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
			
			keywordInDBListModel = DBSAConfigAutoFetch.addItemToLeftKeyworList(keywordInDBListModel, 10);
			
			keywordInDBJList.setModel(keywordInDBListModel);
		}
		return keywordInDBJList;
	}

	private JButton getAddNewKeywordButton() {
		if (addNewKeywordButton == null) {
			addNewKeywordButton = new JButton();
			addNewKeywordButton.setText("Them");
			addNewKeywordButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String keyword = newKeywordTextField.getText();
					DBSAConfigAutoFetch.addNewKeyword(keywordListModel, keyword);
				}
				
			});
		}
		return addNewKeywordButton;
	}

	private JTextField getNewKeywordTextField() {
		if (newKeywordTextField == null) {
			newKeywordTextField = new JTextField();
			newKeywordTextField.setText("Tu khoa moi");
			newKeywordTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return newKeywordTextField;
	}

	private JLabel getNewKeywordJLabel() {
		if (newKeywordJLabel == null) {
			newKeywordJLabel = new JLabel();
			newKeywordJLabel.setText("Them tu khoa moi : ");
		}
		return newKeywordJLabel;
	}

	private JPanel getParameterJPanel() {
		if (parameterJPanel == null) {
			parameterJPanel = new JPanel();
			parameterJPanel.setBorder(BorderFactory.createTitledBorder(null, "Chon cac thong so de thu thap", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			parameterJPanel.setLayout(new GroupLayout());
			parameterJPanel.add(getTimerLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			parameterJPanel.add(getDigitalLibraryJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(46, 12, 12)));
			parameterJPanel.add(getAcmDLJCheckBox(), new Constraints(new Leading(164, 10, 10), new Leading(42, 8, 8)));
			parameterJPanel.add(getCiteseerDLJCheckBox(), new Constraints(new Leading(384, 10, 10), new Leading(42, 8, 8)));
			parameterJPanel.add(getIeeeDLJCheckBox(), new Constraints(new Leading(598, 10, 10), new Leading(42, 8, 8)));
			parameterJPanel.add(getTimerJComboBox(), new Constraints(new Leading(169, 162, 10, 10), new Leading(8, 12, 12)));
		}
		return parameterJPanel;
	}

	private JCheckBox getIeeeDLJCheckBox() {
		if (ieeeDLJCheckBox == null) {
			ieeeDLJCheckBox = new JCheckBox();
			ieeeDLJCheckBox.setText("Thu vien so IEEExplorer");
			ieeeDLJCheckBox.setSelected(DBSAConfigAutoFetch.getIeeeDLCheckboxStatus());
		}
		return ieeeDLJCheckBox;
	}

	private JCheckBox getCiteseerDLJCheckBox() {
		if (citeseerDLJCheckBox == null) {
			citeseerDLJCheckBox = new JCheckBox();
			citeseerDLJCheckBox.setSelected(true);
			citeseerDLJCheckBox.setText("Thu vien so Citeseer");
			citeseerDLJCheckBox.setSelected(DBSAConfigAutoFetch.getCiteseerDLCheckboxStatus());
		}
		return citeseerDLJCheckBox;
	}

	private JCheckBox getAcmDLJCheckBox() {
		if (acmDLJCheckBox == null) {
			acmDLJCheckBox = new JCheckBox();
			acmDLJCheckBox.setText("Thu vien so ACM");
			acmDLJCheckBox.setSelected(DBSAConfigAutoFetch.getAcmDLCheckboxStatus());
		}
		return acmDLJCheckBox;
	}

	private JLabel getDigitalLibraryJLabel() {
		if (digitalLibraryJLabel == null) {
			digitalLibraryJLabel = new JLabel();
			digitalLibraryJLabel.setText("Chon thu vien so :");
		}
		return digitalLibraryJLabel;
	}

	private JLabel getTimerLabel() {
		if (timerLabel == null) {
			timerLabel = new JLabel();
			timerLabel.setText("Chon thoi gian thu thap :");
		}
		return timerLabel;
	}

}
