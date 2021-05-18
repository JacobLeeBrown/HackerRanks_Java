package audiblegroups;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AudibleGroups {

    /*
     * Given the number of users, n, and the n*n map of relationships between the users, determine the number of
     * distinct groups, where a group is defined as all users that directly, or transitively, are related.
     *
     * For each cell, <i,j>, of the map, 0 means the i-th user is not related to user j, and 1 means they are related
     * in some way. The map will always have 1 where i=j, since each user knows themself.
     *
     * Ex: Given 3 total users where user 1 is related to user 2 and neither are related to user 0, the input is
     *
     * 3
     * 100
     * 011
     * 011
     *
     * Thus, there are 2 distinct groups: user 0 is their own group, while users 1 and 2 are part of the 2nd group.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int relatedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> related = IntStream.range(0, relatedCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int result = Result.countGroups(related);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class Result {

        /*
         * Complete the 'countGroups' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING_ARRAY related as parameter.
         */

        public static int countGroups(List<String> related) {
            List<Set<Integer>> groups = new ArrayList<>();
            Map<Integer, Integer> userToGroup = new HashMap<>();

            for (int i = 0; i < related.size(); i++) {
                String curRelations = related.get(i);
                System.out.println(curRelations);
                boolean isAlone = true;
                for (int j = 0; j < curRelations.length(); j++) {
                    if ((i != j) && (curRelations.charAt(j) == '1')) {
                        // User i knows other user j
                        isAlone = false; // i is not alone in this sad, cruel world
                        if(!userToGroup.containsKey(i)) {
                            // This is the first pair for user i
                            Set<Integer> newGroup = new HashSet<>();
                            newGroup.add(i);
                            newGroup.add(j);
                            int newGroupIdx = groups.size();
                            groups.add(newGroup);

                            userToGroup.put(i, newGroupIdx);
                            userToGroup.put(j, newGroupIdx);
                        }
                        else {
                            int groupIdx = userToGroup.get(i);
                            Set<Integer> group = groups.get(groupIdx);
                            group.add(j);
                            groups.set(groupIdx, group);
                            userToGroup.put(j, groupIdx);
                        }
                    }
                }
                if(isAlone) {
                    // user i just has their lonely self
                    Set<Integer> newGroup = new HashSet<>();
                    newGroup.add(i);
                    int newGroupIdx = groups.size();
                    groups.add(newGroup);

                    userToGroup.put(i, newGroupIdx);
                }
            }
            return groups.size();
        }

    }

}
