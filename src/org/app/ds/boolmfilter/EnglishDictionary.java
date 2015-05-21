/**
 * 
 */
package org.app.ds.boolmfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author anandm
 * 
 */
public class EnglishDictionary implements Dictionary {

	private BloomFilter<String> bloomFilter;

	/**
	 * @throws IOException
	 * 
	 */
	public EnglishDictionary() throws IOException {
		super();
		bloomFilter = new BloomFilter<String>(0.00000001, 200000);
		FileReader reader = new FileReader(new File("E:\\WorkData\\workspace\\data-structure-test\\src\\org\\app\\ds\\boolmfilter\\dictionary.txt"));
		BufferedReader bufferedReader = new BufferedReader(reader);
		String word = null;
		while ((word = bufferedReader.readLine()) != null) {
			bloomFilter.add(word);
		}

		bufferedReader.close();

	}

	@Override
	public boolean lookup(String word) {

		return bloomFilter.contains(word);
	}

}
