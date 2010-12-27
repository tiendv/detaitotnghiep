package uit.tkorg.dbsa.gui.main;

/**
 *
 * @author cuongnp
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.jabref.sql.DbImportAction;

import uit.tkorg.dbsa.gui.classification.ClassificationPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPatternDialog;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.DBSAModulesProperties;

public class DBSAApplication extends JPanel {

	public static JFrame dbsaJFrame = null;
	private JMenuBar jMenuBar = null;

	private JMenu fileJMenu = null;
	private JMenu editJMenu = null;
	private JMenu fetcherJMenu = null;
	private JMenu classificationJMenu = null;
	private JMenu optionJMenu = null;
	private JMenu helpJMenu = null;
	
	private JMenuItem checkInternetJMenuItem = null;
	private JMenuItem checkMySQLConnectionJMenuItem = null;
	private JMenuItem exitJMenuItem = null;
	
	private JMenuItem cutJMenuItem = null;
	private JMenuItem copyJMenuItem = null;
	private JMenuItem pasteJMenuItem = null;
	private JMenuItem deleteJMenuItem = null;
	private JMenuItem markEntriesJMenuItem = null;
	private JMenuItem unmarkEntriesJMenuItem = null;
	private JMenuItem unMarkAllJMenuItem = null;
	private JMenuItem selectAllJMenuItem = null;
	
	private JMenuItem patternJMenuItem = null;
	
	private JMenuItem configurationJMenuItem = null;
	
	private JMenuItem helpJMenuItem = null;
	private JMenuItem aboutJMenuItem = null;
	
	private static DBSATabPanel dbsaTabPanel = null;
	private FetcherPanel fetcherPanel = null;
	private ClassificationPanel classificationPanel = null;
	
	private static DBSAToolBar dbsaToolbar = null;
	
	public static DBSAStatusBar dbsaStatusBar = null;

	public static DBSAFetcherPattern dbsaFetcherPattern = null;
	public static FetcherResultPanel fetcherResultPanel = null;
	
	private JFrame getDBSAJFrame(){
	
		if (dbsaJFrame == null) {
			dbsaJFrame = new JFrame();
			dbsaJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dbsaJFrame.setJMenuBar(getJMenuBar());
			//dbsaJFrame.setSize(640, 480);
			dbsaJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			dbsaJFrame.setBackground(Color.white);
			//ComponentUtilities.setMiniSize(dbsaJFrame);
			dbsaJFrame.setTitle(DBSAResourceBundle.res.getString("application.name"));
			dbsaFetcherPattern = new DBSAFetcherPattern();
			fetcherResultPanel = new FetcherResultPanel(dbsaTabPanel);
			
			getDBSAContent();
			if(fetcherPanel == null){
				fetcherPanel = new FetcherPanel(dbsaTabPanel);
			}else{
				if(dbsaStatusBar == null){
					dbsaStatusBar = new DBSAStatusBar();
				}else
					dbsaStatusBar.setStatusJLabel("");
				dbsaStatusBar.repaint();
			}
			
			dbsaJFrame.repaint();
			
		}
		return dbsaJFrame;
	}
	
	public static void getDBSAContent(){
		dbsaJFrame.getContentPane().add(getDBSAToolBar(), BorderLayout.NORTH);
		dbsaJFrame.getContentPane().add(getDBSATabpanel(), BorderLayout.CENTER);
		dbsaJFrame.getContentPane().add(getDBSAStatusBar(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	
	private JMenuBar getJMenuBar(){
		if(jMenuBar == null){
			jMenuBar = new JMenuBar();
			
			jMenuBar.add(getFileJMenu());
			jMenuBar.add(getEditJMenu());
			jMenuBar.add(getFetcherJMenu());
			jMenuBar.add(getClassificationJMenu());
			jMenuBar.add(getOptionJMenu());
			jMenuBar.add(getHelpJMenu());
		}
		
		return jMenuBar;
	}
	
	/**
	 * This method initializes fileJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileJMenu() {
		if (fileJMenu == null) {
			fileJMenu = new JMenu();
			fileJMenu.setText(DBSAResourceBundle.res.getString("file"));
			fileJMenu.add(getcheckInternetJMenuItem());
			fileJMenu.add(getcheckMySQLConnectionJMenuItem());
			fileJMenu.add(getExitJMenuItem());
		}
		return fileJMenu;
	}
	
	/**
	 * This method initalizes checkInternetConnectionJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getcheckInternetJMenuItem(){
		if(checkInternetJMenuItem == null){
			checkInternetJMenuItem = new JMenuItem();
			checkInternetJMenuItem.setText(DBSAResourceBundle.res.getString("check.internet.connection"));
			checkInternetJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, true));
			checkInternetJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(isInternetReachable() == true){
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("check.ok"));
					}else{
						JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("check.false"));
					}
				}
				
			});
		}
		return checkInternetJMenuItem;
	}
	/*
	 * This is method check Internet connection
	 * @return boolean
	 */
	
	 public static boolean isInternetReachable()
     {
             try {
                     //make a URL to a known source
                     URL url = new URL(DBSAApplicationConst.GOOGLE_URL);

                     //open a connection to that source
                     HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

                     //trying to retrieve data from the source. If there
                     //is no connection, this line will fail
                     Object objData = urlConnect.getContent();

             } catch (UnknownHostException e) {
                     // TODO Auto-generated catch block
                     //e.printStackTrace();
                     return false;
             }
             catch (IOException e) {
                     // TODO Auto-generated catch block
                     //.printStackTrace();
                     return false;
             }
             return true;
     }

	 
	/**
	 * This method initalizes checkMySQLConnectionJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getcheckMySQLConnectionJMenuItem(){
		if(checkMySQLConnectionJMenuItem == null){
			checkMySQLConnectionJMenuItem = new JMenuItem();
			checkMySQLConnectionJMenuItem.setText(DBSAResourceBundle.res.getString("check.database.connection"));
		}
		return checkMySQLConnectionJMenuItem;
	}	


	/**
	 * This method initalizes exitJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitJMenuItem(){
		if(exitJMenuItem == null){
			exitJMenuItem = new JMenuItem();
			exitJMenuItem.setText(DBSAResourceBundle.res.getString("exit"));
			exitJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DBSAApplication.dbsaJFrame.dispose();
				}
				
			});
		}
		return exitJMenuItem;
	}		
	
	/**
	 * This method initializes editJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditJMenu() {
		if (editJMenu == null) {
			editJMenu = new JMenu();
			editJMenu.setText(DBSAResourceBundle.res.getString("edit"));
			//editJMenu.add(getCutJMenuItem());
			//editJMenu.add(getCopyJMenuItem());
			//editJMenu.add(getPasteJMenuItem());
			//editJMenu.add(getDeleteJMenuItem());
		//	editJMenu.add(getMarkEntriesJMenuItem());
			//editJMenu.add(getUnmarkEntriesJMenuItem());
			editJMenu.add(getUnmarkAllJMenuItem());
			editJMenu.add(getSelectAllJMenuItem());

		}
		return editJMenu;
	}	
	
	/**
	 * This method initalizes cutJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getCutJMenuItem(){
		if(cutJMenuItem == null){
			cutJMenuItem = new JMenuItem();
			cutJMenuItem.setText("Cut");
			cutJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK, true));
		}
		return cutJMenuItem;
	}
	
	/**
	 * This method initalizes copyJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getCopyJMenuItem(){
		if(copyJMenuItem == null){
			copyJMenuItem = new JMenuItem();
			copyJMenuItem.setText("Copy");
			copyJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK, true));
		}
		return copyJMenuItem;
	}
	
	/**
	 * This method initalizes pasteJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getPasteJMenuItem(){
		if(pasteJMenuItem == null){
			pasteJMenuItem = new JMenuItem();
			pasteJMenuItem.setText("Paste");
			pasteJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK, true));
		}
		return pasteJMenuItem;
	}
	
	/**
	 * This method initalizes deleteJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getDeleteJMenuItem(){
		if(deleteJMenuItem == null){
			deleteJMenuItem = new JMenuItem();
			deleteJMenuItem.setText("Delete");
			deleteJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK, true));
		}
		return deleteJMenuItem;
	}
	
	/**
	 * This method initalizes markEntriesJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getMarkEntriesJMenuItem(){
		if(markEntriesJMenuItem == null){
			markEntriesJMenuItem = new JMenuItem();
			markEntriesJMenuItem.setText("Mark entries");
		}
		return markEntriesJMenuItem;
	}
	
	/**
	 * This method initalizes unmarkEntriesJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getUnmarkEntriesJMenuItem(){
		if(unmarkEntriesJMenuItem == null){
			unmarkEntriesJMenuItem = new JMenuItem();
			unmarkEntriesJMenuItem.setText("Unmark entries");
		}
		return unmarkEntriesJMenuItem;
	}
	
	/**
	 * This method initalizes unmarkAllJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getUnmarkAllJMenuItem(){
		if(unMarkAllJMenuItem == null){
			unMarkAllJMenuItem = new JMenuItem();
			unMarkAllJMenuItem.setText("Unmark all");
		}
		return unMarkAllJMenuItem;
	}
	
	/**
	 * This method initalizes selectAllJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getSelectAllJMenuItem(){
		if(selectAllJMenuItem == null){
			selectAllJMenuItem = new JMenuItem();
			selectAllJMenuItem.setText("Select all");
			selectAllJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK, true));
		}
		return selectAllJMenuItem;
	}
	
	/**
	 * This method initializes classificationJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getClassificationJMenu() {
		if (classificationJMenu == null) {
			classificationJMenu = new JMenu();
			classificationJMenu.setText(DBSAResourceBundle.res.getString("classification"));
//			fileJMenu.add(getSaveMenuItem());
//			fileJMenu.add(getExitMenuItem());
		}
		return classificationJMenu;
	}
	
	/**
	 * This method initializes classificationJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFetcherJMenu() {
		if (fetcherJMenu == null) {
			fetcherJMenu = new JMenu();
			fetcherJMenu.setText(DBSAResourceBundle.res.getString("fetcher"));
			fetcherJMenu.add(getPatternJMenuItem());
//			fileJMenu.add(getExitMenuItem());
		}
		return fetcherJMenu;
	}	

	/**
	 * This method initalizes unmarkAllJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getPatternJMenuItem(){
		if(patternJMenuItem == null){
			patternJMenuItem = new JMenuItem();
			patternJMenuItem.setText("Change pattern");
			patternJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					FetcherPatternDialog f = new FetcherPatternDialog(dbsaJFrame);	
					f.setVisible(true);
				}
				
			});
		}
		return patternJMenuItem;
	}
	
	/**
	 * This method initializes optionJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getOptionJMenu() {
		if (optionJMenu == null) {
			optionJMenu = new JMenu();
			optionJMenu.setText(DBSAResourceBundle.res.getString("option"));
			optionJMenu.add(getConfigurationJMenuItem());
		}
		return optionJMenu;
	}	
	
	/**
	 * This method initalizes configurationJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getConfigurationJMenuItem(){
		if(configurationJMenuItem == null){
			configurationJMenuItem = new JMenuItem();
			configurationJMenuItem.setText(DBSAResourceBundle.res.getString("config"));
			configurationJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DBSAConfigurationDialog dialog = new DBSAConfigurationDialog(dbsaJFrame);
					dialog.setVisible(true);
				}
				
			});
		}
		return configurationJMenuItem;
	}

	/**
	 * This method initializes helpJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpJMenu() {
		if (helpJMenu == null) {
			helpJMenu = new JMenu();
			helpJMenu.setText(DBSAResourceBundle.res.getString("help"));
			helpJMenu.add(getHelpJMenuItem());
			helpJMenu.add(getAboutJMenuItem());
			
		}
		return helpJMenu;
	}

	/**
	 * This method initalizes helpJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getHelpJMenuItem(){
		if(helpJMenuItem == null){
			helpJMenuItem = new JMenuItem();
			helpJMenuItem.setText(DBSAResourceBundle.res.getString("help"));
			
		}
		return helpJMenuItem;
	}
	
	/**
	 * This method initalizes aboutJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAboutJMenuItem(){
		if(aboutJMenuItem == null){
			aboutJMenuItem = new JMenuItem();
			aboutJMenuItem.setText(DBSAResourceBundle.res.getString("about"));
			aboutJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DBSAAboutDialog about = new DBSAAboutDialog(dbsaJFrame);
					about.setVisible(true);
					//about.show();
				}
			});
			
		}
		return aboutJMenuItem;
	}
		
	
	/**
	 * 
	 * return DBSATabPanel
	 */
	private static DBSATabPanel getDBSATabpanel(){
		if(dbsaTabPanel == null){
			dbsaTabPanel = new DBSATabPanel();
			//dbsaTabPanel.g
			dbsaTabPanel.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					setDBSAToolBar(dbsaTabPanel.getSelectedIndex() + 1);
				}				
			});			
		}
		return dbsaTabPanel;
	}
	
	/**
	 * This is method 
	 * @return
	 */
	private static DBSAStatusBar getDBSAStatusBar(){
		if(dbsaStatusBar == null){
			dbsaStatusBar = new DBSAStatusBar();
		}
		return dbsaStatusBar;
	}
	
	private static DBSAToolBar getDBSAToolBar(){
		if(dbsaToolbar == null){
			dbsaToolbar = new DBSAToolBar(getDBSATabpanel());
		}
		return dbsaToolbar;
	}
	
	private static void setDBSAToolBar(int IDToolBar){
		switch (IDToolBar) {
		case DBSAModulesProperties.FETCHER_MODULE_NAME:
			//fetcherPanel.setEnabled(true);
			dbsaJFrame.getContentPane().repaint();
			//DBSAJFrame.getContentPane().add(getFetcherPanel(),BorderLayout.NORTH);
			dbsaJFrame.invalidate();
			dbsaJFrame.validate();
			dbsaJFrame.getContentPane().repaint();
			break;
			
		case DBSAModulesProperties.CLASSIFICATION_MODULE_NAME:
		
			dbsaJFrame.getContentPane().repaint();
			//DBSAJFrame.getContentPane().add(getClassificationPanel(),BorderLayout.NORTH);
			dbsaJFrame.invalidate();
			dbsaJFrame.validate();
			dbsaJFrame.getContentPane().repaint();
			break;
		
		}
	}
	
	/**
	 * This method destroy fetcher module.
	 * 
	 * @return null
	 */
	public void DBSAbleFetcherPanel(){
		if(fetcherPanel != null){
			dbsaJFrame.getContentPane().remove(fetcherPanel);
		}
	}
	
	/**
	 * This method destroy classification module.
	 * 
	 * @return null
	 */
	public void DBSAbleClassificationPanel(){
		if(classificationPanel != null){
			dbsaJFrame.getContentPane().remove(classificationPanel);
		}
	}
	
	/**
	 * This method get progress status.
	 * @return String 
	 */
	
	
	/**
	 * This is method update text of components
	 * 
	 * @return null
	 */
	public static void updateTextOfComponents(){
		dbsaJFrame.setTitle(DBSAResourceBundle.res.getString("application.name"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Locale locale = Locale.US;
					Locale.setDefault(locale);	
					DBSAResourceBundle.res = DBSAResourceBundle.initResources();
					DBSAResourceBundle.swingRes = DBSAResourceBundle.initSwingResources();
					UIManager.setLookAndFeel(DBSAResourceBundle.swingRes.getString("swing.installedlaf.liquid.class"));
					//com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
					com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
					com.birosoft.liquid.LiquidLookAndFeel.setShowTableGrids(true);
					com.birosoft.liquid.LiquidLookAndFeel.setToolbarFlattedButtons(true);
					
				//	com.birosoft.liquid.LiquidLookAndFeel.set
					DBSAApplication DBSAApplication = new DBSAApplication();
					//updateTextOfComponents();
					DBSAApplication.getDBSAJFrame().setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

}
