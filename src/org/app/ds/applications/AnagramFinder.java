package org.app.ds.applications;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;

/**
 * 
 * @author anandm
 * 
 */
public class AnagramFinder {

    private Map<String, List<String>> wordMap = new HashMap<String, List<String>>();

    private boolean ready;

    /**
     * 
     */
    public AnagramFinder() {
        super();
        ready = false;

    }

    /**
     * You must initialize {@link AnagramFinder} before calling
     * {@link #getAnagrams(String)}
     * 
     * @param wordData {@link InputStream} in which each line contains one word. <br>
     *        one such dictionary can be downloaded from <a
     *        href="http://www.andrew.cmu.edu/course/15-121/dictionary.txt">
     *        Here</a>
     * @param characterSet
     * @throws IOException
     */
    public void init(InputStream wordData, String characterSet)
            throws IOException {

        if (ready) {
            throw new IllegalStateException("already initialized");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                wordData, characterSet));

        String word = null;

        while ((word = reader.readLine()) != null) {

            String key = wordToKey(word);

            List<String> angrams = wordMap.get(key);

            if (angrams == null) {
                angrams = new ArrayList<String>();
            }

            angrams.add(word);

            wordMap.put(key, angrams);
        }

        reader.close();
    }

    public List<String> getAnagrams(String word) {
        if (!ready) {
            throw new IllegalStateException("not initialized");
        }

        List<String> anagrams = wordMap.get(wordToKey(word));
        if (anagrams == null) {
            anagrams = new ArrayList<String>();
        }

        return anagrams;
    }

    private String wordToKey(String word) {

        char[] chars = word.toLowerCase().toCharArray();

        Arrays.sort(chars);

        return String.valueOf(chars);

    }

    public List<String> longestWordAnagrams() {
        Set<String> keySet = wordMap.keySet();

        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        Collections.sort(keyList, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {

                return o1.length() - o2.length();
            }
        });

        String longestKey = keyList.get(keyList.size() - 1);
        return wordMap.get(longestKey);
    }

    public List<String> mostWordAnagrams() {
        Collection<List<String>> values = wordMap.values();
        List<List<String>> valueList = new ArrayList<List<String>>(values);

        Collections.sort(valueList, new Comparator<List<String>>() {

            @Override
            public int compare(List<String> o1, List<String> o2) {

                return o1.size() - o2.size();
            }

        });

        return valueList.get(valueList.size() - 1);
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        // create
        final AnagramFinder anagramFinder = new AnagramFinder();
        // initialize
        anagramFinder.init(new URL(
                "http://www.andrew.cmu.edu/course/15-121/dictionary.txt")
                .openStream(), "UTF-8");

        MenuManager menuManager = new MenuManager();

        menuManager.addMenu(new Menu(1, "Find anagrams of a word",
                new MenuCommand() {

                    @Override
                    public boolean execute() {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the word");
                        String word = scanner.nextLine();
                        System.out.println("Anagrams of word : " + word);
                        for (String anagram : anagramFinder.getAnagrams(word)) {
                            System.out.println(anagram);
                        }
                        return true;
                    }
                }));

        menuManager.addMenu(new Menu(2, "Longest Word Anagrams",
                new MenuCommand() {

                    @Override
                    public boolean execute() {
                        System.out.println("Longest Word Anagrams ....");
                        for (String word : anagramFinder.longestWordAnagrams()) {
                            System.out.println(word);
                        }
                        return true;
                    }
                }));

        menuManager.addMenu(new Menu(3, "Most Word Anagrams",
                new MenuCommand() {

                    @Override
                    public boolean execute() {
                        System.out.println("Most Word Anagrams ....");
                        for (String word : anagramFinder.mostWordAnagrams()) {
                            System.out.println(word);
                        }
                        return true;
                    }
                }));

        menuManager.start();

    }

}
