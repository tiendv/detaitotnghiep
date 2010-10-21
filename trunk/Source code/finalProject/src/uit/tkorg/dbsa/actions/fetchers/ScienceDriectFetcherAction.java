/**
 * 
 */
package uit.tkorg.dbsa.actions.fetchers;

import java.io.IOException;

/**
 * @author tiendv
 *
 */
public class ScienceDriectFetcherAction {
	public static void Fetcher(String keyword) throws IOException{
		uit.tkorg.dbsa.cores.fetchers.ScienceDirectFetcher.processQuery1(keyword);
	}
}
