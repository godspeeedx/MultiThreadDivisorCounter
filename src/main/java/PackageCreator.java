import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PackageCreator {

    public static CopyOnWriteArrayList<ArrayList<Integer>> divide(int n, int b) {
        CopyOnWriteArrayList<ArrayList<Integer>> a = new CopyOnWriteArrayList<>();
        while (n > 0) {
            ArrayList<Integer> c = new ArrayList<>();
            int i = 0;
            while (i < b && (n - i) > 0) {
                c.add(n - i);
                i++;
            }
            n -= b;
            a.add(c);
        }
        return a;
    }
}
