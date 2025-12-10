class Solution {

    public int vowelStrings(String[] words, int left, int right) {

        int count = 0;

        ArrayList<Character> charList = new ArrayList<>();

        char[] charArray = { 'a', 'e', 'i', 'o', 'u' };

        for (char c : charArray) {

            charList.add(c);

        }

        for (int i = left; i <= right; i++) {

            int s = words[i].length();

            if (charList.contains(words[i].charAt(0)) && charList.contains(words[i].charAt(s - 1))) {

                count++;

            }

        }

        return count;

    }

}

