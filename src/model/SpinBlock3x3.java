package model;

/**
 * 3x3 Block�� ȸ���� �����ϴ� ȸ���ൿ�� Class �Դϴ�.
 * 
 * @author �Ž���
 *
 */
public class SpinBlock3x3 implements Spinnable {

	/** Block�� ȸ���մϴ�. */
	@Override
	public void spin(Point[] coord) {
		for (int i = 0; i < coord.length; i++) {
			int x = coord[i].getX();
			int y = coord[i].getY();
			coord[i].setX(y);
			coord[i].setY(-x);
		}
	}
}
