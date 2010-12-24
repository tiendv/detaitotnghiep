package uit.tkorg.dbsa.gui.fetcher;
/**
 *@author CuongNP 
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
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

import uit.tkorg.dbsa.actions.database.LoadSubject;
import uit.tkorg.dbsa.actions.fetchers.IEEEXploreFetcherAction;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.gui.main.DBSAStatusBar;
import uit.tkorg.dbsa.model.Subject;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JTabbedPane dbsaTabFrame = null;
	private JPanel fetcherJPanel;
	private JLabel keywordJLabel;
	private JPanel inputJPanel;
	private JLabel fetchFromJLabel;
	private JCheckBox fetchFromACMCheckBox;
	private JLabel maxResultLabel;
	private JCheckBox ieeexploreDLCheckBox;
	private JSpinner acmJSpinner;
	private final int ACM_MAX_RESULT = 100;
	private JSpinner ieeexploreJSpinner;
	private final int IEEE_MAX_RESULT = 1000;
	private JCheckBox citeseerDLCheckBox;
	private JSpinner citeseerJSpinner;
	private final int CITESEER_MAX_RESULT = 100;
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
	
	private JRadioButton searchByAllRadioButton;
	public static boolean checkSearchByAll;
	private JRadioButton searchBySubjectJRadioButton;
	public static boolean checkSearchBySubject;
	private ButtonGroup acmSearchRadioButtonGroup;
	private JComboBox keywordJComboBox;
	
	private static DBSAStatusBar dbsaStatus = new DBSAStatusBar();
	
	public FetcherPanel(JTabbedPane dbsa) {
		initComponents();
		this.dbsaTabFrame = dbsa;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getFetcherJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 0)));
		initAcmSearchRadioButtonGroup();
		
		setSize(935, 477);
	}
	
	@SuppressWarnings("static-access")
	private JComboBox getKeywordJComboBox() {
		if (keywordJComboBox == null) {
			keywordJComboBox = new JComboBox();
			keywordJComboBox.setEditable(true);

			keywordJComboBox.setDoubleBuffered(false);
			keywordJComboBox.setRequestFocusEnabled(false);
			
			ArrayList<Subject> dbsaSubjectList = new ArrayList<Subject>();
			dbsaSubjectList = LoadSubject.getSubject();
			keywordJComboBox.addItem("");

			if(dbsaSubjectList != null){
				for(int i = 0; i < dbsaSubjectList.size(); i++){
					keywordJComboBox.addItem(dbsaSubjectList.get(i).getSbj_name());
				}
			}
			keywordJComboBox.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					
					dbsaStatus.setDBSAProgressMessage("Please choose or enter keyword to fetch!");
				}

				@Override
				public void mouseExited(MouseEvent e) {
					dbsaStatus.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
			});
		}
		return keywordJComboBox;
	}

	private void initAcmSearchRadioButtonGroup() {
		acmSearchRadioButtonGroup = new ButtonGroup();
		acmSearchRadioButtonGroup.add(getSearchByAllRadioButton());
		acmSearchRadioButtonGroup.add(getSearchBySubjectJRadioButton());
	}

	private JRadioButton getSearchBySubjectJRadioButton() {
		if (searchBySubjectJRadioButton == null) {
			searchBySubjectJRadioButton = new JRadioButton();
			searchBySubjectJRadioButton.setVisible(false);
			checkSearchBySubject = false;
			searchBySubjectJRadioButton.setText(DBSAResourceBundle.res.getString("search.by.subject"));
			
			searchBySubjectJRadioButton.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if(searchBySubjectJRadioButton.isSelected()){
						checkSearchBySubject = true;
					}else{
						checkSearchBySubject = false;
					}
				}
				
			});
		}
		return searchBySubjectJRadioButton;
	}

	private JRadioButton getSearchByAllRadioButton() {
		if (searchByAllRadioButton == null) {
			searchByAllRadioButton = new JRadioButton();
			searchByAllRadioButton.setSelected(true);
			checkSearchByAll = true;
			searchByAllRadioButton.setVisible(false);
			searchByAllRadioButton.setText(DBSAResourceBundle.res.getString("search.by.all"));
			searchByAllRadioButton.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if(searchByAllRadioButton.isSelected()){
						checkSearchByAll = true;
					}else{
						checkSearchByAll = false;
					}
						
				}
				
			});
		}
		return searchByAllRadioButton;
	}

	private JLabel getFetcherStatusJLabel() {
		if (fetcherStatusJLabel == null) {
			fetcherStatusJLabel = new JLabel();
			fetcherStatusJLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			fetcherStatusJLabel.setText(DBSAResourceBundle.res.getString("fetcher.status"));
		}
		return fetcherStatusJLabel;
	}

	private JPanel getFetcherJPanel() {
		if (fetcherJPanel == null) {
			fetcherJPanel = new JPanel();
			fetcherJPanel.setBorder(BorderFactory.createTitledBorder(null, "fetcher", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			fetcherJPanel.setLayout(new GroupLayout());
			fetcherJPanel.add(getActionsJPanel(), new Constraints(new Bilateral(0, 0, 513), new Trailing(0, 68, 278, 278)));
			fetcherJPanel.add(getChooseJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(67, 74, 198)));
			fetcherJPanel.add(getInputJPanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 66, 10, 10)));
		}
		return fetcherJPanel;
	}

	private JPanel getActionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(null, "actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getShowResultJButton(), new Constraints(new Trailing(138, 241, 271), new Leading(-1, 36, 12, 12)));
			actionsJPanel.add(getFetcherJButton(), new Constraints(new Trailing(265, 107, 12, 12), new Leading(0, 35, 12, 12)));
			actionsJPanel.add(getCloseJButton(), new Constraints(new Trailing(12, 108, 383, 383), new Leading(-1, 36, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getFetcherJButton() {
		
		if (fetcherJButton == null) {
			fetcherJButton = new JButton();
			fetcherJButton.setText(DBSAResourceBundle.res.getString("fetcher"));
			
			fetcherJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					fetcherFunction(fetcherJButton);
				}
				
			});
			
		}
		return fetcherJButton;
	}
		
	public void fetcherFunction(final JButton fetchJButton){

		fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		
		//boolean checkInternetConnecttion = DBSAApplication.isInternetReachable();
		if(!DBSAApplication.isInternetReachable()){
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("check.internet.connection"));
		}
		
		if(keywordJComboBox.getSelectedItem().toString().replaceAll(" ", "").equals("")){
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("messeage.request.input.keyword"));
		}
		else if(keywordJComboBox.getSelectedItem().toString() != "" && DBSAApplication.isInternetReachable()){
			if(fetchFromACMCheckBox.isSelected() == true) {
				fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				fetcherBoolean = true;
				final String acmQuery = keywordJComboBox.getSelectedItem().toString();
			

			Thread acmThread = new Thread (new Runnable(){
				@Override
				public void run() {
					uit.tkorg.dbsa.cores.fetchers.ACMFetcher.shouldContinue = true;
					setAcmResultNumber(Integer.parseInt(acmJSpinner.getValue().toString()));
					acmJProgressBar.setIndeterminate(true);
					acmJProgressBar.setStringPainted(true);
					acmJProgressBar.setString(DBSAResourceBundle.res.getString("fetching.from.acmdl"));
					try {
						ACMFetcher(acmQuery);
						acmJProgressBar.setIndeterminate(false);
						acmJProgressBar.setString(DBSAResourceBundle.res.getString("complete"));	
						fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						showResultJButton.setEnabled(true);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			});


			acmThread.start();
				//acmThread.interrupt();
		}
		if(citeseerDLCheckBox.isSelected() == true){
			fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			fetcherBoolean = true;
			
			final String citeseerQuery = keywordJComboBox.getSelectedItem().toString();
			Thread citeseerThread = new Thread (new Runnable(){
				@Override
				public void run() {
						setCiteResultNumber(Integer.parseInt(citeseerJSpinner.getValue().toString()));
						citeseerJProgressBar.setIndeterminate(true);
						citeseerJProgressBar.setStringPainted(true);
						citeseerJProgressBar.setString(DBSAResourceBundle.res.getString("fetching.from.citeseerdl"));
						CiteSeeXFetcher(citeseerQuery);	
						fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						citeseerJProgressBar.setIndeterminate(false);
						citeseerJProgressBar.setString(DBSAResourceBundle.res.getString("complete"));
						showResultJButton.setEnabled(true);	
					
				}});
			citeseerThread.start();	
			citeseerThread.interrupt();
		}
			
		if(ieeexploreDLCheckBox.isSelected() == true){
			fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			fetcherBoolean = true;
			final String ieeeQuery = keywordJComboBox.getSelectedItem().toString();
			Thread ieeeThread = new Thread (new Runnable(){
				@Override
				public void run() {
						setIeeeResultNumber(Integer.parseInt(ieeexploreJSpinner.getValue().toString()));
						ieeeploreJProgressBar.setIndeterminate(true);
						ieeeploreJProgressBar.setStringPainted(true);
						ieeeploreJProgressBar.setString(DBSAResourceBundle.res.getString("fetching.from.ieeedl"));
						try {
							IEEExploreFetch(ieeeQuery);
							ieeeploreJProgressBar.setIndeterminate(false);
							ieeeploreJProgressBar.setString(DBSAResourceBundle.res.getString("complete"));
							showResultJButton.setEnabled(true);
							fetchJButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						} catch (IOException t) {

							t.printStackTrace();
						}
					}});
				ieeeThread.start();
				ieeeThread.interrupt();
		}	
	
		
		if(!fetcherBoolean){
			JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("messeage.request.choose.dl"));
		}
			/*
			 * reset application Jpanel
			 */
			//keywordJTextField.setEnabled(false);
			fetcherBoolean = false;
		}
	
	}
	private JButton getShowResultJButton() {
		if (showResultJButton == null) {
			showResultJButton = new JButton();
			showResultJButton.setText(DBSAResourceBundle.res.getString("show.results"));
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
			closeJButton.setText(DBSAResourceBundle.res.getString("close"));
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
			ieeeploreJProgressBar.setDoubleBuffered(true);
			ieeeploreJProgressBar.setOpaque(false);
			ieeeploreJProgressBar.setStringPainted(true);
			ieeeploreJProgressBar.setString(DBSAResourceBundle.res.getString("ieee.digital.library"));
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
			citeseerJProgressBar.setString(DBSAResourceBundle.res.getString("citeseer.digital.library"));
			
		}
		return citeseerJProgressBar;
	}

	private JProgressBar getAcmJProgressBar() {
		if (acmJProgressBar == null) {
			acmJProgressBar = new JProgressBar();
			acmJProgressBar.setStringPainted(true);
			acmJProgressBar.setString(DBSAResourceBundle.res.getString("acm.digital.library"));
			
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
			chooseJPanel.setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("choose.dls"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			chooseJPanel.setLayout(new GroupLayout());
			chooseJPanel.add(getFetchFromJLabel(), new Constraints(new Leading(23, 89, 10, 10), new Leading(12, 33, 12, 12)));
			chooseJPanel.add(getMaxResultLabel(), new Constraints(new Leading(389, 96, 10, 10), new Leading(12, 32, 12, 12)));
			chooseJPanel.add(getFetcherStatusJLabel(), new Constraints(new Trailing(289, 106, 12, 12), new Leading(12, 35, 12, 12)));
			chooseJPanel.add(getCiteseerJProgressBar(), new Constraints(new Bilateral(519, 13, 383), new Leading(97, 26, 12, 12)));
			chooseJPanel.add(getSearchByAllRadioButton(), new Constraints(new Leading(45, 10, 10), new Leading(171, 8, 8)));
			chooseJPanel.add(getSearchBySubjectJRadioButton(), new Constraints(new Leading(44, 8, 8), new Leading(204, 10, 10)));
			chooseJPanel.add(getIeeeploreJProgressBar(), new Constraints(new Bilateral(519, 14, 10), new Leading(53, 28, 12, 12)));
			chooseJPanel.add(getIeeexploreJSpinner1(), new Constraints(new Leading(404, 48, 10, 10), new Leading(53, 27, 12, 12)));
			chooseJPanel.add(getIeeexploreDLCheckBox(), new Constraints(new Leading(23, 30, 168), new Leading(53, 28, 12, 12)));
			chooseJPanel.add(getCiteseerJSpinner(), new Constraints(new Leading(404, 48, 29, 167), new Leading(98, 26, 12, 12)));
			chooseJPanel.add(getAcmJSpinner(), new Constraints(new Leading(404, 48, 35, 407), new Leading(138, 28, 12, 12)));
			chooseJPanel.add(getCiteseerDLCheckBox(), new Constraints(new Leading(23, 29, 167), new Leading(97, 28, 12, 12)));
			chooseJPanel.add(getFetchFromACMCheckBox(), new Constraints(new Leading(23, 35, 407), new Leading(138, 28, 12, 12)));
			chooseJPanel.add(getAcmJProgressBar(), new Constraints(new Bilateral(520, 13, 382), new Leading(138, 28, 12, 12)));
		}
		return chooseJPanel;
	}

	private JSpinner getCiteseerJSpinner() {
		if (citeseerJSpinner == null) {
			citeseerJSpinner = new JSpinner();
			citeseerJSpinner.setModel(new SpinnerNumberModel(0, 0, CITESEER_MAX_RESULT, 1));
			
			citeseerJSpinner.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (Integer.parseInt(citeseerJSpinner.getValue().toString()) < 1) {
						citeseerJSpinner.setValue(1);
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.result.number") + CITESEER_MAX_RESULT);
						
					} else if (Integer.parseInt(citeseerJSpinner.getValue().toString()) > CITESEER_MAX_RESULT) {
						citeseerJSpinner.setValue(CITESEER_MAX_RESULT);
						JOptionPane.showMessageDialog(null,  DBSAResourceBundle.res.getString("please.input.result.number") + CITESEER_MAX_RESULT);
						
					}
				}
			});
		}
		return citeseerJSpinner;
	}

	private JCheckBox getCiteseerDLCheckBox() {
		if (citeseerDLCheckBox == null) {
			citeseerDLCheckBox = new JCheckBox();
			citeseerDLCheckBox.setFont(new Font("Times New Roman", Font.ITALIC, 18));
			citeseerDLCheckBox.setText(DBSAResourceBundle.res.getString("fetching.from.citeseerdl"));
			citeseerDLCheckBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return citeseerDLCheckBox;
	}

	private JSpinner getIeeexploreJSpinner1() {
		if (ieeexploreJSpinner == null) {
			ieeexploreJSpinner = new JSpinner();
			ieeexploreJSpinner.setModel(new SpinnerNumberModel(0, 0, IEEE_MAX_RESULT, 1));
			ieeexploreJSpinner.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (Integer.parseInt(ieeexploreJSpinner.getValue().toString()) < 1) {
						ieeexploreJSpinner.setValue(1);
						JOptionPane.showMessageDialog(null,  DBSAResourceBundle.res.getString("please.input.result.number") + IEEE_MAX_RESULT);
						
					} else if (Integer.parseInt(ieeexploreJSpinner.getValue().toString()) > IEEE_MAX_RESULT) {
						ieeexploreJSpinner.setValue(IEEE_MAX_RESULT);
						JOptionPane.showMessageDialog(null,  DBSAResourceBundle.res.getString("please.input.result.number") + IEEE_MAX_RESULT);
						
					}
				}
			});
		}
		return ieeexploreJSpinner;
	}

	private JSpinner getAcmJSpinner() {
		if (acmJSpinner == null) {
			acmJSpinner = new JSpinner();
			acmJSpinner.setModel(new SpinnerNumberModel(0, 0, ACM_MAX_RESULT, 1));
			
			acmJSpinner.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					
					if (Integer.parseInt(acmJSpinner.getValue().toString()) < 1 || Integer.parseInt(acmJSpinner.getValue().toString()) > 50) {
						acmJSpinner.setValue(1);
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("please.input.result.number") + ACM_MAX_RESULT);
						
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
			ieeexploreDLCheckBox.setText(DBSAResourceBundle.res.getString("fetching.from.ieeedl"));
			ieeexploreDLCheckBox.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return ieeexploreDLCheckBox;
	}

	private JLabel getMaxResultLabel() {
		if (maxResultLabel == null) {
			maxResultLabel = new JLabel();
			maxResultLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			maxResultLabel.setText(DBSAResourceBundle.res.getString("maxresult"));
		}
		return maxResultLabel;
	}

	private JCheckBox getFetchFromACMCheckBox() {
		if (fetchFromACMCheckBox == null) {
			fetchFromACMCheckBox = new JCheckBox();
			fetchFromACMCheckBox.setFont(new Font("Times New Roman", Font.ITALIC, 18));
			fetchFromACMCheckBox.setText(DBSAResourceBundle.res.getString("fetching.from.acmdl"));
			fetchFromACMCheckBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			fetchFromACMCheckBox.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					if (fetchFromACMCheckBox.isSelected()) {
						searchByAllRadioButton.setVisible(true);
						searchBySubjectJRadioButton.setVisible(true);
					} else {
						searchByAllRadioButton.setVisible(false);
						searchBySubjectJRadioButton.setVisible(false);
					}
				}
			});
		}
		return fetchFromACMCheckBox;
	}

	private JLabel getFetchFromJLabel() {
		if (fetchFromJLabel == null) {
			fetchFromJLabel = new JLabel();
			fetchFromJLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			fetchFromJLabel.setText(DBSAResourceBundle.res.getString("fetch.from"));
		}
		return fetchFromJLabel;
	}

	private JPanel getInputJPanel() {
		if (inputJPanel == null) {
			inputJPanel = new JPanel();
			inputJPanel.setBorder(BorderFactory.createTitledBorder(null, " ", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			inputJPanel.setLayout(new GroupLayout());
			inputJPanel.add(getKeywordJLabel(), new Constraints(new Leading(4, 108, 10, 10), new Leading(-3, 36, 10, 10)));
			inputJPanel.add(getKeywordJComboBox(), new Constraints(new Bilateral(120, 12, 783), new Leading(-3, 36, 12, 12)));
		}
		return inputJPanel;
	}

	private JLabel getKeywordJLabel() {
		if (keywordJLabel == null) {
			keywordJLabel = new JLabel();
			keywordJLabel.setBackground(Color.white);
			keywordJLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			keywordJLabel.setText("  " + DBSAResourceBundle.res.getString("input.keyword") + " : ");
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

}
