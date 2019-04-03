package model;

import java.awt.Color;

/**
 * �� Block Class�� ����� ��ġ, Color, ȸ��, �̵� �� ������ Class �Դϴ�.
 * 
 * @author ������
 */
public abstract class Block {

	/** Spin �ൿ�� �� ����� Spinnable Type �� �����Դϴ�. */
	private Spinnable spinnable;
	/** Block�� �ö� GameBoardSolo Type �� �����Դϴ�. */
	protected GameBoardSolo gameBoard;
	/** Block�� �ൿ ������ �� ������ ��ġ��ǥ�Դϴ�. */
	protected Point topLeftPoint;
	/** topLeftPoint�� ���� �ӽ÷� ������ �����Դϴ�. */
	protected Point tempTopLeftPoint;
	/** Block�� ��ġ�� ������ Point Type Array �����Դϴ�. */
	protected Point beforeTopLeftPoint;
	
	protected Point[] coord;
	/** coord�� ���� �ӽ÷� ������ �����Դϴ�. */
	protected Point[] tempCoord;
	/** Block �� ���� ��� �����Դϴ�. */
	protected Color color;
	
	public int blockIndex;

	/**
	 * Block �� �����մϴ�.
	 * 
	 * @param gameBoard
	 *            - Block�� ������ GameBoard �Դϴ�.
	 */
	public Block(GameBoardSolo gameBoard) {
		this.gameBoard = gameBoard;
	}

	/** Block�� ����� �����մϴ�. */
	public abstract void initShape(); // ��ӹ޴� ������ ����

	/** ���� ��ġ�� �ٲߴϴ�. */
	public abstract void changeCoord(); // ��ӹ޴� ������ ����

	/**
	 * Block�� Color�� ��ȯ�մϴ�.
	 * 
	 * @return Block�� Color �Դϴ�.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Block�� Color�� �����մϴ�.
	 * 
	 * @param color
	 *            - Block�� Color�� ������ Color ��ü �Դϴ�.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Block �� TopLeftPoint�� ��ȯ�մϴ�.
	 * 
	 * @return TopLeftPoint�� ��ġ �Դϴ�.
	 */
	public Point getTopLeftPoint() {
		return topLeftPoint;
	}

	/**
	 * Block �� TopLeftPoint�� �����մϴ�.
	 * 
	 * @param topLeftPoint
	 *            - �� ���� TopLeftPoint�� �����մϴ�.
	 */
	public void setTopLeftPoint(Point topLeftPoint) {
		this.topLeftPoint = topLeftPoint;
		
	}

	/**
	 * Block �� ȸ���ൿ�ڸ� �����մϴ�.
	 * 
	 * @param spinnable
	 *            - �� ȸ���ൿ�ڸ� �����մϴ�.
	 */
	public void setSpinBehavior(Spinnable spinnable) {
		this.spinnable = spinnable;
	}

	/** Block GameBoard�� �����ϰ�, next Block�� �����մϴ�. */
	public void fixedAndSetNextBlock() {
		gameBoard.fixedAndSetNextBlock();
	}

	/**
	 * ȸ���� �� ���̳� �ٸ� ������ �浹�ϴ��� Ȯ���մϴ�.
	 * 
	 * @param topLeftPoint
	 *            - ������ ȸ���Ҷ� ������ �Ǵ� ��ǥ�Դϴ�.
	 * @return ȸ���� �� �浹�ϸ� true��, �浹���������� false�� ��ȯ�մϴ�.
	 */
	public boolean isCollisionSpin(Point topLeftPoint) { // ȸ�����ɿ���
		spinnable.spin(tempCoord);
		for (int i = 0; i < tempCoord.length; i++) {
			Point tempNextPoint = topLeftPoint.setCurrentPoint(tempCoord[i]);
			if (gameBoard.isCollistionSpin(tempNextPoint))
				return true;
		}
		return false;
	}

