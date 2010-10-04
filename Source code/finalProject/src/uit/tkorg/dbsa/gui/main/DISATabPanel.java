package uit.tkorg.dbsa.gui.main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uit.tkorg.dbsa.gui.classification.ClassificationPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DISATabPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private FetcherPanel fetcherJPanel;
	private ClassificationPanel classificationJPanel;
	private JPanel databaseManagementJPanel;
	private JTabbedPane disaJTabbedPane;

	public DISATabPanel() {
		super();
		this.setBorder(BorderFactory.createTitledBorder("Functions"));
		add("Fetcher", getFetcherJPanel());
		addTab("Classification",getClassificationJPanel());
		addTab("Database management",getDatabaseManagementJPanel());
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder("Functions"));
		addTab("Fetcher", getFetcherJPanel());
		addTab("Classification", getClassificationJPanel());
		addTab("Database management", getDatabaseManagementJPanel());
		setSize(674, 411);
	}

	private JTabbedPane getDisaJTabbedPane() {
		if (disaJTabbedPane == null) {
			disaJTabbedPane = new JTabbedPane();
			disaJTabbedPane.setBorder(BorderFactory.createTitledBorder("Functions"));
			disaJTabbedPane.addTab("Fetcher", null, getFetcherJPanel(), "Fetcher");
			disaJTabbedPane.addTab("Classification", null, getClassificationJPanel(), "Classification");
			disaJTabbedPane.addTab("Database management", null, getDatabaseManagementJPanel(), "Database management");
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
	

}
