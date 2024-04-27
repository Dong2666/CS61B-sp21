package deque;


import java.util.Iterator;

//双端队列可以可以从两头增减项目，两端都可以进出的队列。用双向链表with循环哨兵

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private Node<T> sentinel;
    private int size;

    private class Node<T>{
        T item;
        Node<T> prev;
        Node<T> next;

        //        initialize a node
        Node(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }
    //  initialize LLD with a circle sentinel
    public LinkedListDeque(){
        sentinel = new Node<>(null);
        sentinel.prev = sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public void addFirst(T item){
//        整体顺序：先修改新节点(first)和老节点(sentinel.next)的双向连接，再修改sentinel和新节点的连接
//        局部顺序：先节点后哨兵，先去后回
        Node<T> first = new Node<>(item);
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        first.prev = sentinel;
        size++;
    }
    public void addLast(T item){
        Node<T> last = new Node<>(item);
        last.prev = sentinel.prev;
        sentinel.prev.next = last;
        sentinel.prev = last;
        last.next = sentinel;
        size++;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    public void printDeque(){

    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size --;
        return item;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size --;
        return item;
    }
    public T get(int index){
        Node<T> node;
        if (index > size){
            return null;
        }
        if (index < size / 2){
            node = sentinel.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            node = sentinel.prev;
            for (int i = 0; i < size - index; i++) {
                node = node.prev;
            }
        }
        return node.item;
    }

//  每次递归index减一，head指向下一个node，在新的链表中接着调用。
    public T getRecursive(int index){
        if ( index > size - 1){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
    public T getRecursiveHelper(int index, Node<T> node) {
        if (index == 0){
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public boolean equals(Object o){
//        需要将object cast为一个本类
        if (o == null){
            return false;
        }
        if (o == this){
            return true;
        }
        if (!(o instanceof Deque)){
            return false;
        }
//        用for迭代
        LinkedListDeque<T> lld = (LinkedListDeque<T>)o;
        if (lld.size != this.size){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != this.get(i)){
                return false;
            }
        }
        return true;
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> current = sentinel.next;

        public boolean hasNext(){
            return current.next.item != null;
        }

        public T next(){
            if (hasNext()){
                T item = current.item;
                current = current.next;
                return item;
            }
            return null;
        }
    }

}