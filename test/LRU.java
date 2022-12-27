package test.test;

import java.util.*;

public class LRU implements CacheReplacementPolicy {
    // Hash set to store the cache words requests
    private final LinkedHashSet<String> wordcache = new LinkedHashSet<String>();

    public void add(String word) {
        if (wordcache.contains(word))
            wordcache.remove(word);
        wordcache.add(word);
    }
    // Return and remove the last word of the hashset
    public String remove() {
        // Obtain iterator for the set
        Iterator<String> itr = this.wordcache.iterator();
        // Navigate to the last element in the set
        String lastElement = null;
        while (itr.hasNext()) {
            lastElement = itr.next();
        }
        itr.remove();
        return lastElement;
    }
}
