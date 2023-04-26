import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SumTest {

    public static void main(String[] args) throws InterruptedException {
        // finalResult = 499999995000000000
        //sum =          499999999500000000, time ms: 8036
//        long start = System.currentTimeMillis();
//        BigDecimal sum = BigDecimal.ZERO;
//
//        for (int i = 0; i < 1_000_000_000; i++) {
//            sum = sum.add(BigDecimal.valueOf(i));
//        }
//
//        long duration = System.currentTimeMillis() - start;
//        System.out.println("sum = " + sum + ", time ms: " + duration);

        long startTime = System.currentTimeMillis();

        //1_000_000_00
        long[][] startFinish = {
                {0, 1_000_000_00},
                {1_000_000_01, 2_000_000_00},
                {2_000_000_01, 3_000_000_00},
                {3_000_000_01, 4_000_000_00},
                {4_000_000_01, 5_000_000_00},
                {5_000_000_01, 6_000_000_00},
                {6_000_000_01, 7_000_000_00},
                {7_000_000_01, 8_000_000_00},
                {8_000_000_01, 9_000_000_00},
                {9_000_000_01, 1_000_000_000}
        };

        List<BigDecimal> results = new CopyOnWriteArrayList<>();

        List<Thread> threads = new ArrayList<>();

        for (long[] pair : startFinish) {
            long start = pair[0];
            long end = pair[1];

            SumThread thread = new SumThread(
                    BigDecimal.valueOf(start),
                    BigDecimal.valueOf(end),
                    results
            );
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        BigDecimal finalResult = BigDecimal.ZERO;
        for (BigDecimal subSum : results) {
            finalResult = finalResult.add(subSum);
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("sum = " + finalResult + ", time ms: " + duration);

    }

    private static class SumThread extends Thread {
        private BigDecimal start;
        private BigDecimal end;

        private List<BigDecimal> result;

        public SumThread(BigDecimal start, BigDecimal end, List<BigDecimal> result) {
            this.start = start;
            this.end = end;
            this.result = result;
        }

        @Override
        public void run() {
            long tmpSum = start.longValue();

            long startLong = start.longValue();
            long endLong = end.longValue();

//            for(long i = startLong + 1; i < endLong; i++) {
//                tmpSum += i;
//            }

            BigDecimal sum = BigDecimal.ZERO;


            for(long i = startLong + 1; i < endLong; i++) {
                sum = sum.add(BigDecimal.valueOf(i));
            }

            result.add(sum);
        }
    }

}
