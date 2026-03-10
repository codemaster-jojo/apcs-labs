import java.util.*;
/**
 * MyHashMap acts like a map.
 * 
 * @author Jonny Tang
 * @version 1/26/2026
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * a constructor for the hash map
     */
    public MyHashMap()
    {
        size = 0;
        
        buckets = new LinkedList[NUM_BUCKETS];
        
        for (int i=0; i<NUM_BUCKETS; i++)
        {
            buckets[i] = new LinkedList<MapEntry<K, V>>();
        }
    }
    
    /**
     * Gets index of key
     * @param obj
     *            the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }

    /**
     * Gets the size of the hash map
     * @return size
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether or not the hash map is empty
     * @return whether or not the hash map is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Checks whether the hash map contains 
     * anything with given key
     * 
     * @param key the key getting checked
     * 
     * @return true or false, read description
     */
    @Override
    public boolean containsKey(Object key)
    {
        int index = toBucketIndex(key);
        for (MapEntry<K, V> entry : buckets[index]) 
        {
            if (entry.getKey().equals(key)) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the hash map contains 
     * anything with given value
     * 
     * @param value the value thats getting checked
     * 
     * @return true or false, read description
     */
    
    public boolean containsValue(Object value)
    {
        for (int i=0; i<NUM_BUCKETS; i++)
        {
            for (MapEntry<K, V> entry : buckets[i])
            {
                if (entry.getValue().equals(value))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the object of given key. 
     * If no key, returns null
     * @param key the key
     * @return the value of the object with key key
     */
    public V get(Object key)
    {
        int index = toBucketIndex(key);
        
        for (MapEntry<K, V> entry : buckets[index])
        {
            if (entry.getKey().equals(key))
            {
                return entry.getValue();
            }
        }

        return null;
    }

    /**
     * Puts the key value pair into the map
     * Returns the value
     * 
     * @param key the key
     * @param value the value
     * 
     * @return null if no value with given key,
     *     or the old value
     */
    public V put(K key, V value)
    {
        int index = toBucketIndex(key);
        V currVal = get(key);
        if (currVal == null)
        {
            buckets[index].add(new MapEntry<K, V>(key, value));
            size++;
            return null;
        }
        else
        {
            replace(key, value);
            return currVal;
        }
    }

    /**
     * Removes the key value pair from the map
     * 
     * @param key the map entry
     * @return the value that got deleted
     */
    public V remove(Object key)
    {
        int index = toBucketIndex(key);
        MapEntry deleting = null; 
        for (MapEntry entry : buckets[index])
        {
            if (entry.getKey().equals(key))
            {
                // cannot remove here b/c will mess with for each loop
                // also there can only be one so I dont need to check that
                deleting = entry;
            }
        }
        
        if (deleting != null)
        {
            buckets[index].remove(deleting);
            size--;
            return (V) deleting.getValue();
        }
        
        return null;
    }

    /**
     * Puts all key value pairs into buckets
     * @param m the entries
     */
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    /**
     * Clears the buckets
     */
    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = new LinkedList<MapEntry<K, V>>();
        }
        size = 0;
    }

    /**
     * Returns a set of all keys
     * @return the set of all keys
     */
    public Set<K> keySet()
    {
        Set<K> set = new HashSet<K>();
        for (int i=0; i<NUM_BUCKETS; i++)
        {
            for (MapEntry<K, V> entry : buckets[i])
            {
                set.add(entry.getKey());
            }
        }
        
        return set;
    }

    /**
     * Returns a collection of all values
     * @return the array list of values
     */
    public Collection<V> values()
    {
        Collection<V> values = new ArrayList<V>();
        
        for (int i=0; i<NUM_BUCKETS; i++)
        {
            for (MapEntry<K, V> entry : buckets[i])
            {
                values.add(entry.getValue());
            }
        }
        
        return values;
    }

    /**
     * Returns a set of all entries (key value pair)
     * 
     * @return the set of entries
     */
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
        Set<Map.Entry<K, V>> entries = new HashSet<Map.Entry<K, V>>();
        for (int i=0; i<NUM_BUCKETS; i++)
        {
            for (MapEntry<K, V> entry : buckets[i])
            {
                entries.add(entry);
            }
        }
        
        return entries;
    }
    
    /**
     * Replaces object with key with value
     * @param key the key
     * @param value the value
     * @return the old value
     */
    public V replace(K key, V value)
    {
        int index = toBucketIndex(key);
        for (MapEntry<K, V> entry : buckets[index])
        {
            if (entry.getKey().equals(key))
            {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        return null;
    }

    
    /**
     * To string override
     * 
     * @return the string
     */
    @Override
    public String toString()
    {
        return entrySet().toString();
    }
}