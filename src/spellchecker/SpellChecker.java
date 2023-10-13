package spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;  
import java.util.Map.Entry;

public class SpellChecker {

    static String file = "Dictionary.txt";
    static ArrayList<String> dictionary = new ArrayList<String>();
    static HashMap<String, Integer> newlist = new HashMap<String, Integer>();

    public static void loadDic(String filename) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            if (line.length() > 2 && line.length() < 26) {
                dictionary.add(line);
            }
        }
    }

    public static void wordSuggester(String word) {
        int i;
        int correct=0;
        for (String s : dictionary) {
            i = LevDis.dis(s, word);
            if(i==0) correct=1;
            if (i < 3) {
                newlist.put(s, i);
            }
        }

        List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(newlist.entrySet());
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });

        Map<String, Integer> orderedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : entries) {
            orderedMap.put(entry.getKey(), entry.getValue());
        }
        if(correct==1)
        {
            System.out.print("The word is correctly spelled");
        }
        else System.out.print("The word is not correctly spelled");
        System.out.print("Suggested words: ");
        for (Entry<String, Integer> e : orderedMap.entrySet()){
        System.out.println(e.getKey());
    }
}

public static void main(String[] args) {
    System.out.println("~~SPELL CHECKER~~");
    try {
            loadDic(file);
        } catch (IOException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
    int check=1;
    LevDis.dis("sepling", "spelling");
    Scanner sc= new Scanner(System.in); 
        while(check==1)
        {
            System.out.print("Enter the word: ");
            String s=sc.nextLine();
            wordSuggester(s);
            System.out.print("Continue(1/0)? ");
            check=sc.nextInt();
        }
    }
    
}
