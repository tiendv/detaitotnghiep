/**
 * 
 */
package uit.tkorg.dbsa.actions.fetchers;

import uit.tkorg.dbsa.cores.fetchers.CiteSeerXFetcher;

/**
 * @author tiendv
 *
 */
public class CiteSeerXFetcherAction {
	public static void Fetcher ( String keyword ){
	CiteSeerXFetcher fetcher = new CiteSeerXFetcher();
	fetcher.processQuery(keyword);
	}

}
