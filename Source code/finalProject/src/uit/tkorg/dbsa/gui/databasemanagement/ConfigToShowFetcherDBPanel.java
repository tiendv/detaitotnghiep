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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.freixas.jcalendar.JCalendar;

import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.properties.files.MyDateListener;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ConfigToShowFetcherDBPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel actionJPanel;
	private JButton okJButton;

	private int sizeResultNumber;
	private int beginResultNumber;
	
	private int width = 375 ;
	private int height = 365;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	
	private JPanel calendarJPanel;
	private JCheckBox searchByDateJCheckBox;
	
	
	public ConfigToShowFetcherDBPanel() {
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(JFrame mainJFrame) {
		super(mainJFrame, true);
		
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Dialog parent) {
		super(parent);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Window parent) {
		super(parent);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public ConfigToShowFetcherDBPanel(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle("title.config.to.show.dblp.db.form");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getCalendarJPanel(), new Constraints(new Bilateral(5, 5, 356), new Leading(8, 237, 10, 10)));
		add(getSearchByDateJCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(253, 84, 88)));
		add(getActionJPanel(), new Constraints(new Bilateral(5, 5, 234), new Leading(285, 68, 12, 12)));
		setSize( width, height);
		setLocation(xLocation, yLocation);
	}

	private JCheckBox getSearchByDateJCheckBox() {
		if (searchByDateJCheckBox == null) {
			searchByDateJCheckBox = new JCheckBox();
			searchByDateJCheckBox.setSelected(true);
			searchByDateJCheckBox.setText("show.by.date");
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
					if(searchByDateJCheckBox.isSelected()){
						if(MyDateListener.getDateSelected() != null){
							DBSAApplication.databaseManagementPanel.ShowFetcherDatabaseByDate(MyDateListener.getDateSelected());
						}else{
							JOptionPane.showMessageDialog(null, "Please, choose date in calendar");
						}
							
					}else{
						DBSAApplication.databaseManagementPanel.ShowFetcherDatabase();
						dispose();
					}
					okJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			});
		}
		return okJButton;
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
