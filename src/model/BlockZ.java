package model;

import java.awt.Color;

/**
 * �� BlockI Ŭ������ Block�� ����ϰ� ������, I Block�� ������ Class �Դϴ�.
 * 
 * @author ������
 *
 */
public class BlockZ extends Block {

	/**
	 * GameBoard ���� I Block�� �����մϴ�.
	 * 
	 * @param gameBoard
	 *            - Block�� ������ GameBoard �Դϴ�.
	 */
	public BlockZ(GameBoardSolo gameBoard) {
		super(gameBoard);
		initShape();
		blockIndex = 1;
	}

	/** Block �� ����� �����մϴ�. */
	@Override
	public void initShape() {
		setSpinBehavior(new SpinBlock3x3());
		setTopLeftPoint(new Point(1, 4));
		tempTopLeftPoint = new Point(2, 4);
		color = new Color(244, 217, 245);
		coord = new Point[] { new Point(-1, -1), new Point(-1, 0), new Point(0, 0), new Point(0, 1) };
		tempCoord = new Point[] { new Point(-1, -1), new Point(-1, 0), new Point(0, 0), new Point(0, 1) };
	}

	/** ���� ��ġ�� �ٲߴϴ�. */
	@Override
	public void changeCoord() {
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[0]), 1);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[1]), 1);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[2]), 1);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[3]), 1);
	}

}
