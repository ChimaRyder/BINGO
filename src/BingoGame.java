import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable {
    List<BingoCard> cards;
    static boolean[] result = new boolean[76];
    static boolean isBingo = false;


    @Override
    public void run() {
        //stuff to do before it starts
        Scanner sc = new Scanner(System.in);
        result[0] = true;
        for (int i = 1; i <= 75; i++) {
            result[i] = false;
        }

        System.out.println("How many players?");
        int cnt = sc.nextInt();

        cards = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            cards.add(new BingoCard(i+1));
        }

        for (BingoCard card : cards) {
            System.out.println("Card "+ card.id);
            System.out.println(card);
        }

        Thread[] checkerRowThreads = new Thread[cnt];
        for(int i = 0; i < cnt; i++) {
            for (int j = 1; j <= 5; j++) {
                checkerRowThreads[i] = new Thread(new BingoRowChecker(cards.get(i), j));
                checkerRowThreads[i].start();
            }
        }

        Thread[] checkerColumnThreads = new Thread[cnt];
        for(int i = 0; i < cnt; i++) {
            for (int j = 1; j <= 5; j++) {
                checkerColumnThreads[i] = new Thread(new BingoColumnChecker(cards.get(i), j));
                checkerColumnThreads[i].start();
            }
        }

        // TODO randomly get number from 1-75 while not bingo

        while (!isBingo) {
            Random rnd = new Random();
            int bingoNum;

            do {
                bingoNum = rnd.nextInt(1, 76);
            } while (result[bingoNum]);

            System.out.print(bingoNum + " ");
            result[bingoNum] = true;
            synchronized (result) {
                result.notifyAll();
            }


            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (isBingo) {
            for (Thread thread: checkerRowThreads) {
                thread.interrupt();
            }

            for (Thread thread: checkerColumnThreads) {
                thread.interrupt();
            }

            System.exit(0);
        }
    }
}
