/*
 * Created on Jun 13, 2004
 *  
 */
package be.miker.dice.data;

import java.util.Random;

/**
 * @author mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DieUtil {
	
	private static Random random = new Random();
	
	/**
	 * Generate a default value list for a given number of faces.
	 * 
	 * @param nrOfFaces
	 *            the number of faces on the die
	 * @return array containing 0 1 ... nrOfFaces -1
	 */
	public static int[] generateFaces(int nrOfFaces) {
		int[] result = new int[nrOfFaces];
		for (int i = 0; i < nrOfFaces; i++) {
			result[i] = i;
		}
		return result;
	}

	public static boolean testFacesCorrectness(Die theDie) {
		int[][] faces = theDie.getFaces();
		int[] faceAccounting = new int[faces.length];
		for (int faceCounter = 0; faceCounter < faces.length; faceCounter++) {
			for (int neighbourCounter = 0; neighbourCounter < faces[faceCounter].length; neighbourCounter++) {
				faceAccounting[faces[faceCounter][neighbourCounter]]++;
			}
		}
		int constant = faceAccounting[0];
		boolean correctness = true;
		for (int faceCounter = 0; faceCounter < faceAccounting.length; faceCounter++) {
			System.out.println("entry = " + faceAccounting[faceCounter]);
			if (faceAccounting[faceCounter] != constant) {
				correctness = false;
				break;
			}
		}
		return correctness;
	}
	
	public static int getRandomDieThrow(int nrOfFaces) {
		return random.nextInt(nrOfFaces);
	}
}