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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import uit.tkorg.dbsa.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JPanel textContentJPanel;
	private int width = 430;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame dbsaJFrame;
	private static JButton closeJButton;
	private JTextArea aboutJTextContent;
	private JScrollPane contentJScrollPane;
	
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
		add(getcloseJButton(), new Constraints(new Leading(332, 10, 10), new Leading(289, 12, 12)));
		add(gettextContentJPanel(), new Constraints(new Leading(250, 228, 12, 12), new Leading(0, 277, 12, 12)));
		setSize(483, 340);
		setLocation(xLocation, yLocation);
		updateTextsOfComponents();
	}

	public static String updateTextsOfComponents(){
		String textContent = "";
		
		textContent += DBSAResourceBundle.res.getString("about.dbsa");
		textContent += DBSAResourceBundle.res.getString("version");
		textContent += DBSAResourceBundle.res.getString("copyright.tkorg");
		textContent += DBSAResourceBundle.res.getString("authors.and.email");
		textContent += DBSAResourceBundle.res.getString("authors.in.about");
		textContent += DBSAResourceBundle.res.getString("thank.you");

		closeJButton.setText(DBSAResourceBundle.res.getString("close"));
		
		return textContent;
	}
	
	private JScrollPane getcontentJScrollPane() {
		if (contentJScrollPane == null) {
			contentJScrollPane = new JScrollPane();
			contentJScrollPane.setViewportView(getAboutJTextContent());
		}
		return contentJScrollPane;
	}

	private JTextArea getAboutJTextContent() {
		if (aboutJTextContent == null) {
			aboutJTextContent = new JTextArea();
			aboutJTextContent.setEditable(false);
			aboutJTextContent.setText(updateTextsOfComponents());
		}
		return aboutJTextContent;
	}

	private JButton getcloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
		}
		return closeJButton;
	}

	private JPanel gettextContentJPanel() {
		if (textContentJPanel == null) {
			textContentJPanel = new JPanel();
			textContentJPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textContentJPanel.setLayout(new GroupLayout());
			textContentJPanel.add(getcontentJScrollPane(), new Constraints(new Bilateral(1, 0, 22), new Leading(1, 270, 10, 10)));
		}
		return textContentJPanel;
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
