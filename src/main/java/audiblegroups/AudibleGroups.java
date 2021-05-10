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
