import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomText {
    Random r;
    String dictionary;
    List<String> dic;
    char[] specialChar = {',', '.', '!', '\'', '?', '\"', ':'};

    public GenerateRandomText(String dictionary) {
        this.dictionary = dictionary;
        dic = loadDictionary();
        r = new Random();
    }

    public List <String> loadDictionary(){
        List<String> dic = new ArrayList<>();
        try (BufferedReader reader= new BufferedReader(new FileReader(dictionary))){
            String line;
            while ((line = reader.readLine()) != null){
                dic.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dic;

    }

    public String getRandomWord(){
        return dic.get(r.nextInt(dic.size()));
    }

    public String[] generateWord(int count) {
        String[] word = new String[count];
        for (int i = 0; i < count; i++) {
            word[i] = getRandomWord();
        }
        return word;
    }

    public String[] generateWord(int count, boolean punctuation) {
        String[] word = new String[count];

        for (int i = 0; i < count; i++) {
            if (Math.random() > 0.8) {
                char c = randomChar();
                word[i] = getRandomWord() + c;
            }else {
                word[i] = getRandomWord();
            }

        }
        System.out.println("generate punctuation");
        return word;
    }

    public char randomChar() {
        return specialChar[r.nextInt(specialChar.length)];
    }

}
