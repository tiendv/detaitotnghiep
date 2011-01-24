package uit.tkorg.dbsa.gui.main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uit.tkorg.dbsa.gui.databasemanagement.DatabaseManagementPanel;
import uit.tkorg.dbsa.gui.databasemanagement.InsertArticleToDatabasePanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
import uit.tkorg.dbsa.gui.statistic.DBSAStatisticPanel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DBSATabPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private FetcherPanel fetcherJPanel = null;
	private FetcherResultPanel fetcherResultPanel = null;
	private InsertArticleToDatabasePanel insertArticleToDatabase = null;
	private DatabaseManagementPanel databaseManagementJPanel = null;
	private DBSAStatisticPanel dbsaStatisticPanel = null;
	private static JTabbedPane dbsaJTabbedPane = null;

	public DBSATabPanel() {
		super();
		initComponents();
		getDbsaJTabbedPane();
		updateTextsOfComponents();
	}

	private void initComponents() {		
		setBackground(Color.white);
		addTab(DBSAResourceBundle.res.getString("fetcher"), getFetcherJPanel());
		addTab(DBSAResourceBundle.res.getString("fetcher.results"), getFetcherResultPanel());
		addTab(DBSAResourceBundle.res.getString("database.management"), getDatabaseManagementJPanel());
		addTab(DBSAResourceBundle.res.getString("insert.article"), getInsertArticleToDatabaseJPanel());
		addTab(DBSAResourceBundle.res.getString("statistic"), getDBSAStatisticPanel());
		setSize(900, 500);
	}

	public void updateTextsOfComponents(){
		setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("functions")));
		dbsaJTabbedPane.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("functions")));
		getDbsaJTabbedPane();
	}
	
	private JTabbedPane getDbsaJTabbedPane() {
		if (dbsaJTabbedPane == null) {
			dbsaJTabbedPane = new JTabbedPane();
			//dbsaJTabbedPane.setBorder(BorderFactory.createTitledBorder(DBSAResourceBundle.res.getString("functions")));
		}
		addTab(DBSAResourceBundle.res.getString("fetcher"), getFetcherJPanel());
		addTab(DBSAResourceBundle.res.getString("fetcher.results"),getFetcherResultPanel());	
		addTab(DBSAResourceBundle.res.getString("database.management"),getDatabaseManagementJPanel());
		addTab(DBSAResourceBundle.res.getString("insert.article"), getInsertArticleToDatabaseJPanel());
		addTab(DBSAResourceBundle.res.getString("statistic"), getDBSAStatisticPanel());
		
		return dbsaJTabbedPane;
	}

	private DatabaseManagementPanel getDatabaseManagementJPanel() {
		if (databaseManagementJPanel == null) {
			databaseManagementJPanel = new DatabaseManagementPanel(this);
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
	
	private DBSAStatisticPanel getDBSAStatisticPanel(){
		if(dbsaStatisticPanel == null){
			dbsaStatisticPanel = new DBSAStatisticPanel();
		}
		return dbsaStatisticPanel;
	}
}
