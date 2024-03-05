import java.util.Random;

public class BingoCard {
    int[][] nums;
    int id;

    public BingoCard(int id) {
        this.id = id;
        nums = new int[5][5];
        //todo randomize nums
        Random rnd = new Random();

        int min = 1;
        int max = 15; //min and max for each column, intial at 1 to 15 for rand
        int[] checknum = new int[76];

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                //col 1: 1-15
                //col 2: 16-30
                //col 3: 31-45 (middle is 0)
                //col 4: 46-60
                //col 5: 61-75

                if (row == 2 && col == 2) {
                    nums[row][col] = 0;
                } else {
                    int bingonum;
                    do {
                        bingonum = rnd.nextInt(min, max+1);
                    } while (checknum[bingonum] == bingonum);

                    nums[row][col] = bingonum;
                    checknum[bingonum] = bingonum;
                    min += 15;
                    max += 15;

                }
            }

            min = 1;
            max = 15;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                sb.append(nums[row][col]).append("\t");

            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
