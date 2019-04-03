package model;

import java.awt.Color;

/**
 * �� BlockI Ŭ������ Block�� ����ϰ� ������, I Block�� ������ Class �Դϴ�.
 * 
 * @author ������
 */
public class BlockJ extends Block {

	/**
	 * GameBoard ���� J Block�� �����մϴ�.
	 * 
	 * @param gameBoard
	 *            - Block�� ������ GameBoard �Դϴ�.
	 */
	public BlockJ(GameBoardSolo gameBoard) {
		super(gameBoard);
		initShape();
		blockIndex = 4;
	}

	/** Block �� ����� �����մϴ�. */
	@Override
	public void initShape() {
		setSpinBehavior(new SpinBlock3x3());
		setTopLeftPoint(new Point(1, 4));
		tempTopLeftPoint = new Point(2, 4);
		color = new Color(0, 92, 244);
		coord = new Point[] { new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1) };
		tempCoord = new Point[] { new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1) };
	}

	/** ���� ��ġ�� �ٲߴϴ�. */
	@Override
	public void changeCoord() {
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[0]), 4);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[1]), 4);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[2]), 4);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[3]), 4);
	}

}
