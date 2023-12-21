import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomText {
    String dictionary;
    List<String> dic;

    public GenerateRandomText(String dictionary) {
        this.dictionary = dictionary;
        dic = loadDictionary();
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
        Random random = new Random();
        return dic.get(random.nextInt(dic.size()));
    }

    public String [] generateWord(int count){
        String [] word = new String[count];
        for (int i = 0; i < count; i++) {
            word[i] = getRandomWord();
        }
        return word;
    }

}