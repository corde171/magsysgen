import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Interpreter {
    public static List<String> getAlphabetChars(int chars) {
        List<String> allChars = new ArrayList<>();
        List<String> alphabetGenerated = new ArrayList<>();
        try  {
            allChars = Files.readAllLines(Paths.get(System.getProperty("user.dir")+"/res/RunicCharacters.txt"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        for(int i = 0; i < chars; i++) {
            alphabetGenerated.add(allChars.remove(random.nextInt(allChars.size())));
        }

        return alphabetGenerated;
    }

    public static List<String> getAllWords() {
        List<String> wordList = new ArrayList<>();
        String basePath = System.getProperty("user.dir") + "/res/";

        try {
            wordList.addAll(Files.readAllLines(Paths.get(basePath + "Elements.txt")));
            wordList.addAll(Files.readAllLines(Paths.get(basePath + "Shapes_Evocation.txt")));
            wordList.addAll(Files.readAllLines(Paths.get(basePath + "Modifiers_Evocation.txt")));
            wordList.addAll(Files.readAllLines(Paths.get(basePath + "PowerLevels.txt")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

}
