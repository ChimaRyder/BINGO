import java.util.List;

public abstract class BingoPattern implements Runnable{
    List<BingoChecker> BingoCheckers;
    BingoCard card;

    public BingoPattern(BingoCard card) {
        this.card = card;
    }

    @Override
    public void run() {
        Thread[] checkerRowThreads = new Thread[5];
        Thread[] checkerColumnThreads = new Thread[5];
        for (int j = 1; j <= 5; j++) {
            BingoCheckers.add(new BingoRowChecker(card, j));
            BingoCheckers.add(new BingoColumnChecker(card, j));
        }

        if (BingoGame.isBingo) {
            for (Thread thread: checkerRowThreads) {
                thread.interrupt();
            }

            for (Thread thread: checkerColumnThreads) {
                thread.interrupt();
            }
        }

    }
}
