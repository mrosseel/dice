/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.data;

/**
 * @author mike
 *         <p>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class D6 extends Die {
    private static int NR_OF_FACES = 6;

    /**
     * positions of all neighbours.
     * face 0: 1
     * face 1: 2
     * face 2: 3
     * face 3: 4
     * face 4: 5
     * face 5: 6
     */
    private static int[][] faces = {
            {1, 4, 3, 2}, // value = 1, neighbour values = 2,3,4,5
            {0, 3, 2, 5}, // value = 2, neighbour values = 1,3,4,6
            {4, 5, 0, 1}, // value = 3, neighbour values = 1,2,5,6
            {5, 4, 0, 1}, // value = 4, neighbour values = 1,2,5,6
            {5, 3, 2, 0}, // value = 5, neighbour values = 1,3,4,6
            {3, 4, 2, 1}  // value = 6, neighbour values = 2,3,4,5
    };
//			{1,2,3,4},
//			{2,0,3,5},
//			{0,1,5,4},
//			{0,1,4,5},
//			{0,2,3,5},
//			{1,2,3,4}};

    /**
     * @param faces
     */
    public D6() {
        this(DieUtil.generateFaces(NR_OF_FACES));
    }

    public D6(int[] values) {
        super(faces);
        assert values.length == getNrOfFaces();
        setValues(values);
    }
}
