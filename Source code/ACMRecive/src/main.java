

/**
 * @author TKORG-PC1
 *
 */
import java.awt.Frame;
import java.awt.Panel;

import net.sf.jabref.imports.ACMPortalFetcher;
public class main {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ACMPortalFetcher test = new ACMPortalFetcher();
		Frame te = new Frame();
		te.setSize(50, 100);
		te.add(test.getOptionsPanel());
		te.show();
		//test.getOptionsPanel().show();
		//ImportInspector dialog = null;
		//test.processQuery("test", dialog, true);

	}

}
