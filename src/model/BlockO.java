package model;

import java.awt.Color;

/**
 * 이 BlockI 클래스는 Block을 상속하고 있으며, I Block을 구현한 Class 입니다.
 * 
 * @author 곽소정
 *
 */
public class BlockO extends Block {

	/**
	 * GameBoard 위에 I Block을 생성합니다.
	 * 
	 * @param gameBoard
	 *            - Block이 생성될 GameBoard 입니다.
	 */
	public BlockO(GameBoardSolo gameBoard) {
		super(gameBoard);
		initShape();
		blockIndex = 6;
	}

	/** Block 의 모양을 결정합니다. */
	@Override
	public void initShape() {
		setSpinBehavior(new SpinBlock2x2());
		setTopLeftPoint(new Point(1, 4));
		tempTopLeftPoint = new Point(2, 4);
		color = new Color(245, 180, 0);
		coord = new Point[] { new Point(-1, 0), new Point(-1, 1), new Point(0, 0), new Point(0, 1) };
		tempCoord = new Point[] { new Point(-1, 0), new Point(-1, 1), new Point(0, 0), new Point(0, 1) };
	}

	/** 블럭의 위치를 바꿉니다. */
	@Override
	public void changeCoord() {
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[0]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[1]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[2]), 6);
		gameBoard.changePoint(topLeftPoint.setCurrentPoint(coord[3]), 6);
	}

}
