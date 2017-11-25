package model;

/**
 * Block의 회전을 결정하는 회전행동자 Class 입니다.
 * 
 * @author 신승현
 */
public interface Spinnable {

	/**
	 * Block을 회전합니다.
	 * 
	 * @param coord
	 *            - 회전할 Block의 위치입니다.
	 */
	public void spin(Point[] coord);
}
