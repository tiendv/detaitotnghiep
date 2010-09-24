package uit.tkorg.disa.gui.main;

import javax.swing.JButton;
import javax.swing.JToolBar;

import uit.tkorg.disa.gui.main.ComponentUilities.ComponentUtilities;
/**
 * 
 * @author CuongNP
 *
 */

public class DISAToolBar extends JToolBar{
	private static final long serialVersionUID = 1L;
	private JButton openButton = null;
	
	public JButton getOpenButton() {
		if(openButton == null) {
			openButton = ComponentUtilities.createButton(null,("open.ofetcher"),(""));
		}
		return openButton;
	}
	public DISAToolBar() {
		super();		
		add(getOpenButton());
		
	}
	
}
