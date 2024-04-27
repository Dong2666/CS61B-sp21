package deque;

import java.util.Comparator;

//加了个max()方法，可以返回最大值
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        comparator = c;
    }

    public T max(){
        return max(comparator);
    }

    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0){
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
