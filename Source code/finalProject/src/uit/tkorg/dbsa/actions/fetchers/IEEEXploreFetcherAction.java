/**
 * 
 */
package uit.tkorg.dbsa.actions.fetchers;

import java.io.IOException;
import java.util.ArrayList;

import uit.tkorg.dbsa.model.DBSAPublication;

/**
 * @author TKORG-PC1
 *
 */
public class IEEEXploreFetcherAction {
	public static void Fetcher(String keyword) throws IOException{
		uit.tkorg.dbsa.cores.fetchers.IEEEXploreFetcher.processQuery(keyword);
	
	}
}
