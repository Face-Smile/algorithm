package algorithm.binarySearch.guessGame;

class GuessGame {
    private int pick;

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public int guess(int num) {
        return Integer.compare(pick, num);
    }
}

public class Solution extends GuessGame {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setPick(10);
        System.out.println(solution.guessNumber(10));
    }
    public int guessNumber(int n) {
        int i = 0, j = n;
        int mid, g;
        while (i <= j) {
            mid = i + ((j - i) >> 1);
            g = guess(mid);
            if (g == 1) i = mid + 1;
            else if (g == -1) j = mid - 1;
            else return mid;
        }
        return -1;
    }
}
