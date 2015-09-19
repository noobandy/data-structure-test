package org.app.ds.applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;

/**
 * 
 * @className:org.app.ds.applications.ConcatenatedWord.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 10:13:54 AM
 */

public class ConcatenatedWord {

    private class WordTuple {

        private String firstWord;

        private String secondWord;

        /**
         * @param firstWord
         * @param secondWord
         */
        public WordTuple(String firstWord, String secondWord) {
            super();
            this.firstWord = firstWord;
            this.secondWord = secondWord;
        }

    }

    private static final int DEFAULT_MAXLENGTH = -1;

    private int maxLength;

    // dictionary for membership test
    private Set<String> filter;

    // memoization
    private Map<String, List<WordTuple>> map;

    /**
     * @throws IOException
     * 
     */
    public ConcatenatedWord() {
        this(DEFAULT_MAXLENGTH);

    }

    /**
     * @param maxLength
     */
    public ConcatenatedWord(int maxLength) {
        super();
        this.maxLength = maxLength;
        filter = new HashSet<String>();
        map = new HashMap<String, List<WordTuple>>();
    }

    private void initFilter(File dictonaryFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dictonaryFile));

        String word = null;

        if (DEFAULT_MAXLENGTH == maxLength) {
            while ((word = br.readLine()) != null) {
                filter.add(word);
            }
        }
        else {
            while ((word = br.readLine()) != null) {
                if (word.length() < maxLength - 1) {
                    filter.add(word);
                }
            }
        }

        br.close();
    }

    public void init(String dictonaryFile) throws IOException {
        File file = new File(dictonaryFile);

        // order of init matters.
        initFilter(file);
    }

    public List<WordTuple> findWordTuples(String word) {

        if (DEFAULT_MAXLENGTH != maxLength && word.length() > maxLength) {
            throw new IllegalArgumentException("maxlenght exceeded");
        }

        List<WordTuple> wordTuples = map.get(word);

        if (wordTuples == null) {
            wordTuples = new ArrayList<ConcatenatedWord.WordTuple>();
            for (int i = 0; i < word.length() - 1; i++) {
                String firstWord = word.substring(0, i + 1);
                String secondWord = word.substring(i + 1, word.length());
                if (filter.contains(firstWord) && filter.contains(secondWord)) {
                    wordTuples.add(new WordTuple(firstWord, secondWord));
                }
            }
        }

        return wordTuples;
    }

    public static void main(String[] args) throws IOException {
        final ConcatenatedWord concatenatedWord = new ConcatenatedWord();

        concatenatedWord
                .init("E:\\WorkData\\workspace\\data-structure-test\\src\\org\\app\\ds\\boolmfilter\\dictionary.txt");

        final Scanner sc = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        menuManager.addMenu(new Menu(1, "Find Concatenated Words",
                new MenuCommand() {

                    @Override
                    public boolean execute() {
                        System.out.println("Enter the word");
                        String word = sc.nextLine();
                        for (WordTuple tuple : concatenatedWord
                                .findWordTuples(word)) {
                            System.out.println(tuple.firstWord + "+"
                                    + tuple.secondWord);
                        }
                        return true;
                    }
                }));

        menuManager.start();

    }
}
