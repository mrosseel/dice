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
public class D20 extends Die {
	
	private static int D20_NR_OF_FACES = 20; 
	
	/** positions of all neighbours. */
	private static int[][] faces = {
			{6,18,12},
			{19 ,11 ,17 },
			{18 ,15 ,16 },
			{17 ,13 ,10 },
			{12 ,17 ,14 },
			{8 ,13 ,15 },
			{0 ,14 ,16 },
			{19 ,15 ,9 },
			{18 ,10 ,5 },
			{11 ,7 ,16 },
			{3 ,8 ,12 },
			{9 ,14 ,1 },
			{0 ,4 ,10 },
			{3 ,5 ,19 },
			{4 ,6 ,11 },
			{5 ,2 ,7 },
			{2 ,6 ,9 },
			{1 ,3 ,4 },
			{0 ,2 ,8 },
			{1 ,7 ,13 }};

	/**
	 * @param faces
	 */
	public D20() {
		this(DieUtil.generateFaces(D20_NR_OF_FACES));
	}
	
	public D20(int [] values) {
		super(faces);
		assert values.length == getNrOfFaces();
		setValues(values);
	}
	
}
