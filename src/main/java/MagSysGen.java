import java.util.*;
import java.util.function.Consumer;

public class MagSysGen {
    public Map<String, String> dictionary;
    public List<String> alphabet;
    public int alphabetLength;
    public int magicWordLength;

    public MagSysGen() {
        this.alphabetLength = new Random().nextInt(20) + 5;
        this.alphabet = Interpreter.getAlphabetChars(alphabetLength);
        this.dictionary = new HashMap<>();
        this.magicWordLength = new Random().nextInt(4) + 2;
        this.makeDictionary();
    }

    private void makeDictionary() {
        List<String> allWords = Interpreter.getAllWords();
        int size = allWords.size();
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            // this makes longer alphabets more powerful because they have more spells.
            // this somewhat balances the endless frustration of trying to find spells.
            if(random.nextGaussian() * 10 + (this.alphabetLength - 10) < 0) {
                allWords.remove(random.nextInt(allWords.size()));
            }
        }

        for(int i = 0; i < allWords.size(); i++) {
            StringBuilder magicWord = new StringBuilder();
            for(int j = 0; j < this.magicWordLength; j++) {
                magicWord.append(alphabet.get(random.nextInt(alphabet.size())));
            }
            // this will result in more words not getting in the language, due to overwriting -- by design
            this.dictionary.put(magicWord.toString(), allWords.get(i));
        }
    }

    public static Consumer<Map.Entry<String, String>> consumer = new EntryConsumer();

    public static void main(String[] args) {
        MagSysGen sysGen = new MagSysGen();
        System.out.println(sysGen.alphabetLength);
        System.out.println(sysGen.alphabet);
        System.out.println(sysGen.magicWordLength);
        double possibleWords = (Math.pow(sysGen.alphabetLength, sysGen.magicWordLength));
        System.out.println("Possible words: " + possibleWords);
        System.out.println("Percentage of actual words over possible words: " + sysGen.dictionary.size()/possibleWords);
        Iterator<Map.Entry<String, String>> iterator = sysGen.dictionary.entrySet().iterator();

        iterator.forEachRemaining(consumer);
    }

    private static class EntryConsumer implements Consumer<Map.Entry<String, String>> {
        @Override
        public void accept(Map.Entry<String, String> stringStringEntry) {
            System.out.println(stringStringEntry.getKey() + ": " + stringStringEntry.getValue());
        }
    }
}
