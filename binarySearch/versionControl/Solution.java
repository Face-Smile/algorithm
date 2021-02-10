package algorithm.binarySearch.versionControl;

class VersionControl {
    private int bad;

    public boolean isBadVersion(int version) {
        return version >= bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }
}


public class Solution extends VersionControl {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setBad(2147483647);
        System.out.println(solution.firstBadVersion(2147483647));
    }

    public int firstBadVersion(int n) {
        int i = 0, j = n;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (isBadVersion(mid+1)) j = mid;
            else i = mid + 1;
        }
        if (i != n && isBadVersion(i+1)) return i+1;
        return -1;
    }

}
