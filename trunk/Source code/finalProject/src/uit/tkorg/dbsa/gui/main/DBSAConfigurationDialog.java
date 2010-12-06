package uit.tkorg.dbsa.gui.main;

/**
 * 
 * @author Nguyen Phuoc Cuong
 * modifi: tiendv
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private int width = 442;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	private JToggleButton cancelJButton;
	private JToggleButton okJButton;
	private JPanel ationsJPanel;
	private JPanel lookAndFeelJPanel;
	private JPanel configurationJPanel;
	private JComboBox lookAndFeelJComboBox;
	private JComboBox languageJComboBox;
	private JComboBox fontSizeJComboBox;
	private JCheckBox fontBoldJCheckBox;
	private JCheckBox fontItalicJCheckBox;
	private JPanel fontOptionsJPanel;
	private JComboBox fontNameJComboBox;
	private JPanel languageJPanel;
	private ButtonGroup fontOptionsButtonGroup;
	private JRadioButton textCompJRadioButton;
	private JRadioButton menuCompJRadioButton;

	public DBSAConfigurationDialog() {
		initComponents();
	}

	public DBSAConfigurationDialog(JFrame mainFrame) {
		super(mainFrame, true);
		dbsaJFrame = mainFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth()-width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight()-height)/2;
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
		initFontOptionsButtonGroup();
		setSize(518, 400);
		setLocation(xLocation, yLocation);
	}

	private void initFontOptionsButtonGroup() {
		fontOptionsButtonGroup = new ButtonGroup();
		fontOptionsButtonGroup.add(getTextCompJRadioButton());
		fontOptionsButtonGroup.add(getMenuCompJRadioButton());
	}

	private JCheckBox getFontItalicJCheckBox() {
		if (fontItalicJCheckBox == null) {
			fontItalicJCheckBox = new JCheckBox();
			fontItalicJCheckBox.setText(DBSAResourceBundle.res.getString("italic"));
		}
		return fontItalicJCheckBox;
	}

	private JCheckBox getFontBoldJCheckBox() {
		if (fontBoldJCheckBox == null) {
			fontBoldJCheckBox = new JCheckBox();
			fontBoldJCheckBox.setText(DBSAResourceBundle.res.getString("bold"));
		}
		return fontBoldJCheckBox;
	}

	private JComboBox getFontSizeJComboBox() {
		if (fontSizeJComboBox == null) {
			fontSizeJComboBox = new JComboBox();
			fontSizeJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
			fontSizeJComboBox.setDoubleBuffered(false);
			fontSizeJComboBox.setBorder(null);
		}
		return fontSizeJComboBox;
	}

	private JRadioButton getTextCompJRadioButton() {
		if (textCompJRadioButton == null) {
			textCompJRadioButton = new JRadioButton();
			textCompJRadioButton.setSelected(true);
			textCompJRadioButton.setText(DBSAResourceBundle.res.getString("text.components.font"));
		}
		return textCompJRadioButton;
	}

	private JPanel getLanguageJPanel() {
		if (languageJPanel == null) {
			languageJPanel = new JPanel();
			languageJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("language")));
			languageJPanel.setLayout(new GroupLayout());
			languageJPanel.add(getLanguageJComboBox(), new Constraints(new Bilateral(7, 12, 60), new Leading(0, 12, 12)));
		}
		return languageJPanel;
	}
	private JRadioButton getMenuCompJRadioButton() {
		if (menuCompJRadioButton == null) {
			menuCompJRadioButton = new JRadioButton();
			menuCompJRadioButton.setText(DBSAResourceBundle.res.getString("menu.component.font"));
		}
		return menuCompJRadioButton;
	}

	private JComboBox getLanguageJComboBox() {
		if (languageJComboBox == null) {
			languageJComboBox = new JComboBox();
			languageJComboBox.setModel(new DefaultComboBoxModel(new Object[] { DBSAApplicationConst.ENGLISH, DBSAApplicationConst.VIETNAM}));
			languageJComboBox.setDoubleBuffered(false);
			languageJComboBox.setBorder(null);
			
			if(Locale.getDefault().toString().equalsIgnoreCase(DBSAApplicationConst.VN_VN)
					|| Locale.getDefault().toString().equalsIgnoreCase(DBSAApplicationConst.VN)){
				languageJComboBox.setSelectedItem(DBSAApplicationConst.VIETNAM);
			}else{
				languageJComboBox.setSelectedItem(DBSAApplicationConst.ENGLISH);
			}
		}
		return languageJComboBox;
	}

	private JComboBox getLookandFeelJComboBox() {
		if (lookAndFeelJComboBox == null) {
			lookAndFeelJComboBox = new JComboBox();
			lookAndFeelJComboBox.setModel(new DefaultComboBoxModel(
						new Object[] { 
								DBSAResourceBundle.swingRes.getString("swing.installedlaf.motif.name"), 
								DBSAResourceBundle.swingRes.getString("swing.installedlaf.metal.name"), 
								DBSAResourceBundle.swingRes.getString("swing.installedlaf.windows.name"), 
								DBSAResourceBundle.swingRes.getString("swing.installedlaf.mac.name"),
								DBSAResourceBundle.swingRes.getString("swing.installedlaf.liquid.name")
						}));
			lookAndFeelJComboBox.setDoubleBuffered(false);
			lookAndFeelJComboBox.setBorder(null);
			
			String lookAndFeelStyle = UIManager.getLookAndFeel().getName();
			lookAndFeelJComboBox.setSelectedItem(lookAndFeelStyle);
		}
		return lookAndFeelJComboBox;
	}

	private JPanel getConfigurationJPanel() {
		if (configurationJPanel == null) {
			configurationJPanel = new JPanel();
			configurationJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("configuration.system")));
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
			lookAndFeelJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("look.and.feel")));
			lookAndFeelJPanel.setLayout(new GroupLayout());
			lookAndFeelJPanel.add(getLookandFeelJComboBox(), new Constraints(new Bilateral(9, 12, 60), new Leading(0, 12, 12)));
		}
		return lookAndFeelJPanel;
	}
	private JComboBox getFontNameJComboBox() {
		if (fontNameJComboBox == null) {
			fontNameJComboBox = new JComboBox();
			fontNameJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "Times New Romance", "Tahoma" }));
			fontNameJComboBox.setDoubleBuffered(false);
			fontNameJComboBox.setBorder(null);
		}
		return fontNameJComboBox;
	}
	private ButtonGroup getFontOptionsButtonGroup() {
		if (fontOptionsButtonGroup == null) {
			fontOptionsButtonGroup = new ButtonGroup();
			fontOptionsButtonGroup.add(textCompJRadioButton);
			fontOptionsButtonGroup.add(menuCompJRadioButton);
		}
		return fontOptionsButtonGroup;
	}
	private JPanel getFontOptionJPanel() {
		if (fontOptionsJPanel == null) {
			fontOptionsJPanel = new JPanel();
			fontOptionsJPanel.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("font.options")));
			fontOptionsJPanel.setLayout(new GroupLayout());
			fontOptionsJPanel.add(getTextCompJRadioButton(), new Constraints(new Leading(3, 10, 10), new Leading(12, 10, 10)));
			fontOptionsJPanel.add(getMenuCompJRadioButton(), new Constraints(new Leading(168, 10, 10), new Leading(12, 8, 8)));
			fontOptionsJPanel.add(getFontBoldJCheckBox(), new Constraints(new Leading(3, 8, 8), new Leading(88, 8, 8)));
			fontOptionsJPanel.add(getFontItalicJCheckBox(), new Constraints(new Leading(80, 10, 10), new Leading(88, 8, 8)));
			fontOptionsJPanel.add(getFontNameJComboBox(), new Constraints(new Leading(6, 345, 12, 12), new Leading(55, 10, 10)));
			fontOptionsJPanel.add(getFontSizeJComboBox(), new Constraints(new Leading(357, 55, 10, 10), new Leading(55, 12, 12)));
			
			getFontOptionsButtonGroup();
		}
		return fontOptionsJPanel;
	}

	private JPanel getAtionsJPanel() {
		if (ationsJPanel == null) {
			ationsJPanel = new JPanel();
			ationsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			ationsJPanel.setLayout(new GroupLayout());
			ationsJPanel.add(getCancelJButton(), new Constraints(new Trailing(12, 124, 147), new Leading(2, 10, 10)));
			ationsJPanel.add(getOkJButton(), new Constraints(new Trailing(103, 73, 12, 12), new Leading(2, 12, 12)));
		}
		return ationsJPanel;
	}

	private JToggleButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JToggleButton();
			okJButton.setText("  Ok  ");
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
			cancelJButton.setText(DBSAResourceBundle.res.getString("close"));
			cancelJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
				
			});
		}
		return cancelJButton;
	}
	
	private void okJButtonAction(ActionEvent event){
		try
		{
		
			String lookAndFeel = (String) this.lookAndFeelJComboBox.getSelectedItem();
			if (lookAndFeel.equalsIgnoreCase(DBSAResourceBundle.swingRes.getString("swing.installedlaf.metal.name"))) {
				UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.metal.class"));
			}
			if (lookAndFeel.equalsIgnoreCase(DBSAResourceBundle.swingRes.getString("swing.installedlaf.motif.name"))){
				UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.motif.class"));
			}
			if (lookAndFeel.equalsIgnoreCase(DBSAResourceBundle.swingRes.getString("swing.installedlaf.windows.name"))){
				UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.windows.class"));
			}
			
			if (lookAndFeel.equalsIgnoreCase(DBSAResourceBundle.swingRes.getString("swing.installedlaf.mac.name"))){
				 UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.mac.class"));
			}
			
			if (lookAndFeel.equalsIgnoreCase(DBSAResourceBundle.swingRes.getString("swing.installedlaf.liquid.name"))){
				
				 UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.liquid.class"));
				 com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
				 com.birosoft.liquid.LiquidLookAndFeel.setShowTableGrids(true);
				 com.birosoft.liquid.LiquidLookAndFeel.setToolbarFlattedButtons(true);
			}
			/**
			 * Select languages
			 */
			String language = (String)this.languageJComboBox.getSelectedItem();
			if(language.equalsIgnoreCase(DBSAApplicationConst.VIETNAM)){
				Locale.setDefault(new Locale(DBSAApplicationConst.vn, DBSAApplicationConst.VN));
				DBSAResourceBundle.res = DBSAResourceBundle.initResources();
				DBSAApplication.updateTextOfComponents();
			}else if(language.equalsIgnoreCase(DBSAApplicationConst.ENGLISH)){
				Locale.setDefault(Locale.US);
				DBSAResourceBundle.res = DBSAResourceBundle.initResources();
				DBSAApplication.updateTextOfComponents();
			}
			SwingUtilities.updateComponentTreeUI(dbsaJFrame);
			this.dispose();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
}
