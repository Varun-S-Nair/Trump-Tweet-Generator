import java.io.FileWriter;
import java.io.IOException;
public class TextGenerator {
    int kgrams = 0;
    int generated = 0;
    String phrase = "";

    public TextGenerator(int k, int t, String p){
        kgrams = k;
        generated = t;
        phrase = p;

        MarkovModel a = new MarkovModel(phrase, kgrams);
        String output = phrase.substring(0, kgrams);
        for(int i = kgrams; i < generated; i++)
            output += a.random(output.substring(output.length()-kgrams));
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        StdOut.println(output);
    }

    public static void main(String[] args) {
        String[] input = StdIn.readAllLines();
        String inputString = "";
        for(String i : input)
            inputString += i + "\n";
        TextGenerator a = new TextGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]), inputString);

    }
}
