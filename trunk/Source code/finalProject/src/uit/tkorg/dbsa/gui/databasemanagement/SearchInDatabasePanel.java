package uit.tkorg.dbsa.gui.databasemanagement;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.gui.main.DBSAApplication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SearchInDatabasePanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox searchByJComboBox;
	private JLabel keywordJLabel;
	private JLabel searchByJLabel;
	private JTextField keywordJTextField;
	private JPanel inputJPanel;
	private JPanel actionsJPanel;
	private JButton closeJButton;
	private JButton searchJButton;

	private int width = 403 ;
	private int height = 250;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	
	public SearchInDatabasePanel() {
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(JFrame mainJFrame) {
		super(mainJFrame, true);
		
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}
	
	public SearchInDatabasePanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public SearchInDatabasePanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent) {
		super(parent);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public SearchInDatabasePanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle("Search papers in database");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(0, 1, 0), new Trailing(10, 10, 128)));
		add(getInputJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 89, 10)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JButton getSearchJButton() {
		if (searchJButton == null) {
			searchJButton = new JButton();
			searchJButton.setText("Search");
			searchJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(keywordJTextField.getText() == ""){
						JOptionPane.showMessageDialog(null, "Please input key word to search.");
					}else{
						DBSAApplication.databaseManagementPanel.SearchResultInDatabase(keywordJTextField.getText(), searchByJComboBox.getSelectedIndex());
					}
					
					dispose();
				}
				
			});
		}
		return searchJButton;
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

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "-", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12),
					new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 102, 10, 10), new Bilateral(0, 12, 26)));
			actionsJPanel.add(getSearchJButton(), new Constraints(new Trailing(132, 102, 12, 12), new Leading(0, 35, 12, 12)));
		}
		return actionsJPanel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			inputJPanel.setBorder(BorderFactory.createTitledBorder(null, "input", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			inputJPanel.setLayout(new GroupLayout());
			inputJPanel.add(getKeywordJLabel(), new Constraints(new Leading(12, 88, 10, 10), new Leading(16, 12, 12)));
			inputJPanel.add(getSearchByJLabel(), new Constraints(new Leading(12, 88, 12, 12), new Leading(59, 25, 10, 10)));
			inputJPanel.add(getKeywordJTextField(), new Constraints(new Bilateral(116, 12, 4), new Leading(12, 25, 12, 12)));
			inputJPanel.add(getSearchByJComboBox(), new Constraints(new Bilateral(118, 15, 60), new Leading(59, 12, 12)));
		}
		return inputJPanel;
	}

	private JTextField getKeywordJTextField() {
		if (keywordJTextField == null) {
			keywordJTextField = new JTextField();
		}
		return keywordJTextField;
	}

	private JLabel getSearchByJLabel() {
		if (searchByJLabel == null) {
			searchByJLabel = new JLabel();
			searchByJLabel.setText("Search by :");
		}
		return searchByJLabel;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setText("Key word :");
		}
		return keywordJLabel;
	}

	private JComboBox getSearchByJComboBox() {
		if (searchByJComboBox == null) {
			searchByJComboBox = new JComboBox();
			searchByJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "Author name", "In the title",  }));
			searchByJComboBox.setDoubleBuffered(false);
			searchByJComboBox.setBorder(null);
		}
		return searchByJComboBox;
	}

}
