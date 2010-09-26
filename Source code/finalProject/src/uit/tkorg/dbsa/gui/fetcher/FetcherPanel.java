package uit.tkorg.dbsa.gui.fetcher;
/**
 *@author CuongNP 
 */

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel keywordJLabel;
	private JTextField keywordJTextField;
	private JButton fetcherJButton;
	private JPanel jPanel1;
	private JLabel jLabel2;
	private JCheckBox jCheckBox0;
	private JLabel jLabel3;
	private JCheckBox jCheckBox1;
	private JSpinner jSpinner0;
	private JSpinner jSpinner1;
	private JCheckBox jCheckBox2;
	private JSpinner jSpinner2;
	private JPanel jPanel2;

	public FetcherPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(428, 272);
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJLabel2(), new Constraints(new Leading(12, 62, 10, 10), new Leading(5, 21, 12, 12)));
			jPanel2.add(getJCheckBox0(), new Constraints(new Leading(8, 8, 8), new Leading(34, 8, 8)));
			jPanel2.add(getJLabel3(), new Constraints(new Leading(230, 67, 10, 10), new Leading(2, 24, 12, 12)));
			jPanel2.add(getJCheckBox1(), new Constraints(new Leading(8, 8, 8), new Leading(67, 8, 8)));
			jPanel2.add(getJSpinner0(), new Constraints(new Leading(230, 43, 10, 10), new Leading(37, 12, 12)));
			jPanel2.add(getJSpinner1(), new Constraints(new Leading(230, 43, 12, 12), new Leading(71, 12, 12)));
			jPanel2.add(getJCheckBox2(), new Constraints(new Leading(8, 8, 8), new Leading(101, 8, 8)));
			jPanel2.add(getJSpinner2(), new Constraints(new Leading(230, 42, 12, 12), new Leading(103, 22, 12, 12)));
		}
		return jPanel2;
	}

	private JSpinner getJSpinner2() {
		if (jSpinner2 == null) {
			jSpinner2 = new JSpinner();
			jSpinner2.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		}
		return jSpinner2;
	}

	private JCheckBox getJCheckBox2() {
		if (jCheckBox2 == null) {
			jCheckBox2 = new JCheckBox();
			jCheckBox2.setText("jCheckBox2");
		}
		return jCheckBox2;
	}

	private JSpinner getJSpinner1() {
		if (jSpinner1 == null) {
			jSpinner1 = new JSpinner();
			jSpinner1.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		}
		return jSpinner1;
	}

	private JSpinner getJSpinner0() {
		if (jSpinner0 == null) {
			jSpinner0 = new JSpinner();
			jSpinner0.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		}
		return jSpinner0;
	}

	private JCheckBox getJCheckBox1() {
		if (jCheckBox1 == null) {
			jCheckBox1 = new JCheckBox();
			jCheckBox1.setSelected(true);
			jCheckBox1.setText("jCheckBox1");
		}
		return jCheckBox1;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("jLabel3");
		}
		return jLabel3;
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setSelected(true);
			jCheckBox0.setText("jCheckBox0");
		}
		return jCheckBox0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("jLabel2");
		}
		return jLabel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getFetcherJButton(), new Constraints(new Trailing(4, 90, 10, 10), new Leading(0, 12, 12)));
			jPanel1.add(getKeywordJLabel(), new Constraints(new Leading(0, 78, 10, 10), new Leading(0, 28, 12, 12)));
			jPanel1.add(getKeywordJTextField(), new Constraints(new Bilateral(88, 112, 4), new Leading(0, 28, 12, 12)));
		}
		return jPanel1;
	}

	private JButton getFetcherJButton() {
		if (fetcherJButton == null) {
			fetcherJButton = new JButton();
			fetcherJButton.setText("jButton0");
		}
		return fetcherJButton;
	}

	private JTextField getKeywordJTextField() {
		if (keywordJTextField == null) {
			keywordJTextField = new JTextField();
		}
		return keywordJTextField;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setText("  Key word :");
			keywordJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return keywordJLabel;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 61, 10, 10)));
			jPanel0.add(getJPanel2(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(67, 0, 0)));
		}
		return jPanel0;
	}

}
