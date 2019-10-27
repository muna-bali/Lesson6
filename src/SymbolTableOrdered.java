import java.util.LinkedList;
import java.util.Queue;

public class SymbolTableOrdered<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public SymbolTableOrdered() {
        keys = (Key[]) new Comparable[2];
        vals = (Value[]) new Object[2];
    }

    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val)  {
        if (key == null || val==null)
            throw new IllegalArgumentException();

        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (n == keys.length)
            resize(2*keys.length);

        for (int j = n; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;

    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return;

        int i = rank(key);

        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length/4)
            resize(keys.length/2);

    }

     public Key min()   {
        if (isEmpty()){
            throw new IndexOutOfBoundsException() ;
        }
        return keys[0];
     }                 // return minimum key

    public Key max(){ if (isEmpty()){
        throw new IndexOutOfBoundsException() ;
    }
    return keys[n-1];}                    // return maximum key

    public Key select(int k){
        if (k >=n || k<0)
            throw new IndexOutOfBoundsException() ;
            return keys[k];}            // return key of rank k

    public void deleteMin(){
        if (isEmpty()){
return;}
            //delete(keys[0]);
    // next time i should just create the loop as this solution is not very efficient
        for (int j = 0; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

    }             // delete min key

    public void deleteMax(){//
        // delete(keys[n-1]);
        if (isEmpty()){
            return;}
        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

    }             // delete max key

    public Key floor(Key key){
        if (key == null)
            throw new IllegalArgumentException();
        int x =rank(key);
        if(keys[x].compareTo(key)==0){return key;}
        else if(x>0)return keys[x-1];
        else return null;
    }           // return largest Key <= key

    Key ceiling(Key key)  {
        if (key == null)
            throw new IllegalArgumentException();
        int x =rank(key);
        if(keys[x].compareTo(key)==0){return key;}
        else if(x<n-1)return keys[x];
        else return null;

    }       // return smallest key >= key

    public int size(Key lo, Key hi){
        if (lo == null || hi == null)
            throw new IllegalArgumentException();
        int rankHi = rank(hi);
        if (keys[rankHi].equals(hi)== false){rankHi--;}
        int rankLo= rank(lo);
        int ans = rankHi-rankLo+1;
        if (ans <0) ans = ans*-1;
        return ans;
    }     // return number of keys between hi and lo, inclusive

     public Iterable<Key> keys(){

         Queue<Key> queue = new LinkedList<Key>();
        for ( int i =0; i<n;i++){

            queue.add(keys[i]);

        }
return queue;
     }

}