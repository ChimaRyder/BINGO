public class BingoRowChecker extends BingoChecker{
    int row;

    public BingoRowChecker(BingoCard card, int row) {
        super(card);
        this.row = row-1;
    }

    @Override
    public void run() {
        for (int col = 0; col  < 5; col++) {
            int num = card.nums[row][col];
            while (!BingoGame.result[num]) {
                try {
                    synchronized (BingoGame.result){
                        BingoGame.result.wait();
                    }
                } catch (InterruptedException e) {

                }
            }
        }

        System.out.println("\nCard " + card.id + " done at row " + (row+1) +": \n" + card);
        BingoGame.isBingo = true;
    }
}
