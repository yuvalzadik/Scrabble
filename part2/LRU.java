package test;
import java.util.*;

public class LRU implements CacheReplacementPolicy {
    // Hash set to store the cache words requests
    // key as Integer and Value as String
    private LinkedHashSet<String> wordcache ;

    public LRU(){
        this.wordcache= new LinkedHashSet<String>();
    }


    void add(String word){
        if (wordcache.contains)

    }

    String remove(){
        int keyRemoved = this.order.getLast();


    }


}
