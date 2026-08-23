class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int cntL = 0;
        int cntR = 0;
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                cntL++;
            } else {
                sumDiff += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                cntR++;
            } else {
                sumDiff -= num.charAt(i) - '0';
            }
        }
        if ((cntL + cntR) % 2 != 0) {
            return true;
        }
        return 2 * sumDiff + 9 * (cntL - cntR) != 0;
    }
}