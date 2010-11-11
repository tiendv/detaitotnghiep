package uit.tkorg.dbsa.gui.fetcher;
/**
 *@author CuongNP 
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import uit.tkorg.dbsa.actions.fetchers.IEEEXploreFetcherAction;
import uit.tkorg.dbsa.gui.main.DBSAApplication;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel fetcherJPanel;
	private JLabel keywordJLabel;
	private JTextField keywordJTextField;
	private JPanel inputJPanel;
	private JLabel fetchFromJLabel;
	private JCheckBox fetchFromACMCheckBox;
	private JLabel maxResultLabel;
	private JCheckBox ieeexploreDLCheckBox;
	private JSpinner acmJSpinner;
	private JSpinner ieeexploreJSpinner1;
	private JCheckBox citeseerDLCheckBox;
	private JSpinner citeseerJSpinner2;
	private JPanel chooseJPanel;
	private JCheckBox scienceDirectDLCheckBox;
	private JSpinner scienceDirectJSpinner;
	private JProgressBar acmJProgressBar;
	private JProgressBar citeseerJProgressBar;
	private JProgressBar ieeeploreJProgressBar;
	private JProgressBar scienceDirectJProgressBar;
	private JButton closeJButton;
	private JButton showResultJButton;
	private JButton fetcherJButton;
	private JPanel actionsJPanel;
	private JLabel fetcherStatusJLabel;
	
	private static int acmResultNumber;
	private static int ieeeResultNumber;
	private static int citeResultNumber;
	
	private boolean isShowResult = false;

	private String tooltipText = null;
	
	private boolean fetcherBoolean = false;
	
	private static int acmProgressPer = 0;
	
	public FetcherPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getFetcherJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(668, 407);
	}

	private JLabel getFetcherStatusJLabel() {
		if (fetcherStatusJLabel == null) {
			fetcherStatusJLabel = new JLabel();
			fetcherStatusJLabel.setText("Fetcher status");
		}
		return fetcherStatusJLabel;
	}

	private JPanel getFetcherJPanel() {
		if (fetcherJPanel == null) {
			fetcherJPanel = new JPanel();
			fetcherJPanel.setBorder(BorderFactory.createTitledBorder("Fetcher"));
			fetcherJPanel.setLayout(new GroupLayout());
			fetcherJPanel.add(getInputJPanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 61, 10, 10)));
			fetcherJPanel.add(getChooseJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(67, 74, 198)));
			fetcherJPanel.add(getActionsJPanel(), new Constraints(new Bilateral(0, 0, 513), new Trailing(0, 68, 278, 278)));
		}
		return fetcherJPanel;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 108, 383, 383), new Leading(2, 12, 12)));
			actionsJPanel.add(getShowResultJButton(), new Constraints(new Trailing(138, 241, 271), new Leading(2, 12, 12)));
			actionsJPanel.add(getFetcherJButton(), new Constraints(new Trailing(265, 107, 12, 12), new Leading(2, 12, 12)));
		}
		return actionsJPanel;
	}
	
	
	private JButton getFetcherJButton() {
		if (fetcherJButton == null) {
			fetcherJButton = new JButton();
			fetcherJButton.setText("Fetcher");
			
			fetcherJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					
					if(keywordJTextField.getText().replaceAll(" ", "").equals("")){
						JOptionPane.showMessageDialog(null, "Input keyword before press Fetch!");
					}
					else if(keywordJTextField.getText() != ""){
						if(fetchFromACMCheckBox.isSelected() == true) {
							fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							fetcherBoolean = true;
							final String acmQuery = keywordJTextField.getText();
						
						Thread acmThread = new Thread (new Runnable(){
							@Override
							public void run() {
								{
									uit.tkorg.dbsa.cores.fetchers.ACMFetcher.shouldContinue = true;
									setAcmResultNumber(Integer.parseInt(acmJSpinner.getValue().toString()));
									acmJProgressBar.setIndeterminate(true);
									acmJProgressBar.setStringPainted(true);
									acmJProgressBar.setString("Downloading Data from ACM Portal ...");
									try {
										ACMFetcher(acmQuery);
										acmJProgressBar.setIndeterminate(false);
										acmJProgressBar.setString("Complete");	
										fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										showResultJButton.setEnabled(true);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}});
						
							acmThread.start();
							acmThread.interrupt();
						}
						if(citeseerDLCheckBox.isSelected() == true){
							fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							
							fetcherBoolean = true;
							
							final String citeseerQuery = keywordJTextField.getText();
							Thread citeseerThread = new Thread (new Runnable(){
								@Override
								public void run() {
									{
										setCiteResultNumber(Integer.parseInt(citeseerJSpinner2.getValue().toString()));
										citeseerJProgressBar.setIndeterminate(true);
										citeseerJProgressBar.setStringPainted(true);
										citeseerJProgressBar.setString(" Downloading Data from Citeseer ...");
										CiteSeeXFetcher(citeseerQuery);	
										fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										citeseerJProgressBar.setIndeterminate(false);
										citeseerJProgressBar.setString("Complete");
										showResultJButton.setEnabled(true);
										
									}
								}});
							citeseerThread.start();	
							citeseerThread.interrupt();
						}
						
						if(ieeexploreDLCheckBox.isSelected() == true){
							fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							fetcherBoolean = true;
							final String ieeeQuery = keywordJTextField.getText();
							Thread ieeeThread = new Thread (new Runnable(){
								@Override
								public void run() {
									{
										setIeeeResultNumber(Integer.parseInt(ieeexploreJSpinner1.getValue().toString()));
										ieeeploreJProgressBar.setIndeterminate(true);
										ieeeploreJProgressBar.setStringPainted(true);
										ieeeploreJProgressBar.setString("Downloading Data from IEEEXPlore ...");
										try {
											IEEExploreFetch(ieeeQuery);
											ieeeploreJProgressBar.setIndeterminate(false);
											ieeeploreJProgressBar.setString("Complete");
											showResultJButton.setEnabled(true);
											fetcherJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										} catch (IOException t) {
			
											t.printStackTrace();
										}
									}
								}});
								ieeeThread.start();
								ieeeThread.interrupt();
						}	
						
						if(!fetcherBoolean){
							JOptionPane.showMessageDialog(null, "Ban chua chon thu vien so.");
						}
						/*
						 * reset application Jpanel
						 */
						keywordJTextField.setText("");
						fetcherBoolean = false;
					}
				}
				
			});
			
		}
		return fetcherJButton;
	}
			
	private JButton getShowResultJButton() {
		if (showResultJButton == null) {
			showResultJButton = new JButton();
			showResultJButton.setText("Show results");
			showResultJButton.isDefaultButton();
			showResultJButton.setEnabled(false);
			showResultJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				}
				
			});
		}
		return showResultJButton;
	}
	
	private void ACMFetcher(String keyword) throws IOException{
		uit.tkorg.dbsa.actions.fetchers.ACMFetcherAction.Fetcher(keyword);
	}

	private void IEEEXploreFethcer( String keyword) throws IOException {
		uit.tkorg.dbsa.actions.fetchers.IEEEXploreFetcherAction.Fetcher(keyword);
		
	}
	private void JSTORFetcher ( String keyword) throws IOException {
		uit.tkorg.dbsa.actions.fetchers.JSTORFetcherAction.Fetcher(keyword);
	}
	private void CiteSeeXFetcher ( String keyword) {
		
		uit.tkorg.dbsa.actions.fetchers.CiteSeerXFetcherAction.Fetcher(keyword);
	}

	private void IEEExploreFetch(String keyword) throws IOException{
		IEEEXploreFetcherAction.Fetcher(keyword);
	}
	
	public void setIsShowResult(boolean isShowResult){
		this.isShowResult = isShowResult;
	}
	
	public boolean getIsShowResult(){
		return isShowResult;
	}
	
	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText("Close");
			closeJButton.setAlignmentX(0.5f);
			
			closeJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DBSAApplication.dbsaJFrame.dispose();
					
				}
				
			});
		}
		return closeJButton;
	}

	private JProgressBar getScienceDirectJProgressBar() {
		if (scienceDirectJProgressBar == null) {
			scienceDirectJProgressBar = new JProgressBar();
			scienceDirectJProgressBar.setStringPainted(true);
			scienceDirectJProgressBar.setString("Science Direct Digital");
		}
		return scienceDirectJProgressBar;
	}

	private JProgressBar getIeeeploreJProgressBar() {
		if (ieeeploreJProgressBar == null) {
			ieeeploreJProgressBar = new JProgressBar();
			ieeeploreJProgressBar.setStringPainted(true);
			ieeeploreJProgressBar.setString("IEEEXplore Digital");
			
		}
		return ieeeploreJProgressBar;
	}

	public void setIeeeProgressBar(int value){
		ieeeploreJProgressBar.setValue(value);
	}
	
	public int getIeeeProgressBar(){
		return ieeeploreJProgressBar.getValue();
	}
	private JProgressBar getCiteseerJProgressBar() {
		if (citeseerJProgressBar == null) {
			citeseerJProgressBar = new JProgressBar();
			citeseerJProgressBar.setStringPainted(true);
			citeseerJProgressBar.setString("Citeseer Digital");
			
		}
		return citeseerJProgressBar;
	}

	private JProgressBar getAcmJProgressBar() {
		if (acmJProgressBar == null) {
			acmJProgressBar = new JProgressBar();
			acmJProgressBar.setStringPainted(true);
			acmJProgressBar.setString("ACM Portal Digital");
			
		}
		return acmJProgressBar;
	}
	
	public static void setAcmProgressBar(int value){
		acmProgressPer = value;
	}
	
	public static int getAcmProgressBar(){
		return acmProgressPer;
	}

	private JSpinner getScienceDirectJSpinner() {
		if (scienceDirectJSpinner == null) {
			scienceDirectJSpinner = new JSpinner();
			scienceDirectJSpinner.setModel(new SpinnerNumberModel(1, null, null, 1));
			scienceDirectJSpinner.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			scienceDirectJSpinner.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					if(Integer.parseInt(scienceDirectJSpinner.getValue().toString()) < 1){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						scienceDirectJSpinner.setValue(1);
					}else if(Integer.parseInt(scienceDirectJSpinner.getValue().toString()) > 50){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						scienceDirectJSpinner.setValue(50);
					}
					
				}
				
			});
		}
		return scienceDirectJSpinner;
	}

	private JCheckBox getScienceDirectDLCheckBox() {
		if (scienceDirectDLCheckBox == null) {
			scienceDirectDLCheckBox = new JCheckBox();
			scienceDirectDLCheckBox.setText("Fetch from ScienceDirect digital library");
		}
		return scienceDirectDLCheckBox;
	}

	private JPanel getChooseJPanel() {
		if (chooseJPanel == null) {
			chooseJPanel = new JPanel();
			chooseJPanel.setBorder(BorderFactory.createTitledBorder(null, "Choose DLs", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			chooseJPanel.setLayout(new GroupLayout());
			chooseJPanel.add(getIeeexploreDLCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(67, 8, 8)));
			chooseJPanel.add(getCiteseerDLCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(101, 8, 8)));
			chooseJPanel.add(getFetchFromJLabel(), new Constraints(new Leading(12, 89, 12, 12), new Leading(5, 21, 12, 12)));
			chooseJPanel.add(getFetchFromACMCheckBox(), new Constraints(new Leading(8, 212, 10, 10), new Leading(34, 8, 8)));
			chooseJPanel.add(getScienceDirectDLCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(137, 10, 10)));
			chooseJPanel.add(getMaxResultLabel(), new Constraints(new Leading(286, 67, 10, 10), new Leading(3, 24, 12, 12)));
			chooseJPanel.add(getAcmJSpinner(), new Constraints(new Leading(293, 43, 10, 10), new Leading(38, 22, 12, 12)));
			chooseJPanel.add(getIeeexploreJSpinner1(), new Constraints(new Leading(293, 43, 12, 12), new Leading(72, 12, 12)));
			chooseJPanel.add(getFetcherStatusJLabel(), new Constraints(new Leading(366, 106, 10, 10), new Leading(7, 12, 12)));
			chooseJPanel.add(getCiteseerJProgressBar(), new Constraints(new Bilateral(347, 12, 10), new Leading(104, 23, 12, 12)));
			chooseJPanel.add(getScienceDirectJProgressBar(), new Constraints(new Bilateral(347, 12, 10), new Leading(138, 23, 12, 12)));
			chooseJPanel.add(getIeeeploreJProgressBar(), new Constraints(new Bilateral(348, 12, 10), new Leading(71, 22, 12, 12)));
			chooseJPanel.add(getAcmJProgressBar(), new Constraints(new Bilateral(348, 13, 250), new Leading(38, 22, 12, 12)));
			chooseJPanel.add(getCiteseerJSpinner2(), new Constraints(new Leading(293, 42, 28, 166), new Leading(104, 22, 12, 12)));
			chooseJPanel.add(getScienceDirectJSpinner(), new Constraints(new Leading(293, 42, 28, 166), new Leading(138, 22, 12, 12)));
		}
		return chooseJPanel;
	}

	private JSpinner getCiteseerJSpinner2() {
		if (citeseerJSpinner2 == null) {
			citeseerJSpinner2 = new JSpinner();
			citeseerJSpinner2.setModel(new SpinnerNumberModel(1, null, null, 1));
			citeseerJSpinner2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			citeseerJSpinner2.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					if(Integer.parseInt(citeseerJSpinner2.getValue().toString()) < 1){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						citeseerJSpinner2.setValue(1);
					}else if(Integer.parseInt(citeseerJSpinner2.getValue().toString()) > 50){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						citeseerJSpinner2.setValue(50);
					}
				}
				
			});
		}
		return citeseerJSpinner2;
	}

	private JCheckBox getCiteseerDLCheckBox() {
		if (citeseerDLCheckBox == null) {
			citeseerDLCheckBox = new JCheckBox();
			citeseerDLCheckBox.setText("Fetch from Citeseer digital library");
		}
		return citeseerDLCheckBox;
	}

	private JSpinner getIeeexploreJSpinner1() {
		if (ieeexploreJSpinner1 == null) {
			ieeexploreJSpinner1 = new JSpinner();
			ieeexploreJSpinner1.setModel(new SpinnerNumberModel(1, null, null, 1));
			ieeexploreJSpinner1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
			ieeexploreJSpinner1.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					if(Integer.parseInt(ieeexploreJSpinner1.getValue().toString()) < 1){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						ieeexploreJSpinner1.setValue(1);
					}else if(Integer.parseInt(ieeexploreJSpinner1.getValue().toString()) > 50){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						ieeexploreJSpinner1.setValue(50);
					}
					
				}
				
			});
		}
		return ieeexploreJSpinner1;
	}

	private JSpinner getAcmJSpinner() {
		if (acmJSpinner == null) {
			acmJSpinner = new JSpinner();
			acmJSpinner.setModel(new SpinnerNumberModel(1, null, null, 1));
			acmJSpinner.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
			acmJSpinner.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					if(Integer.parseInt(acmJSpinner.getValue().toString()) < 1){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						acmJSpinner.setValue(1);
					}else if(Integer.parseInt(acmJSpinner.getValue().toString()) > 50){
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						acmJSpinner.setValue(50);
					}
					
				}
				
			});
		}
		return acmJSpinner;
	}

	private JCheckBox getIeeexploreDLCheckBox() {
		if (ieeexploreDLCheckBox == null) {
			ieeexploreDLCheckBox = new JCheckBox();
			ieeexploreDLCheckBox.setSelected(true);
			ieeexploreDLCheckBox.setText("Fetch from IEEExplore digital libary");
		}
		return ieeexploreDLCheckBox;
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
			fetchFromACMCheckBox.setText("Fetch from ACM digital library");
		}
		return fetchFromACMCheckBox;
	}

	private JLabel getFetchFromJLabel() {
		if (fetchFromJLabel == null) {
			fetchFromJLabel = new JLabel();
			fetchFromJLabel.setText("Fetch from:");
		}
		return fetchFromJLabel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			inputJPanel.setBorder(BorderFactory.createTitledBorder("Input"));
			inputJPanel.setAlignmentX(0.5f);
			inputJPanel.setAlignmentY(0.5f);
			inputJPanel.setLayout(new GroupLayout());
			inputJPanel.add(getKeywordJTextField(), new Constraints(new Bilateral(113, 12, 4), new Leading(0, 28, 12, 12)));
			inputJPanel.add(getKeywordJLabel(), new Constraints(new Leading(0, 108, 10, 10), new Leading(0, 28, 12, 12)));
		}
		return inputJPanel;
	}

	private JTextField getKeywordJTextField() {
		if (keywordJTextField == null) {
			keywordJTextField = new JTextField();
			keywordJTextField.setToolTipText("Nguyeen van a");
			keywordJTextField.addMouseListener(new MouseAdapter() {
	
				public void mousePressed(MouseEvent event) {
					keywordJTextFieldMouseMousePressed(event);
				}
			});
		}
		return keywordJTextField;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setText("  Input keyword :");
			keywordJLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			keywordJLabel.setAlignmentX(0.5f);
		}
		return keywordJLabel;
	}

	public void setTooltip(String tooltipText){
		this.tooltipText = tooltipText;
	}
	
	public String getTooltip(){
		return tooltipText;
	}

	/*
	 * 
	 * 
	 */
	public static int getAcmResultNumber(){
		return acmResultNumber;
	}
	
	public static void setAcmResultNumber(int resultNumber){
		acmResultNumber = resultNumber;
	}
	
	public static int getIeeeResultNumber(){
		return ieeeResultNumber;
	}
	
	public static void setIeeeResultNumber(int resultNumber){
		ieeeResultNumber = resultNumber;
	}

	/**
	 * @param citeResultNumber the citeResultNumber to set
	 */
	public static void setCiteResultNumber(int citeResultNumber) {
		FetcherPanel.citeResultNumber = citeResultNumber;
	}

	/**
	 * @return the citeResultNumber
	 */
	public static int getCiteResultNumber() {
		return citeResultNumber;
	}

	private void keywordJTextFieldMouseMousePressed(MouseEvent event) {
		fetcherJButton.setEnabled(true);
	}
	
	
	
}
