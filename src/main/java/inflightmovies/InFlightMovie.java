package inflightmovies;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class InFlightMovie {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int flightDuration = Integer.parseInt(bufferedReader.readLine().trim());

        int movieDurationCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> movieDuration = IntStream.range(0, movieDurationCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.foo(flightDuration, movieDuration);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class Result {

        /*
         * Complete the 'foo' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER flightDuration
         *  2. INTEGER_ARRAY movieDuration
         */

        public static List<Integer> foo(int flightDuration, List<Integer> movieDuration) {
            List<Integer> pair = new ArrayList<>();
            pair.add(-1);
            pair.add(-1);

            int targetTime = flightDuration - 30;
            int longestMovieDuration = -1;
            int numMovies = movieDuration.size();

            for (int i = 0; i < numMovies; i++) {
                int curMovieDuration = movieDuration.get(i);
                for (int j = (i + 1); j < numMovies; j++) {
                    int otherMovieDuration = movieDuration.get(j);
                    if ((curMovieDuration + otherMovieDuration) == targetTime) {
                        int pairLongestDuration = Math.max(curMovieDuration, otherMovieDuration);
                        if (pairLongestDuration > longestMovieDuration) {
                            longestMovieDuration = pairLongestDuration;
                            pair.clear();
                            pair.add(i);
                            pair.add(j);
                        }
                    }
                }
            }

            return pair;
        }

    }

}
