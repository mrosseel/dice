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
public class D6 extends Die {
	private static int NR_OF_FACES = 12; 
	
	/** positions of all neighbours. */
	private static int[][] faces = {
			{1,2,3,4},
			{2,0,3,5},
			{0,1,5,4},
			{0,1,4,5},
			{0,2,3,5},
			{1,2,3,4}};

	/**
	 * @param faces
	 */
	public D6() {
		this(DieUtil.generateFaces(NR_OF_FACES));
	}
	
	public D6(int [] values) {
		super(faces);
		assert values.length == getNrOfFaces();
		setValues(values);
	}
}
