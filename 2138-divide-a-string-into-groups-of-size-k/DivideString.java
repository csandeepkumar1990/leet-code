// 2138. Divide a String Into Groups of Size k
// Divide string s into groups of size k, padding the last group with fill character if needed.

class Solution {

    public String[] divideString(String s, int k, char fill) {

        List<String> groups = new ArrayList<>();

        // Iterate through string in steps of k
        for (int i = 0; i < s.length(); i += k) {

            int end = Math.min(i + k, s.length());

            String group = s.substring(i, end);

            // If last group is shorter, pad with fill character
            if (group.length() < k) {

                StringBuilder sb = new StringBuilder(group);

                while (sb.length() < k) {

                    sb.append(fill);

                }

                group = sb.toString();

            }

            groups.add(group);

        }

        // Convert list to array and return
        return groups.toArray(new String[0]);

    }

}

