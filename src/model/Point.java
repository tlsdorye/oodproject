package model;

/**
 * �� Class �� ��ġ�� �����մϴ�.
 * 
 * @author �Ž���
 */
public class Point {
	/** x��ǥ�� ��Ÿ�� �����Դϴ�. */
	private int x;
	/** y��ǥ�� ��Ÿ�� �����Դϴ�. */
	private int y;

	/**
	 * x,y�� ��ǥ�� ������ Point�� �����մϴ�.
	 * 
	 * @param x
	 *            - x��ǥ �Դϴ�.
	 * @param y
	 *            - y��ǥ �Դϴ�.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * x��ǥ�� ��ȯ�մϴ�.
	 * 
	 * @return x��ǥ�� ��ġ�� ��ȯ�մϴ�.
	 */
	public int getX() {
		return x;
	}

	/**
	 * x��ǥ�� �����մϴ�.
	 * 
	 * @param x
	 *            - ������ x��ǥ �Դϴ�.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * y��ǥ�� ��ȯ�մϴ�.
	 * 
	 * @return y��ǥ�� ��ġ�� ��ȯ�մϴ�.
	 */
	public int getY() {
		return y;
	}

	/**
	 * y��ǥ�� �����մϴ�.
	 * 
	 * @param y
	 *            - ������ y��ǥ �Դϴ�.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * ������ġ�� �����ؼ� �� ��ġ�� ��Ÿ���� Point�� ��ȯ�մϴ�.
	 * 
	 * @param point
	 *            - �̵��� ��ġ �Դϴ�.
	 * @return �̵��� ��ġ���� TopLeftPoint�� ���� ��ġ�� ��ȯ�մϴ�.
	 */
	public Point setCurrentPoint(Point point) {
		return new Point(x + point.getX(), y + point.getY());
	}

}
