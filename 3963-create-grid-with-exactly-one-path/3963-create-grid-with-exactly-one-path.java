class Solution {
    public String[] createGrid(int m, int n) {
        String[] answer = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];

            for (int j = 0; j < n; j++) {
                row[j] = (i == 0 || j == n - 1) ? '.' : '#';
            }

            answer[i] = new String(row);
        }

        return answer;
    }
}