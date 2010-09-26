package uit.tkorg.dbsa.gui.main;

/**
 *
 * @author Nguyen Phuoc Cuong
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uit.tkorg.dbsa.gui.classification.ClassificationPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherToolBar;
import uit.tkorg.dbsa.properties.files.DISAModulesProperties;

public class DISAApplication {

	private static JFrame disaJFrame = null;
	private JMenuBar jMenuBar = null;
	
	private JMenu fileJMenu = null;
	private JMenu editJMenu = null;
	private JMenu classificationJMenu = null;
	private JMenu optionJMenu = null;
	private JMenu helpJMenu = null;
	
	private JMenuItem newJMenuItem = null;
	private JMenuItem openJMenuItem = null;
	private JMenuItem saveJMenuItem = null;
	private JMenuItem exitJMenuItem = null;
	
	private JMenuItem cutJMenuItem = null;
	private JMenuItem copyJMenuItem = null;
	private JMenuItem pasteJMenuItem = null;
	private JMenuItem deleteJMenuItem = null;
	private JMenuItem markEntriesJMenuItem = null;
	private JMenuItem unmarkEntriesJMenuItem = null;
	private JMenuItem unMarkAllJMenuItem = null;
	private JMenuItem selectAllJMenuItem = null;
	
	private JMenuItem configurationJMenuItem = null;
	
	private JMenuItem helpJMenuItem = null;
	private JMenuItem aboutJMenuItem = null;
	
	private DISATabPanel disaTabPanel = null;
	private FetcherPanel fetcherPanel = null;
	private ClassificationPanel classificationPanel = null;
	
	private FetcherToolBar fetcherToolbar = null;
	
	private JFrame getDISAJFrame(){
	
		if (disaJFrame == null) {
			disaJFrame = new JFrame();
			disaJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			disaJFrame.setJMenuBar(getJMenuBar());
			//disaJFrame.setSize(640, 480);
			disaJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			//ComponentUtilities.setMiniSize(disaJFrame);
			disaJFrame.setTitle("DISA - Data Index Science Articles");
			disaJFrame.getContentPane().add(getFetcherToolBar(), BorderLayout.NORTH);
			disaJFrame.getContentPane().add(getDISATabpanel(), BorderLayout.CENTER);
//			disaJFrame.getContentPane().add(getIDRSStatusBar(), BorderLayout.SOUTH);
//					
		}
		return disaJFrame;
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
			fileJMenu.setText((" File "));
			fileJMenu.add(getNewJMenuItem());
			fileJMenu.add(getOpenJMenuItem());
			fileJMenu.add(getSaveJMenuItem());
			fileJMenu.add(getExitJMenuItem());
		}
		return fileJMenu;
	}
	
	/**
	 * This method initalizes newJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getNewJMenuItem(){
		if(newJMenuItem == null){
			newJMenuItem = new JMenuItem();
			newJMenuItem.setText("New database");
			newJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, true));
		}
		return newJMenuItem;
	}
	
	/**
	 * This method initalizes openJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getOpenJMenuItem(){
		if(openJMenuItem == null){
			openJMenuItem = new JMenuItem();
			openJMenuItem.setText("Open file");
		}
		return openJMenuItem;
	}	

	/**
	 * This method initalizes saveJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getSaveJMenuItem(){
		if(saveJMenuItem == null){
			saveJMenuItem = new JMenuItem();
			saveJMenuItem.setText("Save");
		}
		return saveJMenuItem;
	}

	/**
	 * This method initalizes exitJMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitJMenuItem(){
		if(exitJMenuItem == null){
			exitJMenuItem = new JMenuItem();
			exitJMenuItem.setText("Exit");
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
			editJMenu.setText(("Edit"));
			editJMenu.add(getCutJMenuItem());
			editJMenu.add(getCopyJMenuItem());
			editJMenu.add(getPasteJMenuItem());
			editJMenu.add(getDeleteJMenuItem());
			editJMenu.add(getMarkEntriesJMenuItem());
			editJMenu.add(getUnmarkEntriesJMenuItem());
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
			classificationJMenu.setText(("Classification"));
//			fileJMenu.add(getSaveMenuItem());
//			fileJMenu.add(getExitMenuItem());
		}
		return classificationJMenu;
	}	

	/**
	 * This method initializes optionJMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getOptionJMenu() {
		if (optionJMenu == null) {
			optionJMenu = new JMenu();
			optionJMenu.setText(("Option"));
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
			configurationJMenuItem.setText("Configuration");
			configurationJMenuItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DISAConfigurationDialog dialog = new DISAConfigurationDialog(disaJFrame);
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
			helpJMenu.setText(("Help"));
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
			helpJMenuItem.setText("Help");
			
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
			aboutJMenuItem.setText("About");
			
		}
		return aboutJMenuItem;
	}
	
	/**
	 * This method display fetcher module tab
	 * 
	 */
	private FetcherPanel getFetcherPanel(){
		if(fetcherPanel == null){
			fetcherPanel = new FetcherPanel();
		}
		return fetcherPanel;
	}
	
	/**
	 * This method display fetcher module tab
	 * 
	 */
	private ClassificationPanel getClassificationPanel(){
		if(classificationPanel == null){
			classificationPanel = new ClassificationPanel();
		}
		return classificationPanel;
	}
	
	/**
	 * 
	 * return DISATabPanel
	 */
	private DISATabPanel getDISATabpanel(){
		if(disaTabPanel == null){
			disaTabPanel = new DISATabPanel();
			
			disaTabPanel.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					setDISAToolBar(disaTabPanel.getSelectedIndex() + 1);
				}
				
			});			
		}
		return disaTabPanel;
	}
	
	private FetcherToolBar getFetcherToolBar(){
		if(fetcherToolbar == null){
			fetcherToolbar = new FetcherToolBar(disaJFrame);
		}
		return fetcherToolbar;
	}
	
	private void setDISAToolBar(int IDToolBar){
		switch (IDToolBar) {
		case DISAModulesProperties.FETCHER_MODULE_NAME:
			//disableClassificationPanel();
			//fetcherPanel.setEnabled(true);
			//classificationPanel.setEnabled(false);
			disaJFrame.getContentPane().repaint();
			disaJFrame.getContentPane().add(getFetcherPanel(),BorderLayout.NORTH);
			disaJFrame.invalidate();
			disaJFrame.validate();
			disaJFrame.getContentPane().repaint();
			break;
			
		case DISAModulesProperties.CLASSIFICATION_MODULE_NAME:
			//classificationPanel.setEnabled(true);
			//fetcherPanel.setEnabled(false);
			//
			disaJFrame.getContentPane().repaint();
			disaJFrame.getContentPane().add(getClassificationPanel(),BorderLayout.NORTH);
			disaJFrame.invalidate();
			disaJFrame.validate();
			disaJFrame.getContentPane().repaint();
			break;

		}
	}
	
	/**
	 * This method destroy fetcher module.
	 * 
	 * @return null
	 */
	public void disableFetcherPanel(){
		if(fetcherPanel != null){
			disaJFrame.getContentPane().remove(fetcherPanel);
		}
	}
	
	/**
	 * This method destroy classification module.
	 * 
	 * @return null
	 */
	public void disableClassificationPanel(){
		if(classificationPanel != null){
			disaJFrame.getContentPane().remove(classificationPanel);
		}
	}
	
	/**
	 * This method update text of components
	 * 
	 * @return null
	 */
	public static void updateTextOfComponents(){
		disaJFrame.setTitle(DISAResourceBundle.res.getString("application.title"));
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
					DISAResourceBundle.res = DISAResourceBundle.initResources();
					//UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
					DISAApplication disaApplication = new DISAApplication();
					//updateTextOfComponents();
					disaApplication.getDISAJFrame().setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

}
