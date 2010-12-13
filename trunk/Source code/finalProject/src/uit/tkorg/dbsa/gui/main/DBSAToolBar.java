package uit.tkorg.dbsa.gui.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import uit.tkorg.dbsa.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSAToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton checkConnectionJButton;
	private JButton fetcherJButton;
	private JButton databaseJButton;
	private JButton resultJButton;
	//private JFrame dbsaJFrame = null;

	public DBSAToolBar() {
		super();
		
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(
				51, 51, 51)));
		setLayout(new GroupLayout());
		add(getResultJButton(), new Constraints(new Leading(291, 12, 12), new Bilateral(12, 12, 10)));
		add(getDatabaseJButton(), new Constraints(new Leading(198, 12, 12), new Bilateral(12, 12, 10)));
		add(getFetcherJButton(), new Constraints(new Leading(105, 12, 12), new Bilateral(12, 12, 10)));
		add(getCheckConnectionJButton(), new Constraints(new Leading(12, 12, 12), new Bilateral(12, 12, 10)));
		setSize(555, 144);
	}

	private JButton getResultJButton() {
		if (resultJButton == null) {
			resultJButton = new JButton();
			resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.RESULT_BUTTON_TOOLBAR_ICON)));
			resultJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_RESULT_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					resultJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.RESULT_BUTTON_TOOLBAR_ICON)));
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
			databaseJButton.setToolTipText("Database Manager");
			databaseJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					databaseJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_DATABASE_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
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
			fetcherJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					fetcherJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_FETCHER_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
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
			//checkConnectionJButton.setEnabled(false);
			checkConnectionJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.CHECK_CONNECTION_BUTTON_TOOLBAR_ICON)));
			checkConnectionJButton.setToolTipText("Check Connection");
			checkConnectionJButton.setOpaque(true);
			checkConnectionJButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					Thread thread = new Thread(new Runnable( ){
					public void run() {
						DBSAApplication.dbsaJFrame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						DBSAApplication.dbsaStatusBar.setLoadingStatusBar(true);
						if(DBSAApplication.isInternetReachable() == true){
							JOptionPane.showMessageDialog(null, "Your system is connected the internet. \nYou can fetch article!");
						}else{
							JOptionPane.showMessageDialog(null, "Your computer don't connect to internet. ");
						}
						DBSAApplication.dbsaStatusBar.setLoadingStatusBar(false);
					}
						// TODO Auto-generated method stub
						});
					thread.start();
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					checkConnectionJButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PRESSED_CHECK_CONNECTION_BUTTON_TOOLBAR_ICON)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
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

}
