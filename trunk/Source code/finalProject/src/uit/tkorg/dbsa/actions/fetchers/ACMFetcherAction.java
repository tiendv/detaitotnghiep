package uit.tkorg.dbsa.actions.fetchers;

import java.io.IOException;

public class ACMFetcherAction {
	public static void Fetcher(String keyword) throws IOException{
		uit.tkorg.dbsa.cores.fetchers.ACMFetcher.Fetcher(keyword);
	}
}
