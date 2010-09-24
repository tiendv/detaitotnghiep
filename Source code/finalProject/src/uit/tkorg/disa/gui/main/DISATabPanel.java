package uit.tkorg.disa.gui.main;

import javax.swing.JTabbedPane;

import uit.tkorg.disa.gui.classification.ClassificationPanel;
import uit.tkorg.disa.gui.fetcher.FetcherPanel;

/**
*
* @author Nguyen Phuoc Cuong
*/

public class DISATabPanel extends JTabbedPane{
	
	private static final long serialVersionUID = 1L;
	
	private static FetcherPanel fetcherPanel = null;
	private static ClassificationPanel classificationPanel = null;
	
	public DISATabPanel(){
		super();
		add(this.getFetcherPanel(), "Fetcher");
		addTab("Classification", getClassificationPanel());
	}
	
	public FetcherPanel getFetcherPanel(){
		if(fetcherPanel == null){
			fetcherPanel = new FetcherPanel();
		}
		
		return fetcherPanel;
	}
	
	public ClassificationPanel getClassificationPanel(){
		if(classificationPanel == null){
			classificationPanel = new ClassificationPanel();
		}
		
		return classificationPanel;
	}
	/**
	 * 
	 * @return
	 */
	public int getSelectedPanel() {
		return getSelectedIndex();
	};
}
