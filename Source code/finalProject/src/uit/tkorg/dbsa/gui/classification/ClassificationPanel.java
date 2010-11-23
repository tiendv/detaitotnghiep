package uit.tkorg.dbsa.gui.classification;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import uit.tkorg.dbsa.properties.files.FileLoadder;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ClassificationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel jLabel0;
	private JPanel jPanel1;
	private JButton jButton0;

	public ClassificationPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(440, 251);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
			jButton0.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> patternName = new ArrayList<String>();
					patternName = FileLoadder.loadTextFile("src\\uit\\tkorg\\dbsa\\properties\\files\\DBSA_Define_Pattern");
					
					String temp = "startACMUrl";
					for(int i = 0; i < patternName.size(); i++){
						if(patternName.get(i).substring(0, temp.length()).equalsIgnoreCase(temp))
							System.out.println(patternName.get(i).substring(temp.length() + 1));
					}
				}
				
			});
		}
		return jButton0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder(null, "Classification", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Leading(81, 100, 10, 10), new Leading(57, 100, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(213, 10, 10), new Leading(76, 10, 10)));
		}
		return jPanel0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createTitledBorder("Border Title"));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJLabel0(), new Constraints(new Leading(20, 10, 10), new Leading(26, 10, 10)));
		}
		return jPanel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

}
