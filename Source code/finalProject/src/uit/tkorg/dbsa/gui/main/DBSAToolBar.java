package uit.tkorg.dbsa.gui.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JButton checkConnectionJButton;
	private static JButton fetcherJButton;
	private static JButton databaseJButton;
	private static JButton resultJButton;
	private JTabbedPane dbsaTabFrame = null;

	private DBSAStatusBar statusMessage = new DBSAStatusBar();
	
	public DBSAToolBar(JTabbedPane dbsa) {
		super();
		
		initComponents();
		this.dbsaTabFrame = dbsa;
		updateTextsOfComponent();
	}

	private void initComponents() {
		
		setPreferredSize(new Dimension(320, 80));
		setLayout(new GroupLayout());
		add(getFetcherJButton(), new Constraints(new Leading(12, 49, 12, 12), new Leading(0, 50, 12, 12)));
		add(getResultJButton(), new Constraints(new Leading(73, 47, 12, 12), new Leading(0, 50, 12, 12)));
		add(getDatabaseJButton(), new Constraints(new Leading(132, 49, 12, 12), new Leading(0, 50, 12, 12)));
		add(getCheckConnectionJButton(), new Constraints(new Leading(193, 48, 12, 12), new Leading(0, 50, 12, 12)));
		//setSize(320, 240);
	}

	private JButton getResultJButton() {
		if (resultJButton == null) {
			resultJButton = new JButton();
			resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.RESULT_BUTTON_TOOLBAR_ICON)));
			resultJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					dbsaTabFrame.setSelectedIndex(1);
				}
				
			});
		
			resultJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

								@Override
				public void mouseEntered(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("show.fetcher.results"));
					resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_RESULT_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.RESULT_BUTTON_TOOLBAR_ICON)));
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});	
		}
		return resultJButton;
	}

	private JButton getDatabaseJButton() {
		if (databaseJButton == null) {
			databaseJButton = new JButton();
			databaseJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DATABASE_BUTTON_TOOLBAR_ICON)));
			databaseJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dbsaTabFrame.setSelectedIndex(2);
				}
				
			});
			databaseJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("show.database.management"));
					databaseJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_DATABASE_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
					databaseJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DATABASE_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return databaseJButton;
	}

	private JButton getFetcherJButton() {
		if (fetcherJButton == null) {
			fetcherJButton = new JButton();
			fetcherJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.FETCHER_BUTTON_TOOLBAR_ICON)));
			fetcherJButton.setOpaque(false);
			fetcherJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dbsaTabFrame.setSelectedIndex(0);
				}
				
			});
			fetcherJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("show.fetcher.frame"));
					fetcherJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_FETCHER_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
					fetcherJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.FETCHER_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});	
		}
		return fetcherJButton;
	}

	private JButton getCheckConnectionJButton() {
		if (checkConnectionJButton == null) {
			checkConnectionJButton = new JButton();
			checkConnectionJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.CHECK_CONNECTION_BUTTON_TOOLBAR_ICON)));
			checkConnectionJButton.setOpaque(true);
			checkConnectionJButton.setSize(new Dimension(0,0));
			checkConnectionJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
				
			});
			
			checkConnectionJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					Thread thread = new Thread(new Runnable( ){
					public void run() {
						DBSAApplication.dbsaJFrame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						DBSAApplication.dbsaStatusBar.setLoadingStatusBar(true);
						if(DBSAApplication.isInternetReachable() == true){
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("internet.connection.is.availble"));
						}else{
							JOptionPane.showMessageDialog(null, DBSAResourceBundle.res.getString("internet.connection.is.failed"));
						}
						DBSAApplication.dbsaStatusBar.setLoadingStatusBar(false);
					}
						// TODO Auto-generated method stub
						});
					thread.start();
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("check.internet.connection"));
					checkConnectionJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_CHECK_CONNECTION_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
					statusMessage.setDBSAProgressMessage(DBSAResourceBundle.res.getString("group.name"));
					checkConnectionJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.CHECK_CONNECTION_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return checkConnectionJButton;
	}
	
	public void updateTextsOfComponent(){
		setBorder(BorderFactory.createTitledBorder(null, DBSAResourceBundle.res.getString("toolbar"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
				12), new Color(51, 51, 51)));
		resultJButton.setToolTipText(DBSAResourceBundle.res.getString("show.fetcher.results"));
		checkConnectionJButton.setToolTipText(DBSAResourceBundle.res.getString("check.internet.connection"));
		fetcherJButton.setToolTipText(DBSAResourceBundle.res.getString("show.fetcher.frame"));
		databaseJButton.setToolTipText(DBSAResourceBundle.res.getString("show.database.management"));
		
	}

}
