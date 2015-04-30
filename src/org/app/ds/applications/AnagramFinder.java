package org.app.ds.applications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnagramFinder {

	private static final int query(String source, String query) {
		int foundAnagrams = 0;

		return foundAnagrams;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Please specify input file");
			System.exit(0);
		} else {
			File inputFile = new File(args[0]);

			File outputFile = new File(args[1]);

			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				br = new BufferedReader(new FileReader(inputFile));
				bw = new BufferedWriter(new FileWriter(outputFile));
				int numberOfTestCases = Integer.parseInt(br.readLine());

				for (int i = 0; i < numberOfTestCases; i++) {
					String source = br.readLine();
					int numberOfQueries = Integer.parseInt(br.readLine());

					for (int j = 0; j < numberOfQueries; j++) {
						String query = br.readLine();
						bw.append(String.valueOf(query(source, query)));
						bw.append(System.getProperty("line.separator"));
					}
				}

			} finally {

				if (br != null) {
					br.close();
				}

				if (bw != null) {
					bw.close();
				}

			}

		}

	}
}
