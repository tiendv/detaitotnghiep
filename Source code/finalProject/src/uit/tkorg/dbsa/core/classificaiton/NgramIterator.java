package uit.tkorg.dbsa.core.classificaiton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author tiendv
 *
 *To Token a string by use n gram
 */


class NgramIterator implements Iterator<String> {

    String[] words;
    int pos = 0, n;

    public NgramIterator(int n, String str) {
        this.n = n;
        words = str.split(" ");
    }

    public boolean hasNext() {
        return pos < words.length - n + 1;
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < pos + n; i++)
            sb.append((i > pos ? " " : "") + words[i]);
        pos++;
        return sb.toString();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
    /**
     * 
     * @param n : the mount of charactor want to token
     * @param str : a string to token
     * @return
     * ex: string "this is my car"
     *  n = 2
     *  return: this is, is my, my car
     */
    
    public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }
}
