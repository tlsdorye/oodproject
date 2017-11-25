package model;

/**
 * 이 Class 는 위치를 저장합니다.
 * 
 * @author 신승현
 */
public class Point {
	/** x좌표를 나타낼 변수입니다. */
	private int x;
	/** y좌표를 나타낼 변수입니다. */
	private int y;

	/**
	 * x,y의 좌표를 가지고 Point를 생성합니다.
	 * 
	 * @param x
	 *            - x좌표 입니다.
	 * @param y
	 *            - y좌표 입니다.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * x좌표를 반환합니다.
	 * 
	 * @return x좌표의 위치를 반환합니다.
	 */
	public int getX() {
		return x;
	}

	/**
	 * x좌표를 설정합니다.
	 * 
	 * @param x
	 *            - 설정할 x좌표 입니다.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * y좌표를 반환합니다.
	 * 
	 * @return y좌표의 위치를 반환합니다.
	 */
	public int getY() {
		return y;
	}

	/**
	 * y좌표를 설정합니다.
	 * 
	 * @param y
	 *            - 설정할 y좌표 입니다.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 현재위치를 설정해서 그 위치를 나타내는 Point를 반환합니다.
	 * 
	 * @param point
	 *            - 이동할 위치 입니다.
	 * @return 이동할 위치에서 TopLeftPoint를 더한 위치를 반환합니다.
	 */
	public Point setCurrentPoint(Point point) {
		return new Point(x + point.getX(), y + point.getY());
	}

}
