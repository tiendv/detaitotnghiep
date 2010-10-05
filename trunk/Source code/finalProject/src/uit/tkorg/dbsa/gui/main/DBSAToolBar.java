package uit.tkorg.dbsa.gui.main;

import javax.swing.JButton;
import javax.swing.JToolBar;

import uit.tkorg.dbsa.gui.main.ComponentUilities.ComponentUtilities;
/**
 * 
 * @author CuongNP
 *
 */

public class DBSAToolBar extends JToolBar{
	private static final long serialVersionUID = 1L;
	private JButton openButton = null;
	
	public JButton getOpenButton() {
		if(openButton == null) {
			openButton = ComponentUtilities.createButton(null,("open.ofetcher"),(""));
		}
		return openButton;
	}
	public DBSAToolBar() {
		super();		
		add(getOpenButton());
		
	}
	
}
