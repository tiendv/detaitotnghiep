package uit.tkorg.dbsa.gui.main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uit.tkorg.dbsa.gui.databasemanagement.DatabaseManagementPanel;
import uit.tkorg.dbsa.gui.databasemanagement.InsertArticleToDatabasePanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSATabPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private FetcherPanel fetcherJPanel = null;
	private FetcherResultPanel fetcherResultPanel = null;
	private InsertArticleToDatabasePanel insertArticleToDatabase = null;
	private JPanel databaseManagementJPanel = null;
	private JTabbedPane dbsaJTabbedPane = null;

	public DBSATabPanel() {
		super();
		initComponents();
		getDbsaJTabbedPane();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("functions")));
		setBackground(Color.white);
		addTab("Fetcher", getFetcherJPanel());
		addTab("Database management", getDatabaseManagementJPanel());
		addTab("Insert article", getInsertArticleToDatabaseJPanel());
		setSize(900, 500);
	}

	private JTabbedPane getDbsaJTabbedPane() {
		if (dbsaJTabbedPane == null) {
			dbsaJTabbedPane = new JTabbedPane();
			dbsaJTabbedPane.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("functions")));
		}
		addTab("Fetcher", getFetcherJPanel());
		addTab("Fetcher result",getFetcherResultPanel());	
		addTab("Database management",getDatabaseManagementJPanel());
		addTab("Insert article", getInsertArticleToDatabaseJPanel());

		return dbsaJTabbedPane;
	}

	private JPanel getDatabaseManagementJPanel() {
		if (databaseManagementJPanel == null) {
			databaseManagementJPanel = new DatabaseManagementPanel();
		}
		return databaseManagementJPanel;
	}

	private InsertArticleToDatabasePanel getInsertArticleToDatabaseJPanel() {
		if (insertArticleToDatabase == null) {
			insertArticleToDatabase = new InsertArticleToDatabasePanel();
		}
		return insertArticleToDatabase;
	}

	private FetcherPanel getFetcherJPanel() {
		if (fetcherJPanel == null) {
			fetcherJPanel = new FetcherPanel(this);
		}
		return fetcherJPanel;
	}
	
	private FetcherResultPanel getFetcherResultPanel(){
		if(fetcherResultPanel == null){
			fetcherResultPanel = new FetcherResultPanel(this);
		}
		return fetcherResultPanel;
	}
}
