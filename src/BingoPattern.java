import java.util.List;

public abstract class BingoPattern implements Runnable{
    List<BingoChecker> BingoCheckers;
    BingoCard card;

    public BingoPattern(BingoCard card) {
        this.card = card;
    }

    @Override
    public void run() {
//        for (BingoChecker bc : BingoCheckers) {
//            bc = new Thread(new BingoChecker());
//        }
//
//        for (BingoChecker bc : BingoCheckers) {
//            bc.start();
//        }
    }
}
