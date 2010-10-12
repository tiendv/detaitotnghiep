package uit.tkorg.dbsa.gui.main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uit.tkorg.dbsa.gui.classification.ClassificationPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSATabPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private FetcherPanel fetcherJPanel = null;
	private FetcherResultPanel fetcherResultPanel = null;
	private ClassificationPanel classificationJPanel = null;
	private JPanel databaseManagementJPanel = null;
	private JTabbedPane dbsaJTabbedPane = null;

	public DBSATabPanel() {
		super();
		initComponents();
		getDbsaJTabbedPane();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder("Functions"));
		addTab("Fetcher", getFetcherJPanel());
		addTab("Classification", getClassificationJPanel());
		addTab("Database management", getDatabaseManagementJPanel());
		setSize(755, 437);
	}

	private JTabbedPane getDbsaJTabbedPane() {
		if (dbsaJTabbedPane == null) {
			dbsaJTabbedPane = new JTabbedPane();
			dbsaJTabbedPane.setBorder(BorderFactory.createTitledBorder("Functions"));
		}
		addTab("Fetcher", getFetcherJPanel());
		addTab("Fetcher result",getFetcherResultPanel());	
		addTab("Classification", getClassificationJPanel());
		addTab("Database management",getDatabaseManagementJPanel());

		return dbsaJTabbedPane;
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
