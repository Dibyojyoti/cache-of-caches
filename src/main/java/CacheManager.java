import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/** manages a set of caches where each instance of cache belongs to exactly one entity=manager
*   @param <K> - type of key
*   @param <V> - type of cache object
*/
public class CacheManager<K,V> {
  public static final int maxSize = 1024;
  protected final Cache<String , Cache<K,V>> cacheMap;
  private boolean isWeekKeys;

  public CacheManager() { this(false);}
  public CacheManager(boolean isWeekKeys) { this. isWeekKeys = isWeekKeys ;}

  /** returns a user/manager specific instance of cache
  *  @param entityId user/manager
  */
  public Cache<K,V> getEntityCache(String entityId) {
    Cache<K,V> entityCache = cacheMap.getIfpresent( entityId );
    if( entityCache == null) {
       entityId =  entityId.intern() //do not remove inturn()
       synchronized(entityId) {
           entityCache = cacheMap.getIfPresent(entityId);
           if( entityCache == null ) {
              final CacheBuilder builder =  CacheBuilder.newbuilder().maximumsize(256);
              if(isWeekKeys) {
                  builder.weakKeys();
              }
              entityCache = builder.build();
              cacheMap.put(entityId, entityCache); 
           }
       }
    }
    return  entityCache;
  } 
}
