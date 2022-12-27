package test.test;
import java.util.*;


public class CacheManager {
    private final int maxsize;
    private final HashSet<String> cachedwords ;
    private final CacheReplacementPolicy crp;

    public CacheManager(int maxsize, CacheReplacementPolicy crptype) {
        this.maxsize = maxsize;
        this.cachedwords = new  HashSet<String>();
        this.crp = crptype;
    }

    public void add(String word) {
        if (cachedwords.size() == maxsize){
            String removedword = crp.remove();
            cachedwords.remove(removedword);
        }
        crp.add(word);
        cachedwords.add(word);

    }


}

