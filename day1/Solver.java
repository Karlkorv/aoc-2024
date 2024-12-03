import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solver {
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<Integer> numbersLeft = new ArrayList<>();
    ArrayList<Integer> numbersRight = new ArrayList<>();
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
            numbersLeft.add(Integer.parseInt(splitStr[0]));
            numbersRight.add(Integer.parseInt(splitStr[1]));
        }
    }

    void solve() {
        HashMap<Integer, Integer> rightMap = new HashMap<>();

        for (int i = 0; i < numbersLeft.size(); i++) {
            rightMap.put(numbersRight.get(i), rightMap.getOrDefault(numbersRight.get(i), 0) + 1);
        }

        for (int i = 0; i < numbersLeft.size(); i++) {
            solution += numbersLeft.get(i) * rightMap.getOrDefault(numbersLeft.get(i), 0);
        }
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        try {
            s.readInput("/Users/janneschyffert/Documents/Programmering/aoc-2024/day1/input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        s.parseInput();
        s.solve();

        System.err.println(s.solution);
    }
}