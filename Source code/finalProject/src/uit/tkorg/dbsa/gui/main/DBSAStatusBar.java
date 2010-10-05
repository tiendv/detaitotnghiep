package uit.tkorg.dbsa.gui.main;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAStatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel statusJLabel;
	private JPanel statusJPanel;

	public DBSAStatusBar() {
		initComponents();
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getStatusJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(408, 104);
	}

	private JPanel getStatusJPanel() {
		if (statusJPanel == null) {
			statusJPanel = new JPanel();
			statusJPanel.setBorder(BorderFactory.createTitledBorder("|"));
			statusJPanel.setLayout(new GroupLayout());
			statusJPanel.add(getStatusJLabel(), new Constraints(new Bilateral(0, 0, 41), new Bilateral(4, 0, 16)));
			
		}
		return statusJPanel;
	}

	public void setStatusJLabel(String disaStatus){
		statusJLabel.setText("Status: " + disaStatus );
	}
	
	private JLabel getStatusJLabel() {
		if (statusJLabel == null) {
			statusJLabel = new JLabel("...");
		}
		return statusJLabel;
	}

}
