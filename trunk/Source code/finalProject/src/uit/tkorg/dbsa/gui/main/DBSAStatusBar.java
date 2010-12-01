package uit.tkorg.dbsa.gui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAStatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JLabel statusJLabel;
	private JPanel statusJPanel;
	private JProgressBar dbsaProgressbar;
	private static JLabel dbsaProgressMessage;

	public DBSAStatusBar() {
		initComponents();
		setLoadingStatusBar(false);
		
	}

	private void initComponents() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(600, 50));
		setLayout(new GroupLayout());
		add(getStatusJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(643, 48);
	}

	private JLabel getDbsaProgressMessage() {
		if (dbsaProgressMessage == null) {
			dbsaProgressMessage = new JLabel();
			dbsaProgressMessage.setText("");
			
		}
		return dbsaProgressMessage;
	}

	public static void setDBSAProgressMessage(String text){
		dbsaProgressMessage.setText(text);
	}
	
	private JProgressBar dbsaProgressbar() {
		if (dbsaProgressbar == null) {
			dbsaProgressbar = new JProgressBar();
		}
		return dbsaProgressbar;
	}
	

	private JPanel getStatusJPanel() {
		if (statusJPanel == null) {
			statusJPanel = new JPanel();
			statusJPanel.setBorder(BorderFactory.createTitledBorder(null, "status", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			statusJPanel.setLayout(new GroupLayout());
			statusJPanel.add(getStatusJLabel(), new Constraints(new Leading(12, 85, 40, 508), new Leading(-4, 25, 12, 12)));
			statusJPanel.add(dbsaProgressbar(), new Constraints(new Bilateral(79, 12, 10), new Leading(-4, 21, 12, 12)));
			statusJPanel.add(getDbsaProgressMessage(), new Constraints(new Bilateral(12, 12, 113), new Leading(-6, 29, 12, 12)));
		}
		return statusJPanel;
	}

	public void setStatusJLabel(String dbsaStatus){
		statusJLabel.setText( DBSAResourceBundle.res.getString("status") + " : " + dbsaStatus );
	}
	
	private JLabel getStatusJLabel() {
		if (statusJLabel == null) {
			statusJLabel = new JLabel();
			this.setMessage(DBSAResourceBundle.res.getString("group.name"));
		}
		return statusJLabel;
	}
	

	@SuppressWarnings("static-access")
	public void setLoadingStatusBar(boolean isLoading) {
		if(isLoading) {
			//this.setMessage();
			this.dbsaProgressMessage.setText(DBSAResourceBundle.res.getString("loading") + " : ");
			this.dbsaProgressbar.setIndeterminate(true);
			this.dbsaProgressbar.setVisible(true);
		}else {
			this.setMessage((""));
			this.setMessage("");
			this.dbsaProgressbar.setIndeterminate(false);
			this.dbsaProgressbar.setVisible(false);
			this.dbsaProgressMessage.setText(DBSAResourceBundle.res.getString("group.name"));
		}
	}
	/**
	 * 
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		statusJLabel.setText(" " + message);
	}

}
