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
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
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
import uit.tkorg.dbsa.gui.main.DBSAStatusBar;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JTabbedPane dbsaTabFrame = null;
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
	private JProgressBar acmJProgressBar;
	private JProgressBar citeseerJProgressBar;
	private JProgressBar ieeeploreJProgressBar;
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
	
	private static DBSAStatusBar dbsaStatus = new DBSAStatusBar();
	
	public FetcherPanel(JTabbedPane dbsa) {
		initComponents();
		this.dbsaTabFrame = dbsa;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getFetcherJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		setSize(935, 477);
	}

	private JLabel getFetcherStatusJLabel() {
		if (fetcherStatusJLabel == null) {
			fetcherStatusJLabel = new JLabel();
			fetcherStatusJLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			fetcherStatusJLabel.setText("Fetcher status");
		}
		return fetcherStatusJLabel;
	}

	private JPanel getFetcherJPanel() {
		if (fetcherJPanel == null) {
			fetcherJPanel = new JPanel();
			fetcherJPanel.setBorder(BorderFactory.createTitledBorder(null, "Fetcher", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			fetcherJPanel.setLayout(new GroupLayout());
			fetcherJPanel.add(getInputJPanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 61, 10, 10)));
			fetcherJPanel.add(getActionsJPanel(), new Constraints(new Bilateral(0, 0, 513), new Trailing(0, 68, 278, 278)));
			fetcherJPanel.add(getChooseJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(67, 74, 198)));
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
					
					
					//boolean checkInternetConnecttion = DBSAApplication.isInternetReachable();
					if(!DBSAApplication.isInternetReachable()){
						JOptionPane.showMessageDialog(null, "Your system don't connect to the Internet. \nPlease check your internet!");
					}
					
					if(keywordJTextField.getText().replaceAll(" ", "").equals("")){
						JOptionPane.showMessageDialog(null, "Please input keyword before press Fetch button!");
					}
					else if(keywordJTextField.getText() != "" && DBSAApplication.isInternetReachable()){
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
						keywordJTextField.setEnabled(false);
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
					dbsaTabFrame.setSelectedIndex(1);
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

	private JPanel getChooseJPanel() {
		if (chooseJPanel == null) {
			chooseJPanel = new JPanel();
			chooseJPanel.setBorder(BorderFactory.createTitledBorder(null, "Choose DLs", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			chooseJPanel.setLayout(new GroupLayout());
			chooseJPanel.add(getIeeexploreJSpinner1(), new Constraints(new Leading(399, 48, 12, 12), new Leading(97, 27, 10, 10)));
			chooseJPanel.add(getFetchFromJLabel(), new Constraints(new Leading(23, 89, 10, 10), new Leading(12, 33, 12, 12)));
			chooseJPanel.add(getMaxResultLabel(), new Constraints(new Leading(389, 96, 10, 10), new Leading(12, 32, 12, 12)));
			chooseJPanel.add(getFetcherStatusJLabel(), new Constraints(new Trailing(289, 106, 12, 12), new Leading(12, 35, 12, 12)));
			chooseJPanel.add(getCiteseerJSpinner2(), new Constraints(new Leading(398, 50, 34, 407), new Leading(136, 26, 10, 10)));
			chooseJPanel.add(getAcmJSpinner(), new Constraints(new Leading(399, 48, 34, 406), new Leading(54, 28, 12, 12)));
			chooseJPanel.add(getFetchFromACMCheckBox(), new Constraints(new Leading(21, 294, 10, 10), new Leading(59, 23, 12, 12)));
			chooseJPanel.add(getIeeexploreDLCheckBox(), new Constraints(new Leading(20, 12, 12), new Leading(94, 28, 12, 12)));
			chooseJPanel.add(getCiteseerDLCheckBox(), new Constraints(new Leading(20, 34, 406), new Leading(134, 28, 12, 12)));
			chooseJPanel.add(getAcmJProgressBar(), new Constraints(new Bilateral(521, 12, 382), new Leading(57, 25, 12, 12)));
			chooseJPanel.add(getIeeeploreJProgressBar(), new Constraints(new Bilateral(521, 12, 382), new Leading(94, 28, 12, 12)));
			chooseJPanel.add(getCiteseerJProgressBar(), new Constraints(new Bilateral(520, 12, 383), new Leading(136, 26, 12, 12)));
		}
		return chooseJPanel;
	}

	private JSpinner getCiteseerJSpinner2() {
		if (citeseerJSpinner2 == null) {
			citeseerJSpinner2 = new JSpinner();
			citeseerJSpinner2.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (Integer.parseInt(citeseerJSpinner2.getValue().toString()) < 1) {
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						citeseerJSpinner2.setValue(1);
					} else if (Integer.parseInt(citeseerJSpinner2.getValue().toString()) > 50) {
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
			citeseerDLCheckBox.setFont(new Font("Times New Roman", Font.ITALIC, 18));
			citeseerDLCheckBox.setText("Fetch from Citeseer digital library");
			citeseerDLCheckBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return citeseerDLCheckBox;
	}

	private JSpinner getIeeexploreJSpinner1() {
		if (ieeexploreJSpinner1 == null) {
			ieeexploreJSpinner1 = new JSpinner();
			
			ieeexploreJSpinner1.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (Integer.parseInt(ieeexploreJSpinner1.getValue().toString()) < 1) {
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						ieeexploreJSpinner1.setValue(1);
					} else if (Integer.parseInt(ieeexploreJSpinner1.getValue().toString()) > 50) {
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
			
			acmJSpinner.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (Integer.parseInt(acmJSpinner.getValue().toString()) < 1) {
						JOptionPane.showMessageDialog(null, "Please input result number from 1 to 50! ");
						acmJSpinner.setValue(1);
					} else if (Integer.parseInt(acmJSpinner.getValue().toString()) > 50) {
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
			ieeexploreDLCheckBox.setFont(new Font("Times New Roman", Font.ITALIC, 18));
			ieeexploreDLCheckBox.setText("Fetch from IEEExplore digital libary");
			ieeexploreDLCheckBox.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return ieeexploreDLCheckBox;
	}

	private JLabel getMaxResultLabel() {
		if (maxResultLabel == null) {
			maxResultLabel = new JLabel();
			maxResultLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			maxResultLabel.setText("Max result:");
		}
		return maxResultLabel;
	}

	private JCheckBox getFetchFromACMCheckBox() {
		if (fetchFromACMCheckBox == null) {
			fetchFromACMCheckBox = new JCheckBox();
			fetchFromACMCheckBox.setFont(new Font("Times New Roman", Font.ITALIC, 18));
			fetchFromACMCheckBox.setText("Fetch from ACM digital library");
			fetchFromACMCheckBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return fetchFromACMCheckBox;
	}

	private JLabel getFetchFromJLabel() {
		if (fetchFromJLabel == null) {
			fetchFromJLabel = new JLabel();
			fetchFromJLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			fetchFromJLabel.setText("Fetch from:");
		}
		return fetchFromJLabel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			inputJPanel.setBorder(BorderFactory.createTitledBorder(" "));
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
			keywordJTextField.addMouseListener(new MouseAdapter() {
	
				public void mousePressed(MouseEvent event) {
					keywordJTextFieldMouseMousePressed(event);
				}
			});
			keywordJTextField.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@SuppressWarnings("static-access")
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					dbsaStatus.setDBSAProgressMessage("Input keyword to fetcher");
				}

				@SuppressWarnings("static-access")
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					dbsaStatus.setDBSAProgressMessage("Copyright: TKORG - Text Knowlege Organization Research Group.");
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				//@SuppressWarnings("static-access")
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return keywordJTextField;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setBackground(Color.white);
			keywordJLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			keywordJLabel.setText("  Input keyword :");
			keywordJLabel.setBorder(new LineBorder(Color.lightGray, 1, false));
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
