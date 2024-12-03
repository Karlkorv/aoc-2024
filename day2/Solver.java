import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
    int solution;

    void readInput(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
    }

    void parseInput() {
        for (String string : lines) {
            String[] splitStr = string.split("\\s+");
            levels.add(new ArrayList<>());
            for (String string2 : splitStr) {
                levels.get(levels.size() - 1).add(Integer.parseInt(string2));
            }
        }
    }

    void solve() {
        for (ArrayList<Integer> arrayList : levels) {
            if (validate(arrayList))
                solution++;
        }
    }

    boolean validate(ArrayList<Integer> arrayList) {
        // okej fuck detta jag brute forcear

        if (validateSubArray(arrayList)) {
            return true;
        }

        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Integer> subList = new ArrayList<>(arrayList);
            subList.remove(i);
            if (validateSubArray(subList)) {
                return true;
            }
        }
        return false;
    }

    boolean validateSubArray(ArrayList<Integer> subList) {
        boolean decreasing = subList.get(0) - subList.get(1) > 0;
        for (int i = 1; i < subList.size(); i++) {
            if (Math.abs(subList.get(i) - subList.get(i - 1)) > 3)
                return false;
            if (decreasing && subList.get(i) >= subList.get(i - 1))
                return false;
            if (!decreasing && subList.get(i) <= subList.get(i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        try {
            s.readInput("/Users/janneschyffert/Documents/Programmering/aoc-2024/day2/input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        s.parseInput();
        s.solve();

        System.err.println(s.solution);
    }
}