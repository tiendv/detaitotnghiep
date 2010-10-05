package uit.tkorg.dbsa.gui.main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uit.tkorg.dbsa.gui.classification.ClassificationPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DISATabPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private FetcherPanel fetcherJPanel = null;
	private FetcherResultPanel fetcherResultPanel = null;
	private ClassificationPanel classificationJPanel = null;
	private JPanel databaseManagementJPanel = null;
	private JTabbedPane disaJTabbedPane = null;

	public DISATabPanel() {
		super();
		
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder("Functions"));
		addTab("Fetcher", getFetcherResultPanel());
		addTab("Classification", getClassificationJPanel());
		addTab("Database management", getDatabaseManagementJPanel());
		setSize(674, 411);
	}

	private JTabbedPane getDisaJTabbedPane() {
		if (disaJTabbedPane == null) {
			disaJTabbedPane = new JTabbedPane();
			disaJTabbedPane.setBorder(BorderFactory.createTitledBorder("Functions"));
			disaJTabbedPane.addTab("Fetcher",getFetcherJPanel());
			disaJTabbedPane.addTab("Classification", getClassificationJPanel());
			disaJTabbedPane.addTab("Database management",getDatabaseManagementJPanel());
		}
		return disaJTabbedPane;
	}

	private JPanel getDatabaseManagementJPanel() {
		if (databaseManagementJPanel == null) {
			databaseManagementJPanel = new JPanel();
		}
		return databaseManagementJPanel;
	}

	private ClassificationPanel getClassificationJPanel() {
		if (classificationJPanel == null) {
			classificationJPanel = new ClassificationPanel();
		}
		return classificationJPanel;
	}

	private FetcherPanel getFetcherJPanel() {
		if (fetcherJPanel == null) {
			fetcherJPanel = new FetcherPanel();
		}
		return fetcherJPanel;
	}
	
	private FetcherResultPanel getFetcherResultPanel(){
		if(fetcherResultPanel == null){
			fetcherResultPanel = new FetcherResultPanel();
		}
		return fetcherResultPanel;
	}
}
