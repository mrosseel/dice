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
	private static int NR_OF_FACES = 6;

	/** positions of all neighbours.
	 * face 0: 1
	 * face 1: 2
	 * face 2: 3
	 * face 3: 4
	 * face 4: 5
	 * face 5: 6
	 *
	 * */
	private static int[][] faces = {
			{1,4,3,2}, // 1
			{0,3,2,5}, // 2
			{4,5,0,1}, // 3
			{5,4,0,1}, // 4
			{5,3,2,0}, // 5
			{3,4,2,1}  // 6
	};
//			{1,2,3,4},
//			{2,0,3,5},
//			{0,1,5,4},
//			{0,1,4,5},
//			{0,2,3,5},
//			{1,2,3,4}};

	/**                                               m
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
