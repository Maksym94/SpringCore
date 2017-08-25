package classes;

import interfaces.EventLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym_Petrenko on 8/25/2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache= new ArrayList<Event>();

    public CacheFileEventLogger(String filename,int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size()==cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void destroy(){
        if(!cache.isEmpty()){
           writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        for (Event event:cache) {
            super.logEvent(event);
        }
    }
}
