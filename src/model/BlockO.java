package model;

import java.awt.Color;

/**
 * �� BlockI Ŭ������ Block�� ����ϰ� ������, I Block�� ������ Class �Դϴ�.
 * 
 * @author ������
 *
 */
public class BlockO extends Block {

	/**
	 * GameBoard ���� I Block�� �����մϴ�.
	 * 
	 * @param gameBoard
	 *            - Block�� ������ GameBoard �Դϴ�.
	 */
	public BlockO(GameBoardSolo gameBoard) {
		super(gameBoard);
		initShape();
		blockIndex = 6;
	}

	/** Block �� ����� �����մϴ�. */
	@Override
	public void initShape() {
		setSpinBehavior(new SpinBlock2x2());
		setTopLeftPoint(new Point(1, 4));
		tempTopLeftPoint = new Point(2, 4);
		color = new Color(245, 180, 0);
		coord = new Point[] { new Point(-1, 0), new Point(-1, 1), new Point(0, 0), new Point(0, 1) };
		tempCoord = new Point[] { new Point(-1, 0), new Point(-1, 1), new Point(0, 0), new Point(0, 1) };
	}

	/** ���� ��ġ�� �ٲߴϴ�. */
	@Override
	public void changeCoord() {
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[0]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[1]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[2]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[3]), 6);
	}

}
