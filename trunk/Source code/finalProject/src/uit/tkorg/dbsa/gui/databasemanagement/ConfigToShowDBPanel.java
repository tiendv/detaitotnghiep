package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;
import org.freixas.jcalendar.JCalendar;

import uit.tkorg.dbsa.actions.database.GetParametersForStatisticTab;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.properties.files.MyDateListener;
import uit.tkorg.dbsa.properties.files.TimeShift;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ConfigToShowDBPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel actionJPanel;
	private JSpinner sizeJSpinner;
	private JSpinner beginNumJSpinner;
	private JLabel jLabel1;
	private JLabel sizeJLabel;
	private JPanel configJPanel;
	private JButton okJButton;

	private int sizeResultNumber;
	private int beginResultNumber;
	
	private int width = 375 ;
	private int height = 495;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	
	private final int MAX_RESULT = GetParametersForStatisticTab.getNumberOfPublicationsInDBLP();
	private JPanel calendarJPanel;
	private JCheckBox searchByDateJCheckBox;
	
	
	public ConfigToShowDBPanel() {
		initComponents();
	}

	public ConfigToShowDBPanel(JFrame mainJFrame) {
		super(mainJFrame, true);
		
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}

	public ConfigToShowDBPanel(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ConfigToShowDBPanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowDBPanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ConfigToShowDBPanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ConfigToShowDBPanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public ConfigToShowDBPanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ConfigToShowDBPanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowDBPanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ConfigToShowDBPanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ConfigToShowDBPanel(Window parent) {
		super(parent);
		initComponents();
	}

	public ConfigToShowDBPanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public ConfigToShowDBPanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowDBPanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public ConfigToShowDBPanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle(DBSAResourceBundle.res.getString("title.config.to.show.dblp.db.form"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getConfigJPanel(), new Constraints(new Bilateral(5, 5, 314), new Leading(5, 112, 10, 10)));
		add(getCalendarJPanel(), new Constraints(new Bilateral(5, 5, 356), new Leading(121, 237, 10, 10)));
		add(getActionJPanel(), new Constraints(new Bilateral(5, 5, 268), new Trailing(12, 68, 10, 364)));
		add(getSearchByDateJCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(366, 84, 88)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JCheckBox getSearchByDateJCheckBox() {
		if (searchByDateJCheckBox == null) {
			searchByDateJCheckBox = new JCheckBox();
			searchByDateJCheckBox.setText(DBSAResourceBundle.res.getString("show.by.date"));
		}
		return searchByDateJCheckBox;
	}

	private JPanel getCalendarJPanel() {
		if (calendarJPanel == null) {
			calendarJPanel = new JPanel();
			calendarJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("calendar"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
					12), new Color(51, 51, 51)));
			calendarJPanel.setLayout(new FlowLayout());
			
			MyDateListener dateListener = new MyDateListener();
			
			Border etchedBorder =
				BorderFactory.createEtchedBorder();
		    Border emptyBorder =
				BorderFactory.createEmptyBorder(5, 5, 5, 5);
			    
		    Border compoundBorder =
					BorderFactory.createCompoundBorder(etchedBorder, emptyBorder);
			 
			JCalendar calendar = new JCalendar(JCalendar.DISPLAY_DATE, false);
		    calendar.addDateListener(dateListener);
		    calendar.setBorder(compoundBorder);
		    
		    calendarJPanel.add(calendar);
		    		    
		}
		return calendarJPanel;
	}

	private JPanel getActionJPanel() {
		if (actionJPanel == null) {
			actionJPanel = new JPanel();
			actionJPanel.setBorder(BorderFactory.createTitledBorder(null, "-", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
					12), new Color(51, 51, 51)));
			actionJPanel.setLayout(new GroupLayout());
			actionJPanel.add(getOkJButton(), new Constraints(new Leading(135, 79, 10, 10), new Leading(0, 12, 12)));
		}
		return actionJPanel;
	}

	private JButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setText(DBSAResourceBundle.res.getString("ok"));
			okJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					okJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					setSizeResultNumber(Integer.parseInt(sizeJSpinner.getValue().toString()));
					setBeginResultNumber(Integer.parseInt(beginNumJSpinner.getValue().toString()));
					
					if(searchByDateJCheckBox.isSelected()){						
						DBSAApplication.databaseManagementPanel.ShowDBLPDatabaseByDate(MyDateListener.getDateSelected());
					}else{
						DBSAApplication.databaseManagementPanel.ShowDBLPDatabase(getSizeResultNumber(), getBeginResultNumber());
						
					}
					dispose();
					okJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			});
		}
		return okJButton;
	}

	private JPanel getConfigJPanel() {
		if (configJPanel == null) {
			configJPanel = new JPanel();
			configJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("config.to.show"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			configJPanel.setLayout(new GroupLayout());
			configJPanel.add(getSizeJSpinner(), new Constraints(new Trailing(12, 131, 10, 10), new Leading(0, 30, 12, 12)));
			configJPanel.add(getBeginNumJSpinner(), new Constraints(new Trailing(12, 131, 108, 139), new Leading(48, 30, 12, 12)));
			configJPanel.add(getBeginJLabel(), new Constraints(new Leading(12, 109, 57, 161), new Leading(58, 20, 12, 12)));
			configJPanel.add(getSizeJLabel(), new Constraints(new Leading(12, 109, 57, 161), new Leading(10, 20, 12, 12)));
			configJPanel.add(getCalendarJPanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(90, 256, 10, 10)));
		}
		return configJPanel;
	}

	private JLabel getSizeJLabel() {
		if (sizeJLabel == null) {
			sizeJLabel = new JLabel();
			sizeJLabel.setText(DBSAResourceBundle.res.getString("number.display"));
		}
		return sizeJLabel;
	}

	private JLabel getBeginJLabel() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText(DBSAResourceBundle.res.getString("begin.number"));
		}
		return jLabel1;
	}

	private JSpinner getBeginNumJSpinner() {
		if (beginNumJSpinner == null) {
			beginNumJSpinner = new JSpinner();
			beginNumJSpinner.setModel(new SpinnerNumberModel(1, 1, MAX_RESULT, 1));
		}
		return beginNumJSpinner;
	}

	private JSpinner getSizeJSpinner() {
		if (sizeJSpinner == null) {
			sizeJSpinner = new JSpinner();
			sizeJSpinner.setModel(new SpinnerNumberModel(100, 1, MAX_RESULT, 1));
		}
		return sizeJSpinner;
	}
		
	public int getSizeResultNumber(){
		return sizeResultNumber;
	}
	
	public void setSizeResultNumber(int value){
		sizeResultNumber = value;
	}
	
	public int getBeginResultNumber(){
		return beginResultNumber;
	}
	
	public void setBeginResultNumber(int value){
		beginResultNumber = value;
	}
	

}
