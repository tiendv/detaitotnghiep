package uit.tkorg.dbsa.gui.main;

import java.awt.Color;
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
	private JLabel statusJLabel;
	private JPanel statusJPanel;
	private JProgressBar dbsaProgressbar;
	private JLabel dbsaProgressMessage;

	public DBSAStatusBar() {
		initComponents();
		setLoadingStatusBar(false);
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getStatusJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(486, 114);
	}

	private JLabel getDbsaProgressMessage() {
		if (dbsaProgressMessage == null) {
			dbsaProgressMessage = new JLabel();
			dbsaProgressMessage.setText("");
		}
		return dbsaProgressMessage;
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
			statusJPanel.setBorder(BorderFactory.createTitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			statusJPanel.setLayout(new GroupLayout());
			statusJPanel.add(dbsaProgressbar(), new Constraints(new Bilateral(97, 16, 285), new Leading(32, 18, 10, 10)));
			statusJPanel.add(getStatusJLabel(), new Constraints(new Leading(12, 91, 12, 12), new Bilateral(0, 12, 16)));
			statusJPanel.add(getDbsaProgressMessage(), new Constraints(new Bilateral(102, 12, 90), new Bilateral(0, 12, 16)));
		}
		return statusJPanel;
	}

	public void setStatusJLabel(String disaStatus){
		statusJLabel.setText("Status: " + disaStatus );
	}
	private JLabel getStatusJLabel() {
		if (statusJLabel == null) {
			statusJLabel = new JLabel();
			this.setMessage("Copyright");
		}
		return statusJLabel;
	}
	
	public void setLoadingStatusBar(boolean isLoading) {
		if(isLoading) {
			this.setMessage("Loading : ");
			this.dbsaProgressbar.setIndeterminate(true);
			this.dbsaProgressbar.setVisible(true);
		}else {
			this.setMessage(("copyright"));
			this.dbsaProgressbar.setIndeterminate(false);
			this.dbsaProgressbar.setVisible(false);
			this.dbsaProgressMessage.setText(" TKORG - Group ");
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
