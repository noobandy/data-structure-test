/**
 * 
 */
package org.app.ds.others;

/**
 * @author anandm
 * 
 */
public class SpitNotSoArranger {

	private String[] tokens;
	private String[][] arrangedTokens;

	public SpitNotSoArranger(String[] tokens) {
		if (tokens == null || tokens.length != 9) {
			throw new IllegalArgumentException("tokens are not correct");
		}
		this.tokens = tokens;
	}

	private void arrangeTokens() {
		String largestToken = null;
		String[] tokensOfSizeThreeOrMore = null;
		String[] tokensOfSizeTwoOrMore = null;
		int it3 = 0;
		int it2 = 0;

		for (String token : tokens) {
			if (largestToken == null)
				largestToken = token;

			if (token.length() >= 4) {
				largestToken = token;
			} else if (token.length() >= 3) {
				tokensOfSizeThreeOrMore[it3++] = token;
			} else if (token.length() >= 2) {
				tokensOfSizeTwoOrMore[it2++] = token;
			}
		}
		
		

	}

	public String[][] getArrangedTokesn() {
		return arrangedTokens;
	}

	public void printTokens(boolean arranged) {
		if (arranged) {

		} else {

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
