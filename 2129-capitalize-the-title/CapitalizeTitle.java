// 2129. Capitalize the Title
// Capitalize words with more than 2 characters, keep others lowercase.

class Solution {

    public String capitalizeTitle(String title) {

        String[] words = title.split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            // Convert word to lowercase first
            String word = words[i].toLowerCase();

            if (word.length() > 2) {

                // Capitalize first letter, rest stay lowercase
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1);

            }

            // Add space before word (except first word)
            if (i > 0) result.append(" ");

            result.append(word);

        }

        return result.toString();

    }

}

