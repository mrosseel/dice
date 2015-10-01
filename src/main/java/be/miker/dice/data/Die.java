/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.data;

/**
 * @author mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Die {

	private int[] values;
	private int[][] faces;
	private int nrOfFaces;


	/**
	 *
	 */
	public Die(int[][] faces) {
		this.faces = faces;
		this.nrOfFaces = faces.length;
	}




	/**
	 * @return Returns the faces.
	 */
	public int[][] getFaces() {
		return faces;
	}

	/**
	 * @return Returns the nrOfFaces.
	 */
	public int getNrOfFaces() {
		return nrOfFaces;
	}

	/**
	 * @return Returns the values.
	 */
	public int[] getValues() {
		return values;
	}

	/**
	 * Returns the value at the requested position.
	 * Position counting starts at 0.
	 *
	 * @param position the position, starting at 0.
	 *
	 * @return Returns the value at the requested position.
	 */
	public int getValue(int position) {
		return values[position];
	}

	/**
	 * @param values The values to set.
	 */
	public void setValues(int[] values) {
		assert values.length == getNrOfFaces();
		this.values = values;
	}

	/**
	 * Sets a given value to the specified posiion.
	 * Position counting starts at 0.
	 *
	 * @param position the position, starting at 0.
	 * @param value the value
	 */
	public void setValue(int position, int value) {
		this.values[position] = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	StringBuffer result = new StringBuffer();
	for (int i = 0; i < getNrOfFaces(); i++) {
			result.append("Pos = " + (i+1) + "\t - value = " + (values[i]+1) );
			 result.append("\tNeighbours = ");
			for (int neighbourCounter = 0; neighbourCounter < faces[i].length; neighbourCounter++) {
			   result.append((getValue(faces[i][neighbourCounter])+1) + " ");
            }

			 result.append('\n');

		}

		return result.toString();
	}
}
