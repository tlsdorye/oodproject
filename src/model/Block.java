package model;

import java.awt.Color;

/**
 * 이 Block Class는 블록의 위치, Color, 회전, 이동 이 구현된 Class 입니다.
 * 
 * @author 곽소정
 */
public abstract class Block {

	/** Spin 행동자 를 담당할 Spinnable Type 의 변수입니다. */
	private Spinnable spinnable;
	/** Block이 올라갈 GameBoardSolo Type 의 변수입니다. */
	protected GameBoardSolo gameBoard;
	/** Block의 행동 기준이 될 왼쪽위 위치좌표입니다. */
	protected Point topLeftPoint;
	/** topLeftPoint의 값을 임시로 저장할 변수입니다. */
	protected Point tempTopLeftPoint;
	/** Block의 위치를 저장할 Point Type Array 변수입니다. */
	protected Point[] coord;
	/** coord의 값을 임시로 저장할 변수입니다. */
	protected Point[] tempCoord;
	/** Block 의 색을 담는 변수입니다. */
	protected Color color;

	/**
	 * Block 을 생성합니다.
	 * 
	 * @param gameBoard
	 *            - Block이 생성될 GameBoard 입니다.
	 */
	public Block(GameBoardSolo gameBoard) {
		this.gameBoard = gameBoard;
	}

	/** Block의 모양을 결정합니다. */
	public abstract void initShape(); // 상속받는 블럭에서 구현

	/** 블럭의 위치를 바꿉니다. */
	public abstract void changeCoord(); // 상속받는 블럭에서 구현

	/**
	 * Block의 Color를 반환합니다.
	 * 
	 * @return Block의 Color 입니다.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Block의 Color를 설정합니다.
	 * 
	 * @param color
	 *            - Block의 Color로 설정될 Color 객체 입니다.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Block 의 TopLeftPoint를 반환합니다.
	 * 
	 * @return TopLeftPoint의 위치 입니다.
	 */
	public Point getTopLeftPoint() {
		return topLeftPoint;
	}

	/**
	 * Block 의 TopLeftPoint를 설정합니다.
	 * 
	 * @param topLeftPoint
	 *            - 로 현재 TopLeftPoint를 설정합니다.
	 */
	public void setTopLeftPoint(Point topLeftPoint) {
		this.topLeftPoint = topLeftPoint;
	}

	/**
	 * Block 의 회전행동자를 설정합니다.
	 * 
	 * @param spinnable
	 *            - 로 회전행동자를 설정합니다.
	 */
	public void setSpinBehavior(Spinnable spinnable) {
		this.spinnable = spinnable;
	}

	/** Block GameBoard에 고정하고, next Block을 설정합니다. */
	public void fixedAndSetNextBlock() {
		gameBoard.fixedAndSetNextBlock();
	}

	/**
	 * 회전할 때 벽이나 다른 도형에 충돌하는지 확인합니다.
	 * 
	 * @param topLeftPoint
	 *            - 도형이 회전할때 기준이 되는 좌표입니다.
	 * @return 회전할 때 충돌하면 true를, 충돌하지않으면 false를 반환합니다.
	 */
	public boolean isCollisionSpin(Point topLeftPoint) { // 회전가능여부
		spinnable.spin(tempCoord);
		for (int i = 0; i < tempCoord.length; i++) {
			Point tempNextPoint = topLeftPoint.setCurrentPoint(tempCoord[i]);
			if (gameBoard.isCollistionSpin(tempNextPoint))
				return true;
		}
		return false;
	}

	/**
	 * 이동할 때 벽이나 다른도형에 충돌하는지 확인합니다.
	 * 
	 * @param topLeftPoint
	 *            - 도형이 회전할때 기준이 되는 좌표입니다.
	 * @return 이동할 때 충돌하면 true를, 충돌하지않으면 false를 반환합니다.
	 */
	public boolean isCollisionMove(Point topLeftPoint) {
		for (int i = 0; i < coord.length; i++) {
			Point tempNextPoint = topLeftPoint.setCurrentPoint(coord[i]);
			if (gameBoard.isCollision(tempNextPoint))
				return true;
		}
		return false;
	}

	/** Block 회전을 실행합니다. */
	public void performSpin() { // 블록 스핀하기
		gameBoard.revertMatrix();
		if (!isCollisionSpin(topLeftPoint)) {
			spinnable.spin(coord);
			changeCoord();
		} else {
			changeCoord();
			for (int i = 0; i < coord.length; i++) {
				tempCoord[i].setX(coord[i].getX());
				tempCoord[i].setY(coord[i].getY());
			}
		}
	}

	/** Block 을 왼쪽으로 이동합니다. */
	public void moveLeft() {
		tempTopLeftPoint.setY(topLeftPoint.getY() - 1);
		tempTopLeftPoint.setX(topLeftPoint.getX());
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
		} else {
			topLeftPoint.setY(tempTopLeftPoint.getY() + 1);
			changeCoord();
		}
	}

	/** Block 을 오른쪽으로 이동합니다. */
	public void moveRight() {
		tempTopLeftPoint.setY(topLeftPoint.getY() + 1);
		tempTopLeftPoint.setX(topLeftPoint.getX());
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
		} else {
			topLeftPoint.setY(tempTopLeftPoint.getY() - 1);
			changeCoord();
		}
	}

	/** Block 을 아래로 이동합니다. */
	public void moveDown() {
		tempTopLeftPoint.setX(topLeftPoint.getX() + 1);
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
		} else {
			topLeftPoint.setX(tempTopLeftPoint.getX() - 1);
			changeCoord();
			fixedAndSetNextBlock();
		}
	}

	/**
	 * Block 아래로 한칸 내릴 수 있는지 확인합니다.
	 * 
	 * @return 아래로 한칸 이동할 수 있다면 true를, 없다면 false를 반환합니다.
	 */
	public boolean isMoveDown() {
		Point x = new Point(topLeftPoint.getX() + 1, topLeftPoint.getY());
		gameBoard.revertMatrix();
		if (!isCollisionMove(x))
			return true;
		else
			return false;
	}

	/** Block 을 바로 떨어트립니다. */
	public void fastDown() {
		while (isMoveDown())
			moveDown();
		changeCoord();
		fixedAndSetNextBlock();
	}

	/** Block 을 한칸 떨어트립니다. */
	public void drop() {
		tempTopLeftPoint.setX(topLeftPoint.getX() + 1);
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
		} else {
			topLeftPoint.setX(tempTopLeftPoint.getX() - 1);
			changeCoord();
			fixedAndSetNextBlock();
		}
		gameBoard.update();
	}

}
