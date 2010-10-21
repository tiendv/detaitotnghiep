/**
 * 
 */
package uit.tkorg.dbsa.actions.fetchers;

import java.io.IOException;

/**
 * @author TKORG-PC1
 *
 */
public class JSTORFetcherAction {
	public static void Fetcher(String keyword) throws IOException{
		uit.tkorg.dbsa.cores.fetchers.JSTORFetcher.processQuery(keyword);
	}

}