	/**
	 * �̵��� �� ���̳� �ٸ������� �浹�ϴ��� Ȯ���մϴ�.
	 * 
	 * @param topLeftPoint
	 *            - ������ ȸ���Ҷ� ������ �Ǵ� ��ǥ�Դϴ�.
	 * @return �̵��� �� �浹�ϸ� true��, �浹���������� false�� ��ȯ�մϴ�.
	 */
	public boolean isCollisionMove(Point topLeftPoint) {
		for (int i = 0; i < coord.length; i++) {
			Point tempNextPoint = topLeftPoint.setCurrentPoint(coord[i]);
			if (gameBoard.isCollision(tempNextPoint))
				return true;
		}
		return false;
	}

	/**
	 * Block �Ʒ��� ��ĭ ���� �� �ִ��� Ȯ���մϴ�.
	 * 
	 * @return �Ʒ��� ��ĭ �̵��� �� �ִٸ� true��, ���ٸ� false�� ��ȯ�մϴ�.
	 */
	public boolean isMoveDown() {
		Point x = new Point(topLeftPoint.getX() + 1, topLeftPoint.getY());
		gameBoard.revertMatrix();
		if (!isCollisionMove(x))
			return true;
		else
			return false;
	}
	
	/** Block ȸ���� �����մϴ�. */
	public void performSpin() { // ��� �����ϱ�
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
		gameBoard.update();
	}

	/** Block �� �������� �̵��մϴ�. */
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
		gameBoard.update();
	}

	/** Block �� ���������� �̵��մϴ�. */
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
		gameBoard.update();
	}

	/** Block �� �Ʒ��� �̵��մϴ�. */
	public void moveDown() {
		tempTopLeftPoint.setX(topLeftPoint.getX() + 1);
		tempTopLeftPoint.setY(topLeftPoint.getY());
		//System.out.println(tempTopLeftPoint.getX()+" "+tempTopLeftPoint.getY());
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

	/** Block �� �ٷ� ����Ʈ���ϴ�. */
	public void fastDown() {
		
		while (isMoveDown())
			moveDown();
		changeCoord();
		fixedAndSetNextBlock();
		gameBoard.update();
	}

	/** Block �� ��ĭ ����Ʈ���ϴ�. */
	public void drop() {
		tempTopLeftPoint.setX(topLeftPoint.getX() + 1);
		tempTopLeftPoint.setY(topLeftPoint.getY());
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
	
	public void AIPerformSpin() {
		gameBoard.revertMatrix();
		if (!isCollisionSpin(topLeftPoint)) {
			spinnable.spin(coord);
		} else {
			changeCoord();
			for (int i = 0; i < coord.length; i++) {
				tempCoord[i].setX(coord[i].getX());
				tempCoord[i].setY(coord[i].getY());
			}
		}
	}
	
	public int AIMoveLeft() {
		tempTopLeftPoint.setY(topLeftPoint.getY() - 1);
		tempTopLeftPoint.setX(topLeftPoint.getX());
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
			return 1;
		} else {
			topLeftPoint.setY(tempTopLeftPoint.getY() + 1);
			changeCoord();
			return 0;
		}
	}
	
	public int AIMoveRight() {
		tempTopLeftPoint.setY(topLeftPoint.getY() + 1);
		tempTopLeftPoint.setX(topLeftPoint.getX());
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
			return 1;
		} else {
			topLeftPoint.setY(tempTopLeftPoint.getY() - 1);
			changeCoord();
			return 0;
		}
	}
	
	public void AIMoveDown() {
		tempTopLeftPoint.setX(topLeftPoint.getX() + 1);
		tempTopLeftPoint.setY(topLeftPoint.getY());
		gameBoard.revertMatrix();
		if (!isCollisionMove(tempTopLeftPoint)) {
			setTopLeftPoint(tempTopLeftPoint);
			changeCoord();
		} else {
			topLeftPoint.setX(tempTopLeftPoint.getX() - 1);
			changeCoord();
		}
	}
	
	public void AIFastDown() {
		while (isMoveDown())
			AIMoveDown();
		changeCoord();
	}
	

}
