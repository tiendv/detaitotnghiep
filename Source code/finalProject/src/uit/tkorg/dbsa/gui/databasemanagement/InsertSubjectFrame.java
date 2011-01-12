package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.actions.database.InsertSubject;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.Subject;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InsertSubjectFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton cancelJButton;
	private JButton insertJButton;
	private JPanel actionJPanel;
	private JTextField numberJTextField;
	private JTextField subjectIDJTextField;
	private JTextField subjectNameJTextField;
	private JLabel NoJLabel;
	private JLabel subjectNameJLabel;
	private JLabel subjectIDJLabel;
	private JPanel bodyJPanel;
	private int width = 440;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	
	public InsertSubjectFrame(){
		initComponents();
	}
	
	public InsertSubjectFrame(JFrame mainFrame) {
		super(mainFrame, true);
		dbsaJFrame =  mainFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width) / 2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height) / 2;
		initComponents();	
	}

	private void initComponents() {
		setResizable(false);
		setLayout(new GroupLayout());
		add(getActionJPanel(), new Constraints(new Bilateral(12, 12, 214), new Trailing(12, 147, 147)));
		add(getBodyJPanel(), new Constraints(new Bilateral(12, 12, 0), new Bilateral(12, 84, 10, 144)));
		setSize(320, 259);
		setLocation(xLocation, yLocation);
	}

	private JPanel getBodyJPanel() {
		if (bodyJPanel == null) {
			bodyJPanel = new JPanel();
			bodyJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("body"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
					12), new Color(51, 51, 51)));
			bodyJPanel.setLayout(new GroupLayout());
			bodyJPanel.add(getSubjectIDJTextField(), new Constraints(new Trailing(12, 170, 65, 108), new Leading(33, 22, 12, 12)));
			bodyJPanel.add(getSubjectNameJTextField(), new Constraints(new Trailing(12, 170, 65, 109), new Leading(67, 23, 12, 12)));
			bodyJPanel.add(getNoJLabel(), new Constraints(new Leading(2, 79, 10, 10), new Leading(0, 21, 12, 12)));
			bodyJPanel.add(getSubjectNameJLabel(), new Constraints(new Bilateral(2, 189, 41), new Leading(64, 26, 12, 12)));
			bodyJPanel.add(getSubjectIDJLabel(), new Constraints(new Leading(2, 84, 34, 200), new Leading(35, 19, 12, 12)));
			bodyJPanel.add(getNumberJTextField(), new Constraints(new Trailing(12, 171, 38, 99), new Leading(-1, 22, 12, 12)));
		}
		return bodyJPanel;
	}

	private JTextField getSubjectIDJTextField() {
		if (subjectIDJTextField == null) {
			subjectIDJTextField = new JTextField();
			subjectIDJTextField.setEditable(false);
		}
		return subjectIDJTextField;
	}

	private JTextField getNumberJTextField() {
		if (numberJTextField == null) {
			numberJTextField = new JTextField();
			numberJTextField.setEditable(false);
		}
		return numberJTextField;
	}

	private JPanel getActionJPanel() {
		if (actionJPanel == null) {
			actionJPanel = new JPanel();
			actionJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionJPanel.setLayout(new GroupLayout());
			actionJPanel.add(getCancelJButton(), new Constraints(new Trailing(12, 12, 12), new Leading(0, 12, 12)));
			actionJPanel.add(getInsertJButton(), new Constraints(new Trailing(111, 78, 10, 10), new Leading(0, 12, 12)));
		}
		return actionJPanel;
	}

	private JButton getInsertJButton() {
		if (insertJButton == null) {
			insertJButton = new JButton();
			insertJButton.setText(DBSAResourceBundle.res.getString("insert"));
			insertJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Subject sub =  new Subject();
					if(subjectNameJTextField.getText().replaceAll(" ", "").equals("")){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("insert.subject.name"));
					}else{sub.setSbj_name(
						subjectNameJTextField.getText());
						InsertSubject insertSub = new InsertSubject();
						insertSub.InsertSubjectOfPublication(sub);
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("insert.subject.is.successful"));
						DBSAApplication.databaseManagementPanel.InsertSubject(Integer.parseInt(numberJTextField.getText()) - 1 ,sub.getId(), subjectNameJTextField.getText());
						
						
						numberJTextField.setText((Integer.parseInt(numberJTextField.getText()) + 1) + "");
						subjectIDJTextField.setText((Integer.parseInt(subjectIDJTextField.getText()) + 1) + "");
						subjectNameJTextField.setText("");
						
						
					}
					
				}
				
			});
		}
		return insertJButton;
	}

	private JButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText(DBSAResourceBundle.res.getString("close"));
			cancelJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dispose();
				}
				
			});
		}
		return cancelJButton;
	}

	private JLabel getSubjectIDJLabel() {
		if (subjectIDJLabel == null) {
			subjectIDJLabel = new JLabel();
			subjectIDJLabel.setText(DBSAResourceBundle.res.getString("subject.id") + " : ");
		}
		return subjectIDJLabel;
	}

	private JLabel getSubjectNameJLabel() {
		if (subjectNameJLabel == null) {
			subjectNameJLabel = new JLabel();
			subjectNameJLabel.setText(DBSAResourceBundle.res.getString("subject.name") + " : ");
		}
		return subjectNameJLabel;
	}

	private JLabel getNoJLabel() {
		if (NoJLabel == null) {
			NoJLabel = new JLabel();
			NoJLabel.setText(DBSAResourceBundle.res.getString("no") + " : ");
		}
		return NoJLabel;
	}

	private JTextField getSubjectNameJTextField() {
		if (subjectNameJTextField == null) {
			subjectNameJTextField = new JTextField();
		}
		return subjectNameJTextField;
	}

	public void setTextfieldValue(int number, int subjectID){
		numberJTextField.setText(number + "");
		subjectIDJTextField.setText(subjectID + "");
	}
}
