package uit.tkorg.dbsa.gui.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import uit.tkorg.dbsa.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JPanel jPanel0;
	private JLabel applicationNameJLabel;
	private JLabel versionJLabel;
	private JLabel authorsJLabel;
	private int width = 430;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	private JLabel jLabel0;
	private JButton jButton1;
	
	public AboutDialog() {
		initComponents();
	}

	public AboutDialog(JFrame mainJFrame) {
		super();
		dbsaJFrame = mainJFrame;
		xLocation = dbsaJFrame.getX() + (dbsaJFrame.getWidth() - width)/2;
		yLocation = dbsaJFrame.getY() + (dbsaJFrame.getHeight() - height)/2;
		
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Leading(0, 249, 217, 217), new Leading(-2, 319, 10, 10)));
		add(getJPanel0(), new Constraints(new Leading(250, 199, 10, 10), new Leading(0, 277, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(309, 12, 12), new Leading(283, 12, 12)));
		setSize(451, 340);
		setLocation(xLocation, yLocation);
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Close");
			jButton1.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
		}
		return jButton1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Copyright © by TKORG group");
		}
		return jLabel0;
	}

	private JLabel getAuthorsJLabel() {
		if (authorsJLabel == null) {
			authorsJLabel = new JLabel();
			authorsJLabel.setText("Author: \nCuong Nguyen, \nTien Do.");
		}
		return authorsJLabel;
	}

	private JLabel getVersionJLabel() {
		if (versionJLabel == null) {
			versionJLabel = new JLabel();
			versionJLabel.setText("Version 1.0");
		}
		return versionJLabel;
	}

	private JLabel getApplicationNameJLabel() {
		if (applicationNameJLabel == null) {
			applicationNameJLabel = new JLabel();
			applicationNameJLabel.setText("DBSA - Database ");
		}
		return applicationNameJLabel;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getApplicationNameJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			jPanel0.add(getVersionJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(34, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(56, 12, 12)));
			jPanel0.add(getAuthorsJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(78, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ABOUT_IMAGE)));
			jLabel1.setText("");
		}
		return jLabel1;
	}

}
