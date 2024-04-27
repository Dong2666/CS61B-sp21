package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
        //        用环形数组实现的双端队列
//        数组可以直接调用length()方法查看长度
        private T[] array ;
        int nextFirst;
        int nextLast;
        int size;

        public ArrayDeque(int i) {
                //        creat a generic array by casting
                this.array = (T[]) new Object[i];
                nextFirst = 0;
                nextLast = 1;
                size = i;
        }
        public ArrayDeque() {
                this.array = (T[]) new Object[8];
                nextFirst = 3;
                nextLast = 4;
                size = 0;
        }

        private void ensureCapacity (){
//              数组判满的情况：下一个位置的已经有item了
//              如果此时的指针超限了会报错，因此在每个赋值后的结尾都要调用loop修正特殊情况下的指针，防止超限报错
                if (array[nextLast] == null || array[nextFirst] == null){
                        return;
                }
//                number 是扩容后的大小
                int number = (size * 2);
                T[] newArray  = (T[]) new Object[number];
                for (int i = 0; i < size; i++ ) {
//                        从nextFirst的后一个位置 i.e. current first开始，往后遍历
                        nextFirst = loop(nextFirst + 1) ;
                        newArray[i] = array[nextFirst];
                }
                this.array = newArray;
                nextLast = size;
                nextFirst = array.length - 1;
        }

        //      特殊情况：if next index is out of bound, move it to another side
        private int loop(int index){
                if(index == -1 ){
                        index = array.length -1 ;
                }
                if (index >= array.length ){
                        index %= array.length;
                }
                return index;
        }

        public void addFirst(T item){
                ensureCapacity();
                array[nextFirst] = item;
                nextFirst = loop(nextFirst - 1);
                size ++;
        }

        public void addLast(T item){
                ensureCapacity();
                array[nextLast] = item;
                nextLast = loop(nextLast + 1);
                size ++;
        }

        public boolean isEmpty() {
                return size == 0;
        }

        public int size(){
                return size;
        }

        public void printDeque(){
                int index = 0;
                for (int i = 0; i < size; i++ ) {
//                        从nextFirst的后一个位置 i.e. current first开始，往后遍历
                        index = loop(nextFirst + i + 1);
                        System.out.println( array[index] +" ");
                }
        }

        public T removeFirst(){
                nextFirst = loop(nextFirst +1);
                T item = array [nextFirst ];
                array[nextFirst] = null;
                size --;
                return item;
        }

        public T removeLast(){
                nextLast = loop(nextLast - 1);
                T item = array [nextLast];
                array[nextLast] = null;
                size --;
                return item;
        }

        public T get(int index){
                index = loop(index + 1 + nextFirst);
                return array[index];
        }

        public Iterator<T> iterator(){
                return new ArrayDequeIterator();
        }

        public boolean equals(Object o){
                ArrayDeque<T> array2 = (ArrayDeque<T>) o;
                if (this.size != array2.size){
                        return false;
                }
                for (int i = 0; i < size; i++) {
                        if (this.get(i) != array2.get(i)){
                                return false;
                        }
                }
                return true;
        }
        private class ArrayDequeIterator implements Iterator<T>{
                int index = 0;
                public boolean hasNext(){
                        return index < size;
                }
                public T next(){
                        if (!hasNext()){
                                throw new NoSuchElementException();
                        }
                        T item = array[index];
                        index += 1;
                        return item;
                }
        }
}
