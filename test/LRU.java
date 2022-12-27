package test.test;

import java.util.*;

public class LRU implements CacheReplacementPolicy {
    // Hash set to store the cache words requests
    // key as Integer and Value as String
    private LinkedHashSet<String> wordcache = new LinkedHashSet<String>(); ;


    void add(String word){
        if (wordcache.contains(word))
            wordcache.remove(word);
        wordcache.add(word);



    }
    // return the word we need to remove from the set
    String remove(){
        wordcache.pollLast();


    }


}
