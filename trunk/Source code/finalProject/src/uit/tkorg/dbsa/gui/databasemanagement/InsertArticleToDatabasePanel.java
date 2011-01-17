package uit.tkorg.dbsa.gui.databasemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.actions.database.CheckExist;
import uit.tkorg.dbsa.core.databasemanagement.InsertArtcileToDatabase;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.DBSAPublication;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InsertArticleToDatabasePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel inputJPanel;
	private static JPanel actionsJPanel;
	private static JButton closeJButton;
	private static JButton insertJButton;
	private static JButton clearJButton;
	private JTextField titleJTextField;
	private JTextField authorJTextField;
	private JTextField linkJTextField;
	private JTextField yearJTextField;
	private JTextField abstracJTextField;
	private JTextField publisherJTextField;
	private static JLabel authorJLabel;
	private static JLabel linkJLabel;
	private static JLabel yearJLabel;
	private static JLabel publisherJLabel;
	private static JLabel titleDesJLabel;
	private static JLabel authorDesJLabel;
	private static JLabel linkDesJLabel;
	private static JLabel yearDesJLabel;
	private static JLabel abstractDesJLabel;
	private static JLabel jLabel11;
	private InsertArtcileToDatabase insertArticle = new InsertArtcileToDatabase();
	private static JLabel titleJLabel;
	private static JLabel abstractLabel;

	public InsertArticleToDatabasePanel() {
		initComponents();
	}

	private void initComponents() {
		
		setLayout(new GroupLayout());
		add(getActionsJPanel(), new Constraints(new Bilateral(0, 0, 0), new Trailing(10, 66, 10, 205)));
		add(getInputJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 80, 10, 201)));
		setSize(988, 628);
		updateTextsOfComponents();
	}

	public void updateTextsOfComponents(){
		setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("insert.artilce.to.datatbase"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
				Font.BOLD, 12), new Color(51, 51, 51)));
		abstractLabel.setText(DBSAResourceBundle.res.getString("abstract"));
		titleJLabel.setText(DBSAResourceBundle.res.getString("title"));
		closeJButton.setText(DBSAResourceBundle.res.getString("close"));
		actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("actions"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		jLabel11.setText(DBSAResourceBundle.res.getString("input.publisher.description"));
		abstractDesJLabel.setText(DBSAResourceBundle.res.getString("input.abstract.description"));
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		yearDesJLabel.setText(DBSAResourceBundle.res.getString("input.year.description") + year );
		linkDesJLabel.setText(DBSAResourceBundle.res.getString("input.link.description"));
		authorDesJLabel.setText(DBSAResourceBundle.res.getString("input.author.description"));
		titleDesJLabel.setText(DBSAResourceBundle.res.getString("input.title.description"));
		inputJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("input.data"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
				Font.BOLD, 12), new Color(51, 51, 51)));
		insertJButton.setText(DBSAResourceBundle.res.getString("insert"));
		clearJButton.setText(DBSAResourceBundle.res.getString("clear"));
		authorJLabel.setText(DBSAResourceBundle.res.getString("authors"));
		linkJLabel.setText(DBSAResourceBundle.res.getString("link"));
		yearJLabel.setText(DBSAResourceBundle.res.getString("year"));

		publisherJLabel.setText(DBSAResourceBundle.res.getString("publisher"));
	}
	
	private JLabel getAbstractLabel() {
		if (abstractLabel == null) {
			abstractLabel = new JLabel();
			abstractLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			
		}
		return abstractLabel;
	}

	private JLabel gettitleJLabel() {
		if (titleJLabel == null) {
			titleJLabel = new JLabel();
			titleJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return titleJLabel;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
		}
		return closeJButton;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 81, 12, 12), new Leading(0, 12, 12)));
			actionsJPanel.add(getInsertJButton(), new Constraints(new Trailing(111, 81, 12, 12), new Leading(0, 12, 12)));
			actionsJPanel.add(getClearJButton(), new Constraints(new Trailing(210, 83, 10, 10), new Leading(0, 12, 12)));
		}
		return actionsJPanel;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return jLabel11;
	}

	private JLabel getAbstractDesJLabel() {
		if (abstractDesJLabel == null) {
			abstractDesJLabel = new JLabel();
			abstractDesJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return abstractDesJLabel;
	}

	private JLabel getYearDesJLabel() {
		if (yearDesJLabel == null) {
			yearDesJLabel = new JLabel();
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			yearDesJLabel.setText(DBSAResourceBundle.res.getString("input.year.description") + year );
			yearDesJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return yearDesJLabel;
	}

	private JLabel getLinkDesJLabel() {
		if (linkDesJLabel == null) {
			linkDesJLabel = new JLabel();
			linkDesJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return linkDesJLabel;
	}

	private JLabel getAuthorDesJLabel() {
		if (authorDesJLabel == null) {
			authorDesJLabel = new JLabel();
			authorDesJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return authorDesJLabel;
	}

	private JLabel getTitleDesJLabel() {
		if (titleDesJLabel == null) {
			titleDesJLabel = new JLabel();
			titleDesJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return titleDesJLabel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			
			inputJPanel.setLayout(new GroupLayout());
			inputJPanel.add(getTitleJTextField(), new Constraints(new Bilateral(122, 12, 4), new Leading(0, 26, 10, 10)));
			inputJPanel.add(getAuthorJTextField(), new Constraints(new Bilateral(123, 12, 833), new Leading(73, 26, 10, 10)));
			inputJPanel.add(getLinkJTextField(), new Constraints(new Bilateral(123, 12, 833), new Leading(146, 26, 10, 10)));
			inputJPanel.add(getAbstracJTextField(), new Constraints(new Bilateral(122, 11, 4), new Leading(294, 56, 10, 10)));
			inputJPanel.add(getPublisherJTextField(), new Constraints(new Bilateral(122, 12, 4), new Leading(400, 26, 10, 10)));
			inputJPanel.add(getAuthorJLabel(), new Constraints(new Leading(12, 103, 34, 863), new Leading(73, 31, 10, 10)));
			inputJPanel.add(getLinkJLabel(), new Constraints(new Leading(12, 103, 28, 28), new Leading(146, 30, 10, 10)));
			inputJPanel.add(getYearJLabel(), new Constraints(new Leading(12, 103, 28, 28), new Leading(215, 32, 10, 10)));
			inputJPanel.add(getPublisherJLabel(), new Constraints(new Leading(12, 103, 12, 12), new Leading(400, 30, 10, 10)));
			inputJPanel.add(getYearJTextField(), new Constraints(new Bilateral(122, 12, 4), new Leading(215, 29, 10, 10)));
			inputJPanel.add(gettitleJLabel(), new Constraints(new Leading(12, 103, 28, 28), new Leading(-2, 31, 12, 12)));
			inputJPanel.add(getAbstractLabel(), new Constraints(new Leading(12, 103, 27, 27), new Leading(292, 60, 12, 12)));
			inputJPanel.add(getYearDesJLabel(), new Constraints(new Leading(122, 650, 10, 10), new Leading(248, 26, 12, 12)));
			inputJPanel.add(getLinkDesJLabel(), new Constraints(new Leading(122, 696, 10, 10), new Leading(177, 26, 12, 12)));
			inputJPanel.add(getAuthorDesJLabel(), new Constraints(new Leading(122, 682, 10, 10), new Leading(105, 26, 12, 12)));
			inputJPanel.add(getTitleDesJLabel(), new Constraints(new Leading(122, 725, 10, 10), new Leading(32, 26, 12, 12)));
			inputJPanel.add(getAbstractDesJLabel(), new Constraints(new Leading(123, 653, 10, 10), new Leading(356, 26, 12, 12)));
			inputJPanel.add(getJLabel11(), new Constraints(new Leading(123, 735, 10, 10), new Leading(432, 26, 12, 12)));
		}
		return inputJPanel;
	}

	private JLabel getPublisherJLabel() {
		if (publisherJLabel == null) {
			publisherJLabel = new JLabel();
			publisherJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return publisherJLabel;
	}

	private JLabel getYearJLabel() {
		if (yearJLabel == null) {
			yearJLabel = new JLabel();
			yearJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return yearJLabel;
	}

	private JLabel getLinkJLabel() {
		if (linkJLabel == null) {
			linkJLabel = new JLabel();
			linkJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return linkJLabel;
	}

	private JLabel getAuthorJLabel() {
		if (authorJLabel == null) {
			authorJLabel = new JLabel();
			authorJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return authorJLabel;
	}
	
	private JTextField getPublisherJTextField() {
		if (publisherJTextField == null) {
			publisherJTextField = new JTextField();
			publisherJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return publisherJTextField;
	}

	private JTextField getAbstracJTextField() {
		if (abstracJTextField == null) {
			abstracJTextField = new JTextField();
			abstracJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return abstracJTextField;
	}

	private JTextField getYearJTextField() {
		if (yearJTextField == null) {
			yearJTextField = new JTextField();
			yearJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return yearJTextField;
	}

	private JTextField getLinkJTextField() {
		if (linkJTextField == null) {
			linkJTextField = new JTextField();
			linkJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return linkJTextField;
	}

	private JTextField getAuthorJTextField() {
		if (authorJTextField == null) {
			authorJTextField = new JTextField();
			authorJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return authorJTextField;
	}

	private JTextField getTitleJTextField() {
		if (titleJTextField == null) {
			titleJTextField = new JTextField();
			titleJTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return titleJTextField;
	}

	private JButton getClearJButton() {
		if (clearJButton == null) {
			clearJButton = new JButton();
			clearJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					titleJTextField.setText("");
					authorJTextField.setText("");
					linkJTextField.setText("");
					yearJTextField.setText("");
					abstracJTextField.setText("");
					publisherJTextField.setText("");
					
				}
				
			});
			
		}
		return clearJButton;
	}

	private JButton getInsertJButton() {
		if (insertJButton == null) {
			insertJButton = new JButton();
			insertJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					checkAndInsertToDatabase();
				}
				
			});
		}
		return insertJButton;
	}
	
	private void checkAndInsertToDatabase(){
		if(titleJTextField.getText().equals("") || authorJTextField.getText().equals("") || yearJTextField.getText().equals("") ){
			
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("requirement.input.article.info"));
		}else if(linkJTextField.getText().equals(null)){
			linkJTextField.setText("");
		
		}else if(abstracJTextField.getText().equals(null)){
			abstracJTextField.setText("");
			
		}else if(publisherJTextField.getText().equals(null)){
			publisherJTextField.setText("");
			
		}else{
		
			if(yearJTextField.getText().length() != 4){
				JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("requirement.input.year.again"));
			}else{
				try{
					
					int checkYear = Integer.parseInt(yearJTextField.getText().trim());
					Calendar cal = Calendar.getInstance();
					
					if(checkYear > 1899 && checkYear <= cal.get(Calendar.YEAR)){
						
						DBSAPublication dbsaPublication = new DBSAPublication();
						
						dbsaPublication.setTitle(titleJTextField.getText());
						dbsaPublication.setAuthors(authorJTextField.getText());
						dbsaPublication.setYear(Integer.parseInt(yearJTextField.getText()));
						
						boolean checkExist = false;
						checkExist = checkPublicationDuplicate(dbsaPublication);
						
						if(!checkExist){
							insertArticle.insertArtcile(titleJTextField.getText(), 
									authorJTextField.getText(), linkJTextField.getText(), 
									Integer.parseInt(yearJTextField.getText()), 
									abstracJTextField.getText(), publisherJTextField.getText());
							
						}else{
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("article.is.duplicated"));
						}
							
							titleJTextField.setText("");
							authorJTextField.setText("");
							linkJTextField.setText("");
							yearJTextField.setText("");
							abstracJTextField.setText("");
							publisherJTextField.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("requirement.input.year.again"));
					}
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("requirement.input.year.again"));
				}
			}
		}
	}
	
	/***
	 * 
	 * @param dbsaPublication
	 * @return
	 */
	private boolean checkPublicationDuplicate(DBSAPublication dbsaPublication){
		boolean check = false;
		
		CheckExist checkPubIsExist = new CheckExist();
		check = checkPubIsExist.CheckPublicationInDBSA(dbsaPublication);
		
		return check;
	}

}
