package uit.tkorg.disa.gui.fetcher;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class FetcherToolBar extends JToolBar{
	
private static final long serialVersionUID = 1L;
	
	private JFrame disaJFrame = null; 
	private static JButton newButton;
	
	public FetcherToolBar(JFrame disaJFrame){
		super();
		this.disaJFrame = disaJFrame;
		initComponents();
	};
	
	public void initComponents(){
		add(getNewButton());
	};
	
	private JButton getNewButton(){
		if(newButton == null) {
			newButton = new JButton();
			//newButton.setIcon(new ImageIcon(getClass().getResource()));
			//newButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
			newButton.setBorder(null);
			newButton.setToolTipText("New database");
			newButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					//newButton.setIcon(new ImageIcon(getClass().getResource()));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					//newButton.setIcon(new ImageIcon(getClass().getResource()));
				};
				
			});
		}
		return newButton;
	};
}
