package model;

/**
 * 4x4 Block의 회전을 결정하는 회전행동자 Class 입니다.
 * 
 * @author 신승현
 *
 */
public class SpinBlock4x4 implements Spinnable {

	/** Block을 회전합니다. */
	@Override
	public void spin(Point[] coord) {
		for (int i = 0; i < coord.length; i++) {
			int x = coord[i].getX();
			int y = coord[i].getY();
			coord[i].setX(-y);
			coord[i].setY(-x);
		}
	}
}
