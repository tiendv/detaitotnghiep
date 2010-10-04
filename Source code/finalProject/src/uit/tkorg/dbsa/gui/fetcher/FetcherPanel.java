package uit.tkorg.dbsa.gui.fetcher;
/**
 *@author CuongNP 
 */

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	private JPanel jPanel1;
	private JLabel jLabel2;
	private JCheckBox fetchFromACMCheckBox;
	private JLabel maxResultLabel;
	private JCheckBox jCheckBox1;
	private JSpinner jSpinner0;
	private JSpinner jSpinner1;
	private JCheckBox jCheckBox2;
	private JSpinner jSpinner2;
	private JPanel jPanel2;
	private JCheckBox jCheckBox0;
	private JSpinner jSpinner3;
	private JProgressBar jProgressBar0;
	private JProgressBar jProgressBar2;
	private JProgressBar jProgressBar1;
	private JProgressBar jProgressBar3;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel jPanel3;
	private JLabel jLabel0;

	private String tooltipText = null;
	public FetcherPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(631, 398);
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Fetcher status");
		}
		return jLabel0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 61, 10, 10)));
			jPanel0.add(getJPanel2(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(67, 74, 198)));
			jPanel0.add(getJPanel3(), new Constraints(new Bilateral(0, 0, 513), new Trailing(0, 68, 278, 278)));
		}
		return jPanel0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createTitledBorder("Actions"));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJButton0(), new Constraints(new Trailing(12, 108, 383, 383), new Leading(2, 12, 12)));
			jPanel3.add(getJButton1(), new Constraints(new Trailing(138, 241, 271), new Leading(2, 12, 12)));
			jPanel3.add(getJButton2(), new Constraints(new Trailing(265, 107, 12, 12), new Leading(2, 12, 12)));
		}
		return jPanel3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Fetcher");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Show results");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Close");
		}
		return jButton0;
	}

	private JProgressBar getJProgressBar3() {
		if (jProgressBar3 == null) {
			jProgressBar3 = new JProgressBar();
		}
		return jProgressBar3;
	}

	private JProgressBar getJProgressBar1() {
		if (jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
		}
		return jProgressBar1;
	}

	private JProgressBar getJProgressBar2() {
		if (jProgressBar2 == null) {
			jProgressBar2 = new JProgressBar();
		}
		return jProgressBar2;
	}

	private JProgressBar getJProgressBar0() {
		if (jProgressBar0 == null) {
			jProgressBar0 = new JProgressBar();
		}
		return jProgressBar0;
	}

	private JSpinner getJSpinner3() {
		if (jSpinner3 == null) {
			jSpinner3 = new JSpinner();
			jSpinner3.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		}
		return jSpinner3;
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setText("Fetch from ScienceDirect digital library");
		}
		return jCheckBox0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createTitledBorder("Choose DLs"));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJCheckBox1(), new Constraints(new Leading(8, 8, 8), new Leading(67, 8, 8)));
			jPanel2.add(getJCheckBox2(), new Constraints(new Leading(8, 8, 8), new Leading(101, 8, 8)));
			jPanel2.add(getJLabel2(), new Constraints(new Leading(12, 89, 12, 12), new Leading(5, 21, 12, 12)));
			jPanel2.add(getFetchFromACMCheckBox(), new Constraints(new Leading(8, 212, 10, 10), new Leading(34, 8, 8)));
			jPanel2.add(getJCheckBox0(), new Constraints(new Leading(8, 8, 8), new Leading(137, 10, 10)));
			jPanel2.add(getMaxResultLabel(), new Constraints(new Leading(286, 67, 10, 10), new Leading(3, 24, 12, 12)));
			jPanel2.add(getJSpinner0(), new Constraints(new Leading(293, 43, 10, 10), new Leading(38, 22, 12, 12)));
			jPanel2.add(getJSpinner1(), new Constraints(new Leading(293, 43, 12, 12), new Leading(72, 12, 12)));
			jPanel2.add(getJSpinner2(), new Constraints(new Leading(293, 12, 12), new Leading(104, 22, 12, 12)));
			jPanel2.add(getJSpinner3(), new Constraints(new Leading(293, 12, 12), new Leading(138, 22, 12, 12)));
			jPanel2.add(getJProgressBar0(), new Constraints(new Bilateral(348, 9, 10), new Leading(41, 12, 12)));
			jPanel2.add(getJProgressBar1(), new Constraints(new Bilateral(348, 12, 10), new Leading(78, 12, 12)));
			jPanel2.add(getJProgressBar2(), new Constraints(new Bilateral(347, 12, 10), new Leading(110, 12, 12)));
			jPanel2.add(getJProgressBar3(), new Constraints(new Bilateral(347, 12, 10), new Leading(144, 12, 12)));
			jPanel2.add(getJLabel0(), new Constraints(new Leading(366, 106, 10, 10), new Leading(7, 12, 12)));
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
			jCheckBox2.setText("Fetch from Citeseer digital library");
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
			jCheckBox1.setText("Fetch from IEEExplore digital libary");
		}
		return jCheckBox1;
	}

	private JLabel getMaxResultLabel() {
		if (maxResultLabel == null) {
			maxResultLabel = new JLabel();
			maxResultLabel.setText("Max result:");
		}
		return maxResultLabel;
	}

	private JCheckBox getFetchFromACMCheckBox() {
		if (fetchFromACMCheckBox == null) {
			fetchFromACMCheckBox = new JCheckBox();
			fetchFromACMCheckBox.setSelected(true);
			fetchFromACMCheckBox.setText("Fetch from ACM digital library");
		}
		return fetchFromACMCheckBox;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Fetch from:");
		}
		return jLabel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createTitledBorder("Input"));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getKeywordJTextField(), new Constraints(new Bilateral(113, 12, 4), new Leading(0, 28, 12, 12)));
			jPanel1.add(getKeywordJLabel(), new Constraints(new Leading(0, 108, 10, 10), new Leading(0, 28, 12, 12)));
		}
		return jPanel1;
	}

	private JTextField getKeywordJTextField() {
		if (keywordJTextField == null) {
			keywordJTextField = new JTextField();
			keywordJTextField.setToolTipText("Nguyeen van a");
			setTooltip(getToolTipText());
		}
		return keywordJTextField;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setText("  Input keyword :");
			keywordJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return keywordJLabel;
	}
	
	public void setTooltip(String tooltipText){
		this.tooltipText = tooltipText;
	}
	
	public String getTooltip(){
		return tooltipText;
	}

}
