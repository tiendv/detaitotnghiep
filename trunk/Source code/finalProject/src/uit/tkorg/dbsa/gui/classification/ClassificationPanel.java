package uit.tkorg.dbsa.gui.classification;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ClassificationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;

	public ClassificationPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(16, 10, 10), new Leading(66, 10, 10)));
		setSize(428, 272);
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

}
