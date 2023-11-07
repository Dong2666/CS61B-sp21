package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
@Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> fList = new BuggyAList<>();
        AListNoResizing<Integer> tList = new AListNoResizing<>();
//        add three
        for (int i = 0; i < 3; i++) {
            fList.addLast(i + 4);
            tList.addLast(i + 4);
        }
//        remove three
        for (int i = 0; i < 3; i++) {
            assertEquals(tList.removeLast(), fList.removeLast());
        }
    }
    @Test
    public void randomTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            int size = L.size();
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                size = R.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2 && size > 0) {
                System.out.println("getLast" + L.getLast());
                System.out.println("getlast" + R.getLast());
                assertEquals(L.getLast(), R.getLast());
            }else if ( operationNumber == 3 && size > 0){
                assertEquals(L.removeLast(), R.removeLast());
                System.out.println("removeLast");
            }
        }
    }
}
