package deque;

import java.util.Iterator;
import java.util.LinkedList;

//双端队列可以可以从两头增减项目，两端都可以进出的队列
//用双向链表实现
public class LinkedListDeque<T> implements Deque<T>{

    Node<T> head;


    private static class Node<T>{
        T item;
        Node<T> prev;
        Node<T> next;

        Node( Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque(){

    }

    @Override
    public void addFirst(T item){

    }
    public void addLast(T item)
    public boolean isEmpty()
    public boolean isEmpty()
    public void printDeque()
    public T removeFirst()
    public T removeLast()
    public T get(int index)
    public T getRecursive(int index)
    public Iterator<T> iterator()
    public boolean equals(Object o)
}