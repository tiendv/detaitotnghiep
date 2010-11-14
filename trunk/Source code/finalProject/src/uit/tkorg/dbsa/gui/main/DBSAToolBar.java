package uit.tkorg.dbsa.gui.main;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import uit.tkorg.dbsa.properties.files.GUIProperties;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
/**
 * 
 * @author CuongNP
 * modif : tiendv
 *
 */

public class DBSAToolBar extends JToolBar{
	private static final long serialVersionUID = 1L;
	JFrame dbsaJFrame = null;
	private JButton checkConnectionButton = null;
	private JButton fetcherButton = null;
	private JButton databaseButton = null;

	private JButton resultButton = null;
	
	public DBSAToolBar(JFrame dbsa) {
		super();
		this.dbsaJFrame = dbsaJFrame;
		initComponents();
		
	}
	private void initComponents() {
		add(getCheckConnectionButton());
		add(getFetcherButton());
		add(getResultButton());
		add(getDatabaseButton());
	}
	public JButton getFetcherButton() {
		if(fetcherButton == null) {
			fetcherButton = new JButton();
			fetcherButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.FETCHER_ICON)));
			fetcherButton.setBorder(null);
			fetcherButton.setToolTipText("Fetcher");
			fetcherButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
		
		return fetcherButton;
	}
	public JButton getDatabaseButton() {
		if(databaseButton == null) {
			databaseButton = new JButton();
			databaseButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DATABASE_ICON)));
			databaseButton.setBorder(null);
			databaseButton.setToolTipText("Database Manager");
			databaseButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
		return databaseButton;
	}
	public JButton getResultButton() {
		if(resultButton == null) {
			resultButton = new JButton();
			resultButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.RESULT_ICON)));
			resultButton.setBorder(null);
			resultButton.setToolTipText("Result");
			resultButton.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
		return resultButton;
	}

	public JButton getCheckConnectionButton() {
		if(checkConnectionButton == null) {
			checkConnectionButton = new JButton();
			checkConnectionButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.CHECK_CONNECTION_ICON)));
			checkConnectionButton.setBorder(null);
			checkConnectionButton.setToolTipText("Check Connection");
			checkConnectionButton.addMouseListener(new MouseListener(){

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
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
		return checkConnectionButton;

	}
	
}
