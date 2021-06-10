import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class Demo {

    private static final int PACKAGE_SIZE = 7;

    private static Callable myCallable(CopyOnWriteArrayList<ArrayList<Integer>> a, int d) {
        return () -> {
            LocalTime localTime = LocalTime.now();
            System.out.println("Started:" + " " + Thread.currentThread().getName() + " " + localTime);
            Map<Integer, Integer> cc = new HashMap<>();
            for (int i = 0; i < a.get(a.size() - 1).size(); i++) {
                if (d % a.get(a.size() - 1).get(i) == 0) {
                    cc.put(a.get(a.size() - 1).get(i), d / a.get(a.size() - 1).get(i));
                }
            }
            a.remove(a.size() - 1);
            return cc.isEmpty() ? null : cc;
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите значение для расчета");
        int dividend = in.nextInt();
        System.out.println("Введите желаемое количество потоков");
        int threadnumber = in.nextInt();


        ArrayList<Object> actuals = new ArrayList<>();
        CopyOnWriteArrayList<ArrayList<Integer>> threadsafe = PackageCreator.divide(dividend, PACKAGE_SIZE);

        System.out.println(threadsafe);

        ExecutorService executorService = Executors.newFixedThreadPool(threadnumber);

        while (!threadsafe.isEmpty()) {
            Future future = executorService.submit(myCallable(threadsafe, dividend));
            actuals.add(future.get());
        }
        executorService.shutdown();
        actuals.removeIf(Objects::isNull);
        System.out.println(actuals);
    }

}
