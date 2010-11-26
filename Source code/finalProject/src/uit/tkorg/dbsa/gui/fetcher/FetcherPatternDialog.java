package uit.tkorg.dbsa.gui.fetcher;

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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	private JPanel jPanel0;
	private JLabel patternNameJLabel;
	private JTextField patternNameJTextField;
	private JTextField patternValueJTextField;
	private JLabel patternValueJLabel;
	private JLabel descriptionJLabel;
	private JTextField descriptionJTextField;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
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
	
	public FetcherPatternDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public FetcherPatternDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
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
		add(getJPanel0(), new Constraints(new Bilateral(12, 12, 880), new Bilateral(64, 92, 10, 505)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JTextField getDescriptionJTextField() {
		if (descriptionJTextField == null) {
			descriptionJTextField = new JTextField();
			descriptionJTextField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return descriptionJTextField;
	}

	private JLabel getDescriptionJLabel() {
		if (descriptionJLabel == null) {
			descriptionJLabel = new JLabel();
			descriptionJLabel.setText("Description");
			descriptionJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return descriptionJLabel;
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
			patternValueJTextField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return patternValueJTextField;
	}

	private JTextField getPatternNameJTextField() {
		if (patternNameJTextField == null) {
			patternNameJTextField = new JTextField();
			patternNameJTextField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
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

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getChooseDLJPanel(), new Constraints(new Leading(12, 846, 12, 12), new Leading(12, 60, 47, 438)));
			jPanel0.add(getPatternNameJLabel(), new Constraints(new Leading(12, 132, 28, 87), new Leading(361, 29, 12, 12)));
			jPanel0.add(getPatternNameJTextField(), new Constraints(new Bilateral(159, 12, 4), new Leading(361, 28, 12, 12)));
			jPanel0.add(getPatternValueJTextField(), new Constraints(new Leading(159, 699, 12, 12), new Leading(401, 28, 12, 12)));
			jPanel0.add(getPatternValueJLabel(), new Constraints(new Leading(12, 132, 12, 12), new Leading(401, 28, 12, 12)));
			jPanel0.add(getDescriptionJLabel(), new Constraints(new Leading(12, 132, 12, 12), new Leading(441, 28, 12, 12)));
			jPanel0.add(getDescriptionJTextField(), new Constraints(new Leading(159, 699, 12, 12), new Leading(441, 28, 12, 12)));
			jPanel0.add(getFetcherJTableJScrollPane(), new Constraints(new Bilateral(12, 12, 846), new Leading(76, 267, 12, 12)));
		}
		return jPanel0;
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

	private JTable getPatternJTable() {
		if (patternJTable == null) {
			patternJTable = new JTable();
			patternJTable.setModel(new DefaultTableModel(new Object[][] { { 1, "ten", "gia tri", "giai thich", }, }, new String[] { "No.", "Pattern name", "Pattern value", "Description", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Integer.class, String.class, String.class, String.class };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		patternJTable.setShowGrid(true);
		patternJTable.setShowVerticalLines(true);
		patternJTable.setShowHorizontalLines(true);
		
		for(int i = 0; i < 4; i++){
			TableColumn col = patternJTable.getColumnModel().getColumn(i);
			if(i == 0){
				col.setPreferredWidth(1);
			}else if(i == 1){
				col.setPreferredWidth(33);
			}else if(i == 2){
				col.setPreferredWidth(33);
			}else if(i == 3){
				col.setPreferredWidth(33);
			}
		}
		return patternJTable;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 118, 12, 12), new Leading(6, 29, 10, 10)));
			actionsJPanel.add(getSaveJButton(), new Constraints(new Trailing(148, 118, 12, 12), new Leading(6, 29, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getSaveJButton() {
		if (saveJButton == null) {
			saveJButton = new JButton();
			saveJButton.setText("Save changes");
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
					// TODO Auto-generated method stub
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
			digitalLibraryComboBox.setModel(new DefaultComboBoxModel(new Object[] { "ACM digital library", "Citeseer digital library", "IEEExplore digital library" }));
			digitalLibraryComboBox.setDoubleBuffered(false);
			digitalLibraryComboBox.setBorder(null);
		}
		return digitalLibraryComboBox;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FetcherPatternDialog dialog = new FetcherPatternDialog();
				dialog
						.setDefaultCloseOperation(FetcherPatternDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("FetcherPatternDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
