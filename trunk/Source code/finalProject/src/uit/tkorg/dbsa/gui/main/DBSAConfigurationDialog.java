package uit.tkorg.dbsa.gui.main;

/**
 * 
 * @author Nguyen Phuoc Cuong
 */

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private int width = 442;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame disaJFrame;
	private JToggleButton cancelJButton;
	private JToggleButton okJButton;
	private JPanel ationsJPanel;
	private JPanel fontOptionJPanel;
	private JPanel lookAndFeelJPanel;
	private JPanel configurationJPanel;
	private JComboBox lookandFeelJComboBox;
	private JComboBox languageJComboBox;
	private JPanel languageJPanel;

	public DBSAConfigurationDialog() {
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame mainFrame) {
		super(mainFrame, true);
		disaJFrame = mainFrame;
		xLocation = disaJFrame.getX() + (disaJFrame.getWidth()-width)/2;
		yLocation = disaJFrame.getY() + (disaJFrame.getHeight()-height)/2;
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAConfigurationDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DBSAConfigurationDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DBSAConfigurationDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigurationDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DBSAConfigurationDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DBSAConfigurationDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public DBSAConfigurationDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DBSAConfigurationDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DBSAConfigurationDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DBSAConfigurationDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getConfigurationJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(375, 328);
		setLocation(xLocation, yLocation);
	}

	private JPanel getLanguageJPanel() {
		if (languageJPanel == null) {
			languageJPanel = new JPanel();
			languageJPanel.setBorder(BorderFactory.createTitledBorder("Languages"));
			languageJPanel.setLayout(new GroupLayout());
			languageJPanel.add(getLanguageJComboBox(), new Constraints(new Bilateral(7, 12, 60), new Leading(0, 12, 12)));
		}
		return languageJPanel;
	}

	private JComboBox getLanguageJComboBox() {
		if (languageJComboBox == null) {
			languageJComboBox = new JComboBox();
			languageJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "English", "Vietnamese" }));
			languageJComboBox.setDoubleBuffered(false);
			languageJComboBox.setBorder(null);
			
			if(Locale.getDefault().toString().equalsIgnoreCase("vn_VN")
					|| Locale.getDefault().toString().equalsIgnoreCase("VN")){
				languageJComboBox.setSelectedItem("Vietnamese");
			}else{
				languageJComboBox.setSelectedItem("English");
			}
		}
		return languageJComboBox;
	}

	private JComboBox getLookandFeelJComboBox() {
		if (lookandFeelJComboBox == null) {
			lookandFeelJComboBox = new JComboBox();
			lookandFeelJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "Windows" }));
			lookandFeelJComboBox.setDoubleBuffered(false);
			lookandFeelJComboBox.setBorder(null);
		}
		return lookandFeelJComboBox;
	}

	private JPanel getConfigurationJPanel() {
		if (configurationJPanel == null) {
			configurationJPanel = new JPanel();
			configurationJPanel.setBorder(BorderFactory.createTitledBorder("System configuration"));
			configurationJPanel.setLayout(new GroupLayout());
			configurationJPanel.add(getAtionsJPanel(), new Constraints(new Bilateral(0, 0, 0), new Trailing(0, 63, 10, 10)));
			configurationJPanel.add(getFontOptionJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(70, 69, 0)));
			configurationJPanel.add(getLookAndFeelJPanel(), new Constraints(new Leading(0, 178, 10, 10), new Leading(0, 58, 75, 75)));
			configurationJPanel.add(getLanguageJPanel(), new Constraints(new Trailing(0, 177, 10, 10), new Leading(0, 58, 75, 75)));
		}
		return configurationJPanel;
	}

	private JPanel getLookAndFeelJPanel() {
		if (lookAndFeelJPanel == null) {
			lookAndFeelJPanel = new JPanel();
			lookAndFeelJPanel.setBorder(BorderFactory.createTitledBorder("Look and feel"));
			lookAndFeelJPanel.setLayout(new GroupLayout());
			lookAndFeelJPanel.add(getLookandFeelJComboBox(), new Constraints(new Bilateral(9, 12, 60), new Leading(0, 12, 12)));
		}
		return lookAndFeelJPanel;
	}

	private JPanel getFontOptionJPanel() {
		if (fontOptionJPanel == null) {
			fontOptionJPanel = new JPanel();
			fontOptionJPanel.setBorder(BorderFactory.createTitledBorder("Font options"));
			fontOptionJPanel.setLayout(new GroupLayout());
		}
		return fontOptionJPanel;
	}

	private JPanel getAtionsJPanel() {
		if (ationsJPanel == null) {
			ationsJPanel = new JPanel();
			ationsJPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
			ationsJPanel.setLayout(new GroupLayout());
			ationsJPanel.add(getCancelJButton(), new Constraints(new Leading(202, 10, 10), new Leading(0, 12, 12)));
			ationsJPanel.add(getOkJButton(), new Constraints(new Leading(62, 73, 10, 10), new Leading(0, 12, 12)));
		}
		return ationsJPanel;
	}

	private JToggleButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JToggleButton();
			okJButton.setText("Ok");
			okJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					okJButtonAction(e);
				}
				
			});
		}
		return okJButton;
	}

	private JToggleButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JToggleButton();
			cancelJButton.setText("Cancel");
		}
		return cancelJButton;
	}
	
	private void okJButtonAction(ActionEvent event){
		/**
		 * Select languages
		 */
		String language = (String)this.languageJComboBox.getSelectedItem();
		if(language.equalsIgnoreCase("Vietnamese")){
			Locale.setDefault(new Locale("vn", "VN"));
			DBSAApplication.updateTextOfComponents();
			DBSAResourceBundle.res = DBSAResourceBundle.initResources();
		}else if(language.equalsIgnoreCase("English")){
			Locale.setDefault(Locale.US);
			DBSAResourceBundle.res = DBSAResourceBundle.initResources();
			DBSAApplication.updateTextOfComponents();
		}
		this.dispose();
		
	}
}
