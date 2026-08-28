class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
        }

        int[] curHalf = half.clone();
        boolean canMatchFirstHalf = true;
        for (int i = 0; i < halfLen; i++) {
            int ch = target.charAt(i) - 'a';
            if (curHalf[ch] > 0) {
                curHalf[ch]--;
            } else {
                canMatchFirstHalf = false;
                break;
            }
        }

        if (canMatchFirstHalf) {
            char[] p = new char[n];
            for (int i = 0; i < halfLen; i++) {
                p[i] = target.charAt(i);
                p[n - 1 - i] = target.charAt(i);
            }
            if (n % 2 != 0) {
                p[halfLen] = (char) ('a' + oddChar);
            }
            String cand = new String(p);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }

        for (int i = halfLen - 1; i >= 0; i--) {
            int[] tempHalf = half.clone();
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int ch = target.charAt(j) - 'a';
                if (tempHalf[ch] > 0) {
                    tempHalf[ch]--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (tempHalf[c] > 0) {
                    tempHalf[c]--;

                    char[] p = new char[n];
                    for (int j = 0; j < i; j++) {
                        p[j] = target.charAt(j);
                        p[n - 1 - j] = target.charAt(j);
                    }

                    p[i] = (char) ('a' + c);
                    p[n - 1 - i] = (char) ('a' + c);

                    int idx = i + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (tempHalf[ch] > 0) {
                            p[idx] = (char) ('a' + ch);
                            p[n - 1 - idx] = (char) ('a' + ch);
                            idx++;
                            tempHalf[ch]--;
                        }
                    }

                    if (n % 2 != 0) {
                        p[halfLen] = (char) ('a' + oddChar);
                    }

                    return new String(p);
                }
            }
        }

        return "";
    }
}