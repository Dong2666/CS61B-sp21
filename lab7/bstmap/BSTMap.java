package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{

    //    节点类，key is conparable
    private class BSTNode{
        BSTNode left, right;
        K key;
        V val;

        //        构造方法，初始化key和val，左右子树为null
        BSTNode(K k, V v) {
            key = k;
            val = v;
            left = null;
            right = null;
        }
        //        recursion，通过比较大小来get key
        BSTNode getNode(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
//            if (left == null && right == null) {
//                return null;
//            }
            if (k.compareTo(key) > 0){
                return getNode(right.key);
            }else {
                return getNode(left.key);
            }
        }
    }

    //root 是树的根
    private BSTNode root;
    int size = 0;

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if (root == null){
            return null;
        }
        BSTNode node = root.getNode(key);
        if (node == null){
            return null;
        }
        return node.val;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (root == null){
            return false;
        }
        return root.getNode(key) != null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    @Override
    public void clear(){
        size = 0;
        root = null;
    }


    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        size ++;
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K k, V v){
        BSTNode newNode = new BSTNode(k, v);
//            BSTNode pointer = new BSTNode(root.key, root.val);
        if (node == null) {
            return newNode;
        }
        if (k.compareTo(node.key) > 0){
            node.right = put(node.right, k, v);
        } else {
            node.left = put(node.left, k, v);
        }
        return node;
    }

/* Returns a Set view of the keys contained in this map. Not required for Lab 7.
 * If you don't implement this, throw an UnsupportedOperationException. */
@Override
public Set<K> keySet(){
        throw new UnsupportedOperationException();
        }

/* Removes the mapping for the specified key from this map if present.
 * Not required for Lab 7. If you don't implement this, throw an
 * UnsupportedOperationException. */
@Override
public V remove(K key){
        throw new UnsupportedOperationException();
        }

/* Removes the entry for the specified key only if it is currently mapped to
 * the specified value. Not required for Lab 7. If you don't implement this,
 * throw an UnsupportedOperationException.*/
@Override
public V remove(K key, V value){
        throw new UnsupportedOperationException();
        }

@Override
public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
        }


        }
